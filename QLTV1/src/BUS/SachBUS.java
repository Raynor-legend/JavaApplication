/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SachDAO;
import DTO.NCCDTO;
import DTO.NXBDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import GUI.SachGUI;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class SachBUS {

    public static ArrayList<SachDTO> dssach;
    private DefaultTableModel model;


    public SachBUS() {
        dssach = docsach();
    }
    public ArrayList docsach(){
        SachDAO data = new SachDAO();
        return data.docSach();
    }

    public ArrayList<SachDTO> getListSach() {
        if (dssach == null) {
            dssach = new ArrayList<>();
        }
        dssach = new SachDAO().docSach();

        return dssach;
    }

    public void docDSSACH() {
        SachDAO data = new SachDAO();
        if (dssach == null) {
            dssach = new ArrayList<>();
        }
        dssach = data.docSach();
    }


    public void themSach(SachDTO sach) {
        SachDAO data = new SachDAO();
        data.them(sach);//gọi hàm thêm bên DAO để thêm sách vào database
        dssach.add(sach);//
    }

    public void suaSach(int i, SachDTO sach) {
        SachDAO data = new SachDAO();
        data.sua(sach);
        dssach.set(i, sach);
    }
     public void sua1Sach(int i, SachDTO sach) {
        SachDAO data = new SachDAO();
        data.sua1(sach);
        dssach.set(i-1, sach);
    }

    public void xoa(SachDTO sach, int i) {
        SachDAO sachdao = new SachDAO();
        String Ma = dssach.get(i).getMaSach();
        sachdao.xoa(Ma);
        dssach.remove(i);
    }

    public ArrayList<SachDTO> timkiem_masach(String masach) {
        ArrayList<SachDTO> kq = new ArrayList<SachDTO>();
        for (SachDTO sach : dssach) {
            if (sach.getMaSach().toLowerCase().contains(masach.toLowerCase())) {
                kq.add(sach);
            }
        }
        return kq;
    }

    public ArrayList<SachDTO> timkiem_matl(String matl) {
        ArrayList<SachDTO> kq = new ArrayList<SachDTO>();
        for (SachDTO sach : dssach) {
            if (sach.getMaTL().toLowerCase().contains(matl.toLowerCase())) {
                kq.add(sach);
            }
        }
        return kq;
    }

    public ArrayList<SachDTO> timkiem_matacgia(String matg) {
        ArrayList<SachDTO> kq = new ArrayList<SachDTO>();
        for (SachDTO sach : dssach) {
            if (sach.getMaTG().toLowerCase().contains(matg.toLowerCase())) {
                kq.add(sach);
            }
        }
        return kq;
    }
    public ArrayList<SachDTO> timkiem_tensach(String tensach) {
        ArrayList<SachDTO> kq = new ArrayList<SachDTO>();
        for (SachDTO sach : dssach) {
            if (sach.getTenSach().toLowerCase().contains(tensach.toLowerCase())) {
                kq.add(sach);
            }
        }
        return kq;
    }

    public int kt_trung_ma(String masach) {
        for (SachDTO sach : dssach) {
            if (sach.getMaSach().equals(masach)) {
                return 1;
            }
        }
        return 0;
    }

  

}
