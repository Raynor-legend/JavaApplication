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
public class NCCDTO {

    private String MaNCC, TenNCC, Diachi, Sdt, Mail, TrangThai;
    // private String Sdt;
   public NCCDTO() {

    }

    public NCCDTO(String MaNCC, String TenNCC, String Diachi, String Sdt, String Mail, String TrangThai) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.Diachi = Diachi;
        this.Sdt = Sdt;
        this.Mail = Mail;
        this.TrangThai = TrangThai;

    }
    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}
