/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CTHDBUS;
import DTO.CTHDDTO;
import DTO.HoaDonDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.w3c.dom.events.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class CTHDGUI{
     DefaultTableModel model = new DefaultTableModel();
    
    public JTable tblCTHD;
    Vector header;
    JScrollPane scrollPanel;
    public static JPanel phienthi, QLCTHD;
     public CTHDGUI(){
        initComponents();
        CTHDBUS hd = new CTHDBUS();
        if(CTHDBUS.dscthd == null){
            hd.docDSCTHD();
        }
         Add_header();
        
    }
      private void Add_header() {
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sách");
        header.add("Đơn giá bán");
        header.add("Số lượng");
        header.add("Thành tiền");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }
      private void Add_row(CTHDDTO hd) {
        Vector row = new Vector();
        row.add(hd.getMaHD());
        row.add(hd.getMaSach());
        row.add(hd.getGiaBan());
        row.add(hd.getSoluong());
        row.add(hd.getThanhTien());
        model.addRow(row);
        tblCTHD.setModel(model);
    }
      public void initComponents(){
          JFrame f = new JFrame();
        f.setLayout(null);
        f.setBounds(0, 0, 1200, 800);
        f.setBackground(Color.decode("#78909C"));

        phienthi = TableCTHD();
        phienthi.setBounds(0, 250, 1200, 800);
        phienthi.setBackground(Color.decode("#78909C"));
        f.add(phienthi);
        f.setVisible(true);
     
        JLabel lb1 = new JLabel("CHI TIẾT HÓA ĐƠN");
        lb1.setBounds(100, 150, 400, 200);
    f.add(lb1);
    }
     public JPanel TableCTHD() {
      JPanel  ptablecthd = new JPanel();
        ptablecthd.setLayout(null);
        ptablecthd.setBackground(Color.decode("#FFCA28"));
        header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sách");
        header.add("Đơn giá bán");
        header.add("Số lượng");
        header.add("Thành tiền");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblCTHD = new JTable(null, header);
        tblCTHD.setBounds(0, 0, 1000, 300);

        tblCTHD.setFont(new Font("Arial", 0, 15));
        tblCTHD.setModel(model);//add model len table
        tblCTHD.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblCTHD.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblCTHD.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        tblCTHD.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblCTHD);
        scrollPanel.setBounds(0, 0, 1000, 300);
        ptablecthd.add(scrollPanel); // add table vào scrollPanel
        tblCTHD.setFillsViewportHeight(true);//hiển thị table
        return ptablecthd;
    }
     public static void main(String args[]) {
        CTHDGUI dn=new CTHDGUI();
     }
}
