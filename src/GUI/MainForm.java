package GUI;

import BUS.DangNhapBUS;
import BUS.PhanQuyenBUS;
import CustomFuncs.CustomDialog;
import DTO.PhanQuyen;
import GUI.Color.ColorDesign;
import GUI.Dialog.DlgDoiMatKhau;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author ODL
 */
public class MainForm extends javax.swing.JFrame {

    int px, py;

    PnQuanLyBanHangGUI banHangPanel;
    PnQuanLyKhuyenMaiGUI khuyenMaiPanel;
    PnQuanLyNhapHangGUI nhapHangPanel;
    PnQuanLySanPhamGUI sanPhamPanel;
    PnQuanLyNhanVienGUI nhanVienPanel;
    PnQuanLyKhachHangGUI khachHangPanel;
    PnQuanLyThongKeGUI thongKePanel;
    JButton btnClose, btnMinimize, navTrangChu, navBanHang, navKhuyenMai, navNhapHang, navSanPham, navNhanVien, navKhachHang, navThongKe;
    /**
     * Creates new form MainForm
     */
    
    private JButton currentTab;
    ArrayList<JButton> listMenuLeft;

    public MainForm() {
        new CustomDialog("Đăng nhập thành công!", CustomDialog.SUCCESS_DIALOG);

        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Phần mềm quản lý cửa hàng cà phê");
     
        ImageIcon logo = new ImageIcon(getClass().getResource("/image/logo/icons8-coffee-shop-65.png"));
        setIconImage(logo.getImage());
        this.setVisible(true);
        createNavMenu();
        //adjust scroll speed 
        scrollMenu.getVerticalScrollBar().setUnitIncrement(10);
        draggable();
        this.pack();
        setMinimumSize(new Dimension(1800, 800));
        setExtendedState(getExtendedState()|MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

    }


    public void createNavMenu() {
        String[] navItemInfo = {
            //  "Trang chủ",
            "Bán hàng",
            "Khuyến mãi",
            "Sản phẩm",
            "Nhân viên",
            "Khách hàng",
            "Nhập hàng",
            "Thống kê"};
        String[] navItemIcon = {
            //  "image/menu/icons8-home-page-30.png",
            "image/menu/icons8_small_business_30px_3.png",
            "image/menu/icons8_gift_30px.png",
            "image/menu/icons8-cup-30.png",
            "image/menu/icons8-businessman-30.png",
            "image/menu/icons8_user_30px.png",
            "image/menu/icons8_downloads_30px.png",
            "image/menu/icons8_bar_chart_30px.png"};
        int navWidth = scrollMenu.getWidth();//250
        int navHeight = 50;
        int totalHeight = 0;
        int gapVertical = 0;
        navTrangChu = new JButton();
        navBanHang = new JButton();

        navKhuyenMai = new JButton();
        navNhapHang = new JButton();
        navSanPham = new JButton();
        navNhanVien = new JButton();
        navKhachHang = new JButton();
        navThongKe = new JButton();

        listMenuLeft = new ArrayList<>();
        //==========ADD PANEL BÁN HÀNG + KHUYẾN MÃI (Ko phân quyền)==========

        listMenuLeft.add(navBanHang);
        listMenuLeft.add(navKhuyenMai);
        //======XỬ LÝ PHÂN QUYỀN=======


        PhanQuyen quyen = PhanQuyenBUS.quyenTK;
       
        if (quyen.getQlSanPham() == 1) {
           listMenuLeft.add(navSanPham);
        }
        if (quyen.getQlNhanVien() == 1) {
        listMenuLeft.add(navNhanVien);
        }
        
        if (quyen.getQlKhachHang() == 1) {
        listMenuLeft.add(navKhachHang);
        }
        if (quyen.getNhapHang() == 1) {
            listMenuLeft.add(navNhapHang);
        }
        if (quyen.getThongKe() == 1) {
        listMenuLeft.add(navThongKe);
        }



        for (int i = 0; i < listMenuLeft.size(); i++) {
            JButton nav = listMenuLeft.get(i);
            nav.setText(navItemInfo[i]);
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + navItemIcon[i]));
            nav.setIcon(icon);
            nav.setIconTextGap(18);

  
            nav.setPreferredSize(new Dimension(navWidth, navHeight));
            nav.setHorizontalAlignment(SwingConstants.LEFT);
            nav.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

//            nav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            nav.setBackground(scrollMenu.getBackground());
            nav.setForeground(Color.BLACK);
            nav.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent me) {
                    if (me.getSource() instanceof JButton) {

                        JButton btn = (JButton) me.getSource();
                        if (currentTab != null) {
                            currentTab.setBackground(scrollMenu.getBackground());
                            currentTab.setEnabled(true);

                        }

                        currentTab = btn;
                        currentTab.setBackground(ColorDesign.LIGHT);
                        currentTab.setEnabled(false);

                        doAction(btn.getText());
                    }
                }

            });
            if(this.currentTab ==null)
            {
                this.currentTab= nav;
                nav.doClick();
            }
            menu.add(nav);
            // }
            //  }
            totalHeight += navHeight + gapVertical;

        }

        totalHeight += gapVertical;
        menu.setPreferredSize(new Dimension(menu.getWidth(), totalHeight));
        menu.revalidate();
        menu.repaint();
        //chạy click đầu tiên

    }

    public void draggable() {

        headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                px = me.getX();
                py = me.getY();
            }
        });

        headerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - px, getLocation().y + me.getY() - py);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button5 = new java.awt.Button();
        headerPanel = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        changePasswordBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        scrollMenu = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();

        button5.setLabel("button3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý cafe");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setSize(new java.awt.Dimension(1400, 800));

        headerPanel.setBackground(new java.awt.Color(245, 235, 224));
        headerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(244, 244, 244)));
        headerPanel.setPreferredSize(new java.awt.Dimension(1400, 55));

        label2.setBackground(headerPanel.getBackground());
        label2.setText("label2");
        label2.setVisible(false);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        changePasswordBtn.setBackground(headerPanel.getBackground());
        changePasswordBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/header/icons8-password-32.png"))); // NOI18N
        changePasswordBtn.setToolTipText("logout");
        changePasswordBtn.setBorder(null);
        changePasswordBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        changePasswordBtn.setPreferredSize(new java.awt.Dimension(75, 55));
        changePasswordBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changePasswordBtnMousePressed(evt);
            }
        });
        jPanel1.add(changePasswordBtn);

        logoutBtn.setBackground(headerPanel.getBackground());
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/header/icons8-logout-32.png"))); // NOI18N
        logoutBtn.setToolTipText("logout");
        logoutBtn.setBorder(null);
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        logoutBtn.setPreferredSize(new java.awt.Dimension(75, 55));
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutBtnMousePressed(evt);
            }
        });
        jPanel1.add(logoutBtn);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 913, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        scrollMenu.setBackground(new java.awt.Color(240, 219, 219));
        scrollMenu.setBorder(null);
        scrollMenu.setForeground(new java.awt.Color(38, 46, 74));
        scrollMenu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMenu.setToolTipText("");
        scrollMenu.setAutoscrolls(true);
        scrollMenu.setPreferredSize(new java.awt.Dimension(250, 745));

        menu.setBackground(scrollMenu.getBackground());
        menu.setMinimumSize(new java.awt.Dimension(0, 0));
        menu.setPreferredSize(new java.awt.Dimension(250, 745));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        menu.setLayout(flowLayout1);
        scrollMenu.setViewportView(menu);

        getContentPane().add(scrollMenu, java.awt.BorderLayout.LINE_START);

        contentPanel.setBackground(new java.awt.Color(254, 252, 243));
        contentPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMousePressed
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn đăng xuất ?", "Chú ý",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            this.dispose();
              DangNhapBUS dangNhapBUS = new DangNhapBUS();
              dangNhapBUS.dangXuatTaiKhoan();
            this.dispose();
            new DangNhapGUI().setVisible(true);
     
        }
    }//GEN-LAST:event_logoutBtnMousePressed

    private void changePasswordBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordBtnMousePressed
        // TODO add your handling code here:
        
        new DlgDoiMatKhau();
    }//GEN-LAST:event_changePasswordBtnMousePressed
    public void doAction(String nameAction) {
        contentPanel.removeAll();

        switch (nameAction) {
            case "Bán hàng":
                if (banHangPanel == null) {

                    banHangPanel = new PnQuanLyBanHangGUI();
                }
                contentPanel.add(banHangPanel, BorderLayout.CENTER);
                break;

            case "Nhập hàng":
                if (nhapHangPanel == null) {
                    nhapHangPanel = new PnQuanLyNhapHangGUI();
                }
                contentPanel.add(nhapHangPanel, BorderLayout.CENTER);
                break;
                


            case "Sản phẩm":
     

                if (sanPhamPanel == null) {
                              sanPhamPanel = new PnQuanLySanPhamGUI();

                }
                contentPanel.add(sanPhamPanel, BorderLayout.CENTER);
                break;


            case "Khuyến mãi":
                
      
                if (khuyenMaiPanel == null) {
                    khuyenMaiPanel = new PnQuanLyKhuyenMaiGUI();
                }
                contentPanel.add(khuyenMaiPanel, BorderLayout.CENTER);
                break;

            case "Phiếu nhập":
                if (nhapHangPanel == null) {
                    nhapHangPanel = new PnQuanLyNhapHangGUI();
                }
                contentPanel.add(nhapHangPanel, BorderLayout.CENTER);
                break;

            case "Nhân viên":
                if (nhanVienPanel == null) {
                    nhanVienPanel = new PnQuanLyNhanVienGUI();
                }
                contentPanel.add(nhanVienPanel, BorderLayout.CENTER);
     
                break;

            case "Khách hàng":
                
                khachHangPanel = new PnQuanLyKhachHangGUI();

                if (khachHangPanel == null) {
                    khachHangPanel = new PnQuanLyKhachHangGUI();
                }
                contentPanel.add(khachHangPanel, BorderLayout.CENTER);
                break;

            case "Thống kê":
                thongKePanel = new PnQuanLyThongKeGUI();
                if (thongKePanel == null) {
                    thongKePanel = new PnQuanLyThongKeGUI();
                }
                contentPanel.add(thongKePanel, BorderLayout.CENTER);
                break;

        
        }

        //   headerTitle.setLabel(nameAction.toUpperCase());
        // https://stackoverflow.com/questions/12989388/switching-panels-with-menubar
        contentPanel.revalidate();//refresh ui and layout
        contentPanel.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button5;
    private javax.swing.JButton changePasswordBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label2;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menu;
    private javax.swing.JScrollPane scrollMenu;
    // End of variables declaration//GEN-END:variables
}
