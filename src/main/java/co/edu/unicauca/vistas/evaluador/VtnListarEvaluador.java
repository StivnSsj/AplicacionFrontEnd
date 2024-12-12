package co.edu.unicauca.vistas.evaluador;

import co.edu.unicauca.modelos.Usuario;
import co.edu.unicauca.services.ArticuloServices;
import co.edu.unicauca.services.KeycloakServices;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Mary
 */
/**
 * Clase VtnListarEvaluador.
 *
 * Esta clase representa una ventana interna (JInternalFrame) que permite
 * listar, eliminar y actualizar evaluadores en la aplicación.
 */
public class VtnListarEvaluador extends javax.swing.JInternalFrame {

    // Atributos para los servicios utilizados en la clase
    public ArticuloServices objSArticulo; // Servicio para gestionar artículos.
   
    private KeycloakServices keyServices;
    //public EvaluadorServicio objSEvaluador; // Servicio para gestionar evaluadores.
    //private OrganizadorServicio organizadorServicio;

    /**
     * Constructor de la clase VtnListarEvaluador.
     *
     * Inicializa la ventana y asigna los servicios de artículos y evaluadores.
     * También configura el renderizador de la tabla y llama al método para
     * inicializar la tabla.
     *
     * @param objSArticulo Instancia del servicio de artículos.
     * @param objSEvaluador Instancia del servicio de evaluadores.
     */
    public VtnListarEvaluador(
            ArticuloServices objSArticulo
            //EvaluadorServicio objSEvaluador,
            //OrganizadorServicio organizadorServicio
    ) {
        // Inicializa los componentes de la ventana.
        initComponents();
        this.objSArticulo = objSArticulo;  // Asigna el servicio de artículos.
        //this.objSEvaluador = objSEvaluador; // Asigna el servicio de evaluadores.
        this.jTableListarEvaluadores.setDefaultRenderer(Object.class, new RenderE()); // Configura el renderizador de la tabla.
        // Llama al método para inicializar la tabla.
        inicializarTabla();
        //this.objSEvaluador = new EvaluadorServicio();
        llenarTabla();
        //this.organizadorServicio = organizadorServicio;
    }

    /**
     * Inicializa la tabla para listar evaluadores.
     *
     * Configura el modelo de la tabla, añadiendo las columnas necesarias: "Id",
     * "Nombre", "Apellido", "Eliminar" y "Actualizar".
     */
    private void inicializarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("CORREO");
        model.addColumn("ELIMINAR");
        model.addColumn("ACTUALIZAR");
        this.jTableListarEvaluadores.setModel(model);
    }

    /**
     * Limpia la tabla de evaluadores.
     *
     * Elimina todas las filas de la tabla actual.
     */
    public void limpiarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableListarEvaluadores.getModel();
        int filas = this.jTableListarEvaluadores.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    /**
     * Llena la tabla con los datos de los evaluadores.
     *
     * Limpia la tabla y luego obtiene la lista de evaluadores del servicio,
     * añadiendo los datos a la tabla junto con botones de "Eliminar" y
     * "Actualizar".
     */
    private void llenarTabla() {
        DefaultTableModel model = (DefaultTableModel) this.jTableListarEvaluadores.getModel();
        limpiarTabla();

        try {
            // Inicializa el servicio si es necesario
            if (this.keyServices == null) {
                this.keyServices = new KeycloakServices();
            }

            List<Usuario> listaEvaluadores = this.keyServices.buscarUsuariosXRol("EVALUADOR");

            // Verifica si la lista está vacía
            if (listaEvaluadores == null || listaEvaluadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ningún evaluador.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Sal del método si no hay evaluadores
            }

            // Crea botones para eliminar y actualizar evaluadores
            JButton JButtonEliminarEvaluador = new JButton();
            JButtonEliminarEvaluador.setName("Eliminar");
            JButtonEliminarEvaluador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remove.png")));

            JButton JButtonActualizarEvaluador = new JButton();
            JButtonActualizarEvaluador.setName("Actualizar");
            JButtonActualizarEvaluador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aplicar.png")));

            // Recorre la lista de evaluadores y añade sus datos a la tabla.
            for (Usuario evaluador : listaEvaluadores) {
                Object[] fila = {
                    evaluador.getId(), // ID del evaluador
                    evaluador.getNombre(), // Nombre del evaluador
                    evaluador.getCorreo(), // Correo del evaluador
                    JButtonEliminarEvaluador, // Botón de eliminar
                    JButtonActualizarEvaluador // Botón de actualizar
                };
                model.addRow(fila);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Se produjo un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabelGestionarE = new javax.swing.JLabel();
        jPanelInferior = new javax.swing.JPanel();
        jPanelCentral = new javax.swing.JPanel();
        jButtonActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListarEvaluadores = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconG.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(789, 416));
        setPreferredSize(new java.awt.Dimension(789, 416));

        jPanelSuperior.setPreferredSize(new java.awt.Dimension(544, 30));

        jLabelGestionarE.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelGestionarE.setText("Gestionar Evaluadores");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(404, Short.MAX_VALUE)
                .addComponent(jLabelGestionarE)
                .addContainerGap(405, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelGestionarE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelInferior.setPreferredSize(new java.awt.Dimension(544, 30));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.PAGE_END);

        jButtonActualizar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/comunicacion.png"))); // NOI18N
        jButtonActualizar.setText("Refresh");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jTableListarEvaluadores.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTableListarEvaluadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableListarEvaluadores.setSurrendersFocusOnKeystroke(true);
        jTableListarEvaluadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarEvaluadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListarEvaluadores);

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCentralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonActualizar)
                .addGap(57, 57, 57))
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonActualizar)
                .addContainerGap())
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Maneja el evento de acción para el botón de actualizar.
     *
     * Llama al método llenarTabla() para refrescar los datos mostrados.
     *
     * @param evt Evento de acción que ocurre.
     */
    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    /**
     * Maneja el evento de clic en la tabla de evaluadores.
     *
     * Detecta si se ha hecho clic en un botón de "Eliminar" o "Actualizar" y
     * realiza la acción correspondiente.
     *
     * @param evt Evento de mouse que ocurre.
     */

    private void jTableListarEvaluadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarEvaluadoresMouseClicked
//        int column = this.jTableListarEvaluadores.getColumnModel().getColumnIndexAtX(evt.getX());
//        int row = evt.getY() / jTableListarEvaluadores.getRowHeight();
//
//        if (row < jTableListarEvaluadores.getRowCount() && row >= 0 && column < jTableListarEvaluadores.getColumnCount() && column >= 0) {
//            Object value = jTableListarEvaluadores.getValueAt(row, column);
//
//            if (value instanceof JButton) {
//
//                ((JButton) value).doClick();
//                JButton boton = (JButton) value;
//
//                String idEvaluador = jTableListarEvaluadores.getValueAt(row, 0).toString();
//                int idEvaluadorConvertido = Integer.parseInt(idEvaluador);
//                if (boton.getName().equals("Eliminar")) {
//                    try {
//                        if (Utilidades.mensajeConfirmacion("¿Estás seguro de que quieres eliminar el evaluador con identificador " + idEvaluador + "?", "Confirmación") == 0) {
//                            boolean bandera = this.objSEvaluador.eliminarEvaluador(idEvaluadorConvertido);
//                            if (bandera) {
//                                Utilidades.mensajeExito("El evaluador con identificador " + idEvaluadorConvertido + " fue eliminado exitosamente", "Evaluador eliminado");
//                                llenarTabla();
//                            } else {
//                                System.out.println("Error: La eliminación falló en el servidor o el evaluador no existe.");
//                                Utilidades.mensajeAdvertencia("El evaluador con identificador " + idEvaluadorConvertido + " no fue eliminado", "Error al eliminar");
//                            }
//                        }
//                    } catch (Exception ex) {
//                        System.out.println("Excepción al intentar eliminar: " + ex.getMessage());
//                        Utilidades.mensajeError("Error al eliminar usuario. Inténtelo de nuevo más tarde", "Error");
//                    }
//                } else if (boton.getName().equals("Actualizar")) {
//                    VtnActualizarEvaluador objVtnActualizarEvaluador
//                            = new VtnActualizarEvaluador(objSArticulo, objSEvaluador);
//                    objVtnActualizarEvaluador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                    objVtnActualizarEvaluador.cargarDatos(idEvaluadorConvertido);
//                    objVtnActualizarEvaluador.setVisible(true);
//
//                }
//            }
//        }
    }//GEN-LAST:event_jTableListarEvaluadoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JLabel jLabelGestionarE;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarEvaluadores;
    // End of variables declaration//GEN-END:variables
}
