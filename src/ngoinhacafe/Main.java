/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ngoinhacafe;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import static java.awt.SystemColor.window;
import javax.swing.SwingUtilities;

import ngoinhacafe.GUI.Page.LoginForm;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import ngoinhacafe.GUI.FormChung.ChangePassForm;
import ngoinhacafe.GUI.FormChung.FormChinh;
import ngoinhacafe.GUI.Page.MainForm;
import ngoinhacafe.GUI.Page.Home;
import ngoinhacafe.GUI.Page.HomePage;

public class Main {

    public static void main(String[] args) {
        // set beatiful style compile
        try {
             //  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());               
               UIManager.setLookAndFeel(new FlatLightLaf());


        } catch (UnsupportedLookAndFeelException e) {

        }

    //    new LoginForm().setVisible(true);
        new MainForm().setVisible(true);
       // new ChangePassForm().setVisible(true);
//          new FormChinh().setVisible(true);     
       //new Home().setVisible(true);

    }

 }
