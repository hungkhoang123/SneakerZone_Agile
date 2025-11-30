/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelHoaDon;
import Model.ModelKhachHang;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author VIETTECH88
 */
public class KhachHang {

    private Connection con;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private String sql = null;

    public KhachHang() {
        con = DBConnect.getConnection();
    }

    public ArrayList<ModelKhachHang> getall() {
        ArrayList<ModelKhachHang> list = new ArrayList<>();
        sql = """
              SELECT 
              	      MaKhachHang
                    , Ten
                    , DiaChi
                    , SDT
                    , Email
                    , GioiTinh
              FROM KhachHang
              WHERE TrangThai = 1
              """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString(1);
                String tenKhachHang = rs.getString(2);
                String diaChi = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String email = rs.getString(5);
                Boolean gioiTinh = rs.getBoolean(6);
                list.add(new ModelKhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email,gioiTinh));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelHoaDon> danhSachHoaDon(String s) {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        try {
            sql = """
                  SELECT 
                  	  A.MaHoaDon
                  	, B.MaKhachHang
                  	, A.MaNhanVien
                  	, A.MaKhuyenMai
                  	, A.NgayMuaHang
                  	, A.TongTien
                  	, A.GiaSauGiam
                  	, A.TrangThai
                  FROM HoaDonBackup A
                  JOIN KhachHang B ON a.IDKhachHang = B.ID
                  WHERE B.MaKhachHang = ?
                  """;
            ps = con.prepareStatement(sql);
            ps.setObject(1, s);
            rs = ps.executeQuery();
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

    public int themKhachHang(ModelKhachHang kh) {
        sql = """
              INSERT INTO KhachHang
                (MaKhachHang, Ten, DiaChi, SDT, Email, GioiTinh)
              VALUES (?,?,?,?,?,?)
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMaKhachHang());
            ps.setObject(2, kh.getTen());
            ps.setObject(3, kh.getDiaChi());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.getGioiTinh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int capNhatKhachHang(ModelKhachHang kh, String maKhachHang) {
        sql = """
              UPDATE KhachHang
              SET
                Ten = ?
              , DiaChi = ?
              , SDT = ?
              , Email = ?
              , GioiTinh = ?
              WHERE MaKhachHang = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTen());
            ps.setObject(2, kh.getDiaChi());
            ps.setObject(3, kh.getSdt());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.getGioiTinh());
            ps.setObject(6, maKhachHang);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
