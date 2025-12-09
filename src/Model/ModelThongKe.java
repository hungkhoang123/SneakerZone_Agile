/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HIEU
 */
public class ModelThongKe {
    private int idHoaDon;
private String maHoaDon;
private String ngayTaoDon;
private int tongTienHoaDon;

    public ModelThongKe() {
    }

    public ModelThongKe(int idHoaDon, String maHoaDon, String ngayTaoDon, int tongTienHoaDon) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTaoDon = ngayTaoDon;
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public ModelThongKe(int tongTienHoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayTaoDon() {
        return ngayTaoDon;
    }

    public void setNgayTaoDon(String ngayTaoDon) {
        this.ngayTaoDon = ngayTaoDon;
    }

    public int getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(int tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

   


     public Object[] toDataRow(){
         return new Object[]{
            this.getIdHoaDon(),this.getMaHoaDon(),this.getNgayTaoDon(),this.getTongTienHoaDon()
         };
     }

}
