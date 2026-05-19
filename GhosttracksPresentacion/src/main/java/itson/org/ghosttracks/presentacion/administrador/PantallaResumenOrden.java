package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author nafbr
 */
public class PantallaResumenOrden extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private OrdenDTO orden;
    
    public PantallaResumenOrden() {
        initComponents();
    }
    
    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
    }

    public void cargarOrden(OrdenDTO orden) {
        this.orden = orden;
        if (orden == null) {
            return;
        }

        lblFechaOrdenDisplay.setText(orden.getFechaSolicitud() != null ? orden.getFechaSolicitud().toLocalDate().toString() : "NA");
        lblFechaEntregaDisplay.setText(orden.getFechaEntregaEst() != null ? orden.getFechaEntregaEst().toString() : "NA");
        lblFolioDisplay.setText(texto(orden.getFolio()));
        lblTipoOrdenDisplay.setText(orden.getTipoOrden() != null ? orden.getTipoOrden().toString() : "NA");
        lblSucursalDisplay.setText(orden.getSucursal() != null ? texto(orden.getSucursal().getNombre()) : "NA");
        lblProveedorDisplay.setText(orden.getProveedor() != null ? texto(orden.getProveedor().getNombreProveedor()) : "NA");
        cargarImagenRecepcion(orden.getImagenRecepcion());
        lblTipoOrdenDisplay1.setText(orden.getEstadoOrden() != null ? orden.getEstadoOrden().toString() : "NA");
        txtaComentariosDisplay.setText(texto(orden.getComentarios()));

        DefaultTableModel modelo = (DefaultTableModel) tblProductosOrden.getModel();
        modelo.setRowCount(0);
        if (orden.getProductosOrden() != null) {
            for (ProductoOrdenDTO productoOrden : orden.getProductosOrden()) {
                ProductoDTO producto = productoOrden.getProducto();
                modelo.addRow(new Object[]{
                    producto != null ? producto.getIdProducto() : "NA",
                    producto != null ? texto(producto.getNombre()) : "NA",
                    productoOrden.getCantidadProducto(),
                    String.format("$%.2f", productoOrden.getImporteTotal())
                });
            }
        }
    }

    private String texto(String valor) {
        return valor != null && !valor.isBlank() ? valor : "NA";
    }

    private void cargarImagenRecepcion(Byte[] imagenRecepcion) {
        lblImagenDisplay.setIcon(null);
        lblImagenDisplay.setToolTipText(null);
        if (imagenRecepcion == null || imagenRecepcion.length == 0) {
            lblImagenDisplay.setText("Sin imagen");
            return;
        }

        byte[] bytes = new byte[imagenRecepcion.length];
        for (int i = 0; i < imagenRecepcion.length; i++) {
            bytes[i] = imagenRecepcion[i] != null ? imagenRecepcion[i] : 0;
        }
        ImageIcon icono = new ImageIcon(bytes);
        if (icono.getIconWidth() <= 0 || icono.getIconHeight() <= 0) {
            lblImagenDisplay.setText("Imagen no valida");
            return;
        }
        Image imagenEscalada = icono.getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH);
        lblImagenDisplay.setText("");
        lblImagenDisplay.setIcon(new ImageIcon(imagenEscalada));
        lblImagenDisplay.setToolTipText("Imagen de recepcion");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblResumenOrden = new javax.swing.JLabel();
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
        btnGenerarPDF = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnVolver = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        lblImagen = new javax.swing.JLabel();
        lblImagenDisplay = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();
        lblTipoOrdenDisplay1 = new javax.swing.JLabel();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblResumenOrden.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblResumenOrden.setText("Resumen de orden");

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Producto", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProductosOrden);

        btnGenerarPDF.setBackground(new java.awt.Color(191, 64, 43));
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);

        btnVolver.setBackground(new java.awt.Color(191, 64, 43));
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        lblImagen.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblImagen.setText("Imagen:");

        lblImagenDisplay.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblImagenDisplay.setText("NA");

        lblImagen1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblImagen1.setText("Estado:");

        lblTipoOrdenDisplay1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblTipoOrdenDisplay1.setText("NA");

        javax.swing.GroupLayout panelRedondeado1Layout = new javax.swing.GroupLayout(panelRedondeado1);
        panelRedondeado1.setLayout(panelRedondeado1Layout);
        panelRedondeado1Layout.setHorizontalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(lblImagen1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoOrdenDisplay1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addComponent(lblTipoOrden)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTipoOrdenDisplay))
                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblFechaOrden)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFechaOrdenDisplay))
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblFechaEntrega)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFechaEntregaDisplay))
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblFolio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFolioDisplay)))
                                .addGap(135, 135, 135)
                                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addComponent(lblSucursal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblSucursalDisplay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblComentarios))
                                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                                .addComponent(lblImagen)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblImagenDisplay))
                                            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                                                .addComponent(lblProveedor)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblProveedorDisplay)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
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
                            .addComponent(lblFolioDisplay)
                            .addComponent(lblImagen)
                            .addComponent(lblImagenDisplay))
                        .addGap(18, 18, 18)
                        .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoOrden)
                            .addComponent(lblTipoOrdenDisplay))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImagen1)
                    .addComponent(lblTipoOrdenDisplay1))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblResumenOrden)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblResumenOrden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
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

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        if (controlador != null) {
            String folio = orden != null ? orden.getFolio() : "Orden";
            controlador.exportarReporteIndividual(tblProductosOrden, "Productos de la orden " + texto(folio));
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if (controlador != null) {
            controlador.volverAOrdenes();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnGenerarPDF;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblComentarios;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblFechaEntregaDisplay;
    private javax.swing.JLabel lblFechaOrden;
    private javax.swing.JLabel lblFechaOrdenDisplay;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JLabel lblFolioDisplay;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblImagenDisplay;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblProveedorDisplay;
    private javax.swing.JLabel lblResumenOrden;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JLabel lblSucursalDisplay;
    private javax.swing.JLabel lblTipoOrden;
    private javax.swing.JLabel lblTipoOrdenDisplay;
    private javax.swing.JLabel lblTipoOrdenDisplay1;
    private itson.org.ghosttracks.utilerias.PanelRedondeado panelRedondeado1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblProductosOrden;
    private javax.swing.JTextArea txtaComentariosDisplay;
    // End of variables declaration//GEN-END:variables
}
