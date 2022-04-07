/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Dell
 */
public class NhanVienDTO {

    private String MaNV;
    private String TenNV;
    private String HoNV;
    private String Ngaysinh;
    private String Gioitinh;
    private String CMND;
    private String Diachi;
    private String sdt;
    private String TrangThai;
   
    public NhanVienDTO(){
        
    }
    public NhanVienDTO(String MaNV,String TenNV,String HoNV,String Ngaysinh,String Gioitinh,String CMND,String Diachi,String sdt,String TrangThai){
        this.MaNV=MaNV;
        this.HoNV=HoNV;
        this.TenNV=TenNV;
        this.Ngaysinh=Ngaysinh;
        this.Gioitinh=Gioitinh;
        this.CMND=CMND;
        this.Diachi=Diachi;
        this.sdt=sdt;
        this.TrangThai=TrangThai;
   }
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getHoNV() {
        return HoNV;
    }

    public void setHoNV(String HoNV) {
        this.HoNV = HoNV;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    

  

}