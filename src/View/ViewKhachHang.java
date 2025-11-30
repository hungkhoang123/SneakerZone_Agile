/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.KhachHang;
import java.util.ArrayList;
import Model.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIETTECH88
 */
public class ViewKhachHang extends javax.swing.JPanel {

    private DefaultTableModel mol = new DefaultTableModel();
    private KhachHang khachHang = new KhachHang();
    private int index = -1;

    /**
     * Creates new form ViewKhachHang
     */
    public ViewKhachHang() {
        initComponents();
        dongLabel();
        fillTableKhachHang(khachHang.getall());
    }

    private void dongLabel() {
        lblThongBaoEmail.setVisible(false);
        lblThongBaoMaKhachHang.setVisible(false);
        lblThongBaoSdt.setVisible(false);
        lblThongBaoTenKhachHang.setVisible(false);
    }

    private boolean kiemTraRong() {
        boolean check = true;

        // Mã nhân viên
        if (txtMaKhachHang.getText().trim().isEmpty()) {
            lblThongBaoMaKhachHang.setText("Mã nhân viên không được để trống");
            lblThongBaoMaKhachHang.setVisible(true);
            check = false;
        } else if (!txtMaKhachHang.getText().trim().matches("^KH\\d{4,}$")) {
            lblThongBaoMaKhachHang.setText("Mã Khách hàng không hợp lệ\n VD: KH0003");
            lblThongBaoMaKhachHang.setVisible(true);
            check = false;
        } else {
            lblThongBaoMaKhachHang.setVisible(false);
        }

        // Tên nhân viên
        if (txtTenKhachHang.getText().trim().isEmpty()) {
            lblThongBaoTenKhachHang.setText("Tên khách hàng không được để trống");
            lblThongBaoTenKhachHang.setVisible(true);
            check = false;
        } else {
            lblThongBaoTenKhachHang.setVisible(false);
        }

        // Email
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            lblThongBaoEmail.setText("Email không hợp lệ");
            lblThongBaoEmail.setVisible(true);
            check = false;
        } else {
            lblThongBaoEmail.setVisible(false);
        }

        // Số điện thoại
        if (txtSoDienThoai.getText().trim().isEmpty()) {
            lblThongBaoSdt.setText("Số điện thoại không được để trống");
            lblThongBaoSdt.setVisible(true);
            check = false;
        } else if (!txtSoDienThoai.getText().trim().matches("^0\\d{9}$")) {
            lblThongBaoSdt.setText("Số điện thoại không hợp lệ");
            lblThongBaoSdt.setVisible(true);
            check = false;
        } else {
            lblThongBaoSdt.setVisible(false);
        }

        return check;
    }

    private boolean kiemTraTonTai(String maKhachHang, String soDienThoai, String email, String maKhachHangCanSua) {
        ArrayList<ModelKhachHang> danhSachKhachhang = khachHang.getall();
        boolean check = true;
        for (ModelKhachHang x : danhSachKhachhang) {
            if (maKhachHangCanSua.equalsIgnoreCase(x.getMaKhachHang())) {
                continue;
            }
            if (maKhachHang.equalsIgnoreCase(x.getMaKhachHang())) {
                lblThongBaoMaKhachHang.setText("Mã khách hàng đã tồn tại");
                lblThongBaoMaKhachHang.setVisible(true);
                check = false;
            }

            if (soDienThoai.equalsIgnoreCase(x.getSdt())) {
                lblThongBaoSdt.setText("Số điện thoại đã được sử dụng");
                lblThongBaoSdt.setVisible(true);
                check = false;
            }
            if (email.equalsIgnoreCase(x.getEmail())) {
                lblThongBaoEmail.setText("Email đã được sử dụng");
                lblThongBaoEmail.setVisible(true);
                check = false;
            }
        }
        return check;
    }

    private ModelKhachHang readForm() {
        String ma = txtMaKhachHang.getText().trim();
        String ten = txtTenKhachHang.getText().trim();
        String diachi = txtDiaChi.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        String email = txtEmail.getText().trim();

        // Xử lý giới tính
        Boolean gioitinh = null;
        if (rdoNam.isSelected()) {
            gioitinh = false;
        } else if (rdoNu.isSelected()) {
            gioitinh = true;
        }

        if (gioitinh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
            return null;
        }

        return new ModelKhachHang(ma, ten, diachi, sdt, email, gioitinh);
    }

    private void fillTableKhachHang(ArrayList<ModelKhachHang> list) {
        mol = (DefaultTableModel) tblDanhSachKhachHang.getModel();
        mol.setRowCount(0);
        for (ModelKhachHang x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void fillTableHoaDon(ArrayList<ModelHoaDon> list) {
        mol = (DefaultTableModel) tblHoaDon.getModel();
        mol.setRowCount(0);
        for (ModelHoaDon x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void showData() {
        txtMaKhachHang.setText(tblDanhSachKhachHang.getValueAt(index, 0).toString());
        txtTenKhachHang.setText(tblDanhSachKhachHang.getValueAt(index, 1).toString());
        txtDiaChi.setText(tblDanhSachKhachHang.getValueAt(index, 2).toString());
        txtSoDienThoai.setText(tblDanhSachKhachHang.getValueAt(index, 3).toString());
        txtEmail.setText(tblDanhSachKhachHang.getValueAt(index, 4).toString());
        String gioiTinh = tblDanhSachKhachHang.getValueAt(index, 5).toString();
        if (gioiTinh.equals("Nam")) {
            rdoNam.setSelected(true);
        } else if (gioiTinh.equals("Nữ")) {
            rdoNu.setSelected(true);
        }
    }

    private void layGiaoDichGanNhat(JTable table, int thoiGianColumnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate = null;
        int maxRow = -1;

        for (int i = 0; i < table.getRowCount(); i++) {
            try {
                String dateStr = table.getValueAt(i, thoiGianColumnIndex).toString();
                Date currentDate = sdf.parse(dateStr);

                if (maxDate == null || currentDate.after(maxDate)) {
                    maxDate = currentDate;
                    maxRow = i;
                }
            } catch (Exception e) {
                System.out.println("Không thể parse thời gian ở dòng " + i);
            }
        }

        if (maxDate != null) {
            txtThoiGianGiaoDich.setText(sdf.format(maxDate));
        } else {
            txtThoiGianGiaoDich.setText("Không có dữ liệu");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tabKhachHang = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachKhachHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        lblSoDienThoai = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblThongBaoTenKhachHang = new javax.swing.JLabel();
        lblThongBaoSdt = new javax.swing.JLabel();
        lblThongBaoEmail = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblMaKhachHang = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblThongBaoMaKhachHang = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblThoigianGiaoDich = new javax.swing.JLabel();
        lblSoLuongDonHang = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnTaoMoi = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        txtThoiGianGiaoDich = new javax.swing.JLabel();
        txtSoLuongDonHang = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1078, 350));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        tabKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        tabKhachHang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabKhachHangStateChanged(evt);
            }
        });

        tblDanhSachKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Email", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachKhachHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabKhachHang.addTab("Danh sách khách hàng", jPanel3);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Tên nhân viên", "Mã khuyến mãi", "Ngày mua hàng", "Tổng tiền", "Giá sau giảm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        tabKhachHang.addTab("Lịch Sử giao dịch", jPanel4);

        jPanel2.add(tabKhachHang);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1078, 35));

        lblTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTimKiem.setText("Tìm kiếm:");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(821, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(1, 3));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTenKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenKhachHang.setText("Tên khách hàng");
        jPanel6.add(lblTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 26, -1, -1));

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenKhachHang.setPreferredSize(new java.awt.Dimension(272, 21));
        jPanel6.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 49, -1, -1));

        lblSoDienThoai.setBackground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại");
        jPanel6.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 133, -1, -1));

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoDienThoai.setPreferredSize(new java.awt.Dimension(272, 21));
        jPanel6.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 162, -1, -1));

        lblEmail.setBackground(new java.awt.Color(255, 255, 255));
        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setText("Email");
        jPanel6.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 236, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtEmail.setPreferredSize(new java.awt.Dimension(272, 21));
        jPanel6.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 271, -1, -1));

        lblThongBaoTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoTenKhachHang.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenKhachHang.setText("Tên khách hàng không thể để trống");
        jPanel6.add(lblThongBaoTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 76, 272, -1));

        lblThongBaoSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoSdt.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoSdt.setText("Số điện thoại không thể để trống");
        jPanel6.add(lblThongBaoSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 189, 272, -1));

        lblThongBaoEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoEmail.setText("Email không đúng định dạng");
        jPanel6.add(lblThongBaoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 298, 272, -1));

        jPanel5.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        lblMaKhachHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMaKhachHang.setText("Mã khách hàng");
        jPanel7.add(lblMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 21, -1, -1));

        lblDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDiaChi.setText("Địa chỉ");
        jPanel7.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 128, -1, -1));

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 163, 272, -1));

        lblGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        jPanel7.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 233, -1, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setText("Nam");
        jPanel7.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 268, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        jPanel7.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 268, -1, -1));

        lblThongBaoMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoMaKhachHang.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoMaKhachHang.setText("Mã khách hàng không đúng định dạng");
        jPanel7.add(lblThongBaoMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 77, 272, -1));

        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 50, 272, -1));

        jPanel5.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThoigianGiaoDich.setBackground(new java.awt.Color(255, 255, 255));
        lblThoigianGiaoDich.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThoigianGiaoDich.setText("Thời gian giao dịch gần nhất");
        jPanel8.add(lblThoigianGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 20, -1, -1));

        lblSoLuongDonHang.setBackground(new java.awt.Color(255, 255, 255));
        lblSoLuongDonHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoLuongDonHang.setText("Số lượng đơn hàng");
        jPanel8.add(lblSoLuongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 123, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.setPreferredSize(new java.awt.Dimension(77, 40));
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 253, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setPreferredSize(new java.awt.Dimension(77, 40));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 253, -1, -1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setPreferredSize(new java.awt.Dimension(77, 40));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 253, -1, -1));

        txtThoiGianGiaoDich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThoiGianGiaoDich.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtThoiGianGiaoDich.setPreferredSize(new java.awt.Dimension(280, 21));
        jPanel8.add(txtThoiGianGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 49, 272, -1));

        txtSoLuongDonHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuongDonHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoLuongDonHang.setPreferredSize(new java.awt.Dimension(280, 21));
        jPanel8.add(txtSoLuongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 158, 272, -1));

        jPanel5.add(jPanel8);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tabKhachHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabKhachHangStateChanged
        // TODO add your handling code here:
        int i = tabKhachHang.getSelectedIndex();
        if (i == 1 && index == -1) {
            JOptionPane.showMessageDialog(this, "Chưa có khách hàng nào được chọn");
        } else if (i == 1) {
            String maKhachHang = tblDanhSachKhachHang.getValueAt(index, 0).toString();
            fillTableHoaDon(khachHang.danhSachHoaDon(maKhachHang));
        }
    }//GEN-LAST:event_tabKhachHangStateChanged

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        //        // TODO add your handling code here:
        //        index = tblHoaDon.getSelectedRow();
        //        int i = Integer.parseInt(tblHoaDon.getValueAt(index, 0).toString());
        //        filltableHDCT(hoaDon.danhSachHoaDonChiTiet(i));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblDanhSachKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhachHangMouseClicked
        // TODO add your handling code here:
        index = tblDanhSachKhachHang.getSelectedRow();
        showData();
        fillTableHoaDon(khachHang.danhSachHoaDon(tblDanhSachKhachHang.getValueAt(index, 0).toString()));
        txtSoLuongDonHang.setText(String.valueOf(tblHoaDon.getRowCount()));
        layGiaoDichGanNhat(tblHoaDon, 4);
        dongLabel();
    }//GEN-LAST:event_tblDanhSachKhachHangMouseClicked

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        boolean kiemTraRong1 = kiemTraRong();
        boolean check = kiemTraTonTai(txtMaKhachHang.getText(), txtSoDienThoai.getText(), txtEmail.getText(), "");
        if (kiemTraRong1 && check && khachHang.themKhachHang(readForm()) != 0) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            fillTableKhachHang(khachHang.getall());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng muốn sửa");
        } else {
            boolean kiemTraRong1 = kiemTraRong();
            String maNV = tblDanhSachKhachHang.getValueAt(index, 0).toString();
            boolean check = kiemTraTonTai(txtMaKhachHang.getText(), txtSoDienThoai.getText(), txtEmail.getText(), maNV);
            if (kiemTraRong1 && check && khachHang.capNhatKhachHang(readForm(), maNV) != 0) {
                lblThongBaoMaKhachHang.setText("Mã khách hàng sẽ không được thay đổi");
                lblThongBaoMaKhachHang.setVisible(true);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                fillTableKhachHang(khachHang.getall());
            } else {
                JOptionPane.showMessageDialog(this,  "Sửa thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        txtThoiGianGiaoDich.setText("");
        txtSoLuongDonHang.setText("");
        fillTableKhachHang(khachHang.getall());
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaKhachHang;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblSoLuongDonHang;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblThoigianGiaoDich;
    private javax.swing.JLabel lblThongBaoEmail;
    private javax.swing.JLabel lblThongBaoMaKhachHang;
    private javax.swing.JLabel lblThongBaoSdt;
    private javax.swing.JLabel lblThongBaoTenKhachHang;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabKhachHang;
    private javax.swing.JTable tblDanhSachKhachHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JLabel txtSoLuongDonHang;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JLabel txtThoiGianGiaoDich;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
