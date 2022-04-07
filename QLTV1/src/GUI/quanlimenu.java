/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;

public class quanlimenu extends JFrame {

    private String arr_listmenu[] = {"Quản lí nhân viên", "Quản lí khách hàng", "Hóa đơn", "Nhập sách", "Hóa đơn nhập sách", "Thống kê"};
    private JLabel lbl_listmenu[] = new JLabel[arr_listmenu.length];
    private JPanel pn_listmenu[] = new JPanel[arr_listmenu.length];
    private JPanel pn_content[] = new JPanel[arr_listmenu.length];
    private JPanel pn_nhanvien = new JPanel();
    private JPanel pn_khachhnag = new JPanel();
    private JPanel pn_hoadon = new JPanel();
    private JPanel pn_nhapsach = new JPanel();
    private JPanel pn_thongke = new JPanel();

//    private JPanel pn_hoadon= new JPanel();
    // private JPanel pn_dangxuat = new JPanel();//system.exit(0);
//    private JPanel pn_=new JPanel();
//    private JPanel pn_NguyenLieu=new JPanel();
//    private JPanel pn_TaiKhoan=new JPanel();
    //https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = (int) (screenSize.getWidth() * 76 / 100);
    public static int height = (int) (screenSize.getHeight() * 70 / 100);
    public static int width_menu = width * 15 / 100;
    public static int width_content = width * 85 / 100;

    public quanlimenu() {
        initcomponent();
    }

    public void initcomponent() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.decode("#546E7A"));
        setLayout(new BorderLayout());
        //làm mất thanh chức năng mặc định của Frame
        //Chú ý : phải set lệnh này trước visible nếu không thì lệnh không chạy và gây lỗi
        setUndecorated(true);
        setVisible(true);
        JPanel menu = menu();
        menu.setPreferredSize(new Dimension(width_menu, 0));
        add(menu, BorderLayout.WEST);
        JButton btdangxuat = new JButton("Đăng xuất");
        btdangxuat.setBounds(0, 677, 220, 50);
        btdangxuat.setFont(new Font("Arial", Font.BOLD, 15));
        btdangxuat.setHorizontalAlignment(CENTER);
        btdangxuat.setBackground(Color.decode("#FFCA28"));
        menu.add(btdangxuat);
        btdangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = e.getActionCommand();
                if (src.equals("Đăng xuất")) {
                    btDangXuatMouseClicked();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        JPanel header = header();
        header.setPreferredSize(new Dimension(0, 30));
        add(header, BorderLayout.NORTH);

        JPanel content = content();
        content.setPreferredSize(new Dimension(width_content, 0));
        add(content, BorderLayout.CENTER);

        setSize(width, height);
        setResizable(false);
    }

    private JPanel menu() {
        JPanel menu = new JPanel(null);
        JLabel logo = new JLabel(new ImageIcon("./src/img/user.png"), JLabel.CENTER);
        logo.setBounds(20, 10, 170, 170);
        menu.add(logo);
        menu.setBackground(Color.decode("#546E7A"));
        int y = 200;
        for (int i = 0; i < lbl_listmenu.length; i++) {
            lbl_listmenu[i] = new JLabel(arr_listmenu[i]);
            lbl_listmenu[i].setBounds(20, 0, width_menu, 50);
            lbl_listmenu[i].setFont(new Font("Arial", Font.BOLD, 18));
            lbl_listmenu[i].setHorizontalAlignment(LEFT);

            //Đổi màu chữ thành trắng
            lbl_listmenu[i].setForeground(Color.WHITE);
            pn_listmenu[i] = new JPanel(null);
            pn_listmenu[i].setBackground(Color.decode("#546E7A"));

            pn_listmenu[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    for (int i = 0; i < pn_listmenu.length; i++) {
                        if (evt.getSource() == pn_listmenu[i]) {

                            pn_content[i].setVisible(true);
                            continue;
                        }

                        pn_content[i].setVisible(false);
                    }
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    for (int i = 0; i < pn_listmenu.length; i++) {
                        if (evt.getSource() == pn_listmenu[i]) {
                            pn_listmenu[i].setBackground(Color.decode("#FFCA28"));
                            continue;
                        }
                        pn_listmenu[i].setBackground(Color.decode("#546E7A"));
                    }
                }
//                @Override
//                public void mouseExited(java.awt.event.MouseEvent evt) 
//                {
//                    for(int i=0;i<pn_listmenu.length;i++)
//                    {
//                         if(evt.getSource()==pn_listmenu[i])
//                        {
//                            pn_listmenu[i].setBackground(Color.decode("#0D47A1"));                          
//                            return;
//                        }
//                                                
//                    }
//                }
            });
            pn_listmenu[i].add(lbl_listmenu[i]);
            pn_listmenu[i].setBounds(0, y, width_menu, 50);
            menu.add(pn_listmenu[i]);
            y += 50;

        }

        return menu;
    }

    private JPanel content() {
        JPanel trunggian = new JPanel(null);

        //Tạo thanh menu tự động , gồm các button tạo các bảng
        for (int i = 0; i < arr_listmenu.length; i++) {

            //Với mỗi i chỉ tạo 1 panel
            switch (i) {

                case 0:
                    NhanVienGUI nhanvien = new NhanVienGUI();
                    pn_content[i] = nhanvien;
                    break;
                case 1:
                    KhachHangGUI khachhang = new KhachHangGUI();
                    pn_content[i] = khachhang;
                    break;
                case 2:
                    HoaDonGUI hoadon = new HoaDonGUI();
                    pn_content[i] = hoadon;
                    break;
                case 3:
                    PhieuNhapGUI pnhap = new PhieuNhapGUI();
                    pn_content[i] = pnhap;
                    break;
                case 4:
                    HoaDonNhapGUI nhap = new HoaDonNhapGUI();
                    pn_content[i] = nhap;
                    break;
                case 5:
                    ThongKeGUI thongke = new ThongKeGUI();
                    pn_content[i] = thongke;
                    break;
                
            }
            if (i == 0) {
                pn_content[i].setVisible(true);
            } else {
                pn_content[i].setVisible(false);
            }
            pn_content[i].setBounds(0, 0, width_content, 770);
            trunggian.add(pn_content[i]);
        }
        return trunggian;

    }

    private JPanel header() {
        JPanel header = new JPanel(null);
        header.setBackground(Color.decode("#455A64"));

        JLabel title = new JLabel("QUẢN LÝ CỬA HÀNG SÁCH");
        title.setBounds(width * 50 / 100, 0, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        header.add(title);

////        JLabel logout=new JLabel();
////        logout.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_exit_30px.png")));
//        logout.setBounds(width*0, 0, 30, 30);
//        logout.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mousePressed(MouseEvent evt){
//                //viết code logout
//            }
//        });
//        header.add(logout);
        JLabel minimize = new JLabel(new ImageIcon("./src/img/minimize.png"), JLabel.LEFT);
        minimize.setBounds(width * 94 / 100, 0, 35, 35);
        minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                setState(JFrame.ICONIFIED);
            }
        });
        header.add(minimize);

        JLabel exit = new JLabel(new ImageIcon("./src/img/close.png"), JLabel.LEFT);
//        exit.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_shutdown_30px_1.png")));
        exit.setBounds(width * 97 / 100, 0, 35, 35);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dispose();
            }
        });
        header.add(exit);
        return header;
    }

    private void btDangXuatMouseClicked() {
        this.setVisible(false);
        Dangnhap a = new Dangnhap();
        a.setVisible(true);
    }
//     public static void main(String args[]){
//        GUImenu giaodien=new GUImenu();
//        giaodien.setLocationRelativeTo(null);
//        
//    }
//    

}
