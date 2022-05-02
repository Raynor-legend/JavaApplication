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
import BUS.NXBBUS;
import BUS.SanphamBUS;
import DAO.NXBDAO;
import DTO.NXBDTO;
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

public class NXBGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	JButton[] button;
	public JTable tblNXB;
	public JTextField txMaNXB;
	public JTextField txTenNXB;
	public JTextField txDiachi;
	public JTextField txTimKiem;
	public JComboBox box1;
	Vector header;
	JPanel p3;
	JScrollPane scrollPanel;
	public static JPanel phienthi, QLNXB;
	ArrayList<NXBDTO> dsnxb = new ArrayList<>();
	ArrayList<NXBDTO> kq;

	public NXBGUI() {
		initComponent();
		DocDL();

	}

	public void DocDL() {
		NXBBUS bus = new NXBBUS();
		if (NXBBUS.dsnxb == null) {
			bus.docDSNXB();
		}
		Add_header();
		for (NXBDTO nxb : NXBBUS.dsnxb) {
			Add_row(nxb);

		}
	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("Mã nhà xuất bản");
		header.add("Ten nhà xuất bản");
		header.add("Địa chỉ");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(NXBDTO nxb) {
		Vector row = new Vector();
		row.add(nxb.getMaNXB());
		row.add(nxb.getTenNXB());
		row.add(nxb.getDiachi());
		model.addRow(row);
		tblNXB.setModel(model);
	}

	public void initComponent() {
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));
		phienthi = QuanLiNXB();
		phienthi.setBounds(0, 0, 1200, 800);
		phienthi.setBackground(Color.decode("#78909C"));
		String[] timkiem = { "Mã NXB", "Tên NXB" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(550, 170, 100, 30);
		box1.setSelectedIndex(0);
		phienthi.add(box1);
		JPanel p2 = CHUCNANG();
		p2.setBounds(650, 0, 700, 450);
		p2.setBackground(Color.decode("#78909C"));
		p3 = TabelNCC();
		p3.setBounds(100, 450, 1000, 300);// set vị trí so với phienthi
		p3.setBackground(Color.decode("#78909C"));
		phienthi.add(p2);
		phienthi.add(p3);
		// add(pmenu);
		add(phienthi);
		setVisible(true);
		tblNXB.addMouseListener(this);

	}

	public JPanel QuanLiNXB() {
		QLNXB = new JPanel();
		QLNXB.setLayout(null);
		JLabel[] label;
		label = new JLabel[4];
		JTextField[] textfield;
		// SanphamGUI a=new SanphamGUI();
		// a.tableMouseClicked(e);
		String[] arrNXB = { "Tìm kiếm", "Mã nhà xuất bản", "Tên nhà xuất bản", "Địa chỉ" };

		int toadoxLabel = 200, toadoyLabel = 170;
		int toadoxTextField = 330, toadoyTextField = 170;
		for (int i = 0; i < arrNXB.length; i++) {
			label[i] = new JLabel(arrNXB[i]); // khỡi tạo đối tượng thuộc Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 130, 30); // đinh vị trí cho từng đối tượng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
			label[i].setName("btn" + i);
			label[i].setBackground(Color.decode("#FFCA28"));
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			System.out.println(label[i]);
			QLNXB.add(label[i]);
			toadoyLabel = toadoyLabel + 40;

			switch (i) {
			case 0: {
				txTimKiem = new JTextField();
				txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLNXB.add(txTimKiem);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 1: {
				txMaNXB = new JTextField();
				txMaNXB.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLNXB.add(txMaNXB);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 2: {
				txTenNXB = new JTextField();
				txTenNXB.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLNXB.add(txTenNXB);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 3: {
				txDiachi = new JTextField();
				txDiachi.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLNXB.add(txDiachi);
				toadoyTextField = toadoyTextField + 40;
				break;
			}

			}

		}
		txMaNXB.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txTenNXB.requestFocus();
				}
			}
		});
		txTenNXB.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txDiachi.requestFocus();
				}
			}
		});
		return QLNXB;
	}

	public JPanel CHUCNANG() {
		JPanel pchucnang = new JPanel();
		pchucnang.setLayout(null);
		button = new JButton[7];
		String[] arrchucnang = { "Tìm Kiếm", "Thêm", "Sửa", "Làm mới", "Cập nhật", "Xuất excel" };
		// Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
		int toadoxButton = 50, toadoyButton = 170;
		for (int i = 0; i < arrchucnang.length; i++) {
			if (i == 3) {
				toadoxButton = 240;
				toadoyButton = 170;
			}
			button[i] = new JButton(arrchucnang[i]); // khỡi tạo đối tượng thuộc Jbutton
			button[i].setBounds(toadoxButton, toadoyButton, 150, 30); // đinh vị trí cho từng đối tượng button
			button[i].setHorizontalAlignment(JButton.CENTER); // canh phải cho text button
			button[i].setName("btn" + i);
			button[i].setBackground(Color.decode("#FFCA28"));
			button[i].setFont(new Font("Arial", Font.BOLD, 20));
			System.out.println(button[i]);
			pchucnang.add(button[i]);
			toadoyButton = toadoyButton + 60;
			button[i].addActionListener((ActionListener) this);
		}
		return pchucnang;

	}

	public void btclearMouseClicked() {
		txDiachi.setText("");
		txMaNXB.setText("");
		txMaNXB.setEnabled(true);
		txTenNXB.setText("");

	}

	public JPanel TabelNCC() {
		JPanel ptablencc = new JPanel();
		ptablencc.setLayout(null);
		ptablencc.setBackground(Color.decode("#FFCA28"));
		header = new Vector();
		header.add("Mã Nhà Xuất Bản");
		header.add("Tên Nhà Xuất Bản");
		header.add("Địa chỉ");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblNXB = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblNXB.setBounds(0, 0, 1000, 300);

		tblNXB.setFont(new Font("Arial", 0, 15));
		tblNXB.setModel(model);// add model len table
		tblNXB.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tblNXB.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblNXB.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
		tblNXB.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scrollPanel = new JScrollPane(tblNXB);
		scrollPanel.setBounds(0, 0, 1000, 300);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblNXB.setPreferredSize(new Dimension(500, 500));
		scrollPanel.setPreferredSize(new Dimension(500, 500));

		ptablencc.add(scrollPanel); // add table vào scrollPanel
		tblNXB.setFillsViewportHeight(true);// hiển thị table

		return ptablencc;

	}

	private int kiemtra() {
		if (txMaNXB.getText().equals("") || txTenNXB.getText().equals("") || txDiachi.getText().equals("")) {
			return 0;
		}
		return 1;
	}

	private void checkempty() {
		if (txMaNXB.getText().equals(""))
			txMaNXB.setBorder(BorderFactory.createLineBorder(Color.RED));
		if (txTenNXB.getText().equals(""))
			txTenNXB.setBorder(BorderFactory.createLineBorder(Color.RED));
		if (txDiachi.getText().equals(""))
			txDiachi.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void checkfilled() {
		if (!txMaNXB.getText().equals(""))
			txMaNXB.setBorder(BorderFactory.createEmptyBorder());
		if (!txTenNXB.getText().equals(""))
			txTenNXB.setBorder(BorderFactory.createEmptyBorder());
		if (!txDiachi.getText().equals(""))
			txDiachi.setBorder(BorderFactory.createEmptyBorder());
	}

	private boolean checknhap() {
		if (!CongCu.checkChar(txMaNXB.getText()) || !CongCu.checkChar(txTenNXB.getText())
				|| !CongCu.checkChar(txDiachi.getText())) {
			return false;
		}
		return true;
	}

	private boolean check() {
		NXBDTO nxb = new NXBDTO();
		while (!checknhap()) {

			if (CongCu.checkChar(txMaNXB.getText())) {
			} else {
				JOptionPane.showMessageDialog(null, "Mã NXB chỉ bao gồm các kí tự chữ và số");
				return false;
			}
			if (CongCu.checkChar(txTenNXB.getText())) {
			} else {
				JOptionPane.showMessageDialog(null, "Tên NXB chỉ bao gồm các kí tự chữ và số");
				return false;
			}
			if (CongCu.checkChar(txDiachi.getText())) {
			} else {
				JOptionPane.showMessageDialog(null, "Địa chỉ NXB chỉ bao gồm các kí tự chữ và số");
				return false;
			}
		}
		return true;
	}

	private void btThemMouseClicked() {
		NXBDTO nxb = new NXBDTO();
		NXBBUS bus = new NXBBUS();
		String manxb = txMaNXB.getText();
		int i = bus.kt_trung_ma(manxb);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Mã nhà xuất bản đã tồn tại");
		} else {
			if (kiemtra() == 0) {
				checkempty();
				checkfilled();
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
			} else {
				if (check()) {
					nxb.setMaNXB(txMaNXB.getText());
					nxb.setTenNXB(txTenNXB.getText());
					nxb.setDiachi(txDiachi.getText());
					bus.themNXB(nxb);// thêm sách bên BUS đã có thêm vào database
					Add_header();
					Add_row(nxb);
					checkfilled();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
		}
	}

	private void btXoaMouseClicked() {

		NXBDTO nxb = new NXBDTO();
		NXBBUS bus = new NXBBUS();
		System.out.println("Hii");
		int i = tblNXB.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 nhà cung cấp");
		} else {
			if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeRow(i);
				tblNXB.setModel(model);
				bus.xoa(nxb, i);

			}

		}

	}

	private void btSuaMouseClicked() {
		NXBDTO nxb = new NXBDTO();
		NXBBUS bus = new NXBBUS();
		int i = tblNXB.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhà cung cấp bạn muốn sửa");
		}

		tblNXB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");
			}
		});
		ListSelectionModel model1 = tblNXB.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = tblNXB.getSelectedRow();
				System.out.println("baby ới");
				if (row >= 0) {
					txMaNXB.setText(tblNXB.getValueAt(row, 0).toString());
					txMaNXB.setEnabled(false);
					txTenNXB.setText(tblNXB.getValueAt(row, 1).toString());
					txDiachi.setText(tblNXB.getValueAt(row, 2).toString());
				}
			}
		});
		// tblQLS.addMouseListener(this);
		if (i >= 0) {
			model.setValueAt(txMaNXB.getText(), i, 0);// model là ruột JTable.set giá trị cho model
			model.setValueAt(txTenNXB.getText(), i, 1);
			model.setValueAt(txDiachi.getText(), i, 2);

			tblNXB.setModel(model);
			nxb.setMaNXB(txMaNXB.getText());// nap du lieu vao doi tuong(textfield)
			nxb.setTenNXB(txTenNXB.getText());
			nxb.setDiachi(txDiachi.getText());
		}
		bus.suaNXB(i, nxb);
		JOptionPane.showMessageDialog(null, "Sửa thành công");
	}

	private void CHON(String conditions, String text) {
		NXBBUS bus = new NXBBUS();
		switch (conditions) {
		case "Mã NXB":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_manxb(text);
			Search();
			break;
		case "Tên NXB":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tennxb(text);
			Search();
			break;
		}
	}

	private void Search() {
		DefaultTableModel mode = new DefaultTableModel(header, 0);
		if (kq.size() != 0) {
			for (NXBDTO nxb : kq) {
				Vector row = new Vector();
				row.add(nxb.getMaNXB());
				row.add(nxb.getTenNXB());
				row.add(nxb.getDiachi());
				mode.addRow(row);
				//
			}
			tblNXB.setModel(mode);

		}
	}

	private void bttimkiemMouseClicked() {
		System.out.println("tới rồi nè");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			btcapnhatMouseClicked();
		} else {
			CHON(selectedItem, tukhoa);
		}
	}

	public void btcapnhatMouseClicked() {
		NXBDAO a = new NXBDAO();
		dsnxb = a.docNXB();
		model.setRowCount(0);
		for (NXBDTO nxb : dsnxb) {
			model.addRow(new Object[] { nxb.getMaNXB(), nxb.getTenNXB(), nxb.getDiachi() });
		}
		tblNXB.setModel(model);
		p3.removeAll();
		p3.add(scrollPanel);
		p3.revalidate();
		p3.repaint();
	}

	public void btXuatExcelMouseClicked() {
		JFileChooser file = new JFileChooser(); // Khởi tạo JFileChooser

		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			XSSFWorkbook excelWorkbook = new XSSFWorkbook();
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách nhà xuất bản");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH NHÀ XUẤT BẢN");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã NXB");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên NXB");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Địa chỉ");
			cell = row.createCell(3, CellType.STRING);

			for (int i = 0; i < tblNXB.getRowCount(); i++) {
				row = excelSheet.createRow((short) 3 + i);
				row.setHeight((short) 400);
				for (int j = 0; j < tblNXB.getColumnCount(); j++) {
					row.createCell(j).setCellValue(tblNXB.getValueAt(i, j).toString());
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

	public static void main(String[] args) {
		NXBGUI adminnxb = new NXBGUI();
//        adminnxb.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        adminnxb.setLocationRelativeTo(adminnxb);

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
			// tableMouseClicked();
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
//        if(e.getSource()==button[1]){
//            btXoaMouseClicked();
//            int i=tblQLS.getSelectedRow();
//           dsSanpham.remove(i);
//            
//        }
	}

	/*
	 * public void btnSuaActionPerformed(ActionEvent e){ int
	 * i=tblQLS.getSelectedRow(); if(i>=0) { SanphamDTO Sanpham=new SanphamDTO();
	 * Sanpham.MaSanpham=txMaSanpham.getText();
	 * Sanpham.TenSanpham=txTenSanpham.getText(); Sanpham.MaTL=txMaTL.getText();
	 * Sanpham.MaTG=txMaTG.getText(); Sanpham.MaNXB=txMaNXB.getText();
	 * Sanpham.SoLuong=Integer.parseInt(txSoluong.getText());
	 * Sanpham.DonGia=Integer.parseInt(txDongiaban.getText());
	 * 
	 * SanphamDTO old=dsSanpham.set(i,Sanpham); model.setValueAt(Sanpham.MaSanpham,
	 * i,0); model.setValueAt(Sanpham.TenSanpham, i,1);
	 * model.setValueAt(Sanpham.MaTL, i,2); model.setValueAt(Sanpham.MaTG, i,3);
	 * model.setValueAt(Sanpham.MaNXB, i,4); model.setValueAt(Sanpham.SoLuong, i,6);
	 * model.setValueAt(Sanpham.DonGia, i,7); tblQLS.setModel(model);
	 * 
	 * 
	 * } }
	 */
	// @Override
	// public void mouseClicked(java.awt.event.MouseEvent e) {
	// System.out.println("3333333333333");
	// }
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
		System.out.println("aaaa");
		if (e.getSource().equals(tblNXB)) {
			int row = tblNXB.getSelectedRow();
			System.out.println("baby ới");
			if (row >= 0) {
				txMaNXB.setText(tblNXB.getValueAt(row, 0).toString());
				txMaNXB.setEnabled(false);
				txTenNXB.setText(tblNXB.getValueAt(row, 1).toString());
				txDiachi.setText(tblNXB.getValueAt(row, 2).toString());
			}
		}
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
