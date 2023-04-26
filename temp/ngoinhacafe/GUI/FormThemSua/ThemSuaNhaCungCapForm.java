package ngoinhacafe.GUI.FormThemSua;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemSuaNhaCungCapForm extends JFrame {

    
    String type;
    
    JTextField txMaNCC = new JTextField(10);
    JTextField txTenNCC = new JTextField(10);
    JTextField txDiaChi = new JTextField(10);
    JTextField txSDT = new JTextField(10);
    JTextField txFax = new JTextField(10);
    JTextField txTim = new JTextField(15);

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaNhaCungCapForm(String _type, String _mancc) {

        this.setLayout(new BorderLayout());
        this.setSize(450, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = _type;

        txMaNCC.setBorder(BorderFactory.createTitledBorder("Mã nhà cung cấp"));
        txTenNCC.setBorder(BorderFactory.createTitledBorder("Tên nhà cung cấp"));
        txDiaChi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        txSDT.setBorder(BorderFactory.createTitledBorder("SDT"));
        txFax.setBorder(BorderFactory.createTitledBorder("Fax"));

        JPanel plInput = new JPanel();
        plInput.add(txMaNCC);
        plInput.add(txTenNCC);
        plInput.add(txDiaChi);
        plInput.add(txSDT);
        plInput.add(txFax);

        JPanel plButton = new JPanel();
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm nhà cung cấp");
            

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa nhà cung cấp");
            

            txMaNCC.setEditable(false);

            btnSua.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }
        
        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        this.setVisible(true);
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String maNCC = txMaNCC.getText();
            String tenNCC = txTenNCC.getText();
            String diaChi = txDiaChi.getText();
            String SDT = txSDT.getText();
            String Fax = txFax.getText();
            
        }
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            
        }
    }

    private Boolean checkEmpty() {
        String ma = txMaNCC.getText();
        String ten = txTenNCC.getText();
        String diachi = txDiaChi.getText();
        String sdt = txSDT.getText();
        String fax = txFax.getText();
        if (ma.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không được để trống");
            txMaNCC.requestFocus();
            return false;
        } else if (ten.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được để trống");
            txTenNCC.requestFocus();
            return false;
        } else if (diachi.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ nhà cung cấp không được để trống");
            txDiaChi.requestFocus();
            return false;
        } else if (sdt.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại nhà cung cấp không được để trống");
            txSDT.requestFocus();
            return false;
        } else if (fax.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Số fax nhà cung cấp không được để trống");
            txFax.requestFocus();
            return false;
        }
        return true;
    }
}
