/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelHoaDon {
    private int id;
    private String maHoaDon;
    private String tenKhachHang;
    private String maNhanVien;
    private String maKhuyenMai;
    private String ngayMuaHang;
    private String SDT;
    private Integer tongTien;
    private Integer giaTriGiam;
    private Integer giaSauGiam;
    private Boolean trangThai;
    private String ngayThanhToan; //dùng để chứa ngày tìm kiếm 
    private Integer giaTriA;
    public ModelHoaDon() {
    }

    public ModelHoaDon(String maHoaDon, String tenKhachHang, String ngayMuaHang, String SDT, int tongTien,int giaTriGiam, int giaSauGiam) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayMuaHang = ngayMuaHang;
        this.SDT = SDT;
        this.tongTien = tongTien;
        this.giaTriGiam = giaTriGiam;
        this.giaSauGiam = giaSauGiam;
    }

    public ModelHoaDon(String maHoaDon, String tenKhachHang, String maKhuyenMai, String ngayMuaHang, Integer tongTien, Integer giaSauGiam, Boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayMuaHang = ngayMuaHang;
        this.tongTien = tongTien;
        this.giaSauGiam = giaSauGiam;
        this.trangThai = trangThai;
    }

    
    public ModelHoaDon(int id, String maHoaDon, String tenKhachHang, String ngayMuaHang, String SDT, Integer tongTien, Integer giaTriGiam, Integer giaSauGiam, Boolean trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayMuaHang = ngayMuaHang;
        this.SDT = SDT;
        this.tongTien = tongTien;
        this.giaTriGiam = giaTriGiam;
        this.giaSauGiam = giaSauGiam;
        this.trangThai = trangThai;
    }

    
    public ModelHoaDon(String maHoaDon, String tenKhachHang, String maNhanVien, String maKhuyenMai, String ngayMuaHang, Integer tongTien, Integer giaSauGiam, Boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maNhanVien = maNhanVien;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayMuaHang = ngayMuaHang;
        this.tongTien = tongTien;
        this.giaSauGiam = giaSauGiam;
        this.trangThai = trangThai;
    }

    //tạo một constructor mới để chứa dữ liệu từ form tìm kiếm hóa đơn
    public ModelHoaDon(String ngayMuaHang, Integer tongTien, Boolean trangThai, String ngayThanhToan, Integer giaTriA) {
        this.ngayMuaHang = ngayMuaHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ngayThanhToan = ngayThanhToan;
        this.giaTriA = giaTriA;
    }
    
    
    
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public Integer getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(Integer giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public int getId() {
        return id;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(String ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getGiaSauGiam() {
        return giaSauGiam;
    }

    public void setGiaSauGiam(Integer giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }

    public Boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Integer getGiaTriA() {
        return giaTriA;
    }

    public void setGiaTriA(Integer giaTriA) {
        this.giaTriA = giaTriA;
    }

    @Override
    public String toString() {
       if(trangThai == false){
           return "Chưa thanh toán";
       }else {
           return "Đã thanh toán";
       }
    }
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getMaHoaDon(), this.getTenKhachHang(), this.getMaKhuyenMai()
                ,this.getNgayMuaHang(), this.getTongTien(), this.getGiaSauGiam(), toString()
        };
    }
    public Object[] toDataRow1(){
        return new Object[]{
            this.getId(), this.getMaHoaDon(), this.getTenKhachHang(), this.getNgayMuaHang(), this.getSDT(), this.getTongTien(), this.getGiaTriGiam(), this.getGiaSauGiam(), toString()
        };
    }
}
