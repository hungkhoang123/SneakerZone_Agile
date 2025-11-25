
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hungb
 */
public class Model_MauSac {
    int ID;
    String tenMau;

    public Model_MauSac() {
    }

    public Model_MauSac(String tenMau) {
        this.tenMau = tenMau;
    }

    public Model_MauSac(int ID, String tenMau) {
        this.ID = ID;
        this.tenMau = tenMau;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getID(), this.getTenMau()
        };
    }
}
