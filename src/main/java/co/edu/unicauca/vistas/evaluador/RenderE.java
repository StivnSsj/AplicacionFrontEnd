
package co.edu.unicauca.vistas.evaluador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase Render.
 * 
 * Esta clase extiende DefaultTableCellRenderer y se utiliza para personalizar la renderización de celdas en una JTable.
 * En particular, esta clase permite que los botones sean renderizados correctamente en las celdas de la tabla.
 * 
 * @author Mary
 */
public class RenderE extends DefaultTableCellRenderer {
    /**
     * Constructor de la clase Render.
     */
    public RenderE() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof JButton){
            JButton btn = (JButton)value;
            if(isSelected){
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            }else{
                btn.setForeground(Color.WHITE);
                btn.setBackground(Color.WHITE);
            }            
            return btn;
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
}
