/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
//import DTO.Quyen;
import GUI.TaiKhoanGUI;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class TaiKhoanBUS {
        public static ArrayList<TaiKhoanDTO> dstk;
    //static ArrayList<Quyen> q ;                
    private DefaultTableModel model;
    public TaiKhoanBUS(){
        JTextField txtID,txtPass,txtMaQuyen,txtMaNV;
        JTable tblQLTK;
    }
     public ArrayList docTK(){
        TaiKhoanDAO data= new TaiKhoanDAO();
        return data.doctk();
    }
    public void docDSTK(){
        TaiKhoanDAO data= new TaiKhoanDAO();
        if(dstk == null)    dstk = new ArrayList();
        dstk = data.doctk();
    }
    public void themTK(TaiKhoanDTO tk){
        TaiKhoanDAO data = new TaiKhoanDAO();
        data.them(tk);
        dstk.add(tk);        
    }
    public void xoaTK(TaiKhoanDTO tk,int i){
        TaiKhoanDAO nvdao = new TaiKhoanDAO();
        String ma = dstk.get(i).getMaNV();
        nvdao.xoa(ma);
        dstk.remove(i);
    }
    public void suaTK(int i,TaiKhoanDTO tk){
        TaiKhoanDAO data = new TaiKhoanDAO();
        data.sua(tk);
        dstk.set(i,tk);
    }
    public ArrayList<TaiKhoanDTO> timkiem_id(String ma){
        ArrayList<TaiKhoanDTO> kq = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO tk: dstk){
            if(tk.getMaNV().toLowerCase().contains(ma.toLowerCase()))
                kq.add(tk);
        }
        return kq;
    }
    public int matrung(String ma){
        for(TaiKhoanDTO tk:dstk)
        {
            if(tk.getMaNV().equals(ma))
            {
                return 1;
        }
        }
         return 0;
    }
   
}