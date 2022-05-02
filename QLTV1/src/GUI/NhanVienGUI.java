/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongCu;
import BUS.NhanVienBUS;
import BUS.TheLoaiBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import DTO.TheLoaiDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NhanVienGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel model = new DefaultTableModel();
    JButton[] button;
    public JTable tblQLNV;
    public JTextField txtMaNV, txtHoNV, txtTenNV, txtNgaysinh, txtGioitinh, txtCMND, txtDiachi, txtSdt;
    public JTextField txTimKiem;
    public JComboBox box1, cbbtt, cbbgioitinh;
    Vector header;
    JPanel p3;
    JScrollPane scrollPanel;
    public static JPanel phienthi, QLNV;
    JDateChooser jdngaysinh;
    String ngaysinh;
    ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
    ArrayList<NhanVienDTO> dsnvan = new ArrayList<>();
    ArrayList<NhanVienDTO> kq;

    public NhanVienGUI() {
        initComponents();
        NhanVienBUS nv = new NhanVienBUS();
        if (NhanVienBUS.dsnv == null) {
            nv.docDSNV();
        }
        Add_header();
        for (NhanVienDTO nhanvien : NhanVienBUS.dsnv) {
            Add_row(nhanvien);
        }
        Date date = new Date();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        jdngaysinh.setDateFormatString("yyyy-MM-dd");
    }

    public void initComponents() {
        addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, 1200, 800);
        setBackground(Color.decode("#78909C"));

        phienthi = QLNV();
        phienthi.setBounds(0, 0, 1200, 800);
        phienthi.setBackground(Color.decode("#78909C"));
        String[] timkiem = {"Mã NV", "Tên NV", "SĐT"};
        box1 = new JComboBox(timkiem);
        box1.setBounds(540, 50, 100, 30);
        box1.setSelectedIndex(0);

        phienthi.add(box1);
        JPanel p2 = CHUCNANG();
        p2.setBounds(700, 0, 700, 450);
        p2.setBackground(Color.decode("#78909C"));

        p3 = TableNV();
        p3.setBounds(100, 450, 1000, 300);//set vị trí so với phienthi
        p3.setBackground(Color.decode("#78909C"));
        phienthi.add(p2);
        phienthi.add(p3);
        add(phienthi);
        setVisible(true);
        tblQLNV.addMouseListener(this);

    }

    public JPanel QLNV() {
        QLNV = new JPanel();
        QLNV.setLayout(null);
        JLabel[] label;
        label = new JLabel[10];
        JTextField[] textfield;

        String[] arrNV = {"Tìm kiếm", "Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "CMND", "Địa chỉ", "Số điện thoại", "TT"};

        int toadoxLabel = 200, toadoyLabel = 50;
        int toadoxTextField = 320, toadoyTextField = 50;
        for (int i = 0; i < arrNV.length; i++) {
            label[i] = new JLabel(arrNV[i]); //khỡi tạo đối tượng thuộc Jbutton
            label[i].setBounds(toadoxLabel, toadoyLabel, 120, 30); //đinh vị trí cho từng đối tượng button
            label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
            label[i].setName("btn" + i);
            label[i].setFont(new Font("Arial", Font.BOLD, 15));
            label[i].setBackground(Color.decode("#78909C"));
            label[i].setForeground(Color.WHITE);
            System.out.println(label[i]);
            QLNV.add(label[i]);
            toadoyLabel = toadoyLabel + 40;
            switch (i) {
                case 0: {
                    txTimKiem = new JTextField();
                    txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txTimKiem);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 1: {
                    txtMaNV = new JTextField();
                    txtMaNV.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtMaNV);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 2: {
                    txtHoNV = new JTextField();
                    txtHoNV.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtHoNV);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 3: {
                    txtTenNV = new JTextField();
                    txtTenNV.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtTenNV);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 4: {
//                    txtNgaysinh = new JTextField();
//                    txtNgaysinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);
//                    QLNV.add(txtNgaysinh);
//                    toadoyTextField = toadoyTextField + 40;
//                    break;
                    jdngaysinh = new JDateChooser();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                    jdngaysinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);

                    QLNV.add(jdngaysinh);
                    toadoyTextField = toadoyTextField + 40;
                    //ngaysinh = df.format(jdngaysinh.getDate());
                    break;
                }
                case 5: {
                    String[] gt = {"Nam", "Nữ", "Khác"};
                    cbbgioitinh = new JComboBox(gt);
                    cbbgioitinh.setSelectedIndex(0);
                    cbbgioitinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(cbbgioitinh);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 6: {
                    txtCMND = new JTextField();
                    txtCMND.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtCMND);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 7: {
                    txtDiachi = new JTextField();
                    txtDiachi.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtDiachi);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 8: {
                    txtSdt = new JTextField();
                    txtSdt.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(txtSdt);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }
                case 9: {
                    String[] tt = {"1", "0"};
                    cbbtt = new JComboBox(tt);
                    cbbtt.setSelectedIndex(0);
                    cbbtt.setBounds(toadoxTextField, toadoyTextField, 200, 30);
                    QLNV.add(cbbtt);
                    toadoyTextField = toadoyTextField + 40;
                    break;
                }

            }
        }
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtHoNV.requestFocus();
                }
            }
        });
        txtHoNV.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtTenNV.requestFocus();
                }
            }
        });
//        txtTenNV.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txtNgaysinh.requestFocus();
//                }
//            }
//        });
//           txtNgaysinh.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txtGioitinh.requestFocus();
//                }
//            }
//        });
//        txtGioitinh.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txtCMND.requestFocus();
//                }
//            }
//        });
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtDiachi.requestFocus();
                }
            }
        });
        txtDiachi.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtSdt.requestFocus();
                }
            }
        });
//        txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txTT.requestFocus();
//                }
//            }
//        });
        return QLNV;
    }

    public JPanel CHUCNANG() {
        JPanel pchucnang = new JPanel();
        pchucnang.setLayout(null);
        button = new JButton[8];
        String[] arrchucnang = {"Tìm Kiếm", "Thêm", "Sửa", "Xóa", "Xuất excel", "Cập nhật", "Làm mới", "DS Xóa"};
        // Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
        int toadoxButton = 50, toadoyButton = 50;
        for (int i = 0; i < arrchucnang.length; i++) {
            if (i == 4) {
                toadoxButton = 300;
                toadoyButton = 50;
            }
            button[i] = new JButton(arrchucnang[i]); //khỡi tạo đối tượng thuộc Jbutton
            button[i].setBounds(toadoxButton, toadoyButton, 150, 30); //đinh vị trí cho từng đối tượng button
            button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
            button[i].setName("btn" + i);
            button[i].setBackground(Color.decode("#FFCA28"));
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            System.out.println(button[i]);
            pchucnang.add(button[i]);
            toadoyButton = toadoyButton + 100;
            button[i].addActionListener((ActionListener) this);
        }
        return pchucnang;

    }

    public void btclearMouseClicked() {
        txtCMND.setText("");
        txtDiachi.setText("");
        txtGioitinh.setText("");
        txtHoNV.setText("");
        txtMaNV.setText("");
        txtMaNV.setEnabled(true);
        jdngaysinh.setCalendar(null);

        //txtNgaysinh.setText("");
//         DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//         jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse());
        txtSdt.setText("");
        txtTenNV.setText("");
        cbbtt.setSelectedIndex(0);
    }

    public JPanel TableNV() {
        JPanel ptablenv = new JPanel();
        ptablenv.setLayout(null);
        ptablenv.setBackground(Color.decode("#FFCA28"));
        header = new Vector();
        header.add("Mã nhân viên");
        header.add("Họ nhân viên");
        header.add("Tên nhân viên");
        header.add("Ngày sinh");
        header.add("Giới tính");
        header.add("CMND");
        header.add("Địa chỉ");
        header.add("Số điện thoại");
        header.add("TT");

        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblQLNV = new JTable(null, header) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        tblQLNV.setBounds(0, 0, 1000, 300);

        tblQLNV.setFont(new Font("Arial", 0, 15));
        tblQLNV.setModel(model);//add model len table
        tblQLNV.getTableHeader().setFont(new Font("Arial", BOLD, 15)); //set font cho vector header
        tblQLNV.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblQLNV.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        tblQLNV.getTableHeader().setBackground(Color.decode("#4FC3F7"));//set background cho header

        scrollPanel = new JScrollPane(tblQLNV);
        scrollPanel.setBounds(0, 0, 1000, 300);
        ptablenv.add(scrollPanel); // add table vào scrollPanel
        tblQLNV.setFillsViewportHeight(true);//hiển thị table

        return ptablenv;

    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("Mã nhân viên");
        header.add("Họ nhân viên");
        header.add("Tên nhân viên");
        header.add("Ngày sinh");
        header.add("Giới tính");
        header.add("CMND");
        header.add("Địa chỉ");
        header.add("Số điện thoại");
        header.add("TT");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row(NhanVienDTO nv) {
        Vector row = new Vector();
        row.add(nv.getMaNV());
        row.add(nv.getHoNV());
        row.add(nv.getTenNV());
        row.add(nv.getNgaysinh());
        row.add(nv.getGioitinh());
        row.add(nv.getCMND());
        row.add(nv.getDiachi());
        row.add(nv.getSdt());
        row.add(nv.getTrangThai());
        model.addRow(row);
        tblQLNV.setModel(model);
    }

    private int kiemtra() {
        if (txtMaNV.getText().equals("") || txtHoNV.getText().equals("") || txtTenNV.getText().equals("")
                || txtCMND.getText().equals("") || txtDiachi.getText().equals("")
                || txtSdt.getText().equals("")) {
            return 0;
        }
        return 1;
    }

    private void checkempty() {
        if (txtMaNV.getText().equals("")) {
            txtMaNV.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txtHoNV.getText().equals("")) {
            txtHoNV.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txtTenNV.getText().equals("")) {
            txtTenNV.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
//        if (txtNgaysinh.getText().equals("")) {
//            txtNgaysinh.setBorder(BorderFactory.createLineBorder(Color.RED));
//        }
//        if (txtGioitinh.getText().equals("")) {
//            txtGioitinh.setBorder(BorderFactory.createLineBorder(Color.RED));
//        }
        if (txtDiachi.getText().equals("")) {
            txtDiachi.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txtCMND.getText().equals("")) {
            txtCMND.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txtSdt.getText().equals("")) {
            txtSdt.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    private void checkfilled() {
        if (!txtMaNV.getText().equals("")) {
            txtMaNV.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txtHoNV.getText().equals("")) {
            txtHoNV.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txtTenNV.getText().equals("")) {
            txtTenNV.setBorder(BorderFactory.createEmptyBorder());
        }
//        if(!txtNgaysinh.getText().equals(""))
//            txtNgaysinh.setBorder(BorderFactory.createEmptyBorder());
//        if (!txtGioitinh.getText().equals("")) {
//            txtGioitinh.setBorder(BorderFactory.createEmptyBorder());
//        }
        if (!txtDiachi.getText().equals("")) {
            txtDiachi.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txtCMND.getText().equals("")) {
            txtCMND.setBorder(BorderFactory.createEmptyBorder());
        }
        if (!txtSdt.getText().equals("")) {
            txtSdt.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private boolean check() {
        NhanVienDTO nv = new NhanVienDTO();

        if (!CongCu.checkMa(txtMaNV.getText())) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên chỉ bao gồm chữ cái và số");
            return false;
        } else if (!CongCu.checkNames(txtHoNV.getText())) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên chỉ bao gồm chữ cái");
            return false;
        } else if (!CongCu.isLength15(txtHoNV.getText())) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên không quá 15 kí tự");
            return false;

        } else if (!CongCu.checkNames(txtTenNV.getText())) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên chỉ bao gồm chữ cái");
            return false;
        } else if (!CongCu.isLength15(txtTenNV.getText())) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không quá 15 kí tự");
            return false;

        } else if (jdngaysinh.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
            return false;

//        } else if () {
//            JOptionPane.showMessageDialog(null, "Giới tính chỉ bao gồm 'Nam' hoặc 'nữ'");
//            return false;
        } else if (!CongCu.checkCMND(txtCMND.getText())) {
            JOptionPane.showMessageDialog(null, "CMND chỉ bao gồm số và có 9 hoặc 12 số");
            return false;
        } else if (!CongCu.checkPhoneNumes(txtSdt.getText())) {
            JOptionPane.showMessageDialog(null, "SĐT chỉ bao gồm số và không quá 10 số");
            return false;
        }
        return true;
    }

    private boolean checktrung(String manv, String sdt, String cmnd) {
        NhanVienBUS bus = new NhanVienBUS();
        if (!bus.kiemtramatrung(manv)) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại");
            return false;
        }
        if (!bus.kiemtrasdt(sdt)) {
            JOptionPane.showMessageDialog(null, "SĐT đã tồn tại");
            return false;
        }
        if (!bus.kiemtracmnd(cmnd)) {
            JOptionPane.showMessageDialog(null, "CMND đã tồn tại");
            return false;
        }
        return true;
    }

    private void btThemMouseClicked() {
        NhanVienDTO nv = new NhanVienDTO();
        NhanVienBUS bus = new NhanVienBUS();
        CongCu a = new CongCu();
        String manv = txtMaNV.getText();
        String sdt = txtSdt.getText();
        String cmnd = txtCMND.getText();
        if (checktrung(manv, sdt, cmnd)) {
            if (kiemtra() == 0) {
                checkempty();
                checkfilled();
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            } else {
                if (check()) {
                    nv.setMaNV(txtMaNV.getText());
                    nv.setHoNV(txtHoNV.getText());
                    nv.setTenNV(txtTenNV.getText());
                    DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                    nv.setNgaysinh(date.format(jdngaysinh.getDate()).toString());
                    nv.setGioitinh(cbbgioitinh.getSelectedItem().toString());
                    nv.setCMND(txtCMND.getText());
                    nv.setDiachi(txtDiachi.getText());
                    nv.setSdt(txtSdt.getText());
                    nv.setTrangThai(cbbtt.getSelectedItem().toString());
                    bus.themNV(nv);//thêm sách bên BUS đã có thêm vào database
                    Add_header();
                    Add_row(nv);
                    checkfilled();
                    JOptionPane.showMessageDialog(null, "Thêm thành công");

                }
            }
        }
//                button[1].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				frmThemTK frmThemTK = new frmThemTK();
//				frmThemTK.setVisible(true);
//
//			}
//		});
    }

    private void btXoaMouseClicked() {

        NhanVienDTO nv = new NhanVienDTO();
        NhanVienBUS bus = new NhanVienBUS();
        int i = tblQLNV.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 sản phẩm");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.removeRow(i);
                tblQLNV.setModel(model);
                bus.xoaNV(nv, i);
            }
        }
    }

    private void btSuaMouseClicked() {
        NhanVienDTO nv = new NhanVienDTO();
        NhanVienBUS bus = new NhanVienBUS();

        int i = tblQLNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhân viên muốn sửa");
        }

        tblQLNV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("123");
            }
        });
        ListSelectionModel model1 = tblQLNV.getSelectionModel();
        model1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int row = tblQLNV.getSelectedRow();
                System.out.println("baby ới");
                if (row > 0) {
                       
                        txtMaNV.setText(tblQLNV.getValueAt(row, 0).toString());
                        txtMaNV.setEnabled(false);
                        txtHoNV.setText(tblQLNV.getValueAt(row, 1).toString());
                        txtTenNV.setText(tblQLNV.getValueAt(row, 2).toString());
                        //txtNgaysinh.setText(tblQLNV.getValueAt(row, 3).toString());
//                    jdngaysinh.setDate((Date) tblQLNV.getValueAt(row,3));
                        String date = (String) tblQLNV.getValueAt(row, 3);
                        try {
                            jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                        } catch (ParseException ex) {
                            Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        cbbgioitinh.setSelectedItem(tblQLNV.getValueAt(row, 4).toString());
                        txtCMND.setText(tblQLNV.getValueAt(row, 5).toString());
                        txtDiachi.setText(tblQLNV.getValueAt(row, 6).toString());
                        txtSdt.setText(tblQLNV.getValueAt(row, 7).toString());
                        cbbtt.setSelectedItem(tblQLNV.getValueAt(row, 8).toString());

                    
                }
            }
        });
        // tblQLS.addMouseListener(this);
        if (i >= 0) {

            model.setValueAt(txtMaNV.getText(), i, 0);//model là ruột JTable.set giá trị cho model
            model.setValueAt(txtHoNV.getText(), i, 1);
            model.setValueAt(txtTenNV.getText(), i, 2);
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            model.setValueAt(dateformat.format(jdngaysinh.getDate()), i, 3);
            model.setValueAt(cbbgioitinh.getSelectedItem(), i, 4);
            model.setValueAt(txtCMND.getText(), i, 5);
            model.setValueAt(txtDiachi.getText(), i, 6);
            model.setValueAt(txtSdt.getText(), i, 7);
            model.setValueAt(cbbtt.getSelectedItem(), i, 8);
            tblQLNV.setModel(model);
            nv.setMaNV(txtMaNV.getText());//nap du lieu vao doi tuong(textfield)
            nv.setHoNV(txtHoNV.getText());
            nv.setTenNV(txtTenNV.getText());
            // nv.setNgaysinh(txtNgaysinh.getText());
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            nv.setNgaysinh(date.format(jdngaysinh.getDate()));
            nv.setGioitinh(cbbgioitinh.getSelectedItem().toString());
            nv.setCMND(txtCMND.getText());
            nv.setDiachi(txtDiachi.getText());
            nv.setSdt(txtSdt.getText());
            nv.setTrangThai(cbbtt.getSelectedItem().toString());

        }
        bus.suaNV(i, nv);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }

    private void CHON(String conditions, String text) {
        NhanVienBUS bus = new NhanVienBUS();
        switch (conditions) {
            case "Mã NV":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_manv(text);
                Search();
                break;
            case "Tên NV":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_tennv(text);
                Search();
                break;
            case "SDT":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiemsdt(text);
                Search();
                break;

        }
    }

    private void Search() {
        DefaultTableModel mode = new DefaultTableModel(header, 0);
        if (kq.size() != 0) {
            for (NhanVienDTO nv : kq) {
                Vector row = new Vector();
                row.add(nv.getMaNV());
                row.add(nv.getHoNV());
                row.add(nv.getTenNV());
                row.add(nv.getNgaysinh());
                row.add(nv.getGioitinh());
                row.add(nv.getCMND());
                row.add(nv.getDiachi());
                row.add(nv.getSdt());
                row.add(nv.getTrangThai());
                mode.addRow(row);
            }
            tblQLNV.setModel(mode);

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
        NhanVienBUS a = new NhanVienBUS();
        dsnv = a.docnv();
        model.setRowCount(0);
        for (NhanVienDTO nv : dsnv) {
            model.addRow(new Object[]{nv.getMaNV(), nv.getHoNV(), nv.getTenNV(),
                nv.getNgaysinh(), nv.getGioitinh(), nv.getCMND(), nv.getDiachi(), nv.getSdt(), nv.getTrangThai()});
        }
        tblQLNV.setModel(model);
        p3.removeAll();
        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();

    }

    public void btDSXoaMouseClicked() {
        NhanVienBUS a = new NhanVienBUS();
        dsnvan = a.docnvan();
        model.setRowCount(0);
        for (NhanVienDTO nv : dsnvan) {

            model.addRow(new Object[]{nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), nv.getNgaysinh(), nv.getGioitinh(), nv.getCMND(), nv.getDiachi(), nv.getSdt(), nv.getTrangThai()});

        }
        tblQLNV.setModel(model);
        p3.removeAll();
        p3.add(scrollPanel);
        p3.revalidate();
        p3.repaint();
    }

    public void btXuatExcelMouseClicked() {
        JFileChooser file = new JFileChooser(); //Khởi tạo JFileChooser

        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách nhân viên");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = excelSheet.createRow((short) 1);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH NHÂN VIÊN");

            row = excelSheet.createRow((short) 2);
            row.setHeight((short) 400);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tên NV");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ NV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Gioi tính");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("CMND");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("SĐT");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Trạng thái");

            for (int i = 0; i < tblQLNV.getRowCount(); i++) {
                row = excelSheet.createRow((short) 3 + i);
                row.setHeight((short) 400);
                for (int j = 0; j < tblQLNV.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(tblQLNV.getValueAt(i, j).toString());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Thêm")) {
            btThemMouseClicked();
        }
        if (src.equals("Xóa")) {
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
        if (src.equals("Xuất excel")) {
            btXuatExcelMouseClicked();
        }
        if (src.equals("DS Xóa")) {
            btDSXoaMouseClicked();
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("aaaa");
        if (e.getSource().equals(tblQLNV)) {
            int row = tblQLNV.getSelectedRow();
            System.out.println("baby ới");
            if (row >= 0) {
                txtMaNV.setText(tblQLNV.getValueAt(row, 0).toString());
                txtMaNV.setEnabled(false);
                txtHoNV.setText(tblQLNV.getValueAt(row, 1).toString());
                txtTenNV.setText(tblQLNV.getValueAt(row, 2).toString());
                String date = (String) tblQLNV.getValueAt(row, 3);
                try {
                    jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                } catch (ParseException ex) {
                    Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                // txtNgaysinh.setText(tblQLNV.getValueAt(row, 3).toString());
                //jdngaysinh.setDate((tblQLNV.getValueAt(row, 3).toString()));
                cbbgioitinh.setSelectedItem(tblQLNV.getValueAt(row, 4).toString());
                txtCMND.setText(tblQLNV.getValueAt(row, 5).toString());
                txtDiachi.setText(tblQLNV.getValueAt(row, 6).toString());
                txtSdt.setText(tblQLNV.getValueAt(row, 7).toString());
                cbbtt.setSelectedItem(tblQLNV.getValueAt(row, 8).toString());
                txtCMND.setEnabled(false);
            }
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
