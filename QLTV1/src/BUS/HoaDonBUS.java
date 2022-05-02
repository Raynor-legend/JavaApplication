/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
//import DTO.Quyen;
import GUI.HoaDonGUI;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class HoaDonBUS {
	public static ArrayList<HoaDonDTO> dshd;
	public static ArrayList<HoaDonDTO> dshdan;
//        public static ArrayList<HoaDonDTO>  dshdchung;
	private DefaultTableModel model;

	public HoaDonBUS() {
//        dshd = dochd();
//        dshdan  = dochdan();
//        dshdchung = dochdchung();
	}

	public ArrayList dochd() {
		HoaDonDAO dao = new HoaDonDAO();
		return dao.docHoaDon();
	}

	public ArrayList dochdan() {
		HoaDonDAO dao = new HoaDonDAO();
		return dao.docHoaDonAn();
	}

	public ArrayList dochdchung() {
		HoaDonDAO dao = new HoaDonDAO();
		return dao.docHoaDonChung();
	}

	public void docDSHD() {
		HoaDonDAO data = new HoaDonDAO();
		if (dshd == null)
			dshd = new ArrayList();
		dshd = data.docHoaDon();
	}

//    public void docDSHDChung(){
//        HoaDonDAO data = new HoaDonDAO();
//        if(dshdchung ==null) dshdchung = new ArrayList();
//        dshdchung = data.docHoaDonChung();
//    }
	public void themHD(HoaDonDTO hd) {
		HoaDonDAO data = new HoaDonDAO();
		data.themhd(hd);
		dshd.add(hd);
	}

//   
//    public void suaHD(int i,HoaDonDTO hd){
//        HoaDonDAO data = new HoaDonDAO();
//        data.(hd);
//        dshd.set(i,hd);
//    }
	public void xoaHD(int i, HoaDonDTO hd) {
		HoaDonDAO data = new HoaDonDAO();
		data.xoa(hd);
		dshd.remove(hd);
	}

	public int getMa() {
		if (dshd.isEmpty()) {
			return 500000;
		} else
			return dshd.size() + 500001;
	}

	public ArrayList<HoaDonDTO> timkiem_manv(String ma) {
		ArrayList<HoaDonDTO> kq = new ArrayList<HoaDonDTO>();
		for (HoaDonDTO hd : dshd) {
			if (hd.getMaNV().toLowerCase().contains(ma.toLowerCase()))
				kq.add(hd);
		}
		return kq;
	}

	public ArrayList<HoaDonDTO> timkiem_makh(String ma) {
		ArrayList<HoaDonDTO> kq = new ArrayList<HoaDonDTO>();
		for (HoaDonDTO hd : dshd) {
			if (hd.getMaKH().toLowerCase().contains(ma.toLowerCase()))
				kq.add(hd);
		}
		return kq;
	}
}
