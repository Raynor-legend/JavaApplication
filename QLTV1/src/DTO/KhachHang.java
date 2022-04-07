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
public class KhachHang {

    private String MaKH,HoKH,TenKH,Ngaysinh,Gioitinh,Diachi,Sdt,Mail;
    public KhachHang() {
    }

    public KhachHang(String MaKH, String HoKH, String TenKH, String Ngaysinh, String Gioitinh, String Diachi,String Sdt,String Mail) {
        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.Ngaysinh = Ngaysinh;       
        this.Gioitinh = Gioitinh;
        this.Diachi = Diachi;
        this.Sdt = Sdt;
        this.Mail = Mail;
    }
    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoKH() {
        return HoKH;
    }

    public void setHoKH(String HoKH) {
        this.HoKH = HoKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
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

}

   