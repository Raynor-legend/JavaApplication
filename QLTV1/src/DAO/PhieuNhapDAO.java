
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.PhieuNhapDTO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class PhieuNhapDAO {
    Connect conn = new Connect();
    public PhieuNhapDAO(){}
    public ArrayList docPN(){
        ArrayList dspn= new ArrayList<PhieuNhapDTO>();
        
        try{
            String  qry= "select * from nhapsp where TRANGTHAI='1'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPhieuNhap(rs.getInt(1));
                pn.setMaNV(rs.getString(2));
                //pn.setMaNCC(rs.getString(3));
                pn.setNgayNhap(LocalDate.parse(rs.getString(4)));
                pn.setTongtien(rs.getString(5));
                
                dspn.add(pn);
                
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }
    return dspn;
}
    public ArrayList docPNan(){
        ArrayList dspnan= new ArrayList<PhieuNhapDTO>();
        try{
            String  qry= "select * from nhapsp where TRANGTHAI='0'";
            ResultSet rs = conn.excuteQuery(qry);
            while (rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPhieuNhap(rs.getInt(1));
                pn.setMaNV(rs.getString(2));
                //pn.setMaNCC(rs.getString(3));
                pn.setNgayNhap(LocalDate.parse(rs.getString(4)));
                pn.setTongtien(rs.getString(5));
                
                dspnan.add(pn);
                
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }
    return dspnan;
}
        
    public void thempn(PhieuNhapDTO pn){
        try{
            String qry = "insert into nhapsp values(";
            qry= qry+ "'"+pn.getMaPhieuNhap()+ "'";
            qry = qry+ ",'" +pn.getMaNV()+ "'";
            //qry= qry+ ",'"+pn.getMaNCC()+ "'";
            qry= qry+ ",'"+pn.getNgayNhap()+ "'";
            qry= qry+ ",'"+pn.getTongtien()+ "'";
            qry = qry +",'1'";
            qry = qry+")";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
            
        }
        catch(Exception ex){
    }
}
    public void xoa(PhieuNhapDTO pn) {
        
        try {
            String qry = "Update nhapsp Set TRANGTHAI = '0' where MANHAP ='" + pn.getMaPhieuNhap()+"'";
            conn.getStatement();
            conn.ExecuteUpdate(qry);
            System.out.println(qry);
            conn.close();
        } catch (Exception ex) {

        }
    }


    public static void main ()
    {
        //NhanVien nv = new NhanVien();
        
    }
}
