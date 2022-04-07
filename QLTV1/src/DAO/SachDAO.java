/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SachDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SachDAO {
    Connect conn=new Connect();
    public SachDAO(){}
     public ArrayList docSach() 
    {
        ArrayList dssach=new ArrayList<SachDTO>();
        try{
             String qry = "select * from sach";
           ResultSet rs = conn.excuteQuery(qry);
           while(rs.next())
           {
               SachDTO sach = new SachDTO();
               sach.setMaSach(rs.getString(1));
               sach.setTenSach(rs.getString(2));//Dat gia tri cho ten sach 
               sach.setMaTG(rs.getString(3));
               sach.setMaTL(rs.getString(4));
               sach.setMaNXB(rs.getString(5));
               sach.setSoLuong(rs.getString(6));
               sach.setDonGia(rs.getString(7));
              
               dssach.add(sach);
               
           }
        }
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
       }
        return dssach;
        
    }
    public void them(SachDTO sach){
         try{
            String qry ="insert into sach values (";
            qry = qry+"'"+sach.getMaSach()+"'";
            qry = qry+","+"'"+sach.getTenSach()+"'";
            qry = qry+","+"'"+sach.getMaTG()+"'";
            qry = qry+","+"'"+sach.getMaTL()+"'";
            qry = qry+","+"'"+sach.getMaNXB()+"'";
            qry = qry+","+"'"+sach.getSoLuong()+"'";
            qry = qry+","+"'"+sach.getDonGia()+"'";
             qry = qry+")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
       } catch (Exception ex) {
       }
        
    }
    public void xoa(String  masach){
      String ma =" ";
        try{
            String qry="delete from sach where MASACH='"+masach+"'";
           conn.getStatement();
           conn.ExecuteUpdate(qry);
            System.out.println(qry);
           conn.close();
        }catch(Exception ex){
            
        }
    }
    public void sua(SachDTO sach){
        try{
            String qry="Update sach Set ";
            qry = qry+"MASACH="+"'"+sach.getMaSach()+"',";
            qry = qry+"TENSACH="+"'"+sach.getTenSach()+"'";
            qry = qry+",maTG="+"'"+sach.getMaTG()+"'";
            qry = qry+",MaTL="+"'"+sach.getMaTL()+"'";
            qry = qry+",MANXB="+"'"+sach.getMaNXB()+"'";
            qry = qry+",SOLUONG="+"'"+sach.getSoLuong()+"'";
            qry = qry+",DONGIABAN="+"'"+sach.getDonGia()+"'";
            qry = qry+" "+"where MASACH='"+sach.getMaSach()+"'";
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
    public void sua1(SachDTO sach){
        try{
            String qry="Update sach Set ";
            //qry = qry+"MASACH="+"'"+sach.getMaSach()+"',";
            //qry = qry+"TENSACH="+"'"+sach.getTenSach()+"'";
          //  qry = qry+",maTG="+"'"+sach.getMaTG()+"'";
          //  qry = qry+",MaTL="+"'"+sach.getMaTL()+"'";
           // qry = qry+",MANXB="+"'"+sach.getMaNXB()+"'";
            qry = qry+"SOLUONG="+"'"+sach.getSoLuong()+"'";
            //qry = qry+",DONGIABAN="+"'"+sach.getDonGia()+"'";
            qry = qry+" "+"where MASACH='"+sach.getMaSach()+"'";
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
    
    public static void main(String[] args) {
       // SachDTO sach = new SachDTO("DM12", "Dịnh mệnh", "TT", "TH", "GD",100,105000);
        SachDAO sachdao=new SachDAO();
        //sachdao.sua(sach);
        
      //  SachDTO sach=new SachDTO("DM05","Viet nam","TT","HC","GD",2,1);
      //  SachDAO sachdao = new  SachDAO();
      //  sachdao.them(sach);
      /* SachDTO sach=new SachDTO("DM01","");
       SachDAO sachdao=new SachDAO();
       sachdao.sua(sach);*/
     // SachDTO sach = new SachDTO();
     // SachDAO sachdao= new SachDAO();
     //   String masach="123";
     // sachdao.xoa(masach);
       
    }
}

    

            
            
           