/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.ProductoOrden;
import itson.org.ghosttracks.entidades.ProveedorRef;
import itson.org.ghosttracks.entidades.SucursalRef;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (dto.getProveedor() == null) {
            throw new NegocioException("Debe seleccionar un proveedor.");
        }
        if (dto.getSucursal() == null) {
            throw new NegocioException("Debe seleccionar una sucursal destino.");
        }
        if (dto.getTipoOrden() == null) {
            throw new NegocioException("Debe seleccionar el tipo de orden.");
        }
        if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
            throw new NegocioException("La orden debe incluir al menos un producto.");
        }
        if (dto.getFechaEntregaEstimada() == null) {
            throw new NegocioException("La fecha de entrega estimada es obligatoria.");
        }
        if (dto.getFechaEntregaEstimada().isBefore(LocalDate.now())) {
            throw new NegocioException("La fecha estimada no puede ser anterior a hoy.");
        }
        for (ProductoOrdenDTO item : dto.getProductos()) {
            if (item.getCantidadProducto() <= 0) {
                String nombre = item.getProducto() != null ? item.getProducto().getNombre() : "producto";
                throw new NegocioException("La cantidad de '" + nombre + "' debe ser mayor a cero.");
            }
        }
    }

    @Override
    public OrdenDTO procesarNuevaOrden(NuevaOrdenDTO dto) throws NegocioException {
        validarDatosOrden(dto);

        Orden orden = new Orden();
        orden.setFolio(generarFolio());
        orden.setFecha(LocalDateTime.now());
        orden.setFechaSolicitud(LocalDateTime.now());
        orden.setFechaEntregaEstimada(dto.getFechaEntregaEstimada());
        orden.setComentarios(dto.getComentarios());
        orden.setTipoOrden(TipoOrden.valueOf(dto.getTipoOrden().name()));
        orden.setEstado(EstadoOrden.CONFIRMADO);
        orden.setProveedor(new ProveedorRef(
                dto.getProveedor().getIdProveedor(),
                dto.getProveedor().getNombreProveedor()));
        orden.setSucursal(new SucursalRef(
                dto.getSucursal().getIdSucursal() != null ? String.valueOf(dto.getSucursal().getIdSucursal()) : null,
                dto.getSucursal().getNombre()));

        List<ProductoOrden> productosOrden = new ArrayList<>();
        double total = 0.0;
        for (ProductoOrdenDTO item : dto.getProductos()) {
            ProductoOrden po = new ProductoOrden();
            po.setCantidadProducto(item.getCantidadProducto());
            po.setPrecioUnitario(item.getPrecioUnitario());
            po.setImporteTotal(item.getImporteTotal());
            po.setRecibido(false);
            if (item.getProducto() != null) {
                po.setIdProducto(item.getProducto().getIdProducto());
            }
            total += item.getImporteTotal();
            productosOrden.add(po);
        }
        orden.setProductosOrden(productosOrden);
        orden.setTotal(total);

        try {
            Orden guardada = persistencia.insertarOrden(orden);
            return OrdenMapper.toDTO(guardada, ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo guardar la orden: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarEstadoOrden(Long idOrden, EstadoOrdenDTO nuevoEstado) throws NegocioException {
        if (idOrden == null || nuevoEstado == null) {
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
    public OrdenDTO obtenerOrdenPorId(Long idOrden) throws NegocioException {
        try {
            return OrdenMapper.toDTO(persistencia.obtenerOrdenPorId(idOrden), ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se encontró la orden: " + ex.getMessage(), ex);
        }
    }

    @Override
    public OrdenDTO confirmarRecepcion(Long idOrden, byte[] imagen) throws NegocioException {
        try {
            Orden orden = persistencia.obtenerOrdenPorId(idOrden);
            orden.setImagen(imagen);
            orden.setFechaEntrega(LocalDateTime.now());
            orden.setEstado(EstadoOrden.RECIBIDO);
            Orden actualizada = persistencia.actualizarOrden(orden);
            return OrdenMapper.toDTO(actualizada, ordenFactory);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo confirmar la recepción: " + ex.getMessage(), ex);
        }
    }

    private String generarFolio() {
        return "GT-ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
