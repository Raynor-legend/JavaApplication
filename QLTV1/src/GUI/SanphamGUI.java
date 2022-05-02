/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongCu;
import BUS.NXBBUS;
import BUS.SanphamBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DAO.SanphamDAO;
import DTO.NXBDTO;
import DTO.SanphamDTO;
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
public class SanphamGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel mode = new DefaultTableModel();
	JButton[] button;
	public JTable tblQLS;
	public JTextField txMaSanpham;
	public JTextField txTenSanpham;
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
	public static ArrayList<SanphamDTO> dsSanpham = new ArrayList<>();
	public static ArrayList<TacGiaDTO> dstg = new ArrayList<>();
	ArrayList<SanphamDTO> kq;

	public SanphamGUI() {
		initComponent();
		SanphamBUS bus = new SanphamBUS();
		if (SanphamBUS.dsSanpham == null) {
			bus.docDSSanpham();
		}
		Add_header();
		for (SanphamDTO Sanpham : SanphamBUS.dsSanpham) {
			Add_row(Sanpham);
		}

	}

	public void initComponent() {
		// addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));
		JPanel p1 = QuanLiSanpham();
		p1.setBounds(100, 0, 600, 450);
		p1.setBackground(Color.decode("#78909C"));
		String[] timkiem = { "Mã nước uống", "Tên nước uống", "Mã TG", "Mã TL" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(420, 100, 100, 30);
		box1.setSelectedIndex(0);
		p1.add(box1);

		JPanel p2 = CHUCNANG();
		p2.setBounds(650, 0, 600, 450);
		p2.setBackground(Color.decode("#78909C"));

		p3 = TabelSanpham();
		p3.setBounds(100, 450, 1000, 300);// set vị trí so với phienthi
		p3.setBackground(Color.decode("#78909C"));
		add(p1);
		add(p2);
		add(p3);
		setVisible(true);
		tblQLS.addMouseListener(this);

	}

	public JPanel QuanLiSanpham() {
		JPanel pQLS = new JPanel();
		pQLS.setLayout(null);
		JLabel[] label;
		label = new JLabel[8];
		JTextField[] textfield;
		String[] arrSanpham = { "Tìm kiếm", "Mã nước uống", "Tên nước uống", "Mã tác giả", "Mã thể loại", "Mã NXB",
				"Số lượng", "Đơn giá bán" };

		int toadoxLabel = 100, toadoyLabel = 100;
		int toadoxTextField = 200, toadoyTextField = 100;
		for (int i = 0; i < arrSanpham.length; i++) {
			label[i] = new JLabel(arrSanpham[i]); // khỡi tạo đối tượng thuộc Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); // đinh vị trí cho từng đối tượng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
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
				txMaSanpham = new JTextField();
				txMaSanpham.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				pQLS.add(txMaSanpham);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 2: {
				txTenSanpham = new JTextField();
				txTenSanpham.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				pQLS.add(txTenSanpham);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 3: {
				TacGiaBUS b = new TacGiaBUS();
				dstg = b.docTacGia();
				cbbMaTG = new JComboBox();

				for (int k = 0; k < dstg.size(); k++) {
					if (dstg.get(k).getTrangThai().equals("1"))
						;
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

		txMaSanpham.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txTenSanpham.requestFocus();
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

		String[] arrchucnang = { "Tìm Kiếm", "Thêm", "Sửa", "Xuất excel", "Làm mới", "Cập nhật" };
		int x = 50, y = 100;
		for (int i = 0; i < arrchucnang.length; i++) {
			if (i == 1) {
				x = 240;
				y = 100;
			}
			button[i] = new JButton(arrchucnang[i]); // khỡi tạo đối tượng thuộc Jbutton
			button[i].setBounds(x, y, 150, 30); // đinh vị trí cho từng đối tượng button
			button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
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

	public JPanel TabelSanpham() {
		JPanel ptableSanpham = new JPanel();
		ptableSanpham.setLayout(null);
		ptableSanpham.setBackground(Color.decode("#78909C"));
		header = new Vector();
		header.add("Mã nước uống");
		header.add("Tên nước uống");
		header.add("Mã tác giả");
		header.add("Mã thể loại");
		header.add("Mã NXB");
		header.add("Số lượng");
		header.add("Đơn giá bán");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblQLS = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblQLS.setModel(model);// add model len table
		tblQLS.setBounds(0, 0, 1000, 300);
		tblQLS.setFont(new Font("Arial", 0, 15));
		tblQLS.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblQLS.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblQLS.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
		tblQLS.getTableHeader().setBackground(Color.decode("#FFCA28"));// set background cho header

		scrollPanel = new JScrollPane(tblQLS);
		scrollPanel.setBounds(0, 0, 1000, 300);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblQLS.setPreferredSize(new Dimension(500, 500));
		scrollPanel.setPreferredSize(new Dimension(500, 500));

		ptableSanpham.add(scrollPanel); // add table vào scrollPanel
		tblQLS.setFillsViewportHeight(true);// hiển thị table

		return ptableSanpham;

	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("Mã nước uống");
		header.add("Tên nước uống");
		header.add("Mã tác giả");
		header.add("Mã thể loại");
		header.add("Mã NXB");
		header.add("Số lượng");
		header.add("Đơn giá bán");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(SanphamDTO Sanpham) {
		Vector row = new Vector();
		row.add(Sanpham.getMaSanpham());
		row.add(Sanpham.getTenSanpham());
		row.add(Sanpham.getMaTG());
		row.add(Sanpham.getMaTL());
		row.add(Sanpham.getMaNXB());
		row.add(Sanpham.getSoLuong());
		row.add(Sanpham.getDonGia());
		model.addRow(row);
		tblQLS.setModel(model);
	}

	private void btClearMouseClicked() {
		txMaSanpham.setText("");
		txTenSanpham.setText("");
		cbbMaTG.setSelectedIndex(0);
		cbbMaTL.setSelectedIndex(0);
		cbbNXB.setSelectedIndex(0);
		txSoluong.setText("0");
		txDongiaban.setText("0");
		txMaSanpham.setEnabled(true);
	}

	private boolean check() {
		SanphamDTO Sanpham = new SanphamDTO();

		System.out.println("Hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		if (!CongCu.checkMa(txMaSanpham.getText())) {
			JOptionPane.showMessageDialog(null, "Mã nước uống không chứa kí tự đặc biệt");
			return false;
		} else if (!CongCu.isLength15(txMaSanpham.getText())) {
			JOptionPane.showMessageDialog(null, "Mã nước uống không quá 15 kí tự");
			return false;
		} else if (!CongCu.checkNames(txTenSanpham.getText())) {
			JOptionPane.showMessageDialog(null, "Tên nước uống chỉ gồm chữ và số");
			txTenSanpham.requestFocus();
			return false;
		} else if (!CongCu.isLength50(txTenSanpham.getText())) {
//            System.out.println("zo 50");
			JOptionPane.showMessageDialog(null, "Tên nước uống không được quá 50 ký tự");
			txTenSanpham.requestFocus();
			return false;
		} else if (!CongCu.checkNume(txSoluong.getText())) {
			JOptionPane.showMessageDialog(null, "Số lượng chỉ bao gồm số và không quá 6 chữ số");
			return false;
		} else if (!CongCu.checkPrice(txDongiaban.getText())) {
			JOptionPane.showMessageDialog(null, "Đơn giá chỉ bao gồm số và không quá 9 chữ số");
			return false;
		}
		return true;
	}

	private int kiemtranhap() {
		if (txMaSanpham.getText().equals("") || txTenSanpham.getText().equals("") || txDongiaban.getText().equals("")) {
			return 0;
		} else {
			return 1;
		}
	}

	private void checkempty() {
		if (txMaSanpham.getText().equals("")) {
			txMaSanpham.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txTenSanpham.getText().equals("")) {
			txTenSanpham.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txSoluong.getText().equals("")) {
			txSoluong.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txDongiaban.getText().equals("")) {
			txDongiaban.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	private void checkfilled() {
		if (!txMaSanpham.getText().equals("")) {
			txMaSanpham.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txTenSanpham.getText().equals("")) {
			txTenSanpham.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txSoluong.getText().equals("")) {
			txSoluong.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txDongiaban.getText().equals("")) {
			txDongiaban.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	private void btThemMouseClicked() {
		SanphamDTO Sanpham = new SanphamDTO();
		SanphamBUS bus = new SanphamBUS();
		String maSanpham = txMaSanpham.getText();
		int i = bus.kt_trung_ma(maSanpham);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Mã nước uống đã tồn tại");
		} else {
			if (kiemtranhap() == 0) {
				checkfilled();
				checkempty();
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
			} else {
				if (check()) {
					Sanpham.setMaSanpham(txMaSanpham.getText());
					Sanpham.setTenSanpham(txTenSanpham.getText());
					Sanpham.setMaTL(cbbMaTL.getSelectedItem().toString());
					Sanpham.setMaTG(cbbMaTG.getSelectedItem().toString());
					Sanpham.setMaNXB(cbbNXB.getSelectedItem().toString());
					Sanpham.setSoLuong(txSoluong.getText().toString());
					Sanpham.setDonGia(txDongiaban.getText().toString());
					bus.themSanpham(Sanpham);// thêm sách bên BUS đã có thêm vào database
					Add_header();
					Add_row(Sanpham);
					checkfilled();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
		}
	}

//    private void btXoaMouseClicked() {
//
//        SanphamDTO Sanpham = new SanphamDTO();
//        SanphamBUS bus = new SanphamBUS();
//        System.out.println("Nhue hello");
//        int i = tblQLS.getSelectedRow();
//        if (i < 0) {
//            JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 cuốn Sanpham");
//        } else {
//            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
//                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                model.removeRow(i);
//                tblQLS.setModel(model);
//                bus.xoa(Sanpham, i);
//
//            }
//
//        }
//
//    }

	private void btSuaMouseClicked() {
		SanphamDTO Sanpham = new SanphamDTO();
		SanphamBUS bus = new SanphamBUS();
		int i = tblQLS.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm bạn muốn sửa");
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
					txMaSanpham.setText(tblQLS.getValueAt(row, 0).toString());
					txMaSanpham.setEnabled(false);
					txTenSanpham.setText(tblQLS.getValueAt(row, 1).toString());
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
			model.setValueAt(txMaSanpham.getText(), i, 0);// model là ruột JTable.set giá trị cho model
			model.setValueAt(txTenSanpham.getText(), i, 1);
			model.setValueAt(cbbMaTG.getSelectedItem(), i, 2);
			model.setValueAt(cbbMaTL.getSelectedItem(), i, 3);
			model.setValueAt(cbbNXB.getSelectedItem(), i, 4);
			model.setValueAt(Integer.parseInt(txSoluong.getText()), i, 5);
			model.setValueAt(Integer.parseInt(txDongiaban.getText()), i, 6);
			tblQLS.setModel(model);
			Sanpham.setMaSanpham(txMaSanpham.getText());// nap du lieu vao doi tuong(textfield)
			Sanpham.setTenSanpham(txTenSanpham.getText());
			Sanpham.setMaTG(cbbMaTG.getSelectedItem().toString());
			Sanpham.setMaTL(cbbMaTL.getSelectedItem().toString());
			Sanpham.setMaNXB(cbbNXB.getSelectedItem().toString());
			Sanpham.setSoLuong(txSoluong.getText().toString());
			Sanpham.setDonGia(txDongiaban.getText().toString());

		}
		bus.suaSanpham(i, Sanpham);
		JOptionPane.showMessageDialog(null, "Sửa thành công");
	}

	private void ABCEFG(String condition, String text) {
		SanphamBUS bus = new SanphamBUS();
		switch (condition) {
		case "Mã sách":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_maSanpham(text);
			Search();
			break;
		case "Mã TL":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_matl(text);
			Search();
			break;
		case "Mã TG":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_matacgia(text);
			Search();
			break;
		case "Tên sách":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tenSanpham(text);
			Search();
			break;

		}
	}

	private void Search() {
		DefaultTableModel mode = new DefaultTableModel(header, 0);
		if (kq.size() != 0) {
			for (SanphamDTO Sanpham : kq) {
				Vector row = new Vector();
				row.add(Sanpham.getMaSanpham());
				row.add(Sanpham.getTenSanpham());
				row.add(Sanpham.getMaTG());
				row.add(Sanpham.getMaTL());
				row.add(Sanpham.getMaNXB());
				row.add(Sanpham.getSoLuong());
				row.add(Sanpham.getDonGia());
				mode.addRow(row);
			}
			tblQLS.setModel(mode);

		}
	}

	private void bttimkiemMouseClicked() {
		// System.out.println("tới rồi nè");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			btCapnhatMouseClicked();
		} else {
			ABCEFG(selectedItem, tukhoa);
		}

	}

	private void btXuatExcelMouseClicked() {
		JFileChooser file = new JFileChooser(); // Khởi tạo JFileChooser

		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			XSSFWorkbook excelWorkbook = new XSSFWorkbook();
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách nước uống");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH NƯỚC UỐNG");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã nước uống");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên nước uống");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Mã tác giả");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Mã thể loại");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Mã NXB");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Số lượng");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Đơn giá bán");

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
				JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
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
		SanphamDAO a = new SanphamDAO();
		dsSanpham = a.docSanpham();
		// Add_header();
		model.setRowCount(0);
		for (SanphamDTO s : dsSanpham) {
			model.addRow(new Object[] { s.getMaSanpham(), s.getTenSanpham(), s.getMaTG(), s.getMaTL(), s.getMaNXB(),
					s.getSoLuong(), s.getDonGia() });
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
		if (src.equals("Thêm")) {
			btThemMouseClicked();
		}
//        if (src.equals("Xóa")) {
//            btXoaMouseClicked();
//        }
		if (src.equals("Sửa")) {

			btSuaMouseClicked();
		}
		if (src.equals("Cập nhật")) {
			btCapnhatMouseClicked();
		}
		if (src.equals("Tìm Kiếm")) {
			bttimkiemMouseClicked();
		}
		if (src.equals("Làm mới")) {
			btClearMouseClicked();
		}
		if (src.equals("Xuất excel")) {
			btXuatExcelMouseClicked();
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

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("aa");
		if (e.getSource().equals(tblQLS)) {
			int row = tblQLS.getSelectedRow();
			if (row >= 0) {
				txMaSanpham.setText(tblQLS.getValueAt(row, 0).toString());
				txMaSanpham.setEnabled(false);
				txTenSanpham.setText(tblQLS.getValueAt(row, 1).toString());
				cbbMaTG.setSelectedItem(tblQLS.getValueAt(row, 2).toString());
				cbbMaTL.setSelectedItem(tblQLS.getValueAt(row, 3).toString());

				cbbNXB.setSelectedItem(tblQLS.getValueAt(row, 4).toString());
				txSoluong.setText(tblQLS.getValueAt(row, 5).toString());
				txDongiaban.setText(tblQLS.getValueAt(row, 6).toString());
			}
		}
	}

}
