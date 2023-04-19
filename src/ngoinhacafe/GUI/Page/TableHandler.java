/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngoinhacafe.GUI.Page;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TableHandler {
    public DefaultTableModel getTableModel(javax.swing.JTable tb) {
        return (DefaultTableModel) tb.getModel();
    }

    public void addRowToTable(Object[] data, javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        
        model.addRow(data);
    }
    
    public void removeRow(int i, javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        
        model.removeRow(i);
    }
    
    public boolean updateTableRowWith(String valToCheck, int col, String valToUpdate, int colToUpdate, javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        String stToCheck;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            stToCheck = (String) model.getValueAt(i, 0);
            if (stToCheck.equalsIgnoreCase(valToCheck)) {
                model.setValueAt(valToUpdate, i, colToUpdate);
                return true;
            }
        }
        return false;
    }
    
    public int getRowIndexAtTableWith(String valToCheck, int col, int colToGet, javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        String stToCheck;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            stToCheck = (String) model.getValueAt(i, 0);
            if (stToCheck.equalsIgnoreCase(valToCheck)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getValueAtTableWith(String valToCheck, int col, int colToGet, javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        String stToCheck;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            stToCheck = (String) model.getValueAt(i, 0);
            if (stToCheck.equalsIgnoreCase(valToCheck)) {
                return (String) model.getValueAt(i, colToGet);
            }
        }
        return "";
    }

    public void removeAllRow(javax.swing.JTable tb) {
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        
        model.setRowCount(0);
    }
}
