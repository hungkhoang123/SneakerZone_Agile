/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelNhanVien {

    private String maNhanVien;
    private String tenNhanVien;
    private String tenDangNhap;
    private String matKhau;
    private String diaChi;
    private String soDienThoai;
    private String eMail;
    private Boolean vaiTro;
    private Boolean trangThai;
    private boolean gioiTinh;

    public ModelNhanVien() {
    }

    public ModelNhanVien(String maNhanVien, String tenNhanVien, String tenDangNhap, String matKhau, String diaChi, String soDienThoai, String eMail, Boolean vaiTro, Boolean trangThai, boolean gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.eMail = eMail;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
    }

    public ModelNhanVien(String maNhanVien, String tenNhanVien, String tenDangNhap, String diaChi, String soDienThoai, String eMail, Boolean vaiTro, Boolean trangThai, boolean gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.eMail = eMail;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
    }

    public ModelNhanVien(String maNhanVien, String tenNhanVien, String tenDangNhap, String matKhau, String diaChi, String soDienThoai, String eMail, Boolean vaiTro, boolean gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.eMail = eMail;
        this.vaiTro = vaiTro;
        this.gioiTinh = gioiTinh;
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

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(Boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    
    public String toStringVaiTro() {
        if (this.getVaiTro() == true) {
            return "Quản Lý";
        } else {
            return "Nhân Viên";
        }
    }

    public String toStringTrangThai() {
        if (this.getTrangThai() == true) {
            return "Đang làm việc";
        } else {
            return "Đã nghỉ";
        }
    }

    public String toStringGioiTinh() {
        return isGioiTinh() ? "Nữ" : "Nam";
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaNhanVien(),
            this.getTenNhanVien(),
            this.getTenDangNhap(),
            this.getMatKhau(),
            this.getDiaChi(),
            this.getSoDienThoai(),
            this.geteMail(),
            this.toStringVaiTro(),
            this.toStringTrangThai(),
            this.toStringGioiTinh()
        };
    }
}
