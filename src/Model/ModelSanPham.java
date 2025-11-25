/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hungb
 */
public class ModelSanPham {
    private int id;
    private String hang, maSanPham, tenSp;

    public ModelSanPham() {
    }

    public ModelSanPham(int id, String hang, String maSanPham, String tenSp) {
        this.id = id;
        this.hang = hang;
        this.maSanPham = maSanPham;
        this.tenSp = tenSp;
    }

    public ModelSanPham(String hang, String maSanPham, String tenSp) {
        this.hang = hang;
        this.maSanPham = maSanPham;
        this.tenSp = tenSp;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    

    
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(), this.getHang(), this.getMaSanPham(), this.getTenSp()
        };
    }
}
