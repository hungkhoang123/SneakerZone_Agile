/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ModelKhuyenMai;
import DAO.KhuyenMai;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VIETTECH88
 */
public class ViewKhuyenMai extends javax.swing.JPanel {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DefaultTableModel mol = new DefaultTableModel();
    private KhuyenMai khuyenMai = new KhuyenMai();
    private int thoiGian = -1;
    private int index = -1;
    private int bangHientai = -1;

    /**
     * Creates new form ViewKhuyenMai
     */
    public ViewKhuyenMai() {
        initComponents();
        dongLabel();
        fillTableKhuyenMai(khuyenMai.getall(0), tblDanhSachKhuyenMai);
    }

    private void dongLabel() {
        lblThongBaoMaKM.setVisible(false);
        lblThongBaoDieuKienDH.setVisible(false);
        lblThongBaoGiaTriGiam.setVisible(false);
        lblThongBaoGiaTriHD.setVisible(false);
        lblThongBaoNgayBD.setVisible(false);
        lblThongBaoNgayKT.setVisible(false);
        lblThongBaoTenKM.setVisible(false);
        lblThongBaoLoaiKM.setVisible(false);
        
        lblSoLuongDonHang.setVisible(false);
        lblThongBaoDieuKienDH.setVisible(false);
        txtSoLuongDonHang.setVisible(false);
    }

    private void fillTableKhuyenMai(ArrayList<ModelKhuyenMai> list, JTable tbl) {
        mol = (DefaultTableModel) tbl.getModel();
        mol.setRowCount(0);
        for (ModelKhuyenMai x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void showDataKM(int index, JTable tbl) {
        txtMaKhuyenMai.setText(tbl.getValueAt(index, 1).toString());
        txtTenKhuyenMai.setText(tbl.getValueAt(index, 2).toString());
        txtLoaiKhuyenMai.setText(tbl.getValueAt(index, 3).toString());
        txtGiaTriGiam.setText(tbl.getValueAt(index, 4).toString());
        txtGiaTriHoaDon.setText(tbl.getValueAt(index, 5).toString());
//        txtSoLuongDonHang.setText(tbl.getValueAt(index, 6).toString());
        txtNgayBatDau.setText(tbl.getValueAt(index, 7).toString());
        txtNgayKetThuc.setText(tbl.getValueAt(index, 8).toString());
    }

    private boolean kiemTraRong() {
        boolean check = true;

        // Mã khuyến mại
        if (txtMaKhuyenMai.getText().trim().isEmpty()) {
            lblThongBaoMaKM.setText("Mã khuyến mại không được để trống");
            lblThongBaoMaKM.setVisible(true);
            check = false;
        } else if (!txtMaKhuyenMai.getText().trim().matches("^KM\\d{4,}$")) {
            lblThongBaoMaKM.setText("Mã khuyến mại không hợp lệ\n VD: KM0003");
            lblThongBaoMaKM.setVisible(true);
            check = false;
        } else {
            lblThongBaoMaKM.setVisible(false);
        }

        // Tên khuyến mại
        if (txtTenKhuyenMai.getText().trim().isEmpty()) {
            lblThongBaoTenKM.setText("Tên khuyến mại không được để trống");
            lblThongBaoTenKM.setVisible(true);
            check = false;
        } else {
            lblThongBaoTenKM.setVisible(false);
        }

        if (txtLoaiKhuyenMai.getText().trim().isEmpty()) {
            lblThongBaoLoaiKM.setText("Loại khuyến mại không được để trống");
            lblThongBaoLoaiKM.setVisible(true);
            check = false;
        } else {
            lblThongBaoLoaiKM.setVisible(false);
        }

        if (txtNgayBatDau.getText().trim().isEmpty()) {
            lblThongBaoNgayBD.setText("Ngày bắt đầu không được để trống");
            lblThongBaoNgayBD.setVisible(true);
            check = false;
        } else if (!khuyenMai.isValidDate(txtNgayBatDau.getText().trim(), "yyyy-MM-dd")) {
            lblThongBaoNgayBD.setText("Sai định dạng. Sửa: yyyy-MM-dd");
            lblThongBaoNgayBD.setVisible(true);
        } else {
            lblThongBaoNgayBD.setVisible(false);
        }

        if (txtNgayKetThuc.getText().trim().isEmpty()) {
            lblThongBaoNgayKT.setText("Ngày kết thúc không được để trống");
            lblThongBaoNgayKT.setVisible(true);
            check = false;
        } else if (!khuyenMai.isValidDate(txtNgayKetThuc.getText().trim(), "yyyy-MM-dd")) {
            lblThongBaoNgayKT.setText("Sai định dạng. Sửa: yyyy-MM-dd");
            lblThongBaoNgayKT.setVisible(true);
        } else {
            lblThongBaoNgayKT.setVisible(false);
        }

        //Giá trị hóa đơn
        if (txtGiaTriHoaDon.getText().trim().isEmpty()) {
            lblThongBaoGiaTriHD.setText("Giá trị hóa đơn không được trống");
            lblThongBaoGiaTriHD.setVisible(true);
            check = false;
        } else if (!khuyenMai.isInt(txtGiaTriHoaDon.getText().trim())) {
            lblThongBaoGiaTriHD.setText("Hóa đơn phải là số nguyên dương");
            lblThongBaoGiaTriHD.setVisible(true);
            check = false;
        } else {
            lblThongBaoGiaTriHD.setVisible(false);
        }

        //Số lượng đơn hàng
//        if (txtSoLuongDonHang.getText().trim().isEmpty()) {
//            lblThongBaoDieuKienDH.setText("Số lượng đơn hàng không được để trống");
//            lblThongBaoDieuKienDH.setVisible(true);
//            check = false;
//        } else if (!khuyenMai.isInt(txtSoLuongDonHang.getText().trim())) {
//            lblThongBaoDieuKienDH.setText("Số đơn hàng phải là số nguyên dương");
//            lblThongBaoDieuKienDH.setVisible(true);
//            check = false;
//        } else {
//            lblThongBaoDieuKienDH.setVisible(false);
//        }
        //kiểm tra giá trị giảm
        if (txtGiaTriGiam.getText().trim().isEmpty()) {
            lblThongBaoGiaTriGiam.setText("Giá trị giảm không được để trống");
            lblThongBaoGiaTriGiam.setVisible(true);
            check = false;
        } else if (!khuyenMai.isInt(txtGiaTriGiam.getText().trim())) {
            lblThongBaoGiaTriGiam.setText("Giá trị giảm phải là số nguyên dương");
            lblThongBaoGiaTriGiam.setVisible(true);
            check = false;
        } else {
            int giaTriGiam = Integer.parseInt(txtGiaTriGiam.getText().trim());
            if (txtLoaiKhuyenMai.getText().trim().equalsIgnoreCase("Khuyến mại theo %")) {
                if (giaTriGiam < 0 || giaTriGiam > 100) {
                    lblThongBaoGiaTriGiam.setText("Giá trị giảm theo % phải từ 0 đến 100");
                    lblThongBaoGiaTriGiam.setVisible(true);
                    check = false;
                } else {
                    lblThongBaoGiaTriGiam.setVisible(false);
                }
            } else {
                lblThongBaoGiaTriGiam.setVisible(false);
            }
        }

        if (!kiemTraNgayKhuyenMai(txtNgayBatDau.getText().trim(), txtNgayKetThuc.getText().trim())) {
            check = false;
        }

        return check;
    }

    public boolean kiemTraNgayKhuyenMai(String ngayBatDauStr, String ngayKetThucStr) {
        try {
            LocalDate ngayHienTai = LocalDate.now();
            LocalDate ngayBatDau = LocalDate.parse(ngayBatDauStr, FORMAT);
            LocalDate ngayKetThuc = LocalDate.parse(ngayKetThucStr, FORMAT);

            if (ngayBatDau.isBefore(ngayHienTai)) {
                lblThongBaoNgayBD.setText("Ngày bắt đầu đã qua");
                lblThongBaoNgayBD.setVisible(true);
                return false;
            }

            if (ngayKetThuc.isBefore(ngayHienTai)) {
                lblThongBaoNgayKT.setText("Ngày kết thúc đã qua");
                lblThongBaoNgayKT.setVisible(true);
                return false;
            }

            if (ngayBatDau.isAfter(ngayKetThuc)) {
                lblThongBaoNgayKT.setText("Ngày K Thúc phải > ngày B Đầu");
                lblThongBaoNgayKT.setVisible(true);
                return false;
            }

            return true;

        } catch (DateTimeParseException e) {
            System.out.println("Định dạng ngày không hợp lệ! (Định dạng đúng: yyyy-MM-dd)");
            return false;
        }
    }

    private ModelKhuyenMai readForm() {
        String maKhuyenMai = txtMaKhuyenMai.getText().trim();
        String s = txtLoaiKhuyenMai.getText().trim();
        boolean loaiKhuyenMai = s.equals("Giảm trực tiếp") ? false : true;
        String tenKhuyenMai = txtTenKhuyenMai.getText().trim();
        String ngayBD = txtNgayBatDau.getText().trim();
        String ngayKT = txtNgayKetThuc.getText().trim();
        float dieuKienSoTien = Float.parseFloat(txtGiaTriHoaDon.getText().trim());
        int dieuKienHD = 0;
//                Integer.parseInt(txtSoLuongDonHang.getText().trim());
        boolean trangThai = true;

        float giaTriGiam = 0;
        float phanTramGiam = 0;

        if (loaiKhuyenMai) {
            phanTramGiam = Float.parseFloat(txtGiaTriGiam.getText().trim());
        } else {
            giaTriGiam = Float.parseFloat(txtGiaTriGiam.getText().trim());
        }

        String moTa = JOptionPane.showInputDialog(this, "Nhập mô tả khuyến mãi:");
        if (moTa == null) {
            moTa = "";
        }

        return new ModelKhuyenMai(maKhuyenMai, tenKhuyenMai, loaiKhuyenMai, giaTriGiam, phanTramGiam, dieuKienSoTien, dieuKienHD, ngayBD, ngayKT, trangThai, moTa);
    }

    private boolean kiemTraInsert() {
        if (!kiemTraRong()) {
            return false;
        }
        if (!kiemTraNgayKhuyenMai(txtNgayBatDau.getText().trim(), txtNgayKetThuc.getText().trim())) {
            return false;
        }
        if (khuyenMai.kiemTraMaKM(txtMaKhuyenMai.getText().trim())) {
            lblThongBaoMaKM.setText("Mã khuyến mãi đã tồn tại");
            lblThongBaoMaKM.setVisible(true);
            return false;
        }
        return true;
    }

    private boolean kiemTraUpdate(String maKM) {
        if (!kiemTraRong()) {
            return false;
        }
        if (!kiemTraNgayKhuyenMai(txtNgayBatDau.getText().trim(), txtNgayKetThuc.getText().trim())) {
            return false;
        }
        String maMoi = txtMaKhuyenMai.getText().trim();
        if (khuyenMai.maKhuyenMaiTonTaiKhiSua(maMoi, maKM)) {
            lblThongBaoMaKM.setText("Mã khuyến mãi đã tồn tại");
            lblThongBaoMaKM.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmLoaiKhuyenMai = new javax.swing.JPopupMenu();
        khuyenMaiTheoPhanTram = new javax.swing.JMenuItem();
        khuyenMaiTheoSo = new javax.swing.JMenuItem();
        thoiGianHoatDong = new javax.swing.JDialog();
        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        lblTenKhuyenMai = new javax.swing.JLabel();
        lblMaKhuyenMai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        txtMaKhuyenMai = new javax.swing.JTextField();
        txtTenKhuyenMai = new javax.swing.JTextField();
        lblThongBaoMaKM = new javax.swing.JLabel();
        lblThongBaoTenKM = new javax.swing.JLabel();
        lblThongBaoGiaTriGiam = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtGiaTriHoaDon = new javax.swing.JTextField();
        lblGiaTriHoaDon = new javax.swing.JLabel();
        lblLoaiKhuyenMai = new javax.swing.JLabel();
        lblSoLuongDonHang = new javax.swing.JLabel();
        txtSoLuongDonHang = new javax.swing.JTextField();
        txtLoaiKhuyenMai = new javax.swing.JLabel();
        lblThongBaoLoaiKM = new javax.swing.JLabel();
        lblThongBaoGiaTriHD = new javax.swing.JLabel();
        lblThongBaoDieuKienDH = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtNgayKetThuc = new javax.swing.JTextField();
        lblNgayKetThuc = new javax.swing.JLabel();
        lblNgayBatDau = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnTaoMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        lblThongBaoNgayBD = new javax.swing.JLabel();
        lblThongBaoNgayKT = new javax.swing.JLabel();
        tabDanhSachKM = new javax.swing.JTabbedPane();
        pnlDangHoatDong = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachKhuyenMai = new javax.swing.JTable();
        pnlSapDienRa = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDanhSachSdr = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pnlDaKetThuc = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDanhSachDkt = new javax.swing.JTable();

        pmLoaiKhuyenMai.setPreferredSize(new java.awt.Dimension(252, 52));

        khuyenMaiTheoPhanTram.setText("Khuyến Mại Theo %");
        khuyenMaiTheoPhanTram.setActionCommand("Giảm theo %");
        khuyenMaiTheoPhanTram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khuyenMaiTheoPhanTramActionPerformed(evt);
            }
        });
        pmLoaiKhuyenMai.add(khuyenMaiTheoPhanTram);

        khuyenMaiTheoSo.setText("Giảm trực tiếp");
        khuyenMaiTheoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khuyenMaiTheoSoActionPerformed(evt);
            }
        });
        pmLoaiKhuyenMai.add(khuyenMaiTheoSo);

        thoiGianHoatDong.setModal(true);
        thoiGianHoatDong.setUndecorated(true);

        dateChooserPanel1.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
        dateChooserPanel1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        dateChooserPanel1.setShowOneMonth(true);
        dateChooserPanel1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserPanel1OnSelectionChange(evt);
            }
        });

        javax.swing.GroupLayout thoiGianHoatDongLayout = new javax.swing.GroupLayout(thoiGianHoatDong.getContentPane());
        thoiGianHoatDong.getContentPane().setLayout(thoiGianHoatDongLayout);
        thoiGianHoatDongLayout.setHorizontalGroup(
            thoiGianHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateChooserPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        thoiGianHoatDongLayout.setVerticalGroup(
            thoiGianHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateChooserPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1078, 780));

        pnlThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(1078, 418));
        pnlThongTin.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        lblTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTimKiem.setText("Tìm kiếm:");
        jPanel7.add(lblTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 74, 25));

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 10, 216, -1));

        lblTenKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenKhuyenMai.setText("Tên khuyến mại");
        jPanel7.add(lblTenKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 163, 133, 25));

        lblMaKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        lblMaKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMaKhuyenMai.setText("Mã Khuyến Mại");
        jPanel7.add(lblMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 63, 129, 25));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Giá trị giảm");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 279, 92, 25));

        txtGiaTriGiam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGiaTriGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtGiaTriGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 322, 252, -1));

        txtMaKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 94, 252, -1));

        txtTenKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.add(txtTenKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 206, 252, -1));

        lblThongBaoMaKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoMaKM.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoMaKM.setText("Mã khuyến mại không được để trống");
        jPanel7.add(lblThongBaoMaKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 118, 252, -1));

        lblThongBaoTenKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoTenKM.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenKM.setText("Tên khuyến mại không được trống");
        jPanel7.add(lblThongBaoTenKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 231, 252, -1));

        lblThongBaoGiaTriGiam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoGiaTriGiam.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoGiaTriGiam.setText("Giá trị giảm không được trống");
        jPanel7.add(lblThongBaoGiaTriGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 347, 252, -1));

        pnlThongTin.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMinimumSize(new java.awt.Dimension(64, 18));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGiaTriHoaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGiaTriHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.add(txtGiaTriHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 207, 252, -1));

        lblGiaTriHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        lblGiaTriHoaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGiaTriHoaDon.setText("Giá trị hóa đơn");
        jPanel8.add(lblGiaTriHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 164, 133, 25));

        lblLoaiKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLoaiKhuyenMai.setText("Loại khuyến mại");
        jPanel8.add(lblLoaiKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 58, 129, 25));

        lblSoLuongDonHang.setBackground(new java.awt.Color(255, 255, 255));
        lblSoLuongDonHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoLuongDonHang.setText("Số lượng đơn hàng");
        jPanel8.add(lblSoLuongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 275, 166, 25));

        txtSoLuongDonHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSoLuongDonHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.add(txtSoLuongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 318, 252, -1));

        txtLoaiKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLoaiKhuyenMai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtLoaiKhuyenMai.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLoaiKhuyenMai.setMinimumSize(new java.awt.Dimension(64, 18));
        txtLoaiKhuyenMai.setPreferredSize(new java.awt.Dimension(64, 18));
        txtLoaiKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLoaiKhuyenMaiMouseClicked(evt);
            }
        });
        jPanel8.add(txtLoaiKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 89, 252, 24));

        lblThongBaoLoaiKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoLoaiKM.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoLoaiKM.setText("Loại khuyến mại không được trống");
        jPanel8.add(lblThongBaoLoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 250, -1));

        lblThongBaoGiaTriHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoGiaTriHD.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoGiaTriHD.setText("Giá trị hóa đơn không được trống");
        jPanel8.add(lblThongBaoGiaTriHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 228, 252, -1));

        lblThongBaoDieuKienDH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoDieuKienDH.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoDieuKienDH.setText("Số lượng đơn hàng không được trống");
        jPanel8.add(lblThongBaoDieuKienDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 342, 252, -1));

        pnlThongTin.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNgayKetThuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNgayKetThuc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayKetThucMouseClicked(evt);
            }
        });
        jPanel9.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 206, 252, -1));

        lblNgayKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayKetThuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNgayKetThuc.setText("Ngày kết thúc");
        jPanel9.add(lblNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 163, 133, 25));

        lblNgayBatDau.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayBatDau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNgayBatDau.setText("Ngày bắt đầu");
        jPanel9.add(lblNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 58, 129, 25));

        txtNgayBatDau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNgayBatDau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayBatDau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayBatDauMouseClicked(evt);
            }
        });
        jPanel9.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 95, 252, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnTaoMoi.setBackground(new java.awt.Color(0, 204, 255));
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.setPreferredSize(new java.awt.Dimension(77, 40));
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMinimumSize(new java.awt.Dimension(78, 23));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(77, 40));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnXoa.setBackground(new java.awt.Color(0, 204, 255));
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(77, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5);

        jPanel9.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 307, 356, -1));

        lblThongBaoNgayBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoNgayBD.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoNgayBD.setText("Ngày bắt đầu không được trống");
        jPanel9.add(lblThongBaoNgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 119, 252, -1));

        lblThongBaoNgayKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoNgayKT.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoNgayKT.setText("Ngày kết thúc không được trống");
        jPanel9.add(lblThongBaoNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 230, 252, -1));

        pnlThongTin.add(jPanel9);

        tabDanhSachKM.setBackground(new java.awt.Color(255, 255, 255));
        tabDanhSachKM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabDanhSachKMStateChanged(evt);
            }
        });

        pnlDangHoatDong.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khuyến mại", "Tên khuyến mại", "Loại khuyến mại", "Giá trị giảm", "Điều kiện số tiền", "Điều kiện hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"
            }
        ));
        tblDanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachKhuyenMai);

        javax.swing.GroupLayout pnlDangHoatDongLayout = new javax.swing.GroupLayout(pnlDangHoatDong);
        pnlDangHoatDong.setLayout(pnlDangHoatDongLayout);
        pnlDangHoatDongLayout.setHorizontalGroup(
            pnlDangHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        pnlDangHoatDongLayout.setVerticalGroup(
            pnlDangHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangHoatDongLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        tabDanhSachKM.addTab("Đang hoạt động", pnlDangHoatDong);

        pnlSapDienRa.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSachSdr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khuyến mại", "Tên khuyến mại", "Loại khuyến mại", "Giá trị giảm", "Điều kiện số tiền", "Điều kiện hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"
            }
        ));
        tblDanhSachSdr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSdrMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDanhSachSdr);

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setText("Sửa");
        jButton1.setPreferredSize(new java.awt.Dimension(77, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSapDienRaLayout = new javax.swing.GroupLayout(pnlSapDienRa);
        pnlSapDienRa.setLayout(pnlSapDienRaLayout);
        pnlSapDienRaLayout.setHorizontalGroup(
            pnlSapDienRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
            .addGroup(pnlSapDienRaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSapDienRaLayout.setVerticalGroup(
            pnlSapDienRaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSapDienRaLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 46, Short.MAX_VALUE))
        );

        tabDanhSachKM.addTab("Sắp diễn ra", pnlSapDienRa);

        pnlDaKetThuc.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSachDkt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khuyến mại", "Tên khuyến mại", "Loại khuyến mại", "Giá trị giảm", "Điều kiện số tiền", "Điều kiện hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"
            }
        ));
        tblDanhSachDkt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDktMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDanhSachDkt);

        javax.swing.GroupLayout pnlDaKetThucLayout = new javax.swing.GroupLayout(pnlDaKetThuc);
        pnlDaKetThuc.setLayout(pnlDaKetThucLayout);
        pnlDaKetThucLayout.setHorizontalGroup(
            pnlDaKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        pnlDaKetThucLayout.setVerticalGroup(
            pnlDaKetThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDaKetThucLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        tabDanhSachKM.addTab("Đã kết thúc", pnlDaKetThuc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabDanhSachKM, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabDanhSachKM, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoaiKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLoaiKhuyenMaiMouseClicked
        // TODO add your handling code here:
        pmLoaiKhuyenMai.show(jPanel8, txtLoaiKhuyenMai.getX(), txtLoaiKhuyenMai.getY());
    }//GEN-LAST:event_txtLoaiKhuyenMaiMouseClicked

    private void khuyenMaiTheoPhanTramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khuyenMaiTheoPhanTramActionPerformed
        // TODO add your handling code here:
        txtLoaiKhuyenMai.setText("Khuyến mại theo %");
    }//GEN-LAST:event_khuyenMaiTheoPhanTramActionPerformed

    private void khuyenMaiTheoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khuyenMaiTheoSoActionPerformed
        // TODO add your handling code here:
        txtLoaiKhuyenMai.setText("Khuyến mại theo %");
    }//GEN-LAST:event_khuyenMaiTheoSoActionPerformed

    private void dateChooserPanel1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserPanel1OnSelectionChange
        // TODO add your handling code here:
        if (dateChooserPanel1.getSelectedDate() == null) {
            thoiGianHoatDong.setVisible(false);
        } else if (thoiGian == 0) {
            Date selectedDate = dateChooserPanel1.getSelectedDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            txtNgayBatDau.setText(sdf.format(selectedDate));
        } else if (thoiGian == 1) {
            Date selectedDate = dateChooserPanel1.getSelectedDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            txtNgayKetThuc.setText(sdf.format(selectedDate));
        }
    }//GEN-LAST:event_dateChooserPanel1OnSelectionChange

    private void txtNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayBatDauMouseClicked
        // TODO add your handling code here:
        thoiGian = 0;
        Point location = txtNgayBatDau.getLocationOnScreen();
        int x = location.x;
        int y = location.y + txtNgayBatDau.getHeight();
        thoiGianHoatDong.pack();
        thoiGianHoatDong.setLocation(x, y);
        thoiGianHoatDong.setVisible(true);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_txtNgayBatDauMouseClicked

    private void tabDanhSachKMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabDanhSachKMStateChanged
        // TODO add your handling code here:
        int i = tabDanhSachKM.getSelectedIndex();
        switch (i) {
            case 0 -> {
                bangHientai = 0;
                index = -1;
                fillTableKhuyenMai(khuyenMai.getall(0), tblDanhSachKhuyenMai);
            }
            case 1 -> {
                bangHientai = 1;
                index = -1;
                fillTableKhuyenMai(khuyenMai.getall(1), tblDanhSachSdr);
            }
            case 2 -> {
                bangHientai = 2;
                index = -1;
                fillTableKhuyenMai(khuyenMai.getall(-1), tblDanhSachDkt);
            }
        }
    }//GEN-LAST:event_tabDanhSachKMStateChanged

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        if (kiemTraInsert() && khuyenMai.themKhuyenMai(readForm()) != 0) {
            JOptionPane.showMessageDialog(this, "thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi không thể tạo", "Thông Báo", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void tblDanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhuyenMaiMouseClicked
        // TODO add your handling code here:
        index = tblDanhSachKhuyenMai.getSelectedRow();
        showDataKM(index, tblDanhSachKhuyenMai);
    }//GEN-LAST:event_tblDanhSachKhuyenMaiMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        dongLabel();
        txtMaKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtLoaiKhuyenMai.setText("");
        txtGiaTriGiam.setText("");
        txtGiaTriHoaDon.setText("");
        txtSoLuongDonHang.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");

        index = -1;
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblDanhSachSdrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSdrMouseClicked
        // TODO add your handling code here:
        index = tblDanhSachSdr.getSelectedRow();
        showDataKM(index, tblDanhSachSdr);
    }//GEN-LAST:event_tblDanhSachSdrMouseClicked

    private void tblDanhSachDktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDktMouseClicked
        // TODO add your handling code here:
        index = tblDanhSachDkt.getSelectedRow();
        showDataKM(index, tblDanhSachDkt);
    }//GEN-LAST:event_tblDanhSachDktMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(bangHientai != 2 && index != -1){
            if(bangHientai == 0){
                String maKM = tblDanhSachKhuyenMai.getValueAt(index, 1).toString();
                String lyDo = JOptionPane.showInputDialog(this, "Nhập lý do");
                khuyenMai.xoaKhuyenMai(maKM, lyDo);
                fillTableKhuyenMai(khuyenMai.getall(0), tblDanhSachKhuyenMai);
            }else{
                String maKM = tblDanhSachSdr.getValueAt(index, 1).toString();
                String lyDo = JOptionPane.showInputDialog(this, "Nhập lý do");
                khuyenMai.xoaKhuyenMai(maKM, lyDo);
                fillTableKhuyenMai(khuyenMai.getall(1), tblDanhSachSdr);
            }  
        }else{
            JOptionPane.showMessageDialog(this, "Thông tin bảng hiện tại không thể xóa", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (bangHientai == 1 && index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để sửa");
        } else {
            String maKM = tblDanhSachSdr.getValueAt(index, 1).toString();
            if (kiemTraUpdate(maKM)) {
                int i = khuyenMai.suaKhuyenMai(readForm(), maKM);
                if (i != 0) {
                    fillTableKhuyenMai(khuyenMai.getall(1), tblDanhSachSdr);
                    JOptionPane.showMessageDialog(this, "Thay đổi thành công");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayKetThucMouseClicked
        // TODO add your handling code here:
        thoiGian = 1;
        Point location = txtNgayKetThuc.getLocationOnScreen();
        int x = location.x;
        int y = location.y + txtNgayBatDau.getHeight();
        thoiGianHoatDong.pack();
        thoiGianHoatDong.setLocation(x, y);
        thoiGianHoatDong.setVisible(true);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_txtNgayKetThucMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnXoa;
    private datechooser.beans.DateChooserPanel dateChooserPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenuItem khuyenMaiTheoPhanTram;
    private javax.swing.JMenuItem khuyenMaiTheoSo;
    private javax.swing.JLabel lblGiaTriHoaDon;
    private javax.swing.JLabel lblLoaiKhuyenMai;
    private javax.swing.JLabel lblMaKhuyenMai;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblSoLuongDonHang;
    private javax.swing.JLabel lblTenKhuyenMai;
    private javax.swing.JLabel lblThongBaoDieuKienDH;
    private javax.swing.JLabel lblThongBaoGiaTriGiam;
    private javax.swing.JLabel lblThongBaoGiaTriHD;
    private javax.swing.JLabel lblThongBaoLoaiKM;
    private javax.swing.JLabel lblThongBaoMaKM;
    private javax.swing.JLabel lblThongBaoNgayBD;
    private javax.swing.JLabel lblThongBaoNgayKT;
    private javax.swing.JLabel lblThongBaoTenKM;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JPopupMenu pmLoaiKhuyenMai;
    private javax.swing.JPanel pnlDaKetThuc;
    private javax.swing.JPanel pnlDangHoatDong;
    private javax.swing.JPanel pnlSapDienRa;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JTabbedPane tabDanhSachKM;
    private javax.swing.JTable tblDanhSachDkt;
    private javax.swing.JTable tblDanhSachKhuyenMai;
    private javax.swing.JTable tblDanhSachSdr;
    private javax.swing.JDialog thoiGianHoatDong;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtGiaTriHoaDon;
    private javax.swing.JLabel txtLoaiKhuyenMai;
    private javax.swing.JTextField txtMaKhuyenMai;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSoLuongDonHang;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
