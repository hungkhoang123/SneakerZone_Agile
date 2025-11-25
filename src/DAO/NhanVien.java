/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import View.ViewNhanVien;
import Model.ModelNhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author VIETTECH88
 */
public class NhanVien {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = "";

    public NhanVien() {
        con = DBConnect.getConnection();
    }

    public ArrayList<ModelNhanVien> getall(int trangthai) {
        ArrayList<ModelNhanVien> list = new ArrayList<>();
        sql = """
              SELECT MaNhanVien
              		, TenNhanVien
              		, TenDangNhap
              		, MatKhau
              		, DiaChi
              		, SDT
                        , Email
              		, VaiTro
              		, TrangThai
                        , GioiTinh
              FROM NhanVien
              Where TrangThai = ?;
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("MaNhanVien");
                String tenNhanVien = rs.getString("TenNhanVien");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SDT");
                String eMail = rs.getString("Email");
                Boolean vaiTro = rs.getBoolean("VaiTro");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Boolean trangThai = rs.getBoolean("TrangThai");

                list.add(new ModelNhanVien(maNhanVien, tenNhanVien, tenDangNhap, matKhau, diaChi, soDienThoai, eMail, vaiTro, trangThai, gioiTinh));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelNhanVien> timKiem(String t) {
        ArrayList<ModelNhanVien> list = new ArrayList<>();
        sql = """
              SELECT 
                          MaNhanVien
              		, TenNhanVien
              		, TenDangNhap
              		, MatKhau
              		, DiaChi
              		, SDT
                        , Email
              		, VaiTro
              		, TrangThai
                        , GioiTinh
              FROM NhanVien
              WHERE MaNhanVien LIKE ? OR TenNhanVien LIKE ? OR SDT LIKE ? AND TrangThai = 1
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + t + "%");
            ps.setObject(2, "%" + t + "%");
            ps.setObject(3, "%" + t + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("MaNhanVien");
                String tenNhanVien = rs.getString("TenNhanVien");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SDT");
                String eMail = rs.getString("Email");
                Boolean vaiTro = rs.getBoolean("VaiTro");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Boolean trangThai = rs.getBoolean("TrangThai");

                list.add(new ModelNhanVien(maNhanVien, tenNhanVien, tenDangNhap, matKhau, diaChi, soDienThoai, eMail, vaiTro, trangThai, gioiTinh));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    
    
    public static String taoMatKhau(int length) {
        if (length < 1) {
            return "Độ dài mật khẩu phải lớn hơn 0";
        }

        Random random = new Random();
        StringBuilder matKhau = new StringBuilder();

        for (int i = 0; i < length; i++) {
            matKhau.append(random.nextInt(10)); // Tạo số ngẫu nhiên từ 0 đến 9
        }

        return matKhau.toString();
    }
    
    
    public int themNhanVien(ModelNhanVien nv){
        String sql = """
                     INSERT INTO NhanVien (
                         MaNhanVien, 
                         TenNhanVien, 
                         TenDangNhap, 
                         MatKhau, 
                         DiaChi, 
                         SDT, 
                         Email,
                         VaiTro,
                         GioiTinh
                     )
                     VALUES(?,?,?,?,?,?,?,?,?)
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNhanVien());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getTenDangNhap());
            ps.setObject(4, nv.getMatKhau());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getSoDienThoai());
            ps.setObject(7, nv.geteMail());
            ps.setObject(8, nv.getVaiTro());
            ps.setObject(9, nv.isGioiTinh());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int suaNhanVien(ModelNhanVien nv, String s){
        String sql = """
                     UPDATE NhanVien 
                     SET
                         MaNhanVien = ?,
                         TenNhanVien = ?,
                         TenDangNhap = ?,
                         MatKhau = ?, 
                         DiaChi = ?, 
                         SDT = ?, 
                         Email = ?,
                         VaiTro = ?,
                         GioiTinh = ?
                     WHERE MaNhanVien = ?
                     
                     
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNhanVien());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getTenDangNhap());
            ps.setObject(4, nv.getMatKhau());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getSoDienThoai());
            ps.setObject(7, nv.geteMail());
            ps.setObject(8, nv.getVaiTro());
            ps.setObject(9, nv.isGioiTinh());
            ps.setObject(10, s);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int xoaNhanVien(String s){
        String sql = """
                     UPDATE NhanVien 
                     SET
                         TrangThai = 0
                     WHERE MaNhanVien = ?
                     
                     
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, s);
            
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhatNhanVienDaXoa(String s){
        String sql = """
                     UPDATE NhanVien 
                     SET
                         TrangThai = 1
                     WHERE MaNhanVien = ?
                     
                     
                     """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, s);
            
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
