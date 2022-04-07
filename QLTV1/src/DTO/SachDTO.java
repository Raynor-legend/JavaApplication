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
public class SachDTO {
    private String MaSach;
    private String TenSach;
    private String MaTL;
    private String MaTG;
    private String MaNXB;
    private String SoLuong;
    private String DonGia;
    public SachDTO(){
        
    }
    public SachDTO(String MaSach, String TenSach, String MaTL, String MaTG, String MaNXB, String SoLuong, String DonGia) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.MaTL = MaTL;
        this.MaTG = MaTG;
        this.MaNXB = MaNXB;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
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

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String MaTL) {
        this.MaTL = MaTL;
    }

    public String getMaTG() {
        return MaTG;
    }

    public void setMaTG(String MaTG) {
        this.MaTG = MaTG;
    }

    public String getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(String MaNXB) {
        this.MaNXB = MaNXB;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }
    
    }