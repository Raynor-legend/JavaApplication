/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanphamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SanphamDAO {
    Connect conn=new Connect();
    public SanphamDAO(){}
     public ArrayList docSanpham() 
    {
        ArrayList dsSanpham=new ArrayList<SanphamDTO>();
        try{
             String qry = "select * from sanpham";
           ResultSet rs = conn.excuteQuery(qry);
           while(rs.next())
           {
               SanphamDTO Sanpham = new SanphamDTO();
               Sanpham.setMaSanpham(rs.getString(1));
               Sanpham.setTenSanpham(rs.getString(2));//Dat gia tri cho ten Sanpham 
               Sanpham.setMaTG(rs.getString(3));
               Sanpham.setMaTL(rs.getString(4));
               Sanpham.setMaNXB(rs.getString(5));
               Sanpham.setSoLuong(rs.getString(6));
               Sanpham.setDonGia(rs.getString(7));
              
               dsSanpham.add(Sanpham);
               
           }
        }
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
       }
        return dsSanpham;
        
    }
    public void them(SanphamDTO Sanpham){
         try{
            String qry ="insert into sanpham values (";
            qry = qry+"'"+Sanpham.getMaSanpham()+"'";
            qry = qry+","+"'"+Sanpham.getTenSanpham()+"'";
            qry = qry+","+"'"+Sanpham.getMaTG()+"'";
            qry = qry+","+"'"+Sanpham.getMaTL()+"'";
            qry = qry+","+"'"+Sanpham.getMaNXB()+"'";
            qry = qry+","+"'"+Sanpham.getSoLuong()+"'";
            qry = qry+","+"'"+Sanpham.getDonGia()+"'";
             qry = qry+")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
       } catch (Exception ex) {
       }
        
    }
    public void xoa(String  masanpham){
      String ma =" ";
        try{
            String qry="delete from sanpham where MASP='"+masanpham+"'";
           conn.getStatement();
           conn.ExecuteUpdate(qry);
            System.out.println(qry);
           conn.close();
        }catch(Exception ex){
            
        }
    }
    public void sua(SanphamDTO Sanpham){
        try{
            String qry="Update Sanpham Set ";
            qry = qry+"MASP="+"'"+Sanpham.getMaSanpham()+"',";
            qry = qry+"TENSP="+"'"+Sanpham.getTenSanpham()+"'";
            qry = qry+",maTG="+"'"+Sanpham.getMaTG()+"'";
            qry = qry+",MaTL="+"'"+Sanpham.getMaTL()+"'";
            qry = qry+",MANCC="+"'"+Sanpham.getMaNXB()+"'";
            qry = qry+",SOLUONG="+"'"+Sanpham.getSoLuong()+"'";
            qry = qry+",DONGIABAN="+"'"+Sanpham.getDonGia()+"'";
            qry = qry+" "+"where MASP='"+Sanpham.getMaSanpham()+"'";
//                    qry = qry+"'MASP'= "+Sanpham.getMaSanpham();
//                    qry = qry+",'TENSanpham'= "+Sanpham.getTenSanpham();// sua ten giong dt
//                     qry = qry+",'MaTL'= "+Sanpham.getMaTL();
//                     qry = qry+",'MaTG'= "+Sanpham.getMaTG();
//                     qry = qry+",'MaNXB'= "+Sanpham.getMaNXB();
//                     qry = qry+",'SOLUONG'= "+Sanpham.getSoLuong();
//                     qry = qry+",'DONGIABAN'= "+Sanpham.getDonGia();
//                    qry= qry+" "+" where MASP= '"+Sanpham.getMaSanpham()+"'";
                    conn.getStatement();
                    conn.ExecuteUpdate(qry);
                    System.out.println(qry);
                    conn.close();
                    
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void sua1(SanphamDTO Sanpham){
        try{
            String qry="Update Sanpham Set ";
            //qry = qry+"MASP="+"'"+Sanpham.getMaSanpham()+"',";
            //qry = qry+"TENSanpham="+"'"+Sanpham.getTenSanpham()+"'";
          //  qry = qry+",maTG="+"'"+Sanpham.getMaTG()+"'";
          //  qry = qry+",MaTL="+"'"+Sanpham.getMaTL()+"'";
           // qry = qry+",MANXB="+"'"+Sanpham.getMaNXB()+"'";
            qry = qry+"SOLUONG="+"'"+Sanpham.getSoLuong()+"'";
            //qry = qry+",DONGIABAN="+"'"+Sanpham.getDonGia()+"'";
            qry = qry+" "+"where MASP='"+Sanpham.getMaSanpham()+"'";
//                    qry = qry+"'MASanpham'= "+Sanpham.getMaSanpham();
//                    qry = qry+",'TENSanpham'= "+Sanpham.getTenSanpham();// sua ten giong dt
//                     qry = qry+",'MaTL'= "+Sanpham.getMaTL();
//                     qry = qry+",'MaTG'= "+Sanpham.getMaTG();
//                     qry = qry+",'MaNXB'= "+Sanpham.getMaNXB();
//                     qry = qry+",'SOLUONG'= "+Sanpham.getSoLuong();
//                     qry = qry+",'DONGIABAN'= "+Sanpham.getDonGia();
//                    qry= qry+" "+" where MASP= '"+Sanpham.getMaSanpham()+"'";
                    conn.getStatement();
                    conn.ExecuteUpdate(qry);
                    System.out.println(qry);
                    conn.close();
                    
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
       // SanphamDTO Sanpham = new SanphamDTO("DM12", "Dịnh mệnh", "TT", "TH", "GD",100,105000);
        SanphamDAO Sanphamdao=new SanphamDAO();
        //Sanphamdao.sua(Sanpham);
        
      //  SanphamDTO Sanpham=new SanphamDTO("DM05","Viet nam","TT","HC","GD",2,1);
      //  SanphamDAO Sanphamdao = new  SanphamDAO();
      //  Sanphamdao.them(Sanpham);
      /* SanphamDTO Sanpham=new SanphamDTO("DM01","");
       SanphamDAO Sanphamdao=new SanphamDAO();
       Sanphamdao.sua(Sanpham);*/
     // SanphamDTO Sanpham = new SanphamDTO();
     // SanphamDAO Sanphamdao= new SanphamDAO();
     //   String maSanpham="123";
     // Sanphamdao.xoa(maSanpham);
       
    }
}

    

            
            
           