/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelHoaDon;
import Model.ModelHoaDonChiTiet;
import Model.Model_SPCT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author hungb
 */
public class Reposystory_BanHang {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public Reposystory_BanHang() {
        con = DBConnect.getConnection();
    }

    //Load bảng SPCT
    public ArrayList<Model.Model_SPCT> getAllSPCT() {
        ArrayList<Model.Model_SPCT> listSp = new ArrayList<>();
        sql = "SELECT A.ID, B.TenSanPham, C.TenMauSac, D.KichThuoc, E.TenChatLieu, F.TenCongNghe, A.SoLuong, A.DonGia, A.TrangThai FROM SanPhamChiTiet A\n"
                + "INNER JOIN SanPham B ON A.IDSanPham = B.ID\n"
                + "INNER JOIN MauSac C ON A.IDMauSac = C.ID\n"
                + "INNER JOIN KichThuoc D ON A.IDKichThuoc = D.ID\n"
                + "INNER JOIN ChatLieu E ON A.IDChatLieu = E.ID\n"
                + "INNER JOIN CongNghe F ON A.IDCongNghe = F.ID;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID, soLuong;
                String tenSP, mauSac, kichThuoc, chatLieu, congNghe;
                double donGia;
                boolean trangThai;
                ID = rs.getInt(1);
                tenSP = rs.getString(2);
                mauSac = rs.getString(3);
                kichThuoc = rs.getString(4);
                chatLieu = rs.getString(5);
                congNghe = rs.getString(6);
                soLuong = rs.getInt(7);
                donGia = rs.getDouble(8);
                trangThai = rs.getBoolean(9);
                listSp.add(new Model_SPCT(ID, tenSP, mauSac, kichThuoc, chatLieu, congNghe, soLuong, donGia, trangThai));
            }
            Collections.sort(listSp, Comparator.comparingInt(Model_SPCT::getID));
            return listSp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Load bảng hóa đơn
    public ArrayList<ModelHoaDon> danhSachHoaDon() {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        try {
            sql = "SELECT ROW_NUMBER() OVER (ORDER BY A.id)AS STT, A.MaHoaDon, B.Ten, A.NgayMuaHang, B.SDT, A.TongTien, A.GiaGiam, A.GiaSauGiam, A.TrangThai FROM HoaDon A\n"
                    + "INNER JOIN KhachHang B ON B.ID = A.IDKhachHang\n"
                    + "WHERE A.TrangThai = 0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt(1);
                String maHoaDon = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String ngayMuaHang = rs.getString(4);
                String SDT = rs.getString(5);
                int tongTien = rs.getInt(6);
                int giaTriGiam = rs.getInt(7);
                int giaSauGiam = rs.getInt(8);
                boolean trangThai = rs.getBoolean(9);
                list.add(new ModelHoaDon(ID, maHoaDon, tenKhachHang, ngayMuaHang, SDT, tongTien, giaTriGiam, giaSauGiam, trangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Load HĐCT
    public ArrayList<Model.ModelHoaDonChiTiet> getAllHĐCT(String maHoaDon) {
        ArrayList<Model.ModelHoaDonChiTiet> listHDCT = new ArrayList<>();
        sql = "SELECT ROW_NUMBER() OVER (ORDER BY A.id) AS STT, B.MaSanPhamChiTiet, C.TenSanPham, A.SoLuong, B.DonGia, (B.DonGia*A.SoLuong) AS ThanhTien FROM HoaDonChiTiet A\n"
                + "INNER JOIN SanPhamChiTiet B ON B.ID = A.IDSanPhamChiTiet\n"
                + "INNER JOIN SanPham C ON C.ID = B.IDSanPham\n"
                + "WHERE A.IDHoaDon = (SELECT TOP 1 ID FROM HoaDon WHERE MaHoaDon = ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHoaDon);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID;
                String maSP, tenSp;
                int soLuong, donGia;
                int tongTien;
                ID = rs.getInt(1);
                maSP = rs.getString(2);
                tenSp = rs.getString(3);
                soLuong = rs.getInt(4);
                donGia = rs.getInt(5);
                tongTien = (int) rs.getDouble(6);
                listHDCT.add(new ModelHoaDonChiTiet(ID, maSP, tenSp, soLuong, donGia, tongTien));
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Thêm hóa đơn
    public int themHoaDon() {
        sql = "INSERT INTO HoaDon(IDKhachHang, IDNhanVien, IDKhuyenMai, NgayMuaHang, TongTien, GiaGiam, GiaSauGiam, TrangThai)\n"
                + "SELECT ID, 1, NULL, GETDATE(), 0, 0, 0, 0 FROM KhachHang WHERE MaKhachHang = 'KH0000';";

        try {
            ps = con.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Thêm HĐCT
    public int themHDCT(Model_SPCT spct, String maHoaDon, int soLuong) {
        sql = "INSERT INTO HoaDonChiTiet (IDSanPhamChiTiet, IDHoaDon, SoLuong, DonGia)\n"
                + "SELECT ?, ID, ?, ?\n"
                + "FROM (\n"
                + "    SELECT TOP 1 ID\n"
                + "    FROM HoaDon\n"
                + "    WHERE MaHoaDon = ?\n"
                + ") AS HoaDonDuocChon;";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getID());
            ps.setObject(2, soLuong);
            ps.setObject(3, spct.getDonGia());
            ps.setObject(4, maHoaDon);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Trừ số lượng
    public int truSoLuong(int index, Model_SPCT spct) {
        sql = "UPDATE SanPhamChiTiet\n"
                + "SET SoLuong = SoLuong - ?\n"
                + "WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, index);
            ps.setObject(2, spct.getID());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Cộng số lượng
    public int congSoLuong(int soLuong, String maSp) {
        sql = "UPDATE SanPhamChiTiet\n"
                + "SET SoLuong = SoLuong + ?\n"
                + "WHERE MaSanPhamChiTiet = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, soLuong);
            ps.setObject(2, maSp);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Xóa hóa đơn chi tiết
    public int xoaHoaDonChiTiet(String maHD, String maSP) {
        sql = "DELETE A FROM HoaDonChiTiet A\n"
                + "INNER JOIN HoaDon B ON A.IDHoaDon = B.ID\n"
                + "INNER JOIN SanPhamChiTiet C ON A.IDSanPhamChiTiet = C.ID\n"
                + "WHERE B.MaHoaDon = ? AND C.MaSanPhamChiTiet = ?;\n"
                + "\n"
                + "UPDATE HoaDon\n"
                + "SET GiaGiam = 0,\n"
                + "	GiaSauGiam = 0\n"
                + "WHERE MaHoaDon = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            ps.setObject(2, maSP);
            ps.setObject(3, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Xóa hóa đơn
    public int xoaHoaDon(String maHoaDon) {
        sql = "	IF NOT EXISTS (SELECT 1 \n"
                + "                   FROM HoaDonChiTiet hct\n"
                + "                   JOIN HoaDon hd ON hd.ID = hct.IDHoaDon\n"
                + "                   WHERE hd.MaHoaDon = ?)\n"
                + "				BEGIN\n"
                + "					DELETE FROM HoaDon\n"
                + "					WHERE MaHoaDon = ?\n"
                + "					RETURN;\n"
                + "				END\n"
                + "\n"
                + "    UPDATE A\n"
                + "    SET A.SoLuong = A.SoLuong + t.TongSoLuong\n"
                + "    FROM SanPhamChiTiet A\n"
                + "    INNER JOIN (\n"
                + "        SELECT A.IDSanPhamChiTiet, SUM(A.SoLuong) AS TongSoLuong\n"
                + "        FROM HoaDonChiTiet A\n"
                + "        INNER JOIN HoaDon B ON B.ID = A.IDHoaDon\n"
                + "        WHERE B.MaHoaDon = ?\n"
                + "        GROUP BY A.IDSanPhamChiTiet\n"
                + "    ) t ON A.ID = t.IDSanPhamChiTiet;\n"
                + "\n"
                + "    DELETE A\n"
                + "    FROM HoaDonChiTiet A\n"
                + "    JOIN HoaDon B ON A.IDHoaDon = B.ID\n"
                + "    WHERE B.MaHoaDon = ?;\n"
                + "\n"
                + "    DELETE FROM HoaDon\n"
                + "    WHERE MaHoaDon = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHoaDon);
            ps.setObject(2, maHoaDon);
            ps.setObject(3, maHoaDon);
            ps.setObject(4, maHoaDon);
            ps.setObject(5, maHoaDon);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Tìm kiếm sp
    public ArrayList<Model_SPCT> timSp(String ten, String mau, String size) {
        ArrayList<Model_SPCT> list = new ArrayList<>();
        sql = "SELECT A.ID, B.TenSanPham, C.TenMauSac, D.KichThuoc, E.TenChatLieu, F.TenCongNghe, A.SoLuong, A.DonGia, A.TrangThai FROM SanPhamChiTiet A\n"
                + "INNER JOIN SanPham B ON A.IDSanPham = B.ID\n"
                + "INNER JOIN MauSac C ON A.IDMauSac = C.ID\n"
                + "INNER JOIN KichThuoc D ON A.IDKichThuoc = D.ID\n"
                + "INNER JOIN ChatLieu E ON A.IDChatLieu = E.ID\n"
                + "INNER JOIN CongNghe F ON A.IDCongNghe = F.ID\n"
                + "WHERE B.TenSanPham LIKE ? AND C.TenMauSac = ? AND D.KichThuoc = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            ps.setObject(2, mau);
            ps.setObject(3, size);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID, soLuong;
                String tenSP, mauSac, kichThuoc, chatLieu, congNghe;
                double donGia;
                boolean trangThai;
                ID = rs.getInt(1);
                tenSP = rs.getString(2);
                mauSac = rs.getString(3);
                kichThuoc = rs.getString(4);
                chatLieu = rs.getString(5);
                congNghe = rs.getString(6);
                soLuong = rs.getInt(7);
                donGia = rs.getDouble(8);
                trangThai = rs.getBoolean(9);
                list.add(new Model_SPCT(ID, tenSP, mauSac, kichThuoc, chatLieu, congNghe, soLuong, donGia, trangThai));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Update thanh toán thành công
    public int updateThanhToan(int gia, String maHoaDon) {
        sql = "UPDATE HoaDon SET TrangThai = 1 WHERE MaHoaDon = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHoaDon);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Update thông tin khách hàng
    public int updateThongTin(String maKhachHang, String maHoaDon) {
        sql = "UPDATE HoaDon\n"
                + "SET IDKhachHang = (SELECT TOP 1 ID FROM KhachHang WHERE MaKhachHang = ?)\n"
                + "WHERE MaHoaDon = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKhachHang);
            ps.setObject(2, maHoaDon);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Update tiền khuyến mãi và tiền phải thanh toán
    public int updateTien(String maKhuyenMai, int tienGiam, int tienThanhToan, String maHD) {
        sql = "UPDATE HoaDon\n"
                + "SET IDKhuyenMai = (SELECT TOP 1 ID FROM KhuyenMai WHERE MaKhuyenMai = ?),\n"
                + "	GiaGiam = ?,\n"
                + "	GiaSauGiam = ?\n"
                + "WHERE MaHoaDon = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKhuyenMai);
            ps.setObject(2, tienGiam);
            ps.setObject(3, tienThanhToan);
            ps.setObject(4, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Update tiền khi thêm sản phẩm
    public int updateTienThanhToam(String maHD) {
        sql = "UPDATE HoaDon\n"
                + "SET GiaSauGiam = TongTien -  GiaGiam\n"
                + "WHERE MaHoaDon = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
