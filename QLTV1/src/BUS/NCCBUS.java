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
import DAO.NCCDAO;
import DTO.NCCDTO;
import BUS.NCCBUS;
import static BUS.SachBUS.dssach;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NCCBUS {

    public static ArrayList<NCCDTO> dsncc;
    public static ArrayList<NCCDTO> dsnccan;
    private DefaultTableModel model;

    public NCCBUS() {
        dsncc = docNCC();
        dsnccan = docNCCAN();
    }

    public ArrayList docNCC() {
        NCCDAO data = new NCCDAO();
        return data.docNCC();
    }

    public void docDSNCC() {
        NCCDAO data = new NCCDAO();
        if (dsncc == null) {
            dsncc = new ArrayList<>();
        }
        dsncc = data.docNCC();
    }

    public ArrayList docNCCAN() {
        NCCDAO data = new NCCDAO();
        return data.docNCCAN();
    }

    public void docDSNCCAN() {
        NCCDAO data = new NCCDAO();
        if (dsnccan == null) {
            dsnccan = new ArrayList<>();
        }
        dsnccan = data.docNCCAN();
    }

    public void suaNCC(int i, NCCDTO ncc) {
        NCCDAO data = new NCCDAO();
        data.sua(ncc);
        dsncc.set(i, ncc);
    }

    public void xoa(NCCDTO ncc, int i) {
        NCCDAO nccdao = new NCCDAO();
        String Ma = dsncc.get(i).getMaNCC();
        nccdao.xoa(Ma);
        dsncc.remove(i);
    }

    public int kt_trung_ma(String mancc) {
        for (NCCDTO ncc : dsncc) {
            if (ncc.getMaNCC().equals(mancc)) {
                return 1;
            }
        }
        return 0;
    }

    public ArrayList<NCCDTO> timkiem_mancc(String mancc) {
        ArrayList<NCCDTO> kq = new ArrayList<NCCDTO>();
        for (NCCDTO ncc : dsncc) {
            if (ncc.getMaNCC().toLowerCase().contains(mancc.toLowerCase())) {
                kq.add(ncc);
            }
        }
        return kq;
    }

    public ArrayList<NCCDTO> timkiem_tenncc(String tenncc) {
        ArrayList<NCCDTO> kq = new ArrayList<NCCDTO>();
        for (NCCDTO ncc : dsncc) {
            if (ncc.getTenNCC().toLowerCase().contains(tenncc.toLowerCase())) {
                kq.add(ncc);
            }
        }
        return kq;
    }

    public ArrayList timncc(String tuTimKiem) {
        NCCDAO data = new NCCDAO();
        return data.timncc(tuTimKiem);
    }

    public void themNCC(NCCDTO ncc) {
        NCCDAO data = new NCCDAO();
        data.themncc(ncc);//gọi hàm thêm bên DAO để thêm sách vào database
        dsncc.add(ncc);//
    }
}
