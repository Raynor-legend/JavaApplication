
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
import BUS.CongCu;
import BUS.NCCBUS;
import BUS.SanphamBUS;
import DAO.NCCDAO;
import DTO.NCCDTO;
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

public class NCCGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel model = new DefaultTableModel();
    JButton[] button;
    public JTable tblNCC;
    public JTextField txMaNCC, txTenNCC, txDiachi, txSdt, txMail;
    public JTextField txTimKiem;
    public JComboBox box1, cbbncc;
    Vector header;
    JPanel p3;
    JScrollPane scrollPanel;
    ArrayList<NCCDTO> dsncc = new ArrayList<>();
    ArrayList<NCCDTO> dsnccan= new ArrayList<>();
    ArrayList<NCCDTO> kq;

    public NCCGUI() {
        initComponent();
        DocDL();

    }

    public void DocDL() {
        NCCBUS bus = new NCCBUS();
        if (NCCBUS.dsncc == null) {
            bus.docDSNCC();
        }
        Add_header();
        for (NCCDTO ncc : NCCBUS.dsncc) {
            Add_row(ncc);

        }
    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("Mã nhà cung cấp");
        header.add("Ten nhà cung cấp");
        header.add("Địa chỉ");
        header.add("SĐT");
        header.add("Mail");
        header.add("Trạng Thái");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row(NCCDTO Sanpham) {
        Vector row = new Vector();
        row.add(Sanpham.getMaNCC());
        row.add(Sanpham.getTenNCC());
        row.add(Sanpham.getDiachi());
        row.add(Sanpham.getSdt());
        row.add(Sanpham.getMail());
        row.add(Sanpham.getTrangThai());
        model.addRow(row);
        tblNCC.setModel(model);
    }

    public void initComponent() {
        addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, 1200, 800);
        setBackground(Color.decode("#78909C"));
//        setBounds(0, 0, 1200, 800);
//        setBackground(Color.decode("#78909C"));

        JPanel p1 = QuanLiNCC();
        p1.setBounds(0, 0, 550, 450);
        p1.setBackground(Color.decode("#78909C"));
        String[] timkiem = {"Mã NCC", "Tên NCC"};
        box1 = new JComboBox(timkiem);
        box1.setBounds(570, 50, 100, 30);
        box1.setSelectedIndex(0);
        add(box1);

        JPanel p2 = CHUCNANG();
        p2.setBounds(700, 0, 700, 450);
        p2.setBackground(Color.decode("#78909C"));

        p3 = TabelNCC();
        p3.setBounds(100, 450, 1000, 300);//set vị trí so với phienthi
        p3.setBackground(Color.decode("#78909C"));
        add(p1);
        add(p2);
        add(p3);

        setVisible(true);
        tblNCC.addMouseListener(this);

    }

    public JPanel QuanLiNCC() {
        JPanel QLNCC = new JPanel();
        QLNCC.setLayout(null);
        JLabel[] label;
        label = new JLabel[7];
        JTextField[] textfield;
        // SanphamGUI a=new SanphamGUI();
        //a.tableMouseClicked(e);
        String[] arrNCC = {"Tìm kiếm", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "SĐT", "Mail", "Trạng Thái"};

        int toadoxLabel = 200, toadoyLabel = 50;
        int toadoxTextField = 350, toadoyTextField = 50;
        for (int i = 0; i < arrNCC.length; i++) {
            label[i] = new JLabel(arrNCC[i]); //khỡi tạo đối tượng thuộc Jbutton
            label[i].setBounds(toadoxLabel, toadoyLabel, 150, 30); //đinh vị trí cho từng đối tượng button
            label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
            label[i].setName("btn" + i);
            label[i].setBackground(Color.decode("#FFCA28"));
            label[i].setForeground(Color.WHITE);
            label[i].setFont(new Font("Arial", Font.BOLD, 15));
            System.out.println(label[i]);
            QLNCC.add(label[i]);
            toadoyLabel = toadoyLabel + 50;

            switch (i) {
                case 0: {
                    txTimKiem = new JTextField();
                    txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txTimKiem);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 1: {
                    txMaNCC = new JTextField();
                    txMaNCC.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txMaNCC);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 2: {
                    txTenNCC = new JTextField();
                    txTenNCC.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txTenNCC);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 3: {
                    txDiachi = new JTextField();
                    txDiachi.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txDiachi);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 4: {
                    txSdt = new JTextField();
                    txSdt.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txSdt);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 5: {
                    txMail = new JTextField();
                    txMail.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(txMail);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }
                case 6: {
                    String[] tt = {"1", "0"};

                    cbbncc = new JComboBox(tt);
                    cbbncc.setSelectedIndex(0);
                    cbbncc.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNCC.add(cbbncc);
                    toadoyTextField = toadoyTextField + 50;
                    break;
                }

            }

        }
        txMaNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txTenNCC.requestFocus();
                }
            }
        });
        txTenNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txDiachi.requestFocus();
                }
            }
        });
        return QLNCC;
    }

    public JPanel CHUCNANG() {
        JPanel pchucnang = new JPanel();
        pchucnang.setLayout(null);
        button = new JButton[7];
        String[] arrchucnang = {"Tìm Kiếm", "Thêm", "Sửa", "Làm mới", "DS ẩn", "Làm mới", "Xuất Excel"};
        // Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
        int toadoxButton = 50, toadoyButton = 50;
        for (int i = 0; i < arrchucnang.length; i++) {
            if (i == 1) {
                toadoxButton = 50;
                toadoyButton = 130;
            }
            if (i == 4) {
                toadoxButton = 240;
                toadoyButton = 130;
            }
            button[i] = new JButton(arrchucnang[i]); //khỡi tạo đối tượng thuộc Jbutton
            button[i].setBounds(toadoxButton, toadoyButton, 150, 30); //đinh vị trí cho từng đối tượng button
            button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
            button[i].setName("btn" + i);
            button[i].setBackground(Color.decode("#FFCA28"));
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            System.out.println(button[i]);
            pchucnang.add(button[i]);
            toadoyButton = toadoyButton + 80;
            button[i].addActionListener((ActionListener) this);
        }
        return pchucnang;

    }

    public void btclearMouseClicked() {
        txMaNCC.setBorder(BorderFactory.createEmptyBorder());
        txTenNCC.setBorder(BorderFactory.createEmptyBorder());
        txDiachi.setBorder(BorderFactory.createEmptyBorder());
        txSdt.setBorder(BorderFactory.createEmptyBorder());
        txMail.setBorder(BorderFactory.createEmptyBorder());

        txMaNCC.setText("");
        txMaNCC.setEnabled(true);
        txTenNCC.setText("");
        txDiachi.setText("");
        txSdt.setText("");
        txMail.setText("");
    }

    public JPanel TabelNCC() {
        JPanel ptablencc = new JPanel();
        ptablencc.setLayout(null);
        ptablencc.setBackground(Color.decode("#78909C"));
        header = new Vector();
        header.add("Mã nhà cung cấp");
        header.add("Tên nhà cung cấp");
        header.add("Địa chỉ");
        header.add("SĐT");
        header.add("Mail");
        header.add("Trạng Thái");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblNCC = new JTable(null, header);
        tblNCC.setBounds(0, 0, 1000, 300);

        tblNCC.setFont(new Font("Arial", 0, 15));
        tblNCC.setModel(model);//add model len table
        tblNCC.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblNCC.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblNCC.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        tblNCC.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblNCC);
        scrollPanel.setBounds(0, 0, 1000, 300);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        tblNCC.setPreferredSize(new Dimension(500, 500));
        ptablencc.add(scrollPanel); // add table vào scrollPanel
        tblNCC.setFillsViewportHeight(true);//hiển thị table
        return ptablencc;

    }

    private int kiemtra() {
        if (txMaNCC.getText().equals("") || txTenNCC.getText().equals("") || txDiachi.getText().equals("")) {
            return 0;
        }
        return 1;
    }

    private void checkempty() {
        if (txMaNCC.getText().equals("")) {
            txMaNCC.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txTenNCC.getText().equals("")) {
            txTenNCC.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txDiachi.getText().equals("")) {
            txDiachi.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txSdt.getText().equals("")) {
            txSdt.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txMail.getText().equals("")) {
            txMail.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

    }

    private void checkfilled() {
        if (!txMaNCC.getText().equals("")) {
            txMaNCC.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txTenNCC.getText().equals("")) {
            txTenNCC.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txDiachi.getText().equals("")) {
            txDiachi.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txSdt.getText().equals("")) {
            txSdt.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txMail.getText().equals("")) {
            txMail.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private boolean checknhap() {
        if (!CongCu.checkChar(txMaNCC.getText()) || !CongCu.checkNames(txTenNCC.getText()) || !CongCu.checkChar(txDiachi.getText()) || !CongCu.checkPhoneNumes(txSdt.getText()) || !CongCu.checkGmail(txMail.getText())) {
            return false;
        }
        return true;
    }

    private boolean check() {
        NCCDTO ncc = new NCCDTO();
        while (!checknhap()) {
            if (!CongCu.checkChar(txMaNCC.getText())) {

                JOptionPane.showMessageDialog(null, "Mã NCC chỉ bao gồm kí tự chữ và số");
                return false;
            }
            if (!CongCu.checkNames(txTenNCC.getText())) {
                JOptionPane.showMessageDialog(null, "Tên NCC chỉ bao gồm kí tự chữ");
                return false;
            }
            if (!CongCu.checkChar(txDiachi.getText())) {
                JOptionPane.showMessageDialog(null, "Địa chỉ NCC chỉ bao gồm kí tự chữ và số");
                return false;
            }
            if (!CongCu.checkPhoneNumes(txSdt.getText())) {
                JOptionPane.showMessageDialog(null, "SĐT chỉ bao gồm 10 số");
            }
            if (!CongCu.checkGmail(txMail.getText())) {
                JOptionPane.showMessageDialog(null, "Mail không hợp lệ!");
            }
        }
        return true;
    }

    private void btThemMouseClicked() {
        NCCDTO ncc = new NCCDTO();
        NCCBUS bus = new NCCBUS();
        String mancc = txMaNCC.getText();

        int i = bus.kt_trung_ma(mancc);
        if (i == 1) {
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp đã tồn tại");

        } else {
            if (kiemtra() == 0) {
                checkempty();
                checkfilled();
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
            } else {
                if (check()) {
                    ncc.setMaNCC(txMaNCC.getText());
                    ncc.setTenNCC(txTenNCC.getText());
                    ncc.setDiachi(txDiachi.getText());
                    ncc.setSdt(txSdt.getText());
                    ncc.setMail(txMail.getText());
                    ncc.setTrangThai(cbbncc.getSelectedItem().toString());
                    bus.themNCC(ncc);
                    Add_header();
                    Add_row(ncc);
                    checkfilled();
                    JOptionPane.showMessageDialog(null, "Thêm thành công");

                }

            }
        }
    }

    private void btXoaMouseClicked() {

//        NCCDTO ncc = new NCCDTO();
//        NCCBUS bus = new NCCBUS();
//        System.out.println("Hii");
//        int i = tblNCC.getSelectedRow();
//        if (i < 0) {
//            JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 nhà cung cấp");
//        } else {
//            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
//                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                model.removeRow(i);
//                tblNCC.setModel(model);
//                bus.xoa(ncc, i);
//
//            }
//
//        }
          NCCDAO a = new NCCDAO();
        dsnccan = a.docNCCAN();
        model.setRowCount(0);
        for (NCCDTO ncc : dsnccan) {
            
            model.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiachi(), ncc.getSdt(), ncc.getMail(), ncc.getTrangThai()});
        
        }
        tblNCC.setModel(model);
        p3.removeAll();
        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }

    private void btSuaMouseClicked() {
        NCCDTO ncc = new NCCDTO();
        NCCBUS bus = new NCCBUS();
        int i = tblNCC.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhà cung cấp bạn muốn sửa");
        }

        tblNCC.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("123");
            }
        });
        ListSelectionModel model1 = tblNCC.getSelectionModel();
        model1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int row = tblNCC.getSelectedRow();
                System.out.println("baby ới");
                if (row >= 0) {
                    txMaNCC.setText(tblNCC.getValueAt(row, 0).toString());
                    txMaNCC.setEnabled(false);
                    txTenNCC.setText(tblNCC.getValueAt(row, 1).toString());
                    txDiachi.setText(tblNCC.getValueAt(row, 2).toString());
                    txSdt.setText(tblNCC.getValueAt(row, 3).toString());
                    txMail.setText(tblNCC.getValueAt(row, 4).toString());
                    cbbncc.setSelectedItem(tblNCC.getValueAt(row, 5).toString());
                }
            }
        });
        // tblQLS.addMouseListener(this);
        if (i >= 0) {
            model.setValueAt(txMaNCC.getText(), i, 0);//model là ruột JTable.set giá trị cho model
            model.setValueAt(txTenNCC.getText(), i, 1);
            model.setValueAt(txDiachi.getText(), i, 2);
            model.setValueAt(txSdt.getText(), i, 3);
            model.setValueAt(txMail.getText(), i, 4);
            model.setValueAt(cbbncc.getSelectedItem(), i, 5);

            tblNCC.setModel(model);
            ncc.setMaNCC(txMaNCC.getText());//nap du lieu vao doi tuong(textfield)
            ncc.setTenNCC(txTenNCC.getText());
            ncc.setDiachi(txDiachi.getText());
            ncc.setSdt(txSdt.getText());
            ncc.setMail(txMail.getText());
            ncc.setTrangThai(cbbncc.getSelectedItem().toString());
        }
        bus.suaNCC(i, ncc);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }

    private void CHON(String conditions, String text) {
        NCCBUS bus = new NCCBUS();
        switch (conditions) {
            case "Mã NCC":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_mancc(text);
                Search();
                break;
            case "Tên NCC":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_tenncc(text);
                Search();
                break;
        }
    }

    private void Search() {
        {
            DefaultTableModel mode = new DefaultTableModel(header, 0);
            if (kq.size() != 0) {
                for (NCCDTO ncc : kq) {
                    Vector row = new Vector();
                    row.add(ncc.getMaNCC());
                    row.add(ncc.getTenNCC());
                    row.add(ncc.getDiachi());
                    row.add(ncc.getSdt());
                    row.add(ncc.getMail());
                    row.add(ncc.getTrangThai());
                    mode.addRow(row);
                }
                tblNCC.setModel(mode);
            }
        }
               

    }

    private void bttimkiemMouseClicked() {
        System.out.println("tới rồi nè");
        String selectedItem = (String) box1.getSelectedItem();
        String tukhoa = txTimKiem.getText();
        if (tukhoa.equals("")) {
            //JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            btcapnhatMouseClicked();
        } else {
            CHON(selectedItem, tukhoa);
        }
    }

    public void btXuatExcelMouseClicled() {
        JFileChooser file = new JFileChooser(); //Khởi tạo JFileChooser
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách nhà cung cấp");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = excelSheet.createRow((short) 1);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH  NHÀ CUNG CẤP");

            row = excelSheet.createRow((short) 2);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã NCC");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên NCC");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell = row.createCell(3, CellType.STRING);

            for (int i = 0; i < tblNCC.getRowCount(); i++) {
                row = excelSheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                for (int j = 0; j < tblNCC.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(tblNCC.getValueAt(i, j).toString());
                }
            }

            FileOutputStream excelFOS;
            BufferedOutputStream excelBOS;
            try {
                excelFOS = new FileOutputStream(new File(file.getSelectedFile() + ".xls"));
                excelBOS = new BufferedOutputStream(excelFOS);
                excelWorkbook.write(excelBOS);
                JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
                excelBOS.close();
                excelWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btcapnhatMouseClicked() {
        NCCDAO a = new NCCDAO();
        dsncc = a.docNCC();
        model.setRowCount(0);
        for (NCCDTO ncc : dsncc) {
            model.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiachi(), ncc.getSdt(), ncc.getMail(), ncc.getTrangThai()});
        }
        tblNCC.setModel(model);
        p3.removeAll();
        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }
//      public static void main(String[] args) {
//        NCCGUI adminncc = new NCCGUI();
//        adminncc.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        adminncc.setLocationRelativeTo(adminncc);
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Thêm")) {
            btThemMouseClicked();
        }

        if (src.equals("DS ẩn")) {
            btXoaMouseClicked();
        }
        if (src.equals("Sửa")) {

            btSuaMouseClicked();
        }
        if (src.equals("Tìm Kiếm")) {
            bttimkiemMouseClicked();
        }
        if (src.equals("Cập nhật")) {
            btcapnhatMouseClicked();
        }
        if (src.equals("Làm mới")) {
            btclearMouseClicked();
        }
        if (src.endsWith("Xuất excel")) {
            btXuatExcelMouseClicled();
        }

//        if(e.getSource()==button[1]){
//            btXoaMouseClicked();
//            int i=tblQLS.getSelectedRow();
//           dsSanpham.remove(i);
//            
//        }
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

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("aaaa");
        if (e.getSource().equals(tblNCC)) {
            int row = tblNCC.getSelectedRow();
            System.out.println("baby ới");
            if (row >= 0) {
//                txMaNCC.setBorder(BorderFactory.createEmptyBorder());
//                txTenNCC.setBorder(BorderFactory.createEmptyBorder());
//                txDiachi.setBorder(BorderFactory.createEmptyBorder());
//                txSdt.setBorder(BorderFactory.createEmptyBorder());
//                 txMail.setBorder(BorderFactory.createEmptyBorder());

                txMaNCC.setText(tblNCC.getValueAt(row, 0).toString());
                txMaNCC.setEnabled(false);
                txTenNCC.setText(tblNCC.getValueAt(row, 1).toString());;
                txDiachi.setText(tblNCC.getValueAt(row, 2).toString());
                txSdt.setText(tblNCC.getValueAt(row, 3).toString());
                txMail.setText(tblNCC.getValueAt(row, 4).toString());
                cbbncc.setSelectedItem(tblNCC.getValueAt(row, 5).toString());
            }
        }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
