package itson.org.ghosttracks.presentacion.administrador;


/**
 *
 * @author nafbr
 */
public class PantallaSalidas extends javax.swing.JPanel {

    public PantallaSalidas() {
        initComponents();
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

        lblRegistroSalida.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblRegistroSalida.setText("Registro de salidas de mercancÃ­a");

        cbxRazon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRazon.setBorder(javax.swing.BorderFactory.createTitledBorder("RazÃ³n"));

        dtpFechaInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha inicio"));

        dtpFechaFin.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha fin"));

        btnFiltrar.setBackground(new java.awt.Color(191, 64, 43));
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtrar salidas");

        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Folio", "RazÃ³n", "Comentarios", "Productos", "Cantidad", "Acciones"
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

        btnRegistrarSalida.setBackground(new java.awt.Color(191, 64, 43));
        btnRegistrarSalida.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarSalida.setText("Registrar salida nueva");

        btnGenerarPDF.setBackground(new java.awt.Color(191, 64, 43));
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar PDF");

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnFiltrar;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnGenerarPDF;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnRegistrarSalida;
    private javax.swing.JComboBox<String> cbxRazon;
    private com.github.lgooddatepicker.components.DatePicker dtpFechaFin;
    private com.github.lgooddatepicker.components.DatePicker dtpFechaInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistroSalida;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblSalidas;
    // End of variables declaration//GEN-END:variables
}
