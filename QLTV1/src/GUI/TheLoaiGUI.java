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
import BUS.TheLoaiBUS;
import BUS.SanphamBUS;
import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;
import DTO.SanphamDTO;
import java.awt.*;
import static java.awt.Font.BOLD;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.event.ActionEvent;
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

public class TheLoaiGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	JButton[] button;
	public JTable tblTL;
	public JTextField txMaTL;
	public JTextField txTenTL;
	public JTextField txTimKiem;
	public JComboBox box1;
	Vector header;
	JPanel p3;
	JScrollPane scrollPanel;
	public static JPanel phienthi, QLTL;
	ArrayList<TheLoaiDTO> dstl = new ArrayList<>();
	ArrayList<TheLoaiDTO> kq;

	public TheLoaiGUI() {
		initComponent();
		TheLoaiBUS bus = new TheLoaiBUS();
		if (TheLoaiBUS.dstl == null) {
			bus.docDSTL();
		}
		Add_header();
		for (TheLoaiDTO theloai : TheLoaiBUS.dstl) {
			Add_row(theloai);
		}

	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("Mã thể loại");
		header.add("Tên thể loại");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(TheLoaiDTO tl) {
		Vector row = new Vector();
		row.add(tl.getMaTL());
		row.add(tl.getTenTL());

		model.addRow(row);
		tblTL.setModel(model);
	}

	public void initComponent() {
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));
//        setLocationRelativeTo(null);
//        setResizable(false);

		phienthi = QuanLiTL();
		// phienthi.setBounds(300,0,1200,800);
		phienthi.setBounds(0, 0, 1200, 800);
		phienthi.setBackground(Color.decode("#78909C"));
		String[] timkiem = { "Mã TL", "Tên TL" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(520, 180, 100, 30);
		box1.setSelectedIndex(0);

		phienthi.add(box1);
		JPanel p2 = CHUCNANG();
		p2.setBounds(650, 0, 700, 450);
		p2.setBackground(Color.decode("#78909C"));
		p3 = TabelTL();
		p3.setBounds(100, 450, 1000, 300);// set vị trí so với phienthi
		p3.setBackground(Color.decode("#78909C"));
		phienthi.add(p2);
		phienthi.add(p3);
		// add(pmenu);
		add(phienthi);
		setVisible(true);
		tblTL.addMouseListener(this);

	}

	public JPanel QuanLiTL() {
		QLTL = new JPanel();
		QLTL.setLayout(null);
		JLabel[] label;
		label = new JLabel[4];
		JTextField[] textfield;

		String[] arrTL = { "Tìm kiếm", "Mã thể loại", "Tên thể loại" };

		int toadoxLabel = 200, toadoyLabel = 180;
		int toadoxTextField = 300, toadoyTextField = 180;
		for (int i = 0; i < arrTL.length; i++) {
			label[i] = new JLabel(arrTL[i]); // khỡi tạo đối tượng thuộc Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); // đinh vị trí cho từng đối tượng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
			label[i].setName("btn" + i);
			label[i].setBackground(Color.decode("#FFCA28"));
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			System.out.println(label[i]);
			QLTL.add(label[i]);
			toadoyLabel = toadoyLabel + 40;

			switch (i) {
			case 0: {
				txTimKiem = new JTextField();
				txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTL.add(txTimKiem);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 1: {
				txMaTL = new JTextField();
				txMaTL.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTL.add(txMaTL);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 2: {
				txTenTL = new JTextField();
				txTenTL.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTL.add(txTenTL);
				toadoyTextField = toadoyTextField + 40;
				break;
			}

			}
		}
		txMaTL.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txTenTL.requestFocus();
				}
			}
		});

		return QLTL;
	}

	public void btclearMouseClicked() {
		txMaTL.setText("");
		txMaTL.setEnabled(true);
		txTenTL.setText("");
	}

	public JPanel CHUCNANG() {
		JPanel pchucnang = new JPanel();
		pchucnang.setLayout(null);
		button = new JButton[7];
		String[] arrchucnang = { "Tìm Kiếm", "Thêm", "Sửa", "Hiển thị", "Xuất excel", "Làm mới" };
		// Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
		int toadoxButton = 50, toadoyButton = 180;
		for (int i = 0; i < arrchucnang.length; i++) {
			if (i == 3) {
				toadoxButton = 240;
				toadoyButton = 180;
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

	public JPanel TabelTL() {
		JPanel ptabletl = new JPanel();
		ptabletl.setLayout(null);
		ptabletl.setBackground(Color.decode("#FFCA28"));
		header = new Vector();
		header.add("Mã thể loại");
		header.add("Tên thể loại");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblTL = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblTL.setBounds(0, 0, 1000, 300);

		tblTL.setFont(new Font("Arial", 0, 15));
		tblTL.setModel(model);// add model len table
		tblTL.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tblTL.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblTL.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
		tblTL.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scrollPanel = new JScrollPane(tblTL);
		scrollPanel.setBounds(0, 0, 1000, 300);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblTL.setPreferredSize(new Dimension(500, 500));
		scrollPanel.setPreferredSize(new Dimension(500, 500));

		ptabletl.add(scrollPanel); // add table vào scrollPanel
		tblTL.setFillsViewportHeight(true);// hiển thị table

		return ptabletl;

	}

	private int kiemtra() {
		if (txMaTL.getText().equals("") || txTenTL.getText().equals("")) {
			return 0;
		}
		return 1;
	}

	private void checkempty() {
		if (txMaTL.getText().equals("")) {
			txMaTL.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if (txTenTL.getText().equals("")) {
			txTenTL.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	private void checkfilled() {
		if (!txMaTL.getText().equals("")) {
			txMaTL.setBorder(BorderFactory.createEmptyBorder());
		}
		if (!txTenTL.getText().equals("")) {
			txTenTL.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	private boolean check() {
		TheLoaiDTO tl = new TheLoaiDTO();

		if (CongCu.checkChar(txMaTL.getText())) {
		} else {
			JOptionPane.showMessageDialog(null, "Mã TL chỉ bao gồm chữ và số");
			return false;
		}
		if (CongCu.checkNames(txTenTL.getText())) {
		} else {
			JOptionPane.showMessageDialog(null, "Tên TL chỉ bao gồm kí tự chữ");
			return false;
		}
		return true;
	}

	private void btThemMouseClicked() {
		TheLoaiDTO tl = new TheLoaiDTO();
		TheLoaiBUS bus = new TheLoaiBUS();
		String matl = txMaTL.getText();
		int i = bus.kt_trung_ma(matl);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Mã thể loại đã tồn tại");
		} else {
			if (kiemtra() == 0) {
				checkempty();
				checkfilled();
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
			} else {
				if (check()) {
					tl.setMaTL(txMaTL.getText());
					tl.setTenTL(txTenTL.getText());
					bus.themTL(tl);// thêm sách bên BUS đã có thêm vào database
					Add_header();
					Add_row(tl);
					checkfilled();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}

		}
	}

	private void btXoaMouseClicked() {

		TheLoaiDTO ncc = new TheLoaiDTO();
		TheLoaiBUS bus = new TheLoaiBUS();
		System.out.println("Hii");
		int i = tblTL.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 thể loại");
		} else {
			if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeRow(i);
				tblTL.setModel(model);
				bus.xoa(ncc, i);

			}

		}

	}

	private void btSuaMouseClicked() {
		TheLoaiDTO tl = new TheLoaiDTO();
		TheLoaiBUS bus = new TheLoaiBUS();
		int i = tblTL.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhà cung cấp bạn muốn sửa");
		}
		tblTL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");
			}
		});
		ListSelectionModel model1 = tblTL.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = tblTL.getSelectedRow();
				System.out.println("baby ới");
				if (row != -1) {
					txMaTL.setText(tblTL.getValueAt(row, 0).toString());
					txMaTL.setEnabled(false);
					txTenTL.setText(tblTL.getValueAt(row, 1).toString());

				}
			}
		});
		if (i >= 0) {
			model.setValueAt(txMaTL.getText(), i, 0);// model là ruột JTable.set giá trị cho model
			model.setValueAt(txTenTL.getText(), i, 1);
			tblTL.setModel(model);
			tl.setMaTL(txMaTL.getText());// nap du lieu vao doi tuong(textfield)
			tl.setTenTL(txTenTL.getText());

		}
		bus.suaTL(i, tl);
		JOptionPane.showMessageDialog(null, "Sửa thành công");
	}

	private void CHON(String conditions, String text) {
		TheLoaiBUS bus = new TheLoaiBUS();
		switch (conditions) {
		case "Mã TL":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_matl(text);
			Search();
			break;
		case "Tên TL":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tentheloai(text);
			Search();
			break;
		}
	}

	private void Search() {
		DefaultTableModel mode = new DefaultTableModel(header, 0);
		if (kq.size() != 0) {
			for (TheLoaiDTO tl : kq) {
				Vector row = new Vector();
				row.add(tl.getMaTL());
				row.add(tl.getTenTL());
				mode.addRow(row);
			}
			tblTL.setModel(mode);
		}
	}

	private void bttimkiemMouseClicked() {
		System.out.println("tới rồi nè");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			bthienthiMouseClicked();
		} else {
			CHON(selectedItem, tukhoa);

		}
	}

	public void bthienthiMouseClicked() {
		System.out.println("hello");
		TheLoaiDAO a = new TheLoaiDAO();
		dstl = a.docTheLoai();
		Add_header();
		model.setRowCount(0);
		for (TheLoaiDTO s : dstl) {
			model.addRow(new Object[] { s.getMaTL(), s.getTenTL() });
		}
		tblTL.setModel(model);
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
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách thể loại");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH THỂ LOẠI");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã thể loại");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên thể loại");
			cell = row.createCell(2, CellType.STRING);
			for (int i = 0; i < tblTL.getRowCount(); i++) {
				row = excelSheet.createRow((short) 3 + i);
				row.setHeight((short) 400);
				for (int j = 0; j < tblTL.getColumnCount(); j++) {
					row.createCell(j).setCellValue(tblTL.getValueAt(i, j).toString());
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
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getSource().equals(tblTL)) {
			int row = tblTL.getSelectedRow();
			System.out.println("baby ới");
			txMaTL.setText(tblTL.getValueAt(row, 0).toString());
			txMaTL.setEnabled(false);
			txTenTL.setText(tblTL.getValueAt(row, 1).toString());

			if (tblTL.getRowCount() > 0) {

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
		if (src.equals("Hiển thị")) {
			bthienthiMouseClicked();
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
