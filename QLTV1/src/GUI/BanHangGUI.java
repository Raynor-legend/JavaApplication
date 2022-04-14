
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CTHDBUS;
import BUS.CongCu;
import BUS.HienHanh;
import BUS.HoaDonBUS;
import BUS.SachBUS;
import DAO.Connect;
import DAO.SachDAO;
import DTO.CTHDDTO;
import DTO.HoaDonDTO;
import DTO.SachDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class BanHangGUI extends JPanel implements ActionListener, MouseListener {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCTHD = new DefaultTableModel();
    JScrollPane scroll = new JScrollPane();
    public JTable tblSP, tblCTHD;
    public JTextField txMaHD, txNgayLap, txMaNV, txTimKiem;
    public static JTextField txMaKH, txTong;
    public ArrayList<SachDTO> kq;
    public JComboBox cbbtk;
    JButton bttimkiem;
    JPanel p2, p3;
    public static ArrayList<SachDTO> dssach = new ArrayList<>();

    Vector header;

    public BanHangGUI() {
        initComponents();
        SachBUS bus = new SachBUS();
        if (SachBUS.dssach == null) {
            bus.docDSSACH();
        }
        Add_header();
        for (SachDTO sach : SachBUS.dssach) {
            Add_row(sach);
        }

    }

    public void initComponents() {
        setLayout(null);
        setBounds(0, 0, 1200, 800);
        setFont(new Font("Arial", 15, 0));
        setBackground(Color.decode("#78909C"));
        JPanel p1 = hoadon();
        p1.setBackground(Color.decode("#78909C"));
        p1.setBounds(600, 30, 1300, 100);

        p2 = sanpham();
        p2.setBackground(Color.decode("#78909C"));
        p2.setBounds(0, 0, 580, 700);

        p3 = giohang();
        p3.setBackground(Color.decode("#78909C"));
        p3.setBounds(590, 150, 700, 700);

        add(p1);
        add(p2);
        add(p3);

        setVisible(true);
    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("Mã sách");
        header.add("Tên Sách");
        header.add("Số lượng");
        header.add("Đơn giá bán");
        if (modelCTHD.getRowCount() == 0) {
            modelCTHD = new DefaultTableModel(header, 0);
        }
    }
//

    private void Add_row(SachDTO sach) {
        Vector row = new Vector();
        row.add(sach.getMaSach());
        row.add(sach.getTenSach());
        row.add(sach.getSoLuong());
        row.add(sach.getDonGia());
        modelCTHD.addRow(row);
        tblSP.setModel(modelCTHD);
    }

    public JPanel hoadon() {
        JPanel phoadon = new JPanel();
        phoadon.setLayout(null);
        JLabel lbmakh = new JLabel("Mã khách hàng");
        lbmakh.setBounds(100, 30, 200, 30);
        lbmakh.setForeground(Color.WHITE);
        lbmakh.setFont(new Font("Arial", Font.BOLD, 20));

//        JTextField txMaKH = new JTextField();
//        txMaKH.setText(HienHanh.getKhachHang());
        HienHanh.setKhachHang("0");
//        txMaKH.setBounds(300, 30, 200, 30);

        JButton bttimkiem = new JButton("Mã khách hàng");
        bttimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemKHGUI frmTimKiemKH = new TimKiemKHGUI();
                frmTimKiemKH.setVisible(true);

            }
        });

        bttimkiem.setBounds(300, 30, 150, 30);

        phoadon.add(lbmakh);
//        phoadon.add(txMaKH);
        //  phoadon.add(txtimkiem);
        phoadon.add(bttimkiem);

        return phoadon;
    }

    public JPanel sanpham() {
        JPanel psanpham = new JPanel();
        psanpham.setLayout(null);
        psanpham.setBackground(Color.orange);
        JLabel lbTimKiem = new JLabel("Tìm kiếm");
        lbTimKiem.setBounds(30, 40, 100, 30);
        lbTimKiem.setForeground(Color.WHITE);
        lbTimKiem.setFont(new Font("Arial", Font.BOLD, 20));

        txTimKiem = new JTextField();
        txTimKiem.setBounds(130, 40, 200, 30);

        String[] arr = {"Mã sách", "Tên sách"};
        cbbtk = new JComboBox(arr);
        cbbtk.setBounds(350, 40, 100, 30);
        cbbtk.setSelectedIndex(0);

        bttimkiem = new JButton("Tìm kiếm");
        bttimkiem.setBounds(460, 40, 140, 30);
        bttimkiem.setForeground(Color.BLACK);
        bttimkiem.setFont(new Font("Arial", Font.BOLD, 20));
        bttimkiem.setBackground(Color.decode("#FFCA28"));
        bttimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Tìm kiếm")) {
                    timkiemMouseClicked();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        JButton btchon = new JButton("Chọn hàng");
        btchon.setBounds(420, 650, 150, 40);
        btchon.setForeground(Color.BLACK);
        btchon.setFont(new Font("Arial", Font.BOLD, 20));
        btchon.setBackground(Color.decode("#FFCA28"));
        btchon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Chọn hàng")) {
                    btchonHangMouseClicked();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        JButton btql = new JButton("Quay lại");
        btql.setBounds(10, 650, 150, 40);
        btql.setForeground(Color.BLACK);
        btql.setFont(new Font("Arial", Font.BOLD, 20));
        btql.setBackground(Color.decode("#FFCA28"));
        btql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Quay lại")) {
                    btnCapnhatMouseClicked();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JButton btcn = new JButton("Cập nhật");
        btcn.setBounds(250, 650, 150, 40);
        btcn.setForeground(Color.BLACK);
        btcn.setFont(new Font("Arial", Font.BOLD, 20));
        btcn.setBackground(Color.decode("#FFCA28"));
        btcn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Cập nhật")) {
                    btnCapnhatMouseClicked();
                }
            }

        });
        header = new Vector();
        header.add("Mã sách");
        header.add("Tên sách");
        header.add("Số lượng");
        header.add("Đơn giá bán");

        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        tblSP = new JTable(null, header);
        

        tblSP.setFont(new Font("Arial", 0, 15));
        tblSP.setModel(model);//add model len table
        tblSP.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        tblSP.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblSP.getTableHeader().setPreferredSize(new Dimension(50, 50));//set độ dài độ rộng của header
        tblSP.getTableHeader().setBackground(Color.pink);//set background cho header

        scroll = new JScrollPane(tblSP);
        scroll.setBounds(40, 100, 500, 530);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        tblSP.setPreferredSize(new Dimension(300, 300));
        scroll.setPreferredSize(new Dimension(300, 500));

        psanpham.add(scroll); // add table vào scrollPanel
        tblSP.setFillsViewportHeight(true);//hiển thị table
        psanpham.add(txTimKiem);
        psanpham.add(lbTimKiem);
        psanpham.add(bttimkiem);
        psanpham.add(cbbtk);
        psanpham.add(btchon);
        psanpham.add(btql);
        psanpham.add(btcn);

        return psanpham;
    }

    public JPanel giohang() {
        JPanel pgiohang = new JPanel();
        pgiohang.setLayout(null);
        pgiohang.setBackground(Color.decode("#78909C"));
        JLabel lbChiTiet = new JLabel("CHI TIẾT GIỎ HÀNG");
        lbChiTiet.setBounds(250, 0, 200, 50);
        lbChiTiet.setForeground(Color.WHITE);
        lbChiTiet.setFont(new Font("Arial", BOLD, 20));

        JButton btXoa = new JButton("Xóa");
        btXoa.setBounds(10, 500, 100, 40);
        btXoa.setFont(new Font("Arial", Font.BOLD, 20));
        btXoa.setForeground(Color.BLACK);
        btXoa.setBackground(Color.decode("#FFCA28"));

        btXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Xóa")) {
                    btXoaMouseClicked();
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        JButton btThanhtoan = new JButton("Thanh toán");
        btThanhtoan.setFont(new Font("Arial", Font.BOLD, 20));
        btThanhtoan.setForeground(Color.BLACK);
        btThanhtoan.setBackground(Color.decode("#FFCA28"));
        btThanhtoan.setBounds(500, 500, 150, 40);

        btThanhtoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Thanh toán")) {
                    btThanhToanMouseClicked();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        header = new Vector();
        header.add("STT");
        header.add("Mã sách");
        header.add("Tên sách");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Tổng cộng");

        if (modelCTHD.getRowCount() == 0) {
            modelCTHD = new DefaultTableModel(header, 0);
        }
        tblCTHD = new JTable(null, header) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        

        tblCTHD.setFont(new Font("Arial", 0, 15));
        tblCTHD.setModel(modelCTHD);//add model len table
        tblCTHD.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        tblCTHD.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        tblCTHD.getTableHeader().setPreferredSize(new Dimension(50, 50));//set độ dài độ rộng của header
        tblCTHD.getTableHeader().setBackground(Color.pink);//set background cho header

        scroll = new JScrollPane(tblCTHD);
        scroll.setBounds(60, 50, 450, 430);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        tblCTHD.setPreferredSize(new Dimension(300, 300));
        scroll.setPreferredSize(new Dimension(300, 300));

        pgiohang.add(scroll); // add table vào scrollPanel
        tblSP.setFillsViewportHeight(true);//hiển thị table
        pgiohang.add(lbChiTiet);
        pgiohang.add(btXoa);
        pgiohang.add(btThanhtoan);
  

        return pgiohang;
    }

    private void btchonHangMouseClicked() {
        System.out.println("ftfjcggiygk");
        ThemGioHang();
    }

    private void btXoaMouseClicked() {
        xoaCTHD();
    }

    private void btThanhToanMouseClicked() {
        ThanhToan();
    }

    private void CHON(String condition, String text) {
        SachBUS bus = new SachBUS();
        switch (condition) {
            case "Mã sách":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_masach(text);
                Search();
                break;

            case "Tên sách":
                Add_header();
                model.setRowCount(0);
                kq = bus.timkiem_tensach(text);
                Search();
                break;

        }
    }

    private void Search() {
        // DefaultTableModel mode = new DefaultTableModel(header, 0);
        model.setRowCount(0);
        if (kq.size() != 0) {
            for (SachDTO sach : kq) {
                Vector row = new Vector();
                row.add(sach.getMaSach());
                row.add(sach.getTenSach());
                row.add(sach.getSoLuong());
                row.add(sach.getDonGia());
                model.addRow(row);
            }
            tblSP.setModel(model);

        }
    }

    private void timkiemMouseClicked() {
        String selectedItem = (String) cbbtk.getSelectedItem();
        String tukhoa = txTimKiem.getText();
        if (tukhoa.isEmpty()) {
            btnCapnhatMouseClicked();
        } else {
            CHON(selectedItem, tukhoa);
        }
    }

    private void btnCapnhatMouseClicked() {
        SachDAO a = new SachDAO();
        dssach = a.docSach();
        //Add_header();
        model.setRowCount(0);
        for (SachDTO s : dssach) {
            model.addRow(new Object[]{s.getMaSach(), s.getTenSach(), s.getSoLuong(), s.getDonGia()});
        }
        tblSP.setModel(model);

//        p2.remove(tblSP);
//        p2.add(scroll);
        p2.revalidate();
        p2.repaint();
    }


    private void ThemGioHang() {
        DefaultTableModel modelCTHD = (DefaultTableModel) tblCTHD.getModel();
        int soLuongThem = 0;
        boolean checkInput = false;
        int checkDaCo = 0;

        if (tblSP.getSelectedRow() != -1) {
            int sLSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 2).toString()); // Hư ở đây

            do {
                checkInput = false;
                String soLuongThemStr = JOptionPane.showInputDialog("Chọn số lượng muốn nhập ");

                if (soLuongThemStr != null) {

                    try {
                        soLuongThem = Integer.parseInt(soLuongThemStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên");
                        checkInput = true;
                    }
                    if ((soLuongThem <= sLSP) && (soLuongThem != 0)) {
                        tblSP.setValueAt(sLSP - soLuongThem, tblSP.getSelectedRow(), 2);

                        String maSP = tblSP.getValueAt(tblSP.getSelectedRow(), 0).toString();
                        String tenSP = tblSP.getValueAt(tblSP.getSelectedRow(), 1).toString();

                        int donGiaSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 3).toString());

                        int thanhTien = soLuongThem * donGiaSP;
                        if (tblCTHD.getRowCount() == 0) {
                            Object[] row = {tblCTHD.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem, String.valueOf(thanhTien)};
                            modelCTHD.addRow(row);
                        } else {
                            checkDaCo = 0;
                            for (int i = 0; i < tblCTHD.getRowCount(); i++) {
                                if (maSP == tblCTHD.getValueAt(i, 1).toString()) {
                                    checkDaCo = 1;
                                    int soLuongDaCo = Integer.parseInt(tblCTHD.getValueAt(i, 4).toString());
                                    tblCTHD.setValueAt(soLuongDaCo + soLuongThem, i, 4);
                                    tblCTHD.setValueAt(((soLuongDaCo + soLuongThem) * donGiaSP), i, 5);
                                    break;
                                }
                            }
                            if (checkDaCo != 1) {
                                Object[] row = {tblCTHD.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem, String.valueOf(thanhTien)};
                                modelCTHD.addRow(row);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Trong kho hàng chỉ còn" + sLSP);
                        checkInput = true;
                    }

                } else {
                    checkInput = false;
                }
            } while (checkInput);

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn thêm");
        }

        this.TinhTongTien();
    }

    private void TinhTongTien() {
        int tamTinh = 0;
        int tongTien = 0;

        for (int i = 0; i < tblCTHD.getRowCount(); i++) {
            String thanhTienSPStr = tblCTHD.getValueAt(i, 5).toString();
            // int thanhTienSP = CongCu.FormatTienStringSangInt(thanhTienSPStr);

            tamTinh += Integer.parseInt(thanhTienSPStr);
            tongTien += Integer.parseInt(thanhTienSPStr);
        }

        String tamTinhStr = String.valueOf(tamTinh);
        HienHanh.setTongTien(String.valueOf(tongTien));
        System.out.println(tamTinh);

    }

    private void xoaCTHD() {
        DefaultTableModel modelCTHD = (DefaultTableModel) tblCTHD.getModel();

        if (tblCTHD.getSelectedRow() != -1) {
            int rowDaChon = tblCTHD.getSelectedRow();
            String maSPGioHang = tblCTHD.getValueAt(rowDaChon, 1).toString();
            int soLuongSPGH = Integer.parseInt(tblCTHD.getValueAt(rowDaChon, 4).toString());

            for (int i = 0; i < tblSP.getRowCount(); i++) {
                String maSP = tblSP.getValueAt(i, 0).toString();
                if (maSP.equals(maSPGioHang)) {
                    int soLuongSP = Integer.parseInt(tblSP.getValueAt(i, 2).toString());
                    tblSP.setValueAt(soLuongSP + soLuongSPGH, i, 2);
                    modelCTHD.removeRow(tblCTHD.getSelectedRow());
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chua chon san pham muon xoa");
        }

        this.TinhTongTien();
        txTong.setText(HienHanh.getTongTien());
    }

    private void ThanhToan() {
        SachDTO sanphamDTO = new SachDTO();
        SachBUS sachBUS = new SachBUS();
        HoaDonDTO hoadonDTO = new HoaDonDTO();
        HoaDonBUS hoadonBUS = new HoaDonBUS();
        CTHDDTO chitiethdDTO = new CTHDDTO();
        CTHDBUS chitiethdBUS = new CTHDBUS();
        boolean check = false;
        if (tblCTHD.getRowCount() != 0) {

            txMaHD = new JTextField();
            txMaNV = new JTextField();
            int ma = hoadonBUS.getMa();
            hoadonDTO.setMaHD(ma);
            //txMaKH.setText(String.valueOf(HienHanh.KhachHang));
            hoadonDTO.setMaKH(HienHanh.getKhachHang());
            hoadonDTO.setMaNV(HienHanh.NhanVien);
            hoadonDTO.setNgayLap(LocalDate.now());
            hoadonDTO.setThanhTien(String.valueOf(HienHanh.getTongTien()));
            hoadonBUS.themHD(hoadonDTO);
            try {

                for (int i = 0; i < tblCTHD.getRowCount(); i++) {
                    chitiethdDTO.setSTT(chitiethdBUS.getSTT());
                    chitiethdDTO.setMaHD(ma);
                    chitiethdDTO.setMaSach(tblCTHD.getValueAt(i, 1).toString());
                    chitiethdDTO.setTenSach(tblCTHD.getValueAt(i, 2).toString());
                    chitiethdDTO.setGiaBan(tblCTHD.getValueAt(i, 3).toString());

                    chitiethdDTO.setSoluong(tblCTHD.getValueAt(i, 4).toString());
                    chitiethdDTO.setThanhTien(tblCTHD.getValueAt(i, 5).toString());
                    chitiethdBUS.themCTHD(chitiethdDTO);
                    for (int k = 0; k < tblSP.getRowCount(); k++) {
                        String maSachCT = tblCTHD.getValueAt(i, 1).toString();
                        String maSach = tblSP.getValueAt(k, 0).toString();
                        if (maSachCT.equals(maSach)) {
                            sanphamDTO.setDonGia(tblCTHD.getValueAt(i, 3).toString());
                            sanphamDTO.setSoLuong(tblSP.getValueAt(k, 2).toString());

                            sanphamDTO.setMaSach(maSach);

                            sachBUS.sua1Sach(k, sanphamDTO);
                        }
                    }

                    XuatHoaDon(ma);
//                    txMaHD.setText("");
//                    txMaKH.setText("");
//                    txMaNV.setText("");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
        }
        DefaultTableModel modelCTHD = (DefaultTableModel) tblCTHD.getModel();
        modelCTHD.setRowCount(0);
    }

    private void XuatHoaDon(int maHDDaChon) {
        Connect ketnoiDB = new Connect();
        Connection conn = ketnoiDB.getConnect();

        try {

            HashMap hm = new HashMap();
            hm.put("MAHD", maHDDaChon);

            File fileTemp = new File("src/GUI/XuatHoaDon.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(fileTemp.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(jreport, hm, conn);

            JasperViewer.viewReport(jprint, false);

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public static void main(String[] args) {
        BanHangGUI b = new BanHangGUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
