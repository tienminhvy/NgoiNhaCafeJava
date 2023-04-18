/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ngoinhacafe.GUI.Page.Interface;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public interface Table {
    abstract DefaultTableModel getTableModel();
    abstract void addRowToTable(Object[] data);
    abstract void removeAllRow();
}
