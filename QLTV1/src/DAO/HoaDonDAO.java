/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class HoaDonDAO {

    Connect conn = new Connect();

    public HoaDonDAO() {
    }

    public ArrayList docHoaDon() {
        ArrayList<HoaDonDTO> dshd = new ArrayList<HoaDonDTO>();
        try {
            String qry = "select MAHD,MAKH,MANV,ngaylap,thanhtien from hoadon where TRANGTHAI = '1'";
            System.out.println(qry);
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getString(2));//Dat gia tri cho ten sach 
                hd.setMaNV(rs.getString(3));
                hd.setNgayLap(LocalDate.parse(rs.getString(4)));
                hd.setThanhTien(rs.getString(5));
                dshd.add(hd);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dshd;

    }

    public ArrayList docHoaDonAn() {
        ArrayList<HoaDonDTO> dshdan = new ArrayList<HoaDonDTO>();
        try {
            String qry = "select MAHD,MAKH,MANV,ngaylap,thanhtien from hoadon where TRANGTHAI = '0'";
            System.out.println(qry);
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getString(2));//Dat gia tri cho ten sach 
                hd.setMaNV(rs.getString(3));
                hd.setNgayLap(LocalDate.parse(rs.getString(4)));
                hd.setThanhTien(rs.getString(5));
                dshdan.add(hd);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dshdan;

    }

    public ArrayList docHoaDonChung() {
        ArrayList<HoaDonDTO> dshdchung = new ArrayList<HoaDonDTO>();
        try {
            String qry = "select MAHD,MAKH,MANV,ngaylap,thanhtien from hoadon";
            System.out.println(qry);
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getString(2));//Dat gia tri cho ten sach 
                hd.setMaNV(rs.getString(3));
                hd.setNgayLap(LocalDate.parse(rs.getString(4)));
                hd.setThanhTien(rs.getString(5));
                dshdchung.add(hd);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dshdchung;

    }

    public void themhd(HoaDonDTO hd) {
        try {
            String qry = "insert into hoadon values (";
            qry = qry + "'" + hd.getMaHD() + "'";
            qry = qry + "," + "'" + hd.getMaKH() + "'";
            qry = qry + "," + "'" + hd.getMaNV() + "'";
            qry = qry + "," + "'" + hd.getNgayLap() + "'";
            qry = qry + "," + "'" + hd.getThanhTien() + "'";
            qry = qry + ",'1'";
            qry = qry + ")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {
        }
    }

    public void xoa(HoaDonDTO hd) {
        String ma = " ";
        try {
            String qry = "Update hoadon Set TRANGTHAI = '0' where MAHD =" + hd.getMaHD();
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {

        }
    }

//    public void sua(HoaDonDTO hd) {
//        try {
//            String qry = "Update hoadon Set ";
//            qry = qry + "MAHD=" + "'" + hd.getMaHD() + "'";
//            qry = qry + ",MAKH=" + "'" + hd.getMaKH() + "'";
//            qry = qry + ",MANV=" + "'" + hd.getMaNV() + "'";
//            qry = qry + ",NGAYLAP=" + "'" + hd.getNgayLap() + "'";
//            qry = qry + ",THANHTIEN=" + "'" + hd.getThanhTien() + "'";
//            qry = qry +" where"
//            conn.getStatement();
//            conn.ExecuteUpdate(qry);
//            System.out.println(qry);
//            conn.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//           public static void main(String[] args) {
//        TheLoaiDTO tl = new TheLoaiDTO("NCC1","Tan Phat");
//        TLDAO tldao=new TLDAO();
//        tldao.sua(tl);
}
