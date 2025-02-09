/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package co.edu.unicauca.vistas.autor;

import co.edu.unicauca.vistas.organizador.*;
import co.edu.unicauca.modelos.Usuario;
import co.edu.unicauca.services.KeycloakServices;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mary
 */
/**
 * Clase VtnListarOrganizadores.
 *
 * Esta clase representa una ventana interna para listar organizadores en la
 * aplicación. Permite visualizar, actualizar y registrar nuevos organizadores.
 */
public class VtnListarAutor extends javax.swing.JInternalFrame {

    private KeycloakServices keyServices;

    
    public VtnListarAutor() {
        // Inicializa los componentes de la ventana.
        initComponents();
        
        // Llama al método para inicializar la tabla de organizadores.
        iniciarlizarTabla();
        this.keyServices = new KeycloakServices();
        llenarTabla();
    }

    /**
     * Inicializa la tabla que mostrará la lista de organizadores.
     *
     * Crea un modelo de tabla con columnas específicas para los datos de los
     * organizadores.
     */
    private void iniciarlizarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("USER");
        model.addColumn("CORREO");
        this.jTableListadoOrganizadores.setModel(model);
    }

    /**
     * Limpia los datos de la tabla de organizadores.
     *
     * Elimina todas las filas de la tabla para poder volver a llenarla con
     * nuevos datos.
     */
    public void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableListadoOrganizadores.getModel();
        int filas = this.jTableListadoOrganizadores.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    /**
     * Llena la tabla con la lista de organizadores.
     *
     * Limpia la tabla actual y la llena con datos de organizadores obtenidos
     * del servicio.
     */
    private void llenarTabla() {
        DefaultTableModel model = (DefaultTableModel) this.jTableListadoOrganizadores.getModel();
        limpiarTabla();

        try {
            // Inicializa el servicio si es necesario
            if (this.keyServices == null) {
                this.keyServices = new KeycloakServices();
            }

            List<Usuario> listaOrganizadores = this.keyServices.buscarUsuariosXRol("AUTOR");
                    //this.objServicioAlmacenamientoOrganizadores.listarOrganizadores();

            // Verifica si la lista está vacía
            if (listaOrganizadores == null || listaOrganizadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ningún autor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Sal del método si no hay organizadores
            }

            // Agrega los datos de cada organizador a la tabla.
            for (Usuario organizador : listaOrganizadores) {
                String[] fila = {
                    organizador.getId(),
                    organizador.getNombre(),
                    organizador.getUser(),
                    organizador.getCorreo()
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
        jLabelTitulo = new javax.swing.JLabel();
        jPanelInferior = new javax.swing.JPanel();
        jPanelCentral = new javax.swing.JPanel();
        jButtonActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListadoOrganizadores = new javax.swing.JTable();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconG.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(789, 416));

        jPanelSuperior.setPreferredSize(new java.awt.Dimension(887, 30));

        jLabelTitulo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTitulo.setText("Listar Organizadores");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addContainerGap(360, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelInferior.setPreferredSize(new java.awt.Dimension(889, 30));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 847, Short.MAX_VALUE)
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

        jTableListadoOrganizadores.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTableListadoOrganizadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableListadoOrganizadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableListadoOrganizadores.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(jTableListadoOrganizadores);

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCentralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonActualizar)
                .addGap(47, 47, 47))
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonActualizar)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de acción para el botón de actualizar.
     *
     * Llama al método para llenar la tabla con los datos actualizados de
     * organizadores.
     *
     * @param evt Evento de acción que ocurre al presionar el botón.
     */

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_jButtonActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListadoOrganizadores;
    // End of variables declaration//GEN-END:variables
}
