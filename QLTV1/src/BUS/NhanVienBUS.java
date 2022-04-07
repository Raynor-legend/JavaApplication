
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import GUI.NhanVienGUI;
import DAO.NhanVienDAO;
import DTO.TaiKhoanDTO;

import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class NhanVienBUS {
    public static ArrayList<NhanVienDTO> dsnv,dsnvan;
   // static ArrayList<TaiKhoan> tk ;                
    private DefaultTableModel model;
    public NhanVienBUS(){
        dsnv = docnv();
        dsnvan = docnvan();
    }
     public ArrayList docnv(){
        NhanVienDAO data= new NhanVienDAO();
        return data.docNV();
    }
    public ArrayList docnvan(){
        NhanVienDAO data = new NhanVienDAO();
        return data.docNVAn();
    }
    
    
    public void docDSNV(){
        NhanVienDAO data= new NhanVienDAO();
        if(dsnv == null)    dsnv = new ArrayList();
        dsnv = data.docNV();
    }
    public void docDSNVan(){
        NhanVienDAO data= new NhanVienDAO();
        if(dsnvan == null)    dsnvan = new ArrayList();
        dsnvan = data.docNVAn();
    }
    public void themNV(NhanVienDTO nv){
        NhanVienDAO data = new NhanVienDAO();
        data.them(nv);
        dsnv.add(nv);        
    }
    public void xoaNV(NhanVienDTO nv,int i){
        NhanVienDAO nvdao = new NhanVienDAO();
        String ma = dsnv.get(i).getMaNV();
        nvdao.xoa(nv);
        dsnv.set(i,nv);
    }
    public void suaNV(int i,NhanVienDTO nv){
        NhanVienDAO data = new NhanVienDAO();
        data.sua(nv);
        dsnv.set(i,nv);
    }
    public ArrayList<NhanVienDTO> timkiem_manv(String manv){
        ArrayList<NhanVienDTO> kq = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nv: dsnv){
            if(nv.getMaNV().toLowerCase().contains(manv.toLowerCase()))
                kq.add(nv);
        }
        return kq;
    }
    public ArrayList timkiem_tennv(String tennv){
        ArrayList<NhanVienDTO> kq=new ArrayList<NhanVienDTO>();
        for(NhanVienDTO nv:dsnv){
            if(nv.getTenNV().toLowerCase().contains(tennv.toLowerCase()))
                kq.add(nv);
        }
        return kq;
    }
    public ArrayList timkiemcmnd(String cmnd){
        ArrayList<NhanVienDTO> kq= new ArrayList<NhanVienDTO>();
        for(NhanVienDTO nv:dsnv){
            if(nv.getCMND().toLowerCase().contains(cmnd.toLowerCase()));
            kq.add(nv);
        }
        return kq;
    }
    public ArrayList timkiemsdt(String sdt){
        ArrayList<NhanVienDTO> kq=new ArrayList<NhanVienDTO>();
        for(NhanVienDTO nv:dsnv){
            if(nv.getSdt().toLowerCase().contains(sdt.toLowerCase()))
                kq.add(nv);
        }
        return kq;
    }
    
    public boolean kiemtramatrung( String manv){
        for (NhanVienDTO nv : dsnv) {
            if (nv.getMaNV().equals(manv)) {
                return false;
            }
        }
        for(NhanVienDTO nv : dsnvan){
            if(nv.getMaNV().equals(manv))
                return false;
        }
        return true;
    }
    public boolean kiemtracmnd (String cmnd){
        for (NhanVienDTO nv : dsnv){
            if(nv.getCMND().equals(cmnd))
                return false;
        }
        for(NhanVienDTO nv : dsnvan){
            if(nv.getCMND().equals(cmnd))
                return false;
        }
        return true;
    }
    public boolean kiemtrasdt(String sdt){
        for(NhanVienDTO nv:dsnv){
            if(nv.getSdt().equals(sdt))
                return false;
        }
        for(NhanVienDTO nv :dsnvan){
            if(nv.getSdt().equals(sdt))
                return false;
        }
        return true;
    }
}
