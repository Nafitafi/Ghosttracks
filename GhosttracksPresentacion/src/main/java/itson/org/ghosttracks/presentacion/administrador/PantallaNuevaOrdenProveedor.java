package itson.org.ghosttracks.presentacion.administrador;

/**
 *
 * @author nafbr
 */
public class PantallaNuevaOrdenProveedor extends javax.swing.JPanel {

    public PantallaNuevaOrdenProveedor() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblOrdenesProveedores = new javax.swing.JLabel();
        panelRedondeado1 = new itson.org.ghosttracks.utilerias.PanelRedondeado();
        dtpFecha = new com.github.lgooddatepicker.components.DatePicker();
        cbxSucursal = new javax.swing.JComboBox<>();
        cbxTipoOrden = new javax.swing.JComboBox<>();
        cbxProveedor = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaComentarios = new javax.swing.JTextArea();
        btnBuscarProducto = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        lblCantidad = new javax.swing.JLabel();
        txtCantidadProducto = new itson.org.ghosttracks.utilerias.TextFieldRedondeado();
        btnAgregarProducto = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosOrden = new javax.swing.JTable();
        btnCancelarOrden = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnRealizarOrden = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        lblProducto = new javax.swing.JLabel();
        lblProductoDisplay = new javax.swing.JLabel();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblOrdenesProveedores.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblOrdenesProveedores.setText("Realizar nueva orden");

        panelRedondeado1.setBackground(new java.awt.Color(217, 217, 217));

        dtpFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));

        cbxSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSucursal.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursal"));

        cbxTipoOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoOrden.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo Orden"));

        cbxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));

        txaComentarios.setColumns(20);
        txaComentarios.setRows(5);
        txaComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Comentarios"));
        jScrollPane1.setViewportView(txaComentarios);

        btnBuscarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnBuscarProducto.setText("Buscar producto");

        lblCantidad.setText("Cantidad: ");

        txtCantidadProducto.addActionListener(this::txtCantidadProductoActionPerformed);

        btnAgregarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnAgregarProducto.setText("Agregar producto");

        tblProductosOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Producto", "Cantidad", "Total", "Acciones"
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

        btnCancelarOrden.setBackground(new java.awt.Color(191, 64, 43));
        btnCancelarOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarOrden.setText("Cancelar Orden");

        btnRealizarOrden.setBackground(new java.awt.Color(191, 64, 43));
        btnRealizarOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarOrden.setText("Realizar Orden");

        lblProducto.setText("Producto:");

        lblProductoDisplay.setText("ninguno");

        javax.swing.GroupLayout panelRedondeado1Layout = new javax.swing.GroupLayout(panelRedondeado1);
        panelRedondeado1.setLayout(panelRedondeado1Layout);
        panelRedondeado1Layout.setHorizontalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxTipoOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtpFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSucursal, 0, 160, Short.MAX_VALUE)
                                    .addComponent(cbxProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondeado1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProductoDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCantidad)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(btnCancelarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRealizarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        panelRedondeado1Layout.setVerticalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTipoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto)
                    .addComponent(lblProductoDisplay))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRealizarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblOrdenesProveedores)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelRedondeado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblOrdenesProveedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
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

    private void txtCantidadProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnAgregarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnBuscarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnCancelarOrden;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnRealizarOrden;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JComboBox<String> cbxSucursal;
    private javax.swing.JComboBox<String> cbxTipoOrden;
    private com.github.lgooddatepicker.components.DatePicker dtpFecha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblOrdenesProveedores;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProductoDisplay;
    private itson.org.ghosttracks.utilerias.PanelRedondeado panelRedondeado1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblProductosOrden;
    private javax.swing.JTextArea txaComentarios;
    private itson.org.ghosttracks.utilerias.TextFieldRedondeado txtCantidadProducto;
    // End of variables declaration//GEN-END:variables
}
