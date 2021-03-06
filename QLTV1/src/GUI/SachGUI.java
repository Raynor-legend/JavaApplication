/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongCu;
import BUS.NXBBUS;
import BUS.SachBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DAO.SachDAO;
import DTO.NXBDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import java.awt.*;
import static java.awt.Font.BOLD;
import java.awt.event.*;
import java.awt.event.ActionListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author HP
 */
public class SachGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel mode = new DefaultTableModel();
    JButton[] button;
    public JTable tblQLS;
    public JTextField txMasach;
    public JTextField txTensach;
    public JTextField txMaTL;
    public JTextField txMaTG;
    public JTextField txMaNXB;
    public JTextField txSoluong;
    public JTextField txDongiaban;
    public JTextField txTimKiem;
    public JComboBox box1, cbbMaTL, cbbMaTG, cbbNXB;
    Vector header;
    JPanel p3;
    JScrollPane scrollPanel;
    // public static JPanel QLS;
    public static ArrayList<SachDTO> dssach = new ArrayList<>();
    public static ArrayList<TacGiaDTO> dstg = new ArrayList<>();
    ArrayList<SachDTO> kq;

    public SachGUI() {
        initComponent();
        SachBUS bus = new SachBUS();
        if (SachBUS.dssach == null) {
            bus.docDSSACH();
        }
        Add_header();
        for (SachDTO sach : SachBUS.dssach) {
            Add_row(sach);
        }

    }

    public void initComponent() {
        //addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, 1200, 800);
        setBackground(Color.decode("#78909C"));
        JPanel p1 = QuanLiSach();
        p1.setBounds(100, 0, 600, 450);
        p1.setBackground(Color.decode("#78909C"));
        String[] timkiem = {"M?? s??ch", "T??n s??ch", "M?? TG", "M?? TL"};
        box1 = new JComboBox(timkiem);
        box1.setBounds(420, 100, 100, 30);
        box1.setSelectedIndex(0);
        p1.add(box1);

        JPanel p2 = CHUCNANG();
        p2.setBounds(650, 0, 600, 450);
        p2.setBackground(Color.decode("#78909C"));

        p3 = TabelSach();
        p3.setBounds(100, 450, 1000, 300);//set v??? tr?? so v???i phienthi
        p3.setBackground(Color.decode("#78909C"));
        add(p1);
        add(p2);
        add(p3);
        setVisible(true);
        tblQLS.addMouseListener(this);

    }

    public JPanel QuanLiSach() {
        JPanel pQLS = new JPanel();
        pQLS.setLayout(null);
        JLabel[] label;
        label = new JLabel[8];
        JTextField[] textfield;
        String[] arrSach = {"T??m ki???m", "M?? s??ch", "T??n s??ch", "M?? t??c gi???", "M?? th??? lo???i", "M?? NXB", "S??? l?????ng", "????n gi?? b??n"};

        int toadoxLabel = 100, toadoyLabel = 100;
        int toadoxTextField = 200, toadoyTextField = 100;
        for (int i = 0; i < arrSach.length; i++) {
            label[i] = new JLabel(arrSach[i]); //kh???i t???o ?????i t?????ng thu???c Jbutton
            label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); //??inh v??? tr?? cho t???ng ?????i t?????ng button
            label[i].setHorizontalAlignment(JButton.LEFT); // canh ph???i cho text button
            label[i].setName("btn" + i);
            label[i].setForeground(Color.WHITE);
            label[i].setFont(new Font("Arial", Font.BOLD, 15));

            // label[i].setBackground(Color.decode("#FFCA28"));
            System.out.println(label[i]);
            pQLS.add(label[i]);
            toadoyLabel = toadoyLabel + 40;

            switch (i) {
                case 0: {
                    txTimKiem = new JTextField();
                    txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    pQLS.add(txTimKiem);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }

                case 1: {
                    txMasach = new JTextField();
                    txMasach.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    pQLS.add(txMasach);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 2: {
                    txTensach = new JTextField();
                    txTensach.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    pQLS.add(txTensach);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 3: {
                    TacGiaBUS b = new TacGiaBUS();
                    dstg = b.docTacGia();
                    cbbMaTG = new JComboBox();

                    for (int k = 0; k < dstg.size(); k++) {
                        if (dstg.get(k).getTrangThai().equals("1"));
                        cbbMaTG.addItem(dstg.get(k).getMaTG());
                    }
                    cbbMaTG.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            if (cbbMaTG.getItemCount() != 0) {
                                showTenTG();
                            }
                        }
                    });

                    cbbMaTG.setBounds(200, 220, 100, 30);
                    txMaTG = new JTextField();
                    txMaTG.setBounds(310, 220, 90, 30);
                    txMaTG.setEnabled(false);

                    String getcbbMaTG = cbbMaTG.getSelectedItem().toString();
                    for (int p = 0; p < dstg.size(); p++) {
                        if (getcbbMaTG.equals(dstg.get(p).getMaTG())) {

                            txMaTG.setText(dstg.get(p).getTenTG());
                        }
                    }

                    pQLS.add(txMaTG);
                    pQLS.add(cbbMaTG);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 4: {
                    TheLoaiBUS a = new TheLoaiBUS();
                    ArrayList<TheLoaiDTO> arrTL = new ArrayList<TheLoaiDTO>();
                    arrTL = a.docTheLoai();
                    cbbMaTL = new JComboBox();

                    for (int j = 0; j < arrTL.size(); j++) {
                        cbbMaTL.addItem(arrTL.get(j).getMaTL());
                    }

                    cbbMaTL.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            showTenTL();
                        }
                    });

                    cbbMaTL.setBounds(200, 260, 100, 30);
                    txMaTL = new JTextField();
                    txMaTL.setBounds(310, 260, 90, 30);
                    txMaTL.setEnabled(false);

                    String getcbbMaTL = cbbMaTL.getSelectedItem().toString();
                    for (int p = 0; p < arrTL.size(); p++) {
                        if (getcbbMaTL.equals(arrTL.get(p).getMaTL())) {
                            txMaTL.setText(arrTL.get(p).getTenTL());
                        }
                    }

                    pQLS.add(cbbMaTL);
                    pQLS.add(txMaTL);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }

                case 5: {
                    NXBBUS c = new NXBBUS();
                    ArrayList<NXBDTO> arrNXB = new ArrayList<NXBDTO>();
                    arrNXB = c.docNXB();
                    cbbNXB = new JComboBox();
                    for (int l = 0; l < arrNXB.size(); l++) {
                        cbbNXB.addItem(arrNXB.get(l).getMaNXB());
                    }

                    cbbNXB.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            showTenNXB();
                        }
                    });

                    cbbNXB.setBounds(200, 300, 100, 30);
                    txMaNXB = new JTextField();
                    txMaNXB.setBounds(310, 300, 90, 30);
                    txMaNXB.setEnabled(false);

                    String getcbbMaNXB = cbbNXB.getSelectedItem().toString();
                    for (int p = 0; p < arrNXB.size(); p++) {
                        if (getcbbMaNXB.equals(arrNXB.get(p).getMaNXB())) {
                            txMaNXB.setText(arrNXB.get(p).getTenNXB());
                        }
                    }
                    pQLS.add(txMaNXB);
                    pQLS.add(cbbNXB);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 6: {
                    txSoluong = new JTextField();
                    txSoluong.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    txSoluong.setEnabled(false);
                    txSoluong.setText("0");
                    pQLS.add(txSoluong);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 7: {
                    txDongiaban = new JTextField();
                    txDongiaban.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    pQLS.add(txDongiaban);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }

            }

        }

        txMasach.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txTensach.requestFocus();
                }
            }
        });
        txSoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txDongiaban.requestFocus();
                }
            }
        });
        return pQLS;
    }

    private void showTenTL() {
        TheLoaiBUS a = new TheLoaiBUS();
        ArrayList<TheLoaiDTO> arrTL = new ArrayList<TheLoaiDTO>();
        arrTL = a.docTheLoai();
        String getcbbMaTL = cbbMaTL.getSelectedItem().toString();
        for (int p = 0; p < arrTL.size(); p++) {
            if (getcbbMaTL.equals(arrTL.get(p).getMaTL())) {
                txMaTL.setText(arrTL.get(p).getTenTL());
            }
        }
    }

    private void showTenTG() {
        TacGiaBUS a = new TacGiaBUS();
        ArrayList<TacGiaDTO> arrTG = new ArrayList<TacGiaDTO>();
        arrTG = a.docTacGia();
        String getcbbMaTG = cbbMaTG.getSelectedItem().toString();
        for (int p = 0; p < arrTG.size(); p++) {
            if (getcbbMaTG.equals(arrTG.get(p).getMaTG())) {
                txMaTG.setText(arrTG.get(p).getTenTG());
            }
        }
    }

    private void showTenNXB() {
        NXBBUS a = new NXBBUS();
        ArrayList<NXBDTO> arrNXB = new ArrayList<NXBDTO>();
        arrNXB = a.docNXB();
        String getcbbMaNXB = cbbNXB.getSelectedItem().toString();
        for (int p = 0; p < arrNXB.size(); p++) {
            if (getcbbMaNXB.equals(arrNXB.get(p).getMaNXB())) {
                txMaNXB.setText(arrNXB.get(p).getTenNXB());
            }
        }
    }

    public JPanel CHUCNANG() {
        JPanel pchucnang = new JPanel();
        pchucnang.setLayout(null);
        button = new JButton[6];

        String[] arrchucnang = {"T??m Ki???m", "Th??m", "S???a", "Xu???t excel", "L??m m???i", "C???p nh???t"};
        int x = 50, y = 100;
        for (int i = 0; i < arrchucnang.length; i++) {
           if (i == 1) {
                x = 240;
                y = 100;
            }
            button[i] = new JButton(arrchucnang[i]); //kh???i t???o ?????i t?????ng thu???c Jbutton
            button[i].setBounds(x, y, 150, 30); //??inh v??? tr?? cho t???ng ?????i t?????ng button
            button[i].setHorizontalAlignment(JButton.CENTER); // canh ph???i cho text button
            button[i].setName("btn" + i);
            button[i].setBackground(Color.decode("#FFCA28"));
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            System.out.println(button[i]);
            pchucnang.add(button[i]);
            y = y + 70;
            button[i].addActionListener((ActionListener) this);
        }
        return pchucnang;

    }

    public JPanel TabelSach() {
        JPanel ptablesach = new JPanel();
        ptablesach.setLayout(null);
        ptablesach.setBackground(Color.decode("#78909C"));
        header = new Vector();
        header.add("M?? s??ch");
        header.add("T??n s??ch");
        header.add("M?? t??c gi???");
        header.add("M?? th??? lo???i");
        header.add("M?? NXB");
        header.add("S??? l?????ng");
        header.add("????n gi?? b??n");

        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblQLS = new JTable(null, header) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        tblQLS.setModel(model);//add model len table
        tblQLS.setBounds(0, 0, 1000, 300);
        tblQLS.setFont(new Font("Arial", 0, 15));
        tblQLS.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        tblQLS.getTableHeader().setForeground(Color.black); //set m??u ch??? cho header
        tblQLS.getTableHeader().setPreferredSize(new Dimension(30, 50));//set ????? d??i ????? r???ng c???a header
        tblQLS.getTableHeader().setBackground(Color.decode("#FFCA28"));//set background cho header

        scrollPanel = new JScrollPane(tblQLS);
        scrollPanel.setBounds(0, 0, 1000, 300);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        tblQLS.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setPreferredSize(new Dimension(500, 500));

        ptablesach.add(scrollPanel); // add table v??o scrollPanel
        tblQLS.setFillsViewportHeight(true);//hi???n th??? table

        return ptablesach;

    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("M?? s??ch");
        header.add("T??n S??ch");
        header.add("M?? t??c gi???");
        header.add("M?? th??? lo???i");
        header.add("M?? NXB");
        header.add("S??? l?????ng");
        header.add("????n gi?? b??n");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row(SachDTO sach) {
        Vector row = new Vector();
        row.add(sach.getMaSach());
        row.add(sach.getTenSach());
        row.add(sach.getMaTG());
        row.add(sach.getMaTL());
        row.add(sach.getMaNXB());
        row.add(sach.getSoLuong());
        row.add(sach.getDonGia());
        model.addRow(row);
        tblQLS.setModel(model);
    }

    private void btClearMouseClicked() {
        txMasach.setText("");
        txTensach.setText("");
        cbbMaTG.setSelectedIndex(0);
        cbbMaTL.setSelectedIndex(0);
        cbbNXB.setSelectedIndex(0);
        txSoluong.setText("0");
        txDongiaban.setText("0");
        txMasach.setEnabled(true);
    }

    private boolean check() {
        SachDTO sach = new SachDTO();
        
        System.out.println("Hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        if (!CongCu.checkMa(txMasach.getText())) {
            JOptionPane.showMessageDialog(null, "M?? s??ch kh??ng ch???a k?? t??? ?????c bi???t");
            return false;
        }
        else if(!CongCu.isLength15(txMasach.getText())){
            JOptionPane.showMessageDialog(null,"M?? s??ch kh??ng qu?? 15 k?? t???");
            return false;
        }else if (!CongCu.checkNames(txTensach.getText())) {
            JOptionPane.showMessageDialog(null, "T??n s??ch ch??? g???m ch??? v?? s???");
            txTensach.requestFocus();
            return false;
        } else if (!CongCu.isLength50(txTensach.getText())) {
//            System.out.println("zo 50");
            JOptionPane.showMessageDialog(null, "T??n s??ch kh??ng ???????c qu?? 50 k?? t???");
            txTensach.requestFocus();
            return false;
        } else if (!CongCu.checkNume(txSoluong.getText())) {
            JOptionPane.showMessageDialog(null, "S??? l?????ng ch??? bao g???m s??? v?? kh??ng qu?? 6 ch??? s???");
            return false;
        } else if (!CongCu.checkPrice(txDongiaban.getText())){
            JOptionPane.showMessageDialog(null, "????n gi?? ch??? bao g???m s??? v?? kh??ng qu?? 9 ch??? s???");
            return false;
        }
        return true;
    }

    private int kiemtranhap() {
        if (txMasach.getText().equals("") || txTensach.getText().equals("") || txDongiaban.getText().equals("")) {
            return 0;
        } else {
            return 1;
        }
    }

    private void checkempty() {
        if (txMasach.getText().equals("")) {
            txMasach.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txTensach.getText().equals("")) {
            txTensach.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txSoluong.getText().equals("")) {
            txSoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txDongiaban.getText().equals("")) {
            txDongiaban.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    private void checkfilled() {
        if (!txMasach.getText().equals("")) {
            txMasach.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txTensach.getText().equals("")) {
            txTensach.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txSoluong.getText().equals("")) {
            txSoluong.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txDongiaban.getText().equals("")) {
            txDongiaban.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private void btThemMouseClicked() {
        SachDTO sach = new SachDTO();
        SachBUS bus = new SachBUS();
        String masach = txMasach.getText();
        int i = bus.kt_trung_ma(masach);
        if (i == 1) {
            JOptionPane.showMessageDialog(null, "M?? s??ch ???? t???n t???i");
        } else {
            if (kiemtranhap() == 0) {
                checkfilled();
                checkempty();
                JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????? th??ng tin");
            } else {
                if (check()) {
                    sach.setMaSach(txMasach.getText());
                    sach.setTenSach(txTensach.getText());
                    sach.setMaTL(cbbMaTL.getSelectedItem().toString());
                    sach.setMaTG(cbbMaTG.getSelectedItem().toString());
                    sach.setMaNXB(cbbNXB.getSelectedItem().toString());
                    sach.setSoLuong(txSoluong.getText().toString());
                    sach.setDonGia(txDongiaban.getText().toString());
                    bus.themSach(sach);//th??m s??ch b??n BUS ???? c?? th??m v??o database
                    Add_header();
                    Add_row(sach);
                    checkfilled();
                    JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
                }
            }
        }
    }

//    private void btXoaMouseClicked() {
//
//        SachDTO sach = new SachDTO();
//        SachBUS bus = new SachBUS();
//        System.out.println("Nhue hello");
//        int i = tblQLS.getSelectedRow();
//        if (i < 0) {
//            JOptionPane.showMessageDialog(null, "B???n c???n ph???i ch???n 1 cu???n sach");
//        } else {
//            if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n xo?? kh??ng", "Th??ng b??o",
//                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                model.removeRow(i);
//                tblQLS.setModel(model);
//                bus.xoa(sach, i);
//
//            }
//
//        }
//
//    }

    private void btSuaMouseClicked() {
        SachDTO sach = new SachDTO();
        SachBUS bus = new SachBUS();
        int i = tblQLS.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui l??ng ch???n 1 cuon sach ban muon sua");
        }
        tblQLS.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("123");

            }
        });
        ListSelectionModel model1 = tblQLS.getSelectionModel();
        model1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int row = tblQLS.getSelectedRow();
                System.out.println(row);
                if (row != -1) {
                    txMasach.setText(tblQLS.getValueAt(row, 0).toString());
                    txMasach.setEnabled(false);
                    txTensach.setText(tblQLS.getValueAt(row, 1).toString());
                    System.out.println(tblQLS.getValueAt(row, 0).toString());
                    cbbMaTG.setSelectedItem(tblQLS.getValueAt(row, 2).toString());
                    System.out.println(tblQLS.getValueAt(row, 2).toString());
                    cbbMaTL.setSelectedItem(tblQLS.getValueAt(row, 3).toString());
                    cbbNXB.setSelectedItem(tblQLS.getValueAt(row, 4).toString());
                    txSoluong.setText(tblQLS.getValueAt(row, 5).toString());
                    txDongiaban.setText(tblQLS.getValueAt(row, 6).toString());

                }
            }
        });
        if (i >= 0) {
            model.setValueAt(txMasach.getText(), i, 0);//model l?? ru???t JTable.set gi?? tr??? cho model
            model.setValueAt(txTensach.getText(), i, 1);
            model.setValueAt(cbbMaTG.getSelectedItem(), i, 2);
            model.setValueAt(cbbMaTL.getSelectedItem(), i, 3);
            model.setValueAt(cbbNXB.getSelectedItem(), i, 4);
            model.setValueAt(Integer.parseInt(txSoluong.getText()), i, 5);
            model.setValueAt(Integer.parseInt(txDongiaban.getText()), i, 6);
            tblQLS.setModel(model);
            sach.setMaSach(txMasach.getText());//nap du lieu vao doi tuong(textfield)
            sach.setTenSach(txTensach.getText());
            sach.setMaTG(cbbMaTG.getSelectedItem().toString());
            sach.setMaTL(cbbMaTL.getSelectedItem().toString());
            sach.setMaNXB(cbbNXB.getSelectedItem().toString());
            sach.setSoLuong(txSoluong.getText().toString());
            sach.setDonGia(txDongiaban.getText().toString());

        }
        bus.suaSach(i, sach);
        JOptionPane.showMessageDialog(null, "S???a th??nh c??ng");
    }

    private void ABCEFG(String condition, String text) {
        SachBUS bus = new SachBUS();
        switch (condition) {
            case "M?? s??ch":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_masach(text);
                Search();
                break;
            case "M?? TL":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_matl(text);
                Search();
                break;
            case "M?? TG":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_matacgia(text);
                Search();
                break;
            case "T??n s??ch":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_tensach(text);
                Search();
                break;

        }
    }

    private void Search() {
        DefaultTableModel mode = new DefaultTableModel(header, 0);
        if (kq.size() != 0) {
            for (SachDTO sach : kq) {
                Vector row = new Vector();
                row.add(sach.getMaSach());
                row.add(sach.getTenSach());
                row.add(sach.getMaTG());
                row.add(sach.getMaTL());
                row.add(sach.getMaNXB());
                row.add(sach.getSoLuong());
                row.add(sach.getDonGia());
                mode.addRow(row);
            }
            tblQLS.setModel(mode);

        }
    }

    private void bttimkiemMouseClicked() {
        //System.out.println("t???i r???i n??");
        String selectedItem = (String) box1.getSelectedItem();
        String tukhoa = txTimKiem.getText();
        if (tukhoa.equals("")) {
            btCapnhatMouseClicked();
        } else {
            ABCEFG(selectedItem, tukhoa);
        }

    }

    private void btXuatExcelMouseClicked() {
        JFileChooser file = new JFileChooser(); //Kh???i t???o JFileChooser

        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Danh s??ch s??ch");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = excelSheet.createRow((short) 1);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH S??CH S??CH");

            row = excelSheet.createRow((short) 2);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("M?? s??ch");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("T??n s??ch");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("M?? t??c gi???");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("M?? th??? lo???i");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("M?? NXB");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("S??? l?????ng");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("????n gi?? b??n");

            for (int i = 0; i < tblQLS.getRowCount(); i++) {
                row = excelSheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                for (int j = 0; j < tblQLS.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(tblQLS.getValueAt(i, j).toString());
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

    private void btCapnhatMouseClicked() {
        TacGiaBUS bus = new TacGiaBUS();
        dstg = TacGiaBUS.dstg;
        cbbMaTG.removeAllItems();
        for (int k = 0; k < dstg.size(); k++) {
            if (dstg.get(k).getTrangThai().equals("1")) {
                cbbMaTG.addItem(dstg.get(k).getMaTG());
            }
        }
        cbbMaTG.setSelectedIndex(0);
        SachDAO a = new SachDAO();
        dssach = a.docSach();
        //Add_header();
        model.setRowCount(0);
        for (SachDTO s : dssach) {
            model.addRow(new Object[]{s.getMaSach(), s.getTenSach(),
                s.getMaTG(), s.getMaTL(), s.getMaNXB(), s.getSoLuong(), s.getDonGia()});
        }
        tblQLS.setModel(model);
        p3.removeAll();
        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Th??m")) {
            btThemMouseClicked();
        }
//        if (src.equals("X??a")) {
//            btXoaMouseClicked();
//        }
        if (src.equals("S???a")) {

            btSuaMouseClicked();
        }
        if (src.equals("C???p nh???t")) {
            btCapnhatMouseClicked();
        }
        if (src.equals("T??m Ki???m")) {
            bttimkiemMouseClicked();
        }
        if (src.equals("L??m m???i")) {
            btClearMouseClicked();
        }
        if (src.equals("Xu???t excel")) {
            btXuatExcelMouseClicked();
        }

    }

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

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("aa");
        if (e.getSource().equals(tblQLS)) {
            int row = tblQLS.getSelectedRow();
            if (row >= 0) {
                txMasach.setText(tblQLS.getValueAt(row, 0).toString());
                txMasach.setEnabled(false);
                txTensach.setText(tblQLS.getValueAt(row, 1).toString());
                cbbMaTG.setSelectedItem(tblQLS.getValueAt(row, 2).toString());
                cbbMaTL.setSelectedItem(tblQLS.getValueAt(row, 3).toString());

                cbbNXB.setSelectedItem(tblQLS.getValueAt(row, 4).toString());
                txSoluong.setText(tblQLS.getValueAt(row, 5).toString());
                txDongiaban.setText(tblQLS.getValueAt(row, 6).toString());
            }
        }
    }

}
