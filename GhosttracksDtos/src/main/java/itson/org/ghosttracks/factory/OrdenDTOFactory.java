package itson.org.ghosttracks.factory;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author nafbr
 */
public class OrdenDTOFactory implements IOrdenDTOFactory {

    @Override
    public NuevaOrdenDTO crearNuevaOrden(ProveedorDTO proveedor, SucursalDTO sucursal, List<ProductoOrdenDTO> productos, String comentarios, LocalDate fechaEstimada, TipoOrdenDTO tipoOrden) {
        NuevaOrdenDTO dto = new NuevaOrdenDTO();
        dto.setProveedor(proveedor);
        dto.setSucursal(sucursal);
        dto.setProductos(productos);
        dto.setComentarios(comentarios);
        dto.setFechaEntregaEstimada(fechaEstimada);
        dto.setTipoOrden(tipoOrden);
        return dto;
    }

    @Override
    public OrdenDTO crearOrdenLectura(Long idOrden, String folio, TipoOrdenDTO tipoOrden, String comentarios, Double total,
            LocalDate fechaEntregaEst, LocalDateTime fechaSolicitud, EstadoOrdenDTO estadoOrden,
            List<ProductoOrdenDTO> productosOrden, Byte[] imagenRecepcion, ProveedorDTO proveedor, SucursalDTO sucursal) {

        OrdenDTO dto = new OrdenDTO();
        dto.setIdOrden(idOrden);
        dto.setFolio(folio);
        dto.setTipoOrden(tipoOrden);
        dto.setComentarios(comentarios);
        dto.setTotal(total);
        dto.setFechaEntregaEst(fechaEntregaEst);
        dto.setFechaSolicitud(fechaSolicitud);
        dto.setEstadoOrden(estadoOrden);
        dto.setProductosOrden(productosOrden);
        dto.setImagenRecepcion(imagenRecepcion);
        dto.setProveedor(proveedor);
        dto.setSucursal(sucursal);

        return dto;
    }
}
