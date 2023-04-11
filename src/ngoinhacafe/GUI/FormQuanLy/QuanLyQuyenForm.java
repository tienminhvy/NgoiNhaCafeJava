package ngoinhacafe.GUI.FormQuanLy;

import ngoinhacafe.GUI.FormHienThi.HienThiQuyen;
import ngoinhacafe.GUI.FormThemSua.ThemSuaQuyenForm;
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

public class QuanLyQuyenForm extends JPanel {

    HienThiQuyen formHienThi = new HienThiQuyen();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyQuyenForm() {
        setLayout(new BorderLayout());

        // buttons
        

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);

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
            formHienThi.refresh();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            
        });
    }

    private void btnSuaMouseClicked() {
        String maquyen = formHienThi.getSelectedRow(1);
        if (maquyen != null) {
            ThemSuaQuyenForm suaq = new ThemSuaQuyenForm("Sửa", maquyen);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suaq.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn quyền nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String maquyen = formHienThi.getSelectedRow(1);
        
    }

    private void btnThemMouseClicked() {
        ThemSuaQuyenForm themq = new ThemSuaQuyenForm("Thêm", "");
        themq.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
