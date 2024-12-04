/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cellAction;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jayjay
 */
public class TableActionCellRendererCardView extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelActionCardView action = new PanelActionCardView();
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(new java.awt.Color(204, 204, 204, 80));
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }
}
