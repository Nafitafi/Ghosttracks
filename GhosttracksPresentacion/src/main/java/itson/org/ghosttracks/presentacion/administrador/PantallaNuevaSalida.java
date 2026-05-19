package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoSalidaDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.RazonSalidaDTO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PantallaNuevaSalida extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private final List<SucursalDTO> sucursales = new ArrayList<>();
    private final List<ProductoDTO> productosDisponibles = new ArrayList<>();
    private final List<ProductoSalidaDTO> productosSalida = new ArrayList<>();
    private ProductoDTO productoSeleccionado;

    public PantallaNuevaSalida() {
        initComponents();
        prepararTabla();
    }

    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
        if (controlador != null) {
            controlador.inicializarFormularioNuevaSalida(this);
        }
    }

    public void configurarComponentesDinamicos(List<SucursalDTO> sucursales, List<RazonSalidaDTO> razones) {
        this.sucursales.clear();
        this.sucursales.addAll(sucursales);

        DefaultComboBoxModel<String> modeloSucursales = new DefaultComboBoxModel<>();
        for (SucursalDTO sucursal : sucursales) {
            modeloSucursales.addElement(sucursal.getNombre());
        }
        cbxSucursal.setModel(modeloSucursales);

        DefaultComboBoxModel<String> modeloRazones = new DefaultComboBoxModel<>();
        for (RazonSalidaDTO razon : razones) {
            modeloRazones.addElement(razon.name());
        }
        cbxRazon.setModel(modeloRazones);
    }

    public void cargarProductosDisponibles(List<ProductoDTO> productos) {
        this.productosDisponibles.clear();
        this.productosDisponibles.addAll(productos);
    }

    private void prepararTabla() {
        ((DefaultTableModel) tblProductos.getModel()).setRowCount(0);
        EliminarProductoCell renderEditor = new EliminarProductoCell();
        tblProductos.getColumnModel().getColumn(3).setCellRenderer(renderEditor);
        tblProductos.getColumnModel().getColumn(3).setCellEditor(renderEditor);
    }
    
    
    private class EliminarProductoCell extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JButton boton = new JButton("Quitar");
        private int fila;

        EliminarProductoCell() {
            boton.setFocusPainted(false);
            boton.setOpaque(true);

            boton.addActionListener(e -> {
                int filaAEliminar = fila;
                fireEditingCanceled(); 
                
                quitarProducto(filaAEliminar);
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
            return "Quitar";
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblRegistroSalida = new javax.swing.JLabel();
        panelRedondeado1 = new itson.org.ghosttracks.utilerias.PanelRedondeado();
        cbxSucursal = new javax.swing.JComboBox<>();
        cbxRazon = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaComentarios = new javax.swing.JTextArea();
        btnBuscarProducto = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        lblCantidad = new javax.swing.JLabel();
        tctCantidad = new itson.org.ghosttracks.utilerias.TextFieldRedondeado();
        lblProducto = new javax.swing.JLabel();
        lblProductoDisplay = new javax.swing.JLabel();
        btnAgregarProducto = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnCancelar = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnRegistrar = new itson.org.ghosttracks.utilerias.BotonRedondeado();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblRegistroSalida.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblRegistroSalida.setText("Registrar salida de mercancía");

        panelRedondeado1.setBackground(new java.awt.Color(217, 217, 217));

        cbxSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSucursal.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursal"));

        cbxRazon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRazon.setBorder(javax.swing.BorderFactory.createTitledBorder("Razón"));

        txtaComentarios.setColumns(20);
        txtaComentarios.setRows(5);
        txtaComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Comentarios"));
        jScrollPane1.setViewportView(txtaComentarios);

        btnBuscarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnBuscarProducto.setText("Buscar producto");
        btnBuscarProducto.addActionListener(this::btnBuscarProductoActionPerformed);

        lblCantidad.setText("Cantidad:");

        lblProducto.setText("Producto:");

        lblProductoDisplay.setText("ninguno");

        btnAgregarProducto.setBackground(new java.awt.Color(181, 181, 181));
        btnAgregarProducto.setText("Agregar producto");
        btnAgregarProducto.addActionListener(this::btnAgregarProductoActionPerformed);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Producto", "Cantidad", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProductos);

        btnCancelar.setBackground(new java.awt.Color(191, 64, 43));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar registro");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnRegistrar.setBackground(new java.awt.Color(191, 64, 43));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Realizar registro");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        javax.swing.GroupLayout panelRedondeado1Layout = new javax.swing.GroupLayout(panelRedondeado1);
        panelRedondeado1.setLayout(panelRedondeado1Layout);
        panelRedondeado1Layout.setHorizontalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelRedondeado1Layout.createSequentialGroup()
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblProducto)
                            .addGap(18, 18, 18)
                            .addComponent(lblProductoDisplay)
                            .addGap(40, 40, 40)
                            .addComponent(lblCantidad)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tctCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelRedondeado1Layout.createSequentialGroup()
                            .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelRedondeado1Layout.setVerticalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto)
                    .addComponent(lblProductoDisplay)
                    .addComponent(lblCantidad)
                    .addComponent(tctCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistroSalida)
                    .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblRegistroSalida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRedondeado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(tctCantidad.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numerica.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        productosSalida.add(new ProductoSalidaDTO(productoSeleccionado, cantidad));
        recargarTablaProductos();
        productoSeleccionado = null;
        lblProductoDisplay.setText("ninguno");
        tctCantidad.setText("");
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (controlador == null) {
            return;
        }
        if (cbxSucursal.getSelectedIndex() < 0 || cbxSucursal.getSelectedIndex() >= sucursales.size()) {
            JOptionPane.showMessageDialog(this, "Selecciona una sucursal.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (productosSalida.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos un producto.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RazonSalidaDTO razon = RazonSalidaDTO.valueOf(String.valueOf(cbxRazon.getSelectedItem()));
        controlador.registrarSalidaNueva(
                sucursales.get(cbxSucursal.getSelectedIndex()),
                razon,
                txtaComentarios.getText(),
                new ArrayList<>(productosSalida));
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        if (productosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos disponibles.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] nombres = productosDisponibles.stream()
                .map(producto -> producto.getNombre() + " (stock: " + producto.getStock() + ")")
                .toArray(String[]::new);
        String seleccion = (String) JOptionPane.showInputDialog(this, "Producto:", "Buscar producto",
                JOptionPane.PLAIN_MESSAGE, null, nombres, nombres[0]);
        if (seleccion == null) {
            return;
        }
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equals(seleccion)) {
                productoSeleccionado = productosDisponibles.get(i);
                lblProductoDisplay.setText(productoSeleccionado.getNombre());
                break;
            }
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void quitarProducto(int filaVista) {
        int filaModelo = tblProductos.convertRowIndexToModel(filaVista);
        if (filaModelo >= 0 && filaModelo < productosSalida.size()) {
            productosSalida.remove(filaModelo);
            recargarTablaProductos();
        }
    }

    private void recargarTablaProductos() {
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);
        for (ProductoSalidaDTO productoSalida : productosSalida) {
            ProductoDTO producto = productoSalida.getProducto();
            modelo.addRow(new Object[]{
                producto != null ? producto.getIdProducto() : "NA",
                producto != null ? producto.getNombre() : "NA",
                productoSalida.getCantidad(),
                "Quitar"
            });
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (controlador != null) {
            controlador.volverASalidas();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnAgregarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnBuscarProducto;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnCancelar;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnRegistrar;
    private javax.swing.JComboBox<String> cbxRazon;
    private javax.swing.JComboBox<String> cbxSucursal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProductoDisplay;
    private javax.swing.JLabel lblRegistroSalida;
    private itson.org.ghosttracks.utilerias.PanelRedondeado panelRedondeado1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblProductos;
    private itson.org.ghosttracks.utilerias.TextFieldRedondeado tctCantidad;
    private javax.swing.JTextArea txtaComentarios;
    // End of variables declaration//GEN-END:variables
}
