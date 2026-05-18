package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.RazonSalidaDTO;
import java.util.List;

/**
 * @author nafbr
 */
public class NuevaSalidaDTO {

    private SucursalDTO sucursal;
    private RazonSalidaDTO razon;
    private String comentarios;
    private List<ProductoSalidaDTO> productos;

    public NuevaSalidaDTO() {
    }

    public NuevaSalidaDTO(SucursalDTO sucursal, RazonSalidaDTO razon, String comentarios, List<ProductoSalidaDTO> productos) {
        this.sucursal = sucursal;
        this.razon = razon;
        this.comentarios = comentarios;
        this.productos = productos;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public RazonSalidaDTO getRazon() {
        return razon;
    }

    public void setRazon(RazonSalidaDTO razon) {
        this.razon = razon;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<ProductoSalidaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoSalidaDTO> productos) {
        this.productos = productos;
    }
}
