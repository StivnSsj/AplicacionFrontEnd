package co.edu.unicauca.vistas.articulo;

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
 */
public class Render extends DefaultTableCellRenderer {
    /**
     * Constructor de la clase Render.
     */
    public Render() {
    }
    /**
     * Personaliza la renderización de celdas en la tabla.
     *
     * @param table la JTable que está siendo renderizada
     * @param value el valor de la celda que está siendo renderizada
     * @param isSelected indica si la celda está seleccionada
     * @param hasFocus indica si la celda tiene el foco
     * @param row el índice de la fila de la celda que está siendo renderizada
     * @param column el índice de la columna de la celda que está siendo renderizada
     * @return el componente que debe ser renderizado en la celda
     */
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
