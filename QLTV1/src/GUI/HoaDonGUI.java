/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CTHDBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SachBUS;
import DTO.CTHDDTO;
import DTO.HoaDonDTO;
import DTO.KhachHang;
import DTO.NhanVienDTO;
import DTO.SachDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
//import org.w3c.dom.events.MouseEvent;
//import org.w3c.dom.views.AbstractView;  

/**
 *
 * @author Asus
 */
public class HoaDonGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel modelHD = new DefaultTableModel();
    DefaultTableModel modelct = new DefaultTableModel();
    JButton[] button;
    public JTable tblQLHD, tblCT;
    public JTextField txtMaHD, txtNgayLap, txtThanhTien;
    public JTextField txTimKiem;
    public JComboBox box1, cbbkhachhang, cbbnhanvien;
    public JPanel p3, p4;
    Vector header;
    Vector headerct;
    JScrollPane scrollPanel;
    public static JPanel phienthi, QLHD;
    ArrayList<HoaDonDTO> dshd = new ArrayList<>();
    ArrayList<HoaDonDTO> kq;

    public HoaDonGUI() {
        initComponents();
        HoaDonBUS hd = new HoaDonBUS();
        if (HoaDonBUS.dshd == null) {
            hd.docDSHD();
        }
        Add_headerHD();
        for (HoaDonDTO hoadon : HoaDonBUS.dshd) {
            Add_rowHD(hoadon);
        }
//         CTHDBUS ct = new CTHDBUS();
//        if (CTHDBUS.dscthd == null) {
//            ct.docDSCTHD();
//        }
//        Add_headerct();
//        for (CTHDDTO cthd : CTHDBUS.dscthd) {
//            Add_rowct(cthd);
//        }

    }

    public void initComponents() {
        addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, 1000, 1000);
        setBackground(Color.decode("#78909C"));

        phienthi = QLHD();
        phienthi.setBounds(50, 50, 1200, 800);
        phienthi.setBackground(Color.decode("#78909C"));
        String[] timkiem = {"Mã nhân viên", "Mã khách hàng"};
        box1 = new JComboBox(timkiem);
        box1.setBounds(420, 80, 120, 30);
        box1.setSelectedIndex(0);

        phienthi.add(box1);
        JPanel p2 = CHUCNANG();
        // p2.setBounds(550, 0, 700, 450);
        p2.setBounds(600, 0, 700, 450);
        p2.setBackground(Color.decode("#78909C"));
        p3 = TableHD();
        p3.setBounds(0, 500, 600, 300);//set vị trí so với phienthi
        p3.setBackground(Color.decode("#78909C"));
        p4 = TableCT();
        p4.setBounds(610, 500, 600, 600);
        p4.setBackground(Color.decode("#78909C"));
        phienthi.add(p2);
        phienthi.add(p3);
        phienthi.add(p4);
        add(phienthi);
        setVisible(true);
        tblQLHD.addMouseListener(this);

    }

    public JPanel QLHD() {
        QLHD = new JPanel();
        QLHD.setLayout(null);
        JLabel[] label;
        label = new JLabel[9];
        JTextField[] textfield;
        // SachGUI a=new SachGUI();
        //a.tableMouseClicked(e);
        String[] arrHD = {"Tìm kiếm", "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập", "Tổng tiền"};

        int toadoxLabel = 100, toadoyLabel = 80;
        int toadoxTextField = 200, toadoyTextField = 80;
        for (int i = 0; i < arrHD.length; i++) {
            label[i] = new JLabel(arrHD[i]); //hdỡi tạo đối tượng thuộc Jbutton
            label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); //đinh vị trí cho từng đối tượng button
            label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
            label[i].setName("btn" + i);
            label[i].setBackground(Color.decode("#78909C"));
            label[i].setForeground(Color.WHITE);
            label[i].setFont(new Font("Arial", Font.BOLD, 15));
            System.out.println(label[i]);
            QLHD.add(label[i]);
            toadoyLabel = toadoyLabel + 40;
            switch (i) {
                case 0: {
                    txTimKiem = new JTextField();
                    txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);

                    QLHD.add(txTimKiem);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 1: {
                    txtMaHD = new JTextField();
                    txtMaHD.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txtMaHD.setEnabled(false);
                    QLHD.add(txtMaHD);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 2: {
                    KhachHangBUS kh = new KhachHangBUS();
                    ArrayList<KhachHang> arrkh = new ArrayList<KhachHang>();
                    arrkh = kh.dockhachhang();
                    cbbkhachhang = new JComboBox();
                    for (int k = 0; k < arrkh.size(); k++) {
                        cbbkhachhang.addItem(arrkh.get(k).getMaKH());
                    }
                    cbbkhachhang.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    //cbbkhachhang.setEditable(false);
                    cbbkhachhang.setEnabled(false);
                    QLHD.add(cbbkhachhang);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 3: {
                    NhanVienBUS nv = new NhanVienBUS();
                    ArrayList<NhanVienDTO> arrnv = new ArrayList<NhanVienDTO>();
                    arrnv = nv.docnv();
                    cbbnhanvien = new JComboBox();
                    for (int k = 0; k < arrnv.size(); k++) {
                        cbbnhanvien.addItem(arrnv.get(k).getMaNV());
                    }
                    cbbnhanvien.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    //cbbnhanvien.setEditable(false);
                    cbbnhanvien.setEnabled(false);
                    QLHD.add(cbbnhanvien);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 4: {
                    txtNgayLap = new JTextField();
                    txtNgayLap.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txtNgayLap.setEnabled(false);
                    QLHD.add(txtNgayLap);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 5: {
                    txtThanhTien = new JTextField();
                    txtThanhTien.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txtThanhTien.setEnabled(false);
                    QLHD.add(txtThanhTien);

                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
            }
        }
        return QLHD;
    }

    public JPanel CHUCNANG() {
        JPanel pchucnang = new JPanel();
        pchucnang.setLayout(null);

        // pchucnang.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton bttimkiem = new JButton();

        button = new JButton[10];
        String[] arrchucnang = {"Tìm kiếm", "Xóa", "Cập nhật", "Xuất excel"};
        // Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
        int toadoxButton = 50, toadoyButton = 80;
        for (int i = 0; i < arrchucnang.length; i++) {
//            if (i == 1) {
//                toadoxButton = 240;
//                toadoyButton = 80;
//            }
            button[i] = new JButton(arrchucnang[i]); //hdỡi tạo đối tượng thuộc Jbutton
            button[i].setBounds(toadoxButton, toadoyButton, 150, 30); //đinh vị trí cho từng đối tượng button
            button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
            button[i].setName("btn" + i);
            button[i].setBackground(Color.decode("#FFCA28"));
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            pchucnang.add(button[i]);
            toadoyButton = toadoyButton + 70;
            button[i].addActionListener((ActionListener) this);
        }
        return pchucnang;

    }

    public JPanel TableHD() {
        JPanel ptablehd = new JPanel();
        ptablehd.setLayout(null);
        ptablehd.setBackground(Color.decode("#FFCA28"));
        header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã khách hàng");
        header.add("Mã nhân viên");
        header.add("Ngày lập");
        header.add("Tổng tiền");
        if (modelHD.getRowCount() == 0) {
            modelHD = new DefaultTableModel(header, 0);
        }
        tblQLHD = new JTable(null, header);
        

        tblQLHD.setFont(new Font("Arial", 0, 15));
        tblQLHD.setModel(modelHD);//add model len table
        tblQLHD.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblQLHD.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblQLHD.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        tblQLHD.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblQLHD);
        scrollPanel.setBounds(0, 0, 600, 300);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        tblQLHD.setPreferredSize(new Dimension(500, 500));
        ptablehd.add(scrollPanel); // add table vào scrollPanel
        tblQLHD.setFillsViewportHeight(true);//hiển thị table

        return ptablehd;

    }

    public JPanel TableCT() {
        JPanel ptablect = new JPanel();
        
        ptablect.setLayout(null);
        ptablect.setBackground(Color.decode("#FFCA28"));
        headerct = new Vector();
        headerct.add("Mã hóa đơn");
        headerct.add("Mã sách");
        headerct.add("Đơn giá bán");
        headerct.add("Số lượng");
        headerct.add("Thành tiền");
        if (modelct.getRowCount() == 0) {
            modelct = new DefaultTableModel(headerct, 0);
        }
        tblCT = new JTable(null, headerct);
        

        tblCT.setFont(new Font("Arial", 0, 15));
        tblCT.setModel(modelct);//add model len table
        tblCT.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblCT.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblCT.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        tblCT.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblCT);
        scrollPanel.setBounds(0, 0, 450, 300);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        tblCT.setPreferredSize(new Dimension(500, 500));
        ptablect.add(scrollPanel); // add table vào scrollPanel
        tblCT.setFillsViewportHeight(true);//hiển thị table

        return ptablect;
    }

    private void Add_headerct() {
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sách");
        header.add("Đơn giá bán");
        header.add("Số lượng");
        header.add("Thành tiền");
        if (modelct.getRowCount() == 0) {
            modelct = new DefaultTableModel(header, 0);
        }
    }

    private void Add_rowct(CTHDDTO hd) {
        Vector row = new Vector();
        row.add(hd.getMaHD());
        row.add(hd.getMaSach());
        row.add(hd.getGiaBan());
        row.add(hd.getSoluong());
        row.add(hd.getThanhTien());
        modelct.addRow(row);
        tblCT.setModel(modelct);
    }

    private void Add_headerHD() {
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã khách hàng");
        header.add("Mã nhân viên");
        header.add("Ngày lập");
        header.add("Tổng tiền");
        if (modelHD.getRowCount() == 0) {
            modelHD = new DefaultTableModel(header, 0);
        }
    }

    private void Add_rowHD(HoaDonDTO hd) {
        Vector row = new Vector();
        row.add(hd.getMaHD());
        row.add(hd.getMaKH());
        row.add(hd.getMaNV());
        row.add(hd.getNgayLap());
        row.add(hd.getThanhTien());
        modelHD.addRow(row);
        tblQLHD.setModel(modelHD);
    }

    private void btXoaMouseClicked() {
        HoaDonDTO hoaDon = new HoaDonDTO();
        HoaDonBUS bus = new HoaDonBUS();
        int row = tblQLHD.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hóa đơn muốn xóa");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                tblQLHD.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("123");

                    }
                });
                hoaDon.setMaHD(Integer.parseInt(tblQLHD.getValueAt(row, 0).toString()));
                //int maHD = Integer.parseInt(tblQLHD.getValueAt(row, 0).toString());

                CTHDBUS cthd = new CTHDBUS();
                SachBUS sachBUS = new SachBUS();
                if (CTHDBUS.dscthd == null) {
                    cthd.docDSCTHD();
                }
                if (sachBUS.dssach == null) {
                    sachBUS.docDSSACH();
                }
                int slCTHD = 0;
                int slSach = 0;
                int i = 0;
                //Add_headerct();
                for (CTHDDTO cthdDTO : CTHDBUS.dscthd) {

                    if (cthdDTO.getMaHD() == hoaDon.getMaHD()) {
                        System.out.println("MaHD" + cthdDTO.getMaHD() + "Hóa đơn" + hoaDon.getMaHD());
                        slCTHD = Integer.parseInt(cthdDTO.getSoluong());
                        for (SachDTO sachDTO : sachBUS.dssach) {
                            i++;
                            System.out.println("CTHD" + cthdDTO.getMaSach() + "Sach" + sachDTO.getMaSach());
                            slSach = slCTHD + Integer.parseInt(sachDTO.getSoLuong().toString());
                            if (cthdDTO.getMaSach().equals(sachDTO.getMaSach())) {

                                sachDTO.setSoLuong(String.valueOf(slSach));
                                sachBUS.sua1Sach(i - 1, sachDTO);
                            }
                        }
                        i = 0;
                    }

                }
                bus.xoaHD(row, hoaDon);
                modelHD.removeRow(row);
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
        }
    }

    private void btThemMouseClicked() {
        HoaDonDTO hd = new HoaDonDTO();
        HoaDonBUS bus = new HoaDonBUS();
        hd.setMaHD(Integer.parseInt(txtMaHD.getText()));
        hd.setMaKH(cbbkhachhang.getSelectedItem().toString());
        hd.setMaNV(cbbnhanvien.getSelectedItem().toString());
        //hd.setNgayLap(txtNgayLap.getText());
        hd.setThanhTien(txtThanhTien.getText());

        //Add_row(sach);
        bus.themHD(hd);//thêm sách bên BUS đã có thêm vào database
        Add_headerHD();
        Add_rowHD(hd);

    }

    private void CHON(String conditions, String text) {
        HoaDonBUS bus = new HoaDonBUS();
        switch (conditions) {
            case "Mã nhân viên":
                Add_headerHD();
                modelHD.setRowCount(0);
                kq = bus.timkiem_manv(text);
                Search();
                break;

            case "Mã khách hàng":
                Add_headerHD();
                modelHD.setRowCount(0);
                kq = bus.timkiem_makh(text);
                Search();
                break;

        }
    }

    private void Search() {
        DefaultTableModel mode = new DefaultTableModel(header, 0);
        if (kq.size() != 0) {
            for (HoaDonDTO hd : kq) {
                Vector row = new Vector();
                row.add(hd.getMaHD());
                row.add(hd.getMaKH());
                row.add(hd.getMaNV());
                row.add(hd.getNgayLap());
                row.add(hd.getThanhTien());
                mode.addRow(row);
            }
            tblQLHD.setModel(mode);

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

//    private void bttimkiemMouseClicked() {
//        System.out.println("tới rồi nè");
//        String selectedItem = (String) box1.getSelectedItem();
//        String tukhoa = txTimKiem.getText();
//        if (tukhoa.equals("")) {
//            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
//        } else {
//            if (selectedItem.equals("Mã hóa đơn")) {
//                HoaDonBUS bus = new HoaDonBUS();
//                Add_headerHD();
//                //ArrayList<HoaDonDTO> kq = bus.timkiem_mahd(tukhoa);
//                DefaultTableModel mode = new DefaultTableModel(header, 0);
//                if (kq.size() != 0) {
//                    for (HoaDonDTO hd : kq) {
//                        Vector row = new Vector();
//                        row.add(hd.getMaHD());
//                        row.add(hd.getMaKH());
//                        row.add(hd.getMaNV());
//                        row.add(hd.getNgayLap());
//                        row.add(hd.getThanhTien());
//                        mode.addRow(row);
//                        //
//                    }
//                    tblQLHD.setModel(mode);
//                    tblQLHD = new JTable(null, header);
//                    tblQLHD.setBounds(0, 0, 1000, 300);
//
//                    tblQLHD.setFont(new Font("Arial", 0, 15));
//                    tblQLHD.setModel(mode);//add model len table
//                    tblQLHD.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
//                    tblQLHD.getTableHeader().setForeground(Color.decode("#FFCA28")); //set màu chữ cho header
//                    tblQLHD.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
//                    tblQLHD.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header
//
//                    scrollPanel = new JScrollPane(tblQLHD);
//                    scrollPanel.setBounds(0, 0, 1000, 300);
//
//                    scrollPanel.invalidate();
//                    scrollPanel.validate();
//                    scrollPanel.repaint();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Không tìm thấy");
//                }
//            }
//        }
//    }
//    public void bthienthiMouseClicked() {
//        System.out.println("chạy đén đây chưa bạn");
//
//    }
    public void btcapnhatMouseClicked() {
        HoaDonBUS bus = new HoaDonBUS();
        dshd = bus.dochd();
        modelHD.setRowCount(0);
        for (HoaDonDTO hd : dshd) {
            modelHD.addRow(new Object[]{hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getNgayLap(), hd.getThanhTien()});
        }
        tblQLHD.setModel(modelHD);
        //       p3.remove(tblQLHD);
//        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }

    public void btXuatExcelMouseClicked() {
        JFileChooser file = new JFileChooser(); //Khởi tạo JFileChooser

        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách hóa đơn");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = excelSheet.createRow((short) 1);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH HÓA ĐƠN");

            row = excelSheet.createRow((short) 2);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã HD");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã KH");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày lập");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Thành tiền");
            cell = row.createCell(5, CellType.STRING);
            for (int i = 0; i < tblQLHD.getRowCount(); i++) {
                row = excelSheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                for (int j = 0; j < tblQLHD.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(tblQLHD.getValueAt(i, j).toString());
                }
            }
            FileOutputStream excelFOS;
            BufferedOutputStream excelBOS;
            try {
                excelFOS = new FileOutputStream(new File(file.getSelectedFile() + ".xls"));
                excelBOS = new BufferedOutputStream(excelFOS);
                excelWorkbook.write(excelBOS);
                JOptionPane.showMessageDialog(null, "In thành công!");
                excelBOS.close();
                excelWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Thêm")) {
            btThemMouseClicked();
        }
        if (src.equals("Tìm kiếm")) {
            bttimkiemMouseClicked();
        }
//        if (src.equals("Hiển thị")) {
//            bthienthiMouseClicked();
//        }
        if (src.equals("Cập nhật")) {
            btcapnhatMouseClicked();
        }
        if (src.equals("Xuất Excel")) {
            btXuatExcelMouseClicked();
        }
        if (src.equals("Xóa")) {
            btXoaMouseClicked();
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource().equals(tblQLHD)) {
            int row = tblQLHD.getSelectedRow();

            //if (row >= 0) {
            txtMaHD.setText(tblQLHD.getValueAt(row, 0).toString());
            txtMaHD.setEnabled(false);
            cbbkhachhang.setSelectedItem(tblQLHD.getValueAt(row, 1).toString());
            cbbnhanvien.setSelectedItem(tblQLHD.getValueAt(row, 2).toString());
            txtNgayLap.setText(tblQLHD.getValueAt(row, 3).toString());
            txtThanhTien.setText(tblQLHD.getValueAt(row, 4).toString());

            //}
            modelct.setRowCount(0);
            int maHD = Integer.parseInt(tblQLHD.getValueAt(row, 0).toString());

            CTHDBUS cthd = new CTHDBUS();

            if (CTHDBUS.dscthd == null) {
                cthd.docDSCTHD();
            }
            //Add_headerct();
            for (CTHDDTO cthdDTO : CTHDBUS.dscthd) {

                if (cthdDTO.getMaHD() == maHD) {
                    Add_rowct(cthdDTO);
                    System.out.println("baby ới");
                }

            }

            //
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
