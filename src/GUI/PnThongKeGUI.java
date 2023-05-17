/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DTO.ThongKe;
import BUS.ThongKeBUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class PnThongKeGUI extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form PnThongKeGUI
     */
    public PnThongKeGUI() {
        initComponents();
        
        init();
        hienThiThongKe();
    }
    
    private void init() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= year - 10; i--)
            cmbNam.addItem(i+"");
        
        cmbNam.addActionListener(this);
    }
    
    ThongKeBUS thongKeBUS = new ThongKeBUS();
    private DecimalFormat dcf = new DecimalFormat("###,###");
    
    private void hienThiThongKe() {
        ThongKe thongKe = thongKeBUS.thongKe(Integer.parseInt(cmbNam.getSelectedItem() + ""));
        tk_sp.setText("Số sản phẩm trên hệ thống: "+dcf.format(thongKe.getSoLuongSP()));
        tk_kh.setText("Số lượng khách hàng: "+dcf.format(thongKe.getSoLuongKH()));
        tk_nv.setText("Số lượng nhân viên: "+dcf.format(thongKe.getSoLuongNV()));
        tk_doanhthu.setText("Tổng doanh thu theo năm: "+dcf.format(thongKe.getTongDoanhThu()));
        lb_quy1.setText(dcf.format(thongKe.getTongThuQuy(1)));
        lb_quy2.setText(dcf.format(thongKe.getTongThuQuy(2)));
        lb_quy3.setText(dcf.format(thongKe.getTongThuQuy(3)));
        lb_quy4.setText(dcf.format(thongKe.getTongThuQuy(4)));
        lb_tongcong.setText(dcf.format(thongKe.getTongDoanhThu()));
        
        if (thongKe.getTopSanPhamBanChay() != null)
            for(int i=0;i<thongKe.getTopSanPhamBanChay().size();i++) {
                tb_dsbanchay.setValueAt(thongKe.getTopSanPhamBanChay().get(i).getTenSP(), i, 0);
                tb_dsbanchay.setValueAt(thongKe.getTopSanPhamBanChay().get(i).getSoLuong(), i, 1);
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tk_sp = new javax.swing.JLabel();
        tk_kh = new javax.swing.JLabel();
        tk_nv = new javax.swing.JLabel();
        tk_doanhthu = new javax.swing.JLabel();
        cmbNam = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_tongcong = new javax.swing.JLabel();
        lb_quy2 = new javax.swing.JLabel();
        lb_quy3 = new javax.swing.JLabel();
        lb_quy4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lb_quy1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_dsbanchay = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ TỔNG QUAN");
        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(129, 50));
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        tk_sp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tk_sp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tk_sp.setText("Số sản phẩm trên hệ thống: ");

        tk_kh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tk_kh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tk_kh.setText("Số lượng khách hàng: ");

        tk_nv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tk_nv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tk_nv.setText("Số lượng nhân viên: ");

        tk_doanhthu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tk_doanhthu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tk_doanhthu.setText("Tổng doanh thu theo năm: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Quý");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Quý 1");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Quý 2");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Quý 3");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Quý 4");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Doanh thu");

        lb_tongcong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_tongcong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tongcong.setText("0");

        lb_quy2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_quy2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_quy2.setText("0");

        lb_quy3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_quy3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_quy3.setText("0");

        lb_quy4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_quy4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_quy4.setText("0");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Tổng cộng");

        lb_quy1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_quy1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_quy1.setText("0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Top 5 sản phẩm được mua nhiều nhất (toàn thời gian)");

        tb_dsbanchay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_dsbanchay);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(86, 86, 86))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(31, 31, 31)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_quy1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_quy2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_quy3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_quy4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lb_tongcong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 163, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(tk_sp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tk_kh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tk_nv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tk_doanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tk_sp)
                .addGap(18, 18, 18)
                .addComponent(tk_kh)
                .addGap(18, 18, 18)
                .addComponent(tk_nv)
                .addGap(18, 18, 18)
                .addComponent(tk_doanhthu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lb_quy1)
                    .addComponent(lb_quy3)
                    .addComponent(lb_quy4)
                    .addComponent(lb_quy2))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lb_tongcong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_quy1;
    private javax.swing.JLabel lb_quy2;
    private javax.swing.JLabel lb_quy3;
    private javax.swing.JLabel lb_quy4;
    private javax.swing.JLabel lb_tongcong;
    private javax.swing.JTable tb_dsbanchay;
    private javax.swing.JLabel tk_doanhthu;
    private javax.swing.JLabel tk_kh;
    private javax.swing.JLabel tk_nv;
    private javax.swing.JLabel tk_sp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbNam) {
            hienThiThongKe();
        }
    }
}
