
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NhanVienDAO {

    Connect conn = new Connect();

    public NhanVienDAO() {
    }

    public ArrayList docNV() {
        ArrayList dsnv = new ArrayList<NhanVienDTO>();
        try {
            String qry = "select * from nhanvien where TRANGTHAI='1'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getString(1));
                nv.setHoNV(rs.getString(2));
                nv.setTenNV(rs.getString(3));
                nv.setNgaysinh(rs.getString(4));
                nv.setGioitinh(rs.getString(5));
                nv.setCMND(rs.getString(6));
                nv.setDiachi(rs.getString(7));
                nv.setSdt(rs.getString(8));
                nv.setTrangThai(rs.getString(9));

                dsnv.add(nv);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dsnv;
    }

    public ArrayList docNVAn() {
        ArrayList dsnvan = new ArrayList<NhanVienDTO>();
        try {
            String qry = "select * from nhanvien where TRANGTHAI='0'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getString(1));
                nv.setHoNV(rs.getString(2));
                nv.setTenNV(rs.getString(3));
                nv.setNgaysinh(rs.getString(4));
                nv.setGioitinh(rs.getString(5));
                nv.setCMND(rs.getString(6));
                nv.setDiachi(rs.getString(7));
                nv.setSdt(rs.getString(8));
                nv.setTrangThai(rs.getString(9));
                dsnvan.add(nv);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dsnvan;
    }

    public void them(NhanVienDTO nv) {
        try {
            String qry = "insert into nhanvien values(";
            qry = qry + "'" + nv.getMaNV() + "'";
            qry = qry + ",'" + nv.getHoNV() + "'";
            qry = qry + ",'" + nv.getTenNV() + "'";
            qry = qry + ",'" + nv.getNgaysinh() + "'";
            qry = qry + ",'" + nv.getGioitinh() + "'";
            qry = qry + ",'" + nv.getCMND() + "'";
            qry = qry + ",'" + nv.getDiachi() + "'";
            qry = qry + ",'" + nv.getSdt() + "'";
            qry = qry + ",'" + nv.getTrangThai() + "'";
            qry = qry + ")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();

        } catch (Exception ex) {
        }
    }

    public void xoa(NhanVienDTO nv) {
        String ma = " ";
        try {
            String qry = "Update nhanvien Set";
            qry = qry + " TRANGTHAI='0'";
            qry = qry + " where MANV ='" + nv.getMaNV() + "'";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();

        } catch (Exception ex) {

        }
    }

    public void sua(NhanVienDTO nv) {
        try {
            String qry = "Update nhanvien Set";
            qry = qry + " MANV='" + nv.getMaNV() + "'";
            qry = qry + ",HONV='" + nv.getHoNV() + "'";
            qry = qry + ",TENNV='" + nv.getTenNV() + "'";
            qry = qry + ",NGAYSINH = '" + nv.getNgaysinh() + "'";
            qry = qry + ",GIOITINH = '" + nv.getGioitinh() + "'";
            qry = qry + ",CMND ='" + nv.getCMND() + "'";
            qry = qry + ",DIACHI = '" + nv.getDiachi() + "'";
            qry = qry + ",SDT = '" + nv.getSdt() + "'";
            qry = qry + ",TRANGTHAI= '" + nv.getTrangThai() + "' ";
            qry = qry + " WHERE MANV ='" + nv.getMaNV() + "'";

            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main() {
        NhanVienDTO nv = new NhanVienDTO();

    }
}
