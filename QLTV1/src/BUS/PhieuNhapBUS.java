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
import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import static BUS.SanphamBUS.dsSanpham;
import DAO.CTHDNhapDAO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapBUS {

    public static ArrayList<PhieuNhapDTO> dspn, dspnan;
    private DefaultTableModel model;

    public PhieuNhapBUS() {
        dspn = docPhieuNhap();
        dspnan = docPhieuNhapan();
    }

    public ArrayList docPhieuNhap() {
        PhieuNhapDAO pn = new PhieuNhapDAO();
        return pn.docPN();
    }

    public ArrayList docPhieuNhapan() {
        PhieuNhapDAO pn = new PhieuNhapDAO();
        return pn.docPNan();
    }

    public void docDSPN() {
        PhieuNhapDAO data = new PhieuNhapDAO();
        if (dspn == null) {
            dspn = new ArrayList<>();
        }
        dspn = data.docPN();
    }

    public void docDSPNan() {
        PhieuNhapDAO data = new PhieuNhapDAO();
        if (dspnan == null) {
            dspnan = new ArrayList<>();
        }
        dspnan = data.docPNan();
    }
//     public void suaPN(int i,PhieuNhapDTO pn)
//    {
//        PhieuNhapDAO data=new PhieuNhapDAO();
//        data.sua(pn);
//        dspn.set(i, pn);
//    }

    public void xoaPN(PhieuNhapDTO pn, int i) {
        PhieuNhapDAO pndao = new PhieuNhapDAO();
        pndao.xoa(pn);
        dspn.remove(i);
    }

    public ArrayList<PhieuNhapDTO> timkiem_manhap(String manhap) {
        ArrayList<PhieuNhapDTO> kq = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO pn : dspn) {
            if (String.valueOf(pn.getMaPhieuNhap()).toLowerCase().contains(manhap.toLowerCase())) {
                kq.add(pn);
            }
        }
        return kq;
    }

    public ArrayList<PhieuNhapDTO> timkiem_manv(String ma) {
        ArrayList<PhieuNhapDTO> kq = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO pn : dspn) {
            if (pn.getMaNV().toLowerCase().contains(ma.toLowerCase())) {
                kq.add(pn);
            }
        }
        return kq;
    }

    public ArrayList<PhieuNhapDTO> timkiem_macc(String ma) {
        ArrayList<PhieuNhapDTO> kq = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO pn : dspn) {
            if (pn.getMaNCC().toLowerCase().contains(ma.toLowerCase())) {
                kq.add(pn);
            }
        }
        return kq;
    }

//     public   int kt_trung_ma (int maPN)
//    {
//        for (PhieuNhapDTO pn: dspn)
//        {
//            if(pn.getMaPhieuNhap()==maPN)
//                return 1;
//        }
//        return 0;
//    }
    public void themPN(PhieuNhapDTO pn) {
        PhieuNhapDAO data = new PhieuNhapDAO();
        data.thempn(pn);//gọi hàm thêm bên DAO để thêm sách vào database
        dspn.add(pn);//
    }

    public int getMa() {

        CTHDNhapDAO data = new CTHDNhapDAO();
        if (dspn == null) {
            dspn = new ArrayList();
            dspn = data.docCTHDNhap();
        }
        System.out.println("abc:" + (dspn.size() + 1));

        return dspn.size() + 1 + 9000000;
    }

    public void xoaPN(int i, PhieuNhapDTO pn) {
        PhieuNhapDAO data = new PhieuNhapDAO();
        data.xoa(pn);
        dspn.remove(pn);
    }
}
