/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Asus
 */
public class TaiKhoanDTO {


    private String  Pass, MaNV;
    private String Quyen;
    public TaiKhoanDTO (){
        
    }
    public TaiKhoanDTO (String MaNV,String Pass,String Quyen){
        this.Pass= Pass;
        this.Quyen = Quyen;
        this.MaNV = MaNV;
    }
      
   
    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
}