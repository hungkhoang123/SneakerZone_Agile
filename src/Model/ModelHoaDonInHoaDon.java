/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelHoaDonInHoaDon {

    private String maHoaDon;
    private String ngayMuaHang;
    private double tongTien;
    private double giaGiam;
    private double giaSauGiam;
    private Boolean trangThai;

    // Thông tin khách hàng
    private String tenKhachHang;
    private String sdtKhachHang;
    private String emailKhachHang;

    // Thông tin nhân viên
    private String maNhanVien;
    private String tenNhanVien;

    // Thông tin khuyến mãi (nếu có)
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private double soTienGiam;
    private double phanTramGiam;

    public ModelHoaDonInHoaDon() {
    }

    public ModelHoaDonInHoaDon(String maHoaDon, String ngayMuaHang, double tongTien, double giaGiam, double giaSauGiam, Boolean trangThai, String tenKhachHang, String sdtKhachHang, String maNhanVien, String tenNhanVien, String maKhuyenMai, String tenKhuyenMai) {
        this.maHoaDon = maHoaDon;
        this.ngayMuaHang = ngayMuaHang;
        this.tongTien = tongTien;
        this.giaGiam = giaGiam;
        this.giaSauGiam = giaSauGiam;
        this.trangThai = trangThai;
        this.tenKhachHang = tenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(String ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public double getGiaSauGiam() {
        return giaSauGiam;
    }

    public void setGiaSauGiam(double giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getEmailKhachHang() {
        return emailKhachHang;
    }

    public void setEmailKhachHang(String emailKhachHang) {
        this.emailKhachHang = emailKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public double getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(double soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    

}
