package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nafbr
 */
public class PantallaNuevaOrdenProveedor extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private List<ProductoDTO> productosDisponibles = new ArrayList<>();
    private final List<ProductoOrdenDTO> productosOrden = new ArrayList<>();
    private ProductoDTO productoSeleccionado;

    public PantallaNuevaOrdenProveedor() {
        initComponents();
        prepararTabla();
        dtpFecha.setDate(LocalDate.now().plusDays(10));
        refrescarTablaProductos();
    }
    
    private void prepararTabla() {
        EliminarProductoCell renderEditor = new EliminarProductoCell();
        tblProductosOrden.getColumnModel().getColumn(4).setCellRenderer(renderEditor);
        tblProductosOrden.getColumnModel().getColumn(4).setCellEditor(renderEditor);
    }
    

    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
        if (controlador != null) {
            controlador.inicializarFormularioNuevaOrden(this);
        }
    }

    public void configurarComponentesDinamicos(List<ProveedorDTO> proveedores, List<SucursalDTO> sucursales, List<TipoOrdenDTO> tipos) {
        DefaultComboBoxModel<Object> modeloProveedores = new DefaultComboBoxModel<>();
        for (ProveedorDTO proveedor : proveedores) {
            modeloProveedores.addElement(proveedor);
        }
        cbxProveedor.setModel(modeloProveedores);
        cbxProveedor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ProveedorDTO proveedor) {
                    value = proveedor.getNombreProveedor();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        DefaultComboBoxModel<Object> modeloSucursales = new DefaultComboBoxModel<>();
        for (SucursalDTO sucursal : sucursales) {
            modeloSucursales.addElement(sucursal);
        }
        cbxSucursal.setModel(modeloSucursales);
        cbxSucursal.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof SucursalDTO sucursal) {
                    value = sucursal.getNombre();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        DefaultComboBoxModel<Object> modeloTipos = new DefaultComboBoxModel<>();
        for (TipoOrdenDTO tipo : tipos) {
            modeloTipos.addElement(tipo);
        }
        cbxTipoOrden.setModel(modeloTipos);
    }

    public void cargarProductosDisponibles(List<ProductoDTO> productos) {
        this.productosDisponibles = productos != null ? productos : new ArrayList<>();
    }

    private void buscarProducto() {
        if (productosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos disponibles.", "Productos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JComboBox<ProductoDTO> comboProductos = new JComboBox<>(productosDisponibles.toArray(new ProductoDTO[0]));
        comboProductos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ProductoDTO producto) {
                    double precio = producto.getPrecio() != null ? producto.getPrecio() : 0.0;
                    value = producto.getNombre() + " - $" + String.format("%.2f", precio);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.add(comboProductos, BorderLayout.CENTER);
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Seleccionar producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (opcion == JOptionPane.OK_OPTION && comboProductos.getSelectedItem() instanceof ProductoDTO producto) {
            productoSeleccionado = producto;
            lblProductoDisplay.setText(producto.getNombre());
        }
    }

    private void agregarProductoSeleccionado() {
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto primero.", "Producto requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidadProducto.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero entero.", "Cantidad invalida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Cantidad invalida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProductoOrdenDTO existente = buscarProductoEnOrden(productoSeleccionado);
        if (existente != null) {
            existente.setCantidadProducto(existente.getCantidadProducto() + cantidad);
        } else {
            double precio = productoSeleccionado.getPrecio() != null ? productoSeleccionado.getPrecio() : 0.0;
            productosOrden.add(new ProductoOrdenDTO(productoSeleccionado, cantidad, precio));
        }

        productoSeleccionado = null;
        lblProductoDisplay.setText("ninguno");
        txtCantidadProducto.setText("");
        refrescarTablaProductos();
    }

    private ProductoOrdenDTO buscarProductoEnOrden(ProductoDTO producto) {
        for (ProductoOrdenDTO item : productosOrden) {
            if (item.getProducto() != null
                    && item.getProducto().getIdProducto() != null
                    && item.getProducto().getIdProducto().equals(producto.getIdProducto())) {
                return item;
            }
        }
        return null;
    }

    private void refrescarTablaProductos() {
        DefaultTableModel modelo = (DefaultTableModel) tblProductosOrden.getModel();
        modelo.setRowCount(0);
        for (ProductoOrdenDTO item : productosOrden) {
            ProductoDTO producto = item.getProducto();
            modelo.addRow(new Object[]{
                producto != null ? producto.getIdProducto() : "NA",
                producto != null ? producto.getNombre() : "NA",
                item.getCantidadProducto(),
                String.format("$%.2f", item.getImporteTotal()),
                "Eliminar"
            });
        }
    }

    private void realizarOrden() {
        Object proveedor = cbxProveedor.getSelectedItem();
        Object sucursal = cbxSucursal.getSelectedItem();
        Object tipo = cbxTipoOrden.getSelectedItem();
        LocalDate fecha = dtpFecha.getDate();

        if (!(proveedor instanceof ProveedorDTO) || !(sucursal instanceof SucursalDTO) || !(tipo instanceof TipoOrdenDTO)) {
            JOptionPane.showMessageDialog(this, "Selecciona proveedor, sucursal y tipo de orden.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Selecciona la fecha estimada de entrega.", "Fecha requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (productosOrden.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos un producto a la orden.", "Productos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        controlador.registrarOrdenNueva(this, (ProveedorDTO) proveedor, (SucursalDTO) sucursal, (TipoOrdenDTO) tipo,
                fecha, txaComentarios.getText(), new ArrayList<>(productosOrden));
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

        lblOrdenesProveedores.setText("Realizar nueva orden");
        lblOrdenesProveedores.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N

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

        btnBuscarProducto.setText("Buscar producto");
        btnBuscarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnBuscarProducto.addActionListener(this::btnBuscarProductoActionPerformed);

        lblCantidad.setText("Cantidad: ");

        txtCantidadProducto.addActionListener(this::txtCantidadProductoActionPerformed);

        btnAgregarProducto.setText("Agregar producto");
        btnAgregarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnAgregarProducto.addActionListener(this::btnAgregarProductoActionPerformed);

        tblProductosOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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

        btnCancelarOrden.setText("Cancelar Orden");
        btnCancelarOrden.setBackground(new java.awt.Color(191, 64, 43));
        btnCancelarOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarOrden.addActionListener(this::btnCancelarOrdenActionPerformed);

        btnRealizarOrden.setText("Realizar Orden");
        btnRealizarOrden.setBackground(new java.awt.Color(191, 64, 43));
        btnRealizarOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarOrden.addActionListener(this::btnRealizarOrdenActionPerformed);

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

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        agregarProductoSeleccionado();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        buscarProducto();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnCancelarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOrdenActionPerformed
        if (controlador != null) {
            controlador.volverAOrdenes();
        }
    }//GEN-LAST:event_btnCancelarOrdenActionPerformed

    private void btnRealizarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarOrdenActionPerformed
        if (controlador != null) {
            realizarOrden();
        }
    }//GEN-LAST:event_btnRealizarOrdenActionPerformed

    private class EliminarProductoCell extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JButton boton = new JButton("Eliminar");
        private int fila;

        EliminarProductoCell() {
            boton.setFocusPainted(false);
            boton.setOpaque(true);

            boton.addActionListener(e -> {
                int filaModelo = tblProductosOrden.convertRowIndexToModel(fila);
                
                fireEditingCanceled(); 
                
                if (filaModelo >= 0 && filaModelo < productosOrden.size()) {
                    productosOrden.remove(filaModelo);
                    refrescarTablaProductos();
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return boton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.fila = row;
            return boton;
        }

        @Override
        public Object getCellEditorValue() {
            return "Eliminar";
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnAgregarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnBuscarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnCancelarOrden;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnRealizarOrden;
    private javax.swing.JComboBox<Object> cbxProveedor;
    private javax.swing.JComboBox<Object> cbxSucursal;
    private javax.swing.JComboBox<Object> cbxTipoOrden;
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
