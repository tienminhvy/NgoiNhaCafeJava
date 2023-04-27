package GUI;

import BUS.PhanQuyenBUS;
import DTO.PhanQuyen;
import GUI.Color.ColorDesign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author ODL
 */
public class MainForm extends javax.swing.JFrame {

    int px, py;
//    BanHangForm banhang;
//    NhapHangForm nhaphang;
//    SanPhamForm qlsp;
//    LoaiSanPhamForm qllsp;
//    TaiKhoanForm qltk;
//    NhanVienForm qlnv;
//    KhachHangForm qlkh;
//    QuyenForm qlq;
//    HoaDonForm qlhd;
//    PhieuNhapForm qlpn;
//    KhuyenMaiForm qlkm;
//    NhaCungCapForm qlncc;
//    ThongKeForm thongke;
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
    JButton currentTab;
    ArrayList<JButton> listMenuLeft;

    public MainForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Phần mềm quản lý cửa hàng cà phê");
        System.out.println("GUI.MainForm.<init>()");
        ImageIcon logo = new ImageIcon(getClass().getResource("/image/logo/icons8-coffee-shop-65.png"));
        setIconImage(logo.getImage());
        this.setVisible(true);
        createNavMenu();
        //adjust scroll speed 
        scrollMenu.getVerticalScrollBar().setUnitIncrement(10);
        draggable();
    }

    class MenuNav {

        private String title;
        private String icon;
        private boolean isSeparate = false;
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
        listMenuLeft.add(navBanHang);
        listMenuLeft.add(navKhuyenMai);
        listMenuLeft.add(navSanPham);
        listMenuLeft.add(navNhanVien);
        listMenuLeft.add(navKhachHang);
        listMenuLeft.add(navNhapHang);
        listMenuLeft.add(navThongKe);

//        for (JButton lbl : listMenuLeft) {
//            lbl.setVisible(false);
//            lbl.setPreferredSize(new Dimension(250, 65));
//            lbl.setOpaque(true);
//            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//          //  pnMenuLeft.add(lbl);
//        }
//        lblBanHang.setBackground(clLeftItemSelected);
//        lblBanHang.setVisible(true);
//        lblKhuyenMai.setVisible(true);
        // pnMain.add(pnMenuLeft, BorderLayout.WEST);
        for (int i = 0; i < listMenuLeft.size(); i++) {
            JButton nav = listMenuLeft.get(i);
            nav.setText(navItemInfo[i]);
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + navItemIcon[i]));
            nav.setIcon(icon);
            nav.setIconTextGap(18);
//        for (JButton nav : listMenuLeft) {

            nav.setPreferredSize(new Dimension(navWidth, navHeight));
            nav.setHorizontalAlignment(SwingConstants.LEFT);
            nav.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
//            if (!navItemInfo[i + 1].isEmpty()) {
//                ImageIcon icon = new ImageIcon(getClass().getResource("/assets/images/menu/" + navItemInfo[i + 1]));
//                nav.setIconTextGap(18);
//                nav.setIcon(icon);
//
//            }
            //   nav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        minimalizeBtn = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        scrollMenu = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();

        button5.setLabel("button3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý cafe");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setUndecorated(true);
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

        minimalizeBtn.setBackground(headerPanel.getBackground());
        minimalizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/header/icons8-chevron-down-32.png"))); // NOI18N
        minimalizeBtn.setToolTipText("minimal");
        minimalizeBtn.setBorder(null);
        minimalizeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        minimalizeBtn.setMaximumSize(new java.awt.Dimension(1000, 1000));
        minimalizeBtn.setPreferredSize(new java.awt.Dimension(75, 55));
        minimalizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                minimalizeBtnMousePressed(evt);
            }
        });
        jPanel1.add(minimalizeBtn);

        btnExit.setBackground(headerPanel.getBackground());
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/header/icons8-shutdown-32.png"))); // NOI18N
        btnExit.setToolTipText("exit");
        btnExit.setBorder(null);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExit.setMaximumSize(new java.awt.Dimension(1000, 1000));
        btnExit.setPreferredSize(new java.awt.Dimension(75, 55));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 763, Short.MAX_VALUE)
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

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void minimalizeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimalizeBtnMousePressed
        //hide program
        setState(ICONIFIED);

    }//GEN-LAST:event_minimalizeBtnMousePressed

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn thoát chương trình?", "Chú ý",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMousePressed

    private void logoutBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMousePressed
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn đăng xuất ?", "Chú ý",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            // new LoginForm().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutBtnMousePressed

    private void changePasswordBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordBtnMousePressed
        // TODO add your handling code here:
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
    private javax.swing.JButton btnExit;
    private java.awt.Button button5;
    private javax.swing.JButton changePasswordBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label2;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menu;
    private javax.swing.JButton minimalizeBtn;
    private javax.swing.JScrollPane scrollMenu;
    // End of variables declaration//GEN-END:variables
}
