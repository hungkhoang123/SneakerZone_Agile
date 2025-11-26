/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelHoaDonChiTiet {

    private int id;
    private int maHoaDonChiTiet;
    private String maSp;
    private String tenSanPham;
    private String maHoaDon;
    private int soLuong;
    private int donGia;
    private int tongTien;

    public ModelHoaDonChiTiet(int id, String maSp, String tenSanPham, int soLuong, int donGia, int tongTien) {
        this.id = id;
        this.maSp = maSp;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public ModelHoaDonChiTiet(int maHoaDonChiTiet, String tenSanPham, String maHoaDon, int soLuong, int donGia) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ModelHoaDonChiTiet() {
    }

    public int getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(int maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaHoaDonChiTiet(), this.getTenSanPham(), this.getMaHoaDon(), this.getSoLuong(), this.getDonGia()
        };
    }
    
    public Object[] toDataRow1() {
        return new Object[]{
            this.getId(), this.getMaSp(), this.getTenSanPham(), this.getDonGia() , this.getSoLuong(), this.getTongTien()
        };
    }
}
