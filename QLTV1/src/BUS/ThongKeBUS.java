/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.ThongKeDTO;
import java.util.ArrayList;
import DAO.ThongKeDAO;
/**
 *
 * @author Kieu Oanh
 */
public class ThongKeBUS {
    public static ArrayList<ThongKeDTO> dsTK;

    public ThongKeBUS() {
    }
    public void ThongKeNV(String fromDate, String toDate){
        ThongKeDAO data= new ThongKeDAO();
        if(dsTK == null)    dsTK = new ArrayList();
        dsTK = data.ThongKeNV(fromDate, toDate);
    }
    public void ThongKeKH(String fromDate, String toDate){
        ThongKeDAO data= new ThongKeDAO();
        if(dsTK == null)    dsTK = new ArrayList();
        dsTK = data.ThongKeKH(fromDate, toDate);
    }
    public void ThongKeSB(String fromDate, String toDate){
        ThongKeDAO data= new ThongKeDAO();
        if(dsTK == null)    dsTK = new ArrayList();
        dsTK = data.ThongKeSB(fromDate, toDate);
    }
    public void ThongKeSN(String fromDate, String toDate){
        ThongKeDAO data= new ThongKeDAO();
        if(dsTK == null)    dsTK = new ArrayList();
        dsTK = data.ThongKeSN(fromDate, toDate);
    }
    public void ThongKeSK(){
        ThongKeDAO data= new ThongKeDAO();
        if(dsTK == null)    dsTK = new ArrayList();
        dsTK = data.ThongKeSK();
    }
}
