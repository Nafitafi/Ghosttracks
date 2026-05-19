package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import java.awt.Component;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nafbr
 */
public class PantallaOrdenesProveedores extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private List<OrdenDTO> ordenesDesplegadas = new ArrayList<>();

    public PantallaOrdenesProveedores() {
        initComponents();
    }

    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
        if (controlador != null) {
            controlador.inicializarFiltrosYTabla(this);
        }
    }

    public void configurarComponentesDinamicos(List<ProveedorDTO> proveedores, List<EstadoOrdenDTO> estados, List<TipoOrdenDTO> tipos) {
        // Carga de Proveedores
        DefaultComboBoxModel<Object> modelProv = new DefaultComboBoxModel<>();
        modelProv.addElement("TODOS");
        for (ProveedorDTO p : proveedores) {
            modelProv.addElement(p);
        }
        cmbxProovedor.setModel(modelProv);
        cmbxProovedor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ProveedorDTO proveedor) {
                    value = proveedor.getNombreProveedor();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        DefaultComboBoxModel<Object> modelEstado = new DefaultComboBoxModel<>();
        modelEstado.addElement("TODOS");
        for (EstadoOrdenDTO e : estados) {
            modelEstado.addElement(e);
        }
        cmbxEstado.setModel(modelEstado);

        DefaultComboBoxModel<Object> modelTipo = new DefaultComboBoxModel<>();
        modelTipo.addElement("TODOS");
        for (TipoOrdenDTO t : tipos) {
            modelTipo.addElement(t);
        }
        cmbxTipo.setModel(modelTipo);
    }

    public void llenarTabla(List<OrdenDTO> listaOrdenes) {
        this.ordenesDesplegadas = listaOrdenes;
        DefaultTableModel model = (DefaultTableModel) tblOrdenes.getModel();
        model.setRowCount(0);

        for (OrdenDTO o : listaOrdenes) {
            Object[] row = new Object[]{
                o.getIdOrden(),
                o.getFolio(),
                o.getTipoOrden(),
                o.getProveedor() != null ? o.getProveedor().getNombreProveedor() : "N/A",
                o.getComentarios() != null ? o.getComentarios() : "",
                String.format("$%.2f", o.getTotal()),
                o.getFechaEntregaEst() != null ? o.getFechaEntregaEst().toString() : "Sin definir",
                o.getEstadoOrden(),
                o
            };
            model.addRow(row);
        }

        if (tblOrdenes.getColumnModel().getColumnCount() == 9) {
            tblOrdenes.removeColumn(tblOrdenes.getColumnModel().getColumn(0));
        }

        int indiceColumnaAcciones = -1;
        for (int i = 0; i < tblOrdenes.getColumnModel().getColumnCount(); i++) {
            if ("Acciones".equals(tblOrdenes.getColumnModel().getColumn(i).getHeaderValue())) {
                indiceColumnaAcciones = i;
                break;
            }
        }

        if (indiceColumnaAcciones != -1) {
            AccionesCellRenderEditor renderEditor = new AccionesCellRenderEditor(this.controlador, this);
            tblOrdenes.getColumnModel().getColumn(indiceColumnaAcciones).setCellRenderer(renderEditor);
            tblOrdenes.getColumnModel().getColumn(indiceColumnaAcciones).setCellEditor(renderEditor);
            tblOrdenes.getColumnModel().getColumn(indiceColumnaAcciones).setMinWidth(245);
            tblOrdenes.getColumnModel().getColumn(indiceColumnaAcciones).setPreferredWidth(270);
        }

        // Ajustar la altura de las filas para que los 3 botones se desplieguen estéticamente
        tblOrdenes.setRowHeight(42);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblOrdenesProveedores = new javax.swing.JLabel();
        cmbxProovedor = new javax.swing.JComboBox<>();
        cmbxEstado = new javax.swing.JComboBox<>();
        cmbxTipo = new javax.swing.JComboBox<>();
        dpFechaInicio = new com.github.lgooddatepicker.components.DatePicker();
        dpFechaFIn = new com.github.lgooddatepicker.components.DatePicker();
        btnFiltrarOrdenes = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        scrlpTabla = new javax.swing.JScrollPane();
        tblOrdenes = new javax.swing.JTable();
        btnGenerarPDF = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnAgregarOrden = new itson.org.ghosttracks.utilerias.BotonRedondeado();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblOrdenesProveedores.setText("Ordenes a proveedores");
        lblOrdenesProveedores.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N

        cmbxProovedor.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor:"));

        cmbxEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado:"));

        cmbxTipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo:"));

        dpFechaInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha inicio:"));

        dpFechaFIn.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha fin:"));

        btnFiltrarOrdenes.setText("Filtrar ordenes");
        btnFiltrarOrdenes.setBackground(new java.awt.Color(191, 64, 43));
        btnFiltrarOrdenes.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarOrdenes.addActionListener(this::btnFiltrarOrdenesActionPerformed);

        tblOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Folio", "Tipo orden", "Proveedor", "Comentarios", "Total", "Fecha Entrega estimada", "Estado", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpTabla.setViewportView(tblOrdenes);

        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.setBackground(new java.awt.Color(191, 64, 43));
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);

        btnAgregarOrden.setText("Agregar orden nueva");
        btnAgregarOrden.setBackground(new java.awt.Color(191, 64, 43));
        btnAgregarOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarOrden.addActionListener(this::btnAgregarOrdenActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(cmbxProovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dpFechaFIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnFiltrarOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblOrdenesProveedores)))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrlpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblOrdenesProveedores)
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbxProovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dpFechaFIn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFiltrarOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
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

    private void btnFiltrarOrdenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarOrdenesActionPerformed
        if (controlador != null) {
            Object prov = cmbxProovedor.getSelectedItem();
            Object est = cmbxEstado.getSelectedItem();
            Object tipo = cmbxTipo.getSelectedItem();
            LocalDate inicio = dpFechaInicio.getDate();
            LocalDate fin = dpFechaFIn.getDate();

            controlador.filtrarOrdenes(this, prov, est, tipo, inicio, fin);
        }
    }//GEN-LAST:event_btnFiltrarOrdenesActionPerformed

    private void btnAgregarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOrdenActionPerformed
       if (controlador != null) {
            controlador.irAAgregarOrdenNueva();
        }
    }//GEN-LAST:event_btnAgregarOrdenActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
       if (controlador != null) {
            controlador.exportarReporteAgrupado(tblOrdenes, "Ordenes a proveedores", obtenerFiltrosReporte());
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private String obtenerFiltrosReporte() {
        List<String> filtros = new ArrayList<>();
        Object proveedor = cmbxProovedor.getSelectedItem();
        Object estado = cmbxEstado.getSelectedItem();
        Object tipo = cmbxTipo.getSelectedItem();
        if (proveedor instanceof ProveedorDTO proveedorDTO) {
            filtros.add("Proveedor: " + proveedorDTO.getNombreProveedor());
        }
        if (estado instanceof EstadoOrdenDTO) {
            filtros.add("Estado: " + estado);
        }
        if (tipo instanceof TipoOrdenDTO) {
            filtros.add("Tipo: " + tipo);
        }
        if (dpFechaInicio.getDate() != null) {
            filtros.add("Fecha inicio: " + dpFechaInicio.getDate());
        }
        if (dpFechaFIn.getDate() != null) {
            filtros.add("Fecha fin: " + dpFechaFIn.getDate());
        }
        return filtros.isEmpty() ? "todos" : String.join("; ", filtros);
    }
    
    //Para renderizal los botones:
    public class AccionesCellRenderEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        private final JButton btnVer = new JButton("Ver");
        private final JButton btnConfirmar = new JButton("Confirmar");
        private final JButton btnCancelar = new JButton("Cancelar");
        private OrdenDTO ordenActual;

        public AccionesCellRenderEditor(ControlAbastecimiento controlador, PantallaOrdenesProveedores vista) {
            panel.setOpaque(true);

            // Ver Orden
            btnVer.addActionListener(e -> {
                fireEditingStopped(); // Detiene el modo edición de la celda en Swing
                if (controlador != null && ordenActual != null) {
                    controlador.verDetalleOrden(ordenActual);
                }
            });

            // Confirmar Orden
            btnConfirmar.addActionListener(e -> {
                fireEditingStopped();
                if (controlador != null && ordenActual != null) {
                    controlador.confirmarOrden(ordenActual, vista);
                }
            });

            // Acción: Cancelar Orden
            btnCancelar.addActionListener(e -> {
                fireEditingStopped();
                if (controlador != null && ordenActual != null) {
                    controlador.cancelarOrden(ordenActual, vista);
                }
            });

            panel.add(btnVer);
            panel.add(btnConfirmar);
            panel.add(btnCancelar);
        }

        private void configurarOrdenActual(JTable table, int row) {
            int filaModelo = table.convertRowIndexToModel(row);
            ordenActual = filaModelo >= 0 && filaModelo < ordenesDesplegadas.size()
                    ? ordenesDesplegadas.get(filaModelo)
                    : null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            configurarOrdenActual(table, row);
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return ordenActual;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnAgregarOrden;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnFiltrarOrdenes;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnGenerarPDF;
    private javax.swing.JComboBox<Object> cmbxEstado;
    private javax.swing.JComboBox<Object> cmbxProovedor;
    private javax.swing.JComboBox<Object> cmbxTipo;
    private com.github.lgooddatepicker.components.DatePicker dpFechaFIn;
    private com.github.lgooddatepicker.components.DatePicker dpFechaInicio;
    private javax.swing.JLabel lblOrdenesProveedores;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JScrollPane scrlpTabla;
    private javax.swing.JTable tblOrdenes;
    // End of variables declaration//GEN-END:variables
}
