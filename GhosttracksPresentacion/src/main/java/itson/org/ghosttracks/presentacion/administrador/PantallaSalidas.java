package itson.org.ghosttracks.presentacion.administrador;

import itson.org.ghosttracks.controladores.ControlAbastecimiento;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.enums.RazonSalidaDTO;
import java.awt.Component;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author nafbr
 */
public class PantallaSalidas extends javax.swing.JPanel {

    private ControlAbastecimiento controlador;
    private List<SalidaDTO> salidasDesplegadas = new ArrayList<>();

    public PantallaSalidas() {
        initComponents();
    }

    public void setControlador(ControlAbastecimiento controlador) {
        this.controlador = controlador;
        if (controlador != null) {
            controlador.inicializarFiltrosYTablaSalidas(this);
        }
    }

    public void configurarComponentesDinamicos(List<RazonSalidaDTO> razones) {
        DefaultComboBoxModel<Object> modelRazon = new DefaultComboBoxModel<>();
        modelRazon.addElement("TODAS");
        for (RazonSalidaDTO razon : razones) {
            modelRazon.addElement(razon);
        }
        cbxRazon.setModel(modelRazon);
    }

    public void llenarTabla(List<SalidaDTO> salidas) {
        this.salidasDesplegadas = salidas;
        DefaultTableModel model = (DefaultTableModel) tblSalidas.getModel();
        model.setRowCount(0);
        for (SalidaDTO salida : salidas) {
            model.addRow(new Object[]{
                salida.getIdSalida(),
                salida.getFolio(),
                salida.getRazon(),
                salida.getComentarios() != null ? salida.getComentarios() : "",
                salida.getResumenProductos(),
                salida.getCantidadTotalProductos(),
                "Ver"
            });
        }
        configurarAccionVer();
    }

    private void configurarAccionVer() {
        tblSalidas.setRowHeight(32);
        for (int i = 0; i < tblSalidas.getColumnModel().getColumnCount(); i++) {
            TableColumn columna = tblSalidas.getColumnModel().getColumn(i);
            if ("Acciones".equals(columna.getHeaderValue())) {
                columna.setMinWidth(90);
                columna.setPreferredWidth(100);
                VerSalidaRenderEditor renderEditor = new VerSalidaRenderEditor();
                columna.setCellRenderer(renderEditor);
                columna.setCellEditor(renderEditor);
                break;
            }
        }
    }

    private String obtenerFiltrosReporte() {
        List<String> filtros = new ArrayList<>();
        Object razon = cbxRazon.getSelectedItem();
        if (razon instanceof RazonSalidaDTO) {
            filtros.add("Razon: " + razon);
        }
        if (dtpFechaInicio.getDate() != null) {
            filtros.add("Fecha inicio: " + dtpFechaInicio.getDate());
        }
        if (dtpFechaFin.getDate() != null) {
            filtros.add("Fecha fin: " + dtpFechaFin.getDate());
        }
        return filtros.isEmpty() ? "todos" : String.join("; ", filtros);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblRegistroSalida = new javax.swing.JLabel();
        cbxRazon = new javax.swing.JComboBox<>();
        dtpFechaInicio = new com.github.lgooddatepicker.components.DatePicker();
        dtpFechaFin = new com.github.lgooddatepicker.components.DatePicker();
        btnFiltrar = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalidas = new javax.swing.JTable();
        btnRegistrarSalida = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnGenerarPDF = new itson.org.ghosttracks.utilerias.BotonRedondeado();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));

        lblRegistroSalida.setText("Registro de salidas de mercancía");
        lblRegistroSalida.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N

        cbxRazon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRazon.setBorder(javax.swing.BorderFactory.createTitledBorder("Razón"));

        dtpFechaInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha inicio"));

        dtpFechaFin.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha fin"));

        btnFiltrar.setText("Filtrar salidas");
        btnFiltrar.setBackground(new java.awt.Color(191, 64, 43));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);

        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Folio", "Razón", "Comentarios", "Productos", "Cantidad", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSalidas);

        btnRegistrarSalida.setText("Registrar salida nueva");
        btnRegistrarSalida.setBackground(new java.awt.Color(191, 64, 43));
        btnRegistrarSalida.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarSalida.addActionListener(this::btnRegistrarSalidaActionPerformed);

        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.setBackground(new java.awt.Color(191, 64, 43));
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRegistroSalida)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(dtpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtpFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 432, Short.MAX_VALUE)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblRegistroSalida)
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dtpFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtpFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
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

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        if (controlador != null) {
            Object razon = cbxRazon.getSelectedItem();
            LocalDate inicio = dtpFechaInicio.getDate();
            LocalDate fin = dtpFechaFin.getDate();
            controlador.filtrarSalidas(this, razon, inicio, fin);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnRegistrarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSalidaActionPerformed
        if (controlador != null) {
            controlador.irAAgregarSalidaNueva();
        }
    }//GEN-LAST:event_btnRegistrarSalidaActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        if (controlador != null) {
            controlador.exportarReporteAgrupado(tblSalidas, "Registro de salidas", obtenerFiltrosReporte());
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed
    private class VerSalidaRenderEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        private final JButton btnVer = new JButton("Ver");
        private SalidaDTO salidaActual;

        VerSalidaRenderEditor() {
            panel.setOpaque(true);
            btnVer.addActionListener(e -> {
                fireEditingStopped();
                if (controlador != null && salidaActual != null) {
                    controlador.verDetalleSalida(salidaActual);
                }
            });
            panel.add(btnVer);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            configurarSalidaActual(table, row);
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return salidaActual;
        }

        private void configurarSalidaActual(JTable table, int row) {
            int filaModelo = table.convertRowIndexToModel(row);
            salidaActual = filaModelo >= 0 && filaModelo < salidasDesplegadas.size()
                    ? salidasDesplegadas.get(filaModelo)
                    : null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnFiltrar;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnGenerarPDF;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnRegistrarSalida;
    private javax.swing.JComboBox<Object> cbxRazon;
    private com.github.lgooddatepicker.components.DatePicker dtpFechaFin;
    private com.github.lgooddatepicker.components.DatePicker dtpFechaInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistroSalida;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblSalidas;
    // End of variables declaration//GEN-END:variables
}
