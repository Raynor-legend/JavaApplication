/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ThongKeDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kieu Oanh
 */
public class ThongKeDAO {

    Connect conn = new Connect();

    public ThongKeDAO() {

    }

    public ArrayList ThongKeNV(String fromDate, String toDate) {
        ArrayList<ThongKeDTO> dstk = new ArrayList<ThongKeDTO>();
        try {
            String str = "SELECT nhanvien.MANV,"
                    + " nhanvien.TENNV,"
                    + " COUNT(hoadon.MAHD),"
                    + " SUM(hoadon.THANHTIEN) FROM hoadon INNER JOIN nhanvien ON "
                    + "hoadon.MANV = nhanvien.MANV "
                    + "WHERE hoadon.TRANGTHAI = 1 AND( hoadon.NGAYLAP BETWEEN '" + fromDate + "' AND " + "'" + toDate + "' )"
                    + "GROUP BY MANV";
            ResultSet rs = conn.excuteQuery(str);
            while (rs.next()) {
                ThongKeDTO tk = new ThongKeDTO();
                tk.setMa(rs.getString(1));
                tk.setTen(rs.getString(2));
                tk.setSoluong(rs.getString(3));
                tk.setGiatri(rs.getString(4));
                dstk.add(tk);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu");
        }
        return dstk;

    }

    public ArrayList ThongKeKH(String fromDate, String toDate) {
        ArrayList<ThongKeDTO> dstk = new ArrayList<ThongKeDTO>();
        try {
            String str = "SELECT khachhang.MAKH,"
                    + " khachhang.TENKH,"
                    + " COUNT(hoadon.MAHD),"
                    + " SUM(hoadon.THANHTIEN) FROM hoadon"
                    + " INNER JOIN khachhang ON hoadon.MAKH = khachhang.MAKH"
                    + " WHERE hoadon.TRANGTHAI = 1 AND( hoadon.NGAYLAP BETWEEN '" + fromDate + "' AND " + "'" + toDate + "') "
                    + " GROUP BY MAKH";
            ResultSet rs = conn.excuteQuery(str);
            while (rs.next()) {
                ThongKeDTO tk = new ThongKeDTO();
                tk.setMa(rs.getString(1));
                tk.setTen(rs.getString(2));
                tk.setSoluong(rs.getString(3));
                tk.setGiatri(rs.getString(4));
                dstk.add(tk);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu");
        }
        return dstk;
    }
    public ArrayList ThongKeSB(String fromDate, String toDate){
        ArrayList<ThongKeDTO> dstk= new ArrayList<ThongKeDTO>();
        try{
            String str ="SELECT sanpham.MASP, sanpham.TENSP, SUM(cthd.SOLUONG),SUM(cthd.THANHTIEN) FROM "
                    +" (sanpham INNER JOIN cthd ON sanpham.MASP= cthd.MASP) INNER JOIN hoadon ON cthd.MAHD = hoadon.MAHD "
                    +" WHERE hoadon.TRANGTHAI =1 AND (hoadon.NGAYLAP BETWEEN '"+ fromDate +"' AND '"+ toDate+"') GROUP BY sanpham.MASP";
           
            ResultSet rs = conn.excuteQuery(str);
            while (rs.next()) {
                ThongKeDTO tk = new ThongKeDTO();
                tk.setMa(rs.getString(1));
                tk.setTen(rs.getString(2));
                tk.setSoluong(rs.getString(3));
                tk.setGiatri(rs.getString(4));
                dstk.add(tk);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu");
        }
        return dstk;
    }
    public ArrayList ThongKeSN (String fromDate, String toDate){
         ArrayList<ThongKeDTO> dstk= new ArrayList<ThongKeDTO>();
        try{
            String str ="SELECT sanpham.MASP,sanpham.TENSP,SUM(cthdnhap.SOLUONG),SUM(cthdnhap.THANHTIEN) FROM"
                    + " (sanpham INNER JOIN cthdnhap ON sanpham.MASP = cthdnhap.MASP ) INNER JOIN nhapsp ON cthdnhap.MAPHIEUNHAP = nhapsp.MANHAP"
                    + " WHERE nhapsp.TRANGTHAI AND ( nhapsp.NGAYNHAP BETWEEN '" + fromDate + "' AND '"+ toDate +"' )GROUP BY sanpham.MASP";
           // Bên chi tiết hoá đơn nhập của tụi em có gì trong đó đâu mà để nó SUM
           
            ResultSet rs = conn.excuteQuery(str);
            while (rs.next()) {
                ThongKeDTO tk = new ThongKeDTO();
                tk.setMa(rs.getString(1));
                tk.setTen(rs.getString(2));
                tk.setSoluong(rs.getString(3));
                tk.setGiatri(rs.getString(4));
                dstk.add(tk);
            }
        }catch(Exception ex){
             System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu");
        }
        return dstk;
    }
    public ArrayList ThongKeSK(){
        ArrayList<ThongKeDTO> dstk= new ArrayList<ThongKeDTO>();
        try{
            String str ="SELECT MASP,"
                +"TENSP,"
                + " SOLUONG,"
                + " SOLUONG * DONGIABAN FROM `sanpham`"
                + " GROUP BY MASP";
            ResultSet rs = conn.excuteQuery(str);
            while (rs.next()) {
                ThongKeDTO tk = new ThongKeDTO();
                tk.setMa(rs.getString(1));
                tk.setTen(rs.getString(2));
                tk.setSoluong(rs.getString(3));
                tk.setGiatri(rs.getString(4));
                dstk.add(tk);
            }
        }catch(Exception ex){
           
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu");
        }
        return dstk;
    }
}
