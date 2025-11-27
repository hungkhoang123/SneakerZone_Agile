/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.SanPhamChiTiet;
import DAO.SanPham;
import DAO.ThuocTinhSanPham;
import Model.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hungb
 */
public class ViewQuanLiSanPham extends javax.swing.JPanel {

    final SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
    final ThuocTinhSanPham thuocTinhSanPham;
    final SanPham sanPham = new SanPham();
    private DefaultTableModel mol = new DefaultTableModel();
    private int kiemTraBang = -1;
    private int index = 1;

    /**
     * Creates new form ViewQuanLiSanPham
     */
    public ViewQuanLiSanPham() {
        initComponents();
        donglabel();
        thuocTinhSanPham = new ThuocTinhSanPham(this);
        sanPhamChiTiet.dataCombobox();
        loadCbo();

    }

    public ViewQuanLiSanPham(ThuocTinhSanPham ttsp) {
        this.thuocTinhSanPham = ttsp;
    }

    public JTextField getTxtMaThuocTinh() {
        return txtMaThuocTinh;
    }

    public JTextField getTxtTenThuocTinh() {
        return txtTenThuocTinh;
    }

    public JTextArea getTxtMoTa() {
        return txtMoTa;
    }

    private void donglabel() {
        lblThongBaoMaThuocTinh.setVisible(false);
        lblThongBaoTenThuocTinh.setVisible(false);
        lblThongBaoMaSP.setVisible(false);
        lblThongBaoTenSP.setVisible(false);
    }

    //load cbo.
    private void loadCbo() {
        cbo_chatLieu.removeAllItems();
        cbo_congNghe.removeAllItems();
        cbo_hang.removeAllItems();
        cbo_kichThuoc.removeAllItems();
        cbo_mau.removeAllItems();
        cbo_sanPham.removeAllItems();

        cbo_chatLieu.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getChatLieuSet().toArray(new String[0])));
        cbo_congNghe.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getCongNgheSet().toArray(new String[0])));
        cbo_hang.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getHangSet().toArray(new String[0])));
        cbo_kichThuoc.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getKichThuocSet().toArray(new String[0])));
        cbo_mau.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getMauSacSet().toArray(new String[0])));
        cbo_sanPham.setModel(new DefaultComboBoxModel<>(sanPhamChiTiet.getTenSanPhamSet().toArray(new String[0])));
    }

    //Bảng thông tin sản phầm
    void fillTableSanPham(ArrayList<ModelSanPham> list) {
        mol = (DefaultTableModel) tbl_sanPham.getModel();
        mol.setRowCount(0);
        for (ModelSanPham x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    // thuộc tính sản phẩm
    public String radio() {
        if (rdoChatLieu.isSelected()) {
            return "ChatLieu";
        } else if (rdoCongNghe.isSelected()) {
            return "CongNghe";
        } else if (rdoHang.isSelected()) {
            return "Hang";
        } else if (rdoKichThuoc.isSelected()) {
            return "KichThuoc";
        } else if (rdoMauSac.isSelected()) {
            return "MauSac";
        } else {
            return "SanPham";
        }
    }

    public void filltableTTSP(ArrayList<ModelThuocTinhSanPham> list, JTable tbl) {
//        if (rdoCongNghe.isSelected()) {
//            mol.addColumn("Mô Tả");
//            mol.setRowCount(0);
//            for (ModelThuocTinhSanPham x : list) {
//                mol.addRow(x.toDataRow());
//            }
//        } else 

        if (list != null) {
            mol = (DefaultTableModel) tbl.getModel();
            mol.setRowCount(0);
            for (ModelThuocTinhSanPham x : list) {
                mol.addRow(x.toDataRow());
            }
        }
    }

    //Kiểm tra điều kiện thuộc tính
    public boolean kiemTraDieuKienThuocTinh() {
        boolean check = true;
        if (rdoChatLieu.isSelected() && !txtMaThuocTinh.getText().matches("^CL\\d{3,}$")) {
            lblThongBaoMaThuocTinh.setText("Định dạng sai. VD:CL + số");
            lblThongBaoMaThuocTinh.setVisible(true);
            check = false;
        }
        if (rdoCongNghe.isSelected() && !txtMaThuocTinh.getText().matches("^CN\\d{3,}$")) {
            lblThongBaoMaThuocTinh.setText("Định dạng sai. VD:CN + số");
            lblThongBaoMaThuocTinh.setVisible(true);
            check = false;
        }
        if (rdoHang.isSelected() && !txtMaThuocTinh.getText().matches("^H\\d{3,}$")) {
            lblThongBaoMaThuocTinh.setText("Định dạng sai. VD:H + số");
            lblThongBaoMaThuocTinh.setVisible(true);
            check = false;
        }
        if (rdoKichThuoc.isSelected() && !txtMaThuocTinh.getText().matches("^KT\\d{3,}$")) {
            lblThongBaoMaThuocTinh.setText("Định dạng sai. VD:KT + số");
            lblThongBaoMaThuocTinh.setVisible(true);
            check = false;
        } else if (rdoKichThuoc.isSelected() && !txtTenThuocTinh.getText().matches("^\\d+$")) {
            lblThongBaoTenThuocTinh.setText("Tên thuộc tính phải là số và không âm");
            lblThongBaoTenThuocTinh.setVisible(true);
            check = false;
        }
        if (rdoMauSac.isSelected() && !txtMaThuocTinh.getText().matches("^MS\\d{3,}$")) {
            lblThongBaoMaThuocTinh.setText("Định dạng sai. VD:MS + số");
            lblThongBaoMaThuocTinh.setVisible(true);
            check = false;
        }
        return check;
    }

    private boolean kiemTraDieuKienSPCT() {
        if (cbo_chatLieu.getSelectedItem() == null || cbo_congNghe.getSelectedItem() == null
                || cbo_hang.getSelectedItem() == null || cbo_kichThuoc.getSelectedItem() == null
                || cbo_mau.getSelectedItem() == null || cbo_sanPham.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ các thuộc tính trong ComboBox",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txt_donGia.getText().isEmpty() || txt_soLuong.getText().isEmpty() || txtMaSPCt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ mã SPCT, đơn giá và số lượng",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!txtMaSPCt.getText().trim().matches("^SPCT\\d{4,}$")) {
            JOptionPane.showMessageDialog(this, "Mã SPCT phải có định dạng SPCT + ít nhất 4 số",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtMaSPCt.setForeground(Color.RED);
            return false;
        }

        double donGia;
        try {
            donGia = Double.parseDouble(txt_donGia.getText().trim());
            if (donGia <= 0) {
                txt_donGia.setText("Đơn giá phải lớn hơn 0");
                txt_donGia.setForeground(Color.RED);
                return false;
            }
        } catch (NumberFormatException e) {
            txt_donGia.setText("Đơn giá phải là số hợp lệ");
            txt_donGia.setForeground(Color.RED);
            return false;
        }

        int soLuong;
        try {
            soLuong = Integer.parseInt(txt_soLuong.getText().trim());
            if (soLuong <= 0) {
                txt_soLuong.setText("Số lượng phải lớn hơn 0");
                txt_soLuong.setForeground(Color.RED);
                return false;
            }
        } catch (NumberFormatException e) {
            txt_soLuong.setText("Số lượng phải là số hợp lệ");
            txt_soLuong.setForeground(Color.RED);
            return false;
        }

        if (!rdoConHang.isSelected() && !rdoHetHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái (Đang bán hoặc Ngừng bán)",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        txtMaSPCt.setForeground(Color.BLACK);
        txt_donGia.setForeground(Color.BLACK);
        txt_soLuong.setForeground(Color.BLACK);

        return true;
    }

    public boolean kiemTraSanPham() {
        boolean check = true;

        lblThongBaoMaSP.setVisible(false);
        lblThongBaoTenSP.setVisible(false);

        String maSanPham = txt_maSp.getText().trim();
        String tenSanPham = txtTenSanPham.getText().trim();

        if (maSanPham.isEmpty()) {
            lblThongBaoMaSP.setText("Mã sản phẩm không được để trống");
            lblThongBaoMaSP.setVisible(true);
            check = false;
        }

        if (tenSanPham.isEmpty()) {
            lblThongBaoTenSP.setText("Tên sản phẩm không được để trống");
            lblThongBaoTenSP.setVisible(true);
            check = false;
        }

        if (!maSanPham.isEmpty() && !maSanPham.matches("^SP\\d{3,}$")) {
            lblThongBaoMaSP.setText("Mã không đúng định dạng. VD: SP0001");
            lblThongBaoMaSP.setVisible(true);
            check = false;
        }

        if (cbo_hang.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hãng", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            check = false;
        }

        if (!tenSanPham.isEmpty() && tenSanPham.length() > 100) {
            lblThongBaoTenSP.setText("Tên sản phẩm không được vượt quá 100 ký tự");
            lblThongBaoTenSP.setVisible(true);
            check = false;
        }

        return check;
    }

    //kiểm tra trùng lặp
    private boolean kiemTraTrungLap(String ma) {
        HashSet<String> set = sanPham.getAllHang();
        if (set == null) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách mã");
            return false;
        }
        // Kiểm tra xem mã SPCT có tồn tại trong tập hợp hay không
        if (set.contains(ma)) {
            JOptionPane.showMessageDialog(this, "Mã SPCT trùng lặp, vui lòng chọn mã khác");
            return false;
        }
        return true;
    }

    //bảng sản phẩm chi tiết
    void fillTableSPCT(ArrayList<Model.ModelSanPhamChiTiet> list) {
        mol = (DefaultTableModel) tbl_SPCT.getModel();
        mol.setRowCount(0);
        for (Model.ModelSanPhamChiTiet x : list) {
            mol.addRow(x.obDanhSachSP());
        }
    }

    //hiển thị thông tin sản phẩm lên bảng
    private void showDataSanPham(int index) {
        txt_maSp.setText(tbl_sanPham.getValueAt(index, 2).toString());
        txtTenSanPham.setText(tbl_sanPham.getValueAt(index, 3).toString());
        cbo_hang.setSelectedItem(tbl_sanPham.getValueAt(index, 1).toString());
    }

    //hiển thị thông tin thuộc tính chi tiết lên bảng
    private void showDataThuocTinhSanPham(int index) {
        txtMaThuocTinh.setText(tblThuocTinhSanPham.getValueAt(index, 0).toString());
        txtTenThuocTinh.setText(tblThuocTinhSanPham.getValueAt(index, 1).toString());
        if (tblThuocTinhSanPham.getColumnCount() >= 3) {
            txtMoTa.setText(tblThuocTinhSanPham.getValueAt(index, 2).toString());
        }
    }

    private void showDataSanPhamChiTiet(int index) {
        txtMaSPCt.setText(tbl_SPCT.getValueAt(index, 1).toString());
        cbo_sanPham.setSelectedItem(tbl_SPCT.getValueAt(index, 2).toString());
        cbo_mau.setSelectedItem(tbl_SPCT.getValueAt(index, 3).toString());
        cbo_kichThuoc.setSelectedItem(tbl_SPCT.getValueAt(index, 4).toString());
        cbo_chatLieu.setSelectedItem(tbl_SPCT.getValueAt(index, 5).toString());
        cbo_congNghe.setSelectedItem(tbl_SPCT.getValueAt(index, 6).toString());
        txt_soLuong.setText(tbl_SPCT.getValueAt(index, 7).toString());
        txt_donGia.setText(tbl_SPCT.getValueAt(index, 8).toString());
        String t = tbl_SPCT.getValueAt(index, 9).toString();
        if (t.equals("Đang bán")) {
            rdoConHang.setSelected(true);
        } else if (t.equals("Ngừng bán")) {
            rdoHetHang.setSelected(true);
        }
    }

    // đọc form thuộc tính chi tiết
    private ModelSanPham readFormSanPham() {
        String hang = cbo_hang.getSelectedItem().toString();
        String maSanPham = txt_maSp.getText().trim();
        String tenSanPham = txtTenSanPham.getText().trim();
        return new ModelSanPham(hang, maSanPham, tenSanPham);
    }

    private ModelSanPhamChiTiet readFormSPCT() {
        String maSPCT = txtMaSPCt.getText().trim();
        String tenSanPham = cbo_sanPham.getSelectedItem().toString();
        String tenMauSac = cbo_mau.getSelectedItem().toString();
        String kichThuoc = cbo_kichThuoc.getSelectedItem().toString();
        String tenChatLieu = cbo_chatLieu.getSelectedItem().toString();
        String tenCongNghe = cbo_congNghe.getSelectedItem().toString();
        int soLuong = Integer.parseInt(txt_soLuong.getText().trim());
        double donGia = Double.parseDouble(txt_donGia.getText().trim());
        Boolean trangThai = null;
        if (rdoConHang.isSelected()) {
            trangThai = true;
        } else if (rdoHetHang.isSelected()) {
            trangThai = false;
        } else {
            JOptionPane.showMessageDialog(this, "Trạng thái NUll kìa bạn ơi");
        }
        return new ModelSanPhamChiTiet(maSPCT, tenSanPham, tenMauSac, kichThuoc, tenChatLieu, tenCongNghe, soLuong, donGia, trangThai);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        viewSanPham = new javax.swing.JTabbedPane();
        ThongTinSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSuaSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txt_maSp = new javax.swing.JTextField();
        cbo_hang = new javax.swing.JComboBox<>();
        lblThongBaoMaSP = new javax.swing.JLabel();
        lblThongBaoTenSP = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        SanPhamChiTiet = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnThemSPCT = new javax.swing.JButton();
        btnCapNhatSPCT = new javax.swing.JButton();
        btnXoaSPCT = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbo_sanPham = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbo_congNghe = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbo_mau = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbo_kichThuoc = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_donGia = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cbo_chatLieu = new javax.swing.JComboBox<>();
        jPanel25 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtMaSPCt = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_SPCT = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        pnlBangThuocTinh = new javax.swing.JPanel();
        pnlChucNang = new javax.swing.JPanel();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        pnlThuocTinh = new javax.swing.JPanel();
        lblThongBaoMaThuocTinh = new javax.swing.JLabel();
        lblMaThuocTinh = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        lblThongBaoTenThuocTinh = new javax.swing.JLabel();
        lblTenThuocTinh = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        lblMoTa = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        pnlRadio = new javax.swing.JPanel();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoHang = new javax.swing.JRadioButton();
        rdoCongNghe = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuocTinhSanPham = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 600));
        setLayout(new java.awt.BorderLayout());

        viewSanPham.setBackground(new java.awt.Color(255, 255, 255));
        viewSanPham.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                viewSanPhamStateChanged(evt);
            }
        });

        ThongTinSP.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Thông tin sản phẩm");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 222));

        btnSuaSP.setBackground(new java.awt.Color(0, 204, 255));
        btnSuaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(0, 204, 255));
        btnThemSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.setPreferredSize(new java.awt.Dimension(100, 35));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnXoaSP.setBackground(new java.awt.Color(0, 204, 255));
        btnXoaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaSP.setText("Xóa");
        btnXoaSP.setPreferredSize(new java.awt.Dimension(100, 35));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setBackground(new java.awt.Color(0, 204, 255));
        btnLamMoiSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.setPreferredSize(new java.awt.Dimension(100, 35));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã Sản Phẩm");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 26, 106, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên Sản phẩm");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 100, 106, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Hãng");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 162, 106, -1));

        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 97, 327, -1));

        txt_maSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel3.add(txt_maSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 23, 327, -1));

        cbo_hang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_hang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(cbo_hang, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 159, 327, -1));

        lblThongBaoMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoMaSP.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoMaSP.setText("Mã định dạng sai. VD: SP + số");
        jPanel3.add(lblThongBaoMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 55, 327, -1));

        lblThongBaoTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThongBaoTenSP.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenSP.setText("Chẳng có gì cả...");
        jPanel3.add(lblThongBaoTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 127, 327, -1));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Danh sách sản phẩm");

        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Hãng", "Mã Sản Phẩm", "Tên Sản Phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanPham);

        javax.swing.GroupLayout ThongTinSPLayout = new javax.swing.GroupLayout(ThongTinSP);
        ThongTinSP.setLayout(ThongTinSPLayout);
        ThongTinSPLayout.setHorizontalGroup(
            ThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ThongTinSPLayout.createSequentialGroup()
                        .addGroup(ThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        ThongTinSPLayout.setVerticalGroup(
            ThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinSPLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        viewSanPham.addTab("Sản phẩm", ThongTinSP);

        SanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Thông tin sản phẩm");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setPreferredSize(new java.awt.Dimension(1068, 260));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 238));

        btnThemSPCT.setBackground(new java.awt.Color(0, 204, 255));
        btnThemSPCT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemSPCT.setText("Thêm");
        btnThemSPCT.setPreferredSize(new java.awt.Dimension(100, 35));
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnCapNhatSPCT.setBackground(new java.awt.Color(0, 204, 255));
        btnCapNhatSPCT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCapNhatSPCT.setText("Cập nhật ");
        btnCapNhatSPCT.setPreferredSize(new java.awt.Dimension(100, 35));
        btnCapNhatSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPCTActionPerformed(evt);
            }
        });

        btnXoaSPCT.setBackground(new java.awt.Color(0, 204, 255));
        btnXoaSPCT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaSPCT.setText("Xóa");
        btnXoaSPCT.setPreferredSize(new java.awt.Dimension(100, 35));
        btnXoaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCTActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 255));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setPreferredSize(new java.awt.Dimension(100, 35));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhatSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.LINE_END);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(866, 260));
        jPanel8.setLayout(new java.awt.GridLayout(5, 2));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tên sản phẩm");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 19, -1, -1));

        cbo_sanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_sanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel9.add(cbo_sanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 16, 259, -1));

        jPanel8.add(jPanel9);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Công nghệ");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbo_congNghe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_congNghe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(cbo_congNghe, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 16, 259, -1));

        jPanel8.add(jPanel14);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Màu sắc");
        jPanel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 19, -1, -1));

        cbo_mau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_mau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel16.add(cbo_mau, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 16, 259, -1));

        jPanel8.add(jPanel16);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Số lượng");
        jPanel20.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt_soLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel20.add(txt_soLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 260, -1));

        jPanel8.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Kích thước");
        jPanel21.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 19, -1, -1));

        cbo_kichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_kichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel21.add(cbo_kichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 16, 259, -1));

        jPanel8.add(jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Đơn giá");
        jPanel22.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt_donGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel22.add(txt_donGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 260, -1));

        jPanel8.add(jPanel22);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Chất liệu");
        jPanel23.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 19, -1, -1));

        cbo_chatLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_chatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel23.add(cbo_chatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 16, 259, -1));

        jPanel8.add(jPanel23);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Mã SPCT");
        jPanel25.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtMaSPCt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel25.add(txtMaSPCt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 260, -1));

        jPanel8.add(jPanel25);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Trạng Thái");
        jPanel24.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 19, -1, -1));

        buttonGroup2.add(rdoConHang);
        rdoConHang.setText("Đang Bán");
        jPanel24.add(rdoConHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        buttonGroup2.add(rdoHetHang);
        rdoHetHang.setText("Ngừng bán");
        jPanel24.add(rdoHetHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jPanel8.add(jPanel24);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        tbl_SPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên sản phẩm", "Màu sắc", "Kích thước", "Chất liệu", "Công nghệ", "Số lượng", "Đơn giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPCTMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_SPCT);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Danh sách sản phẩm");

        javax.swing.GroupLayout SanPhamChiTietLayout = new javax.swing.GroupLayout(SanPhamChiTiet);
        SanPhamChiTiet.setLayout(SanPhamChiTietLayout);
        SanPhamChiTietLayout.setHorizontalGroup(
            SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(SanPhamChiTietLayout.createSequentialGroup()
                        .addGroup(SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        SanPhamChiTietLayout.setVerticalGroup(
            SanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamChiTietLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        viewSanPham.addTab("Sản phẩm chi tiết", SanPhamChiTiet);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        pnlBangThuocTinh.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính sản phẩm"));
        pnlBangThuocTinh.setPreferredSize(new java.awt.Dimension(1078, 250));
        pnlBangThuocTinh.setLayout(new java.awt.BorderLayout());

        pnlChucNang.setBackground(new java.awt.Color(255, 255, 255));
        pnlChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlChucNang.setPreferredSize(new java.awt.Dimension(200, 214));

        btnThemThuocTinh.setBackground(new java.awt.Color(0, 204, 255));
        btnThemThuocTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemThuocTinh.setText("Thêm ");
        btnThemThuocTinh.setPreferredSize(new java.awt.Dimension(100, 35));
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setBackground(new java.awt.Color(0, 204, 255));
        btnSuaThuocTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnLoc.setBackground(new java.awt.Color(0, 204, 255));
        btnLoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.setPreferredSize(new java.awt.Dimension(100, 35));
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setBackground(new java.awt.Color(0, 204, 255));
        btnXoaThuocTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaThuocTinh.setText("Xóa");
        btnXoaThuocTinh.setPreferredSize(new java.awt.Dimension(100, 35));
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pnlBangThuocTinh.add(pnlChucNang, java.awt.BorderLayout.LINE_END);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        pnlThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlThuocTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlThuocTinh.setPreferredSize(new java.awt.Dimension(500, 226));

        lblThongBaoMaThuocTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoMaThuocTinh.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoMaThuocTinh.setText("Mã phải có dạng....");

        lblMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaThuocTinh.setText("Mã Thuộc tính: ");

        txtMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblThongBaoTenThuocTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThongBaoTenThuocTinh.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTenThuocTinh.setText("jLabel15");

        lblTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenThuocTinh.setText("Tên thuộc tính: ");

        txtTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblMoTa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMoTa.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        javax.swing.GroupLayout pnlThuocTinhLayout = new javax.swing.GroupLayout(pnlThuocTinh);
        pnlThuocTinh.setLayout(pnlThuocTinhLayout);
        pnlThuocTinhLayout.setHorizontalGroup(
            pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThuocTinhLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaThuocTinh)
                    .addComponent(lblTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThongBaoTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(lblThongBaoMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuocTinh)
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThuocTinhLayout.setVerticalGroup(
            pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThuocTinhLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaThuocTinh)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongBaoMaThuocTinh)
                .addGap(18, 18, 18)
                .addGroup(pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenThuocTinh)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongBaoTenThuocTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel10.add(pnlThuocTinh);

        pnlRadio.setBackground(new java.awt.Color(255, 255, 255));
        pnlRadio.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlRadio.setLayout(new java.awt.GridLayout(3, 2));

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoMauSac.setText("Màu sắc");
        pnlRadio.add(rdoMauSac);

        buttonGroup1.add(rdoKichThuoc);
        rdoKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoKichThuoc.setText("Kích thước");
        pnlRadio.add(rdoKichThuoc);

        buttonGroup1.add(rdoHang);
        rdoHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoHang.setText("Hãng");
        pnlRadio.add(rdoHang);

        buttonGroup1.add(rdoCongNghe);
        rdoCongNghe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoCongNghe.setText("Công nghệ");
        pnlRadio.add(rdoCongNghe);

        buttonGroup1.add(rdoChatLieu);
        rdoChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoChatLieu.setText("Chất liệu ");
        pnlRadio.add(rdoChatLieu);

        jPanel10.add(pnlRadio);

        pnlBangThuocTinh.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel6.add(pnlBangThuocTinh);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblThuocTinhSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính"
            }
        ));
        tblThuocTinhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuocTinhSanPham);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7);

        viewSanPham.addTab("Thuộc tính sản phẩm", jPanel6);

        add(viewSanPham, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        kiemTraBang = 0;
        index = tbl_sanPham.getSelectedRow();
        this.showDataSanPham(index);
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void tblThuocTinhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhSanPhamMouseClicked
        // TODO add your handling code here:
        kiemTraBang = 2;
        index = tblThuocTinhSanPham.getSelectedRow();
        this.showDataThuocTinhSanPham(index);
    }//GEN-LAST:event_tblThuocTinhSanPhamMouseClicked

    private void viewSanPhamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewSanPhamStateChanged
        // TODO add your handling code here:
        int i = viewSanPham.getSelectedIndex();
        if (i == 1) {
            fillTableSPCT(sanPhamChiTiet.getAllSPCT());
            sanPhamChiTiet.dataCombobox();
            loadCbo();
        } else if (i == 0) {
            fillTableSanPham(sanPham.getAllSanPham());
            sanPhamChiTiet.dataCombobox();
            loadCbo();
        } else if (i == 2) {
            rdoChatLieu.setSelected(true);
            filltableTTSP(thuocTinhSanPham.getAll(radio()), tblThuocTinhSanPham);
        }
    }//GEN-LAST:event_viewSanPhamStateChanged

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        // TODO add your handling code here:
        if (kiemTraDieuKienThuocTinh() == true) {
            String ma = txtMaThuocTinh.getText().trim();
            if (kiemTraTrungLap(ma)) {
                int check = thuocTinhSanPham.themThuocTinh(thuocTinhSanPham.readFormThemThuocTinh(), radio());
                if (check != 0) {
                    filltableTTSP(thuocTinhSanPham.getAll(radio()), tblThuocTinhSanPham);
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                    donglabel();
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể thêm thuộc tính");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Điều kiện không hợp lệ");
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        if (kiemTraSanPham() == true) {
            String ma = txt_maSp.getText().trim();
            if (kiemTraTrungLap(ma)) {
                int i = sanPham.themSanPham(readFormSanPham());
                if (i != 0) {
                    fillTableSanPham(sanPham.getAllSanPham());
                    sanPhamChiTiet.dataCombobox();
                    loadCbo();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        donglabel();
        filltableTTSP(thuocTinhSanPham.getAll(radio()), tblThuocTinhSanPham);

        if (radio().equals("CongNghe")) {
            lblMoTa.setVisible(true);
            txtMoTa.setVisible(true);
        } else {
            lblMoTa.setVisible(false);
            txtMoTa.setVisible(false);
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        // TODO add your handling code here:
        if (kiemTraBang == 2 && index != -1) {
            String maThuocTinh = tblThuocTinhSanPham.getValueAt(index, 0).toString();
            int check = thuocTinhSanPham.suaThuocTinh(thuocTinhSanPham.readFormThemThuocTinh(), maThuocTinh);
            if (check == 0) {
                JOptionPane.showMessageDialog(this, "Dữ liệu chưa được thay đổi");
            } else {
                filltableTTSP(thuocTinhSanPham.getAll(radio()), tblThuocTinhSanPham);
                lblThongBaoMaThuocTinh.setText("Mã thuộc tính sẽ không bị thay đổi");
                lblThongBaoMaThuocTinh.setVisible(true);
                JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để thay đổi");
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        // TODO add your handling code here:
        if (kiemTraBang == 2 && index != -1) {
            String s = tblThuocTinhSanPham.getValueAt(index, 0).toString();
            int i = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa thuộc tính " + s,
                    "Yêu cầu xác nhận", JOptionPane.YES_NO_OPTION);
            int check = thuocTinhSanPham.xoaThuocTinh(thuocTinhSanPham.readFormThemThuocTinh());
            if (i == JOptionPane.YES_OPTION && check != 0) {
                filltableTTSP(thuocTinhSanPham.getAll(radio()), tblThuocTinhSanPham);
                sanPhamChiTiet.dataCombobox();
                loadCbo();
                JOptionPane.showMessageDialog(this, "Xóa thuộc tính thành công");
                index = -1;
            } else if (i == JOptionPane.YES_OPTION && check == 0) {
                JOptionPane.showMessageDialog(this, "Lỗi không thể xóa", "thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào để xóa");
        }
    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        // TODO add your handling code here:
        if (kiemTraDieuKienSPCT() == true) {
            String ma = txtMaSPCt.getText().trim();
            if (kiemTraTrungLap(ma)) {
                int check = sanPhamChiTiet.themSanPhamChiTiet(readFormSPCT());
                if (check != 0) {
                    fillTableSPCT(sanPhamChiTiet.getAllSPCT());
                    JOptionPane.showMessageDialog(this, "thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi, nghỉ cho khỏe");
                }
            }
        }
    }//GEN-LAST:event_btnThemSPCTActionPerformed

    private void btnCapNhatSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPCTActionPerformed
        // TODO add your handling code here:

        if (kiemTraBang == 1 && index != -1) {
            if (kiemTraDieuKienSPCT() == true) {
                String s = tbl_SPCT.getValueAt(index, 1).toString();
                int check = sanPhamChiTiet.suaSanPhamChiTiet(readFormSPCT(), s);
                if (check != 0) {
                    fillTableSPCT(sanPhamChiTiet.getAllSPCT());
                    sanPhamChiTiet.dataCombobox();
                    loadCbo();
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể sửa", "thông báo", JOptionPane.ERROR_MESSAGE);

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để sửa");
        }
    }//GEN-LAST:event_btnCapNhatSPCTActionPerformed

    private void btnXoaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCTActionPerformed
        // TODO add your handling code here:
        if (kiemTraBang == 1 && index != -1) {
            String s = tbl_SPCT.getValueAt(index, 1).toString();
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa SP " + s, "Yêu cầu xác nhận", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                if (sanPhamChiTiet.xoaSanPhamChiTiet(readFormSPCT(), s) != 0) {
                    fillTableSPCT(sanPhamChiTiet.getAllSPCT());
                    index = -1;
                    sanPhamChiTiet.dataCombobox();
                    loadCbo();
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa", "thông báo", JOptionPane.ERROR_MESSAGE);

                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để sửa");
        }
    }//GEN-LAST:event_btnXoaSPCTActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here:
        if (kiemTraBang == 0 && index != -1) {
            if (kiemTraSanPham() == true) {
                int i = sanPham.suaSanPham(readFormSanPham());
                if (i != 0) {
                    fillTableSanPham(sanPham.getAllSanPham());
                    sanPhamChiTiet.dataCombobox();
                    loadCbo();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể Sửa");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để chỉnh sửa");
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        if (kiemTraBang == 0 && index != -1) {
            String t = tbl_sanPham.getValueAt(index, 2).toString();
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa " + t, "Thông Báo", JOptionPane.YES_NO_OPTION);

            if (check == JOptionPane.YES_OPTION) {
                if (sanPham.xoaSanPham(t) != 0) {
                    fillTableSanPham(sanPham.getAllSanPham());
                    sanPhamChiTiet.dataCombobox();
                    loadCbo();
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi, Không thể xóa", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đối tượng nào để chỉnh sửa");
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void tbl_SPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPCTMouseClicked
        // TODO add your handling code here:
        kiemTraBang = 1;
        index = tbl_SPCT.getSelectedRow();
        showDataSanPhamChiTiet(index);
    }//GEN-LAST:event_tbl_SPCTMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SanPhamChiTiet;
    private javax.swing.JPanel ThongTinSP;
    private javax.swing.JButton btnCapNhatSPCT;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaSPCT;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_chatLieu;
    private javax.swing.JComboBox<String> cbo_congNghe;
    private javax.swing.JComboBox<String> cbo_hang;
    private javax.swing.JComboBox<String> cbo_kichThuoc;
    private javax.swing.JComboBox<String> cbo_mau;
    private javax.swing.JComboBox<String> cbo_sanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMaThuocTinh;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTenThuocTinh;
    private javax.swing.JLabel lblThongBaoMaSP;
    private javax.swing.JLabel lblThongBaoMaThuocTinh;
    private javax.swing.JLabel lblThongBaoTenSP;
    private javax.swing.JLabel lblThongBaoTenThuocTinh;
    private javax.swing.JPanel pnlBangThuocTinh;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlRadio;
    private javax.swing.JPanel pnlThuocTinh;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoCongNghe;
    private javax.swing.JRadioButton rdoHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JTable tblThuocTinhSanPham;
    private javax.swing.JTable tbl_SPCT;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTextField txtMaSPCt;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txt_donGia;
    private javax.swing.JTextField txt_maSp;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTabbedPane viewSanPham;
    // End of variables declaration//GEN-END:variables
}
