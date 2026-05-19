/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.infraestructura;

import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author nafbr
 */
public class NotificacionEmailStrategy implements IComunicacionProveedor {

    public static final String CORREO_DESTINO = "nahomi.figueroa262728@potros.itson.edu.mx";
    private static final Path DIRECTORIO_SALIDA = Path.of("notificaciones-proveedores");
    private static final DateTimeFormatter FORMATO_ARCHIVO = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy HH:mm", new Locale("es", "MX"));

    @Override
    public boolean notificar(OrdenDTO orden) {
        if (orden == null || orden.getFolio() == null || orden.getProveedor() == null) {
            return false;
        }
        try {
            Files.createDirectories(DIRECTORIO_SALIDA);
            Path destino = DIRECTORIO_SALIDA.resolve(nombreArchivo(orden));
            Files.writeString(destino, construirMensaje(orden), StandardCharsets.UTF_8);
            System.out.println("[Proveedor Email] Notificacion simulada para " + CORREO_DESTINO
                    + " generada en " + destino.toAbsolutePath());
            return true;
        } catch (IOException ex) {
            System.err.println("[Proveedor Email] No se pudo generar la notificacion: " + ex.getMessage());
            return false;
        }
    }

    private String nombreArchivo(OrdenDTO orden) {
        String folioSeguro = orden.getFolio().replaceAll("[^a-zA-Z0-9_-]+", "_");
        return "orden-" + folioSeguro + "-" + LocalDateTime.now().format(FORMATO_ARCHIVO) + ".eml";
    }

    private String construirMensaje(OrdenDTO orden) {
        String asunto = "Nueva orden de abastecimiento " + orden.getFolio();
        return "To: " + CORREO_DESTINO + System.lineSeparator()
                + "Subject: " + asunto + System.lineSeparator()
                + "Content-Type: text/plain; charset=UTF-8" + System.lineSeparator()
                + System.lineSeparator()
                + "Se ha generado una nueva orden para proveedor." + System.lineSeparator()
                + System.lineSeparator()
                + "Folio: " + texto(orden.getFolio()) + System.lineSeparator()
                + "Proveedor: " + texto(orden.getProveedor().getNombreProveedor()) + System.lineSeparator()
                + "Sucursal destino: " + (orden.getSucursal() != null ? texto(orden.getSucursal().getNombre()) : "Sin sucursal")
                + System.lineSeparator()
                + "Tipo: " + texto(orden.getTipoOrden()) + System.lineSeparator()
                + "Estado: " + texto(orden.getEstadoOrden()) + System.lineSeparator()
                + "Fecha estimada: " + (orden.getFechaEntregaEst() != null ? orden.getFechaEntregaEst() : "Sin definir")
                + System.lineSeparator()
                + "Fecha de notificacion: " + LocalDateTime.now().format(FORMATO_FECHA) + System.lineSeparator()
                + "Total: " + NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(orden.getTotal() != null ? orden.getTotal() : 0.0)
                + System.lineSeparator()
                + System.lineSeparator()
                + "Productos:" + System.lineSeparator()
                + productosTexto(orden)
                + System.lineSeparator()
                + "Comentarios: " + texto(orden.getComentarios()) + System.lineSeparator();
    }

    private String productosTexto(OrdenDTO orden) {
        if (orden.getProductosOrden() == null || orden.getProductosOrden().isEmpty()) {
            return "- Sin productos" + System.lineSeparator();
        }
        StringBuilder productos = new StringBuilder();
        for (ProductoOrdenDTO producto : orden.getProductosOrden()) {
            String nombre = producto.getProducto() != null ? producto.getProducto().getNombre() : "Producto";
            productos.append("- ")
                    .append(nombre)
                    .append(" | cantidad: ")
                    .append(producto.getCantidadProducto())
                    .append(" | subtotal: ")
                    .append(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(producto.getImporteTotal()))
                    .append(System.lineSeparator());
        }
        return productos.toString();
    }

    private String texto(Object valor) {
        return valor == null ? "N/A" : String.valueOf(valor);
    }
}
