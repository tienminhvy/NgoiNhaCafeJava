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

        changLNF("");
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
            UIManager.put("OptionPane.background",ColorDesign.FIRST_LIGHT);
            UIManager.put("Menu.selectionBackground", Color.BLUE);
            UIManager.put("Menu.selectionForeground", Color.WHITE);
            UIManager.put("Menu.background", ColorDesign.FIRST_LIGHT);
            UIManager.put("Menu.foreground", Color.BLACK);
            UIManager.put("TabbedPane.unselectedForeground", ColorDesign.FOURTH_LIGHT);
            UIManager.put("TabbedPane.selectedBackground", ColorDesign.FIRST_LIGHT);
            Color panelColor = UIManager.getColor("Panel.background");
            UIManager.put("TextField.background", panelColor);
            UIManager.put("PasswordField.background", panelColor);
            UIManager.put("FileChooser.background", ColorDesign.FIRST_LIGHT);
            UIManager.put("FileChooser.textForeground", ColorDesign.FIRST_LIGHT);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("ComboBox.arrowButton.background", panelColor);
            
 
            UIManager.put("Menu.opaque", false);
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
