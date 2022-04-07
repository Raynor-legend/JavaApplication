/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.KhachHangDAO;
import DTO.KhachHang;
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
public class KhachHangBUS {
        public static ArrayList<KhachHang> dskh;              
    private DefaultTableModel model;
    public KhachHangBUS(){
        JTextField txtMaNV,txtPass,txtQuyen;
        JTable tblQLTK;
    }
    
     public ArrayList dockhachhang(){
        KhachHangDAO data= new KhachHangDAO();
        return data.dockh();
    }
     
    public ArrayList timkh(String tuTimKiem) {
        KhachHangDAO data= new KhachHangDAO();
        return data.timkh(tuTimKiem);
    }
     
    public void docDSKH(){
        KhachHangDAO data= new KhachHangDAO();
        if(dskh == null)    dskh = new ArrayList();
        dskh = data.dockh();
    }
    public void themKH(KhachHang tk){
        KhachHangDAO data = new KhachHangDAO();
        data.themkh(tk);
        dskh.add(tk);        
    }
    public void xoaKH(KhachHang kh,int i){
        KhachHangDAO khdao = new KhachHangDAO();
        String ma = dskh.get(i).getMaKH();
        khdao.xoa(ma);
        dskh.remove(i);
    }
    public void suaKH(int i,KhachHang kh){
        KhachHangDAO data = new KhachHangDAO();
        data.suakh(kh);
        dskh.set(i,kh);
    }
    public ArrayList<KhachHang> timkiem_makh(String ma){
        ArrayList<KhachHang> kq = new ArrayList<KhachHang>();
        for (KhachHang kh: dskh){
            if(kh.getMaKH().toLowerCase().contains(ma.toLowerCase()))
                kq.add(kh);
        }
        return kq;
    }
    public ArrayList<KhachHang> timkiem_tenkh(String ten){
         ArrayList<KhachHang> kq=new ArrayList<KhachHang>();
         for(KhachHang kh:dskh){
             if(kh.getTenKH().toLowerCase().contains(ten.toLowerCase()))
                 kq.add(kh);
         }
         return kq;
     }
     public ArrayList<KhachHang>timkiem_sdt(String sdt){
         ArrayList<KhachHang> kq=new ArrayList<KhachHang>();
         for(KhachHang kh:dskh){
             if(kh.getSdt().toLowerCase().contains(sdt.toLowerCase()))
                 kq.add(kh);
         }
         return kq;
     }
     public int kt_trung_ma(String makh) {
        for (KhachHang kh : dskh) {
            if (kh.getMaKH().equals(makh)) {
                return 1;
            }
        }
        return 0;
    }
     
}    

