/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.CTHDDAO;
//import DAO.HoaDonDAO;
import DTO.CTHDDTO;
//import DTO.Quyen;
import GUI.HoaDonGUI;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class CTHDBUS {
        public static ArrayList<CTHDDTO> dscthd;              
    private DefaultTableModel model;
    public CTHDBUS(){
        JTextField txtMaHD,txtMaSach,txtGiaBan,txtSoluong,txtThanhTien;
        JTable tblDSHD;
        dscthd = doccthd();
    }
    public ArrayList doccthd(){
        CTHDDAO data = new CTHDDAO();
        return data.docCTHD();
    }
    public void docDSCTHD(){
        CTHDDAO data= new CTHDDAO();
        if(dscthd == null)    dscthd = new ArrayList();
        dscthd = data.docCTHD();
    }
     public void themCTHD(CTHDDTO ct){
        CTHDDAO data = new CTHDDAO();
        data.themcthd(ct);
        dscthd.add(ct);        
    }
     public int getSTT(){
         CTHDDAO data = new CTHDDAO();
         if(dscthd == null){
             dscthd = new ArrayList();
             dscthd = data.docCTHD();
         }
         System.out.println("abc:" + (dscthd.size() +1) );
        
         
         return dscthd.size() + 1;
     }

}    

