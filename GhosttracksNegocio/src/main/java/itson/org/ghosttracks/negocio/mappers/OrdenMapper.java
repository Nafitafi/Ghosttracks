/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import itson.org.ghosttracks.factory.IOrdenDTOFactory;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class OrdenMapper {

   public static OrdenDTO toDTO(Orden orden, IOrdenDTOFactory factory) {
        if (orden == null) {
            return null;
        }

        Byte[] wrapperImagen = null;
        if (orden.getImagen() != null) {
            wrapperImagen = new Byte[orden.getImagen().length];
            int i = 0;
            for (byte b : orden.getImagen()) {
                wrapperImagen[i++] = b;
            }
        }

        ProveedorDTO provDTO = ProveedorMapper.toProveedorDTO(orden.getProveedor());
        SucursalDTO sucDTO = SucursalMapper.toSucursalDTO(orden.getSucursal());
        List<ProductoOrdenDTO> productosDTO = ProductosOrdenMapper.toProductosOrdenDTO(orden.getProductosOrden());

        return factory.crearOrdenLectura(
                orden.getIdOrden(),
                orden.getFolio(),
                orden.getTipoOrden() != null ? TipoOrdenDTO.valueOf(orden.getTipoOrden().name()) : null,
                orden.getComentarios(),
                orden.getTotal(),
                orden.getFechaEntregaEstimada(),
                orden.getFechaSolicitud(),
                orden.getEstado() != null ? EstadoOrdenDTO.valueOf(orden.getEstado().name()) : null,
                provDTO,
                sucDTO,
                productosDTO,
                wrapperImagen
        );
    }
}
