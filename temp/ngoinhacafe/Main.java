/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ngoinhacafe;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ngoinhacafe.GUI.ColorDesign;
import ngoinhacafe.GUI.FormChung.FormChinh;
import ngoinhacafe.GUI.Page.MainForm;

public class Main {

    public static void main(String[] args) {
        // set beatiful style compile
        try {
            //  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());               
            UIManager.setLookAndFeel(new FlatLightLaf());

            //  UIManager.put("Button.hoverBackground",Color.BLACK);
            UIManager.put("Button.background", Color.WHITE);
           // UIManager.put("JButton.cursor", Cursor.HAND_CURSOR);
           UIManager.put("Panel.background", ColorDesign.FIRST_LIGHT);
          Color panelColor=UIManager.getColor( "Panel.background" );
        //   UIManager.put("TextField.background",panelColor);    
           UIManager.put("TextField.background",panelColor);  

           UIManager.put("ComboBox.background",Color.WHITE);
            UIManager.put("ComboBox.arrowButton.background",panelColor);


        } catch (UnsupportedLookAndFeelException e) {

        }

        //    new LoginForm().setVisible(true);
        new MainForm().setVisible(true);
        // new ChangePassForm().setVisible(true);
        new FormChinh().setVisible(true);     
        //new Home().setVisible(true);

    }

}
