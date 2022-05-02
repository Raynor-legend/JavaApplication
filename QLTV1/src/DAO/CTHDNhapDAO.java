/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CTHDNhapDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Dell
 */
public class CTHDNhapDAO {
 Connect conn = new Connect();
 public CTHDNhapDAO(){}
    public ArrayList docCTHDNhap() 
    {
        ArrayList<CTHDNhapDTO> dscthdNhap=new ArrayList<CTHDNhapDTO>();
        try{
             String qry = "select * from cthdnhap";
           ResultSet rs = conn.excuteQuery(qry);
            System.out.println(qry);
           while(rs.next())
           {
               CTHDNhapDTO cthdNhap = new CTHDNhapDTO();
               cthdNhap.setMaPhieuNhap(rs.getInt(2));
               cthdNhap.setMaSanpham(rs.getString(3));//Dat gia tri cho ten Sanpham 
               cthdNhap.setTenSanpham(rs.getString(4));
               cthdNhap.setDonGia(rs.getString(5));
               cthdNhap.setSoLuong(rs.getString(6));
               cthdNhap.setThanhTien(rs.getString(7));
               dscthdNhap.add(cthdNhap);
                
           }
        }
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
       }
        return dscthdNhap;
        
    }
     public void themcthdNhap(CTHDNhapDTO ct) {
        try {
            String qry = "insert into cthdnhap values (";
           qry = qry + "'" + ct.getSTT()+ "'";
            qry = qry + ",'" + ct.getMaPhieuNhap()+ "'";
            qry = qry + "," + "'" + ct.getMaSanpham() + "'";
            qry = qry + "," + "'" + ct.getTenSanpham()+ "'";
            
            qry = qry + "," + "'" + ct.getDonGia()+ "'";
            qry = qry + "," + "'" + ct.getSoLuong() + "'";
            qry = qry + "," + "'" + ct.getThanhTien() + "'";
            qry = qry + ")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {
        }
    }

    public void xoacthdNhap(String mact) {
        String ma = " ";
        try {
            String qry = "delete from cthdnhap where MANNHAP='" + mact + "'"  ;
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {

        }
    }

    public void suacthdNhap(CTHDNhapDTO ct) {
        try {
            String qry = "Update cthdnhap Set ";
            qry = qry + "MAPHIEUNHAP=" + "'" + ct.getMaPhieuNhap()+ "',";
            qry = qry + "MASP=" + "'" + ct.getMaSanpham() + "'";
            qry = qry + "TENSP=" + "'" + ct.getTenSanpham()+ "'";
            qry = qry + "DONGIANHAP=" + "'" + ct.getDonGia()+ "'";
            qry = qry + "SOLUONG=" + "'" + ct.getSoLuong() + "'";
            qry = qry + "THANHTIEN=" + "'" + ct.getThanhTien() + "'";

            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
          
}
