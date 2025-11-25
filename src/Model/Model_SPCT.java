/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hungb
 */
public class Model_SPCT {

    private int ID;
    private String tenSP, mauSac, kichThuoc, chatLieu, congNghe;
    private int soLuong;
    private double donGia;
    private boolean trangThai;

    public Model_SPCT() {
    }

    public Model_SPCT(int ID, double donGia) {
        this.ID = ID;
        this.donGia = donGia;
    }
    
    public Model_SPCT(int ID, String tenSP, String mauSac, String kichThuoc, String chatLieu, String congNghe, int soLuong, double donGia) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.congNghe = congNghe;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Model_SPCT(String tenSP, String mauSac, String kichThuoc, String chatLieu, String congNghe, int soLuong, double donGia) {
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.congNghe = congNghe;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public Model_SPCT(int ID, String tenSP, String mauSac, String kichThuoc, String chatLieu, String congNghe, int soLuong, double donGia, boolean trangThai) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.congNghe = congNghe;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getCongNghe() {
        return congNghe;
    }

    public void setCongNghe(String congNghe) {
        this.congNghe = congNghe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getID(), this.getTenSP(), this.getMauSac(), this.getKichThuoc(), this.getChatLieu(),
            this.congNghe, this.getSoLuong(), this.getDonGia(), this.isTrangThai()?"Còn hàng":"Hết hàng"};
    }
}
