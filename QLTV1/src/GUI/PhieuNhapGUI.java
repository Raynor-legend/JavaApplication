
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
import BUS.NCCBUS;
import BUS.PhieuNhapBUS;
import BUS.SanphamBUS;
import DAO.SanphamDAO;
import DTO.CTHDDTO;
import DTO.CTHDNhapDTO;
import DTO.HoaDonDTO;
import DTO.NCCDTO;
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
import java.util.ArrayList;
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
public class PhieuNhapGUI extends JPanel implements ActionListener, MouseListener {

	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel modelCTHDNhap = new DefaultTableModel();
	JScrollPane scroll = new JScrollPane();
	public JTable tblSP, tblCTHDNhap;
	public JTextField txMaPhieuNhap, txNgayLap, txMaNV, txMaNCC, txTimKiem;
	public JComboBox cbbtk;
	public JButton btNCC, bttimkiem;
	public JPanel p2, p3;
	Vector header;
	public static ArrayList<SanphamDTO> kq;
	public static ArrayList<SanphamDTO> dsSanpham = new ArrayList<>();
//    public static ArrayList<NCCDTO> dsncc = new ArrayList<NCCDTO>();
//    public JComboBox cbbMaNCC;

	public PhieuNhapGUI() {
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
		header.add("????n gi?? ");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
	}
//

	private void Add_row(SanphamDTO Sanpham) {
		Vector row = new Vector();
		row.add(Sanpham.getMaSanpham());
		row.add(Sanpham.getTenSanpham());
		row.add(Sanpham.getSoLuong());
		row.add(Sanpham.getDonGia());
		model.addRow(row);
		tblSP.setModel(model);
	}

	public JPanel hoadon() {
		JPanel phoadon = new JPanel();
		phoadon.setLayout(null);
		JLabel lbmancc = new JLabel("M?? nh?? cung c???p");
		lbmancc.setBounds(100, 30, 200, 30);
		lbmancc.setForeground(Color.WHITE);
		lbmancc.setFont(new Font("Arial", Font.BOLD, 20));
//        txMaNCC = new JTextField();
//        txMaNCC.setBounds(300, 30, 200, 30);
		JButton btNCC = new JButton("M?? nh?? cung c???p");
		btNCC.setBounds(300, 30, 200, 30);

		btNCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmTimKiemNCC TimKiemNCC = new frmTimKiemNCC();
				TimKiemNCC.setVisible(true);

			}
		});
		phoadon.add(lbmancc);
//        phoadon.add(txMaNCC);
		phoadon.add(btNCC);
		return phoadon;
	}

	public JPanel sanpham() {
		JPanel psanpham = new JPanel();
		psanpham.setLayout(null);
		psanpham.setBackground(Color.orange);
		JLabel lbTimKiem = new JLabel("T??m ki???m");
		lbTimKiem.setBounds(10, 40, 100, 30);
		lbTimKiem.setForeground(Color.WHITE);
		lbTimKiem.setFont(new Font("Arial", Font.BOLD, 20));

		txTimKiem = new JTextField();
		txTimKiem.setBounds(100, 40, 150, 30);

		String[] arr = { "M?? n?????c u???ng", "T??n n?????c u???ng" };
		cbbtk = new JComboBox(arr);
		cbbtk.setBounds(270, 40, 150, 30);
		cbbtk.setSelectedIndex(0);

		bttimkiem = new JButton("T??m ki???m");
		bttimkiem.setBounds(430, 40, 150, 30);
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
		JButton btchon = new JButton("Ch???n h??ng");
		btchon.setBounds(420, 650, 150, 40);
		btchon.setForeground(Color.BLACK);
		btchon.setFont(new Font("Arial", Font.BOLD, 20));
		btchon.setBackground(Color.decode("#FFCA28"));

		header = new Vector();
		header.add("M?? n?????c u???ng");
		header.add("T??n n?????c u???ng");
		header.add("S??? l?????ng");
		header.add("????n gi?? nh???p");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblSP = new JTable(null, header);
		tblSP.setBounds(0, 100, 580, 530);

		tblSP.setFont(new Font("Arial", 0, 15));
		tblSP.setModel(model);// add model len table
		tblSP.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblSP.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblSP.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblSP.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblSP);
		scroll.setBounds(0, 100, 580, 530);
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
		psanpham.add(btcn);
		psanpham.add(btql);
		return psanpham;
	}

	public JPanel giohang() {
		JPanel pgiohang = new JPanel();
		pgiohang.setLayout(null);
		pgiohang.setBackground(Color.decode("#78909C"));
		JLabel lbChiTiet = new JLabel("CHI TI???T PHI???U NH???P");
		lbChiTiet.setBounds(200, 0, 250, 50);
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
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
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
		btThanhtoan.setFont(new Font("Arial", Font.BOLD, 20));
		btThanhtoan.setForeground(Color.BLACK);
		btThanhtoan.setBackground(Color.decode("#FFCA28"));
		btThanhtoan.setBounds(500, 500, 150, 40);
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
		tblCTHDNhap.setBounds(0, 50, 650, 430);

		tblCTHDNhap.setFont(new Font("Arial", 0, 15));
		tblCTHDNhap.setModel(modelCTHDNhap);// add model len table
		tblCTHDNhap.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblCTHDNhap.getTableHeader().setForeground(Color.black); // set m??u ch??? cho header
		tblCTHDNhap.getTableHeader().setPreferredSize(new Dimension(50, 50));// set ????? d??i ????? r???ng c???a header
		tblCTHDNhap.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblCTHDNhap);
		scroll.setBounds(0, 50, 650, 430);
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

	private void btXoaMouseClicked() {
		xoaCTHD();
	}

	private void btThanhToanMouseClicked() {
		ThanhToan();
	}

	private void ThemGioHang() {
		DefaultTableModel modelCTHDNhap = (DefaultTableModel) tblCTHDNhap.getModel();
		int soLuongThem = 0;
		int DonGia = 0;
		boolean checkInput = false;
		int checkDaCo = 0;

		if (tblSP.getSelectedRow() != -1) {
			int sLSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 2).toString());

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
//                    if ((soLuongThem <= sLSP) && (soLuongThem != 0)) {
					// int SLSPConLai = giaTriSLSP - soLuongThem;

					// Th??m v??o chi ti???t
					String maSP = tblSP.getValueAt(tblSP.getSelectedRow(), 0).toString();
					String tenSP = tblSP.getValueAt(tblSP.getSelectedRow(), 1).toString();
					String DonGiaStr = JOptionPane.showInputDialog("Nh???p ????n gi?? ");
					if (DonGiaStr != null) {

						try {
							DonGia = Integer.parseInt(DonGiaStr);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "????n gi?? ph???i l?? s??? nguy??n");
							checkInput = true;
						}

						tblSP.setValueAt(sLSP + soLuongThem, tblSP.getSelectedRow(), 2);// Set s??? l?????ng c??n l???i

						int thanhTien = soLuongThem * DonGia;
						if (tblCTHDNhap.getRowCount() == 0) {
							Object[] row = { tblCTHDNhap.getRowCount() + 1, maSP, tenSP, DonGia, soLuongThem,
									String.valueOf(thanhTien) };
							modelCTHDNhap.addRow(row);
						} else {
							checkDaCo = 0;
							for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
								// int maSPGioHang = Integer.parseInt(tblGioHang.getValueAt(i, 1).toString());
								if (maSP == tblCTHDNhap.getValueAt(i, 1).toString()) {
									checkDaCo = 1;
									int soLuongDaCo = Integer.parseInt(tblCTHDNhap.getValueAt(i, 4).toString());
									tblCTHDNhap.setValueAt(soLuongDaCo + soLuongThem, i, 3);
									tblCTHDNhap.setValueAt(((soLuongDaCo + soLuongThem) * DonGia), i, 5);
									break;
								}
							}
							if (checkDaCo != 1) {
								Object[] row = { tblCTHDNhap.getRowCount() + 1, maSP, tenSP, DonGia, soLuongThem,
										String.valueOf(thanhTien) };
								modelCTHDNhap.addRow(row);
							}
							System.out.println();
						}

						this.TinhTongTien();
						System.out.println(HienHanh.TongTien);
					} else {
						checkInput = false;
					}
				}
			} while (checkInput);

		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m mu???n th??m");
		}
	}

	private void TinhTongTien() {
		int tamTinh = 0;
		int tongTien = 0;

		for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
			String thanhTienSPStr = tblCTHDNhap.getValueAt(i, 5).toString();

			tamTinh += Integer.parseInt(thanhTienSPStr);
			tongTien += Integer.parseInt(thanhTienSPStr);
		}

		String tamTinhStr = String.valueOf(tamTinh);
		HienHanh.setTongTien(String.valueOf(tongTien));
		System.out.println(tamTinh);

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
					tblSP.setValueAt(soLuongSP - soLuongSPGH, i, 2);
					modelCTHDNhapnhapnhap.removeRow(tblCTHDNhap.getSelectedRow());
					break;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m mu???n x??a");
		}

		this.TinhTongTien();
	}

	private void ThanhToan() {
		SanphamDTO sanphamDTO = new SanphamDTO();
		SanphamBUS SanphamBUS = new SanphamBUS();
		PhieuNhapDTO phieunhapDTO = new PhieuNhapDTO();
		PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
		CTHDNhapDTO chitiethdNhapDTO = new CTHDNhapDTO();
		CTHDNhapBUS chitiethdBUS = new CTHDNhapBUS();
//		if (HienHanh.getNCC() != null) 
                {
			if (tblCTHDNhap.getRowCount() != 0) {
				//txMaNCC = new JTextField();
				txMaPhieuNhap = new JTextField();
				txMaNV = new JTextField();
				int ma = phieunhapBUS.getMa();
				phieunhapDTO.setMaPhieuNhap(ma);

				//phieunhapDTO.setMaNCC(HienHanh.getNCC());

				phieunhapDTO.setMaNV(HienHanh.NhanVien);

				phieunhapDTO.setNgayNhap(LocalDate.now());
				phieunhapDTO.setTongtien(String.valueOf(HienHanh.getTongTien()));
				phieunhapBUS.themPN(phieunhapDTO);
				try {
					for (int i = 0; i < tblCTHDNhap.getRowCount(); i++) {
						chitiethdNhapDTO.setSTT(chitiethdBUS.getSTT());
						chitiethdNhapDTO.setMaPhieuNhap(ma);
						chitiethdNhapDTO.setMaSanpham(tblCTHDNhap.getValueAt(i, 1).toString());
						chitiethdNhapDTO.setTenSanpham(tblCTHDNhap.getValueAt(i, 2).toString());
						chitiethdNhapDTO.setDonGia(tblCTHDNhap.getValueAt(i, 3).toString());

						chitiethdNhapDTO.setSoLuong(tblCTHDNhap.getValueAt(i, 4).toString());
						chitiethdNhapDTO.setThanhTien(tblCTHDNhap.getValueAt(i, 5).toString());
						chitiethdBUS.themCTHDNhap(chitiethdNhapDTO);
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
//                    txMaPhieuNhap.setText("");
//                    txMaNCC.setText("");
//                    txMaNV.setText(""); 

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Vui l??ng ch???n s???n ph???m");
			}
			DefaultTableModel modelCTHDNhap = (DefaultTableModel) tblCTHDNhap.getModel();
			modelCTHDNhap.setRowCount(0);
//			HienHanh.setNCC(null);
//		} else {
//			//JOptionPane.showMessageDialog(null, "Vui l??ng ch???n nh?? cung c???p");
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
