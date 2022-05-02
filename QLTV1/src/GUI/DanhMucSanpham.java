
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
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.control.Cell;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author HP
 */
public class DanhMucSanpham extends JPanel implements ActionListener, MouseListener {

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
	JScrollPane scrollPanel = new JScrollPane(tblQLS);
	// public static JPanel QLS;
	ArrayList<SanphamDTO> dsSanpham = new ArrayList<>();
	ArrayList<SanphamDTO> kq;

	public DanhMucSanpham() {
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
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));
		JPanel p1 = QuanLiSanpham();
		p1.setBounds(0, 0, 550, 450);
		p1.setBackground(Color.decode("#78909C"));
		String[] timkiem = { "Mã đồ uống", "Tên đồ uống", "Mã TG", "Mã TL" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(420, 100, 100, 30);
		box1.setSelectedIndex(1);
		p1.add(box1);

		JPanel p2 = CHUCNANG();
		p2.setBounds(550, 0, 700, 450);
		p2.setBackground(Color.decode("#78909C"));

		p3 = TabelSanpham();
		p3.setBounds(100, 450, 1000, 450);// set vị trí so với phienthi
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
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			label[i].setName("btn" + i);

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
				ArrayList<TacGiaDTO> arrTG = new ArrayList<TacGiaDTO>();
				arrTG = b.docTacGia();
				cbbMaTG = new JComboBox();
				for (int k = 0; k < arrTG.size(); k++) {
					cbbMaTG.addItem(arrTG.get(k).getMaTG());
				}

				cbbMaTG.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						showTenTG();
					}
				});

				cbbMaTG.setBounds(200, 220, 100, 30);
				txMaTG = new JTextField();
				txMaTG.setBounds(310, 220, 90, 30);
				txMaTG.setEnabled(false);

				String getcbbMaTG = cbbMaTG.getSelectedItem().toString();
				for (int p = 0; p < arrTG.size(); p++) {
					if (getcbbMaTG.equals(arrTG.get(p).getMaTG())) {
						txMaTG.setText(arrTG.get(p).getTenTG());
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
		button = new JButton[7];// *************************************************
		String[] arrchucnang = { "Tìm Kiếm", "Hiển thị" };

		int x = 240, y = 90;
		int toadoxButton = 50, toadoyButton = 100;
		for (int i = 0; i < arrchucnang.length; i++) {
			button[i] = new JButton(arrchucnang[i]); // khỡi tạo đối tượng thuộc Jbutton
			button[i].setBounds(toadoxButton, toadoyButton, 150, 30); // đinh vị trí cho từng đối tượng button
			button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
			button[i].setName("btn" + i);
			button[i].setBackground(Color.decode("#FFCA28"));
			button[i].setFont(new Font("Arial", Font.BOLD, 20));
			System.out.println(button[i]);
			pchucnang.add(button[i]);
			toadoyButton = toadoyButton + 70;
			button[i].addActionListener((ActionListener) this);
		}
//        for (int j = 0; j < arrclear.length; j++) {
//            button[j] = new JButton(arrclear[j]); //khỡi tạo đối tượng thuộc Jbutton
//            button[j].setBounds(x, y, 150, 30); //đinh vị trí cho từng đối tượng button
//            button[j].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
//            button[j].setName("btn" + j);
//            button[j].setBackground(Color.decode("#FFCA28"));
//            button[j].setFont(new Font("Arial", Font.BOLD, 20));
//            System.out.println(button[j]);
//            pchucnang.add(button[j]);
//            y = y + 70;
//            button[j].addActionListener((ActionListener) this);

		return pchucnang;

	}

	public JPanel TabelSanpham() {
		JPanel ptableSanpham = new JPanel();
		ptableSanpham.setLayout(null);
		ptableSanpham.setBackground(Color.orange);
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
		tblQLS = new JTable(null, header);
		tblQLS.setBounds(0, 0, 1000, 300);

		tblQLS.setFont(new Font("Arial", 0, 15));
		tblQLS.setModel(model);// add model len table
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
		row.add(Sanpham.getMaTL());
		row.add(Sanpham.getSoLuong());
		row.add(Sanpham.getDonGia());
		model.addRow(row);
		tblQLS.setModel(model);
	}

	private void btClearMouseClicked() {
		txMaSanpham.setText("");
		txTenSanpham.setText("");
		txSoluong.setText("");
		txDongiaban.setText("");
		txMaSanpham.setEnabled(true);
	}

	private void btThemMouseClicked() {
		SanphamDTO Sanpham = new SanphamDTO();
		SanphamBUS bus = new SanphamBUS();
		Sanpham.setMaSanpham(txMaSanpham.getText());
		Sanpham.setTenSanpham(txTenSanpham.getText());
		Sanpham.setMaTL(cbbMaTL.getSelectedItem().toString());
		Sanpham.setSoLuong(txSoluong.getText());
		Sanpham.setDonGia(txDongiaban.getText());
		String maSanpham = txMaSanpham.getText();
		int i = bus.kt_trung_ma(maSanpham);

		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Mã nước uống đã tồn tại");
		} else if (kiemtra() == 0) {
			txMaSanpham.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
		} else {
			bus.themSanpham(Sanpham);// thêm sách bên BUS đã có thêm vào database
			Add_header();
			Add_row(Sanpham);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		}

	}

	private int kiemtra() {
		if (txMaSanpham.getText().equals("") || txTenSanpham.getText().equals(""))
			return 0;
		else
			return 1;
	}

	private void btXoaMouseClicked() {

		SanphamDTO Sanpham = new SanphamDTO();
		SanphamBUS bus = new SanphamBUS();
		System.out.println("Nhue hello");
		int i = tblQLS.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 sản phẩm");
		} else {
			if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeRow(i);
				tblQLS.setModel(model);
				bus.xoa(Sanpham, i);

			}

		}

	}

	private void btSuaMouseClicked() {
		SanphamDTO Sanpham = new SanphamDTO();
		SanphamBUS bus = new SanphamBUS();
		int i = tblQLS.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nước uống bạn muốn sửa");
		}
		tblQLS.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");

			}
		});
		ListSelectionModel model1 = tblQLS.getSelectionModel();// *************************************************
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
			Sanpham.setMaTL(cbbMaTL.getSelectedItem().toString());
			Sanpham.setSoLuong(txSoluong.getText());
			Sanpham.setDonGia(txDongiaban.getText());

		}
		bus.suaSanpham(i, Sanpham);
		JOptionPane.showMessageDialog(null, "Sửa thành công");
	}

	public void ClickMouseClicked() {// ********************************************
		SanphamDTO Sanpham = new SanphamDTO();
		int i = tblQLS.getSelectedRow();
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
			Sanpham.setMaTL(cbbMaTL.getSelectedItem().toString());
			Sanpham.setSoLuong(txSoluong.getText());
			Sanpham.setDonGia(txDongiaban.getText());

		}
	}

	private void bttimkiemMouseClicked() {
		// System.out.println("tới rồi nè");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ khóa");
		} else {
			// switch(tukhoa){
			// case "Mã sách":
			// break;
			// }
			if (selectedItem.equals("Mã nước uống")) {
				System.out.println("mã nước uống");
				SanphamBUS bus = new SanphamBUS();
				Add_header();
				kq = bus.timkiem_maSanpham(tukhoa);
				DefaultTableModel mode = new DefaultTableModel(header, 0);
				if (kq.size() != 0) {
					for (SanphamDTO Sanpham : kq) {
						Vector row = new Vector();
						row.add(Sanpham.getMaSanpham());
						row.add(Sanpham.getTenSanpham());
						row.add(Sanpham.getMaTL());
						row.add(Sanpham.getSoLuong());
						row.add(Sanpham.getDonGia());
						mode.addRow(row);
					}
					tblQLS.setModel(mode);
					tblQLS = new JTable(null, header);
					tblQLS.setBounds(0, 0, 1000, 300);
					tblQLS.setFont(new Font("Arial", 0, 15));
					tblQLS.setModel(mode);// add model len table
					tblQLS.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
					tblQLS.getTableHeader().setForeground(Color.black); // set màu chữ cho header
					tblQLS.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
					tblQLS.getTableHeader().setBackground(Color.decode("#FFCA28"));// set background cho header
					scrollPanel = new JScrollPane(tblQLS);
					scrollPanel.setBounds(0, 0, 1000, 300);
					scrollPanel.invalidate();
					scrollPanel.validate();
					scrollPanel.repaint();
				}
			} else if (selectedItem.equals("Mã TL")) {
				System.out.println("thể loại");
				System.out.println("hihi");
				SanphamBUS bus = new SanphamBUS();
				Add_header();
				model.setRowCount(0);
				kq = bus.timkiem_matl(tukhoa);
				// DefaultTableModel mod = new DefaultTableModel(header, 0);
				if (kq.size() != 0) {
					for (SanphamDTO Sanpham : kq) {
						Vector row = new Vector();// hienthi Sanpham
						row.add(Sanpham.getMaSanpham());
						row.add(Sanpham.getTenSanpham());
						row.add(Sanpham.getMaTL());
						row.add(Sanpham.getSoLuong());
						row.add(Sanpham.getDonGia());
						model.addRow(row);
					}

					tblQLS = new JTable(null, header);// tạo mới bảng
					tblQLS.setModel(model);// add dữ kiệu
					tblQLS.setBounds(0, 0, 1000, 300);
					tblQLS.setFont(new Font("Arial", 0, 15));
					// tblQLS.setModel(mode);//add model len table
					tblQLS.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
					tblQLS.getTableHeader().setForeground(Color.black); // set màu chữ cho header
					tblQLS.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
					tblQLS.getTableHeader().setBackground(Color.decode("#FFCA28"));// set background cho header
					scrollPanel = new JScrollPane(tblQLS);
					scrollPanel.setBounds(0, 0, 1000, 300);
					// tblQLS.setModel(mod);
					p3.removeAll();
					p3.add(scrollPanel);
					p3.revalidate();
					p3.repaint();

				} else
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
			}

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
			cell.setCellValue("Mã thể loại");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Số lượng");
			cell = row.createCell(4, CellType.STRING);
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

	private void btHienThiMouseClicked() {
		SanphamDAO a = new SanphamDAO();
		dsSanpham = a.docSanpham();
		// Add_header();
		model.setRowCount(0);
		for (SanphamDTO s : dsSanpham) {
			model.addRow(new Object[] { s.getMaSanpham(), s.getTenSanpham(), s.getMaTL(),
					s.getSoLuong(), s.getDonGia() });
		}
		tblQLS.setModel(model);
		p3.removeAll();
		p3.add(scrollPanel);
		p3.revalidate();
		p3.repaint();

	}

	public static void main(String[] args) {
		SanphamGUI admin = new SanphamGUI();
//        admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        admin.setLocationRelativeTo(admin);
//
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();

		if (src.equals("Tìm Kiếm")) {
			bttimkiemMouseClicked();
		}
		if (src.equals("Hiển thị")) {
			btHienThiMouseClicked();

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
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
