package BUS;
import DAO.CTHDNhapDAO;
//import DAO.HoaDonDAO;
import DTO.CTHDNhapDTO;
//import DTO.Quyen;
import GUI.HoaDonGUI;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class CTHDNhapBUS {
        public static ArrayList<CTHDNhapDTO> dscthdNhap;              
    private DefaultTableModel model;
    public CTHDNhapBUS(){
        dscthdNhap = docctnhap();
    }
    public ArrayList docctnhap(){
        CTHDNhapDAO data = new CTHDNhapDAO();
        return data.docCTHDNhap();
    }
    public void docDSCTHD(){
        CTHDNhapDAO data= new CTHDNhapDAO();
        if(dscthdNhap == null)    dscthdNhap = new ArrayList();
        dscthdNhap = data.docCTHDNhap();
    }
     public void themCTHDNhap(CTHDNhapDTO ct){
        CTHDNhapDAO data = new CTHDNhapDAO();
        data.themcthdNhap(ct);
        dscthdNhap.add(ct);        
    }
     public int getSTT(){
         CTHDNhapDAO data = new CTHDNhapDAO();
         if(dscthdNhap == null){
             dscthdNhap = new ArrayList();
             dscthdNhap = data.docCTHDNhap();
         }
         System.out.println("abc:" + (dscthdNhap.size() +1) );
        
         
         return dscthdNhap.size() + 1;
     }

}    
