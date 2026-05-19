package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nafbr
 */
public class PantallaConfirmacionRecepcion extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private OrdenDTO orden;
    private byte[] imagenRecepcion;
    private final List<ProductoOrdenDTO> productosOrden = new ArrayList<>();

    public PantallaConfirmacionRecepcion() {
        initComponents();
        configurarTablaProductosOrden();
    }

    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
    }

    public void cargarOrden(OrdenDTO orden) {
        this.orden = orden;
        this.imagenRecepcion = null;
        this.productosOrden.clear();
        if (orden == null) {
            return;
        }

        lblFechaOrdenDisplay.setText(orden.getFechaSolicitud() != null ? orden.getFechaSolicitud().toLocalDate().toString() : "NA");
        lblFechaEntregaDisplay.setText(orden.getFechaEntregaEst() != null ? orden.getFechaEntregaEst().toString() : "NA");
        lblFolioDisplay.setText(texto(orden.getFolio()));
        lblTipoOrdenDisplay.setText(orden.getTipoOrden() != null ? orden.getTipoOrden().toString() : "NA");
        lblSucursalDisplay.setText(orden.getSucursal() != null ? texto(orden.getSucursal().getNombre()) : "NA");
        lblProveedorDisplay.setText(orden.getProveedor() != null ? texto(orden.getProveedor().getNombreProveedor()) : "NA");
        txtaComentariosDisplay.setText(texto(orden.getComentarios()));

        DefaultTableModel modelo = (DefaultTableModel) tblProductosOrden.getModel();
        modelo.setRowCount(0);
        if (orden.getProductosOrden() != null) {
            for (ProductoOrdenDTO productoOrden : orden.getProductosOrden()) {
                productosOrden.add(productoOrden);
                ProductoDTO producto = productoOrden.getProducto();
                modelo.addRow(new Object[]{
                    producto != null ? producto.getIdProducto() : "NA",
                    producto != null ? texto(producto.getNombre()) : "NA",
                    productoOrden.getCantidadProducto(),
                    String.format("$%.2f", productoOrden.getImporteTotal()),
                    productoOrden.isRecibido()
                });
            }
        }
        configurarEstadoConfirmacion();
    }

    private String texto(String valor) {
        return valor != null && !valor.isBlank() ? valor : "NA";
    }

    private void configurarTablaProductosOrden() {
        tblProductosOrden.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Producto", "Cantidad", "Total", "Recibido"}) {
            private final Class<?>[] tipos = new Class<?>[]{
                String.class, String.class, Integer.class, String.class, Boolean.class
            };
            private final boolean[] editable = new boolean[]{
                false, false, false, false, true
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return tipos[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editable[columnIndex];
            }
        });

        JCheckBox editorCheckBox = new JCheckBox();
        editorCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        tblProductosOrden.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(editorCheckBox));
        tblProductosOrden.getColumnModel().getColumn(4).setCellRenderer(checkboxRenderer());
    }

    private TableCellRenderer checkboxRenderer() {
        return (table, value, isSelected, hasFocus, row, column) -> {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setSelected(Boolean.TRUE.equals(value));
            checkBox.setOpaque(true);
            checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return checkBox;
        };
    }
    
    private void configurarEstadoConfirmacion() {
        boolean puedeConfirmar = puedeConfirmarRecepcion();
        btnAgregarImagen.setText("Agregar imagen de recepcion");
        btnAgregarImagen.setEnabled(puedeConfirmar);
        btnConfirmarRecepcion.setEnabled(puedeConfirmar);
        tblProductosOrden.setEnabled(puedeConfirmar);
        if (!puedeConfirmar) {
            btnConfirmarRecepcion.setToolTipText("La orden ya fue recibida, cerrada o cancelada.");
        } else {
            btnConfirmarRecepcion.setToolTipText(null);
        }
    }

    private boolean puedeConfirmarRecepcion() {
        if (orden == null || orden.getEstadoOrden() == null) {
            return false;
        }
        return orden.getEstadoOrden() != EstadoOrdenDTO.RECIBIDO
                && orden.getEstadoOrden() != EstadoOrdenDTO.CERRADO
                && orden.getEstadoOrden() != EstadoOrdenDTO.CANCELADO;
    }

    private List<ProductoOrdenDTO> leerProductosRecibidos() {
        DefaultTableModel modelo = (DefaultTableModel) tblProductosOrden.getModel();
        for (int i = 0; i < modelo.getRowCount() && i < productosOrden.size(); i++) {
            productosOrden.get(i).setRecibido(Boolean.TRUE.equals(modelo.getValueAt(i, 4)));
        }
        return new ArrayList<>(productosOrden);
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
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);

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
        if (controlador != null) {
            controlador.volverAOrdenes();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConfirmarRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarRecepcionActionPerformed
        if (controlador == null || orden == null) {
            return;
        }
        List<ProductoOrdenDTO> productosActualizados = leerProductosRecibidos();
        boolean hayProductoRecibido = productosActualizados.stream().anyMatch(ProductoOrdenDTO::isRecibido);
        if (!hayProductoRecibido) {
            JOptionPane.showMessageDialog(this, "Marca al menos un producto como recibido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (imagenRecepcion == null || imagenRecepcion.length == 0) {
            JOptionPane.showMessageDialog(this, "Agrega una imagen de recepcion antes de confirmar.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        controlador.confirmarRecepcionOrden(orden, imagenRecepcion, productosActualizados);
    }//GEN-LAST:event_btnConfirmarRecepcionActionPerformed

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png", "bmp", "gif"));
        if (selector.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        try {
            imagenRecepcion = Files.readAllBytes(archivo.toPath());
            btnAgregarImagen.setText("Imagen cargada: " + archivo.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer la imagen seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

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
