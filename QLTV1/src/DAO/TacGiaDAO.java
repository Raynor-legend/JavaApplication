/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TacGiaDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class TacGiaDAO {

    Connect conn = new Connect();

    public TacGiaDAO() {
    }

    public ArrayList docTG() {
        ArrayList dstg = new ArrayList<TacGiaDTO>();
        try {
            String qry = "select * from tacgia where TRANGTHAI='1'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                TacGiaDTO tg = new TacGiaDTO();
                tg.setMaTG(rs.getString(1));
                tg.setTenTG(rs.getString(2));//Dat gia tri cho ten sach 
                tg.setTrangThai(rs.getString(3));
                dstg.add(tg);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dstg;

    }
 public ArrayList docTGAN() {
        ArrayList dstg = new ArrayList<TacGiaDTO>();
        try {
            String qry = "select * from tacgia where TRANGTHAI='0'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()) {
                TacGiaDTO tg = new TacGiaDTO();
                tg.setMaTG(rs.getString(1));
                tg.setTenTG(rs.getString(2));//Dat gia tri cho ten sach 
                tg.setTrangThai(rs.getString(3));
                dstg.add(tg);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách");
        }
        return dstg;

    }

    public void themtg(TacGiaDTO tg) {
        try {
            String qry = "insert into tacgia values (";
            qry = qry + "'" + tg.getMaTG() + "'";
            qry = qry + "," + "'" + tg.getTenTG() + "'";
            qry = qry + "," + "'" + tg.getTrangThai()+"'";
            qry = qry + ")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            conn.close();
        } catch (Exception ex) {
        }
    }

    public boolean xoa(String matg) {
        String ma = " ";
        try {
            String qry = "delete  from tacgia where MATG='" + matg + "'";
          //String qry = "update tacgia set TRANGTHAI where MATG='" + matg + "'";
            conn.getStatement();
            int row = conn.ExecuteUpdate(qry);
            conn.close();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    public void sua(TacGiaDTO tg) {
        try {
            String qry = "Update tacgia Set ";
            qry = qry + "MATG=" + "'" + tg.getMaTG() + "',";
            qry = qry + "TENTG=" + "'" + tg.getTenTG() + "'";
            qry+=",TRANGTHAI=" + "'" + tg.getTrangThai()+ "'";
            qry = qry + "where MATG='" + tg.getMaTG() + "'";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        TacGiaDTO tg = new TacGiaDTO("TG1", "Tan Phat");
//        TacGiaDAO tgdao = new TacGiaDAO();
//        tgdao.sua(tg);
//    }
}
