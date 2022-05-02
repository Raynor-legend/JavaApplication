/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NXBDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class NXBDAO {
	Connect conn = new Connect();

	public NXBDAO() {
	}

	public ArrayList docNXB() {
		ArrayList dsnxb = new ArrayList<NXBDTO>();
		try {
			String qry = "select * from nhaxuatban";
			ResultSet rs = conn.excuteQuery(qry);
			while (rs.next()) {
				NXBDTO nxb = new NXBDTO();
				nxb.setMaNXB(rs.getString(1));
				nxb.setTenNXB(rs.getString(2));// Dat gia tri cho ten sach
				nxb.setDiachi(rs.getString(3));

				dsnxb.add(nxb);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
		}
		return dsnxb;

	}

	public void themnxb(NXBDTO nxb) {
		try {
			String qry = "insert into nhaxuatban values (";
			qry = qry + "'" + nxb.getMaNXB() + "'";
			qry = qry + "," + "'" + nxb.getTenNXB() + "'";
			qry = qry + "," + "'" + nxb.getDiachi() + "'";
			qry = qry + ")";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {
		}
	}

	public void xoa(String manxb) {
		String ma = " ";
		try {
			String qry = "delete from nhaxuatban where MANXB='" + manxb + "'";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {

		}
	}

	public void sua(NXBDTO nxb) {
		try {
			String qry = "Update nhaxuatban Set ";
			qry = qry + "MANXB=" + "'" + nxb.getMaNXB() + "',";
			qry = qry + "TENNXB=" + "'" + nxb.getTenNXB() + "'";
			qry = qry + ",DIACHI=" + "'" + nxb.getDiachi() + "'";
			qry = qry + " " + "where MANXB='" + nxb.getMaNXB() + "'";
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

	public static void main(String[] args) {
		NXBDTO nxb = new NXBDTO("NXB1", "Tan Phat", "109 Duong ba trac");
		NXBDAO nxbdao = new NXBDAO();
		nxbdao.sua(nxb);
	}
}
