package ngoinhacafe.GUI.FormQuanLy;

import ngoinhacafe.GUI.FormHienThi.HienThiTaiKhoan;
import ngoinhacafe.GUI.FormThemSua.ThemSuaTaiKhoanForm;
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

public class QuanLyTaiKhoanForm extends JPanel {

    HienThiTaiKhoan formHienThi = new HienThiTaiKhoan();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyTaiKhoanForm() {
        setLayout(new BorderLayout());

        // buttons
        

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);

        //=========== add all to this jpanel ===========
        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

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
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            ThemSuaTaiKhoanForm suatk = new ThemSuaTaiKhoanForm("Sửa", masp);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suatk.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn tài khoản nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String malsp = formHienThi.getSelectedRow(1);
        
    }

    private void btnThemMouseClicked() {
        ThemSuaTaiKhoanForm themtk = new ThemSuaTaiKhoanForm("Thêm", "");

        themtk.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
