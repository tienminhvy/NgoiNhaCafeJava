package Main;

import DAO.MyConnect;
import GUI.DangNhapGUI;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {

    public static void main(String[] args) {
        new MyConnect();

        changLNF("FlatLaf Light");
        new DangNhapGUI();
    }

    public static void changLNF(String nameLNF) {
        FlatLightLaf.setup();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }
}
