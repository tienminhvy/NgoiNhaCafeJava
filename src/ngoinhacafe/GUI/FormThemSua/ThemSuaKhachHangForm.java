package ngoinhacafe.GUI.FormThemSua;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemSuaKhachHangForm extends JFrame {

    String type;
    

    JTextField txMakh = new JTextField(15);
    JTextField txTenkh = new JTextField(15);
    JTextField txDiachi = new JTextField(15);
    JTextField txSDT = new JTextField(15);
    JComboBox<String> cbChonTrangThai;

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaKhachHangForm(String _type, String _makh) {
        this.setLayout(new BorderLayout());
        this.setSize(450, 300);
        this.setLocationRelativeTo(null);
        this.type = _type;

        // inputs
        txMakh.setBorder(BorderFactory.createTitledBorder("Mã khách hàng"));
        txTenkh.setBorder(BorderFactory.createTitledBorder("Tên khách hàng"));
        txDiachi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        txSDT.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
        cbChonTrangThai = new JComboBox<>(new String[]{"Ẩn", "Hiện"});

        // chon trang thai
        JPanel plChonTT = new JPanel();
        plChonTT.setBorder(BorderFactory.createTitledBorder("Trạng thái"));
        JLabel lbChonTT = new JLabel("Trạng thái: ");
        plChonTT.add(lbChonTT);
        plChonTT.add(cbChonTrangThai);

        JPanel plInput = new JPanel();
        plInput.add(txMakh);
        plInput.add(txTenkh);
        plInput.add(txDiachi);
        plInput.add(txSDT);
        plInput.add(plChonTT);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm khách hàng");
            

            cbChonTrangThai.setSelectedItem("Hiện");

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa khách hàng");

            txMakh.setEditable(false);
            txMakh.setText(_makh);

            btnSua.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        // mouse listener
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

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String makh = txMakh.getText();
            String tenkh = txTenkh.getText();
            String diachi = txDiachi.getText();
            String sdt = txSDT.getText();
            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 0 : 1);
            
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String makh = txMakh.getText();
            String tenkh = txTenkh.getText();
            String diachi = txDiachi.getText();
            String sdt = txSDT.getText();
            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 0 : 1);
            
        }
    }

    private Boolean checkEmpty() {
        String makh = txMakh.getText();
        String tenkh = txTenkh.getText();
        String diachi = txDiachi.getText();
        String sdt = txSDT.getText();

        if (makh.trim().equals("")) {
            return showErrorTx(txMakh, "Mã khách hàng không được để trống");

        } else if (tenkh.trim().equals("")) {
            return showErrorTx(txTenkh, "Tên khách hàng không được để trống");

        } else if (diachi.trim().equals("")) {
            return showErrorTx(txTenkh, "Địa chỉ không được để trống");

        } else if (sdt.trim().equals("")) {
            return showErrorTx(txTenkh, "Số điện thoại không được để trống");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
}
