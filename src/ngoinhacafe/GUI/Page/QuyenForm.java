/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ngoinhacafe.GUI.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import ngoinhacafe.GUI.FormThemSua.ThemSuaTaiKhoanForm;
import ngoinhacafe.GUI.Page.Popup.ThemSuaKhachHangPopup;
import ngoinhacafe.GUI.Page.Popup.ThemSuaQuyenPopup;
import ngoinhacafe.GUI.Page.Popup.ThongBao;

/**
 *
 * @author ODL
 */
public class QuyenForm extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form KhachHangForm
     */
    public QuyenForm() {
        initComponents();
        addActionPerform();
        quyenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tbHandler.addRowToTable(new Object[]{
            1,

            "A",
            1,
            "nhập hàng"
        }, quyenTable);
        tbHandler.addRowToTable(new Object[]{
            2, 
            "Nguyễn Văn A",
            2,
             "bán hàng"
        }, quyenTable);

    }

    private void addActionPerform() {
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        themBtn = new javax.swing.JButton();
        xoaBtn = new javax.swing.JButton();
        suaBtn = new javax.swing.JButton();
        quyenTableContainer = new javax.swing.JScrollPane();
        quyenTable = new javax.swing.JTable();
        timkiemContainer = new javax.swing.JPanel();
        timKiemTheo = new javax.swing.JComboBox<>();
        timKiem = new javax.swing.JTextField();
        timKiemBtn = new javax.swing.JButton();
        lamMoiBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 252, 243));
        setFocusTraversalPolicyProvider(true);

        themBtn.setText("Thêm");

        xoaBtn.setText("Xoá");

        suaBtn.setText("Sửa");

        quyenTableContainer.setToolTipText("");

        quyenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài khoản", "tài khoản", "Mã quyền", "Tên quyền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        quyenTableContainer.setViewportView(quyenTable);

        timkiemContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        timKiemTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã tài khoản", "Tên tài khoản", "Mã quyền", "Tên quyền" }));

        timKiemBtn.setText("Tìm kiếm");

        javax.swing.GroupLayout timkiemContainerLayout = new javax.swing.GroupLayout(timkiemContainer);
        timkiemContainer.setLayout(timkiemContainerLayout);
        timkiemContainerLayout.setHorizontalGroup(
            timkiemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timkiemContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timKiemTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timKiemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        timkiemContainerLayout.setVerticalGroup(
            timkiemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timkiemContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timkiemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timKiemTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiemBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lamMoiBtn.setText("Làm mới");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quyenTableContainer)
            .addGroup(layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(timkiemContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(357, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(themBtn)
                .addGap(18, 18, 18)
                .addComponent(xoaBtn)
                .addGap(18, 18, 18)
                .addComponent(suaBtn)
                .addGap(18, 18, 18)
                .addComponent(lamMoiBtn)
                .addGap(399, 399, 399))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBtn)
                    .addComponent(xoaBtn)
                    .addComponent(suaBtn)
                    .addComponent(lamMoiBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timkiemContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quyenTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton lamMoiBtn;
    private javax.swing.JTable quyenTable;
    private javax.swing.JScrollPane quyenTableContainer;
    private javax.swing.JButton suaBtn;
    private javax.swing.JButton themBtn;
    private javax.swing.JTextField timKiem;
    private javax.swing.JButton timKiemBtn;
    private javax.swing.JComboBox<String> timKiemTheo;
    private javax.swing.JPanel timkiemContainer;
    private javax.swing.JButton xoaBtn;
    // End of variables declaration//GEN-END:variables

    TableHandler tbHandler = new TableHandler();

    ThemSuaQuyenPopup fThemSua = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fThemSua != null) {
            fThemSua.dispose();
        }

        if (e.getSource() == themBtn) {
            fThemSua = new ThemSuaQuyenPopup("Thêm", "");
        } else if (e.getSource() == lamMoiBtn) {

        } else if (e.getSource() == timKiemBtn) {
            String timKiemTheoTxt = (String) timKiemTheo.getSelectedItem();
            if (timKiemTheoTxt.equals("Mã tài khoản")) {

            }
            if (timKiemTheoTxt.equals("Tên tài khoản")) {

            }

            if (timKiemTheoTxt.equals("Mã quyền")) {

            }
            if (timKiemTheoTxt.equals("Tên quyền")) {

            }
  
        } else if (quyenTable.getSelectedRow() == -1) {
            ThongBao.hienLoi("Vui lòng chọn khách hàng để thực hiện thao tác!");
            return;
        } else {
            DefaultTableModel model = tbHandler.getTableModel(quyenTable);
            int selectedRow = quyenTable.getSelectedRow();

            int maTK = (int) model.getValueAt(selectedRow, 0);

            if (e.getSource() == suaBtn) {
                fThemSua = new ThemSuaQuyenPopup("Sửa", Integer.toString(maTK) );
            }
            if (e.getSource() == xoaBtn) {
                int cf = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá khách hàng này không?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);

                if (cf == JOptionPane.OK_OPTION) {
                    // Xác nhận xoá
                }
            }
        }
    }
}
