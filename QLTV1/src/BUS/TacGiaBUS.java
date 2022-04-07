/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author Dell
 */
import DAO.TacGiaDAO;
import DTO.TacGiaDTO;
import static BUS.SachBUS.dssach;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class TacGiaBUS {
    public static ArrayList<TacGiaDTO> dstg,dstgan;
    
    private DefaultTableModel model;
    public TacGiaBUS(){
        dstg=docTacGia();
        dstgan=docTacGiaAn();
    
}
     public ArrayList docTacGia(){
        TacGiaDAO data= new TacGiaDAO();
        return data.docTG();
    }
     public ArrayList docTacGiaAn(){
         TacGiaDAO data = new TacGiaDAO();
         return data.docTGAN();
     }
    public void docDSTG(){
    TacGiaDAO data= new TacGiaDAO();
    if(dstg==null) dstg = new ArrayList<>();
        dstg = data.docTG();
}
    public void docDSTGan(){
        TacGiaDAO data=new TacGiaDAO();
        if(dstgan==null) dstgan = new ArrayList<>();
        dstgan = data.docTGAN();
    }
     public void suaTG(int i,TacGiaDTO tg)
    {
        TacGiaDAO data=new TacGiaDAO();
        data.sua(tg);
        dstg.set(i, tg);
    }
      public boolean xoa(TacGiaDTO tg,int i)
    {
        TacGiaDAO tgdao =new TacGiaDAO();
        String Ma=dstg.get(i).getMaTG();
        if(tgdao.xoa(Ma)){
            dstg.remove(i);
            return true;
        }else{
            return false;
        }
        
    }
    public  ArrayList<TacGiaDTO> timkiem_matg(String matg)
    {
        ArrayList<TacGiaDTO> kq = new ArrayList<TacGiaDTO>();
        for(TacGiaDTO tg: dstg)
        {
            if(tg.getMaTG().toLowerCase().contains(matg.toLowerCase()))
                kq.add(tg);
        }
        return kq;
    }
    public ArrayList<TacGiaDTO> timkiem_matgan(String matg){
                ArrayList<TacGiaDTO> kq = new ArrayList<TacGiaDTO>();
        for(TacGiaDTO tg: dstgan)
        {
            if(tg.getMaTG().toLowerCase().contains(matg.toLowerCase()))
                kq.add(tg);
        }
        return kq;
    }
     public  ArrayList<TacGiaDTO> timkiem_tenTG(String tentg)
    {
        ArrayList<TacGiaDTO> kq = new ArrayList<TacGiaDTO>();
        for(TacGiaDTO tg: dstg)
        {
            if(tg.getTenTG().toLowerCase().contains(tentg.toLowerCase()))
                kq.add(tg);
        }
        return kq;
    }
     
     public  ArrayList<TacGiaDTO> timkiem_tenTGAn(String tentg)
    {
        ArrayList<TacGiaDTO> kq = new ArrayList<TacGiaDTO>();
        for(TacGiaDTO tg: dstgan)
        {
            if(tg.getTenTG().toLowerCase().contains(tentg.toLowerCase()))
                kq.add(tg);
        }
        return kq;
    }
     public   int kt_trung_ma (String matg)
    {
        for (TacGiaDTO tg: dstg)
        {
            if(tg.getMaTG().equals(matg))
                return 1;
        }
        //check torng ds ẩn

        for(TacGiaDTO tg: dstgan){
            if(tg.getMaTG().equals(matg))
                return 1;
        }
        
        return 0;
    }
  
    public void themTG(TacGiaDTO tg)
    {
        TacGiaDAO data = new TacGiaDAO();
        data.themtg(tg);//gọi hàm thêm bên DAO để thêm sách vào database
        dstg.add(tg);//
    }
}
