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
public class TacGiaDTO {
	private String MaTG;
	private String TenTG;
	private String TrangThai;

	public TacGiaDTO() {

	}

	public TacGiaDTO(String MaTG, String TenTG, String TrangThai) {
		this.MaTG = MaTG;
		this.TenTG = TenTG;
		this.TrangThai = TrangThai;
	}

	public String getMaTG() {
		return MaTG;
	}

	public void setMaTG(String MaTG) {
		this.MaTG = MaTG;
	}

	public String getTenTG() {
		return TenTG;
	}

	public void setTenTG(String TenTG) {
		this.TenTG = TenTG;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String TrangThai) {
		this.TrangThai = TrangThai;
	}

}