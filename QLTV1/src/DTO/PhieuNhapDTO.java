/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class PhieuNhapDTO {
    private int MaPhieuNhap;
    private String MaNCC;
    private String MaNV;
    private LocalDate NgayNhap;
    private String Tongtien;
    public PhieuNhapDTO(){
        
    }
    public PhieuNhapDTO(int MaPhieuNhap,String MaNCC,String MaNV,LocalDate NgayNhap,String Tongtien){
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.NgayNhap = NgayNhap;
        this.Tongtien = Tongtien;
    }
    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(int MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    } 
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public LocalDate getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(LocalDate NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getTongtien() {
        return Tongtien;
    }

    public void setTongtien(String Tongtien) {
        this.Tongtien = Tongtien;
    }    
}