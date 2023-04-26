package Main;

import DAO.MyConnect;
import GUI.DangNhapGUI;

public class Main {

    public static void main(String[] args) {
        new MyConnect();

        changLNF("FlatLaf Light");
        DangNhapGUI login = new DangNhapGUI();
    }

    public static void changLNF(String nameLNF) {
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
