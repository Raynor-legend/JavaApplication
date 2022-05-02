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
public class SanphamDTO {
	private String MaSanpham;
	private String TenSanpham;
	private String MaTL;
	private String MaTG;
	private String MaNXB;
	private String SoLuong;
	private String DonGia;

	public SanphamDTO() {

	}

	public SanphamDTO(String MaSanpham, String TenSanpham, String MaTL, String MaTG, String MaNXB, String SoLuong,
			String DonGia) {
		this.MaSanpham = MaSanpham;
		this.TenSanpham = TenSanpham;
		this.MaTL = MaTL;
		this.MaTG = MaTG;
		this.MaNXB = MaNXB;
		this.SoLuong = SoLuong;
		this.DonGia = DonGia;
	}

	public String getMaSanpham() {
		return MaSanpham;
	}

	public void setMaSanpham(String MaSanpham) {
		this.MaSanpham = MaSanpham;
	}

	public String getTenSanpham() {
		return TenSanpham;
	}

	public void setTenSanpham(String TenSanpham) {
		this.TenSanpham = TenSanpham;
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