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
public interface IOrdenDTOFactory {

    NuevaOrdenDTO crearNuevaOrden(ProveedorDTO proveedor, SucursalDTO sucursal, List<ProductoOrdenDTO> productos, String comentarios, LocalDate fechaEstimada, TipoOrdenDTO tipoOrden);
    
    OrdenDTO crearOrdenLectura(String idOrden, String folio, TipoOrdenDTO tipoOrden, String comentarios, Double total, 
                               LocalDate fechaEntregaEst, LocalDateTime fechaSolicitud, EstadoOrdenDTO estadoOrden, 
                               List<ProductoOrdenDTO> productosOrden, Byte[] imagenRecepcion, ProveedorDTO proveedor, SucursalDTO sucursal);
}
