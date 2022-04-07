/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author HP
 */
public class HienHanh {

    public static String getNhanVien() {
        return NhanVien;
    }

    public static void setNhanVien(String NhanVien) {
        HienHanh.NhanVien = NhanVien;
    }

    public static String getKhachHang() {
        return KhachHang;
    }

    public static void setKhachHang(String KhachHang) {
        HienHanh.KhachHang = KhachHang;
    }

    public static int getMaQuyen() {
        return MaQuyen;
    }

    public static void setMaQuyen(int MaQuyen) {
        HienHanh.MaQuyen = MaQuyen;
    }

    public static String getMaHD() {
        return MaHD;
    }

    public static void setMaHD(String MaHD) {
        HienHanh.MaHD = MaHD;
    }
    public static String NhanVien;
    public static String KhachHang;
    public static int MaQuyen;
    public static String MaHD;
    public static String NCC;

    public static String getNCC() {
        return NCC;
    }

    public static void setNCC(String NCC) {
        HienHanh.NCC = NCC;
    }
    public static String TongTien;

    public static String getTongTien() {
        return TongTien;
    }

    public static void setTongTien(String TongTien) {
        HienHanh.TongTien = TongTien;
    }
}
