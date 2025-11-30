/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelKhachHang {

    private String maKhachHang;
    private String ten;
    private String diaChi;
    private String sdt;
    private String email;
    private Boolean trangThai;
    private Boolean gioiTinh;

    public ModelKhachHang() {
    }

    public ModelKhachHang(String maKhachHang, String ten, String diaChi, String sdt) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public ModelKhachHang(String maKhachHang, String ten, String diaChi, String sdt, String email, Boolean trangThai, Boolean gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.trangThai = trangThai;
        this.gioiTinh = gioiTinh;
    }

    public ModelKhachHang(String maKhachHang, String ten, String diaChi, String sdt, String email, Boolean gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
    }

    

    

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public String toGioiTinh(){
        if(gioiTinh == true){
            return "Nữ";
        }else if(gioiTinh == false){
            return "Nam";
        }else{
            return "Không xác định";
        }
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaKhachHang(), this.getTen(), this.getDiaChi(), this.getSdt(), this.getEmail(), this.toGioiTinh()
        };
    }
}
