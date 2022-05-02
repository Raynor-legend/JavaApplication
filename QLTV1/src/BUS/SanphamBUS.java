/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanphamDAO;
import DTO.NCCDTO;
import DTO.NXBDTO;
import DTO.SanphamDTO;
import DTO.SanphamDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import GUI.SanphamGUI;
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
public class SanphamBUS {

    public static ArrayList<SanphamDTO> dsSanpham;
    private DefaultTableModel model;


    public SanphamBUS() {
        dsSanpham = docSanpham();
    }
    public ArrayList docSanpham(){
        SanphamDAO data = new SanphamDAO();
        return data.docSanpham();
    }

    public ArrayList<SanphamDTO> getListSanpham() {
        if (dsSanpham == null) {
            dsSanpham = new ArrayList<>();
        }
        dsSanpham = new SanphamDAO().docSanpham();

        return dsSanpham;
    }

    public void docDSSanpham() {
        SanphamDAO data = new SanphamDAO();
        if (dsSanpham == null) {
            dsSanpham = new ArrayList<>();
        }
        dsSanpham = data.docSanpham();
    }


    public void themSanpham(SanphamDTO Sanpham) {
        SanphamDAO data = new SanphamDAO();
        data.them(Sanpham);//gọi hàm thêm bên DAO để thêm sách vào database
        dsSanpham.add(Sanpham);//
    }

    public void suaSanpham(int i, SanphamDTO Sanpham) {
        SanphamDAO data = new SanphamDAO();
        data.sua(Sanpham);
        dsSanpham.set(i, Sanpham);
    }
     public void sua1Sanpham(int i, SanphamDTO Sanpham) {
        SanphamDAO data = new SanphamDAO();
        data.sua1(Sanpham);
        dsSanpham.set(i-1, Sanpham);
    }

    public void xoa(SanphamDTO Sanpham, int i) {
        SanphamDAO Sanphamdao = new SanphamDAO();
        String Ma = dsSanpham.get(i).getMaSanpham();
        Sanphamdao.xoa(Ma);
        dsSanpham.remove(i);
    }

    public ArrayList<SanphamDTO> timkiem_maSanpham(String maSanpham) {
        ArrayList<SanphamDTO> kq = new ArrayList<SanphamDTO>();
        for (SanphamDTO Sanpham : dsSanpham) {
            if (Sanpham.getMaSanpham().toLowerCase().contains(maSanpham.toLowerCase())) {
                kq.add(Sanpham);
            }
        }
        return kq;
    }

    public ArrayList<SanphamDTO> timkiem_matl(String matl) {
        ArrayList<SanphamDTO> kq = new ArrayList<SanphamDTO>();
        for (SanphamDTO Sanpham : dsSanpham) {
            if (Sanpham.getMaTL().toLowerCase().contains(matl.toLowerCase())) {
                kq.add(Sanpham);
            }
        }
        return kq;
    }

    public ArrayList<SanphamDTO> timkiem_matacgia(String matg) {
        ArrayList<SanphamDTO> kq = new ArrayList<SanphamDTO>();
        for (SanphamDTO Sanpham : dsSanpham) {
            if (Sanpham.getMaTG().toLowerCase().contains(matg.toLowerCase())) {
                kq.add(Sanpham);
            }
        }
        return kq;
    }
    public ArrayList<SanphamDTO> timkiem_tenSanpham(String tenSanpham) {
        ArrayList<SanphamDTO> kq = new ArrayList<SanphamDTO>();
        for (SanphamDTO Sanpham : dsSanpham) {
            if (Sanpham.getTenSanpham().toLowerCase().contains(tenSanpham.toLowerCase())) {
                kq.add(Sanpham);
            }
        }
        return kq;
    }

    public int kt_trung_ma(String maSanpham) {
        for (SanphamDTO sanpham : dsSanpham) {
            if (sanpham.getMaSanpham().equals(maSanpham)) {
                return 1;
            }
        }
        return 0;
    }

  

}
