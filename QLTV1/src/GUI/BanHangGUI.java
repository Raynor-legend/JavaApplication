
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
import BUS.SanphamBUS;
import DAO.Connect;
import DAO.SanphamDAO;
import DTO.CTHDDTO;
import DTO.HoaDonDTO;
import DTO.SanphamDTO;
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
	public ArrayList<SanphamDTO> kq;
	public JComboBox cbbtk;
	JButton bttimkiem;
	JPanel p2, p3;
	public static ArrayList<SanphamDTO> dsSanpham = new ArrayList<>();

	Vector header;

	public BanHangGUI() {
		initComponents();
		SanphamBUS bus = new SanphamBUS();
		if (SanphamBUS.dsSanpham == null) {
			bus.docDSSanpham();
		}
		Add_header();
		for (SanphamDTO Sanpham : SanphamBUS.dsSanpham) {
			Add_row(Sanpham);
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
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ng");
		header.add("S??? l?????ng");
		header.add("????n gi?? b??n");
		if (modelCTHD.getRowCount() == 0) {
			modelCTHD = new DefaultTableModel(header, 0);
		}
	}
//

	private void Add_row(SanphamDTO Sanpham) {
		Vector row = new Vector();
		row.add(Sanpham.getMaSanpham());
		row.add(Sanpham.getTenSanpham());
		row.add(Sanpham.getSoLuong());
		row.add(Sanpham.getDonGia());
		modelCTHD.addRow(row);
		tblSP.setModel(modelCTHD);
	}

	public JPanel hoadon() {
		JPanel phoadon = new JPanel();
		phoadon.setLayout(null);
		JLabel lbmakh = new JLabel("M?? kh??ch h??ng");
		lbmakh.setBounds(100, 30, 200, 30);
		lbmakh.setForeground(Color.WHITE);
		lbmakh.setFont(new Font("Arial", Font.BOLD, 20));

//        JTextField txMaKH = new JTextField();
//        txMaKH.setText(HienHanh.getKhachHang());
		HienHanh.setKhachHang("0");
//        txMaKH.setBounds(300, 30, 200, 30);

		JButton bttimkiem = new JButton("M?? kh??ch h??ng");
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
		// phoadon.add(txtimkiem);
		phoadon.add(bttimkiem);

		return phoadon;
	}

	public JPanel sanpham() {
		JPanel psanpham = new JPanel();
		psanpham.setLayout(null);
		psanpham.setBackground(Color.orange);
		JLabel lbTimKiem = new JLabel("T??m ki???m");
		lbTimKiem.setBounds(30, 40, 100, 30);
		lbTimKiem.setForeground(Color.WHITE);
		lbTimKiem.setFont(new Font("Arial", Font.BOLD, 20));

		txTimKiem = new JTextField();
		txTimKiem.setBounds(130, 40, 200, 30);

		String[] arr = { "M?? n?????c u???ng", "T??n n?????c u???ng" };
		cbbtk = new JComboBox(arr);
		cbbtk.setBounds(350, 40, 100, 30);
		cbbtk.setSelectedIndex(0);

		bttimkiem = new JButton("T??m ki???m");
		bttimkiem.setBounds(460, 40, 140, 30);
		bttimkiem.setForeground(Color.BLACK);
		bttimkiem.setFont(new Font("Arial", Font.BOLD, 20));
		bttimkiem.setBackground(Color.decode("#FFCA28"));
		bttimkiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("T??m ki???m")) {
					timkiemMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});

		JButton btchon = new JButton("Ch???n h??ng");
		btchon.setBounds(420, 650, 150, 40);
		btchon.setForeground(Color.BLACK);
		btchon.setFont(new Font("Arial", Font.BOLD, 20));
		btchon.setBackground(Color.decode("#FFCA28"));
		btchon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Ch???n h??ng")) {
					btchonHangMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});

		JButton btql = new JButton("Quay l???i");
		btql.setBounds(10, 650, 150, 40);
		btql.setForeground(Color.BLACK);
		btql.setFont(new Font("Arial", Font.BOLD, 20));
		btql.setBackground(Color.decode("#FFCA28"));
		btql.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Quay l???i")) {
					btnCapnhatMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});
		JButton btcn = new JButton("C???p nh???t");
		btcn.setBounds(250, 650, 150, 40);
		btcn.setForeground(Color.BLACK);
		btcn.setFont(new Font("Arial", Font.BOLD, 20));
		btcn.setBackground(Color.decode("#FFCA28"));
		btcn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("C???p nh???t")) {
					btnCapnhatMouseClicked();
				}
			}

		});
		header = new Vector();
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ng");
		header.add("S??? l?????ng");
		header.add("????n gi?? b??n");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblSP = new JTable(null, header);

		tblSP.setFont(new Font("Arial", 0, 15));
		tblSP.setModel(model);// add model len table
		tblSP.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblSP.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblSP.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblSP.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblSP);
		scroll.setBounds(40, 100, 500, 530);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblSP.setPreferredSize(new Dimension(300, 300));
		scroll.setPreferredSize(new Dimension(300, 500));

		psanpham.add(scroll); // add table v??o scrollPanel
		tblSP.setFillsViewportHeight(true);// hi???n th??? table
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
		JLabel lbChiTiet = new JLabel("CHI TI???T GI??? H??NG");
		lbChiTiet.setBounds(250, 0, 200, 50);
		lbChiTiet.setForeground(Color.WHITE);
		lbChiTiet.setFont(new Font("Arial", BOLD, 20));

		JButton btXoa = new JButton("X??a");
		btXoa.setBounds(10, 500, 100, 40);
		btXoa.setFont(new Font("Arial", Font.BOLD, 20));
		btXoa.setForeground(Color.BLACK);
		btXoa.setBackground(Color.decode("#FFCA28"));

		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("X??a")) {
					btXoaMouseClicked();
				}
				throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																				// choose Tools | Templates.
			}
		});

		JButton btThanhtoan = new JButton("Thanh to??n");
		btThanhtoan.setFont(new Font("Arial", Font.BOLD, 20));
		btThanhtoan.setForeground(Color.BLACK);
		btThanhtoan.setBackground(Color.decode("#FFCA28"));
		btThanhtoan.setBounds(500, 500, 150, 40);

		btThanhtoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Thanh to??n")) {
					btThanhToanMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});

		header = new Vector();
		header.add("STT");
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ngg");
		header.add("????n gi??");
		header.add("S??? l?????ng");
		header.add("T???ng c???ng");

		if (modelCTHD.getRowCount() == 0) {
			modelCTHD = new DefaultTableModel(header, 0);
		}
		tblCTHD = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};

		tblCTHD.setFont(new Font("Arial", 0, 15));
		tblCTHD.setModel(modelCTHD);// add model len table
		tblCTHD.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblCTHD.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblCTHD.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblCTHD.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblCTHD);
		scroll.setBounds(60, 50, 450, 430);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblCTHD.setPreferredSize(new Dimension(300, 300));
		scroll.setPreferredSize(new Dimension(300, 300));

		pgiohang.add(scroll); // add table v??o scrollPanel
		tblSP.setFillsViewportHeight(true);// hi???n th??? table
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
		SanphamBUS bus = new SanphamBUS();
		switch (condition) {
		case "M?? n?????c u???ng":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_maSanpham(text);
			Search();
			break;

		case "T??n n?????c u???ng":
			Add_header();
			model.setRowCount(0);
			kq = bus.timkiem_tenSanpham(text);
			Search();
			break;

		}
	}

	private void Search() {
		// DefaultTableModel mode = new DefaultTableModel(header, 0);
		model.setRowCount(0);
		if (kq.size() != 0) {
			for (SanphamDTO Sanpham : kq) {
				Vector row = new Vector();
				row.add(Sanpham.getMaSanpham());
				row.add(Sanpham.getTenSanpham());
				row.add(Sanpham.getSoLuong());
				row.add(Sanpham.getDonGia());
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
		SanphamDAO a = new SanphamDAO();
		dsSanpham = a.docSanpham();
		// Add_header();
		model.setRowCount(0);
		for (SanphamDTO s : dsSanpham) {
			model.addRow(new Object[] { s.getMaSanpham(), s.getTenSanpham(), s.getSoLuong(), s.getDonGia() });
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
			int sLSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 2).toString()); // H?? ??? ????y

			do {
				checkInput = false;
				String soLuongThemStr = JOptionPane.showInputDialog("Ch???n s??? l?????ng mu???n nh???p ");

				if (soLuongThemStr != null) {

					try {
						soLuongThem = Integer.parseInt(soLuongThemStr);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "S??? l?????ng ph???i l?? s??? nguy??n");
						checkInput = true;
					}
					if ((soLuongThem <= sLSP) && (soLuongThem != 0)) {
						tblSP.setValueAt(sLSP - soLuongThem, tblSP.getSelectedRow(), 2);

						String maSP = tblSP.getValueAt(tblSP.getSelectedRow(), 0).toString();
						String tenSP = tblSP.getValueAt(tblSP.getSelectedRow(), 1).toString();

						int donGiaSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 3).toString());

						int thanhTien = soLuongThem * donGiaSP;
						if (tblCTHD.getRowCount() == 0) {
							Object[] row = { tblCTHD.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem,
									String.valueOf(thanhTien) };
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
								Object[] row = { tblCTHD.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem,
										String.valueOf(thanhTien) };
								modelCTHD.addRow(row);
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Trong kho h??ng ch??? c??n" + sLSP);
						checkInput = true;
					}

				} else {
					checkInput = false;
				}
			} while (checkInput);

		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n n?????c u???ng mu???n th??m");
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
			JOptionPane.showMessageDialog(null, "Ch??a ch???n n?????c u???ng c???n xo??");
		}

		this.TinhTongTien();
		txTong.setText(HienHanh.getTongTien());
	}

	private void ThanhToan() {
		SanphamDTO sanphamDTO = new SanphamDTO();
		SanphamBUS SanphamBUS = new SanphamBUS();
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
			// txMaKH.setText(String.valueOf(HienHanh.KhachHang));
			hoadonDTO.setMaKH(HienHanh.getKhachHang());
			hoadonDTO.setMaNV(HienHanh.NhanVien);
			hoadonDTO.setNgayLap(LocalDate.now());
			hoadonDTO.setThanhTien(String.valueOf(HienHanh.getTongTien()));
			hoadonBUS.themHD(hoadonDTO);
			try {

				for (int i = 0; i < tblCTHD.getRowCount(); i++) {
					chitiethdDTO.setSTT(chitiethdBUS.getSTT());
					chitiethdDTO.setMaHD(ma);
					chitiethdDTO.setMaSanpham(tblCTHD.getValueAt(i, 1).toString());
					chitiethdDTO.setTenSanpham(tblCTHD.getValueAt(i, 2).toString());
					chitiethdDTO.setGiaBan(tblCTHD.getValueAt(i, 3).toString());

					chitiethdDTO.setSoluong(tblCTHD.getValueAt(i, 4).toString());
					chitiethdDTO.setThanhTien(tblCTHD.getValueAt(i, 5).toString());
					chitiethdBUS.themCTHD(chitiethdDTO);
					for (int k = 0; k < tblSP.getRowCount(); k++) {
						String maSanphamCT = tblCTHD.getValueAt(i, 1).toString();
						String maSanpham = tblSP.getValueAt(k, 0).toString();
						if (maSanphamCT.equals(maSanpham)) {
							sanphamDTO.setDonGia(tblCTHD.getValueAt(i, 3).toString());
							sanphamDTO.setSoLuong(tblSP.getValueAt(k, 2).toString());

							sanphamDTO.setMaSanpham(maSanpham);

							SanphamBUS.sua1Sanpham(k, sanphamDTO);
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
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n n?????c u???ng!");
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
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void mousePressed(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void mouseExited(MouseEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
