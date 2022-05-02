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
public class HoaDonDTO {

	public int getMaHD() {
		return MaHD;
	}

	public void setMaHD(int MaHD) {
		this.MaHD = MaHD;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String MaNV) {
		this.MaNV = MaNV;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String MaKH) {
		this.MaKH = MaKH;
	}

	public String getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(String ThanhTien) {
		this.ThanhTien = ThanhTien;
	}

	public LocalDate getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(LocalDate NgayLap) {
		this.NgayLap = NgayLap;
	}

	private int MaHD;
	private String MaNV;
	private String MaKH;
	private String ThanhTien;
	private LocalDate NgayLap;

	public HoaDonDTO() {

	}

	public HoaDonDTO(int MaHD, String MaNV, String MaKH, String ThanhTien, LocalDate NgayLap) {
		this.MaHD = 0;
		this.MaKH = MaKH;
		this.MaNV = MaNV;
		this.NgayLap = NgayLap;
		this.ThanhTien = ThanhTien;
	}
}
