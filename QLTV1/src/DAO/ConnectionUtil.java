package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class ConnectionUtil {
    String className ="com.mysql.cj.jdbc.Driver";
    String Host ="localhost";
    String Database ="bookstore";
    String Username ="root";
    String Password ="";
    String url ="jdbc:mysql://localhost:3306/bookstore";
    Connection connect = null;
    Statement st = null;
    ResultSet rs = null;

    public ConnectionUtil(String Host, String Username, String Password, String Database)
    {
        this.Host = Host;
        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }

    public ConnectionUtil() {
       
    }
    protected void driverTest() throws Exception{
            try {
                Class.forName(className);
            } catch (ClassNotFoundException ex) {
            }
    }
    public Connection getConnection() throws Exception{
        if(this.connect==null){
            driverTest();
        url ="jdbc:mysql://"+this.Host +":3306/" +this.Database;    
        try {
            this.connect=DriverManager.getConnection(url, this.Username, this.Password);
           // JOptionPane.showMessageDialog(null, "kết nối dữ liệu thành công");
           // System.out.println("kết nối dữ liệu thành công");
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(null, "kết nối thất bại");
        }
        }
         return this.connect;    
    }
    
    public Statement getStatement() throws Exception{
        try{
            if(this.st==null?true:this.st.isClosed()){
                this.st=this.getConnection().createStatement();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
    }
        return this.st;
    }
    
    public ResultSet excuteQuery(String qry) throws Exception{
        try{
            this.rs = this.getStatement().executeQuery(qry);
        } catch (Exception ex){
            throw new Exception("Eror: "+ex.getMessage()+"-"+qry);
    }
        return this.rs;
    }
    
    public int ExecuteUpdate(String qry) throws Exception{
        int res =0;
        try{
            res = this.getStatement().executeUpdate(qry);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
    
    public void close () throws SQLException{
        if(this.rs!=null && !this.rs.isClosed()){
        this.rs.close();
        this.rs=null;
    }
        if(this.st!=null && !this.st.isClosed()){
        this.st.close();
        this.st=null;
    }
        if(this.connect!=null && !this.connect.isClosed()){
        this.connect.close();
        this.connect=null;
    }
    }
    public static void main(String[] args) throws Exception {
        ConnectionUtil conn=new ConnectionUtil();
        conn.getConnection();
        
    }
}
