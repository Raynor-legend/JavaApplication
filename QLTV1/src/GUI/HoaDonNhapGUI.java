
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Dell
 */
import BUS.CTHDNhapBUS;
import BUS.PhieuNhapBUS;
import BUS.NhanVienBUS;
import BUS.KhachHangBUS;
import BUS.SanphamBUS;
import DAO.PhieuNhapDAO;
import DTO.CTHDNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanphamDTO;
import java.awt.*;
import static java.awt.Font.BOLD;
import java.awt.Frame;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HoaDonNhapGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modect = new DefaultTableModel();
    JButton[] button;
    public JTable tblPN, tblCT;
    public JTextField txMaNhap, txMaNCC, txMaNV, txNgayNhap, txTongtien;
    public JTextField txTimKiem;
    public JComboBox box1;
    public JPanel p3, p4;
    Vector header;
    JScrollPane scrollPanel;
    public static JPanel phienthi, QLPN;
    ArrayList<PhieuNhapDTO> dspn = new ArrayList<>();
    ArrayList<PhieuNhapDTO> kq;

    public HoaDonNhapGUI() {
        initComponent();
        DocDL();

    }

    public void DocDL() {
        PhieuNhapBUS bus = new PhieuNhapBUS();
        if (PhieuNhapBUS.dspn == null) {
            bus.docDSPN();
        }
        Add_header();
        for (PhieuNhapDTO pn : PhieuNhapBUS.dspn) {
            Add_row(pn);

        }
    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("M?? nh???p");
        header.add("M?? nh??n vi??n");
        header.add("M?? nh?? cung c???p");
        header.add("Ng??y nh???p n?????c u???ng");
        header.add("T???ng ti???n");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row(PhieuNhapDTO pn) {
        Vector row = new Vector();
        row.add(pn.getMaPhieuNhap());
        row.add(pn.getMaNV());
        row.add(pn.getMaNCC());
        row.add(pn.getNgayNhap());
        row.add(pn.getTongtien());

        model.addRow(row);
        tblPN.setModel(model);
    }

    private void Add_rowct(CTHDNhapDTO ctpn) {
        Vector row = new Vector();
        row.add(ctpn.getMaPhieuNhap());
        row.add(ctpn.getMaSanpham());
        row.add(ctpn.getSoLuong());
        row.add(ctpn.getSoLuong());
        row.add(ctpn.getThanhTien());

        modect.addRow(row);
        tblCT.setModel(modect);
    }

    public void initComponent() {
        addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, 1200, 800);
        setBackground(Color.decode("#78909C"));
//        setLocationRelativeTo(null);
//        setResizable(false);
//      
        phienthi = QuanLiPN();
        // phienthi.setBounds(300,0,1200,800);
        phienthi.setBounds(0, 0, 1200, 800);
        phienthi.setBackground(Color.decode("#78909C"));
        String[] timkiem = {"M?? nh???p", "M?? nh??n vi??n", "M?? nh?? cung c???p", "Ng??y nh???p", "T???ng ti???n"};
        box1 = new JComboBox(timkiem);
        box1.setBounds(550, 100, 120, 30);
        box1.setSelectedIndex(0);
        // box1.setSelectedIndex(2);
        //addMouseListener(this);
        //phienthi.add(btn);
        //  phienthi.add(txTimKiem);
        //    phienthi = Nhacungcap();
        phienthi.add(box1);
        JPanel p2 = CHUCNANG();
        p2.setBounds(650, 0, 700, 450);
        p2.setBackground(Color.decode("#78909C"));
        p3 = TabelNS();
        p3.setBounds(0, 450, 600, 250);//set v??? tr?? so v???i phienthi
        p3.setBackground(Color.decode("#78909C"));
        p4 = TabelCT();
        p4.setBounds(610, 450, 600, 250);
        p4.setBackground(Color.decode("#78909C"));
        phienthi.add(p2);
        phienthi.add(p3);
        phienthi.add(p4);
        //add(pmenu);
        add(phienthi);
        setVisible(true);
        tblPN.addMouseListener(this);

    }

    public JPanel QuanLiPN() {
        QLPN = new JPanel();
        QLPN.setLayout(null);
        JLabel[] label;
        label = new JLabel[6];
        JTextField[] textfield;
        String[] arrPN = {"T??m Ki???m", "M?? nh???p", "M?? nh??n vi??n", "M?? nh?? cung c???p", "Ng??y nh???p", "T???ng ti???n"};

        int toadoxLabel = 200, toadoyLabel = 100;
        int toadoxTextField = 340, toadoyTextField = 100;
        for (int i = 0; i < arrPN.length; i++) {
            label[i] = new JLabel(arrPN[i]); //kh???i t???o ?????i t?????ng thu???c Jbutton
            label[i].setBounds(toadoxLabel, toadoyLabel, 150, 30); //??inh v??? tr?? cho t???ng ?????i t?????ng button
            label[i].setHorizontalAlignment(JButton.LEFT); // canh ph???i cho text button
            label[i].setName("btn" + i);
            label[i].setBackground(Color.decode("#FFCA28"));
            label[i].setForeground(Color.WHITE);
            label[i].setFont(new Font("Arial", Font.BOLD, 15));
            // System.out.println(label[i]);
            QLPN.add(label[i]);
            toadoyLabel = toadoyLabel + 40;
            //txTimKiem.setBounds(200,220,200,30);

            switch (i) {
                case 0: {
                    txTimKiem = new JTextField();
                    txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLPN.add(txTimKiem);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 1: {
                    txMaNhap = new JTextField();
                    txMaNhap.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txMaNhap.setEnabled(false);
                    QLPN.add(txMaNhap);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 2: {
                    txMaNV = new JTextField();
                    txMaNV.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txMaNV.setEnabled(false);
                    QLPN.add(txMaNV);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 3: {
                    txMaNCC = new JTextField();
                    txMaNCC.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txMaNCC.setEnabled(false);
                    QLPN.add(txMaNCC);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }

                case 4: {
                    txNgayNhap = new JTextField();
                    txNgayNhap.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txNgayNhap.setEnabled(false);
                    QLPN.add(txNgayNhap);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 5: {
                    txTongtien = new JTextField();
                    txTongtien.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txTongtien.setEnabled(false);
                    QLPN.add(txTongtien);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
            }

        }
        return QLPN;
    }

    public JPanel CHUCNANG() {
        JPanel pchucnang = new JPanel();
        pchucnang.setLayout(null);
        button = new JButton[4];
        String[] arrchucnang = {"T??m Ki???m", "X??a", "C???p nh???t", "Xu???t excel"};
        // Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
        int toadoxButton = 145, toadoyButton = 100;
        for (int i = 0; i < arrchucnang.length; i++) {
            button[i] = new JButton(arrchucnang[i]); //kh???i t???o ?????i t?????ng thu???c Jbutton
            button[i].setBounds(toadoxButton, toadoyButton, 150, 30); //??inh v??? tr?? cho t???ng ?????i t?????ng button
            button[i].setHorizontalAlignment(JButton.CENTER); // canh ph???i cho text button
            button[i].setName("btn" + i);
            button[i].setBackground(Color.decode("#FFCA28"));
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            System.out.println(button[i]);
            pchucnang.add(button[i]);
            toadoyButton = toadoyButton + 70;
            button[i].addActionListener((ActionListener) this);
        }
        return pchucnang;

    }

    public JPanel TabelNS() {
        JPanel ptabletg = new JPanel();
        ptabletg.setLayout(null);
        ptabletg.setBackground(Color.decode("#FFCA28"));
        header = new Vector();
        header.add("M?? nh???p");
        header.add("M?? nh??n vi??n");
        header.add("M?? nh?? cung c???p");
        header.add("Ng??y nh???p");
        header.add("T???ng ti???n");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblPN = new JTable(null, header) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        tblPN.setBounds(0, 0, 600, 250);

        tblPN.setFont(new Font("Arial", 0, 15));
        tblPN.setModel(model);//add model len table
        tblPN.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblPN.getTableHeader().setForeground(Color.black); //set m??u ch??? cho header
        tblPN.getTableHeader().setPreferredSize(new Dimension(30, 50));//set ????? d??i ????? r???ng c???a header
        tblPN.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblPN);
        scrollPanel.setBounds(0, 0, 600, 250);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        tblPN.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setPreferredSize(new Dimension(500, 500));

        ptabletg.add(scrollPanel); // add table v??o scrollPanel
        tblPN.setFillsViewportHeight(true);//hi???n th??? table

        return ptabletg;

    }

    public JPanel TabelCT() {
        JPanel ptablect = new JPanel();
        ptablect.setLayout(null);
        ptablect.setBackground(Color.decode("#FFCA28"));
        header = new Vector();
        header.add("M?? nh???p ");
        header.add("M?? n?????c u???ng");
        header.add("????n gi?? nh???p");
        header.add("S??? l?????ng");
        header.add("Th??nh ti???n");
        if (modect.getRowCount() == 0) {
            modect = new DefaultTableModel(header, 0);
        }

        tblCT = new JTable(null, header) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        tblCT.setBounds(0, 0, 600, 300);

        tblCT.setFont(new Font("Arial", 0, 15));
        tblCT.setModel(modect);//add model len table
        tblCT.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblCT.getTableHeader().setForeground(Color.black); //set m??u ch??? cho header
        tblCT.getTableHeader().setPreferredSize(new Dimension(30, 50));//set ????? d??i ????? r???ng c???a header
        tblCT.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblCT);
        scrollPanel.setBounds(0, 0, 600, 300);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        tblCT.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setPreferredSize(new Dimension(500, 500));

        ptablect.add(scrollPanel); // add table v??o scrollPanel
        tblCT.setFillsViewportHeight(true);//hi???n th??? table

        return ptablect;
    }

    private boolean kiemtra() {
        if (txMaNhap.getText().equals("") || txMaNCC.getText().equals("") || txMaNV.getText().equals("") || txNgayNhap.getText().equals("")) {
            return false;
        }
        return true;
    }

    private void checkempty() {
        if (txMaNhap.getText().equals("")) {
            txMaNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txMaNCC.getText().equals("")) {
            txMaNCC.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txMaNV.getText().equals("")) {
            txMaNV.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txNgayNhap.getText().equals("")) {
            txNgayNhap.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    private void checkfilled() {
        if (!txMaNhap.getText().equals("")) {
            txMaNhap.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txMaNCC.getText().equals("")) {
            txMaNCC.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txMaNV.getText().equals("")) {
            txMaNV.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txNgayNhap.getText().equals("")) {
            txNgayNhap.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private void btXoaMouseClicked() {
        PhieuNhapDTO hoaDon = new PhieuNhapDTO();
        PhieuNhapBUS bus = new PhieuNhapBUS();
        int row = tblPN.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui l??ng ch???n 1 h??a ????n mu???n x??a");
        } else {
            if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n xo?? kh??ng", "Th??ng b??o",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                tblPN.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("123");

                    }
                });
                hoaDon.setMaPhieuNhap(Integer.parseInt(tblPN.getValueAt(row, 0).toString()));
                //int maHD = Integer.parseInt(tblQLHD.getValueAt(row, 0).toString());

                CTHDNhapBUS cthd = new CTHDNhapBUS();
                SanphamBUS SanphamBUS = new SanphamBUS();
                if (CTHDNhapBUS.dscthdNhap == null) {
                    cthd.docDSCTHD();
                }
                if (SanphamBUS.dsSanpham == null) {
                    SanphamBUS.docDSSanpham();
                }
                int slCTHD = 0;
                int slSanpham = 0;
                int i = 0;
                //Add_headerct();
                for (CTHDNhapDTO cthdDTO : CTHDNhapBUS.dscthdNhap) {

                    if (cthdDTO.getMaPhieuNhap()== hoaDon.getMaPhieuNhap()) {
                        System.out.println("MaHD" + cthdDTO.getMaPhieuNhap()+ "H??a ????n" + hoaDon.getMaPhieuNhap());
                        slCTHD = Integer.parseInt(cthdDTO.getSoLuong());
                        for (SanphamDTO SanphamDTO : SanphamBUS.dsSanpham) {
                            i++;
                            System.out.println("CTHD" + cthdDTO.getMaSanpham() + "Sanpham" + SanphamDTO.getMaSanpham());
                            slSanpham = Integer.parseInt(SanphamDTO.getSoLuong().toString())-slCTHD;
                            if (cthdDTO.getMaSanpham().equals(SanphamDTO.getMaSanpham())) {

                                SanphamDTO.setSoLuong(String.valueOf(slSanpham));
                                SanphamBUS.sua1Sanpham(i - 1, SanphamDTO);
                            }
                        }
                        i = 0;
                    }

                }
                bus.xoaPN(row, hoaDon);
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "X??a th??nh c??ng");
            }
        }
    }

    
//    private void bttimkiemMouseClicked() {
//        System.out.println("t???i r???i n??");
//        String selectedItem = (String) box1.getSelectedItem();
//        String tukhoa = txTimKiem.getText();
//        if (tukhoa.equals("")) {
//            JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y k???t qu???");
//        } else {
//            if (selectedItem.equals("M?? Nh???p")) {
//                PhieuNhapBUS bus = new PhieuNhapBUS();
//                Add_header();
//                ArrayList<PhieuNhapDTO> kq = bus.timkiem_manhap(tukhoa);
//                DefaultTableModel mode = new DefaultTableModel(header, 0);
//                if (kq.size() != 0) {
//                    for (PhieuNhapDTO pn : kq) {
//                        Vector row = new Vector();
//                        row.add(pn.getMaPhieuNhap());
//                        row.add(pn.getMaNV());
//                        row.add(pn.getMaNCC());
//                        row.add(pn.getNgayNhap());
//                        row.add(pn.getTongtien());
//
//                        mode.addRow(row);
//                        //
//                    }
//                    tblPN.setModel(mode);
//                    tblPN = new JTable(null, header);
//                    tblPN.setBounds(0, 0, 1000, 300);
//
//                    tblPN.setFont(new Font("Arial", 0, 15));
//                    tblPN.setModel(mode);//add model len table
//                    tblPN.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
//                    tblPN.getTableHeader().setForeground(Color.decode("#FFCA28")); //set m??u ch??? cho header
//                    tblPN.getTableHeader().setPreferredSize(new Dimension(30, 50));//set ????? d??i ????? r???ng c???a header
//                    tblPN.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header
//
//                    scrollPanel = new JScrollPane(tblPN);
//                    scrollPanel.setBounds(0, 0, 1000, 300);
//
//                    scrollPanel.invalidate();
//                    scrollPanel.validate();
//                    scrollPanel.repaint();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y");
//                }
//            }
//        }
//    }

    private void CHON(String conditions, String text) {
        PhieuNhapBUS bus = new PhieuNhapBUS();
        switch (conditions) {
            case "M?? nh??n vi??n":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_manv(text);
                Search();
                break;

            case "M?? nh?? cung c???p":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_macc(text);
                Search();
                break;

        }
    }

    private void Search() {
        DefaultTableModel mode = new DefaultTableModel(header, 0);
        if (kq.size() != 0) {
            for (PhieuNhapDTO pn : kq) {
                Vector row = new Vector();
                row.add(pn.getMaPhieuNhap());
                row.add(pn.getMaNV());
                row.add(pn.getMaNCC());
                mode.addRow(row);
            }
            tblPN.setModel(mode);

        }

    }

    private void bttimkiemMouseClicked() {
        String selectedItem = (String) box1.getSelectedItem();
        String tukhoa = txTimKiem.getText();
        if (tukhoa.equals("")) {
            btcapnhatMouseClicked();
        } else {
            CHON(selectedItem, tukhoa);
        }
    }

    public void btcapnhatMouseClicked() {
        PhieuNhapBUS bus = new PhieuNhapBUS();
        dspn = bus.docPhieuNhap();
        model.setRowCount(0);
        for (PhieuNhapDTO pn : dspn) {
            model.addRow(new Object[]{pn.getMaPhieuNhap(), pn.getMaNV(), pn.getMaNCC(), pn.getNgayNhap(), pn.getTongtien()});
        }
        tblPN.setModel(model);
//        p3.remove(tblPN);
//        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }

    public void btXuatExcelMouseClicked() {
        JFileChooser file = new JFileChooser(); //Kh???i t???o JFileChooser
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Danh s??ch nh???p n?????c u???ng");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = excelSheet.createRow((short) 1);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH S??CH PHI???U NH???P N?????C U???NG");

            row = excelSheet.createRow((short) 2);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("M?? nh???p");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("M?? nh??n vi??n");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("M?? nh?? cung c???p");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ng??y nh???p");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("T???ng ti???n");
            cell = row.createCell(5, CellType.STRING);

            for (int i = 0; i < tblPN.getRowCount(); i++) {
                row = excelSheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                for (int j = 0; j < tblPN.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(tblPN.getValueAt(i, j).toString());
                }
            }

            FileOutputStream excelFOS;
            BufferedOutputStream excelBOS;
            try {
                excelFOS = new FileOutputStream(new File(file.getSelectedFile() + ".xls"));
                excelBOS = new BufferedOutputStream(excelFOS);
                excelWorkbook.write(excelBOS);
                JOptionPane.showMessageDialog(null, "Xu???t file excel th??nh c??ng!");
                excelBOS.close();
                excelWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//      public static void main(String[] args) {
//        TacGiaGUI admintg = new TacGiaGUI();
//        admintg.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        admintg.setLocationRelativeTo(admintg);
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        if (src.equals("X??a")) {
            btXoaMouseClicked();
        }
//      
        if (src.equals("T??m Ki???m")) {
            bttimkiemMouseClicked();
        }
        if (src.equals("C???p nh???t")) {
            btcapnhatMouseClicked();
        }
        if (src.equals("Xu???t excel")) {
            btXuatExcelMouseClicked();
        }
//        if(e.getSource()==button[1]){
//            btXoaMouseClicked();
//            int i=tblQLS.getSelectedRow();
//           dsSanpham.remove(i);
//            
//        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource().equals(tblPN)) {
            int row = tblPN.getSelectedRow();

            // if (row >= 0) {
            txMaNhap.setText(tblPN.getValueAt(row, 0).toString());
            txMaNhap.setEnabled(false);
            txMaNV.setText(tblPN.getValueAt(row, 1).toString());
            txMaNCC.setText(tblPN.getValueAt(row, 2).toString());
            txNgayNhap.setText(tblPN.getValueAt(row, 3).toString());
            txTongtien.setText(tblPN.getValueAt(row, 4).toString());
            //txtThanhTien.setText(tblQLHD.getValueAt(row, 4).toString());

            modect.setRowCount(0);
            int maHD = Integer.parseInt(tblPN.getValueAt(row, 0).toString());

            CTHDNhapBUS cthd = new CTHDNhapBUS();

            if (CTHDNhapBUS.dscthdNhap == null) {
                cthd.docDSCTHD();
            }
            //Add_headerct();
            for (CTHDNhapDTO cthdDTO : CTHDNhapBUS.dscthdNhap) {

                if (cthdDTO.getMaPhieuNhap() == maHD) {
                    Add_rowct(cthdDTO);
                    System.out.println("baby ???i");
                }

            }
            //

        }
    }


    /* public void btnSuaActionPerformed(ActionEvent e){
        int i=tblQLS.getSelectedRow();
        if(i>=0)
        {
            SanphamDTO Sanpham=new SanphamDTO();
            Sanpham.MaSanpham=txMaSanpham.getText();
            Sanpham.TenSanpham=txTenSanpham.getText();
            Sanpham.MaTL=txMaTL.getText();
            Sanpham.MaTG=txMaTG.getText();
            Sanpham.MaNXB=txMaNXB.getText();
            Sanpham.SoLuong=Integer.parseInt(txSoluong.getText());
            Sanpham.DonGia=Integer.parseInt(txDongiaban.getText());
            
            SanphamDTO old=dsSanpham.set(i,Sanpham);
            model.setValueAt(Sanpham.MaSanpham, i,0);
            model.setValueAt(Sanpham.TenSanpham, i,1);
            model.setValueAt(Sanpham.MaTL, i,2);
            model.setValueAt(Sanpham.MaTG, i,3);
            model.setValueAt(Sanpham.MaNXB, i,4);
            model.setValueAt(Sanpham.SoLuong, i,6);
            model.setValueAt(Sanpham.DonGia, i,7);
            tblQLS.setModel(model);
            
            
        }
    }*/
    //@Override
    //public void mouseClicked(java.awt.event.MouseEvent e) {
    // System.out.println("3333333333333");
    //}
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
