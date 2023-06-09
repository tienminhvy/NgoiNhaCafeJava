/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngoinhacafe.GUI.Page.Popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;

/**
 *
 * @author Admin
 */
public class ThemSuaKhachHangPopup extends javax.swing.JFrame implements ActionListener {
    
    /**
     * Creates new form PopupThemSuaKhachHang
     */
    public ThemSuaKhachHangPopup(String loai, String makh) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        WindowActions.WindowClosing(this);
        updateInit();
        
        if (loai.equalsIgnoreCase("thêm")) {
            setTitle("Thêm khách hàng");
            themSuaBtn.setText("Thêm");
        } else {
            setTitle("Sửa khách hàng");
            themSuaBtn.setText("Sửa");
            
            txtMaKH.setText(makh);
            txtMaKH.setEditable(false);
        }
        
    }
    
    public ThemSuaKhachHangPopup(javax.swing.JTextField txtMaKH) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        WindowActions.WindowClosing(this);
        updateInit();
        
        setTitle("Thêm khách hàng");
        themSuaBtn.setText("Thêm");
        
        txtMaKHOutside = txtMaKH;
    }
    
    javax.swing.JTextField txtMaKHOutside = null;
    
    private void updateInit() {
        // Add action listener
        themSuaBtn.addActionListener(this);
        thoatBtn.addActionListener(this);
        setVisible(true);
    }
    
    private void kiemTraDieuKien(String loai) {
        boolean check = true;
        
        if (txtMaKH.getText().length() == 0 && check) {
            check = false;
            
            ThongBao.hienLoi("Mã khách hàng không được để trống!");
            txtMaKH.requestFocus();
        }
        
        if (txtTenKH.getText().length() == 0 && check) {
            check = false;
            
            ThongBao.hienLoi("Tên khách hàng không được để trống!");
            txtTenKH.requestFocus();
        }
        
        if (!Regex.ktraTen(txtTenKH.getText()) && check) {
            check = false;
            
            ThongBao.hienLoi("Tên khách hàng không hợp lệ!");
            txtTenKH.requestFocus();
        }
        
        if (txtDiaChi.getText().length() == 0 && check) {
            check = false;
            
            ThongBao.hienLoi("Địa chỉ không được để trống!");
            txtDiaChi.requestFocus();
        }
        
        if (txtSDT.getText().length() == 0 && check) {
            check = false;
            
            ThongBao.hienLoi("Số điện thoại không được để trống!");
            txtSDT.requestFocus();
        }
        
        if (!Regex.ktraSoDienThoai(txtSDT.getText()) && check) {
            check = false;
            
            ThongBao.hienLoi("Số điện thoại không hợp lệ!");
            txtSDT.requestFocus();
        }
            
        if (check) {
            if (loai.equalsIgnoreCase("thêm")) {
                themData();
                
                if (txtMaKHOutside != null) {
                    txtMaKHOutside.setText(txtMaKH.getText());
                    this.dispose();
                }
                
            } else {
                capNhatData();
            }
        }
    }
    
    private void themData() {
        
    }
    
    private void capNhatData() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        trangThai = new javax.swing.JComboBox<>();
        themSuaBtn = new javax.swing.JButton();
        thoatBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTenKH.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên khách hàng"));

        txtDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txtSDT.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtMaKH.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã khách hàng"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái"));

        trangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiện", "Ẩn" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        themSuaBtn.setText("Thêm/Sửa");

        thoatBtn.setText("Thoát");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txtDiaChi))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txtTenKH)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(thoatBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(themSuaBtn)))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thoatBtn)
                    .addComponent(themSuaBtn))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton themSuaBtn;
    private javax.swing.JButton thoatBtn;
    private javax.swing.JComboBox<String> trangThai;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themSuaBtn) {
            kiemTraDieuKien(themSuaBtn.getText());
        }
        if (e.getSource() == thoatBtn) {
            this.dispose();
        }
    }
}
