/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TheLoaiDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Dell
 */
public class TheLoaiDAO {
 Connect conn = new Connect();
 public TheLoaiDAO(){}
    public ArrayList docTheLoai() 
    {
        ArrayList<TheLoaiDTO> dstl=new ArrayList<TheLoaiDTO>();
        try{
             String qry = "select * from theloai";
           ResultSet rs = conn.excuteQuery(qry);
           while(rs.next())
           {
               TheLoaiDTO tl = new TheLoaiDTO();
               tl.setMaTL(rs.getString(1));
               tl.setTenTL(rs.getString(2));//Dat gia tri cho ten sach 
               dstl.add(tl);
                
           }
        }
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
       }
        return dstl;
        
    }
 
public void themtl(TheLoaiDTO ncc){
         try{
            String qry ="insert into theloai values (";
            qry = qry+"'"+ncc.getMaTL()+"'";
            qry = qry+","+"'"+ncc.getTenTL()+"'";
             qry = qry+")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
       } catch (Exception ex) {
       }
}
         public void xoa(String  matl){
      String ma =" ";
        try{
            String qry="delete from theloai where MATL='"+matl+"'";
           conn.getStatement();
           conn.ExecuteUpdate(qry);
            System.out.println(qry);
           conn.close();
        }catch(Exception ex){
            
        }
    }
         public void sua(TheLoaiDTO ncc){
        try{
            String qry="Update theloai Set ";
            qry = qry+"MATL="+"'"+ncc.getMaTL()+"',";
            qry = qry+"TENTL="+"'"+ncc.getTenTL()+"'";
      
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
                    
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
//           public static void main(String[] args) {
//        TheLoaiDTO tl = new TheLoaiDTO("NCC1","Tan Phat");
//        TLDAO tldao=new TLDAO();
//        tldao.sua(tl);
          
}