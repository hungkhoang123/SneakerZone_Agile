/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import Model.*;
import View.ViewQuanLiSanPham;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 * @author VIETTECH88
 */
public class SanPhamChiTiet {

    private View.ViewQuanLiSanPham view;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    private TreeSet<String> tenSanPhamSet = new TreeSet<>();
    private TreeSet<String> mauSacSet = new TreeSet<>();
    private TreeSet<String> congNgheSet = new TreeSet<>();
    private TreeSet<String> chatLieuSet = new TreeSet<>();
    private TreeSet<String> kichThuocSet = new TreeSet<>();
    private TreeSet<String> hangSet = new TreeSet<>();

    public SanPhamChiTiet() {
        con = DBConnect.getConnection();
    }

    public SanPhamChiTiet(ViewQuanLiSanPham view) {
        this.view = view;
        con = DBConnect.getConnection();
    }

    public ArrayList<ModelSanPhamChiTiet> getAllSPCT() {
        ArrayList<ModelSanPhamChiTiet> listSp = new ArrayList<>();
        sql = """
              SELECT 
              	   ROW_NUMBER() OVER (ORDER BY MaSanPhamChiTiet) AS [STT]
              	 , SPCT.MaSanPhamChiTiet
              	 , SP.TenSanPham
              	 , MS.TenMauSac
              	 , KT.KichThuoc
              	 , CL.TenChatLieu
              	 , CN.TenCongNghe
              	 , SPCT.SoLuong
              	 , SPCT.DonGia
              	 , SPCT.TrangThai
              FROM SanPhamChiTiet SPCT
              JOIN ChatLieu CL ON SPCT.IDChatLieu = CL.ID
              JOIN CongNghe CN ON SPCT.IDCongNghe = CN.ID
              JOIN MauSac MS ON SPCT.IDMauSac = MS.ID
              JOIN KichThuoc KT ON SPCT.IDKichThuoc = KT.ID
              JOIN SanPham SP ON SPCT.IDSanPham = SP.ID
              WHERE SPCT.TrangThai = 1 AND SP.TrangThai = 1 AND MS.TrangThai = 1 AND KT.TrangThai = 1
              		AND CL.TrangThai = 1 AND CN.TrangThai = 1.
              """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID, soLuong;
                String maSPCT, tenSP, mauSac, kichThuoc, chatLieu, congNghe;
                double donGia;
                boolean trangThai;
                ID = rs.getInt(1);
                maSPCT = rs.getString(2);
                tenSP = rs.getString(3);
                mauSac = rs.getString(4);
                kichThuoc = rs.getString(5);
                chatLieu = rs.getString(6);
                congNghe = rs.getString(7);
                soLuong = rs.getInt(8);
                donGia = rs.getDouble(9);
                trangThai = rs.getBoolean(10);
                listSp.add(new ModelSanPhamChiTiet(ID, maSPCT, tenSP, mauSac, kichThuoc, chatLieu, congNghe, soLuong, donGia, trangThai));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//thêm sản phẩm chi tiết
    public int themSanPhamChiTiet(ModelSanPhamChiTiet t) {
        sql = """
          INSERT INTO SanPhamChiTiet (MaSanPhamChiTiet, IDSanPham, IDMauSac, IDKichThuoc, IDChatLieu, IDCongNghe, SoLuong, DonGia, TrangThai)
          SELECT 
              ?, SP.ID, MS.ID, KT.ID, CL.ID, CN.ID, ?, ?, ?
          FROM SanPham SP
          JOIN MauSac MS ON MS.TenMauSac = ?
          JOIN KichThuoc KT ON KT.KichThuoc = ?
          JOIN ChatLieu CL ON CL.TenChatLieu = ?
          JOIN CongNghe CN ON CN.TenCongNghe = ?
          WHERE SP.TenSanPham = ?
          """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, t.getMaSPCT());
            ps.setObject(2, t.getSoLuong());
            ps.setObject(3, t.getDonGia());
            ps.setObject(4, t.isTrangThai());
            ps.setObject(5, t.getMauSac());
            ps.setObject(6, t.getKichThuoc());
            ps.setObject(7, t.getChatLieu());
            ps.setObject(8, t.getCongNghe());
            ps.setObject(9, t.getTenSP());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int suaSanPhamChiTiet(ModelSanPhamChiTiet t, String maSPCT){
        sql = """
              UPDATE SanPhamChiTiet
                SET
                    IDSanPham = (SELECT ID FROM SanPham WHERE TenSanPham = ?),
                    IDMauSac = (SELECT ID FROM MauSac WHERE TenMauSac = ?),
                    IDKichThuoc = (SELECT ID FROM KichThuoc WHERE KichThuoc = ?),
                    IDChatLieu = (SELECT ID FROM ChatLieu WHERE TenChatLieu = ?),
                    IDCongNghe = (SELECT ID FROM CongNghe WHERE TenCongNghe = ?),
                    SoLuong = ?,
                    DonGia = ?,
                    TrangThai = ?
                WHERE MaSanPhamChiTiet = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, t.getTenSP());
            ps.setObject(2, t.getMauSac());
            ps.setObject(3, t.getKichThuoc());
            ps.setObject(4, t.getChatLieu());
            ps.setObject(5, t.getCongNghe());
            ps.setObject(6, t.getSoLuong());
            ps.setObject(7, t.getDonGia());
            ps.setObject(8, t.isTrangThai());
            ps.setObject(9, maSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int xoaSanPhamChiTiet(ModelSanPhamChiTiet t, String maSPCT){
        sql = """
              UPDATE SanPhamChiTiet
                SET
                    TrangThai = 0
                WHERE MaSanPhamChiTiet = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//Lấy dữ liệu từ csdl vào treeset
    public void dataCombobox() {
        tenSanPhamSet.clear();
        chatLieuSet.clear();
        congNgheSet.clear();
        mauSacSet.clear();
        kichThuocSet.clear();
        hangSet.clear();
        sql = """
              SELECT 
              	  SP.TenSanPham
              	, CL.TenChatLieu 
              	, CN.TenCongNghe
              	, MS.TenMauSac
              	, KT.KichThuoc
                , H.TenHang
              FROM SanPhamChiTiet SPCT
              FULL OUTER JOIN ChatLieu CL ON SPCT.IDChatLieu = CL.ID
              FULL OUTER JOIN CongNghe CN ON SPCT.IDCongNghe = CN.ID
              FULL OUTER JOIN MauSac MS ON SPCT.IDMauSac = MS.ID
              FULL OUTER JOIN KichThuoc KT ON SPCT.IDKichThuoc = KT.ID
              FULL OUTER JOIN SanPham SP ON SPCT.IDSanPham = SP.ID
              FULL OUTER JOIN Hang H ON SPCT.IDSanPham = H.ID
              WHERE SPCT.TrangThai = 1 OR CL.TrangThai = 1 OR CN.TrangThai = 1 OR 
                    MS.TrangThai = 1 OR KT.TrangThai = 1 OR SP.TrangThai = 1 OR 
                    H.TrangThai = 1
              """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham = rs.getString(1);
                String chatLieu = rs.getString(2);
                String congNghe = rs.getString(3);
                String mauSac = rs.getString(4);
                String kichThuoc = rs.getString(5);
                String hang = rs.getString(6);
                if (TenSanPham != null) {
                    tenSanPhamSet.add(TenSanPham);
                }
                if (mauSac != null) {
                    mauSacSet.add(mauSac);
                }
                if (congNghe != null) {
                    congNgheSet.add(congNghe);
                }
                if (chatLieu != null) {
                    chatLieuSet.add(chatLieu);
                }
                if (kichThuoc != null) {
                    kichThuocSet.add(kichThuoc);
                }
                if (hang != null) {
                    hangSet.add(hang);
                }
            }
        } catch (Exception e) {
        }
    }

    public TreeSet<String> getTenSanPhamSet() {
        return tenSanPhamSet;
    }

    public TreeSet<String> getMauSacSet() {
        return mauSacSet;
    }

    public TreeSet<String> getCongNgheSet() {
        return congNgheSet;
    }

    public TreeSet<String> getChatLieuSet() {
        return chatLieuSet;
    }

    public TreeSet<String> getKichThuocSet() {
        return kichThuocSet;
    }

    public TreeSet<String> getHangSet() {
        return hangSet;
    }
}
