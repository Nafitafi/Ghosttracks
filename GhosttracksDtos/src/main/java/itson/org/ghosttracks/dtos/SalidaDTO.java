package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.RazonSalidaDTO;
import java.time.LocalDate;
import java.util.List;

/**
 * @author nafbr
 */
public class SalidaDTO {

    private Long idSalida;
    private String folio;
    private LocalDate fechaSalida;
    private String comentarios;
    private RazonSalidaDTO razon;
    private SucursalDTO sucursal;
    private List<ProductoSalidaDTO> productos;

    public SalidaDTO() {
    }

    public Long getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(Long idSalida) {
        this.idSalida = idSalida;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public RazonSalidaDTO getRazon() {
        return razon;
    }

    public void setRazon(RazonSalidaDTO razon) {
        this.razon = razon;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public List<ProductoSalidaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoSalidaDTO> productos) {
        this.productos = productos;
    }

    public int getCantidadTotalProductos() {
        if (productos == null) {
            return 0;
        }
        return productos.stream().mapToInt(ProductoSalidaDTO::getCantidad).sum();
    }

    public String getResumenProductos() {
        if (productos == null || productos.isEmpty()) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        for (ProductoSalidaDTO item : productos) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            String nombre = item.getProducto() != null ? item.getProducto().getNombre() : "Producto";
            sb.append(nombre);
        }
        return sb.toString();
    }
}
