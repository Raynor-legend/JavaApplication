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
import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;
import static BUS.SanphamBUS.dsSanpham;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class TheLoaiBUS {
    public static ArrayList<TheLoaiDTO> dstl;
    private DefaultTableModel model;
    public TheLoaiBUS(){
    JTextField txMaTL;
    JTextField txTenTL;
    JTable tbTL    ;
    
}
    public ArrayList docTheLoai(){
        TheLoaiDAO data= new TheLoaiDAO();
        return data.docTheLoai();
    }
    
    public void docDSTL(){
    TheLoaiDAO data= new TheLoaiDAO();
    if(dstl==null) dstl = new ArrayList<>();
        dstl = data.docTheLoai();
}
     public void suaTL(int i,TheLoaiDTO tl)
    {
        TheLoaiDAO data=new TheLoaiDAO();
        data.sua(tl);
        dstl.set(i, tl);
    }
      public void xoa(TheLoaiDTO tl,int i)
    {
        TheLoaiDAO tldao =new TheLoaiDAO();
        String Ma=dstl.get(i).getMaTL();
        tldao.xoa(Ma);
        dstl.remove(i);
    }
      public   int kt_trung_ma (String matl)
    {
        for (TheLoaiDTO tl: dstl)
        {
            if(tl.getMaTL().equals(matl))
                return 1;
        }
        return 0;
    }
    public  ArrayList<TheLoaiDTO> timkiem_matl(String matl)
    {
        ArrayList<TheLoaiDTO> kq = new ArrayList<TheLoaiDTO>();
        for(TheLoaiDTO tl: dstl)
        {
            if(tl.getMaTL().toLowerCase().contains(matl.toLowerCase()))
                kq.add(tl);
        }
        return kq;
    }
    public ArrayList<TheLoaiDTO> timkiem_tentheloai(String tenTL){
        ArrayList<TheLoaiDTO> kq =new ArrayList<TheLoaiDTO>();
        for(TheLoaiDTO tl:dstl){
            if(tl.getTenTL().toLowerCase().contains(tenTL.toLowerCase()))
                kq.add(tl);
        }
        return kq;
    }
    public void themTL(TheLoaiDTO tl)
    {
        TheLoaiDAO data = new TheLoaiDAO();
        data.themtl(tl);//gọi hàm thêm bên DAO để thêm sách vào database
        dstl.add(tl);//
    }
}