package itson.org.ghosttracks.presentacion.administrador;

/**
 *
 * @author nafbr
 */
public class PantallaConfirmacionRecepcion extends javax.swing.JPanel {

    public PantallaConfirmacionRecepcion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblCOnfirmacion = new javax.swing.JLabel();
        panelRedondeado1 = new itson.org.ghosttracks.utilerias.PanelRedondeado();
        lblFechaOrden = new javax.swing.JLabel();
        lblFechaOrdenDisplay = new javax.swing.JLabel();
        lblFechaEntrega = new javax.swing.JLabel();
        lblFechaEntregaDisplay = new javax.swing.JLabel();
        lblFolio = new javax.swing.JLabel();
        lblFolioDisplay = new javax.swing.JLabel();
        lblTipoOrden = new javax.swing.JLabel();
        lblTipoOrdenDisplay = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        lblSucursalDisplay = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        lblProveedorDisplay = new javax.swing.JLabel();
        lblComentarios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaComentariosDisplay = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosOrden = new javax.swing.JTable();
        btnAgregarImagen = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnConfirmarRecepcion = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnVolver = new itson.org.ghosttracks.utilerias.BotonRedondeado();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblCOnfirmacion.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblCOnfirmacion.setText("Confirmación de recepción");

        panelRedondeado1.setBackground(new java.awt.Color(217, 217, 217));

        lblFechaOrden.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden.setText("Fecha de la orden:");

        lblFechaOrdenDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrdenDisplay.setText("NA");

        lblFechaEntrega.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaEntrega.setText("Fecha de entrega:");

        lblFechaEntregaDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaEntregaDisplay.setText("NA");

        lblFolio.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFolio.setText("Folio:");

        lblFolioDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFolioDisplay.setText("NA");

        lblTipoOrden.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblTipoOrden.setText("Tipo Orden:");

        lblTipoOrdenDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblTipoOrdenDisplay.setText("NA");

        lblSucursal.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblSucursal.setText("Sucursal:");

        lblSucursalDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblSucursalDisplay.setText("NA");

        lblProveedor.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblProveedor.setText("Proveedor:");

        lblProveedorDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblProveedorDisplay.setText("NA");

        lblComentarios.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblComentarios.setText("Comentarios:");

        txtaComentariosDisplay.setEditable(false);
        txtaComentariosDisplay.setBackground(new java.awt.Color(217, 217, 217));
        txtaComentariosDisplay.setColumns(20);
        txtaComentariosDisplay.setRows(5);
        txtaComentariosDisplay.setBorder(null);
        jScrollPane1.setViewportView(txtaComentariosDisplay);

        tblProductosOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Producto", "Cantidad", "Total", "Recibido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProductosOrden);

        btnAgregarImagen.setBackground(new java.awt.Color(191, 64, 43));
        btnAgregarImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarImagen.setText("Agregar imagen de recepción");

        javax.swing.GroupLayout panelRedondeado1Layout = new javax.swing.GroupLayout(panelRedondeado1);
        panelRedondeado1.setLayout(panelRedondeado1Layout);
        panelRedondeado1Layout.setHorizontalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addComponent(lblFolio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFolioDisplay))
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblFechaOrden)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFechaOrdenDisplay))
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblFechaEntrega)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFechaEntregaDisplay)))
                                .addGap(135, 135, 135)
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblProveedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblProveedorDisplay))
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblSucursal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblSucursalDisplay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblComentarios))))
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addComponent(lblTipoOrden)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTipoOrdenDisplay)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondeado1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        panelRedondeado1Layout.setVerticalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRedondeado1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaOrden)
                            .addComponent(lblFechaOrdenDisplay)
                            .addComponent(lblSucursal)
                            .addComponent(lblSucursalDisplay)
                            .addComponent(lblComentarios))
                        .addGap(18, 18, 18)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaEntregaDisplay)
                            .addComponent(lblFechaEntrega)
                            .addComponent(lblProveedor)
                            .addComponent(lblProveedorDisplay))
                        .addGap(18, 18, 18)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFolio)
                            .addComponent(lblFolioDisplay))
                        .addGap(18, 18, 18)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoOrden)
                            .addComponent(lblTipoOrdenDisplay))))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        btnConfirmarRecepcion.setBackground(new java.awt.Color(191, 64, 43));
        btnConfirmarRecepcion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarRecepcion.setText("Confirmar recepción");
        btnConfirmarRecepcion.addActionListener(this::btnConfirmarRecepcionActionPerformed);

        btnVolver.setBackground(new java.awt.Color(191, 64, 43));
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCOnfirmacion)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirmarRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblCOnfirmacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmarRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConfirmarRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarRecepcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarRecepcionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnAgregarImagen;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnConfirmarRecepcion;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCOnfirmacion;
    private javax.swing.JLabel lblComentarios;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblFechaEntregaDisplay;
    private javax.swing.JLabel lblFechaOrden;
    private javax.swing.JLabel lblFechaOrdenDisplay;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JLabel lblFolioDisplay;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblProveedorDisplay;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblSucursalDisplay;
    private javax.swing.JLabel lblTipoOrden;
    private javax.swing.JLabel lblTipoOrdenDisplay;
    private itson.org.ghosttracks.utilerias.PanelRedondeado panelRedondeado1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblProductosOrden;
    private javax.swing.JTextArea txtaComentariosDisplay;
    // End of variables declaration//GEN-END:variables
}
