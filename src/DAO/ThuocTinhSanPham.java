/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelThuocTinhSanPham;
import View.ViewQuanLiSanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThuocTinhSanPham {

    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private DefaultTableModel mol = new DefaultTableModel();
    private ViewQuanLiSanPham view;
    private SanPhamChiTiet SPCT;

    public ThuocTinhSanPham() {
        con = DBConnect.getConnection();
    }

    public ThuocTinhSanPham(ViewQuanLiSanPham view) {
        con = DBConnect.getConnection();
        this.view = view;
    }

    public ThuocTinhSanPham(SanPhamChiTiet SPCT) {
        this.SPCT = SPCT;
    }

    public ArrayList<ModelThuocTinhSanPham> getAll(String type) {
        ArrayList<ModelThuocTinhSanPham> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        if (type.equalsIgnoreCase("kichthuoc")) {
            sql.append("SELECT MaKichThuoc, KichThuoc FROM KichThuoc WHERE TrangThai = 1");
        } else if (type.equalsIgnoreCase("CongNghe")) {
            sql.append("SELECT Ma").append(type)
                    .append(", Ten").append(type)
                    .append(", Mota")
                    .append(" FROM ").append(type)
                    .append(" WHERE TrangThai = 1 ");
        } else {
            sql.append("SELECT Ma").append(type)
                    .append(", Ten").append(type)
                    .append(" FROM ").append(type)
                    .append(" WHERE TrangThai = 1 ");
        }

        try {
            ps = con.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                String maThuocTinh = rs.getString(1);
                String tenThuocTinh = rs.getString(2);
                if (type.equalsIgnoreCase("CongNghe")) {
                    String mota = rs.getString(3);
                    list.add(new ModelThuocTinhSanPham(maThuocTinh, tenThuocTinh, mota));
                } else {
                    list.add(new ModelThuocTinhSanPham(maThuocTinh, tenThuocTinh));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int themThuocTinh(ModelThuocTinhSanPham t, String type) {
        // Kiểm tra dữ liệu đầu vào
        if (t == null || t.getMaThuocTinh() == null || t.getMaThuocTinh().isEmpty()
                || t.getTenThuocTinh() == null || t.getTenThuocTinh().isEmpty()) {
            return 0;
        }
        ArrayList<Object> psObject = new ArrayList<>();
        ArrayList<ModelThuocTinhSanPham> list = getAll(type);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaThuocTinh().equalsIgnoreCase(t.getMaThuocTinh())) {
                return 0;
            }
        }
        String table = view.radio();
        StringBuilder sql = new StringBuilder();

        // Xây dựng truy vấn INSERT bằng StringBuilder
        if (table.equalsIgnoreCase("kichthuoc")) {
            sql.append("INSERT INTO KichThuoc (MaKichThuoc, KichThuoc) VALUES (?, ?)");
            psObject.add(t.getMaThuocTinh());
            psObject.add(t.getTenThuocTinh());
        } else if (table.equalsIgnoreCase("CongNghe")) {
            sql.append("INSERT INTO ").append(table)
                    .append(" (Ma").append(table)
                    .append(", Ten").append(table)
                    .append(", Mota")
                    .append(") VALUES (?, ?, ?)");
            psObject.add(t.getMaThuocTinh());
            psObject.add(t.getTenThuocTinh());
            psObject.add(t.getMoTa());
        } else {
            sql.append("INSERT INTO ").append(table)
                    .append(" (Ma").append(table)
                    .append(", Ten").append(table)
                    .append(") VALUES (?, ?)");
            psObject.add(t.getMaThuocTinh());
            psObject.add(t.getTenThuocTinh());
        }

        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < psObject.size(); i++) {
                ps.setObject(i + 1, psObject.get(i));
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Sửa thuộc tính
    public int suaThuocTinh(ModelThuocTinhSanPham t, String s) {
        // Kiểm tra dữ liệu đầu vào
        if (t == null || t.getMaThuocTinh() == null || t.getMaThuocTinh().isEmpty()
                || t.getTenThuocTinh() == null || t.getTenThuocTinh().isEmpty()) {
            return 0;
        }

        String table = view.radio();
        StringBuilder sql = new StringBuilder();
        if (table.equalsIgnoreCase("kichthuoc")) {
            sql.append("UPDATE KichThuoc SET KichThuoc = ? WHERE MaKichThuoc = ?");
        } else if (table.equalsIgnoreCase("CongNghe")) {
            sql.append("UPDATE ").append(table)
                    .append(" SET Ten").append(table)
                    .append(" = ? , Mota")
                    .append(" = ? WHERE Ma").append(table)
                    .append(" = ?");
        } else {
            sql.append("UPDATE ").append(table)
                    .append(" SET Ten").append(table)
                    .append(" = ? WHERE Ma").append(table)
                    .append(" = ?");
        }

        try {
            ps = con.prepareStatement(sql.toString());
            if (table.equals("CongNghe")) {
                ps.setObject(1, t.getTenThuocTinh());
                ps.setObject(2, t.getMoTa());
                ps.setObject(3, s);
            } else {
                ps.setString(1, t.getTenThuocTinh());
                ps.setString(2, s);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
 //Xóa thuộc tính
    public int xoaThuocTinh(ModelThuocTinhSanPham t) {
        // Kiểm tra dữ liệu đầu vào
        if (t == null || t.getMaThuocTinh() == null || t.getMaThuocTinh().isEmpty()
                || t.getTenThuocTinh() == null || t.getTenThuocTinh().isEmpty()) {
            return 0;
        }

        String table = view.radio();
        StringBuilder sql = new StringBuilder();

        // Xây dựng truy vấn UPDATE bằng StringBuilder
        if (table.equalsIgnoreCase("kichthuoc")) {
            sql.append("UPDATE KichThuoc SET TrangThai = 0 WHERE MaKichThuoc = ?");
        } else {
            sql.append("UPDATE ").append(table)
                    .append(" SET TrangThai = 0")
                    .append(" WHERE Ma").append(table)
                    .append(" = ?");
        }

        try {
            ps = con.prepareStatement(sql.toString());
            ps.setObject(1, t.getMaThuocTinh());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ModelThuocTinhSanPham readFormThemThuocTinh() {
        String ma = view.getTxtMaThuocTinh().getText().trim();
        String ten = view.getTxtTenThuocTinh().getText().trim();
        String mota = view.getTxtMoTa().getText().trim();
        return new ModelThuocTinhSanPham(ma, ten, mota);
    }
    


}
