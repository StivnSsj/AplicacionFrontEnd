/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.vistas.articulo;

import co.edu.unicauca.modelos.Articulo;
import co.edu.unicauca.modelos.Conferencia;
import co.edu.unicauca.modelos.User;
import co.edu.unicauca.modelos.Usuario;
import co.edu.unicauca.services.ArticuloServices;
import co.edu.unicauca.services.ConferenciaServices;
import co.edu.unicauca.services.KeycloakServices;
import co.edu.unicauca.services.UsuarioServices;
import co.edu.unicauca.utilidades.Utilidades;
import co.unicauca.login.VtnLoginn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mary
 */
/**
 * Ventana interna para registrar un nuevo artículo.
 */
public final class VtnRegistrarArticulo extends javax.swing.JFrame {

    // Variables de instancia para los servicios utilizados en la ventana
    private ArticuloServices objSArticulo;
    private ConferenciaServices objSConferencia;
    private UsuarioServices objSUsuario;
    private KeycloakServices keyServices;
    private List<Usuario> listUsuarios;
    private VtnListarArticulos objListarArticulo;
    private VtnLoginn vtnLogin;
    private Integer idUser;
    private String token = User.token;

    /**
     * Constructor de la clase VtnRegistrarArticulo.
     *
     * @param objServicio1 Servicio para gestionar los artículos.
     * @param objServicio2 Servicio para gestionar las conferencias.
     * @param objSAutores Servicio para gestionar los autores.
     */
    public VtnRegistrarArticulo(ArticuloServices objServicio1,
            ConferenciaServices objServicio2,
            UsuarioServices objSUsuario,
            VtnListarArticulos objListarArticulo) {
        initComponents();
        this.objSArticulo = objServicio1;
        this.objSConferencia = objServicio2;
        this.objSUsuario = objSUsuario;
        this.objListarArticulo = objListarArticulo;
        this.keyServices = new KeycloakServices();
        this.vtnLogin = new VtnLoginn();
        //this.idUser = vtnLogin.getIdUsuario();
        //listAutores = (ArrayList<Autor>) this.objSAutores.listarAutores();
        cargarConferencias();
        llenarTabla();
    }

    // Método para llenar las tablas u otros elementos que necesiten actualizarse
    /**
     * Método para inicializar la ventana y actualizar los elementos.
     */
    public void initialize() throws Exception {
        //listAutores = (ArrayList<Autor>) this.objSAutores.listarAutores();
        cargarConferencias();
        llenarTabla();
    }

    /**
     * Método para cargar las conferencias disponibles en el combo box.
     */
    public void cargarConferencias() {
        try {
            String token = User.token;
            jComboBoxConferencia.removeAllItems();
            ArrayList<Conferencia> conferencias = (ArrayList<Conferencia>) this.objSConferencia.listarConferencias(token);
            for (int i = 0; i < conferencias.size(); i++) {
                this.jComboBoxConferencia.addItem(conferencias.get(i).getNombre());
            }
        } catch (Exception ex) {
            Logger.getLogger(VtnRegistrarArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para limpiar la tabla de autores.
     */
    public void limpiarTabla() {
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) this.jTableAutores.getModel();
        int filas = this.jTableAutores.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    /**
     * Método para llenar la tabla con la lista de autores disponibles.
     */
    public void llenarTabla() {
        DefaultTableModel model = (DefaultTableModel) this.jTableAutores.getModel();
        limpiarTabla();

        this.listUsuarios = this.keyServices.buscarUsuariosXRol("AUTOR");
        //objSUsuario.obtenerUsuariosPorRol("AUTOR");

        if (this.listUsuarios != null) {
            for (int i = 0; i < this.listUsuarios.size(); i++) {
                String[] fila = {this.listUsuarios.get(i).getNombre(), this.listUsuarios.get(i).getCorreo()};
                model.addRow(fila);
            }
        }
    }

    /**
     * Método para obtener la lista de autores seleccionados en la tabla.
     *
     * @param tabla Tabla de autores.
     * @return Lista de autores seleccionados.
     */
    public ArrayList<Usuario> obtenerAutoresSeleccionados(JTable tabla) {
        ArrayList<Usuario> usuariosSeleccionados = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        // Recorrer las filas para verificar cuáles están seleccionadas
        for (int i = 0; i < model.getRowCount(); i++) {
            Object isSelected = (Boolean) model.getValueAt(i, 2);

            if (isSelected != null && (Boolean) isSelected) {
                Usuario usuario = listUsuarios.get(i);
                usuariosSeleccionados.add(usuario);
            }
        }
        return usuariosSeleccionados;
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
        jLabel3 = new javax.swing.JLabel();
        jPanelCentral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxConferencia = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPalabras = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAutores = new javax.swing.JTable();
        jButtonRegistrar = new javax.swing.JButton();
        jPanelInferior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("Registrar articulo");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Titulo:");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Palabras clave:");

        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        jScrollPane1.setViewportView(txtResumen);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setText("Conferencia:");

        jComboBoxConferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConferenciaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setText("Resumen:");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel6.setText("Autores:");

        jTableAutores.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTableAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "APELLIDO", "ASIGNAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAutores.setToolTipText("");
        jTableAutores.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(jTableAutores);

        jButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/verificar.png"))); // NOI18N
        jButtonRegistrar.setText("REGISTRAR");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonRegistrar)
                    .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addGroup(jPanelCentralLayout.createSequentialGroup()
                            .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                                .addComponent(txtTitulo)
                                .addComponent(jComboBoxConferencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelCentralLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRegistrar)
                .addContainerGap())
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción del botón registrar para almacenar el artículo.
     *
     * @param evt Evento de acción.
     */
    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed

        try {
            String titulo, resumen, palabras;
            ArrayList<Usuario> listAutores = obtenerAutoresSeleccionados(jTableAutores);
            List<String> idAutores = new ArrayList<>();
            // Validar que se hayan seleccionado autores
            if (listAutores.isEmpty()) {
                Utilidades.mensajeError("Debe seleccionar al menos un autor.", "Error en el registro");
                return; // Salir del método si no hay autores seleccionados
            }
            for (int i = 0; i < listAutores.size(); i++) {
                idAutores.add(listAutores.get(i).getId());
            }
            Conferencia objConferencia;
            boolean bandera, bandera1;
            titulo = this.txtTitulo.getText().trim(); // Eliminar espacios en blanco
            resumen = this.txtResumen.getText().trim(); // Eliminar espacios en blanco
            palabras = this.txtPalabras.getText().trim(); // Eliminar espacios en blanco
            // Validar que no haya campos vacíos
            if (titulo.isEmpty() || resumen.isEmpty() || palabras.isEmpty()) {
                Utilidades.mensajeError("Todos los campos son obligatorios", "Error en el registro");
                return; // Salir del método si algún campo está vacío
            }
            String nombreConferencia = (String) this.jComboBoxConferencia.getSelectedItem();
            // Verificar si se seleccionó una conferencia
            if (nombreConferencia == null) {
                Utilidades.mensajeError("Debe seleccionar una conferencia.", "Error en el registro");
                return; // Salir del método si no hay conferencia seleccionada
            }
            objConferencia = objSConferencia.obtenerConferenciaPorNombre(token, nombreConferencia);

            // Verificar si la conferencia existe
            if (objConferencia == null) {
                Utilidades.mensajeError("La conferencia seleccionada no es válida.", "Error en el registro");
                return; // Salir del método si la conferencia no es válida
            }

            Articulo objArticulo = new Articulo();
            objArticulo.setId(this.objListarArticulo.getjTableListarArticulos().getRowCount() + 1);
            objArticulo.setTitulo(titulo);
            objArticulo.setResumen(resumen);
            objArticulo.setPalabrasClave(palabras);
            objArticulo.setAutores(idAutores);
            objArticulo.setConferencia(objConferencia);
            

            // Almacenar el artículo utilizando el servicio
            bandera = this.objSArticulo.guardarArticulo(token, objArticulo);
            bandera1 = this.objSConferencia.actualizarConferencia(token, objConferencia.getId(), objArticulo.getId());

            // Mostrar mensaje de éxito o error
            if (bandera && bandera1) {
                Utilidades.mensajeExito("Registro exitoso", "Registro exitoso");
                objListarArticulo.llenarTabla();
                dispose();
            } else {
                Utilidades.mensajeError("Artículo no almacenado", "Error al almacenar el artículo");
            }
        } catch (Exception ex) {
            Logger.getLogger(VtnRegistrarArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jComboBoxConferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConferenciaActionPerformed

    }//GEN-LAST:event_jComboBoxConferenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JComboBox<String> jComboBoxConferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAutores;
    private javax.swing.JTextField txtPalabras;
    private javax.swing.JTextArea txtResumen;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
