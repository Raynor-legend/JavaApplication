/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class TaiKhoanDAO {
	Connect conn = new Connect();

	public TaiKhoanDAO() {
	}

	public ArrayList doctk() {
		ArrayList dstk = new ArrayList<TaiKhoanDTO>();
		try {
			String qry = "Select * from taikhoan";
			ResultSet rs = conn.excuteQuery(qry);
			while (rs.next()) {
				TaiKhoanDTO tk = new TaiKhoanDTO();
				tk.setMaNV(rs.getString(1));
				tk.setPass(rs.getString(2));
				tk.setQuyen(rs.getString(3));

				dstk.add(tk);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách!!");
		}
		return dstk;
	}

	public void them(TaiKhoanDTO tk) {
		try {
			String qry = "insert into taikhoan values";
			qry = qry + "MANV='" + tk.getMaNV() + "'";
			qry = qry + ",PASS = '" + tk.getPass() + "'";
			qry = qry + ",  Quyen = '" + tk.getQuyen() + "'";

			qry = qry + ")";

			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {

		}
	}

	public void xoa(String id) {
		try {
			String qry = "Delete from taikhoan where MANV ='" + id + "'";
			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {

		}
	}

	public void sua(TaiKhoanDTO tk) {
		try {
			String qry = "Update taikhoan Set ";
			qry = qry + ",MANV = '" + tk.getMaNV() + "'";
			qry = qry + ",PASS = '" + tk.getPass() + "'";
			qry = qry + ",QUYEN = '" + tk.getQuyen() + "'";

			qry = qry + ")";

			conn.getStatement();
			conn.ExecuteUpdate(qry);
			System.out.println(qry);
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}