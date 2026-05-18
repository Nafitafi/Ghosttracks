package itson.org.ghosttracks.presentacion.administrador;


/**
 *
 * @author nafbr
 */
public class PantallaRegistroSalida extends javax.swing.JPanel {

    public PantallaRegistroSalida() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblRegistroSalida = new javax.swing.JLabel();
        panelRedondeado1 = new itson.org.ghosttracks.utilerias.PanelRedondeado();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnCancelar = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        btnGenerarPDF = new itson.org.ghosttracks.utilerias.BotonRedondeado();
        lblFechaOrden = new javax.swing.JLabel();
        lblFechaOrden1 = new javax.swing.JLabel();
        lblFechaOrden2 = new javax.swing.JLabel();
        lblFechaOrden3 = new javax.swing.JLabel();
        lblFechaOrden4 = new javax.swing.JLabel();
        lblFechaOrden5 = new javax.swing.JLabel();
        lblFechaOrden6 = new javax.swing.JLabel();
        lblFechaOrden7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        pnlPrincipal.setBackground(new java.awt.Color(237, 229, 222));
        pnlPrincipal.setEnabled(false);
        pnlPrincipal.setRequestFocusEnabled(false);

        lblRegistroSalida.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        lblRegistroSalida.setText("Registro de salida de mercancía");

        panelRedondeado1.setBackground(new java.awt.Color(217, 217, 217));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Producto", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProductos);

        btnCancelar.setBackground(new java.awt.Color(191, 64, 43));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Volver");

        btnGenerarPDF.setBackground(new java.awt.Color(191, 64, 43));
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);

        lblFechaOrden.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden.setText("Fecha:");

        lblFechaOrden1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden1.setText("NA");

        lblFechaOrden2.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden2.setText("Folio:");

        lblFechaOrden3.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden3.setText("NA");

        lblFechaOrden4.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden4.setText("Razón:");

        lblFechaOrden5.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden5.setText("NA");

        lblFechaOrden6.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden6.setText("Sucursal:");

        lblFechaOrden7.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        lblFechaOrden7.setText("NA");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(217, 217, 217));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel", 1, 18))); // NOI18N
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout panelRedondeado1Layout = new javax.swing.GroupLayout(panelRedondeado1);
        panelRedondeado1.setLayout(panelRedondeado1Layout);
        panelRedondeado1Layout.setHorizontalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495)
                        .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(panelRedondeado1Layout.createSequentialGroup()
                        .addComponent(lblFechaOrden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaOrden1)
                        .addGap(60, 60, 60)
                        .addComponent(lblFechaOrden2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaOrden3)
                        .addGap(85, 85, 85)
                        .addComponent(lblFechaOrden4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaOrden5)
                        .addGap(74, 74, 74)
                        .addComponent(lblFechaOrden6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaOrden7))
                    .addComponent(jScrollPane3))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelRedondeado1Layout.setVerticalGroup(
            panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRedondeado1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaOrden)
                    .addComponent(lblFechaOrden1)
                    .addComponent(lblFechaOrden2)
                    .addComponent(lblFechaOrden3)
                    .addComponent(lblFechaOrden4)
                    .addComponent(lblFechaOrden5)
                    .addComponent(lblFechaOrden6)
                    .addComponent(lblFechaOrden7))
                .addGap(58, 58, 58)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRedondeado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnCancelar;
    private itson.org.ghosttracks.utilerias.BotonRedondeado btnGenerarPDF;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFechaOrden;
    private javax.swing.JLabel lblFechaOrden1;
    private javax.swing.JLabel lblFechaOrden2;
    private javax.swing.JLabel lblFechaOrden3;
    private javax.swing.JLabel lblFechaOrden4;
    private javax.swing.JLabel lblFechaOrden5;
    private javax.swing.JLabel lblFechaOrden6;
    private javax.swing.JLabel lblFechaOrden7;
    private javax.swing.JLabel lblRegistroSalida;
    private itson.org.ghosttracks.utilerias.PanelRedondeado panelRedondeado1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
