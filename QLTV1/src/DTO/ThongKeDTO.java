/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Kieu Oanh
 */
public class ThongKeDTO {
    String ma, ten, soluong, giatri;
    public ThongKeDTO(){
        
    }
    
    public ThongKeDTO(String ma, String ten, String soluong, String giatri){
        this.ma = ma;
        this.ten = ten;
        this.soluong = soluong;
        this.giatri = giatri;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getSoluong() {
        return soluong;
    }

    public String getGiatri() {
        return giatri;
    }
    
    //

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }
    
    
    
}
