/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelKhuyenMai {

    private int id;
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private boolean loaiKhuyenMai;
    private float giaTriGiam;
    private float phanTramGiam;
    private float dieuKienSoTien;
    private int dieuKienHoaDon;
    private String ngayBatDau;
    private String ngayKetThuc;
    private Boolean trangThai;
    private String moTa;

    public ModelKhuyenMai() {
    }

    public ModelKhuyenMai(int id, String maKhuyenMai, String tenKhuyenMai, boolean loaiKhuyenMai, float giaTriGiam, float phanTramGiam, float dieuKienSoTien, int dieuKienHoaDon, String ngayBatDau, String ngayKetThuc, Boolean trangThai, String moTa) {
        this.id = id;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.giaTriGiam = giaTriGiam;
        this.phanTramGiam = phanTramGiam;
        this.dieuKienSoTien = dieuKienSoTien;
        this.dieuKienHoaDon = dieuKienHoaDon;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public ModelKhuyenMai(String maKhuyenMai, String tenKhuyenMai, boolean loaiKhuyenMai, float giaTriGiam, float phanTramGiam, float dieuKienSoTien, int dieuKienHoaDon, String ngayBatDau, String ngayKetThuc, Boolean trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.giaTriGiam = giaTriGiam;
        this.phanTramGiam = phanTramGiam;
        this.dieuKienSoTien = dieuKienSoTien;
        this.dieuKienHoaDon = dieuKienHoaDon;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public ModelKhuyenMai(String maKhuyenMai, String tenKhuyenMai, boolean loaiKhuyenMai, float giaTriGiam, float phanTramGiam, float dieuKienSoTien, int dieuKienHoaDon, String ngayBatDau, String ngayKetThuc, Boolean trangThai, String moTa) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.giaTriGiam = giaTriGiam;
        this.phanTramGiam = phanTramGiam;
        this.dieuKienSoTien = dieuKienSoTien;
        this.dieuKienHoaDon = dieuKienHoaDon;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }
    
    

    

    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isLoaiKhuyenMai() {
        return loaiKhuyenMai;
    }

    public void setLoaiKhuyenMai(boolean loaiKhuyenMai) {
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    public float getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(float giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public float getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public float getDieuKienSoTien() {
        return dieuKienSoTien;
    }

    public void setDieuKienSoTien(float dieuKienSoTien) {
        this.dieuKienSoTien = dieuKienSoTien;
    }

    public int getDieuKienHoaDon() {
        return dieuKienHoaDon;
    }

    public void setDieuKienHoaDon(int dieuKienHoaDon) {
        this.dieuKienHoaDon = dieuKienHoaDon;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public String getTenTrangThai(){
        if(trangThai == true ){
            return "Bình thường";
        }else if(trangThai == false){
            return "Buộc dừng";
        }else{
            return "Không hoạt động";
        }
       
    }

    public float getGiaTriKhuyenMaiHienThi() {
        if (loaiKhuyenMai) {
            return phanTramGiam;
        } else {
            return giaTriGiam;
        }
    }
    
    public String getLoaiKhuyenMai(){
        if(loaiKhuyenMai == true){
            return "Khuyến mại theo %";
        }else{
            return "Giảm trực tiếp";
        }
    }

    public Object[] toDataRow() {
        return new Object[]{
        this.getId(),this.getMaKhuyenMai(), this.getTenKhuyenMai(), this.getLoaiKhuyenMai(), this.getGiaTriKhuyenMaiHienThi(), 
            this.getDieuKienSoTien(), this.getDieuKienHoaDon(), this.getNgayBatDau(), this.getNgayKetThuc(), this.getTenTrangThai()
                , this.getMoTa()
        };
    }
}
