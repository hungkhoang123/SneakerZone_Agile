/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ModelHoaDon;
import Model.ModelHoaDonChiTiet;
import Model.ModelKhachHang;
import Model.ModelKhuyenMai;
import Model.Model_DangNhap;
import Model.Model_SPCT;
import DAO.Reposystory_BanHang;
import DAO.KhachHang;
import DAO.KhuyenMai;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DAO.SanPham;

/**
 *
 * @author hungb
 */
public class ViewBanHang extends javax.swing.JPanel {

    private Reposystory_BanHang repo = new Reposystory_BanHang();
    private SanPham repo1 = new SanPham();
    private KhachHang repo2 = new KhachHang();
    private KhuyenMai repo3 = new KhuyenMai();
    private DefaultTableModel mol = new DefaultTableModel();
    private int index = -1;
    Model.Model_DangNhap nv = ThongTinNguoiDungHienTai.getNguoiDungNow();

    /**
     * Creates new form ViewBanHang
     */
    public ViewBanHang() {
        initComponents();
        this.fillTableSPCT(repo.getAllSPCT());
        this.fillTableHoaDon(repo.danhSachHoaDon());
        this.fillComboBoxMau(repo1.getAllMauSac());
        this.fillComboBoxSize(repo1.getAllKichThuoc());
        khachHang.setSize(650, 650);
        khachHang.setLocationRelativeTo(null);
        this.fillTableKhachHang(repo2.getall());
        khuyenMai.setSize(1000, 650);
        khuyenMai.setLocationRelativeTo(null);
        this.fillTableKhuyenMai(repo3.getall(1));
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                capNhatLaiBang();
            }
        });
    }

    void capNhatLaiBang() {
        this.fillTableSPCT(repo.getAllSPCT());
    }

    void fillTableSPCT(ArrayList<Model.Model_SPCT> list) {
        mol = (DefaultTableModel) tbl_sanPham.getModel();
        mol.setRowCount(0);
        for (Model.Model_SPCT x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    void fillTableKhachHang(ArrayList<Model.ModelKhachHang> list) {
        mol = (DefaultTableModel) tbl_DanhSachKhachHang.getModel();
        mol.setRowCount(0);
        for (ModelKhachHang x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void fillTableKhuyenMai(ArrayList<ModelKhuyenMai> list) {
        mol = (DefaultTableModel) tbl_DanhSachKhuyenMai.getModel();
        mol.setRowCount(0);
        for (ModelKhuyenMai x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    private void fillTableHoaDon(ArrayList<ModelHoaDon> list) {
        mol = (DefaultTableModel) tbl_hoaDon.getModel();
        mol.setRowCount(0);
        for (ModelHoaDon x : list) {
            mol.addRow(x.toDataRow1());
        }
    }

    void fillTableHDCT(ArrayList<Model.ModelHoaDonChiTiet> list) {
        mol = (DefaultTableModel) tbl_hoaDonChiTiet.getModel();
        mol.setRowCount(0);
        for (Model.ModelHoaDonChiTiet x : list) {
            mol.addRow(x.toDataRow1());
        }
    }

    void fillComboBoxMau(ArrayList<Model.Model_MauSac> list) {
        cbo_mauSac.removeAllItems();
        Set<String> themItem = new HashSet<>();
        for (int i = 0; i < cbo_mauSac.getItemCount(); i++) {
            themItem.add(cbo_mauSac.getItemAt(i));
        }
        for (Model.Model_MauSac x : list) {
            String mau = x.getTenMau();
            if (!themItem.contains(mau)) {
                cbo_mauSac.addItem(mau);
            }
        }
    }

    void fillComboBoxSize(ArrayList<Model.Model_KichThuoc> list) {
        cbo_size.removeAllItems();
        Set<String> themItem = new HashSet<>();
        for (int i = 0; i < cbo_size.getItemCount(); i++) {
            themItem.add(cbo_size.getItemAt(i));
        }
        for (Model.Model_KichThuoc x : list) {
            String kt = x.getKichThuoc();
            if (!themItem.contains(kt)) {
                cbo_size.addItem(kt);
            }
        }
    }

    ModelHoaDon readFormHoaDon(int index) {
        String maHoaDon = tbl_hoaDon.getValueAt(index, 1).toString();
        String tenKhachHang = tbl_hoaDon.getValueAt(index, 2).toString();
        String ngayMuaHang = tbl_hoaDon.getValueAt(index, 3).toString();
        String SDT = tbl_hoaDon.getValueAt(index, 4).toString();
        int tongTien = Integer.parseInt(tbl_hoaDon.getValueAt(index, 5).toString());
        int giaTriGiam = Integer.parseInt(tbl_hoaDon.getValueAt(index, 6).toString());
        int giaSauGiam = Integer.parseInt(tbl_hoaDon.getValueAt(index, 7).toString());
        return new ModelHoaDon(maHoaDon, tenKhachHang, ngayMuaHang, SDT, tongTien, giaTriGiam, giaSauGiam);
    }

    Model.Model_SPCT readFormSPCT(int index) {
        int maSP;
        float donGia;
        maSP = Integer.parseInt(tbl_sanPham.getValueAt(index, 0).toString());
        donGia = Float.parseFloat(tbl_sanPham.getValueAt(index, 7).toString());
        return new Model_SPCT(maSP, donGia);
    }

    void showData(int index) {
        txt_tenKH.setText(tbl_hoaDon.getValueAt(index, 2).toString());
        try {
            if (tbl_hoaDon.getValueAt(index, 4) == null) {
                txt_soDienThoai.setText("");
            } else {
                txt_soDienThoai.setText(tbl_hoaDon.getValueAt(index, 4).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt_tongTien.setText(tbl_hoaDon.getValueAt(index, 5).toString());
        int tongTien = Integer.parseInt(txt_tongTien.getText());
        txt_khuyenMai.setText(tbl_hoaDon.getValueAt(index, 6).toString());
        txt_thanhToan.setText(tbl_hoaDon.getValueAt(index, 7).toString());
        if (nv != null) {
            txt_tenNhanVien.setText(nv.getTenNhanVien());
        }
    }

    ModelKhachHang readTable(int index) {
        String maKhachHang = tbl_DanhSachKhachHang.getValueAt(index, 0).toString();
        String ten = tbl_DanhSachKhachHang.getValueAt(index, 1).toString();
        String diaChi = tbl_DanhSachKhachHang.getValueAt(index, 2).toString();
        String sdt = tbl_DanhSachKhachHang.getValueAt(index, 3).toString();
        return new ModelKhachHang(maKhachHang, ten, diaChi, sdt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        khachHang = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachKhachHang = new javax.swing.JTable();
        khuyenMai = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_DanhSachKhuyenMai = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        hoaDon = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_hoaDon = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_xoaHoaDon = new javax.swing.JButton();
        btn_taoHoaDon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        gioHang = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_hoaDonChiTiet = new javax.swing.JTable();
        btn_xoaHDCT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        sanPham = new javax.swing.JPanel();
        txt_tenSp = new javax.swing.JTextField();
        cbo_mauSac = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbo_size = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        btn_tim = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        donHang = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        thongTinKhachHang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_tenKH = new javax.swing.JTextField();
        txt_soDienThoai = new javax.swing.JTextField();
        btn_timKH = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        thongTinHoaDon = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_tenNhanVien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_tienKhach = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_tienThua = new javax.swing.JTextField();
        btn_hoanThanh = new javax.swing.JButton();
        btn_tinhTien = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txt_thanhToan = new javax.swing.JTextField();
        btn_timKhuyenMai = new javax.swing.JButton();
        txt_khuyenMai = new javax.swing.JTextField();

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Danh sách khách hàng");
        khachHang.getContentPane().add(jLabel7, java.awt.BorderLayout.PAGE_START);

        tbl_DanhSachKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_DanhSachKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachKhachHang);

        khachHang.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Danh sách khuyến mãi");
        jLabel20.setMaximumSize(new java.awt.Dimension(154, 15));
        jLabel20.setPreferredSize(new java.awt.Dimension(154, 15));

        tbl_DanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khuyến mại", "Tên khuyến mại", "Loại khuyến mại", "Giá trị giảm", "Điều kiện số tiền", "Điều kiện hóa đơn", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbl_DanhSachKhuyenMai.setMaximumSize(new java.awt.Dimension(2147483647, 63154));
        tbl_DanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_DanhSachKhuyenMai);

        javax.swing.GroupLayout khuyenMaiLayout = new javax.swing.GroupLayout(khuyenMai.getContentPane());
        khuyenMai.getContentPane().setLayout(khuyenMaiLayout);
        khuyenMaiLayout.setHorizontalGroup(
            khuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        khuyenMaiLayout.setVerticalGroup(
            khuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khuyenMaiLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1078, 750));
        setPreferredSize(new java.awt.Dimension(1078, 750));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn");

        hoaDon.setBackground(new java.awt.Color(255, 255, 255));
        hoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        hoaDon.setLayout(new java.awt.BorderLayout());

        tbl_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày tạo", "Số điện thoại", "Tổng tiền", "Khuyến mãi", "Thanh toán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_hoaDon);
        if (tbl_hoaDon.getColumnModel().getColumnCount() > 0) {
            tbl_hoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        hoaDon.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        btn_xoaHoaDon.setBackground(new java.awt.Color(0, 204, 255));
        btn_xoaHoaDon.setText("Xóa");
        btn_xoaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaHoaDonActionPerformed(evt);
            }
        });

        btn_taoHoaDon.setBackground(new java.awt.Color(0, 204, 255));
        btn_taoHoaDon.setText("Tạo hóa đơn");
        btn_taoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(499, Short.MAX_VALUE)
                .addComponent(btn_taoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_xoaHoaDon)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_taoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hoaDon.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Giỏ hàng");

        gioHang.setBackground(new java.awt.Color(255, 255, 255));
        gioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tbl_hoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_hoaDonChiTiet);
        if (tbl_hoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tbl_hoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbl_hoaDonChiTiet.getColumnModel().getColumn(4).setPreferredWidth(30);
            tbl_hoaDonChiTiet.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        btn_xoaHDCT.setBackground(new java.awt.Color(0, 204, 255));
        btn_xoaHDCT.setText("Xóa");
        btn_xoaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaHDCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gioHangLayout = new javax.swing.GroupLayout(gioHang);
        gioHang.setLayout(gioHangLayout);
        gioHangLayout.setHorizontalGroup(
            gioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xoaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        gioHangLayout.setVerticalGroup(
            gioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gioHangLayout.createSequentialGroup()
                        .addComponent(btn_xoaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");

        sanPham.setBackground(new java.awt.Color(255, 255, 255));
        sanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_tenSp.setPreferredSize(new java.awt.Dimension(64, 25));

        cbo_mauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_mauSac.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel5.setText("Màu sắc:");

        jLabel6.setText("Size:");

        cbo_size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_size.setPreferredSize(new java.awt.Dimension(72, 25));

        tbl_sanPham.setAutoCreateRowSorter(true);
        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Màu sắc", "Kích thước", "Chất liệu", "Công nghệ", "Số lượng", "Đơn giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sanPham.setToolTipText("");
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_sanPham);

        jLabel18.setText("Tên sản phẩm:");

        btn_tim.setBackground(new java.awt.Color(0, 204, 255));
        btn_tim.setText("Tìm");
        btn_tim.setPreferredSize(new java.awt.Dimension(75, 25));
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sanPhamLayout = new javax.swing.GroupLayout(sanPham);
        sanPham.setLayout(sanPhamLayout);
        sanPhamLayout.setHorizontalGroup(
            sanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPhamLayout.createSequentialGroup()
                .addGroup(sanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(sanPhamLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sanPhamLayout.setVerticalGroup(
            sanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPhamLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(sanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_tenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Đơn hàng");

        donHang.setBackground(new java.awt.Color(255, 255, 255));
        donHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Thông tin khách hàng");

        thongTinKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        thongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên KH:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("SĐT:");

        txt_tenKH.setPreferredSize(new java.awt.Dimension(71, 30));

        txt_soDienThoai.setPreferredSize(new java.awt.Dimension(71, 30));

        btn_timKH.setBackground(new java.awt.Color(0, 204, 255));
        btn_timKH.setText("Tìm");
        btn_timKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout thongTinKhachHangLayout = new javax.swing.GroupLayout(thongTinKhachHang);
        thongTinKhachHang.setLayout(thongTinKhachHangLayout);
        thongTinKhachHangLayout.setHorizontalGroup(
            thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(txt_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_timKH))
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        thongTinKhachHangLayout.setVerticalGroup(
            thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinKhachHangLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btn_timKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(thongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Thông tin hóa đơn");

        thongTinHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        thongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên nhân viên:");

        txt_tenNhanVien.setPreferredSize(new java.awt.Dimension(71, 30));
        txt_tenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenNhanVienActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tổng tiền:");

        txt_tongTien.setPreferredSize(new java.awt.Dimension(71, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Thanh toán:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tiền khách đưa:");

        txt_tienKhach.setPreferredSize(new java.awt.Dimension(71, 30));
        txt_tienKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienKhachActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Tiền thừa:");

        txt_tienThua.setPreferredSize(new java.awt.Dimension(71, 30));

        btn_hoanThanh.setBackground(new java.awt.Color(0, 204, 255));
        btn_hoanThanh.setText("Thành công");
        btn_hoanThanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hoanThanhMouseClicked(evt);
            }
        });
        btn_hoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoanThanhActionPerformed(evt);
            }
        });

        btn_tinhTien.setBackground(new java.awt.Color(0, 204, 255));
        btn_tinhTien.setText("Tính tiền");
        btn_tinhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tinhTienActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Khuyến mãi:");

        txt_thanhToan.setPreferredSize(new java.awt.Dimension(71, 30));

        btn_timKhuyenMai.setBackground(new java.awt.Color(0, 204, 255));
        btn_timKhuyenMai.setText("Chọn khuyến mãi");
        btn_timKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKhuyenMaiActionPerformed(evt);
            }
        });

        txt_khuyenMai.setPreferredSize(new java.awt.Dimension(71, 30));

        javax.swing.GroupLayout thongTinHoaDonLayout = new javax.swing.GroupLayout(thongTinHoaDon);
        thongTinHoaDon.setLayout(thongTinHoaDonLayout);
        thongTinHoaDonLayout.setHorizontalGroup(
            thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongTinHoaDonLayout.createSequentialGroup()
                        .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_tinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_tienKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addComponent(txt_thanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_khuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btn_timKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongTinHoaDonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_hoanThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        thongTinHoaDonLayout.setVerticalGroup(
            thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(35, 35, 35)
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(35, 35, 35)
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_timKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(btn_hoanThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout donHangLayout = new javax.swing.GroupLayout(donHang);
        donHang.setLayout(donHangLayout);
        donHangLayout.setHorizontalGroup(
            donHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(donHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(donHangLayout.createSequentialGroup()
                        .addGroup(donHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(donHangLayout.createSequentialGroup()
                        .addGroup(donHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(thongTinHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thongTinKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        donHangLayout.setVerticalGroup(
            donHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongTinKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(648, 648, 648)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(gioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(sanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addComponent(donHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(gioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(sanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(donHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                int soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng"));
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    int i = tbl_hoaDon.getSelectedRow();
                    if (i == -1) {
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần thêm vào", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    index = tbl_sanPham.getSelectedRow();
                    int tonKho = Integer.parseInt(tbl_sanPham.getValueAt(index, 6).toString());
                    int conLai = tonKho - soLuong;
                    if (conLai < 0) {
                        JOptionPane.showMessageDialog(this, "Hàng trong kho không đủ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String maHoaDon = tbl_hoaDon.getValueAt(i, 1).toString();
                    repo.themHDCT(readFormSPCT(index), maHoaDon, soLuong);
                    repo.truSoLuong(soLuong, readFormSPCT(index));
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    this.fillTableHoaDon(repo.danhSachHoaDon());
                    repo.updateTienThanhToam(maHoaDon);
                    this.fillTableHoaDon(repo.danhSachHoaDon());
                    this.fillTableSPCT(repo.getAllSPCT());
                    this.fillTableHDCT(repo.getAllHĐCT(maHoaDon));
                    this.showData(i);
                    tbl_hoaDon.setRowSelectionInterval(i, i);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void tbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonMouseClicked
        index = tbl_hoaDon.getSelectedRow();
        String maHoaDon = tbl_hoaDon.getValueAt(index, 1).toString();
        this.fillTableHDCT(repo.getAllHĐCT(maHoaDon));
        this.showData(index);
        txt_tienKhach.setText("");
        txt_tienThua.setText("");
    }//GEN-LAST:event_tbl_hoaDonMouseClicked

    private void tbl_hoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_hoaDonChiTietMouseClicked

    private void btn_tinhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tinhTienActionPerformed
        if (txt_thanhToan.getText().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int thanhToan = Integer.parseInt(txt_thanhToan.getText());
        int tienKhach = 0;
        try {
            tienKhach = Integer.parseInt(txt_tienKhach.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số tiền khách.");
            return;
        }
        int tienThua = tienKhach - thanhToan;
        txt_tienThua.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_btn_tinhTienActionPerformed

    private void btn_xoaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaHDCTActionPerformed
        index = tbl_hoaDonChiTiet.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int in = tbl_hoaDon.getSelectedRow();
        String maHd = tbl_hoaDon.getValueAt(in, 1).toString();
        String maSP = tbl_hoaDonChiTiet.getValueAt(index, 1).toString();
        int soLuong = Integer.parseInt(tbl_hoaDonChiTiet.getValueAt(index, 4).toString());
        repo.xoaHoaDonChiTiet(maHd, maSP);
        repo.congSoLuong(soLuong, maSP);
        this.fillTableHDCT(repo.getAllHĐCT(maHd));
        this.fillTableHoaDon(repo.danhSachHoaDon());
        this.fillTableSPCT(repo.getAllSPCT());
        this.showData(in);
        tbl_hoaDon.setRowSelectionInterval(in, in);
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btn_xoaHDCTActionPerformed

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
        String tenSp = txt_tenSp.getText().toString();
        String mau = cbo_mauSac.getSelectedItem().toString();
        String size = cbo_size.getSelectedItem().toString();
        this.fillTableSPCT(repo.timSp(tenSp, mau, size));
    }//GEN-LAST:event_btn_timActionPerformed

    private void btn_hoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoanThanhActionPerformed
        index = tbl_hoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để thanh toán.");
            return;
        }
        int gia = Integer.parseInt(txt_thanhToan.getText().toString());
        String maHoaDon = tbl_hoaDon.getValueAt(index, 1).toString();

        Model_DangNhap nv = ThongTinNguoiDungHienTai.getNguoiDungNow();
        ArrayList<Model.ModelHoaDonChiTiet> listHDCT = new ArrayList<>();
        mol = (DefaultTableModel) tbl_hoaDonChiTiet.getModel();
        int in = -1;
        in = tbl_hoaDonChiTiet.getRowCount();

        for (int i = 0; i < in; i++) {
            int id = Integer.parseInt(mol.getValueAt(i, 0).toString());
            String maSp = mol.getValueAt(i, 1).toString();
            String tenSp = mol.getValueAt(i, 2).toString();
            int donGia = Integer.parseInt(mol.getValueAt(i, 3).toString());
            int soLuong = Integer.parseInt(mol.getValueAt(i, 4).toString());
            int thanhTien = Integer.parseInt(mol.getValueAt(i, 5).toString());
            Model.ModelHoaDonChiTiet ct = new ModelHoaDonChiTiet(id, maSp, tenSp, soLuong, donGia, thanhTien);
            listHDCT.add(ct);
        }
        int check = 1;
        check = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (check == 0) {
            InHoaDon.taoHoaDon(this.readFormHoaDon(index), listHDCT, nv);
        }
        JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        repo.updateThanhToan(gia, maHoaDon);
        this.fillTableHoaDon(repo.danhSachHoaDon());
        DefaultTableModel model = (DefaultTableModel) tbl_hoaDonChiTiet.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btn_hoanThanhActionPerformed

    private void btn_hoanThanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hoanThanhMouseClicked

    }//GEN-LAST:event_btn_hoanThanhMouseClicked

    private void txt_tenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenNhanVienActionPerformed

    private void btn_timKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKHActionPerformed
        index = tbl_hoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        khachHang.setVisible(true);
    }//GEN-LAST:event_btn_timKHActionPerformed

    private void btn_xoaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaHoaDonActionPerformed
        index = tbl_hoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maHoaDon = tbl_hoaDon.getValueAt(index, 1).toString();
        repo.xoaHoaDon(maHoaDon);
        this.fillTableHoaDon(repo.danhSachHoaDon());
        this.fillTableHDCT(repo.getAllHĐCT(maHoaDon));
        this.fillTableSPCT(repo.getAllSPCT());
        txt_tenKH.setText("");
        txt_soDienThoai.setText("");
        txt_tenNhanVien.setText("");
        txt_tongTien.setText("");
        txt_thanhToan.setText("");
        txt_khuyenMai.setText("");
        txt_tienKhach.setText("");
        txt_tienThua.setText("");
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btn_xoaHoaDonActionPerformed

    private void btn_taoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDonActionPerformed
        repo.themHoaDon();
        this.fillTableHoaDon(repo.danhSachHoaDon());
        index = tbl_hoaDon.getRowCount() - 1;
        tbl_hoaDon.setRowSelectionInterval(index, index);
        JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
    }//GEN-LAST:event_btn_taoHoaDonActionPerformed

    private void tbl_DanhSachKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachKhachHangMouseClicked
        index = tbl_DanhSachKhachHang.getSelectedRow();
        int in = tbl_hoaDon.getSelectedRow();
        String maHoaDon = tbl_hoaDon.getValueAt(in, 1).toString();
        ModelKhachHang kh = this.readTable(index);
        khachHang.dispose();
        repo.updateThongTin(kh.getMaKhachHang(), maHoaDon);
        this.fillTableHoaDon(repo.danhSachHoaDon());
        this.showData(in);
        tbl_hoaDon.setRowSelectionInterval(in, in);
    }//GEN-LAST:event_tbl_DanhSachKhachHangMouseClicked

    private void txt_tienKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachActionPerformed

    private void btn_timKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKhuyenMaiActionPerformed
        index = tbl_hoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        khuyenMai.setVisible(true);
    }//GEN-LAST:event_btn_timKhuyenMaiActionPerformed

    private void tbl_DanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachKhuyenMaiMouseClicked
        index = tbl_DanhSachKhuyenMai.getSelectedRow();
        String idKM = tbl_DanhSachKhuyenMai.getValueAt(index, 1).toString();
        float giaTriGiam = Float.parseFloat(tbl_DanhSachKhuyenMai.getValueAt(index, 4).toString());

        int in = tbl_hoaDon.getSelectedRow();
        float tongTien = Float.parseFloat(tbl_hoaDon.getValueAt(in, 5).toString());
        int tienGiam = (int) (tongTien * giaTriGiam * 0.01);
        String maHD = tbl_hoaDon.getValueAt(in, 1).toString();

        txt_khuyenMai.setText(String.valueOf(tienGiam));
        int thanhToan = (int) (tongTien - tienGiam);
        txt_thanhToan.setText(String.valueOf(thanhToan));

        khuyenMai.dispose();
        repo.updateTien(idKM, tienGiam, thanhToan, maHD);
        this.fillTableHoaDon(repo.danhSachHoaDon());
        tbl_hoaDon.setRowSelectionInterval(in, in);
    }//GEN-LAST:event_tbl_DanhSachKhuyenMaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hoanThanh;
    private javax.swing.JButton btn_taoHoaDon;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_timKH;
    private javax.swing.JButton btn_timKhuyenMai;
    private javax.swing.JButton btn_tinhTien;
    private javax.swing.JButton btn_xoaHDCT;
    private javax.swing.JButton btn_xoaHoaDon;
    private javax.swing.JComboBox<String> cbo_mauSac;
    private javax.swing.JComboBox<String> cbo_size;
    private javax.swing.JPanel donHang;
    private javax.swing.JPanel gioHang;
    private javax.swing.JPanel hoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JDialog khachHang;
    private javax.swing.JDialog khuyenMai;
    private javax.swing.JPanel sanPham;
    private javax.swing.JTable tbl_DanhSachKhachHang;
    private javax.swing.JTable tbl_DanhSachKhuyenMai;
    private javax.swing.JTable tbl_hoaDon;
    private javax.swing.JTable tbl_hoaDonChiTiet;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JPanel thongTinHoaDon;
    private javax.swing.JPanel thongTinKhachHang;
    private javax.swing.JTextField txt_khuyenMai;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_tenKH;
    private javax.swing.JTextField txt_tenNhanVien;
    private javax.swing.JTextField txt_tenSp;
    private javax.swing.JTextField txt_thanhToan;
    private javax.swing.JTextField txt_tienKhach;
    private javax.swing.JTextField txt_tienThua;
    private javax.swing.JTextField txt_tongTien;
    // End of variables declaration//GEN-END:variables
}
