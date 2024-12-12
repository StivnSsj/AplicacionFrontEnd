package co.edu.unicauca.utilidades;
import javax.swing.JOptionPane;
import co.edu.unicauca.utilidades.CargarImagenes;
/**
 * Clase Utilidades.
 *
 * Esta clase proporciona métodos estáticos para mostrar diferentes tipos de mensajes emergentes (JOptionPane).
 * Incluye métodos para mostrar mensajes de advertencia, error, éxito y confirmación.
 * 
 * Los métodos de esta clase utilizan la clase CargarImagenes para personalizar los iconos de los mensajes.
 */
public class Utilidades {

    /**
     * Constructor por defecto de la clase Utilidades.
     * Este constructor no realiza ninguna acción.
     */
    public Utilidades() {
        // Constructor vacío
    }
    /**
     * Genera un emergente de aventencia
     *
     * @param mns mensaje dentro de la ventana
     * @param titulo título de la ventana
     */
    public static void mensajeAdvertencia(String mns, String titulo) {
        CargarImagenes objCargarImagenes= new CargarImagenes();
        JOptionPane.showMessageDialog(null, mns, titulo, JOptionPane.DEFAULT_OPTION, objCargarImagenes.CargarImagen("/recursos/logo.png"));
    }

    /**
     * Genera un emergente de error
     *
     * @param mns mensaje dentro de la ventana
     * @param titulo título de la ventana
     */
    public static void mensajeError(String mns, String titulo) {
        CargarImagenes objCargarImagenes= new CargarImagenes();
        JOptionPane.showMessageDialog(null, mns, titulo, JOptionPane.DEFAULT_OPTION, objCargarImagenes.CargarImagen("/recursos/warning.png"));
    }

    /**
     * Genera un emergente de exito
     *
     * @param mns mensaje dentro de la ventana
     * @param titulo título de la ventana
     */
    public static void mensajeExito(String mns, String titulo) {
        CargarImagenes objCargarImagenes= new CargarImagenes();
        JOptionPane.showMessageDialog(null, mns, titulo, JOptionPane.DEFAULT_OPTION, objCargarImagenes.CargarImagen("/recursos/exitoso.png"));
    }

    /**
     * Genera un emergente de confirmación con los botones Si ó No
     *
     * @param mns mensaje dentro de la ventana
     * @param titulo título de la ventana
     * @return Si ó No
     */
    public static int mensajeConfirmacion(String mns, String titulo) {        
        return JOptionPane.showConfirmDialog(null, mns, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}
