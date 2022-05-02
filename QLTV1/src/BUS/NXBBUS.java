/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author Dell
 */
import DAO.NXBDAO;
import DTO.NXBDTO;
import BUS.NXBBUS;
import static BUS.SanphamBUS.dsSanpham;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NXBBUS {
	public static ArrayList<NXBDTO> dsnxb;
	private DefaultTableModel model;

	public NXBBUS() {
		JTextField txMaNXB;
		JTextField txTenNXB;
		JTextField txDiachi;
		JTable tbNXB;

	}

	public ArrayList docNXB() {
		NXBDAO data = new NXBDAO();
		return data.docNXB();
	}

	public void docDSNXB() {
		NXBDAO data = new NXBDAO();
		if (dsnxb == null)
			dsnxb = new ArrayList<>();
		dsnxb = data.docNXB();
	}

	public void suaNXB(int i, NXBDTO nxb) {
		NXBDAO data = new NXBDAO();
		data.sua(nxb);
		dsnxb.set(i, nxb);
	}

	public void xoa(NXBDTO nxb, int i) {
		NXBDAO nxbdao = new NXBDAO();
		String Ma = dsnxb.get(i).getMaNXB();
		nxbdao.xoa(Ma);
		dsnxb.remove(i);
	}

	public int kt_trung_ma(String manxb) {
		for (NXBDTO nxb : dsnxb) {
			if (nxb.getMaNXB().equals(manxb))
				return 1;
		}
		return 0;
	}

	public ArrayList<NXBDTO> timkiem_manxb(String manxb) {
		ArrayList<NXBDTO> kq = new ArrayList<NXBDTO>();
		for (NXBDTO nxb : dsnxb) {
			if (nxb.getMaNXB().toLowerCase().contains(manxb.toLowerCase()))
				kq.add(nxb);
		}
		return kq;
	}

	public ArrayList<NXBDTO> timkiem_tennxb(String tennxb) {
		ArrayList<NXBDTO> kq = new ArrayList<NXBDTO>();
		for (NXBDTO nxb : dsnxb) {
			if (nxb.getTenNXB().toLowerCase().contains(tennxb.toLowerCase()))
				kq.add(nxb);
		}
		return kq;
	}

	public void themNXB(NXBDTO nxb) {
		NXBDAO data = new NXBDAO();
		data.themnxb(nxb);// gọi hàm thêm bên DAO để thêm sách vào database
		dsnxb.add(nxb);//
	}
}
