/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongCu;
import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHang;
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
import java.util.ArrayList;
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
public class KhachHangGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	JButton[] button;
	public JTable tblQLKH;
	public JTextField txtMaKH, txtHoKH, txtTenKH, txtNgaysinh, txtGioitinh, txtDiachi, txtSdt, txMail;
	public JTextField txTimKiem;
	public JComboBox box1;
	Vector header;
	JPanel p3;
	JDateChooser jdngaysinh;
	JScrollPane scrollPanel;
	public static JPanel phienthi, QLKH;
	ArrayList<KhachHang> dskh = new ArrayList<>();
	ArrayList<KhachHang> kq;

	public KhachHangGUI() {
		initComponents();
		KhachHangBUS kh = new KhachHangBUS();
		if (KhachHangBUS.dskh == null) {
			kh.docDSKH();
		}
		Add_header();
		for (KhachHang khachhang : KhachHangBUS.dskh) {
			Add_row(khachhang);
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		jdngaysinh.setDateFormatString("yyyy-MM-dd");

	}

	public void initComponents() {
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));

		phienthi = QLKH();
		phienthi.setBounds(0, 0, 1200, 800);
		phienthi.setBackground(Color.decode("#78909C"));
		String[] timkiem = { "M?? KH", "T??n KH", "S??T" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(520, 50, 100, 30);
		box1.setSelectedIndex(0);

		phienthi.add(box1);
		JPanel p2 = CHUCNANG();
		p2.setBounds(700, 0, 700, 450);
		p2.setBackground(Color.decode("#78909C"));

		p3 = TableKH();
		p3.setBounds(100, 450, 1000, 250);// set v??? tr?? so v???i phienthi
		p3.setBackground(Color.decode("#78909C"));

		phienthi.add(p2);
		phienthi.add(p3);
		add(phienthi);
		setVisible(true);
		tblQLKH.addMouseListener(this);

	}

	public JPanel QLKH() {
		QLKH = new JPanel();
		QLKH.setLayout(null);
		JLabel[] label;
		label = new JLabel[9];
		JTextField[] textfield;
		// SanphamGUI a=new SanphamGUI();
		// a.tableMouseClicked(e);
		String[] arrNV = { "T??m ki???m", "M?? kh??ch h??ng", "H??? kh??ch h??ng", "T??n kh??ch h??ng", "Ng??y sinh", "Gi???i t??nh",
				"?????a ch???", "S??? ??i???n tho???i", "Mail" };

		int toadoxLabel = 150, toadoyLabel = 50;
		int toadoxTextField = 300, toadoyTextField = 50;
		for (int i = 0; i < arrNV.length; i++) {
			label[i] = new JLabel(arrNV[i]); // kh???i t???o ?????i t?????ng thu???c Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 150, 30); // ??inh v??? tr?? cho t???ng ?????i t?????ng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh ph???i cho text button
			label[i].setName("btn" + i);
			label[i].setBackground(Color.decode("#78909C"));
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			label[i].setForeground(Color.WHITE);
			System.out.println(label[i]);
			QLKH.add(label[i]);
			toadoyLabel = toadoyLabel + 40;
			switch (i) {
			case 0: {
				txTimKiem = new JTextField();
				txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txTimKiem);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 1: {
				txtMaKH = new JTextField();
				txtMaKH.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtMaKH);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 2: {
				txtHoKH = new JTextField();
				txtHoKH.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtHoKH);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 3: {
				txtTenKH = new JTextField();
				txtTenKH.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtTenKH);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 4: {
//                    txtNgaysinh = new JTextField();
//                    txtNgaysinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);
//                    QLKH.add(txtNgaysinh);
//                    toadoyTextField = toadoyTextField + 40;
//                    break;
				jdngaysinh = new JDateChooser();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				jdngaysinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(jdngaysinh);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 5: {
				txtGioitinh = new JTextField();
				txtGioitinh.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtGioitinh);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 6: {
				txtDiachi = new JTextField();
				txtDiachi.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtDiachi);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 7: {
				txtSdt = new JTextField();
				txtSdt.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txtSdt);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 8: {
				txMail = new JTextField();
				txMail.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLKH.add(txMail);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			}
		}
		txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txtHoKH.requestFocus();
				}
			}
		});
		txtHoKH.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txtTenKH.requestFocus();
				}
			}
		});
		txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txtNgaysinh.requestFocus();
				}
			}
		});
//        txtNgaysinh.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txtGioitinh.requestFocus();
//                }
//            }
//        });
		txtGioitinh.addKeyListener(new java.awt.event.KeyAdapter() {
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
		txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txMail.requestFocus();
				}
			}
		});
		return QLKH;
	}

	public JPanel CHUCNANG() {
		JPanel pchucnang = new JPanel();
		pchucnang.setLayout(null);
		button = new JButton[6];
		String[] arrchucnang = { "T??m ki???m", "Th??m", "S???a", "Hi???n th???", "L??m m???i", "Xu???t excel" };
		// Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
		int toadoxButton = 50, toadoyButton = 50;
		for (int i = 0; i < arrchucnang.length; i++) {
			if (i == 3) {
				toadoxButton = 300;
				toadoyButton = 50;
			}
			button[i] = new JButton(arrchucnang[i]); // kh???i t???o ?????i t?????ng thu???c Jbutton
			button[i].setBounds(toadoxButton, toadoyButton, 150, 30); // ??inh v??? tr?? cho t???ng ?????i t?????ng button
			button[i].setHorizontalAlignment(JButton.CENTER); // canh ph???i cho text button
			button[i].setName("btn" + i);
			button[i].setBackground(Color.decode("#FFCA28"));
			button[i].setFont(new Font("Arial", Font.BOLD, 20));
			System.out.println(button[i]);
			pchucnang.add(button[i]);
			toadoyButton = toadoyButton + 110;
			button[i].addActionListener((ActionListener) this);
		}
		return pchucnang;

	}

	public JPanel TableKH() {
		JPanel ptablekh = new JPanel();
		ptablekh.setLayout(null);
		ptablekh.setBackground(Color.decode("#FFCA28"));
		header = new Vector();
		header.add("M?? kh??ch h??ng");
		header.add("H??? kh??ch h??ng");
		header.add("T??n kh??ch h??ng");
		header.add("Ng??y sinh");
		header.add("Gi???i t??nh");
		header.add("?????a ch???");
		header.add("S??? ??i???n tho???i");
		header.add("Mail");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblQLKH = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblQLKH.setBounds(0, 0, 1000, 250);

		tblQLKH.setFont(new Font("Arial", 0, 15));
		tblQLKH.setModel(model);// add model len table
		tblQLKH.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tblQLKH.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblQLKH.getTableHeader().setPreferredSize(new Dimension(30, 50));// set ????? d??i ????? r???ng c???a header
		tblQLKH.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scrollPanel = new JScrollPane(tblQLKH);
		scrollPanel.setBounds(0, 0, 1000, 250);
		scrollPanel.setPreferredSize(new Dimension(500, 500));
		tblQLKH.setPreferredSize(new Dimension(500, 500));
		ptablekh.add(scrollPanel); // add table v??o scrollPanel
		tblQLKH.setFillsViewportHeight(true);// hi???n th??? table

		return ptablekh;

	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("M?? kh??ch h??ng");
		header.add("H??? kh??ch h??ng");
		header.add("T??n kh??ch h??ng");
		header.add("Ng??y sinh");
		header.add("Gi???i t??nh");
		header.add("?????a ch???");
		header.add("S??? ??i???n tho???i");
		header.add("Mail");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(KhachHang kh) {
		Vector row = new Vector();
		row.add(kh.getMaKH());
		row.add(kh.getHoKH());
		row.add(kh.getTenKH());
		row.add(kh.getNgaysinh());
		row.add(kh.getGioitinh());
		row.add(kh.getDiachi());
		row.add(kh.getSdt());
		row.add(kh.getMail());
		model.addRow(row);
		tblQLKH.setModel(model);
	}

	public void btclearMouseClicked() {
		txtMaKH.setText("");
		txtHoKH.setText("");
		txtTenKH.setText("");
		jdngaysinh.setCalendar(null);
		txtGioitinh.setText("");
		txtDiachi.setText("");
		txtTenKH.setText("");
		txtMaKH.setEnabled(true);
		// txtNgaysinh.setText("");
//         DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//         jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse());
		txtSdt.setText("");
		txMail.setText(" ");
	}

	private int kiemtra() {
		if (txtMaKH.getText().equals("") || txtHoKH.getText().equals("") || txtTenKH.getText().equals("")
				|| txtGioitinh.getText().equals("") || txtDiachi.getText().equals("") || txtSdt.getText().equals("")
				|| txMail.getText().equals("")) {
			return 0;
		}
		return 1;
	}

	private void checkempty() {
		if (txtMaKH.getText().equals("")) {
			txtMaKH.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtHoKH.getText().equals("")) {
			txtHoKH.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtTenKH.getText().equals("")) {
			txtTenKH.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtNgaysinh.getText().equals("")) {
			txtNgaysinh.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtGioitinh.getText().equals("")) {
			txtGioitinh.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtDiachi.getText().equals("")) {
			txtDiachi.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txtSdt.getText().equals("")) {
			txtSdt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txMail.getText().equals("")) {
			txMail.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	private void checkfilled() {
		if (!txtMaKH.getText().equals("")) {
			txtMaKH.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txtHoKH.getText().equals("")) {
			txtHoKH.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txtTenKH.getText().equals("")) {
			txtTenKH.setBorder(BorderFactory.createEmptyBorder());
		}
//        if (!txtNgaysinh.getText().equals("")) {
//            txtNgaysinh.setBorder(BorderFactory.createEmptyBorder());
//        }
		if (!txtGioitinh.getText().equals("")) {
			txtGioitinh.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txtDiachi.getText().equals("")) {
			txtDiachi.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txtSdt.getText().equals("")) {
			txtSdt.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txMail.getText().equals("")) {
			txMail.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	private boolean checknhap() {
		if (!CongCu.checkChar(txtMaKH.getText()) || !CongCu.checkNames(txtHoKH.getText())
				|| !CongCu.checkNames(txtTenKH.getText()) || !CongCu.checkNames(txtGioitinh.getText())
				|| !CongCu.checkChar(txtDiachi.getText()) || !CongCu.checkPhoneNumes(txtSdt.getText())) {
			return false;
		}
		return true;
	}

	private boolean check() {
		if (!CongCu.checkChar(txtMaKH.getText())) {
			JOptionPane.showMessageDialog(null, "M?? kh??ch h??ng ch??? bao g???m ch??? c??i");
			return false;
		} else if (!CongCu.isLength10(txtMaKH.getText())) {
			JOptionPane.showMessageDialog(null, "M?? kh??ng qu?? 15 k?? t???");
			return false;
		} else if (!CongCu.checkNames(txtHoKH.getText())) {
			JOptionPane.showMessageDialog(null, "H??? kh??ch h??ng ch??? bao g???m ch??? c??i");
			return false;
		} else if (!CongCu.isLength15(txtHoKH.getText())) {
			JOptionPane.showMessageDialog(null, "H??? kh??ng qu?? 15 k?? t???");
			return false;
		} else if (!CongCu.checkNames(txtTenKH.getText())) {
			JOptionPane.showMessageDialog(null, "T??n kh??ch h??ng ch??? bao g???m ch??? c??i");
			return false;
		} else if (!CongCu.isLength15(txtTenKH.getText())) {
			JOptionPane.showMessageDialog(null, "T??n kh??ng qu?? 15 k?? t???");
			return false;
		} else if (jdngaysinh.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n ng??y sinh!");
			return false;
		} else if (!CongCu.checkNames(txtGioitinh.getText())) {
			JOptionPane.showMessageDialog(null, "Gi???i t??nh ch??? bao g???m ch??? c??i");
			return false;
		} else if (!CongCu.checkPhoneNumes(txtSdt.getText())) {
			JOptionPane.showMessageDialog(null, "S??T ch??? bao g???m s??? v?? kh??ng qu?? 10 s???");
			return false;
		} else if (!CongCu.checkGmail(txMail.getText())) {
			JOptionPane.showMessageDialog(null, "");
			return false;
		}
		return true;
	}

	private void btThemMouseClicked() {
		KhachHang kh = new KhachHang();
		KhachHangBUS bus = new KhachHangBUS();
		String makh = txtMaKH.getText();
		int i = bus.kt_trung_ma(makh);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "M?? kh??ch h??ng ???? t???n t???i");
		} else {
			if (kiemtra() == 0) {
				checkempty();
				checkfilled();
				JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ?????y ????? th??ng tin");
			} else {
				if (check()) {
					// Add_row(Sanpham);
					kh.setMaKH(txtMaKH.getText());
					kh.setHoKH(txtHoKH.getText());
					kh.setTenKH(txtTenKH.getText());
					DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					kh.setNgaysinh(date.format(jdngaysinh.getDate()));
//                        String date = (String) tblQLKH.getValueAt(row, 3);
//                    try {
//                        jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
//                    } catch (ParseException ex) {
//                        Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                      DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//                      kh.setNgaysinh(fm.format(jdngaysinh.getDate()));
					kh.setGioitinh(txtGioitinh.getText());
					kh.setDiachi(txtDiachi.getText());
					kh.setSdt(txtSdt.getText());
					kh.setMail(txMail.getText());
					bus.themKH(kh);// th??m s??ch b??n BUS ???? c?? th??m v??o database
					Add_header();
					Add_row(kh);
					checkfilled();
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
				}
			}
		}
	}

	private void btXoaMouseClicked() {

		KhachHang kh = new KhachHang();
		KhachHangBUS bus = new KhachHangBUS();
		System.out.println("Nhue hello");
		int i = tblQLKH.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "B???n c???n ph???i ch???n 1 kh??ch h??ng");
		} else {
			if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n xo?? kh??ng", "Th??ng b??o",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeRow(i);
				tblQLKH.setModel(model);
				bus.xoaKH(kh, i);

			}

		}

	}

	private void btSuaMouseClicked() {
		KhachHang kh = new KhachHang();
		KhachHangBUS bus = new KhachHangBUS();
		int i = tblQLKH.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n 1 kh??ch h??ng ban muon sua");
		}

		tblQLKH.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");
			}
		});
		ListSelectionModel model1 = tblQLKH.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = tblQLKH.getSelectedRow();
				System.out.println("baby ???i");
				if (row >= 0) {
					txtMaKH.setText(tblQLKH.getValueAt(row, 0).toString());
					txtMaKH.setEnabled(false);
					txtHoKH.setText(tblQLKH.getValueAt(row, 1).toString());
					txtTenKH.setText(tblQLKH.getValueAt(row, 2).toString());
					String date = (String) tblQLKH.getValueAt(row, 3);
					try {
						jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
					} catch (ParseException ex) {
						Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, ex);
					}

					txtGioitinh.setText(tblQLKH.getValueAt(row, 4).toString());
					txtDiachi.setText(tblQLKH.getValueAt(row, 5).toString());
					txtSdt.setText(tblQLKH.getValueAt(row, 6).toString());
					txMail.setText(tblQLKH.getValueAt(row, 7).toString());
				}
			}
		});
		tblQLKH.addMouseListener(this);
		if (i >= 0) {
			model.setValueAt(txtMaKH.getText(), i, 0);// model l?? ru???t JTable.set gi?? tr??? cho model
			model.setValueAt(txtHoKH.getText(), i, 1);
			model.setValueAt(txtTenKH.getText(), i, 2);
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			model.setValueAt(dateformat.format(jdngaysinh.getDate()), i, 3);
			model.setValueAt(txtGioitinh.getText(), i, 4);
			model.setValueAt(txtDiachi.getText(), i, 5);
			model.setValueAt(txtSdt.getText(), i, 6);
			model.setValueAt(txMail.getText(), i, 7);
			tblQLKH.setModel(model);
			kh.setMaKH(txtMaKH.getText());// nap du lieu vao doi tuong(textfield)
			kh.setHoKH(txtHoKH.getText());
			kh.setTenKH(txtTenKH.getText());
			DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			kh.setNgaysinh(fm.format(jdngaysinh.getDate()));
			kh.setGioitinh(txtGioitinh.getText());
			kh.setDiachi(txtDiachi.getText());
			kh.setSdt(txtSdt.getText());
			kh.setMail(txMail.getText());

		}
		bus.suaKH(i, kh);
		JOptionPane.showMessageDialog(null, "S???a th??nh c??ng");
	}

	private void CHON(String conditions, String text) {
		KhachHangBUS bus = new KhachHangBUS();
		switch (conditions) {
		case "M?? KH":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_makh(text);
			Search();
			break;
		case "T??n KH":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tenkh(text);
			Search();
			break;
		case "S??T":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_sdt(text);
			Search();
			break;
		}
	}

	private void Search() {
		DefaultTableModel mode = new DefaultTableModel(header, 0);
		if (kq.size() != 0) {
			for (KhachHang kh : kq) {
				Vector row = new Vector();
				row.add(kh.getMaKH());
				row.add(kh.getHoKH());
				row.add(kh.getTenKH());
				row.add(kh.getNgaysinh());
				row.add(kh.getGioitinh());
				row.add(kh.getDiachi());
				row.add(kh.getSdt());
				row.add(kh.getMail());
				mode.addRow(row);
			}
			tblQLKH.setModel(mode);

		}

	}

	private void bttimkiemMouseClicked() {
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			bthienthiMouseClicked();
		} else {

			CHON(selectedItem, tukhoa);
		}
	}

	public void bthienthiMouseClicked() {
		System.out.println("ch???y ????n ????y ch??a b???n");
		KhachHangDAO a = new KhachHangDAO();
		dskh = a.dockh();
		model.setRowCount(0);
		for (KhachHang kh : dskh) {
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.getNgaysinh(), kh.getGioitinh(),
					kh.getDiachi(), kh.getSdt(), kh.getMail() });
		}
		tblQLKH.setModel(model);
		p3.removeAll();
		p3.add(scrollPanel);
		p3.revalidate();
		p3.repaint();

	}

	public void btLamMoiMouseClicked() {

		txtDiachi.setText("");
		txtGioitinh.setText("");
		txtHoKH.setText("");
		txtTenKH.setText("");
		txtMaKH.setText("");
		txtMaKH.setEnabled(true);
		jdngaysinh.setCalendar(null);
		txtSdt.setText("");
		txMail.setText("");

	}

	public void btXuatExcelMouseClicked() {
		JFileChooser file = new JFileChooser(); // Kh???i t???o JFileChooser

		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			XSSFWorkbook excelWorkbook = new XSSFWorkbook();
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh s??ch kh??ch h??ng");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH S??CH KH??CH H??NG");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("M?? KH");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("T??n KH");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("H??? KH");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Ng??y sinh");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Gioi t??nh");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("?????a ch???");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("S??T");
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Mail");

			for (int i = 0; i < tblQLKH.getRowCount(); i++) {
				row = excelSheet.createRow((short) 3 + i);
				row.setHeight((short) 400);
				for (int j = 0; j < tblQLKH.getColumnCount(); j++) {
					row.createCell(j).setCellValue(tblQLKH.getValueAt(i, j).toString());
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

//    public static void main(String[] args) {
//        NhanvienGUI nv = new NhanvienGUI();
//        nv.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        nv.setLocationRelativeTo(nv);
//
//    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Th??m")) {
			btThemMouseClicked();
		}
		if (src.equals("X??a")) {
			btXoaMouseClicked();
		}
		if (src.equals("S???a")) {
			btSuaMouseClicked();
		}
		if (src.equals("T??m ki???m")) {
			bttimkiemMouseClicked();
		}
		if (src.equals("Hi???n th???")) {
			bthienthiMouseClicked();
		}
		if (src.equals("L??m m???i")) {
			btLamMoiMouseClicked();
		}
		if (src.equals("Xu???t excel")) {
			btXuatExcelMouseClicked();
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("aaaa");
		if (e.getSource().equals(tblQLKH)) {
			int row = tblQLKH.getSelectedRow();
			System.out.println("baby ???i");
			if (row >= 0) {
				txtMaKH.setText(tblQLKH.getValueAt(row, 0).toString());
				txtMaKH.setEnabled(false);
				txtHoKH.setText(tblQLKH.getValueAt(row, 1).toString());
				txtTenKH.setText(tblQLKH.getValueAt(row, 2).toString());
				String date = (String) tblQLKH.getValueAt(row, 3);
				try {
					jdngaysinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
				} catch (ParseException ex) {
					Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
				txtGioitinh.setText(tblQLKH.getValueAt(row, 4).toString());
				txtDiachi.setText(tblQLKH.getValueAt(row, 5).toString());
				txtSdt.setText(tblQLKH.getValueAt(row, 6).toString());
				txMail.setText(tblQLKH.getValueAt(row, 7).toString());
			}
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}
}
