
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
import BUS.TacGiaBUS;
import BUS.SanphamBUS;
import DAO.TacGiaDAO;
import DTO.TacGiaDTO;
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

public class TacGiaGUI extends JPanel implements ActionListener, MouseListener {

	public static DefaultTableModel model = new DefaultTableModel();
	JButton[] button;
	public JTable tblTG;
	public JTextField txMaTG;
	public JTextField txTenTG;
	public JTextField txTimKiem;
	public JTextField txTrangThai;
	public JComboBox box1, cbbtt;
	Vector header;
	JPanel p3;
	JScrollPane scrollPanel = new JScrollPane(tblTG);
	public static JPanel phienthi, QLTG;
	ArrayList<TacGiaDTO> dstg = new ArrayList<>();
	ArrayList<TacGiaDTO> dstgan = new ArrayList<>();
	ArrayList<TacGiaDTO> kq;

	public TacGiaGUI() {
		initComponent();
		DocDL();

	}

	public static void DocDL() {
//        TacGiaBUS bus = new TacGiaBUS();
//        if (TacGiaBUS.dstg == null) {
//            bus.docDSTG();
//        }
//        Add_header();
//        for (TacGiaDTO tg : TacGiaBUS.dstg) {
//            Add_row(tg);
//
//        }

		TacGiaBUS bus = new TacGiaBUS();
		model.setRowCount(0);
		TacGiaBUS.dstg = bus.docTacGia();
		for (TacGiaDTO tg : TacGiaBUS.dstg) {
			model.addRow(new Object[] { tg.getMaTG(), tg.getTenTG(), tg.getTrangThai() });
		}
	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("M?? t??c gi???");
		header.add("T??n t??c gi???");
		header.add("Tr???ng th??i");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(TacGiaDTO tg) {
		Vector row = new Vector();
		row.add(tg.getMaTG());
		row.add(tg.getTenTG());
		row.add(tg.getTrangThai());

		model.addRow(row);
		tblTG.setModel(model);
	}

	public void initComponent() {
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));

		phienthi = QuanLiTG();
		phienthi.setBounds(0, 0, 1200, 800);
		phienthi.setBackground(Color.decode("#78909C"));

		String[] timkiem = { "M?? TG", "T??n TG" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(520, 170, 100, 30);
		box1.setSelectedIndex(0);
		phienthi.add(box1);

		JPanel p2 = CHUCNANG();
		p2.setBounds(650, 0, 700, 450);
		p2.setBackground(Color.decode("#78909C"));

		p3 = TabelTG();
		p3.setBounds(100, 450, 1000, 300);// set v??? tr?? so v???i phienthi
		p3.setBackground(Color.decode("#78909C"));
		phienthi.add(p2);
		phienthi.add(p3);
		add(phienthi);
		setVisible(true);
		tblTG.addMouseListener(this);

	}

	public JPanel QuanLiTG() {
		QLTG = new JPanel();
		QLTG.setLayout(null);
		JLabel[] label;
		label = new JLabel[4];
		JTextField[] textfield;
		String[] arrTG = { "T??m Ki???m", "M?? t??c gi???", "T??n t??c gi???", "Tr???ng th??i" };

		int toadoxLabel = 200, toadoyLabel = 170;
		int toadoxTextField = 300, toadoyTextField = 170;
		for (int i = 0; i < arrTG.length; i++) {
			label[i] = new JLabel(arrTG[i]); // kh???i t???o ?????i t?????ng thu???c Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); // ??inh v??? tr?? cho t???ng ?????i t?????ng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh ph???i cho text button
			label[i].setName("btn" + i);
			label[i].setBackground(Color.decode("#FFCA28"));
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			System.out.println(label[i]);
			QLTG.add(label[i]);
			toadoyLabel = toadoyLabel + 40;
			// txTimKiem.setBounds(200,220,200,30);

			switch (i) {
			case 0: {
				txTimKiem = new JTextField();
				txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTG.add(txTimKiem);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 1: {
				txMaTG = new JTextField();
				txMaTG.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTG.add(txMaTG);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 2: {
				txTenTG = new JTextField();
				txTenTG.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTG.add(txTenTG);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 3: {
				String[] tt = { "1", "0" };
				cbbtt = new JComboBox(tt);
				cbbtt.setSelectedIndex(0);
				cbbtt.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTG.add(cbbtt);
				toadoyTextField = toadoyTextField + 40;
				break;
			}

			}

		}
		txMaTG.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txTenTG.requestFocus();
				}
			}
		});
//        txTenTG.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                    txTrangThai.requestFocus();
//                }
//            }
//        });
		return QLTG;
	}

	public JPanel CHUCNANG() {
		JPanel pchucnang = new JPanel();
		pchucnang.setLayout(null);
		button = new JButton[7];
		String[] arrchucnang = { "T??m Ki???m", "Th??m", "S???a", "L??m m???i", "Xu???t excel", "C???p nh???t", "DS ???n" };
		// Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
		int toadoxButton = 50, toadoyButton = 170;
		for (int i = 0; i < arrchucnang.length; i++) {

			if (i == 4) {
				toadoxButton = 240;
				toadoyButton = 230;
			}
			button[i] = new JButton(arrchucnang[i]); // kh???i t???o ?????i t?????ng thu???c Jbutton
			button[i].setBounds(toadoxButton, toadoyButton, 150, 30); // ??inh v??? tr?? cho t???ng ?????i t?????ng button
			button[i].setHorizontalAlignment(JButton.CENTER); // canh ph???i cho text button
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
		txMaTG.setText("");
		txMaTG.setEnabled(true);
		txTenTG.setText("");
		cbbtt.setSelectedIndex(0);

	}

	public JPanel TabelTG() {
		JPanel ptabletg = new JPanel();
		ptabletg.setLayout(null);
		ptabletg.setBackground(Color.decode("#FFCA28"));
		header = new Vector();
		header.add("M?? t??c gi???");
		header.add("T??n t??c gi???");
		header.add("Tr???ng th??i");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblTG = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblTG.setBounds(0, 0, 1000, 250);

		tblTG.setFont(new Font("Arial", 0, 15));
		tblTG.setModel(model);// add model len table
		tblTG.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tblTG.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblTG.getTableHeader().setPreferredSize(new Dimension(30, 50));// set ????? d??i ????? r???ng c???a header
		tblTG.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scrollPanel = new JScrollPane(tblTG);
		scrollPanel.setBounds(0, 0, 1000, 250);
		tblTG.setPreferredSize(new Dimension(500, 500));
		scrollPanel.setPreferredSize(new Dimension(500, 500));
		ptabletg.add(scrollPanel);

		tblTG.setFillsViewportHeight(true);// hi???n th??? table

		return ptabletg;

	}

	private int kiemtra() {
		if (txMaTG.getText().equals("") || txTenTG.getText().equals("")) {
			return 0;
		}
		return 1;
	}

	private void checkempty() {
		if (txMaTG.getText().equals(""))
			txMaTG.setBorder(BorderFactory.createLineBorder(Color.RED));
		if (txTenTG.getText().equals(""))
			txTenTG.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void checkfilled() {
		if (!txMaTG.getText().equals(""))
			txMaTG.setBorder(BorderFactory.createEmptyBorder());
		if (!txTenTG.getText().equals(""))
			txTenTG.setBorder(BorderFactory.createEmptyBorder());
	}

	private boolean check() {
		TacGiaDTO tg = new TacGiaDTO();
		if (CongCu.checkNames(txMaTG.getText())) {
		} else {
			JOptionPane.showMessageDialog(null, "M?? t??c gi??? ch??? g???m ch??? c??i");
			return false;
		}
		if (CongCu.checkNames(txTenTG.getText())) {
		} else {
			JOptionPane.showMessageDialog(null, "T??n t??c gi??? ch??? g???m ch??? c??i");
			return false;
		}
		return true;
	}

	private void btThemMouseClicked() {
		TacGiaDTO tg = new TacGiaDTO();
		TacGiaBUS bus = new TacGiaBUS();

		String matg = txMaTG.getText();
		int i = bus.kt_trung_ma(matg);
		if (i == 1) {
			JOptionPane.showMessageDialog(null, "M?? t??c gi??? ???? t???n t???i");
		} else {
			if (kiemtra() == 0) {
				checkempty();
				checkfilled();
				JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????? th??ng tin");
			} else {
				if (check()) {
					tg.setMaTG(txMaTG.getText());
					tg.setTenTG(txTenTG.getText());
					bus.themTG(tg);// th??m s??ch b??n BUS ???? c?? th??m v??o database
					Add_header();
					Add_row(tg);
					checkfilled();
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
				}
			}
		}
	}

	private void btXoaMouseClicked() {

		TacGiaBUS a = new TacGiaBUS();
		dstgan = a.docTacGiaAn();
		model.setRowCount(0);
		for (TacGiaDTO ncc : dstgan) {

			model.addRow(new Object[] { ncc.getMaTG(), ncc.getTenTG(), ncc.getTrangThai() });

		}
		tblTG.setModel(model);
		p3.removeAll();
		p3.add(scrollPanel);
		p3.revalidate();
		p3.repaint();

	}

	private void btSuaMouseClicked() {
		TacGiaDTO tg = new TacGiaDTO();
		TacGiaBUS bus = new TacGiaBUS();
		int i = tblTG.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n 1 t??c gi??? b???n mu???n s???a");
		}

		tblTG.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");
			}
		});
		ListSelectionModel model1 = tblTG.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = tblTG.getSelectedRow();
				System.out.println("baby ???i");
				if (row >= 0) {
					txMaTG.setText(tblTG.getValueAt(row, 0).toString());
					txMaTG.setEnabled(false);
					txTenTG.setText(tblTG.getValueAt(row, 1).toString());
					cbbtt.setSelectedItem(tblTG.getValueAt(row, 2).toString());
				}
			}
		});
		// tblQLS.addMouseListener(this);
		if (i >= 0) {
			model.setValueAt(txMaTG.getText(), i, 0);// model l?? ru???t JTable.set gi?? tr??? cho model
			model.setValueAt(txTenTG.getText(), i, 1);
			model.setValueAt(cbbtt.getSelectedItem(), i, 2);
			tblTG.setModel(model);
			tg.setMaTG(txMaTG.getText());// nap du lieu vao doi tuong(textfield)
			tg.setTenTG(txTenTG.getText());
			tg.setTrangThai(cbbtt.getSelectedItem().toString());
		}
		bus.suaTG(i, tg);
		JOptionPane.showMessageDialog(null, "S???a th??nh c??ng");
	}

	private void CHON(String conditions, String text) {
		TacGiaBUS bus = new TacGiaBUS();
		switch (conditions) {
		case "M?? TG":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_matg(text);
			Search();
			break;
		case "T??n TG":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tenTG(text);
			Search();
			break;
		}
	}

	private void Search() {
		DefaultTableModel mode = new DefaultTableModel(header, 0);
		if (kq.size() != 0) {
			for (TacGiaDTO tg : kq) {
				Vector row = new Vector();
				row.add(tg.getMaTG());
				row.add(tg.getTenTG());
				mode.addRow(row);
				//
			}
			tblTG.setModel(mode);

		}
	}

	private void bttimkiemMouseClicked() {
		System.out.println("t???i r???i n??");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			btcapnhatMouseClicked();
		} else {
			CHON(selectedItem, tukhoa);
		}
	}

	public void btcapnhatMouseClicked() {
		TacGiaDAO a = new TacGiaDAO();
		dstg = a.docTG();
		model.setRowCount(0);
		for (TacGiaDTO tg : dstg) {
			model.addRow(new Object[] { tg.getMaTG(), tg.getTenTG(), tg.getTrangThai() });
		}
		tblTG.setModel(model);
		p3.removeAll();
		p3.add(scrollPanel);
		p3.revalidate();
		p3.repaint();
	}

	public void btXuatExcelMouseClicked() {
		JFileChooser file = new JFileChooser(); // Kh???i t???o JFileChooser
		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			XSSFWorkbook excelWorkbook = new XSSFWorkbook();
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh s??ch t??c gi???");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH S??CH T??C GI???");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("M?? t??c gi???");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("T??n t??c gi???");
			cell = row.createCell(2, CellType.STRING);

			for (int i = 0; i < tblTG.getRowCount(); i++) {
				row = excelSheet.createRow((short) 3 + i);
				row.setHeight((short) 400);
				for (int j = 0; j < tblTG.getColumnCount(); j++) {
					row.createCell(j).setCellValue(tblTG.getValueAt(i, j).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Th??m")) {
			btThemMouseClicked();
		}

		if (src.equals("Tr???ng th??i")) {
			btXoaMouseClicked();
		}
		if (src.equals("S???a")) {

			btSuaMouseClicked();
			// tableMouseClicked();
		}
		if (src.equals("DS ???n")) {
			btXoaMouseClicked();
		}
		if (src.equals("T??m Ki???m")) {
			bttimkiemMouseClicked();
		}
		if (src.equals("C???p nh???t")) {
			btcapnhatMouseClicked();
		}
		if (src.equals("L??m m???i")) {
			btclearMouseClicked();
		}
		if (src.equals("Xu???t excel")) {
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
		if (e.getSource().equals(tblTG)) {
			int row = tblTG.getSelectedRow();
			System.out.println("baby ???i");
			if (row >= 0) {
				txMaTG.setText(tblTG.getValueAt(row, 0).toString());
				txTenTG.setText(tblTG.getValueAt(row, 1).toString());
				;
				txMaTG.setEnabled(false);
				cbbtt.setSelectedItem(tblTG.getValueAt(row, 2).toString());
			}
		}
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
