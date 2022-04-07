/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HP
 */
public class CTHDDTO {

    private int MaHD;
    private String MaSach;
    private String TenSach;
    private int sTT;
   
    private String ThanhTien;
    private String GiaBan;
    private String Soluong;
 public int getSTT(){
        return sTT;
    }
    public void setSTT(int s){
        this.sTT=s;
    }
    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }
    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String GiaBan) {
        this.GiaBan = GiaBan;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String Soluong) {
        this.Soluong = Soluong;
    }

    public CTHDDTO() {

    }

    public CTHDDTO(String MaHD, String MaSach, String ThanhTien, String GiaBan, String Soluong) {
        this.GiaBan = GiaBan;
        this.MaHD = 0;
        this.MaSach = MaSach;
        this.Soluong = Soluong;
        this.ThanhTien = ThanhTien;
    }

}
