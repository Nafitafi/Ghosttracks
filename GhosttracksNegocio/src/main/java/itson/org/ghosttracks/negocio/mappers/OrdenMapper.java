/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrden;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import itson.org.ghosttracks.factory.IOrdenDTOFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                productosDTO,
                wrapperImagen,
                provDTO,
                sucDTO
        );
    }

    public static Orden toEntidad(NuevaOrdenDTO dto, String folio) {
        if (dto == null) {
            return null;
        }

        Orden orden = new Orden();
        LocalDateTime fechaSolicitud = dto.getFechaSolicitud() != null ? dto.getFechaSolicitud() : LocalDateTime.now();
        orden.setFolio(folio);
        orden.setFecha(fechaSolicitud);
        orden.setFechaSolicitud(fechaSolicitud);
        orden.setFechaEntregaEstimada(obtenerFechaEstimada(dto, fechaSolicitud));
        orden.setComentarios(dto.getComentarios());
        orden.setTipoOrden(dto.getTipoOrden() != null ? TipoOrden.valueOf(dto.getTipoOrden().name()) : null);
        orden.setEstado(EstadoOrden.CONFIRMADO);
        orden.setProveedor(ProveedorMapper.toRef(dto.getProveedor()));
        orden.setSucursal(SucursalMapper.toRef(dto.getSucursal()));
        orden.setProductosOrden(ProductosOrdenMapper.toEntidades(dto.getProductos()));
        orden.setTotal(calcularTotal(dto.getProductos()));
        return orden;
    }

    private static LocalDate obtenerFechaEstimada(NuevaOrdenDTO dto, LocalDateTime fechaSolicitud) {
        if (dto.getFechaEntregaEstimada() != null) {
            return dto.getFechaEntregaEstimada();
        }
        return fechaSolicitud.toLocalDate().plusDays(10);
    }

    private static Double calcularTotal(List<ProductoOrdenDTO> productos) {
        if (productos == null) {
            return 0.0;
        }
        return productos.stream()
                .mapToDouble(ProductoOrdenDTO::getImporteTotal)
                .sum();
    }
}
