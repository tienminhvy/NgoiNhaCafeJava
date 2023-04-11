package ngoinhacafe.GUI.FormQuanLy;

import ngoinhacafe.GUI.FormHienThi.HienThiKhachHang;
import ngoinhacafe.GUI.FormThemSua.ThemSuaKhachHangForm;
import ngoinhacafe.GUI.FormChung.LoginForm;
import ngoinhacafe.GUI.MyButton.ExportExcelButton;
import ngoinhacafe.GUI.MyButton.ImportExcelButton;
import ngoinhacafe.GUI.MyButton.SuaButton;
import ngoinhacafe.GUI.MyButton.ThemButton;
import ngoinhacafe.GUI.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuanLyKhachHangForm extends JPanel {

    HienThiKhachHang formHienThi = new HienThiKhachHang();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyKhachHangForm() {
        setLayout(new BorderLayout());

        // buttons

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);

        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);

        // actionlistener
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            
        });
    }

    private void btnSuaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            ThemSuaKhachHangForm suakh = new ThemSuaKhachHangForm("Sửa", makh);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suakh.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            
            

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaKhachHangForm themkh = new ThemSuaKhachHangForm("Thêm", "");
        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
