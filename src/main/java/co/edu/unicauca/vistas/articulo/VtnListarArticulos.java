package co.edu.unicauca.vistas.articulo;

import co.edu.unicauca.modelos.Articulo;
import co.edu.unicauca.modelos.Conferencia;
import co.edu.unicauca.modelos.User;
import co.edu.unicauca.modelos.Usuario;
import co.edu.unicauca.services.ArticuloServices;
import co.edu.unicauca.services.ConferenciaServices;
import co.edu.unicauca.services.KeycloakServices;
import co.edu.unicauca.services.UsuarioServices;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana para listar artículos en el sistema de conferencias.
 */
public class VtnListarArticulos extends javax.swing.JInternalFrame {

    // Variables de instancia para los servicios utilizados en la ventana
    /**
     * Servicio para gestionar los artículos.
     */
    public ArticuloServices objSArticulo;
    /**
     * Servicio para gestionar las conferencias.
     */
    public ConferenciaServices objSConferencia;

    private UsuarioServices objSUsuario;
    
    private KeycloakServices keyServices;
    
    private String token;

    /**
     * Constructor de la clase VtnListarArticulos.
     *
     * @param objSArticulo Servicio para gestionar los artículos.
     * @param objSConferencia Servicio para gestionar las conferencias.
     * @param objSEvaluador Servicio para gestionar los evaluadores.
     */
    public VtnListarArticulos(
            ArticuloServices objSArticulo,
            ConferenciaServices objSConferencia,
            UsuarioServices objSUsuario
    ) {
        initComponents();
        this.objSArticulo = objSArticulo;
        this.objSConferencia = objSConferencia;
        this.objSUsuario = objSUsuario;
        this.jTableListarArticulos.setDefaultRenderer(Object.class, new Render());
    }

    public JTable getjTableListarArticulos() {
        return jTableListarArticulos;
    }

    // Método para llenar las tablas u otros elementos que necesiten actualizarse
    /**
     * Método para inicializar la ventana y actualizar los elementos.
     */
    public void initialize() {
        btnAsignar.setVisible(false);
        btnActualizar.setVisible(false);
        btnComentarios.setVisible(false);
        btnEvaluar.setVisible(false);
        llenarTabla();
    }

    /**
     * Gestiona la visibilidad de los elementos de la ventana según el rol del
     * usuario.
     *
     * @param rol El rol del usuario (Autor, Evaluador, Organizador).
     */
    public void gestionRol(String rol) {
        switch (rol) {
            case "AUTOR":
                btnAsignar.setEnabled(false);
                jButtonRegistrar.setVisible(true);
                break;
            case "ORGANIZADOR":
                btnAsignar.setEnabled(true);
                jButtonRegistrar.setVisible(false);
                break;
            case "EVALUADOR":
                btnAsignar.setEnabled(false);
                jButtonRegistrar.setVisible(false);
                break;
            default:
                break;
        }
    }

    /**
     * Método para limpiar la tabla de artículos.
     */
    public void limpiarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableListarArticulos.getModel();
        int filas = this.jTableListarArticulos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    /**
     * Método para llenar la tabla con los artículos listados.
     */
    public void llenarTabla() {
        try {
            keyServices = new KeycloakServices();
            token = User.token;
            DefaultTableModel model = (DefaultTableModel) this.jTableListarArticulos.getModel();
            
            limpiarTabla();
            ArrayList<Articulo> listaArticulos = (ArrayList<Articulo>) this.objSArticulo.listarArticulos(token);
            
            for (Articulo articulo : listaArticulos) {
                StringBuilder autoresConcatenados = new StringBuilder();
                
                // Obtener los IDs de los autores asociados al artículo y consultar sus nombres
                List<String> listaIdsAutores = articulo.getAutores(); // suponiendo que getAutores() devuelve los IDs de los autores
                for (String idAutor : listaIdsAutores) {
                    Usuario autor = keyServices.getUserBySub(idAutor);
                            //objSUsuario.obtenerUsuario(idAutor); // Método en Usuario para obtener el usuario por ID
                    if (autor != null) {
                        if (autoresConcatenados.length() > 0) {
                            autoresConcatenados.append(", ");
                        }
                        autoresConcatenados.append(autor.getNombre()); // Suponiendo que getNombre() devuelve el nombre del usuario
                    }
                }
                Conferencia objConferencia = this.objSConferencia.obtenerConferenciaPorId(token, articulo.getConferencia().getId());
                        //consultarConferenciaPorId(articulo.getConferencia().getId());
                
                model.addRow(new Object[]{
                    articulo.getId(),
                    articulo.getTitulo(),
                    articulo.getResumen(),
                    articulo.getPalabrasClave(),
                    autoresConcatenados.toString(),
                    objConferencia.getNombre(),
                    articulo.getPalabrasClave()
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(VtnListarArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblIndice = new javax.swing.JLabel();
        jPanelCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListarArticulos = new javax.swing.JTable();
        btnAsignar = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnComentarios = new javax.swing.JButton();
        btnEvaluar = new javax.swing.JButton();
        jPanelInferior = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconG.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(789, 416));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Listado de articulos");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(304, 304, 304)
                .addComponent(lblIndice)
                .addGap(31, 31, 31))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblIndice))
                .addContainerGap())
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelCentral.setForeground(new java.awt.Color(0, 0, 153));

        jTableListarArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO", "RESUMEN", "PALABRAS CLAVE", "AUTORES", "CONFERENCIA", "PALABRA CLAVE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListarArticulos.setColumnSelectionAllowed(true);
        jTableListarArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableListarArticulos.setSurrendersFocusOnKeystroke(true);
        jTableListarArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarArticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListarArticulos);
        jTableListarArticulos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAsignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/asignar.png"))); // NOI18N
        btnAsignar.setText("Asignar Evaluador");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        jButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/verificar.png"))); // NOI18N
        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnComentarios.setText("Comentarios");
        btnComentarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComentariosActionPerformed(evt);
            }
        });

        btnEvaluar.setText("Evaluar");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCentralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEvaluar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRegistrar)))
                .addContainerGap())
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRegistrar)
                        .addComponent(btnAsignar))
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEvaluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        jPanelInferior.setPreferredSize(new java.awt.Dimension(777, 30));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción del botón registrar para abrir la ventana de registro de
     * artículos.
     *
     * @param evt Evento de acción.
     */
    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed

        VtnRegistrarArticulo objVtnRegistrarArticulo = new VtnRegistrarArticulo(objSArticulo, objSConferencia, objSUsuario, this);
        objVtnRegistrarArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objVtnRegistrarArticulo.setVisible(true);

    }//GEN-LAST:event_jButtonRegistrarActionPerformed
    /**
     * Manejo del evento de clic en la tabla de artículos.
     *
     * @param evt Evento de clic del mouse.
     */
    private void jTableListarArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarArticulosMouseClicked
//        int indice = jTableListarArticulos.getSelectedRow();
//        int idArticulo = Integer.parseInt(jTableListarArticulos.getValueAt(indice, 0).toString());
//        Articulo articulo = objSArticulo.consultarArticulo(idArticulo);
//        btnAsignar.setVisible(false);
//        btnActualizar.setVisible(false);
//        btnComentarios.setVisible(false);
//        btnEvaluar.setVisible(false);
//        if (articulo.getEstado() == "Revisado"){
//            btnComentarios.setVisible(true);
//            btnEvaluar.setVisible(true);
//            btnActualizar.setVisible(true);
//        } else if (articulo.getEstado() == "En Revision"){
//            btnEvaluar.setVisible(true);
//            btnAsignar.setVisible(true);
//            btnActualizar.setVisible(true);
//        } else{
//            btnAsignar.setVisible(true);
//            btnActualizar.setVisible(true); 
//        }
    }//GEN-LAST:event_jTableListarArticulosMouseClicked
    /**
     * Acción del botón asignar para abrir la ventana de asignación de
     * evaluadores.
     *
     * @param evt Evento de acción.
     */
    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
//        int indice = jTableListarArticulos.getSelectedRow();
//        int idArticulo = Integer.parseInt(jTableListarArticulos.getValueAt(indice, 0).toString());
//        VtnAsignarEvaluador objVtnAsignarEvaluador = new VtnAsignarEvaluador(objSArticulo, idArticulo, this);
//        String titulo = jTableListarArticulos.getValueAt(indice, 0).toString();
//        objVtnAsignarEvaluador.lblArticulo.setText(titulo);
//        objVtnAsignarEvaluador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        objVtnAsignarEvaluador.setVisible(true);
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
//        int indice = jTableListarArticulos.getSelectedRow();
//        int idArticulo = Integer.parseInt(jTableListarArticulos.getValueAt(indice, 0).toString());
//        VtnActualizarArticulo objVtnActualizarArticulo
//                = new VtnActualizarArticulo(objSArticulo, objSConferencia, this);
//        objVtnActualizarArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        objVtnActualizarArticulo.cargarDatos(idArticulo);
//        objVtnActualizarArticulo.setVisible(true);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnComentariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComentariosActionPerformed
//        int indice = jTableListarArticulos.getSelectedRow();
//        int idArticulo = Integer.parseInt(jTableListarArticulos.getValueAt(indice, 0).toString());
//        VtnComentarios objVtnComentarios
//                = new VtnComentarios(objSArticulo, idArticulo);
//        objVtnComentarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        objVtnComentarios.setVisible(true);
    }//GEN-LAST:event_btnComentariosActionPerformed

    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
//        int indice = jTableListarArticulos.getSelectedRow();
//        int idArticulo = Integer.parseInt(jTableListarArticulos.getValueAt(indice, 0).toString());
//        VtnEvaluarPaper objVtnEvaluarPaper
//                = new VtnEvaluarPaper(objSArticulo, idArticulo, this);
//        objVtnEvaluarPaper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        objVtnEvaluarPaper.setVisible(true);
    }//GEN-LAST:event_btnEvaluarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnComentarios;
    private javax.swing.JButton btnEvaluar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarArticulos;
    private javax.swing.JLabel lblIndice;
    // End of variables declaration//GEN-END:variables
}
