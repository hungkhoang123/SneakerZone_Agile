/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hungb
 */
public class Model_KichThuoc {
    private int ID;
    private String kichThuoc;

    public Model_KichThuoc() {
    }

    public Model_KichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Model_KichThuoc(int ID, String kichThuoc) {
        this.ID = ID;
        this.kichThuoc = kichThuoc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getID(), this.getKichThuoc()
        };
    }
}
