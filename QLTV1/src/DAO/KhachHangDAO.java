/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class KhachHangDAO {

    Connect conn = new Connect();

    public KhachHangDAO() {
    }

    public ArrayList dockh() {
        ArrayList dskh = new ArrayList<KhachHang>();
        try {
            String qry = "select * from khachhang";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setHoKH(rs.getString(2));//Dat gia tri cho ten sach 
                kh.setTenKH(rs.getString(3));
                kh.setNgaysinh(rs.getString(4));
                kh.setGioitinh(rs.getString(5));
                kh.setDiachi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setMail(rs.getString(8));
                dskh.add(kh);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dskh;

    }
    
    public ArrayList timkh(String tuTimKiem) {
        ArrayList dskh = new ArrayList<KhachHang>();
        try {
            String qry = "SELECT * FROM `khachhang` WHERE MAKH LIKE '%" + tuTimKiem + "%' "
                    + "OR HOKH LIKE '%" + tuTimKiem + "%'"
                    + " OR TENKH LIKE '%" + tuTimKiem + "%'"
                    + " OR NGAYSINH LIKE '%" + tuTimKiem + "%'"
                    + " OR GIOITINH LIKE '%" + tuTimKiem + "%'"
                    + " OR DIACHI LIKE '%" + tuTimKiem + "%'"
                    + " OR SDT LIKE '%" + tuTimKiem + "%' "
                    + "OR Mail LIKE '%" + tuTimKiem + "%' ";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setHoKH(rs.getString(2));//Dat gia tri cho ten sach 
                kh.setTenKH(rs.getString(3));
                kh.setNgaysinh(rs.getString(4));
                kh.setGioitinh(rs.getString(5));
                kh.setDiachi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setMail(rs.getString(8));
                dskh.add(kh);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dskh;

    }

    public void themkh(KhachHang kh) {
        try {
            String qry = "insert into khachhang values (";
            qry = qry + "'" + kh.getMaKH() + "'";
            qry = qry + "," + "'" + kh.getHoKH() + "'";
            qry = qry + ",'" + kh.getTenKH() + "'";
            qry = qry + ",'" + kh.getNgaysinh() + "'";
            qry = qry + ",'" + kh.getGioitinh() + "'";
            qry = qry + ",'" + kh.getDiachi() + "'";
            qry = qry + ",'" + kh.getSdt() + "'";
            qry = qry + ",'" + kh.getMail() + "'";
            qry = qry + ")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {
        }
    }

    public void xoa(String makh) {
        String ma = " ";
        try {
            String qry = "delete from khachhang where makh='" + makh + "'";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {

        }
    }

    public void suakh(KhachHang kh) {
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "MAKH=" + "'" + kh.getMaKH() + "'";
            qry = qry + ",HOKH=" + "'" + kh.getHoKH() + "'";
            qry = qry + ",TENKH=" + "'" + kh.getTenKH() + "'";
            qry = qry + ",NGAYSINH=" + "'" + kh.getNgaysinh() + "'";
            qry = qry + ",GIOITINH=" + "'" + kh.getGioitinh() + "'";
            qry = qry + ",DIACHI='" + kh.getDiachi() + "'";
            qry = qry + ",SDT='" + kh.getSdt()+ "'";
            qry = qry + ",MAIL='" + kh.getMail() + "'";

            qry = qry + " where MAKH='" + kh.getMaKH() + "'";
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
        KhachHangDAO khdao = new KhachHangDAO();
    }
}
