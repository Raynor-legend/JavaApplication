
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
		header.add("Mã nước uống");
		header.add("Tên nước uống");
		header.add("Số lượng");
		header.add("Đơn giá ");
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
		JLabel lbmahd = new JLabel("Mã hóa đơn");
		lbmahd.setBounds(200, 30, 200, 30);
		JTextField txMaPhieuNhap = new JTextField();
		txMaPhieuNhap.setBounds(300, 30, 200, 30);

		JLabel lbngaylap = new JLabel("Ngày lập");
		lbngaylap.setBounds(200, 70, 200, 30);
		JTextField txNgayLap = new JTextField();
		txNgayLap.setBounds(300, 70, 200, 30);

		JLabel lbmanv = new JLabel("Mã nhân viên");
		lbmanv.setBounds(200, 110, 200, 30);
		JTextField txMaNV = new JTextField();
		txMaNV.setBounds(300, 110, 200, 30);

		JLabel lbmakh = new JLabel("Mã khách hàng");
		lbmakh.setBounds(600, 30, 200, 30);
		JTextField txMaNCC = new JTextField();
		txMaNCC.setBounds(700, 30, 200, 30);

		JButton bttimkiem = new JButton("...");
		bttimkiem.setBounds(900, 30, 40, 30);

		JLabel lbdiachi = new JLabel("Địa chỉ");
		lbdiachi.setBounds(600, 70, 200, 30);
		JTextField txdiachi = new JTextField();
		txdiachi.setBounds(700, 70, 200, 30);

		JLabel lbsdt = new JLabel("SĐT");
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
		JLabel lbTimKiem = new JLabel("Tìm kiếm");
		lbTimKiem.setBounds(10, 40, 80, 30);

		JTextField txTimKiem = new JTextField();
		txTimKiem.setBounds(100, 40, 200, 30);

		String[] arr = { "Mã nước uống", "Tên nước uống" };
		JComboBox cbbtk = new JComboBox(arr);
		cbbtk.setBounds(300, 40, 100, 30);

		JButton bttimkiem = new JButton("Tìm kiếm");
		bttimkiem.setBounds(400, 40, 100, 30);

		JButton btchon = new JButton("Chọn hàng");
		btchon.setBounds(480, 350, 100, 30);

		header = new Vector();
		header.add("STT");
		header.add("Mã nước uống");
		header.add("Tên nước uống");
		header.add("Số lượng");
		header.add("Đơn giá nhập");

		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(header, 0);
		}
		tblSP = new JTable(null, header);
		tblSP.setBounds(0, 100, 580, 250);

		tblSP.setFont(new Font("Arial", 0, 15));
		tblSP.setModel(model);// add model len table
		tblSP.getTableHeader().setFont(new Font("Arial", BOLD, 18)); // set font cho vector header
		tblSP.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblSP.getTableHeader().setPreferredSize(new Dimension(50, 50));// set độ dài độ rộng của header
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
				if (src.equals("Chọn hàng")) {
					btchonHangMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});

		psanpham.add(scroll); // add table vào scrollPanel
		tblSP.setFillsViewportHeight(true);// hiển thị table
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
		JLabel lbChiTiet = new JLabel("Chi tiết phiếu nhập");
		lbChiTiet.setBounds(250, 40, 150, 50);
		lbChiTiet.setForeground(Color.ORANGE);
		lbChiTiet.setFont(new Font("Arial", BOLD, 20));
		JButton btXoa = new JButton("Xóa");
		btXoa.setBounds(370, 350, 100, 30);

		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Xóa")) {
					btXoaMouseClicked();
				}
				throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																				// choose Tools | Templates.
			}
		});

		JButton btThanhtoan = new JButton("Thanh toán");
		btThanhtoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Thanh toán")) {
					btThanhToanMouseClicked();
				}
				// throw new UnsupportedOperationException("Not supported yet."); //To change
				// body of generated methods, choose Tools | Templates.
			}
		});

		btThanhtoan.setBounds(480, 350, 100, 30);
		header = new Vector();
		header.add("STT");
		header.add("Mã nước uống");
		header.add("Tên nước uống");
		header.add("Đơn giá");
		header.add("Số lượng");
		header.add("Tổng cộng");

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
		tblCTHDNhap.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tblCTHDNhap.getTableHeader().setPreferredSize(new Dimension(50, 50));// set độ dài độ rộng của header
		tblCTHDNhap.getTableHeader().setBackground(Color.pink);// set background cho header

		scroll = new JScrollPane(tblCTHDNhap);
		scroll.setBounds(0, 100, 580, 250);
		// tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0,
		// true));
		tblCTHDNhap.setPreferredSize(new Dimension(500, 500));
		scroll.setPreferredSize(new Dimension(500, 500));

		pgiohang.add(scroll); // add table vào scrollPanel
		tblSP.setFillsViewportHeight(true);// hiển thị table
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
			int sLSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 2).toString()); // Hư ở đây

			do {
				checkInput = false;
				String soLuongThemStr = JOptionPane.showInputDialog("Chọn số lượng muốn nhập ");

				if (soLuongThemStr != null) {

					try {
						soLuongThem = Integer.parseInt(soLuongThemStr);
					} catch (NumberFormatException e) { // Kiá»ƒm tra nháº­p sá»‘
						JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên");
						checkInput = true;
					}
					if ((soLuongThem <= sLSP) && (soLuongThem != 0)) {
						// int SLSPConLai = giaTriSLSP - soLuongThem;
						tblSP.setValueAt(sLSP - soLuongThem, tblSP.getSelectedRow(), 2);// Set số lượng còn lại

						// Thêm vào chi tiết
						String maSP = tblSP.getValueAt(tblSP.getSelectedRow(), 0).toString();
						String tenSP = tblSP.getValueAt(tblSP.getSelectedRow(), 1).toString();

						int donGiaSP = Integer.parseInt(tblSP.getValueAt(tblSP.getSelectedRow(), 3).toString());
						// int donGiaSP = CongCu.FormatTienStringSangInt(donGiaSPStr);

						int thanhTien = soLuongThem * donGiaSP;
						// String thanhTienStr =String.valueOf(thanhTien) ;

						// Kiá»ƒm tra sáº£n pháº©m Ä‘Ã£ tá»“n táº¡i trong giá»� hÃ ng hay chÆ°a, náº¿u
						// cÃ³ thÃ¬ tÄƒng sá»‘ lÆ°á»£ng
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
									tblCTHDNhap.setValueAt(soLuongDaCo + soLuongThem, i, 3); // Thay Ä‘á»•i sá»‘
																								// lÆ°á»£ng
									tblCTHDNhap.setValueAt(((soLuongDaCo + soLuongThem) * donGiaSP), i, 5);// Thay
																											// Ä‘á»•i
																											// thÃ nh
																											// tiá»�n
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
						JOptionPane.showMessageDialog(null, "Trong kho hàng chỉ còn" + sLSP);
						checkInput = true;
					}

				} else {
					checkInput = false; // Náº¿u báº¥m ThoÃ¡t thÃ¬ thoÃ¡t vÃ²ng láº·p
				}
			} while (checkInput); // VÃ²ng láº·p check nháº­p vÃ o

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn thêm");
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
		// Gáº¯n vÃ o textfield
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
			JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm muốn xoá");
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
			txMaNCC = new JTextField();
			txMaPhieuNhap = new JTextField();
			txMaNV = new JTextField();
			int ma = pnBUS.getMa();
			pnDTO.setMaPhieuNhap(ma);

			pnDTO.setMaNCC(HienHanh.getNCC());

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
//                txMaNV.setText(""); // Ngay dòng này bị lỗi nè
////                Chạy được rồi đó mà giao diện  của em bị lỗi gì kia 

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lÃ²ng thÃªm sáº£n pháº©m vÃ o giá»� hÃ ng!");
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
