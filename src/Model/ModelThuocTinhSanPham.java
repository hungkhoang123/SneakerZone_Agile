/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author VIETTECH88
 */
public class ModelThuocTinhSanPham {
    private int idThuocTinh;
    private String maThuocTinh;
    private String tenThuocTinh;
    private String moTa;

    public ModelThuocTinhSanPham() {
    }

    public ModelThuocTinhSanPham(String maThuocTinh, String tenThuocTinh, String moTa) {
        this.maThuocTinh = maThuocTinh;
        this.tenThuocTinh = tenThuocTinh;
        this.moTa = moTa;
    }

    public ModelThuocTinhSanPham(String maThuocTinh, String tenThuocTinh) {
        this.maThuocTinh = maThuocTinh;
        this.tenThuocTinh = tenThuocTinh;
    }

    public int getIdThuocTinh() {
        return idThuocTinh;
    }

    public void setIdThuocTinh(int idThuocTinh) {
        this.idThuocTinh = idThuocTinh;
    }

    public String getMaThuocTinh() {
        return maThuocTinh;
    }

    public void setMaThuocTinh(String maThuocTinh) {
        this.maThuocTinh = maThuocTinh;
    }

    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    
    
    public Object[] toDataRow(){
        return new Object[]{
          this.getMaThuocTinh(), this.getTenThuocTinh(), this.getMoTa()
        };
    };
}
