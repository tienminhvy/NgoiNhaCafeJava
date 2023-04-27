package Main;

import DAO.MyConnect;
import GUI.Color.ColorDesign;
import GUI.DangNhapGUI;
import GUI.MainForm;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {


    public static void main(String[] args) {
        new MyConnect();

        changLNF("djfdksf");
        DangNhapGUI login = new DangNhapGUI();      
       // new MainForm().setVisible(true);

    }

    public static void changLNF(String name) {
        try {

            UIManager.setLookAndFeel(new FlatLightLaf());
            //title bar 
            UIManager.put("TitlePane.unifiedBackground", false);
            UIManager.put("TitlePane.background", ColorDesign.FIRST_LIGHT);

            UIManager.put("Button.background", Color.WHITE);
            UIManager.put("Panel.background", ColorDesign.FIRST_LIGHT);
            Color panelColor = UIManager.getColor("Panel.background");
            UIManager.put("TextField.background", panelColor);
            UIManager.put("PasswordField.background", panelColor);

            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("ComboBox.arrowButton.background", panelColor);
        } //catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        catch (UnsupportedLookAndFeelException ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |UnsupportedLookAndFeelException e) {
                // handle exception
            }
        }
    }
}
