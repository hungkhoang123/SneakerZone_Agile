/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.util.ArrayList;
import Model.*;
import DAO.NhanVien;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIETTECH88
 */
public class ViewNhanVien extends javax.swing.JPanel {

    private DefaultTableModel mol = new DefaultTableModel();
    private NhanVien repoNhanVien = new NhanVien();
    private int indexNV = -1;
    private int indexNVDN = -1;

    /**
     * Creates new form ViewNhanVien
     */
    public ViewNhanVien() {
        initComponents();
        donglable();
        filltableNhanVien(repoNhanVien.getall(1), tblNhanVien);

    }

    private void donglable() {
        lblThongBaoDiaChi.setVisible(false);
        lblThongBaoEmail.setVisible(false);
        lblThongBaoMaNhanVien.setVisible(false);
        lblThongBaoSoDienThoai.setVisible(false);
        lblThongBaoTenDangNhap.setVisible(false);
        lblThongBaoTenNhanVien.setVisible(false);
    }

    private void filltableNhanVien(ArrayList<ModelNhanVien> list, JTable tbl) {
        mol = (DefaultTableModel) tbl.getModel();
        mol.setRowCount(0);
        for (ModelNhanVien x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void showData(int index, JTable tbl) {
        txtMaNhanVien.setText(tbl.getValueAt(index, 0).toString());
        txtHoTen.setText(tbl.getValueAt(index, 1).toString());
        txtTenDangNhap.setText(tbl.getValueAt(index, 2).toString());
        txtDiaChi.setText(tbl.getValueAt(index, 4).toString());
        txtSoDienThoai.setText(tbl.getValueAt(index, 5).toString());
        txtEmail.setText(tbl.getValueAt(index, 6).toString());
        txtVaiTro.setText(tbl.getValueAt(index, 7).toString());
        String gioiTinh = tbl.getValueAt(index, 9).toString();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
            rdoNu.setSelected(true);
        }
    }

    private boolean kiemTraRong() {
        boolean check = true;

        // Mã nhân viên
        if (txtMaNhanVien.getText().trim().isEmpty()) {
            lblThongBaoMaNhanVien.setText("Mã nhân viên không được để trống");
            lblThongBaoMaNhanVien.setVisible(true);
            check = false;
        } else if (!txtMaNhanVien.getText().trim().matches("^NV\\d{4,}$")) {
            lblThongBaoMaNhanVien.setText("Mã nhân viên không hợp lệ\n VD: NV0003");
            lblThongBaoMaNhanVien.setVisible(true);
            check = false;
        } else {
            lblThongBaoMaNhanVien.setVisible(false);
        }

        // Tên nhân viên
        if (txtHoTen.getText().trim().isEmpty()) {
            lblThongBaoTenNhanVien.setText("Tên nhân viên không được để trống");
            lblThongBaoTenNhanVien.setVisible(true);
            check = false;
        } else {
            lblThongBaoTenNhanVien.setVisible(false);
        }

        // Địa chỉ
        if (txtDiaChi.getText().trim().isEmpty()) {
            lblThongBaoDiaChi.setText("Địa chỉ không được để trống");
            lblThongBaoDiaChi.setVisible(true);
            check = false;
        } else {
            lblThongBaoDiaChi.setVisible(false);
        }

        // Email
        if (txtEmail.getText().trim().isEmpty()) {
            lblThongBaoEmail.setText("Email không được để trống");
            lblThongBaoEmail.setVisible(true);
            check = false;
        } else if (!txtEmail.getText().trim().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            lblThongBaoEmail.setText("Email không hợp lệ");
            lblThongBaoEmail.setVisible(true);
            check = false;
        } else {
            lblThongBaoEmail.setVisible(false);
        }

        // Số điện thoại
        if (txtSoDienThoai.getText().trim().isEmpty()) {
            lblThongBaoSoDienThoai.setText("Số điện thoại không được để trống");
            lblThongBaoSoDienThoai.setVisible(true);
            check = false;
        } else if (!txtSoDienThoai.getText().trim().matches("^0\\d{9}$")) {
            lblThongBaoSoDienThoai.setText("Số điện thoại không hợp lệ");
            lblThongBaoSoDienThoai.setVisible(true);
            check = false;
        } else {
            lblThongBaoSoDienThoai.setVisible(false);
        }

        // Tên đăng nhập
        if (txtTenDangNhap.getText().trim().isEmpty()) {
            lblThongBaoTenDangNhap.setText("Tên đăng nhập không được để trống");
            lblThongBaoTenDangNhap.setVisible(true);
            check = false;
        } else {
            lblThongBaoTenDangNhap.setVisible(false);
        }

        return check;
    }

    private boolean kiemTraTonTai(String maNhanVien, String soDienThoai, String email, String tenDangNhap, String maNhanVienCanSua) {
        ArrayList<ModelNhanVien> danhSachNhanVien = repoNhanVien.getall(1);
        boolean check = true;
        for (ModelNhanVien x : danhSachNhanVien) {
            if (maNhanVienCanSua.equalsIgnoreCase(x.getMaNhanVien())) {
                continue;
            }
            if (maNhanVien.equalsIgnoreCase(x.getMaNhanVien())) {
                lblThongBaoMaNhanVien.setText("Mã nhân viên đã tồn tại");
                lblThongBaoMaNhanVien.setVisible(true);
                check = false;
            }

            if (soDienThoai.equalsIgnoreCase(x.getSoDienThoai())) {
                lblThongBaoSoDienThoai.setText("Số điện thoại đã được sử dụng");
                lblThongBaoSoDienThoai.setVisible(true);
                check = false;
            }
            if (email.equalsIgnoreCase(x.geteMail())) {
                lblThongBaoEmail.setText("Email đã được sử dụng");
                lblThongBaoEmail.setVisible(true);
                check = false;
            }
            if (tenDangNhap.equalsIgnoreCase(x.getTenDangNhap())) {
                lblThongBaoTenDangNhap.setText("Tên đăng nhập đã được sử dụng");
                lblThongBaoTenDangNhap.setVisible(true);
                check = false;
            }
        }
        return check;
    }

//    private ModelNhanVien readForm() {
//        String ma = txtMaNhanVien.getText().trim();
//        String ten = txtHoTen.getText().trim();
//        String tendangnhap = txtTenDangNhap.getText().trim();
//        String matkhau = repoNhanVien.taoMatKhau(5);
//        String diachi = txtDiaChi.getText().trim();
//        String sdt = txtSoDienThoai.getText().trim();
//        String email = txtEmail.getText().trim();
//
//        Boolean gioitinh = true;
//        if (rdoNam.isSelected()) {
//            gioitinh = false;
//        } else if (rdoNu.isSelected()) {
//            gioitinh = true;
//        }
//
//        Boolean vaitro = null;
//        String t = txtVaiTro.getText().trim();
//        if (t.equalsIgnoreCase("quản lý")) {
//            vaitro = true;
//        } else if(t.equalsIgnoreCase("nhân viên")) {
//            vaitro = false;
//        }
//
//        return new ModelNhanVien(ma, ten, tendangnhap, matkhau, diachi, sdt, email, gioitinh, vaitro);
//    }
    private ModelNhanVien readForm() {
        String ma = txtMaNhanVien.getText().trim();
        String ten = txtHoTen.getText().trim();
        String tendangnhap = txtTenDangNhap.getText().trim();
        String matkhau = repoNhanVien.taoMatKhau(5);
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

        // Xử lý vai trò
        Boolean vaitro = null;
        String t = txtVaiTro.getText().trim().toLowerCase();
        if (t.equals("quản lý")) {
            vaitro = true;
        } else if (t.equals("nhân viên")) {
            vaitro = false;
        } else {
            JOptionPane.showMessageDialog(this, "Vai trò không hợp lệ");
            return null;
        }

        return new ModelNhanVien(ma, ten, tendangnhap, matkhau, diachi, sdt, email, vaitro, gioitinh);
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        pmQuanLy = new javax.swing.JMenuItem();
        pmNhanVien = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tabNhanVien = new javax.swing.JTabbedPane();
        dangLam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        daNghi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVienDaNghi = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblVaiTro = new javax.swing.JLabel();
        txtVaiTro = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        lblThongBaoMaNhanVien = new javax.swing.JLabel();
        lblThongBaoSoDienThoai = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblTenNhanVien = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblThongBaoTenNhanVien = new javax.swing.JLabel();
        lblThongBaoDiaChi = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblTenDangNhap = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        lblThongBaoTenDangNhap = new javax.swing.JLabel();
        lblThongBaoEmail = new javax.swing.JLabel();
        btnKhoiPhuc = new javax.swing.JButton();

        jPopupMenu1.setPreferredSize(new java.awt.Dimension(242, 52));

        pmQuanLy.setText("Quản lý");
        pmQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmQuanLyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pmQuanLy);

        pmNhanVien.setText("Nhân Viên");
        pmNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmNhanVienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pmNhanVien);

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1078, 350));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        tabNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        tabNhanVien.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabNhanVienStateChanged(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tên đăng nhập", "Mật khẩu ", "Địa chỉ", "Số điện thoại", "Email", "Vai trò", "Trạng thái ", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout dangLamLayout = new javax.swing.GroupLayout(dangLam);
        dangLam.setLayout(dangLamLayout);
        dangLamLayout.setHorizontalGroup(
            dangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangLamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
                .addContainerGap())
        );
        dangLamLayout.setVerticalGroup(
            dangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangLamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabNhanVien.addTab("Đang làm việc", dangLam);

        tblNhanVienDaNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tên đăng nhập", "Mật khẩu ", "Địa chỉ", "Số điện thoại", "Email", "Vai trò", "Trạng thái ", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienDaNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienDaNghiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVienDaNghi);

        javax.swing.GroupLayout daNghiLayout = new javax.swing.GroupLayout(daNghi);
        daNghi.setLayout(daNghiLayout);
        daNghiLayout.setHorizontalGroup(
            daNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daNghiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
                .addContainerGap())
        );
        daNghiLayout.setVerticalGroup(
            daNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daNghiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabNhanVien.addTab("Đã nghỉ", daNghi);

        jPanel2.add(tabNhanVien);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1078, 35));

        lblTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTimKiem.setText("Tìm kiếm:");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(775, Short.MAX_VALUE))
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

        lblMaNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMaNhanVien.setText("Mã nhân viên");

        lblSoDienThoai.setBackground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoDienThoai.setPreferredSize(new java.awt.Dimension(242, 21));

        lblVaiTro.setBackground(new java.awt.Color(255, 255, 255));
        lblVaiTro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVaiTro.setText("Vai trò");

        txtVaiTro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtVaiTro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtVaiTro.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtVaiTro.setPreferredSize(new java.awt.Dimension(242, 21));
        txtVaiTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVaiTroMouseClicked(evt);
            }
        });

        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaNhanVien.setPreferredSize(new java.awt.Dimension(242, 21));

        lblThongBaoMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoMaNhanVien.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoMaNhanVien.setText("Thông tin không hợp lệ");

        lblThongBaoSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoSoDienThoai.setText("Thông tin không hơp lệ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblVaiTro)
                    .addComponent(lblSoDienThoai)
                    .addComponent(lblMaNhanVien)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThongBaoMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVaiTro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThongBaoSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblMaNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblThongBaoMaNhanVien)
                .addGap(36, 36, 36)
                .addComponent(lblSoDienThoai)
                .addGap(18, 18, 18)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblThongBaoSoDienThoai)
                .addGap(27, 27, 27)
                .addComponent(lblVaiTro)
                .addGap(18, 18, 18)
                .addComponent(txtVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblTenNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblTenNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenNhanVien.setText("Tên nhân viên");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtHoTen.setPreferredSize(new java.awt.Dimension(242, 21));

        lblDiaChi.setBackground(new java.awt.Color(255, 255, 255));
        lblDiaChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDiaChi.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiaChi.setPreferredSize(new java.awt.Dimension(242, 21));

        lblGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        lblThongBaoTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoTenNhanVien.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenNhanVien.setText("Thông tin không hợp lệ");

        lblThongBaoDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoDiaChi.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoDiaChi.setText("Thông tin không hợp lệ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(39, 39, 39)
                        .addComponent(rdoNu))
                    .addComponent(lblGioiTinh)
                    .addComponent(lblDiaChi)
                    .addComponent(lblTenNhanVien)
                    .addComponent(lblThongBaoTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(lblThongBaoDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTenNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblThongBaoTenNhanVien)
                .addGap(34, 34, 34)
                .addComponent(lblDiaChi)
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblThongBaoDiaChi)
                .addGap(27, 27, 27)
                .addComponent(lblGioiTinh)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTenDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        lblTenDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenDangNhap.setText("Tên đăng nhập");
        jPanel8.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 20, -1, -1));

        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenDangNhap.setPreferredSize(new java.awt.Dimension(242, 21));
        jPanel8.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 49, -1, -1));

        lblEmail.setBackground(new java.awt.Color(255, 255, 255));
        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setText("Email");
        jPanel8.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 118, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtEmail.setMinimumSize(new java.awt.Dimension(242, 21));
        jPanel8.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 159, 242, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setText("Tạo mới");
        btnThem.setPreferredSize(new java.awt.Dimension(77, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 247, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnSua.setText("Cập nhật");
        btnSua.setPreferredSize(new java.awt.Dimension(77, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 247, -1, -1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(77, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 247, -1, -1));

        lblThongBaoTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoTenDangNhap.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenDangNhap.setText("Thông tin không hợp lệ");
        jPanel8.add(lblThongBaoTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 70, 242, -1));

        lblThongBaoEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoEmail.setText("Thông tin không hợp lệ");
        jPanel8.add(lblThongBaoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 180, 242, -1));

        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.setPreferredSize(new java.awt.Dimension(84, 40));
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });
        jPanel8.add(btnKhoiPhuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 305, -1, -1));

        jPanel5.add(jPanel8);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        indexNV = tblNhanVien.getSelectedRow();
        showData(indexNV, tblNhanVien);
        donglable();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblNhanVienDaNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienDaNghiMouseClicked
        // TODO add your handling code here:
        indexNVDN = tblNhanVienDaNghi.getSelectedRow();
        showData(indexNVDN, tblNhanVienDaNghi);
        donglable();
    }//GEN-LAST:event_tblNhanVienDaNghiMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        String t = txtTimKiem.getText().trim();
        if (t != null && !t.isBlank()) {
            filltableNhanVien(repoNhanVien.timKiem(t), tblNhanVien);
        } else {
            JOptionPane.showMessageDialog(this, "Thông tin tìm kiếm trống");
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        String s = tblNhanVien.getValueAt(indexNV, 0).toString();
        boolean kiemTraRong1 = kiemTraRong();
        boolean check = kiemTraTonTai(txtMaNhanVien.getText(), txtSoDienThoai.getText(), txtEmail.getText(), txtTenDangNhap.getText(), "");
        if (kiemTraRong1 && check && repoNhanVien.themNhanVien(readForm()) != 0) {
            JOptionPane.showMessageDialog(this, "OK RỒI BẠN ƠI");
            filltableNhanVien(repoNhanVien.getall(1), tblNhanVien);
        } else {
            JOptionPane.showMessageDialog(this, "Không được đâu bạn ơi");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtVaiTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVaiTroMouseClicked
        // TODO add your handling code here:
        jPopupMenu1.show(jPanel6, txtVaiTro.getX(), txtVaiTro.getY());
        jPopupMenu1.setSize(txtVaiTro.getWidth(), txtVaiTro.getHeight());
    }//GEN-LAST:event_txtVaiTroMouseClicked

    private void pmQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmQuanLyActionPerformed
        // TODO add your handling code here:
        txtVaiTro.setText("Quản Lý");
        txtVaiTro.repaint();
        System.out.println(txtVaiTro.getText());
    }//GEN-LAST:event_pmQuanLyActionPerformed

    private void pmNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmNhanVienActionPerformed
        // TODO add your handling code here:
        txtVaiTro.setText("Nhân Viên");
        txtVaiTro.repaint();
        System.out.println(txtVaiTro.getText());
    }//GEN-LAST:event_pmNhanVienActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (indexNV == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn gì kìa bạn ơi");
        } else {
            ModelNhanVien nv = readForm();
            boolean kiemTraRong1 = kiemTraRong();
            String s = tblNhanVien.getValueAt(indexNV, 0).toString();
            boolean check = kiemTraTonTai(txtMaNhanVien.getText(), txtSoDienThoai.getText(), txtEmail.getText(), txtTenDangNhap.getText(), s);
            if (kiemTraRong1 && check && repoNhanVien.suaNhanVien(nv, s) != 0) {
                JOptionPane.showMessageDialog(this, "Ok rồi đấy");
                filltableNhanVien(repoNhanVien.getall(1), tblNhanVien);
            } else {
                JOptionPane.showMessageDialog(this, "ĐÉO SỬA ĐƯỢC BẠN NHÉ");
            }
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (indexNV == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên");
        } else {
            String nv = tblNhanVien.getValueAt(indexNV, 0).toString();

            int check = JOptionPane.showConfirmDialog(this, "Bạn có sẽ xóa nhân viên " + nv, "Xóa " + nv, JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION && repoNhanVien.xoaNhanVien(nv) != 0) {
                filltableNhanVien(repoNhanVien.getall(1), tblNhanVien);
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tabNhanVienStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabNhanVienStateChanged
        // TODO add your handling code here:
        int i = tabNhanVien.getSelectedIndex();

        if (i == 1) {
            btnThem.setVisible(false);
            btnXoa.setVisible(false);
            btnSua.setVisible(false);
            btnKhoiPhuc.setVisible(true);

            filltableNhanVien(repoNhanVien.getall(0), tblNhanVienDaNghi);
            btnXoa.repaint();
        } else if (i == 0) {
            btnThem.setVisible(true);
            btnSua.setVisible(true);
            btnXoa.setVisible(true);
            btnKhoiPhuc.setVisible(false);
            filltableNhanVien(repoNhanVien.getall(1), tblNhanVienDaNghi);
        }
    }//GEN-LAST:event_tabNhanVienStateChanged

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        // TODO add your handling code here:
        if (indexNVDN == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên");
        } else {
            String t = tblNhanVienDaNghi.getValueAt(indexNVDN, 0).toString();
            if (repoNhanVien.capNhatNhanVienDaXoa(t) != 0) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công");
                filltableNhanVien(repoNhanVien.getall(0), tblNhanVienDaNghi);
                filltableNhanVien(repoNhanVien.getall(1), tblNhanVien);
            } else {
                JOptionPane.showMessageDialog(this, "không thể khôi phục");
            }
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel daNghi;
    private javax.swing.JPanel dangLam;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThongBaoDiaChi;
    private javax.swing.JLabel lblThongBaoEmail;
    private javax.swing.JLabel lblThongBaoMaNhanVien;
    private javax.swing.JLabel lblThongBaoSoDienThoai;
    private javax.swing.JLabel lblThongBaoTenDangNhap;
    private javax.swing.JLabel lblThongBaoTenNhanVien;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JMenuItem pmNhanVien;
    private javax.swing.JMenuItem pmQuanLy;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabNhanVien;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVienDaNghi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtVaiTro;
    // End of variables declaration//GEN-END:variables
}
