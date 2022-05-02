/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ThongKeGUI extends JPanel {

	public JComboBox cbbtc;
	public JTable tbl;
	DefaultTableModel model = new DefaultTableModel();
	public JScrollPane scroll = new JScrollPane();
	Vector tieude;
	JDateChooser time1, time2;
	JButton bttk;

	public JLabel lbtc, lbtungay, lbdenngay;

	public ThongKeGUI() {
		initcomponents();

		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		time1.setDateFormatString("yyyy-MM-dd");
		time2.setDateFormatString("yyyy-MM-dd");
	}

	public void initcomponents() {
		setLayout(null);
		setBounds(0, 0, 1200, 800);
		setBackground(Color.decode("#78909C"));
		setFont(new Font("Arial", 0, 15));
		setForeground(Color.white);

		JPanel p1 = Header();
		p1.setBounds(0, 0, 1200, 100);
		p1.setBackground(Color.decode("#78909C"));

		JPanel p2 = Foot();
		p2.setBounds(100, 120, 1100, 680);
		p2.setBackground(Color.decode("#78909C"));

		add(p1);
		add(p2);
		setVisible(true);
	}

	public JPanel Header() {
		JPanel phead = new JPanel();
		phead.setLayout(null);
		lbtc = new JLabel("Tiêu chí");
		lbtc.setBounds(50, 20, 100, 30);
		lbtc.setForeground(Color.WHITE);
		lbtc.setFont(new Font("Arial", Font.BOLD, 20));

		String[] tc = { "Nhân viên", "Sản phẩm tồn kho", "Sản phẩm bán", "Sản phẩm nhập" };
		cbbtc = new JComboBox(tc);
		cbbtc.setSelectedIndex(0);
		cbbtc.setBounds(160, 20, 200, 30);

		lbtungay = new JLabel("Từ ngày");
		lbtungay.setBounds(370, 20, 100, 30);
		lbtungay.setForeground(Color.WHITE);
		lbtungay.setFont(new Font("Arial", Font.BOLD, 20));

		time1 = new JDateChooser();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		time1.setBounds(480, 20, 200, 30);

		lbdenngay = new JLabel("Đến ngày");
		lbdenngay.setBounds(700, 20, 100, 30);
		lbdenngay.setForeground(Color.WHITE);
		lbdenngay.setFont(new Font("Arial", Font.BOLD, 20));

		time2 = new JDateChooser();
		DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
		time2.setBounds(820, 20, 200, 30);
		bttk = new JButton("OK");
		bttk.setBounds(1050, 20, 100, 30);
		bttk.setBackground(Color.decode("#FFCA28"));
		bttk.setFont(new Font("Arial", Font.BOLD, 20));
		bttk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OK();
			}

		});

		phead.add(cbbtc);
		phead.add(lbtc);
		phead.add(lbtungay);
		phead.add(time1);
		phead.add(lbdenngay);
		phead.add(time2);
		phead.add(bttk);
		return phead;
	}

	public JPanel Foot() {
		JPanel pfoot = new JPanel();
		pfoot.setLayout(null);
		pfoot.setBackground(Color.decode("#78909C"));
		tieude = new Vector();
		tieude.add("Mã");
		tieude.add("Tên");
		tieude.add("Số lượng");
		tieude.add("Gía trị");
		if (model.getRowCount() == 0) {
			model = new DefaultTableModel(tieude, 0);
		}
		tbl = new JTable(null, tieude);
		tbl.setBounds(0, 0, 1050, 650);

		tbl.setFont(new Font("Arial", 0, 15));
		tbl.setModel(model);// add model len table
		tbl.getTableHeader().setFont(new Font("Arial", BOLD, 15)); // set font cho vector header
		tbl.getTableHeader().setForeground(Color.black); // set màu chữ cho header
		tbl.getTableHeader().setPreferredSize(new Dimension(30, 50));// set độ dài độ rộng của header
		tbl.getTableHeader().setBackground(Color.decode("#4FC3F7"));// set background cho header

		scroll = new JScrollPane(tbl);
		scroll.setBounds(0, 0, 1050, 650);
		scroll.setPreferredSize(new Dimension(500, 500));
		tbl.setPreferredSize(new Dimension(500, 500));
		pfoot.add(scroll); // add table vào scrollPanel
		tbl.setFillsViewportHeight(true);
		return pfoot;

	}

	private void Add_row(ThongKeDTO tk) {
		Vector row = new Vector();
		row.add(tk.getMa());
		row.add(tk.getTen());
		row.add(tk.getSoluong());
		row.add(tk.getGiatri());
		model.addRow(row);
		tbl.setModel(model);
	}

	private void OK() {
		System.out.println("oki");
		DefaultTableModel model = (DefaultTableModel) tbl.getModel();
		ThongKeBUS thongKeBUS = new ThongKeBUS();
		model.setRowCount(0);
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = date.format(time1.getDate());
		String toDate = date.format(time2.getDate());
		ThongKeBUS.dsTK = null;
		String tieuChi = cbbtc.getSelectedItem().toString();
		System.out.println("tiêu chi" + tieuChi);
		switch (tieuChi) {
		case "Nhân viên": {
			System.out.println("Nhân viên");
			thongKeBUS.ThongKeNV(fromDate, toDate);
			for (ThongKeDTO tkDTO : ThongKeBUS.dsTK) {
				Add_row(tkDTO);
			}
			break;
		}
//		case "Khách hàng": {
//			System.out.println("Khách hàng");
//			thongKeBUS.ThongKeKH(fromDate, toDate);
//			for (ThongKeDTO tkDTO : ThongKeBUS.dsTK) {
//				Add_row(tkDTO);
//			}
//			break;
//		}

		case "Sản phẩm tồn kho": {
			System.out.println("Khách hàng");
			thongKeBUS.ThongKeSK();
			for (ThongKeDTO tkDTO : ThongKeBUS.dsTK) {
				Add_row(tkDTO);
			}
			break;
		}
		case "Sản phẩm bán": {
			System.out.println("Khách hàng");
			thongKeBUS.ThongKeSB(fromDate, toDate);
			for (ThongKeDTO tkDTO : ThongKeBUS.dsTK) {
				Add_row(tkDTO);
			}
			break;
		}
		case "Sản phẩm nhập": {
			System.out.println("Khách hàng");
			thongKeBUS.ThongKeSN(fromDate, toDate);
			for (ThongKeDTO tkDTO : ThongKeBUS.dsTK) {
				Add_row(tkDTO);
			}
			break;
		}
		default:
			break;
		}
	}
}
