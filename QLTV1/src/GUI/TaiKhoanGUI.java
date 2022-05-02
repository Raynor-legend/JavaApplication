/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Frame;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.Vector;
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
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author HP
 */
public class TaiKhoanGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	JButton[] button;
	public JTable tblQLTK;
	public JTextField txtID, txtPass, txtennv;
	public JTextField txTimKiem;
	public JComboBox quyen, nhanvien;
	public JComboBox box1, cbbMaNV;
	Vector header;
	JPanel p3;
	JScrollPane scrollPanel;
	public static JPanel phienthi, QLTK;
	public static ArrayList<TaiKhoanDTO> dstk = new ArrayList<>();
	public static ArrayList<NhanVienDTO> dsnv = new ArrayList<>();

	public TaiKhoanGUI() {
		initComponent();
		TaiKhoanBUS bus = new TaiKhoanBUS();
		if (TaiKhoanBUS.dstk == null) {
			bus.docDSTK();
		}
		Add_header();
		for (TaiKhoanDTO tk : TaiKhoanBUS.dstk) {
			Add_row(tk);
		}

	}

	/**
	 *
	 */
	public void initComponent() {
		addMouseListener(this);
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));

		phienthi = QuanLiTK();
		phienthi.setBounds(0, 0, 1200, 800);
		phienthi.setBackground(Color.decode("#78909C"));

		String[] timkiem = { "Mã nhân viên" };
		box1 = new JComboBox(timkiem);
		box1.setBounds(520, 150, 120, 30);
		box1.setSelectedIndex(0);

		phienthi.add(box1);
		JPanel p2 = CHUCNANG();
		p2.setBounds(650, 50, 700, 400);
		p2.setBackground(Color.decode("#78909C"));
		p3 = TableTK();
		p3.setBounds(100, 450, 1000, 300);// set vị trí so với phienthi
		p3.setBackground(Color.decode("#78909C"));
		phienthi.add(p2);
		phienthi.add(p3);
		// add(pmenu);
		add(phienthi);
		setVisible(true);
		tblQLTK.addMouseListener(this);

	}

	/**
	 *
	 * @return
	 */
	private void showTenNV() {
		NhanVienBUS a = new NhanVienBUS();
		ArrayList<NhanVienDTO> arrNV = new ArrayList<NhanVienDTO>();
		arrNV = a.docnv();
		String getcbbMaNV = cbbMaNV.getSelectedItem().toString();
		for (int p = 0; p < arrNV.size(); p++) {
			if (getcbbMaNV.equals(arrNV.get(p).getMaNV())) {
				txtennv.setText(arrNV.get(p).getTenNV());
			}
		}
	}

	public JPanel QuanLiTK() {
		QLTK = new JPanel();
		QLTK.setLayout(null);
		JLabel[] label;
		label = new JLabel[4];
		JTextField[] textfield;

		String[] arrSanpham = { "Tìm kiếm", "Mã nhân viên", "Pass", "Quyền" };

		int toadoxLabel = 200, toadoyLabel = 150;
		int toadoxTextField = 300, toadoyTextField = 150;
		for (int i = 0; i < arrSanpham.length; i++) {
			label[i] = new JLabel(arrSanpham[i]); // khỡi tạo đối tượng thuộc Jbutton
			label[i].setBounds(toadoxLabel, toadoyLabel, 100, 30); // đinh vị trí cho từng đối tượng button
			label[i].setHorizontalAlignment(JButton.LEFT); // canh phải cho text button
			label[i].setName("btn" + i);
			label[i].setBackground(Color.decode("#FFCA28"));
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("Arial", Font.BOLD, 15));
			System.out.println(label[i]);
			QLTK.add(label[i]);
			toadoyLabel = toadoyLabel + 40;

			switch (i) {
			case 0: {
				txTimKiem = new JTextField();
				txTimKiem.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTK.add(txTimKiem);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 1: {
				NhanVienBUS b = new NhanVienBUS();
				dsnv = b.docnv();
				cbbMaNV = new JComboBox();

				for (int k = 0; k < dsnv.size(); k++) {
					if (dsnv.get(k).getTrangThai().equals("Hiện"))
						;
					cbbMaNV.addItem(dsnv.get(k).getMaNV());
				}
				cbbMaNV.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (cbbMaNV.getItemCount() != 0)
							showTenNV();
					}
				});

				cbbMaNV.setBounds(toadoxTextField, toadoyTextField, 100, 30);
				txtennv = new JTextField();
				txtennv.setBounds(400, toadoyTextField, 100, 30);
				txtennv.setEnabled(false);

				String getcbbMaNV = cbbMaNV.getSelectedItem().toString();
				for (int p = 0; p < dsnv.size(); p++) {
					if (getcbbMaNV.equals(dsnv.get(p).getMaNV())) {

						txtennv.setText(dsnv.get(p).getTenNV());
					}
				}

				QLTK.add(txtennv);
				QLTK.add(cbbMaNV);
				toadoyTextField = toadoyTextField + 40;
				break;
			}

			case 2: {
				txtPass = new JTextField();
				txtPass.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTK.add(txtPass);
				toadoyTextField = toadoyTextField + 40;
				break;
			}
			case 3: {
				String[] chucvu = { "admin", "NV", "QL" };
				quyen = new JComboBox(chucvu);
				quyen.setBounds(toadoxTextField, toadoyTextField, 200, 30);
				QLTK.add(quyen);
				toadoyTextField = toadoyTextField + 40;
				break;
			}

			}
		}

		return QLTK;
	}

	public JPanel CHUCNANG() {
		JPanel pchucnang = new JPanel();
		pchucnang.setLayout(null);
		button = new JButton[7];
		String[] arrchucnang = { "Tìm kiếm", "Thêm", "Xóa", "Hiển thị", "Xuất excel", "Làm mới" };
		// Button[] = {"btnThem","btnSua","btnXoa","btnLuuLai" };
		int toadoxButton = 50, toadoyButton = 100;
		for (int i = 0; i < arrchucnang.length; i++) {
			if (i == 3) {
				toadoxButton = 240;
				toadoyButton = 100;
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
// 
		nhanvien.setEnabled(true);
		nhanvien.setSelectedItem("");
		txtPass.setText("");
	}
//    private void showTenNV() {
//        NhanVienBUS a = new NhanVienBUS();
//        ArrayList<NhanVienDTO> arrNV = new ArrayList<NhanVienDTO>();
//        arrNV = a.docnv();
//        String getmanhanvien = nhanvien.getSelectedItem().toString();
//        for (int p = 0; p < arrNV.size(); p++) {
//            if (getmanhanvien.equals(arrNV.get(p).getMa())) {
//                txMaTG.setText(arrTG.get(p).getTenTG());
//            }
//        }
//    }

	public JPanel TableTK() {
		JPanel ptabletk = new JPanel();
		ptabletk.setLayout(null);
		ptabletk.setBackground(Color.decode("#FFCA28"));
		header = new Vector();
		header.add("Mã nhân viên");
		header.add("Pass");
		header.add("Mã quyền");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblQLTK = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblQLTK.setBounds(0, 0, 1000, 300);

		tblQLTK.setFont(new Font("Arial", 0, 15));
		tblQLTK.setModel(model);// add model len table
		tblQLTK.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tblQLTK.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblQLTK.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
		tblQLTK.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scrollPanel = new JScrollPane(tblQLTK);
		scrollPanel.setBounds(0, 0, 1000, 300);
		ptabletk.add(scrollPanel); // add table vào scrollPanel
		tblQLTK.setFillsViewportHeight(true);// hiển thị table

		return ptabletk;

	}

	private void Add_header() {
		Vector header = new Vector();
		header.add("Mã nhân viên");
		header.add("Pass");
		header.add("Mã quyền");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}

	private void Add_row(TaiKhoanDTO tk) {
		Vector row = new Vector();
		row.add(tk.getMaNV());
		row.add(tk.getPass());
		row.add(tk.getQuyen());

		model.addRow(row);
		tblQLTK.setModel(model);
	}

	private int kiemtra() {
		if (txtPass.getText().equals("")) {
			return 0;
		}
		return 1;
	}

	private void checkempty() {
		if (txtPass.getText().equals("")) {
			txtPass.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	private void checkfilled() {
		if (!txtPass.getText().equals("")) {
			txtPass.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	private void btThemMouseClicked() {
		TaiKhoanDTO tk = new TaiKhoanDTO();
		TaiKhoanBUS bus = new TaiKhoanBUS();
		tk.setMaNV(nhanvien.getSelectedItem().toString());
		tk.setPass(txtPass.getText());
		tk.setQuyen(quyen.getSelectedItem().toString());

		if (kiemtra() == 0) {
			checkempty();
			checkfilled();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
		} else {

			bus.themTK(tk);// thêm sách bên BUS đã có thêm vào database
			Add_header();
			Add_row(tk);
			checkfilled();
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		}
	}

	private void btXoaMouseClicked() {

		TaiKhoanDTO tk = new TaiKhoanDTO();
		TaiKhoanBUS bus = new TaiKhoanBUS();
		System.out.println("Nhue hello");
		int i = tblQLTK.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "Bạn cần phải chọn 1 sản phẩm");
		} else {
			if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá không", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				model.removeRow(i);
				tblQLTK.setModel(model);
				bus.xoaTK(tk, i);
			}
		}
	}

	private void btSuaMouseClicked() {
		TaiKhoanDTO tk = new TaiKhoanDTO();
		TaiKhoanBUS bus = new TaiKhoanBUS();
		int i = tblQLTK.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản bạn muốn sửa");
		}
		tblQLTK.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("123");
			}
		});
		ListSelectionModel model1 = tblQLTK.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = tblQLTK.getSelectedRow();
				System.out.println("baby ới");
				if (row >= 0) {
					nhanvien.setSelectedItem(tblQLTK.getValueAt(row, 0).toString());
					nhanvien.setEnabled(false);
					txtPass.setText(tblQLTK.getValueAt(row, 1).toString());
					quyen.setSelectedItem(tblQLTK.getValueAt(row, 2).toString());

				}
			}
		});
		// tblQLS.addMouseListener(this);
		if (i >= 0) {
			model.setValueAt(nhanvien.getSelectedItem(), i, 0);
			model.setValueAt(txtPass.getText(), i, 1);
			model.setValueAt(quyen.getSelectedItem(), i, 2);

			tblQLTK.setModel(model);
			tk.setMaNV(nhanvien.getSelectedItem().toString());
			tk.setPass(txtPass.getText());
			tk.setQuyen(quyen.getSelectedItem().toString());

		}
		bus.suaTK(i, tk);
		JOptionPane.showMessageDialog(null, "Sửa thành công");
	}

	private void bttimkiemMouseClicked() {
		System.out.println("tới rồi nè");
		String selectedItem = (String) box1.getSelectedItem();
		String tukhoa = txTimKiem.getText();
		if (tukhoa.equals("")) {
			bthienthiMouseClicked();
		} else {
			if (selectedItem.equals("Mã nhân viên")) {
				TaiKhoanBUS bus = new TaiKhoanBUS();
				Add_header();
				ArrayList<TaiKhoanDTO> kq = bus.timkiem_id(tukhoa);
				DefaultTableModel mode = new DefaultTableModel(header, 0);
				if (kq.size() != 0) {
					for (TaiKhoanDTO tk : kq) {
						Vector row = new Vector();
						row.add(tk.getMaNV());
						row.add(tk.getPass());
						row.add(tk.getQuyen());
						mode.addRow(row);
						//
					}
					tblQLTK.setModel(mode);

				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
				}
			}
		}
	}

	public void bthienthiMouseClicked() {
		System.out.println("chạy đén đây chưa bạn");
		NhanVienBUS bus = new NhanVienBUS();
		dsnv = NhanVienBUS.dsnv;
		nhanvien.removeAllItems();
		for (int k = 0; k < dsnv.size(); k++) {
			if (dsnv.get(k).getTrangThai().equals("Hiện"))
				nhanvien.addItem(dsnv.get(k).getMaNV());
		}
		nhanvien.setSelectedIndex(0);
		TaiKhoanDAO a = new TaiKhoanDAO();
		dstk = a.doctk();
		model.setRowCount(0);
		for (TaiKhoanDTO tk : dstk) {
			model.addRow(new Object[] { tk.getMaNV(), tk.getPass(), tk.getQuyen() });
		}
		tblQLTK.setModel(model);
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
			XSSFSheet excelSheet = excelWorkbook.createSheet("Danh sách tài khoản");

			XSSFRow row = null;
			XSSFCell cell = null;

			row = excelSheet.createRow((short) 1);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH TÀI KHOẢN");

			row = excelSheet.createRow((short) 2);
			row.setHeight((short) 400);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã NV");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Pass");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Quyền");
			cell = row.createCell(3, CellType.STRING);

			for (int i = 0; i < tblQLTK.getRowCount(); i++) {
				row = excelSheet.createRow((short) 3 + i);
				row.setHeight((short) 400);
				for (int j = 0; j < tblQLTK.getColumnCount(); j++) {
					row.createCell(j).setCellValue(tblQLTK.getValueAt(i, j).toString());
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

//    public static void main(String[] args) {
//        TaiKhoanGUI tk = new TaiKhoanGUI();
//        tk.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        tk.setLocationRelativeTo(tk);
//
//    }
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
		if (src.equals("Tìm kiếm")) {
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
		if (e.getSource().equals(tblQLTK)) {
			int row = tblQLTK.getSelectedRow();
			if (row >= 0) {
				cbbMaNV.setSelectedItem(tblQLTK.getValueAt(row, 0).toString());
				txtPass.setText(tblQLTK.getValueAt(row, 1).toString());

				quyen.setSelectedItem(tblQLTK.getValueAt(row, 2).toString());

			}
		}
	}

}
