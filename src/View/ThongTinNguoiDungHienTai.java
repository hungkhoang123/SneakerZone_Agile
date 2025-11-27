/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Model_DangNhap;

/**
 *
 * @author hungb
 */
public class ThongTinNguoiDungHienTai {
    private static Model.Model_DangNhap nguoiDungNow;

    public static Model_DangNhap getNguoiDungNow() {
        return nguoiDungNow;
    }

    public static void setNguoiDungNow(Model_DangNhap nguoiDungNow) {
        ThongTinNguoiDungHienTai.nguoiDungNow = nguoiDungNow;
    }
    
    public static void logout(){
        nguoiDungNow = null;
    }
}
