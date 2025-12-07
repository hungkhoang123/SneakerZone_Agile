/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Model_DangNhap;
import Model.ModelSanPham;
import Model.Model_KichThuoc;
import Model.Model_MauSac;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author hungb
 */
public class SanPham {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public SanPham() {
        con = DBConnect.getConnection();
    }

    public ArrayList<Model.Model_DangNhap> checkDangNhap(String DangNhap, String MatKhau) {
        ArrayList<Model.Model_DangNhap> listNv = new ArrayList<>();
        sql = "SELECT MaNhanVien, TenNhanVien, TenDangNhap, MatKhau \n"
                + "FROM NhanVien \n"
                    + "WHERE TenDangNhap COLLATE Latin1_General_BIN = ? \n"
                    + "AND MatKhau COLLATE Latin1_General_BIN = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, DangNhap);
            ps.setObject(2, MatKhau);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma_nv, ten_nv, dang_nhap, mat_khau;
                ma_nv = rs.getString(1);
                ten_nv = rs.getString(2);
                dang_nhap = rs.getString(3);
                mat_khau = rs.getString(4);
                listNv.add(new Model_DangNhap(ma_nv, ten_nv, dang_nhap, mat_khau));
            }
            return listNv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model.ModelSanPham> getAllSanPham() {
        ArrayList<Model.ModelSanPham> listSp = new ArrayList<>();
        sql = """
              SELECT 
              	 ROW_NUMBER() OVER (ORDER BY MaSanPham) AS [STT]
              	, H.TenHang
              	, SP.MaSanPham
              	, SP.TenSanPham
              FROM SanPham SP
              JOIN Hang H ON SP.IDHang = H.ID
              WHERE SP.TrangThai = 1
              """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID;
                String maSanPham, Hang, tenSp;
                ID = rs.getInt(1);
                Hang = rs.getString(2);
                maSanPham = rs.getString(3);
                tenSp = rs.getString(4);
                listSp.add(new ModelSanPham(ID, Hang, maSanPham, tenSp));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    public int themSanPham(ModelSanPham Sp_moi) {
        sql = """
              INSERT INTO SanPham (MaSanPham, TenSanPham, IDHang)
              SELECT 
                    ?, ? , Hang.ID
              FROM Hang
              WHERE Hang.TenHang = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, Sp_moi.getMaSanPham());
            ps.setObject(2, Sp_moi.getTenSp());
            ps.setObject(3, Sp_moi.getHang());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int suaSanPham(ModelSanPham sua){
        sql="""
            UPDATE SanPham
            SET TenSanPham = ?,
            IDHang = (SELECT ID FROM Hang WHERE TenHang = ?) 
            WHERE MaSanPham = ?""";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, sua.getTenSp());
            ps.setObject(2, sua.getHang());
            ps.setObject(3, sua.getMaSanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int xoaSanPham(String t){
        sql="""
            UPDATE SanPham
            SET TrangThai = 0
            WHERE MaSanPham = ?""";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, t);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
   public HashSet<String> getAllHang() {
        HashSet<String> setMa = new HashSet<>();
        String sql = """
                        SELECT 
                            SP.MaSanPham,
                            MS.MaMauSac,
                            KT.MaKichThuoc,
                            CL.MaChatLieu,
                            CN.MaCongNghe,
                            H.MaHang,
                            SPCT.MaSanPhamChiTiet
                        FROM SanPhamChiTiet SPCT
                        FULL OUTER JOIN SanPham SP ON SPCT.IDSanPham = SP.ID
                        FULL OUTER JOIN Hang H ON SP.IDHang = H.ID
                        FULL OUTER JOIN MauSac MS ON SPCT.IDMauSac = MS.ID
                        FULL OUTER JOIN KichThuoc KT ON SPCT.IDKichThuoc = KT.ID
                        FULL OUTER JOIN ChatLieu CL ON SPCT.IDChatLieu = CL.ID
                        FULL OUTER JOIN CongNghe CN ON SPCT.IDCongNghe = CN.ID
            """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString(1);
                String maMauSac = rs.getString(2);
                String maKichThuoc = rs.getString(3);
                String maChatLieu = rs.getString(4);
                String maCongNghe = rs.getString(5);
                String maHang = rs.getString(6);
                String maSanPhamChiTiet = rs.getString(7);

                // Thêm tất cả các mã vào HashSet
                setMa.add(maSanPham);
                setMa.add(maMauSac);
                setMa.add(maKichThuoc);
                setMa.add(maChatLieu);
                setMa.add(maCongNghe);
                setMa.add(maHang);
                setMa.add(maSanPhamChiTiet);
            }
            return setMa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
   public ArrayList<Model.Model_MauSac> getAllMauSac() {
        ArrayList<Model.Model_MauSac> listHang = new ArrayList<>();
        sql = "SELECT ID, TenMauSac FROM MauSac;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHang;
                String tenHang;
                maHang = rs.getInt(1);
                tenHang = rs.getString(2);
                listHang.add(new Model_MauSac(maHang, tenHang));
            }
            return listHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
   public ArrayList<Model.Model_KichThuoc> getAllKichThuoc() {
        ArrayList<Model.Model_KichThuoc> listHang = new ArrayList<>();
        sql = "SELECT ID, KichThuoc FROM KichThuoc;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHang;
                String tenHang;
                maHang = rs.getInt(1);
                tenHang = rs.getString(2);
                listHang.add(new Model_KichThuoc(maHang, tenHang));
            }
            return listHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
}
    

