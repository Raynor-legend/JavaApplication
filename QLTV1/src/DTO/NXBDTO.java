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
public class NXBDTO {
	private String MaNXB;
	private String TenNXB;
	private String Diachi;

	public NXBDTO() {

	}

	public NXBDTO(String MaNXB, String TenNXB, String Diachi) {
		this.MaNXB = MaNXB;
		this.TenNXB = TenNXB;

		this.Diachi = Diachi;

	}

	public String getMaNXB() {
		return MaNXB;
	}

	public void setMaNXB(String MaNXB) {
		this.MaNXB = MaNXB;
	}

	public String getTenNXB() {
		return TenNXB;
	}

	public void setTenNXB(String TenNXB) {
		this.TenNXB = TenNXB;
	}

	public String getDiachi() {
		return Diachi;
	}

	public void setDiachi(String Diachi) {
		this.Diachi = Diachi;
	}

}
