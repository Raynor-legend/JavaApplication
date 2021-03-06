
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CTHDBUS;
import BUS.CTHDNhapBUS;
import BUS.CongCu;
import BUS.HienHanh;
import BUS.HoaDonBUS;
import BUS.PhieuNhapBUS;
import BUS.SanphamBUS;
import DTO.CTHDDTO;
import DTO.CTHDNhapDTO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapDTO;
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
import java.time.LocalDate;
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

/**
 *
 * @author HP
 */
public class NhapHangGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel modelCTHDNhap = new DefaultTableModel();
	JScrollPane scroll = new JScrollPane();
	public JTable tblSP, tblCTHDNhap;
	public JTextField txMaPhieuNhap, txNgayLap, txMaNV, txMaNCC;
	Vector header;

	public NhapHangGUI() {
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
		JPanel p1 = hoadon();
		p1.setBackground(Color.gray);
		p1.setBounds(0, 0, 1300, 200);

		JPanel p2 = sanpham();
		p2.setBackground(Color.GRAY);
		p2.setBounds(0, 250, 580, 700);

		JPanel p3 = giohang();
		p3.setBackground(Color.GRAY);
		p3.setBounds(600, 250, 580, 700);

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
		header.add("????n gi?? ");
		if (modelCTHDNhap.getRowCount() == 0) {
			modelCTHDNhap = new DefaultTableModel(header, 0);
		}
	}
//

	private void Add_row(SanphamDTO Sanpham) {
		Vector row = new Vector();
		row.add(Sanpham.getMaSanpham());
		row.add(Sanpham.getTenSanpham());
		row.add(Sanpham.getSoLuong());
		row.add(Sanpham.getDonGia());
		modelCTHDNhap.addRow(row);
		tblSP.setModel(modelCTHDNhap);
	}

	public JPanel hoadon() {
		JPanel phoadon = new JPanel();
		phoadon.setLayout(null);
		JLabel lbmahd = new JLabel("M?? h??a ????n");
		lbmahd.setBounds(200, 30, 200, 30);
		JTextField txMaPhieuNhap = new JTextField();
		txMaPhieuNhap.setBounds(300, 30, 200, 30);

		JLabel lbngaylap = new JLabel("Ng??y l???p");
		lbngaylap.setBounds(200, 70, 200, 30);
		JTextField txNgayLap = new JTextField();
		txNgayLap.setBounds(300, 70, 200, 30);

		JLabel lbmanv = new JLabel("M?? nh??n vi??n");
		lbmanv.setBounds(200, 110, 200, 30);
		JTextField txMaNV = new JTextField();
		txMaNV.setBounds(300, 110, 200, 30);

		JLabel lbmakh = new JLabel("M?? kh??ch h??ng");
		lbmakh.setBounds(600, 30, 200, 30);
		JTextField txMaNCC = new JTextField();
		txMaNCC.setBounds(700, 30, 200, 30);

		JButton bttimkiem = new JButton("...");
		bttimkiem.setBounds(900, 30, 40, 30);

		JLabel lbdiachi = new JLabel("?????a ch???");
		lbdiachi.setBounds(600, 70, 200, 30);
		JTextField txdiachi = new JTextField();
		txdiachi.setBounds(700, 70, 200, 30);

		JLabel lbsdt = new JLabel("S??T");
		lbsdt.setBounds(600, 110, 200, 30);
		JTextField txsdt = new JTextField();
		txsdt.setBounds(700, 110, 200, 30);
		phoadon.add(lbmahd);
		phoadon.add(lbmanv);
		phoadon.add(txMaPhieuNhap);
		phoadon.add(txMaNV);
		phoadon.add(lbngaylap);
		phoadon.add(txNgayLap);
		phoadon.add(lbmakh);
		phoadon.add(txMaNCC);
		phoadon.add(lbdiachi);
		phoadon.add(txdiachi);
		phoadon.add(lbsdt);
		phoadon.add(txsdt);
		// phoadon.add(txtimkiem);
		phoadon.add(bttimkiem);

		return phoadon;
	}

	public JPanel sanpham() {
		JPanel psanpham = new JPanel();
		psanpham.setLayout(null);
		psanpham.setBackground(Color.orange);
		JLabel lbTimKiem = new JLabel("T??m ki???m");
		lbTimKiem.setBounds(10, 40, 80, 30);

		JTextField txTimKiem = new JTextField();
		txTimKiem.setBounds(100, 40, 200, 30);

		String[] arr = { "M?? n?????c u???ng", "T??n n?????c u???ng" };
		JComboBox cbbtk = new JComboBox(arr);
		cbbtk.setBounds(300, 40, 100, 30);

		JButton bttimkiem = new JButton("T??m ki???m");
		bttimkiem.setBounds(400, 40, 100, 30);

		JButton btchon = new JButton("Ch???n h??ng");
		btchon.setBounds(480, 350, 100, 30);

		header = new Vector();
		header.add("STT");
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ng");
		header.add("S??? l?????ng");
		header.add("????n gi?? nh???p");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblSP = new JTable(null, header);
		tblSP.setBounds(0, 100, 580, 250);

		tblSP.setFont(new Font("Arial", 0, 15));
		tblSP.setModel(model);// add model len table
		tblSP.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblSP.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblSP.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblSP.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblSP);
		scroll.setBounds(0, 100, 580, 250);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblSP.setPreferredSize(new Dimension(500, 500));
		scroll.setPreferredSize(new Dimension(500, 500));

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

		psanpham.add(scroll); // add table v??o scrollPanel
		tblSP.setFillsViewportHeight(true);// hi???n th??? table
		psanpham.add(txTimKiem);
		psanpham.add(lbTimKiem);
		psanpham.add(bttimkiem);
		psanpham.add(cbbtk);
		psanpham.add(btchon);

		return psanpham;
	}

	public JPanel giohang() {
		JPanel pgiohang = new JPanel();
		pgiohang.setLayout(null);
		pgiohang.setBackground(Color.orange);
		JLabel lbChiTiet = new JLabel("Chi ti???t phi???u nh???p");
		lbChiTiet.setBounds(250, 40, 150, 50);
		lbChiTiet.setForeground(Color.ORANGE);
		lbChiTiet.setFont(new Font("Arial", BOLD, 20));
		JButton btXoa = new JButton("X??a");
		btXoa.setBounds(370, 350, 100, 30);

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

		btThanhtoan.setBounds(480, 350, 100, 30);
		header = new Vector();
		header.add("STT");
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ng");
		header.add("????n gi??");
		header.add("S??? l?????ng");
		header.add("T???ng c???ng");

		if (modelCTHDNhap.getRowCount() == 0) {
			modelCTHDNhap = new DefaultTableModel(header, 0);
		}
		tblCTHDNhap = new JTable(null, header) {
			public boolean isCellEditable(int rowIndex, int mCollndex) {
				return false;
			}
		};
		tblCTHDNhap.setBounds(0, 100, 580, 250);

		tblCTHDNhap.setFont(new Font("Arial", 0, 15));
		tblCTHDNhap.setModel(modelCTHDNhap);// add model len table
		tblCTHDNhap.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblCTHDNhap.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblCTHDNhap.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblCTHDNhap.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblCTHDNhap);
		scroll.setBounds(0, 100, 580, 250);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblCTHDNhap.setPreferredSize(new Dimension(500, 500));
		scroll.setPreferredSize(new Dimension(500, 500));

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

	private void ThemGioHang() {
		DefaultTableModel modelCTHDNhap = (DefaultTableModel) tblCTHDNhap.getModel();
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
					} catch (NumberFormatException e) { // Ki??????m tra nh??????p s???????
						JOptionPane.showMessageDialog(null, "S??? l?????ng ph???i l?? s??? nguy??n");
						checkInput = true;
					}
					if ((soLuongThem <= sLSP) && (soLuongThem != 0)) {
						// int SLSPConLai = giaTriSLSP - soLuongThem;
						tblSP.setValueAt(sLSP - soLuongThem, tblSP.getSelectedRow(), 2);// Set s??? l?????ng c??n l???i

						// Th??m v??o chi ti???t
						String maSP = tblSP.getValueAt(tblSP.getSelectedRow(), 0).toString();
						String tenSP = tblSP.getValueAt(tblSP.getSelectedRow(), 1).toString();

						int donGiaSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 3).toString());
						// int donGiaSP = CongCu.FormatTienStringSangInt(donGiaSPStr);

						int thanhTien = soLuongThem * donGiaSP;
						// String thanhTienStr =String.valueOf(thanhTien) ;

						// Ki??????m tra s??????n ph??????m ????????? t???????n t??????i trong gi??????? h?? ng hay ch????a, n??????u
						// c???? th???? t????ng s??????? l??????????ng
						if (tblCTHDNhap.getRowCount() == 0) {
							Object[] row = { tblCTHDNhap.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem,
									String.valueOf(thanhTien) };
							modelCTHDNhap.addRow(row);
						} else {
							checkDaCo = 0;
							for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
								// int maSPGioHang = Integer.parseInt(tblGioHang.getValueAt(i, 1).toString());
								if (maSP == tblCTHDNhap.getValueAt(i, 1).toString()) {
									checkDaCo = 1;
									int soLuongDaCo = Integer.parseInt(tblCTHDNhap.getValueAt(i, 2).toString());
									tblCTHDNhap.setValueAt(soLuongDaCo + soLuongThem, i, 3); // Thay ????????????i s???????
																								// l??????????ng
									tblCTHDNhap.setValueAt(((soLuongDaCo + soLuongThem) * donGiaSP), i, 5);// Thay
																											// ????????????i
																											// th?? nh
																											// ti???????n
									break;
								}
							}
							if (checkDaCo != 1) {
								Object[] row = { tblCTHDNhap.getRowCount() + 1, maSP, tenSP, donGiaSP, soLuongThem,
										String.valueOf(thanhTien) };
								modelCTHDNhap.addRow(row);
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Trong kho h??ng ch??? c??n" + sLSP);
						checkInput = true;
					}

				} else {
					checkInput = false; // N??????u b??????m Tho????t th???? tho????t v????ng l??????p
				}
			} while (checkInput); // V????ng l??????p check nh??????p v?? o

		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m mu???n th??m");
		}

		this.TinhTongTien();
	}

	private void TinhTongTien() {
		int tamTinh = 0;
		int tongTien = 0;

		for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
			String thanhTienSPStr = tblCTHDNhap.getValueAt(i, 5).toString();
			// int thanhTienSP = CongCu.FormatTienStringSangInt(thanhTienSPStr);

			tamTinh += Integer.parseInt(thanhTienSPStr);
			tongTien += Integer.parseInt(thanhTienSPStr);
		}

		String tamTinhStr = String.valueOf(tamTinh);
		HienHanh.setTongTien(String.valueOf(tongTien));
		System.out.println(tamTinh);
		// G??????n v?? o textfield
		// txfTamTinh.setText(tamTinhStr);
		// txfTongTien.setText(tongTienStr);

	}

	private void xoaCTHD() {
		DefaultTableModel modelCTHDNhapnhapnhap = (DefaultTableModel) tblCTHDNhap.getModel();

		if (tblCTHDNhap.getSelectedRow() != -1) {
			int rowDaChon = tblCTHDNhap.getSelectedRow();
			String maSPGioHang = tblCTHDNhap.getValueAt(rowDaChon, 1).toString();
			int soLuongSPGH = Integer.parseInt(tblCTHDNhap.getValueAt(rowDaChon, 4).toString());

			for (int i = 0; i < tblSP.getRowCount(); i++) {
				String maSP = tblSP.getValueAt(i, 0).toString();
				if (maSP.equals(maSPGioHang)) {
					int soLuongSP = Integer.parseInt(tblSP.getValueAt(i, 2).toString());
					tblSP.setValueAt(soLuongSP + soLuongSPGH, i, 2);
					modelCTHDNhapnhapnhap.removeRow(tblCTHDNhap.getSelectedRow());
					break;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Ch??a ch???n s???n ph???m mu???n xo??");
		}

		this.TinhTongTien();
	}

	private void ThanhToan() {
		SanphamDTO sanphamDTO = new SanphamDTO();
		SanphamBUS SanphamBUS = new SanphamBUS();
		PhieuNhapDTO pnDTO = new PhieuNhapDTO();
		PhieuNhapBUS pnBUS = new PhieuNhapBUS();
		CTHDNhapDTO chitiethdNhapDTO = new CTHDNhapDTO();
		CTHDNhapBUS chitiethdNhapBUS = new CTHDNhapBUS();

		if (tblCTHDNhap.getRowCount() != 0) {
			//txMaNCC = new JTextField();
			txMaPhieuNhap = new JTextField();
			txMaNV = new JTextField();
			int ma = pnBUS.getMa();
			pnDTO.setMaPhieuNhap(ma);

			//pnDTO.setMaNCC(HienHanh.getNCC());

			pnDTO.setMaNV(HienHanh.NhanVien);

			pnDTO.setNgayNhap(LocalDate.now());
			pnDTO.setTongtien(String.valueOf(HienHanh.getTongTien()));
			pnBUS.themPN(pnDTO);
			try {
				for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
					chitiethdNhapDTO.setSTT(chitiethdNhapBUS.getSTT());
					chitiethdNhapDTO.setMaPhieuNhap(ma);
					chitiethdNhapDTO.setMaSanpham(tblCTHDNhap.getValueAt(i, 1).toString());
					chitiethdNhapDTO.setTenSanpham(tblCTHDNhap.getValueAt(i, 2).toString());
					chitiethdNhapDTO.setDonGia(tblCTHDNhap.getValueAt(i, 3).toString());

					chitiethdNhapDTO.setSoLuong(tblCTHDNhap.getValueAt(i, 4).toString());
					chitiethdNhapDTO.setThanhTien(tblCTHDNhap.getValueAt(i, 5).toString());
					chitiethdNhapBUS.themCTHDNhap(chitiethdNhapDTO);
					for (int k = 0; k < tblSP.getRowCount(); k++) {
						String maSanphamCT = tblCTHDNhap.getValueAt(i, 1).toString();
						String maSanpham = tblSP.getValueAt(k, 0).toString();
						if (maSanphamCT.equals(maSanpham)) {
							sanphamDTO.setDonGia(tblCTHDNhap.getValueAt(i, 3).toString());
							sanphamDTO.setSoLuong(tblSP.getValueAt(k, 2).toString());

							sanphamDTO.setMaSanpham(maSanpham);

							SanphamBUS.sua1Sanpham(k, sanphamDTO);
						}
					}
				}
//                txMaPhieuNhap.setText("");
//                txMaNCC.setText("");
//                txMaNV.setText(""); // Ngay d??ng n??y b??? l???i n??
////                Ch???y ???????c r???i ???? m?? giao di???n  c???a em b??? l???i g?? kia 

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui l????ng th????m s??????n ph??????m v?? o gi??????? h?? ng!");
		}
		DefaultTableModel modelCTHDNhapnhapnhap = (DefaultTableModel) tblCTHDNhap.getModel();
		modelCTHDNhapnhapnhap.setRowCount(0);
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
