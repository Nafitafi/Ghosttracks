/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.FiltroOrdenDTO;
import itson.org.ghosttracks.dtos.FiltroOrdenPersistenciaDTO;
import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.ProductoOrden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.factory.IOrdenDTOFactory;
import itson.org.ghosttracks.factory.OrdenDTOFactory;
import itson.org.ghosttracks.negocio.interfaces.IOrdenesBO;
import itson.org.ghosttracks.negocio.mappers.OrdenMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author nafbr
 */
public class OrdenesBO implements IOrdenesBO {

    private final IPersistenciaAbastecimiento persistencia;
    private final IOrdenDTOFactory ordenFactory;

    public OrdenesBO() {
        this.persistencia = new PersistenciaFachada();
        this.ordenFactory = new OrdenDTOFactory();
    }

    @Override
    public void validarDatosOrden(NuevaOrdenDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("El DTO de la orden no puede ser nulo.");
        }
        if (dto.getProveedor() == null || dto.getSucursal() == null || dto.getTipoOrden() == null || dto.getProductos() == null) {
            throw new NegocioException("Los datos base de la orden son obligatorios.");
        }
        for (ProductoOrdenDTO producto : dto.getProductos()) {
            if (producto == null || producto.getProducto() == null) {
                throw new NegocioException("Los productos de la orden son obligatorios.");
            }
        }
    }

    @Override
    public OrdenDTO procesarNuevaOrden(NuevaOrdenDTO dto) throws NegocioException {
        validarDatosOrden(dto);

        Orden orden = OrdenMapper.toEntidad(dto, generarFolio());
        try {
            Orden guardada = persistencia.insertarOrden(orden);
            return OrdenMapper.toDTO(guardada, ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo guardar la orden: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws NegocioException {
        if (idOrden == null || idOrden.isBlank() || nuevoEstado == null) {
            throw new NegocioException("El identificador y el estado son obligatorios.");
        }
        try {
            persistencia.actualizarEstadoOrden(idOrden, EstadoOrden.valueOf(nuevoEstado.name()));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo actualizar el estado de la orden: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<OrdenDTO> obtenerOrdenes() throws NegocioException {
        try {
            return persistencia.obtenerOrdenes().stream()
                    .map(orden -> OrdenMapper.toDTO(orden, ordenFactory))
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las órdenes: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<OrdenDTO> obtenerOrdenes(FiltroOrdenDTO filtro) throws NegocioException {
        try {
            return persistencia.obtenerOrdenes(mapearFiltroPersistencia(filtro)).stream()
                    .map(orden -> OrdenMapper.toDTO(orden, ordenFactory))
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las ordenes filtradas: " + ex.getMessage(), ex);
        }
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(String idOrden) throws NegocioException {
        try {
            return OrdenMapper.toDTO(persistencia.obtenerOrdenPorId(idOrden), ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se encontró la orden: " + ex.getMessage(), ex);
        }
    }

    @Override
    public OrdenDTO confirmarRecepcion(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws NegocioException {
        if (idOrden == null || idOrden.isBlank()) {
            throw new NegocioException("El identificador de la orden es obligatorio.");
        }
        if (imagen == null) {
            throw new NegocioException("La imagen de recepcion no puede ser nula.");
        }
        if (productosRecibidos == null) {
            throw new NegocioException("Los productos recibidos no pueden ser nulos.");
        }
        try {
            Orden orden = persistencia.obtenerOrdenPorId(idOrden);
            orden.setImagen(imagen);
            orden.setFechaEntrega(LocalDateTime.now());
            orden.setEstado(EstadoOrden.RECIBIDO);
            aplicarProductosRecibidos(orden.getProductosOrden(), productosRecibidos);
            Orden actualizada = persistencia.actualizarOrden(orden);
            return OrdenMapper.toDTO(actualizada, ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo confirmar la recepción: " + ex.getMessage(), ex);
        }
    }

    private void aplicarProductosRecibidos(List<ProductoOrden> productosOrden, List<ProductoOrdenDTO> productosRecibidos) {
        if (productosOrden == null) {
            return;
        }
        for (int i = 0; i < productosOrden.size(); i++) {
            ProductoOrdenDTO productoRecibido = productosRecibidos.size() > i ? productosRecibidos.get(i) : null;
            productosOrden.get(i).setRecibido(productoRecibido != null && productoRecibido.isRecibido());
        }
    }

    private String generarFolio() {
        return "GT-ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private FiltroOrdenPersistenciaDTO mapearFiltroPersistencia(FiltroOrdenDTO filtro) {
        if (filtro == null) {
            return null;
        }
        FiltroOrdenPersistenciaDTO filtroPersistencia = new FiltroOrdenPersistenciaDTO();
        filtroPersistencia.setIdProveedor(filtro.getIdProveedor());
        filtroPersistencia.setEstado(filtro.getEstado() != null ? EstadoOrden.valueOf(filtro.getEstado().name()) : null);
        filtroPersistencia.setTipoOrden(filtro.getTipoOrden() != null ? TipoOrden.valueOf(filtro.getTipoOrden().name()) : null);
        filtroPersistencia.setFechaInicio(filtro.getFechaInicio());
        filtroPersistencia.setFechaFin(filtro.getFechaFin());
        return filtroPersistencia;
    }
}
