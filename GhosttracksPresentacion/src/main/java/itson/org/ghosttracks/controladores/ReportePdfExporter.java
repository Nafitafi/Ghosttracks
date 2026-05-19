/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.controladores;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nafbr
 */
public class ReportePdfExporter {

    private static final Font FUENTE_TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
    private static final Font FUENTE_TEXTO = FontFactory.getFont(FontFactory.HELVETICA, 11);
    private static final Font FUENTE_ENCABEZADO_TABLA = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE);
    private static final Font FUENTE_CELDA_TABLA = FontFactory.getFont(FontFactory.HELVETICA, 9);
    private static final BaseColor COLOR_ENCABEZADO_TABLA = new BaseColor(191, 64, 43);

    public boolean exportarTabla(JTable tabla, String titulo, String filtros, boolean incluirFiltros)
            throws IOException, DocumentException {
        List<Integer> columnasVisibles = columnasExportables(tabla);
        if (columnasVisibles.isEmpty()) {
            throw new DocumentException("No hay columnas exportables.");
        }

        File destino = seleccionarDestinoPdf(titulo);
        if (destino == null) {
            return false;
        }

        Document documento = new Document(PageSize.LETTER.rotate(), 36, 36, 36, 36);
        try (FileOutputStream salida = new FileOutputStream(destino)) {
            PdfWriter.getInstance(documento, salida);
            documento.open();
            try {
                documento.add(new Paragraph(titulo, FUENTE_TITULO));
                documento.add(new Paragraph("Fecha de generación: " + fechaReporteEnEspanol(), FUENTE_TEXTO));
                if (incluirFiltros) {
                    documento.add(new Paragraph("Filtros aplicados: " + filtrosTexto(filtros), FUENTE_TEXTO));
                }
                documento.add(Chunk.NEWLINE);
                documento.add(crearTablaPdf(tabla, columnasVisibles));
                return true;
            } finally {
                if (documento.isOpen()) {
                    documento.close();
                }
            }
        }
    }

    private File seleccionarDestinoPdf(String titulo) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar reporte PDF");
        selector.setFileFilter(new FileNameExtensionFilter("Archivo PDF", "pdf"));
        selector.setSelectedFile(new File(nombreArchivoPdf(titulo)));
        if (selector.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase(Locale.ROOT).endsWith(".pdf")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".pdf");
        }
        return archivo;
    }

    private String nombreArchivoPdf(String titulo) {
        String limpio = titulo == null ? "reporte" : titulo.replaceAll("[^a-zA-Z0-9_-]+", "_");
        return limpio + ".pdf";
    }

    private PdfPTable crearTablaPdf(JTable tabla, List<Integer> columnasVisibles) throws DocumentException {
        PdfPTable tablaPdf = new PdfPTable(columnasVisibles.size());
        tablaPdf.setWidthPercentage(100);
        agregarEncabezadosPdf(tablaPdf, tabla.getColumnModel(), columnasVisibles);
        agregarFilasPdf(tablaPdf, tabla, columnasVisibles);
        return tablaPdf;
    }

    private List<Integer> columnasExportables(JTable tabla) {
        TableColumnModel columnas = tabla.getColumnModel();
        List<Integer> columnasVisibles = new ArrayList<>();
        for (int colVista = 0; colVista < columnas.getColumnCount(); colVista++) {
            TableColumn columna = columnas.getColumn(colVista);
            String titulo = String.valueOf(columna.getHeaderValue());
            if (!"Acciones".equalsIgnoreCase(titulo)) {
                columnasVisibles.add(colVista);
            }
        }
        return columnasVisibles;
    }

    private void agregarEncabezadosPdf(PdfPTable tablaPdf, TableColumnModel columnas, List<Integer> columnasVisibles) {
        for (Integer colVista : columnasVisibles) {
            PdfPCell celda = new PdfPCell(new Phrase(
                    String.valueOf(columnas.getColumn(colVista).getHeaderValue()), FUENTE_ENCABEZADO_TABLA));
            celda.setBackgroundColor(COLOR_ENCABEZADO_TABLA);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(5);
            tablaPdf.addCell(celda);
        }
    }

    private void agregarFilasPdf(PdfPTable tablaPdf, JTable tabla, List<Integer> columnasVisibles) {
        for (int filaVista = 0; filaVista < tabla.getRowCount(); filaVista++) {
            for (Integer colVista : columnasVisibles) {
                PdfPCell celda = new PdfPCell(new Phrase(
                        String.valueOf(tabla.getValueAt(filaVista, colVista)), FUENTE_CELDA_TABLA));
                celda.setPadding(4);
                tablaPdf.addCell(celda);
            }
        }
    }

    private String fechaReporteEnEspanol() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "d 'de' MMMM 'de' yyyy, HH:mm", new Locale("es", "MX"));
        return LocalDateTime.now().format(formato);
    }

    private String filtrosTexto(String filtros) {
        return filtros == null || filtros.isBlank() ? "todos" : filtros;
    }
}
