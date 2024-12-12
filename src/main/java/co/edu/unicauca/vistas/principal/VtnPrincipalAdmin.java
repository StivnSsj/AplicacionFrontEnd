package co.edu.unicauca.vistas.principal;


import co.edu.unicauca.vistas.articulo.VtnListarArticulos;
import co.edu.unicauca.services.ArticuloServices;
import co.edu.unicauca.services.ConferenciaServices;
import co.edu.unicauca.services.UsuarioServices;
import co.edu.unicauca.vistas.articulo.VtnRegistrarArticulo;
import co.edu.unicauca.vistas.autor.VtnListarAutor;
import co.edu.unicauca.vistas.conferencia.VtnListarConferencias;
import co.edu.unicauca.vistas.evaluador.VtnListarEvaluador;
import co.edu.unicauca.vistas.organizador.VtnListarOrganizadores;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Clase VtnPrincipalAdmin
 * 
 * Esta clase representa la ventana principal para el administrador en la aplicación de gestión de conferencias.
 * Proporciona la interfaz gráfica para gestionar conferencias, artículos, organizadores, evaluadores y autores.
 * También permite la asignación de roles y la visibilidad de opciones basadas en el rol del usuario.
 * 
 * Se usa la librería Swing para la creación de la interfaz gráfica de usuario.
 */

public class VtnPrincipalAdmin extends javax.swing.JFrame {
//    // Declaración de ventanas secundarias que serán gestionadas desde esta ventana principal
    private VtnListarArticulos objVtnListarArticulos;
    private VtnListarConferencias objVtnListarConferencias;
    private VtnListarOrganizadores objVtnListarOrganizadores;
    private VtnListarEvaluador objVtnListarEvaluador;
    private VtnListarAutor objVtnListarAutores;
    private VtnRegistrarArticulo objVtnRegistrarArticulo;
//    // Declaración de servicios utilizados por la aplicación
    private ConferenciaServices objSConferencia;
    private ArticuloServices objSArticulo;
    private UsuarioServices objSUsuario;
    
    // Rol del usuario actual
    private String rol;
     /**
     * Constructor de la clase VtnPrincipalAdmin.
     * Inicializa los componentes gráficos y establece el estado inicial de la ventana.
     */
    
    public VtnPrincipalAdmin() {
        initComponents();
        asociarServicios(new ConferenciaServices(), new ArticuloServices(), new UsuarioServices());
        this.setExtendedState(JFrame.NORMAL);
        
    }
    /**
     * Método para gestionar la visibilidad de los botones según el rol del usuario.
     * 
     * @param rol El rol del usuario actual (Autor, Evaluador, Organizador).
     */

    public void gestionRol(String rol) {
        this.rol = rol;
        switch (rol) {
            case "AUTOR":
                jButtonGestionarE.setVisible(false);
                jButtonGestionarO.setVisible(false);
                jButtonAutores.setVisible(true);
                jButtonGestionarConferencias.setVisible(true);
                jButtonGestionarConferencias.setText("Ver Conferencias");
                break;
            case "ORGANIZADOR":
                jButtonGestionarE.setVisible(true);
                jButtonGestionarO.setVisible(true);
                jButtonAutores.setVisible(true);
                jButtonGestionarConferencias.setVisible(true);
                jButtonVerArticulosEnviados.setText("Ver Articulos");
                break;
            case "EVALUADOR":
                jButtonGestionarO.setVisible(false);
                jButtonAutores.setVisible(false);
                jButtonGestionarE.setVisible(true);
                jButtonGestionarConferencias.setVisible(false);
            default:
                break;
        }
    }
    
    /* Método para asociar los servicios a la ventana principal.
     * 
     * @param objServicio1 Servicio de conferencias.
     * @param objServicio2 Servicio de artículos.
     * @param objServicio3 Servicio de organizadores.
     * @param objServicio4 Servicio de evaluadores.
     * @param objSAutores Servicio de autores.
     */

    public void asociarServicios(
            ConferenciaServices objSConferencia,
            ArticuloServices objSArticulo,
            UsuarioServices objSUsuario
            ) {
        this.objSConferencia = objSConferencia;
        this.objSArticulo = objSArticulo;
        this.objSUsuario = objSUsuario;
        relacionarInternalFrameConJdesptokPane();
    }
    
    /**
     * Método para relacionar las ventanas internas con el panel de escritorio principal.
     */

    private void relacionarInternalFrameConJdesptokPane() {

        this.objVtnListarArticulos = new VtnListarArticulos(this.objSArticulo, this.objSConferencia,this.objSUsuario);
                this.jDesktopPanelPrincipal.add(this.objVtnListarArticulos);

        this.objVtnListarConferencias = new VtnListarConferencias(this.objSConferencia);
                this.jDesktopPanelPrincipal.add(this.objVtnListarConferencias);

        this.objVtnListarEvaluador = new VtnListarEvaluador(this.objSArticulo);
                //this.objServicio4, objServicio3);
        this.jDesktopPanelPrincipal.add(this.objVtnListarEvaluador);

        this.objVtnListarOrganizadores = new VtnListarOrganizadores();
        this.jDesktopPanelPrincipal.add(this.objVtnListarOrganizadores);

        this.objVtnListarAutores = new VtnListarAutor();
                this.jDesktopPanelPrincipal.add(this.objVtnListarAutores);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabelImagenOrganizacion = new javax.swing.JLabel();
        jPanelBar = new javax.swing.JPanel();
        jButtonGestionarConferencias = new javax.swing.JButton();
        jButtonGestionarE = new javax.swing.JButton();
        jButtonVerArticulosEnviados = new javax.swing.JButton();
        jButtonGestionarO = new javax.swing.JButton();
        jButtonAutores = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanelCentral = new javax.swing.JPanel();
        jDesktopPanelPrincipal = new javax.swing.JDesktopPane();
        jPanelInferior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 300));
        setSize(new java.awt.Dimension(1072, 400));

        jPanelSuperior.setLayout(new java.awt.BorderLayout());

        jLabelImagenOrganizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconG.png"))); // NOI18N
        jLabelImagenOrganizacion.setToolTipText("");
        jLabelImagenOrganizacion.setAlignmentX(1.0F);
        jLabelImagenOrganizacion.setIconTextGap(5);
        jLabelImagenOrganizacion.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanelSuperior.add(jLabelImagenOrganizacion, java.awt.BorderLayout.LINE_START);

        jPanelBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelBar.setLayout(new javax.swing.BoxLayout(jPanelBar, javax.swing.BoxLayout.LINE_AXIS));

        jButtonGestionarConferencias.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonGestionarConferencias.setText("Gestionar conferencias");
        jButtonGestionarConferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionarConferenciasActionPerformed(evt);
            }
        });
        jPanelBar.add(jButtonGestionarConferencias);

        jButtonGestionarE.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonGestionarE.setText("Gestionar Evaluadores");
        jButtonGestionarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionarEActionPerformed(evt);
            }
        });
        jPanelBar.add(jButtonGestionarE);

        jButtonVerArticulosEnviados.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonVerArticulosEnviados.setText("Gestionar articulos");
        jButtonVerArticulosEnviados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerArticulosEnviadosActionPerformed(evt);
            }
        });
        jPanelBar.add(jButtonVerArticulosEnviados);

        jButtonGestionarO.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonGestionarO.setText("Organizadores Registrados");
        jButtonGestionarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionarOActionPerformed(evt);
            }
        });
        jPanelBar.add(jButtonGestionarO);

        jButtonAutores.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonAutores.setText("Autores Registrados");
        jButtonAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutoresActionPerformed(evt);
            }
        });
        jPanelBar.add(jButtonAutores);

        btnSalir.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnSalir.setText("Cerrar Sesion");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanelBar.add(btnSalir);

        jPanelSuperior.add(jPanelBar, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelCentral.setLayout(new java.awt.CardLayout());

        jDesktopPanelPrincipal.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPanelPrincipal.setMaximumSize(new java.awt.Dimension(1072, 400));

        javax.swing.GroupLayout jDesktopPanelPrincipalLayout = new javax.swing.GroupLayout(jDesktopPanelPrincipal);
        jDesktopPanelPrincipal.setLayout(jDesktopPanelPrincipalLayout);
        jDesktopPanelPrincipalLayout.setHorizontalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
        );
        jDesktopPanelPrincipalLayout.setVerticalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jPanelCentral.add(jDesktopPanelPrincipal, "card2");

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        jPanelInferior.setAlignmentX(0.0F);
        jPanelInferior.setAlignmentY(0.0F);
        jPanelInferior.setAutoscrolls(true);
        jPanelInferior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
//        if (objVtnListarConferencias != null) {
//            objVtnListarConferencias.setVisible(false);
//        }
//        if (objVtnListarArticulos != null) {
//            objVtnListarArticulos.setVisible(false);
//        }
//        if (objVtnRegistrarOrganizadores != null) {
//            objVtnRegistrarOrganizadores.setVisible(false);
//        }
//        if (objVtnListarEvaluador != null) {
//            objVtnListarEvaluador.setVisible(false);
//        }
//        if (objVtnRegistrarAutor != null) {
//            objVtnRegistrarAutor.setVisible(false);
//        }       
        
        // Cierra la ventana principal
        dispose();
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jButtonGestionarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionarOActionPerformed
        // Configurar la ventana para ocultarse al cerrarse
        this.objVtnListarOrganizadores.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.objVtnListarConferencias.setVisible(false);
        this.objVtnListarOrganizadores.setVisible(true);
        this.objVtnListarArticulos.setVisible(false);
        this.objVtnListarEvaluador.setVisible(false);
    }//GEN-LAST:event_jButtonGestionarOActionPerformed

    private void jButtonGestionarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionarEActionPerformed
        // Configurar la ventana para ocultarse al cerrarse
        this.objVtnListarEvaluador.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.objVtnListarEvaluador.setVisible(true);
        this.objVtnListarConferencias.setVisible(false);
        this.objVtnListarArticulos.setVisible(false);

    }//GEN-LAST:event_jButtonGestionarEActionPerformed

    private void jButtonVerArticulosEnviadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerArticulosEnviadosActionPerformed
        // Configurar la ventana para ocultarse al cerrarse
        this.objVtnListarArticulos.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        objVtnListarArticulos.initialize();
        objVtnListarArticulos.gestionRol(rol);
        this.objVtnListarArticulos.setVisible(true);
        this.objVtnListarConferencias.setVisible(false);
        this.objVtnListarEvaluador.setVisible(false);

    }//GEN-LAST:event_jButtonVerArticulosEnviadosActionPerformed

    private void jButtonGestionarConferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionarConferenciasActionPerformed
        // Configurar la ventana para ocultarse al cerrarse
        this.objVtnListarConferencias.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.objVtnListarConferencias.gestionRol(rol);
        this.objVtnListarConferencias.setVisible(true);
        this.objVtnListarArticulos.setVisible(false);

    }//GEN-LAST:event_jButtonGestionarConferenciasActionPerformed

    private void jButtonAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutoresActionPerformed
       // Configurar la ventana para ocultarse al cerrarse
        this.objVtnListarAutores.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); 
        this.objVtnListarConferencias.setVisible(false);
        this.objVtnListarArticulos.setVisible(false);
        this.objVtnListarOrganizadores.setVisible(false);
        this.objVtnListarEvaluador.setVisible(false);
        this.objVtnListarAutores.setVisible(true);
    }//GEN-LAST:event_jButtonAutoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButtonAutores;
    private javax.swing.JButton jButtonGestionarConferencias;
    private javax.swing.JButton jButtonGestionarE;
    private javax.swing.JButton jButtonGestionarO;
    private javax.swing.JButton jButtonVerArticulosEnviados;
    private javax.swing.JDesktopPane jDesktopPanelPrincipal;
    private javax.swing.JLabel jLabelImagenOrganizacion;
    private javax.swing.JPanel jPanelBar;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    // End of variables declaration//GEN-END:variables

}
