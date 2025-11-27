/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelKhuyenMai;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author VIETTECH88
 */
public class KhuyenMai {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public KhuyenMai() {
        con = DBConnect.getConnection();
    }

    public ArrayList<ModelKhuyenMai> getall(int i) {
        ArrayList<ModelKhuyenMai> list = new ArrayList<>();
        sql = """
              SELECT 
                  ROW_NUMBER() OVER (ORDER BY MaKhuyenMai) AS [STT]
              	, MaKhuyenMai
              	, TenKhuyenMai
              	, LoaiKhuyenMai
              	, GiaTriGiam
              	, PhanTramGiam
              	, DieuKienSoTien
              	, DieuKienHoaDon
              	, NgayBatDau
              	, NgayKetThuc
                , TrangThai
                , Mota
              FROM KhuyenMai
              """;
        StringBuilder s = new StringBuilder(sql);
        switch (i) {
            case 0 ->
                s.append(" WHERE GETDATE() > NgayBatDau AND GETDATE() < NgayKetThuc AND TrangThai = 1");
            case 1 ->
                s.append(" WHERE GETDATE() < NgayBatDau AND TrangThai = 1");
            default ->
                s.append(" WHERE TrangThai = 0");
        }
        try {
            ps = con.prepareStatement(s.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String maKhuyenMai = rs.getString(2);
                String tenKhuyenMai = rs.getString(3);
                boolean loaiKhuyenMai = rs.getBoolean(4);
                float giaTriGiam = rs.getFloat(5);
                float phanTramGiam = rs.getFloat(6);
                float DieuKienSoTien = rs.getFloat(7);
                int DieuKienHoaDon = rs.getInt(8);
                String NgayBatDau = rs.getString(9);
                String NgayKetThuc = rs.getString(10);
                Boolean TrangThai = rs.getBoolean(11);
                String MoTa = rs.getString(12);
                

                list.add(new ModelKhuyenMai(id, maKhuyenMai, tenKhuyenMai, loaiKhuyenMai, giaTriGiam, phanTramGiam, DieuKienSoTien, DieuKienHoaDon, NgayBatDau, NgayKetThuc, TrangThai, MoTa));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int themKhuyenMai(ModelKhuyenMai km) {
        sql = """
              INSERT INTO KhuyenMai
                         ( MaKhuyenMai
                         , TenKhuyenMai
                         , LoaiKhuyenMai
                         , GiaTriGiam
                         , PhanTramGiam
                         , DieuKienSoTien
                         , DieuKienHoaDon
                         , NgayBatDau
                         , NgayKetThuc
                         , TrangThai
                         , Mota)
                   VALUES
                         (?,?,?,?,?,?,?,?,?,?,?)
              """;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMaKhuyenMai());
            ps.setObject(2, km.getTenKhuyenMai());
            ps.setObject(3, km.isLoaiKhuyenMai());
            if (km.isLoaiKhuyenMai()) {
                ps.setObject(4, null);
                ps.setObject(5, km.getPhanTramGiam());
            } else {
                ps.setObject(4, km.getGiaTriGiam());
                ps.setObject(5, null);
            }
            ps.setObject(6, km.getDieuKienSoTien());
            ps.setObject(7, km.getDieuKienHoaDon());
            ps.setObject(8, km.getNgayBatDau());
            ps.setObject(9, km.getNgayKetThuc());
            ps.setObject(10, km.getTrangThai());
            ps.setObject(11, km.getMoTa());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //Sửa khuyến mại
    public int suaKhuyenMai(ModelKhuyenMai km, String s) {
        sql = """
              UPDATE [dbo].[KhuyenMai]
                            SET 
                                [MaKhuyenMai] = ?
                               ,[TenKhuyenMai] = ?
                               ,[LoaiKhuyenMai] = ?
                               ,[GiaTriGiam] = ?
                               ,[PhanTramGiam] = ?
                               ,[DieuKienSoTien] = ?
                               ,[DieuKienHoaDon] = ?
                               ,[NgayBatDau] = ?
                               ,[NgayKetThuc] = ?
                               ,[TrangThai] = ?
                               ,[Mota] = ?
                          WHERE MaKhuyenMai = ?
              """;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMaKhuyenMai());
            ps.setObject(2, km.getTenKhuyenMai());
            ps.setObject(3, km.isLoaiKhuyenMai());
            if (km.isLoaiKhuyenMai()) {
                ps.setObject(4, null);
                ps.setObject(5, km.getPhanTramGiam());
            } else {
                ps.setObject(4, km.getGiaTriGiam());
                ps.setObject(5, null);
            }
            ps.setObject(6, km.getDieuKienSoTien());
            ps.setObject(7, km.getDieuKienHoaDon());
            ps.setObject(8, km.getNgayBatDau());
            ps.setObject(9, km.getNgayKetThuc());
            ps.setObject(10, km.getTrangThai());
            ps.setObject(11, km.getMoTa());
            ps.setObject(12, s);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    //Xóa khuyến mại
    public int xoaKhuyenMai(String maKM, String moTa){
        sql ="""
             UPDATE [dbo].[KhuyenMai]
                        SET
                          [TrangThai] = 0
                         ,[Mota] = ?
                        WHERE MaKhuyenMai = ?
                        
             """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, moTa);
            ps.setObject(2, maKM);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public boolean isValidDate(String input, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isInt(String s) {
        try {
            int value = Integer.parseInt(s);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //kiểm tra mã có tồn tại không
    public boolean kiemTraMaKM(String maKM) {
        sql = "SELECT 1 FROM KhuyenMai WHERE MaKhuyenMai = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKM);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //kiểm ra mã mới sửa có trùng với mã đã có không
    public boolean maKhuyenMaiTonTaiKhiSua(String maMoi, String maGoc) {
        sql = "SELECT 1 FROM KhuyenMai WHERE MaKhuyenMai = ? AND MaKhuyenMai <> ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maMoi);
            ps.setString(2, maGoc);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
