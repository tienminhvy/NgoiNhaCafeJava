/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngoinhacafe.GUI.Page.Popup;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class WindowActions {
    public static void WindowClosing(JFrame f) {
        
        f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                f.dispose();
            }
        });
    }
}
