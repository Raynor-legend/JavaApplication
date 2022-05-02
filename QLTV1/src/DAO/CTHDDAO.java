/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CTHDDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class CTHDDAO {
	Connect conn = new Connect();

	public CTHDDAO() {
	}

	public ArrayList docCTHD() {
		ArrayList<CTHDDTO> dscthd = new ArrayList<CTHDDTO>();
		try {
			String qry = "select * from cthd";
			ResultSet rs = conn.excuteQuery(qry);
			System.out.println(qry);
			while (rs.next()) {
				CTHDDTO cthd = new CTHDDTO();
				cthd.setMaHD(rs.getInt(2));
				cthd.setMaSanpham(rs.getString(3));// Dat gia tri cho ten Sanpham
				cthd.setTenSanpham(rs.getString(4));
				cthd.setGiaBan(rs.getString(5));
				cthd.setSoluong(rs.getString(6));
				cthd.setThanhTien(rs.getString(7));
				dscthd.add(cthd);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
		}
		return dscthd;

	}

	public void themcthd(CTHDDTO ct) {
		try {
			String qry = "insert into cthd values (";
			qry = qry + "'" + ct.getSTT() + "'";
			qry = qry + ",'" + ct.getMaHD() + "'";
			qry = qry + "," + "'" + ct.getMaSanpham() + "'";
			qry = qry + "," + "'" + ct.getTenSanpham() + "'";

			qry = qry + "," + "'" + ct.getGiaBan() + "'";
			qry = qry + "," + "'" + ct.getSoluong() + "'";
			qry = qry + "," + "'" + ct.getThanhTien() + "'";
			qry = qry + ")";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {
		}
	}

	public void xoa(String mact) {
		String ma = " ";
		try {
			String qry = "delete from cthd where MAHD='" + mact + "'";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {

		}
	}

	public void sua(CTHDDTO ct) {
		try {
			String qry = "Update hoadon Set ";
			qry = qry + "MAHD=" + "'" + ct.getMaHD() + "',";
			qry = qry + "MASP=" + "'" + ct.getMaSanpham() + "'";
			qry = qry + "TENSP=" + "'" + ct.getTenSanpham() + "'";
			qry = qry + "GIABAN=" + "'" + ct.getGiaBan() + "'";
			qry = qry + "SOLUONG=" + "'" + ct.getSoluong() + "'";
			qry = qry + "THANHTIEN=" + "'" + ct.getThanhTien() + "'";

			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//           public static void main(String[] args) {
//        TheLoaiDTO tl = new TheLoaiDTO("NCC1","Tan Phat");
//        TLDAO tldao=new TLDAO();
//        tldao.sua(tl);

}