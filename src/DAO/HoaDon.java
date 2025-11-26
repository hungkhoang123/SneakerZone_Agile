/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import View.*;
import Model.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author VIETTECH88
 */
public class HoaDon {

    final Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public HoaDon() {
        con = DBConnect.getConnection();
    }

    public ArrayList<ModelHoaDon> danhSachHoaDon() {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        try {
            sql = "SELECT MaHoaDon,TenKhachHang, MaKhuyenMai, NgayMuaHang, TongTien, GiaSauGiam, TrangThai FROM HoaDonBackup";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                String tenKhachHang = rs.getString(2);
                String maKhuyenMai = rs.getString(3);
                String ngayMuaHang = rs.getString(4);
                Integer tongTien = rs.getInt(5);
                Integer giaSauGiam = rs.getInt(6);
                boolean trangThai = rs.getBoolean(7);
                list.add(new ModelHoaDon(maHoaDon, tenKhachHang, maKhuyenMai, ngayMuaHang, tongTien, giaSauGiam, trangThai));
            }
            list.sort((o1, o2) -> o1.getTongTien().compareTo(o2.getTongTien()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelHoaDon> oTimKiem(String t) {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        try {
            sql = """
                SELECT 
                      MaHoaDon
                    , TenKhachHang
                    , MaNhanVien
                    , MaKhuyenMai
                    , NgayMuaHang
                    , TongTien
                    , GiaSauGiam
                    , TrangThai
                FROM HoaDonBackup
                WHERE TenKhachHang LIKE ? OR MaNhanVien LIKE ? OR MaHoaDon LIKE ? 
                  """;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + t + "%");
            ps.setString(2, "%" + t + "%");
            ps.setString(3, "%" + t + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                String tenKhachHang = rs.getString(2);
                String maNhanVien = rs.getString(3);
                String maKhuyenMai = rs.getString(4);
                String ngayMuaHang = rs.getString(5);
                Integer tongTien = rs.getInt(6);
                Integer giaSauGiam = rs.getInt(7);
                Boolean trangThai = rs.getBoolean(8);
                list.add(new ModelHoaDon(maHoaDon, tenKhachHang, maNhanVien, maKhuyenMai, ngayMuaHang, tongTien, giaSauGiam, trangThai));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelHoaDonChiTiet> danhSachHoaDonChiTiet(String mahoadon) {
        ArrayList<ModelHoaDonChiTiet> list = new ArrayList<>();
        try {

            sql = "\"SELECT ROW_NUMBER() OVER (ORDER BY A.id) AS STT, B.MaSanPhamChiTiet, C.TenSanPham, A.SoLuong, B.DonGia, (B.DonGia*A.SoLuong) AS ThanhTien FROM HoaDonChiTiet A\\n\"\n"
                    + "                + \"INNER JOIN SanPhamChiTiet B ON B.ID = A.IDSanPhamChiTiet\\n\"\n"
                    + "                + \"INNER JOIN SanPham C ON C.ID = B.IDSanPham\\n\"\n"
                    + "                + \"WHERE A.IDHoaDon = (SELECT TOP 1 ID FROM HoaDon WHERE MaHoaDon = ?)\"";
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahoadon);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHoaDonChiTiet = rs.getInt(1);
                String tenSanPham = rs.getString(2);
                String maHoaDon = rs.getString(3);
                int soLuong = rs.getInt(4);
                int donGia = rs.getInt(5);
                list.add(new ModelHoaDonChiTiet(maHoaDonChiTiet, tenSanPham, maHoaDon, soLuong, donGia));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelHoaDon> locSanPham(ModelHoaDon t) {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        String danhSach = """
                        SELECT 
                            MaHoaDon
                            , TenKhachHang
                            , MaNhanVien
                            , MaKhuyenMai
                            , NgayMuaHang
                            , TongTien
                            , GiaSauGiam
                            , TrangThai
                        FROM HoaDonBackup
                        WHERE 1 = 1
                          """;
        try {
            StringBuilder sql = new StringBuilder(danhSach);
            ArrayList<Object> danhSachDieuKien = new ArrayList<>();
            if (t.isTrangThai() != null) {
                sql.append("AND TrangThai = ?");
                danhSachDieuKien.add(t.isTrangThai());
            }
            if (t.getTongTien() != null) {
                sql.append(" AND TongTien BETWEEN ? AND ?");
                danhSachDieuKien.add(t.getTongTien());
                danhSachDieuKien.add(t.getGiaTriA());
            }
            if (t.getNgayMuaHang() != null && t.getNgayThanhToan() != null) {
                sql.append("AND NgayMuaHang BETWEEN ? AND ?");
                danhSachDieuKien.add(t.getNgayMuaHang());
                danhSachDieuKien.add(t.getNgayThanhToan());
            }

//            if (s != null && !s.isBlank()) {
//                sql.append("AND (ID LIKE ? OR TenKhachHang LIKE ? OR TenNhanVien LIkE ?)");
//                danhSachDieuKien.add("%" + s + "%");
//                danhSachDieuKien.add("%" + s + "%");
//                danhSachDieuKien.add("%" + s + "%");
//            }
            ps = con.prepareCall(sql.toString());
            for (int i = 0; i < danhSachDieuKien.size(); i++) {
                ps.setObject(i + 1, danhSachDieuKien.get(i));
            }
            rs = ps.executeQuery();
            System.out.println(danhSachDieuKien.size());
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                String tenKhachHang = rs.getString(2);
                String maNhanVien = rs.getString(3);
                String maKhuyenMai = rs.getString(4);
                String ngayMuaHang = rs.getString(5);
                Integer tongTien = rs.getInt(6);
                Integer giaSauGiam = rs.getInt(7);
                boolean trangThai = rs.getBoolean(8);
                list.add(new ModelHoaDon(maHoaDon, tenKhachHang, maNhanVien, maKhuyenMai, ngayMuaHang, tongTien, giaSauGiam, trangThai));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ModelHoaDonInHoaDon getChiTietHoaDon(String maHoaDon) {
        ModelHoaDonInHoaDon hoaDon = null;

        try {
            String sql = "SELECT hd.MaHoaDon, hd.NgayMuaHang, hd.TongTien, hd.GiaGiam, hd.GiaSauGiam, hd.TrangThai, "
                    + "kh.Ten AS TenKhachHang, kh.SDT AS SDTKhachHang, kh.Email AS EmailKhachHang, "
                    + "nv.MaNhanVien, nv.TenNhanVien, "
                    + "km.MaKhuyenMai, km.TenKhuyenMai "
                    + "FROM HoaDon hd "
                    + "LEFT JOIN KhachHang kh ON hd.IDKhachHang = kh.ID "
                    + "INNER JOIN NhanVien nv ON hd.IDNhanVien = nv.ID "
                    + "LEFT JOIN KhuyenMai km ON hd.IDKhuyenMai = km.ID "
                    + "WHERE hd.MaHoaDon = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            rs = ps.executeQuery();

            if (rs.next()) {
                hoaDon = new ModelHoaDonInHoaDon();
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setNgayMuaHang(rs.getString("NgayMuaHang"));
                hoaDon.setTongTien(rs.getDouble("TongTien"));
                hoaDon.setGiaGiam(rs.getDouble("GiaGiam"));
                hoaDon.setGiaSauGiam(rs.getDouble("GiaSauGiam"));
                hoaDon.setTrangThai(rs.getBoolean("TrangThai"));

                hoaDon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDon.setSdtKhachHang(rs.getString("SDTKhachHang"));
                hoaDon.setEmailKhachHang(rs.getString("EmailKhachHang"));

                hoaDon.setMaNhanVien(rs.getString("MaNhanVien"));
                hoaDon.setTenNhanVien(rs.getString("TenNhanVien"));

                hoaDon.setMaKhuyenMai(rs.getString("MaKhuyenMai"));
                hoaDon.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hoaDon;
    }
}
