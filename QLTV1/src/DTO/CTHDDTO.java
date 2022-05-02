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
	private String MaSanpham;
	private String TenSanpham;
	private int sTT;

	private String ThanhTien;
	private String GiaBan;
	private String Soluong;

	public int getSTT() {
		return sTT;
	}

	public void setSTT(int s) {
		this.sTT = s;
	}

	public String getTenSanpham() {
		return TenSanpham;
	}

	public void setTenSanpham(String TenSanpham) {
		this.TenSanpham = TenSanpham;
	}

	public int getMaHD() {
		return MaHD;
	}

	public void setMaHD(int MaHD) {
		this.MaHD = MaHD;
	}

	public String getMaSanpham() {
		return MaSanpham;
	}

	public void setMaSanpham(String MaSanpham) {
		this.MaSanpham = MaSanpham;
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

	public CTHDDTO(String MaHD, String MaSanpham, String ThanhTien, String GiaBan, String Soluong) {
		this.GiaBan = GiaBan;
		this.MaHD = 0;
		this.MaSanpham = MaSanpham;
		this.Soluong = Soluong;
		this.ThanhTien = ThanhTien;
	}

}
