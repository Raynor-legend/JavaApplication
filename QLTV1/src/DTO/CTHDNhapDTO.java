/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Kieu Oanh
 */
public class CTHDNhapDTO {

    private int STT;
    private int MaPhieuNhap;
    private String MaSach;
    private String TenSach;
    private String DonGia;
    private String SoLuong;
    private String ThanhTien;

    public CTHDNhapDTO(int STT, int MaPhieuNhap, String MaSach, String TenSach, String DonGia, String SoLuong, String ThanhTien) {
        this.STT = STT;
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;
    }

    public CTHDNhapDTO(CTHDNhapDTO ct) {
        this.STT = STT;
        this.MaPhieuNhap = ct.MaPhieuNhap;
        this.MaSach = ct.MaSach;
        this.TenSach = ct.TenSach;
        this.DonGia = ct.DonGia;
        this.SoLuong = ct.SoLuong;
        this.ThanhTien = ct.ThanhTien;
    }

    public CTHDNhapDTO() {
        this.STT = 0;
        this.MaPhieuNhap = 0;
        this.MaSach = "";
        this.TenSach = "";
        this.DonGia = "";
        this.SoLuong = "";
        this.ThanhTien = "";
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(int MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

}
