/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.ModelThongKe;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HIEU
 */
public class ThongKe {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ThongKe() {
    
    con =DBConnect.getConnection();
    }
    public ArrayList<ModelThongKe>danhSachThongKe(){
        ArrayList<ModelThongKe>lise=new ArrayList<>();
        try{
sql="select  IdHoaDon,MaHoaDon,NgayMuaHang,GiaSauGiam\n" +
"from HoaDonBackup;";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                int idHoaDon=rs.getInt(1);
                String maHoaDon=rs.getString(2);
                String ngayTaoHoaDon=rs.getString(3);
                int tongTienHoaDon=rs.getInt(4);
                lise.add(new ModelThongKe(idHoaDon, maHoaDon, ngayTaoHoaDon, tongTienHoaDon));
               
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return lise;
    }
 public String tongdoanhthu() {
    int tong = 0;
    try {
        String sql = "SELECT SUM(GiaSauGiam) AS tong_doanh_thu FROM HoaDonBackup";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            tong = rs.getInt(1);
        }

        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
        return "0";
    }
    return String.valueOf(tong);
}
public int demSoHoaDon() {
    int count = 0;
    try {
        String sql = "SELECT COUNT(*) FROM HoaDonChiTietBackup;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}
public int demSoKhachHang() {
    int count = 0;
    try {
        String sql = "SELECT COUNT(*) FROM KhachHang;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}


   
}

