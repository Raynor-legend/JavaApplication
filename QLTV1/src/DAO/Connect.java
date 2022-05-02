package DAO;

import GUI.Dangnhap;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	private String hostName = "localhost";
	private String dbName = "bookstore";
	private String userName = "root";
	private String password = "";
	private Connection conn;
	Statement st = null;
	ResultSet rs = null;

	private String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
			+ "?useUnicode=yes&characterEncoding=UTF-8";

	public Connect() {
		// Class.forName("com.mysql.jdbc.Driver").newInstance();
		 
	}

	// sử dụng lớp
	public Connection getConnect() {
		conn = null;

		// String url = "jdbc:mysql://localhost:3306/cddb";
		// String user = "root";
		// String password = "";
		try {
			// Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException er) {
			System.out.println(er.toString());
		}
		return (Connection) conn;

	}

	public void loaddata() throws SQLException {
		getConnect();
		Dangnhap b = new Dangnhap();
		// b.txttentk.getText();
		Statement stmt = (Statement) conn.createStatement();
		// get data from table 'student'
		ResultSet rs = stmt.executeQuery("SELECT Ten_tai_khoan,Mat_khau FROM taikhoan WHERE Ten_tai_khoan='admin'"); // tra
																														// ve
																														// dang
																														// dang
																														// bang

		// show data
		while (rs.next()) {
			System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  ");

		}

		conn.close();
	}

	public Statement getStatement() throws Exception {
		try {
			if (this.st == null ? true : this.st.isClosed()) {
				this.st = this.getConnect().createStatement();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.st;
	}

	public ResultSet excuteQuery(String qry) throws Exception {
		try {
			this.rs = this.getStatement().executeQuery(qry);
		} catch (Exception ex) {
			throw new Exception("Eror: " + ex.getMessage() + "-" + qry);
		}
		return this.rs;
	}

	public int ExecuteUpdate(String qry) throws Exception {
		int res = 0;
		try {
			res = this.getStatement().executeUpdate(qry);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	public void close() throws SQLException {
		if (this.rs != null && !this.rs.isClosed()) {
			this.rs.close();
			this.rs = null;
		}
		if (this.st != null && !this.st.isClosed()) {
			this.st.close();
			this.st = null;
		}
		if (this.conn != null && !this.conn.isClosed()) {
			this.conn.close();
			this.conn = null;
		}
	}

	public static void main(String[] args) throws SQLException {
		Connect a = new Connect();
		if (a.getConnect() == null)
			System.out.println("kết nối thất bại");
		else
			System.out.println("thahf công");
		a.loaddata();

	}

}