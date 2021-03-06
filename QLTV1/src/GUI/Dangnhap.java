/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongCu;
import BUS.HienHanh;
import DAO.Connect;
import DAO.ConnectionUtil;
import DTO.TaiKhoanDTO;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Dangnhap extends javax.swing.JFrame {

	/**
	 * Creates new form Dangnhap
	 */
	public Dangnhap() {
		initComponents();
		setLocationRelativeTo(null);
		setResizable(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		lbtentk = new javax.swing.JLabel();
		txttentk = new javax.swing.JTextField();
		lbmatkhau = new javax.swing.JLabel();
		btndangnhap = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		txtmatkhau = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jLabel1.setBackground(new java.awt.Color(153, 255, 153));
		jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("ĐĂNG NHẬP");
		jLabel1.setOpaque(true);

		lbtentk.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
		lbtentk.setText("Tên tài khoản:");

		txttentk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txttentkActionPerformed(evt);
			}
		});
		txttentk.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txttentkKeyPressed(evt);
			}
		});

		lbmatkhau.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
		lbmatkhau.setText("Mật khẩu:");

		btndangnhap.setBackground(new java.awt.Color(153, 255, 153));
		btndangnhap.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		btndangnhap.setText("Đăng Nhập");
		btndangnhap.setBorderPainted(false);
		btndangnhap.setOpaque(false);
		btndangnhap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btndangnhapActionPerformed(evt);
			}
		});

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-admin-settings-male-100.png"))); // NOI18N

		txtmatkhau.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtmatkhauActionPerformed(evt);
			}
		});
		txtmatkhau.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtmatkhauKeyPressed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 108,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(43, 43, 43)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btndangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 139,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txttentk, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtmatkhau))
						.addGap(62, 62, 62))
				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(31, 31, 31)
												.addComponent(lbtentk).addContainerGap(324, Short.MAX_VALUE))
										.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jSeparator1)
												.addContainerGap())))));
		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(44, 44, 44)
										.addComponent(txttentk, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
										.addComponent(
												btndangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(220, 220, 220)
												.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(29, 29, 29)
												.addComponent(lbtentk, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(159, 159, 159))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txttentkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txttentkActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txttentkActionPerformed

	private void btndangnhapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btndangnhapActionPerformed

		try {
			Login();
		} catch (SQLException ex) {
			Logger.getLogger(Dangnhap.class.getName()).log(Level.SEVERE, null, ex);
		}

	}// GEN-LAST:event_btndangnhapActionPerformed

	private void txttentkKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txttentkKeyPressed
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			txtmatkhau.requestFocus();
		}
	}// GEN-LAST:event_txttentkKeyPressed

	private void txtmatkhauKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtmatkhauKeyPressed
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			btndangnhap.requestFocus();
			try {
				Login();
			} catch (SQLException ex) {
				Logger.getLogger(Dangnhap.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}// GEN-LAST:event_txtmatkhauKeyPressed

	private void txtmatkhauActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtmatkhauActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtmatkhauActionPerformed

	private boolean check() {

		if (!CongCu.checkMa(txttentk.getText())) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản không bao gồm các kí tự đặc biệt");
			return false;
		} else if (!CongCu.checkChar(txtmatkhau.getText())) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không bao gồm các kí tự đặc biệt");
			return false;
		}
		return true;
	}

	private void Login() throws SQLException {

		if (txttentk.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên đăng nhập trống.Vui lòng nhập tên đăng nhập");
		} else if (txtmatkhau.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Pass trống.Vui lòng nhập pass");
		} else if (check()) {
			try {
				Connect conn = new Connect();
				if (conn.getConnect() == null) {
					System.out.println("kết nói thất bại");
				} else {
					System.out.println("thành công");
				}
				String sql = "SELECT MANV,PASS,QUYEN FROM taikhoan WHERE MANV='" + txttentk.getText() + "'" + " AND "
						+ "PASS='" + txtmatkhau.getText() + "'";
				// String sql="SELECT ID,PASS FROM taikhoan WHERE ID=? AND PASS=?";
				System.out.println("sql=" + sql);
				// PreparedStatement ps= (PreparedStatement) ;

				Statement stmt = (Statement) conn.getStatement();
				ResultSet rs = stmt.executeQuery(sql);

				// System.out.println(rs.getString(2));
//            if(rs.getString(2)==txtmatkhau.getText())
				if (rs.next()) {
					if (rs.getString(3).toString().equals("NV")) {
						GUImenu giaodien = new GUImenu();
						// KhachHangmenu giaodien=new Adminmenu();
						giaodien.setVisible(true);
						giaodien.setResizable(false);
						giaodien.setLocationRelativeTo(null);
						this.setVisible(false);
					} else if (rs.getString(3).toString().equals("admin")) {
						Adminmenu banhang = new Adminmenu();
						banhang.setVisible(true);
						banhang.setResizable(false);
						banhang.setLocationRelativeTo(null);
						this.setVisible(false);
					} else if (rs.getString(3).toString().equals("QL")) {
						quanlimenu quanli = new quanlimenu();
						quanli.setVisible(true);
						quanli.setResizable(false);
						quanli.setLocationRelativeTo(null);
						this.setVisible(false);
					}

					HienHanh.setNhanVien(txttentk.getText());
				} // }
				else {
					JOptionPane.showMessageDialog(this, "Đăng nhập thất bại");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				// System.out.println(ex);
			}

		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		Dangnhap dn = new Dangnhap();

		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Dangnhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Dangnhap().setVisible(true);

			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btndangnhap;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JLabel lbmatkhau;
	private javax.swing.JLabel lbtentk;
	private javax.swing.JPasswordField txtmatkhau;
	private javax.swing.JTextField txttentk;
	// End of variables declaration//GEN-END:variables
}
