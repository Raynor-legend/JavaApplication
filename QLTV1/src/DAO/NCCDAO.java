/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NCCDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class NCCDAO {

	Connect conn = new Connect();

	public NCCDAO() {
	}

	public ArrayList docNCC() {
		ArrayList dsncc = new ArrayList<NCCDTO>();
		try {
			String qry = "select * from nhacungcap where TRANGTHAI='1'";
			ResultSet rs = conn.excuteQuery(qry);
			while (rs.next()) {
				NCCDTO ncc = new NCCDTO();
				ncc.setMaNCC(rs.getString(1));
				ncc.setTenNCC(rs.getString(2));// Dat gia tri cho ten sach
				ncc.setDiachi(rs.getString(3));
				ncc.setSdt(rs.getString(4));
				ncc.setMail(rs.getString(5));
				ncc.setTrangThai(rs.getString(6));
				dsncc.add(ncc);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
		}
		return dsncc;

	}

	public ArrayList docNCCAN() {
		ArrayList dsncc = new ArrayList<NCCDTO>();
		try {
			String qry = "select * from nhacungcap where TRANGTHAI='0'";
			ResultSet rs = conn.excuteQuery(qry);
			while (rs.next()) {
				NCCDTO ncc = new NCCDTO();
				ncc.setMaNCC(rs.getString(1));
				ncc.setTenNCC(rs.getString(2));// Dat gia tri cho ten sach
				ncc.setDiachi(rs.getString(3));
				ncc.setSdt(rs.getString(4));
				ncc.setMail(rs.getString(5));
				ncc.setTrangThai(rs.getString(6));
				dsncc.add(ncc);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
		}
		return dsncc;

	}

	public ArrayList timncc(String tuTimKiem) {
		ArrayList dsncc = new ArrayList<NCCDTO>();
		try {
			String qry = "SELECT * FROM `nhacungcap` WHERE MANCC LIKE '%" + tuTimKiem + "%' " + "OR TENNCC LIKE '%"
					+ tuTimKiem + "%'" + " OR DIACHI LIKE '%" + tuTimKiem + "%' " + " OR SDT LIKE '%" + tuTimKiem
					+ "%' " + " OR GMAIL LIKE '%" + tuTimKiem + "%' " + " OR TRANGTHAI LIKE '%" + tuTimKiem + "%' ";
			ResultSet rs = conn.excuteQuery(qry);
			while (rs.next()) {
				NCCDTO ncc = new NCCDTO();
				ncc.setMaNCC(rs.getString(1));
				ncc.setTenNCC(rs.getString(2));// Dat gia tri cho ten sach
				ncc.setDiachi(rs.getString(3));
				ncc.setSdt(rs.getString(4));
				ncc.setMail(rs.getString(5));
				ncc.setTrangThai(rs.getString(6));
				dsncc.add(ncc);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
		}
		return dsncc;
	}

	public void themncc(NCCDTO ncc) {
		try {
			String qry = "insert into nhacungcap values (";
			qry = qry + "'" + ncc.getMaNCC() + "'";
			qry = qry + "," + "'" + ncc.getTenNCC() + "'";
			qry = qry + "," + "'" + ncc.getDiachi() + "'";
			qry = qry + ",'" + ncc.getSdt() + "'";
			qry = qry + ",'" + ncc.getMail() + "'";
			qry = qry + ")";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {
		}
	}

	public void xoa(String mancc) {
		String ma = " ";
		try {
			String qry = "delete from nhacungcap where MANCC='" + mancc + "'";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {

		}
	}

	public void sua(NCCDTO ncc) {
		try {
			String qry = "Update nhacungcap Set ";
			qry = qry + "MANCC=" + "'" + ncc.getMaNCC() + "',";
			qry = qry + "TENNCC=" + "'" + ncc.getTenNCC() + "'";
			qry = qry + ",DIACHI=" + "'" + ncc.getDiachi() + "'";
			qry = qry + ",SDT='" + ncc.getSdt() + "'";
			qry = qry + ",GMAIL='" + ncc.getMail() + "'";
			qry = qry + ",TRANGTHAI='" + ncc.getTrangThai() + "'";
			qry = qry + " " + "where MANCC='" + ncc.getMaNCC() + "'";
//                    qry = qry+"'MASACH'= "+sach.getMaSach();
//                    qry = qry+",'TENSACH'= "+sach.getTenSach();// sua ten giong dt
//                     qry = qry+",'MaTL'= "+sach.getMaTL();
//                     qry = qry+",'MaTG'= "+sach.getMaTG();
//                     qry = qry+",'MaNXB'= "+sach.getMaNXB();
//                     qry = qry+",'SOLUONG'= "+sach.getSoLuong();
//                     qry = qry+",'DONGIABAN'= "+sach.getDonGia();
//                    qry= qry+" "+" where MASACH= '"+sach.getMaSach()+"'";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//    public static void main(String[] args) {
//        NCCDTO ncc = new NCCDTO("NCC1", "Tan Phat", "109 Duong ba trac");
//        NCCDAO nccdao = new NCCDAO();
//        nccdao.sua(ncc);
//    }
}
