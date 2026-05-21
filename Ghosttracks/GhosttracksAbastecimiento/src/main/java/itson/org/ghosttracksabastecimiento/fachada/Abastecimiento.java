/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.fachada;

import itson.org.ghosttracks.dtos.FiltroOrdenDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaDTO;
import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProductoSalidaDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.negocio.interfaces.IOrdenesBO;
import itson.org.ghosttracks.negocio.interfaces.IProductosBO;
import itson.org.ghosttracks.negocio.interfaces.IProveedoresBO;
import itson.org.ghosttracks.negocio.interfaces.ISalidasBO;
import itson.org.ghosttracks.negocio.interfaces.ISucursalesBO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import itson.org.ghosttracks.negocio.objetosNegocio.OrdenesBO;
import itson.org.ghosttracks.negocio.objetosNegocio.ProductosBO;
import itson.org.ghosttracks.negocio.objetosNegocio.ProveedoresBO;
import itson.org.ghosttracks.negocio.objetosNegocio.SalidasBO;
import itson.org.ghosttracks.negocio.objetosNegocio.SucursalesBO;
import itson.org.ghosttracksabastecimiento.excepciones.AbastecimientoException;
import itson.org.ghosttracksabastecimiento.excepciones.CodigoErrorAbastecimiento;
import itson.org.infraestructura.ComunicacionProveedor;
import itson.org.infraestructura.IComunicacionProveedor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class Abastecimiento implements IAbastecimiento {

    private final IOrdenesBO ordenesBO;
    private final IProveedoresBO proveedoresBO;
    private final ISalidasBO salidasBO;
    private final ISucursalesBO sucursalesBO;
    private final IProductosBO productosBO;
    private final IComunicacionProveedor comunicacionProveedor;

    /**
     * Construye una nueva fachada de abastecimiento con sus dependencias de
     * negocio.
     */
    public Abastecimiento() {
        this.ordenesBO = new OrdenesBO();
        this.proveedoresBO = new ProveedoresBO();
        this.salidasBO = new SalidasBO();
        this.sucursalesBO = new SucursalesBO();
        this.productosBO = new ProductosBO();
        this.comunicacionProveedor = new ComunicacionProveedor();
    }

    /**
     * Obtiene una lista con todas las órdenes registradas en el sistema.
     *
     * @return Lista de todas las órdenes encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<OrdenDTO> obtenerTodasLasOrdenes() throws AbastecimientoException {
        try {
            return ordenesBO.obtenerOrdenes();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener órdenes.", ex);
        }
    }

    /**
     * Obtiene una lista de órdenes que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * órdenes.
     * @return Lista de órdenes que cumplen con el filtro aplicado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<OrdenDTO> obtenerOrdenes(FiltroOrdenDTO filtro) throws AbastecimientoException {
        try {
            return ordenesBO.obtenerOrdenes(filtro);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener ordenes filtradas.", ex);
        }
    }

    /**
     * Obtiene una orden en específico mediante su identificador único.
     *
     * @param idOrden Identificador de la orden a buscar.
     * @return La orden correspondiente al ID proporcionado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public OrdenDTO obtenerOrdenPorId(String idOrden) throws AbastecimientoException {
        if (idOrden == null || idOrden.isBlank()) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.DATOS_INVALIDOS, "ID de orden inválido.");
        }
        try {
            return ordenesBO.obtenerOrdenPorId(idOrden);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ORDEN_NO_ENCONTRADA, "No se encontró la orden especificada.", ex);
        }
    }

    /**
     * Registra una nueva orden de compra y notifica al proveedor
     * correspondiente.
     *
     * @param dto Objeto DTO con los datos de la orden a registrar.
     * @return Orden registrada con éxito.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public OrdenDTO registrarNuevaOrden(NuevaOrdenDTO dto) throws AbastecimientoException {
        try {
            ordenesBO.validarDatosOrden(dto);
            if (dto.getProductos().isEmpty()) {
                throw new NegocioException("La orden debe incluir al menos un producto.");
            }
            LocalDate fechaEstimada = dto.getFechaEntregaEstimada() != null
                    ? dto.getFechaEntregaEstimada()
                    : (dto.getFechaSolicitud() != null ? dto.getFechaSolicitud() : LocalDateTime.now()).toLocalDate().plusDays(10);
            if (fechaEstimada.isBefore(LocalDate.now())) {
                throw new NegocioException("La fecha estimada no puede ser anterior a hoy.");
            }
            for (ProductoOrdenDTO item : dto.getProductos()) {
                if (item.getCantidadProducto() <= 0) {
                    String nombre = item.getProducto() != null ? item.getProducto().getNombre() : "producto";
                    throw new NegocioException("La cantidad de '" + nombre + "' debe ser mayor a cero.");
                }
            }

            OrdenDTO ordenRegistrada = ordenesBO.procesarNuevaOrden(dto);
            if (!comunicacionProveedor.notificar(ordenRegistrada)) {
                throw new NegocioException("La orden se guardo, pero no se pudo notificar al proveedor.");
            }
            return ordenRegistrada;
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.DATOS_INVALIDOS, ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza el estado de una orden existente mediante su identificador
     * único.
     *
     * @param idOrden Identificador de la orden a actualizar.
     * @param nuevoEstado Nuevo estado que se asignará a la orden.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException {
        try {
            ordenesBO.actualizarEstadoOrden(idOrden, nuevoEstado);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "No se pudo actualizar el estado.", ex);
        }
    }

    /**
     * Confirma la recepción de una orden y actualiza el stock de los productos
     * recibidos.
     *
     * @param idOrden Identificador de la orden a confirmar.
     * @param imagen Evidencia de la recepción de la orden.
     * @param productosRecibidos Lista de productos recibidos en la orden.
     * @return Orden actualizada con la recepción confirmada.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public OrdenDTO confirmarRecepcionOrden(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws AbastecimientoException {
        try {
            if (idOrden == null || idOrden.isBlank()) {
                throw new NegocioException("El identificador de la orden es obligatorio.");
            }
            if (imagen == null || imagen.length == 0) {
                throw new NegocioException("Debe cargar una imagen como evidencia de recepcion.");
            }
            if (productosRecibidos == null || productosRecibidos.isEmpty()) {
                throw new NegocioException("Debe confirmar al menos un producto recibido.");
            }
            OrdenDTO ordenActual = ordenesBO.obtenerOrdenPorId(idOrden);
            if (ordenActual.getEstadoOrden() == EstadoOrdenDTO.RECIBIDO || ordenActual.getEstadoOrden() == EstadoOrdenDTO.CERRADO
                    || ordenActual.getEstadoOrden() == EstadoOrdenDTO.CANCELADO) {
                throw new NegocioException("Esta orden ya no puede confirmar recepcion.");
            }

            OrdenDTO orden = ordenesBO.confirmarRecepcion(idOrden, imagen, productosRecibidos);
            if (orden.getProductosOrden() != null) {
                for (ProductoOrdenDTO productoOrden : orden.getProductosOrden()) {
                    if (productoOrden != null && productoOrden.isRecibido()) {
                        validarCantidadMovimientoStock(productoOrden.getCantidadProducto());
                        productosBO.incrementarStockProducto(obtenerIdProducto(productoOrden), productoOrden.getCantidadProducto());
                    }
                }
            }
            return orden;
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al confirmar la recepción.", ex);
        }
    }

    /**
     * Obtiene una lista con todos los proveedores registrados en el sistema.
     *
     * @return Lista de todos los proveedores encontrados.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedores() throws AbastecimientoException {
        try {
            List<ProveedorDTO> proveedores = proveedoresBO.obtenerTodos();
            if (proveedores == null) {
                throw new NegocioException("La consulta de proveedores no puede ser nula.");
            }
            return proveedores;
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener proveedores.", ex);
        }
    }

    /**
     * Obtiene una lista con todas las sucursales registradas en el sistema.
     *
     * @return Lista de todas las sucursales encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<SucursalDTO> obtenerTodasLasSucursales() throws AbastecimientoException {
        try {
            List<SucursalDTO> sucursales = sucursalesBO.obtenerTodos();
            if (sucursales == null) {
                throw new NegocioException("La consulta de sucursales no puede ser nula.");
            }
            return sucursales;
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener sucursales.", ex);
        }
    }

    /**
     * Obtiene una lista con todos los productos disponibles en el sistema.
     *
     * @return Lista de todos los productos disponibles encontrados.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<ProductoDTO> obtenerProductosDisponibles() throws AbastecimientoException {
        try {
            List<ProductoDTO> productos = productosBO.obtenerProductosDisponibles();
            if (productos == null) {
                throw new NegocioException("La consulta de productos no puede ser nula.");
            }
            return productos;
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener productos.", ex);
        }
    }

    /**
     * Registra una nueva salida de productos y descuenta el stock
     * correspondiente.
     *
     * @param dto Objeto DTO con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public SalidaDTO registrarNuevaSalida(NuevaSalidaDTO dto) throws AbastecimientoException {
        try {
            if (dto == null) {
                throw new NegocioException("Los datos de salida son obligatorios.");
            }
            if (dto.getSucursal() == null) {
                throw new NegocioException("Debe seleccionar una sucursal.");
            }
            if (dto.getRazon() == null) {
                throw new NegocioException("Debe seleccionar la razon de salida.");
            }
            if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
                throw new NegocioException("Debe agregar al menos un producto.");
            }
            for (ProductoSalidaDTO productoSalida : dto.getProductos()) {
                if (productoSalida == null || productoSalida.getProducto() == null) {
                    throw new NegocioException("Debe indicar el producto de la salida.");
                }
                if (productoSalida.getCantidad() <= 0) {
                    throw new NegocioException("La cantidad de salida debe ser mayor a cero.");
                }
                ProductoDTO producto = productosBO.obtenerProductoDTOPorId(obtenerIdProducto(productoSalida));
                int stockActual = producto.getStock() != null ? producto.getStock() : 0;
                if (stockActual < productoSalida.getCantidad()) {
                    throw new NegocioException("Stock insuficiente para el producto: " + producto.getNombre());
                }
            }
            for (ProductoSalidaDTO productoSalida : dto.getProductos()) {
                validarCantidadMovimientoStock(productoSalida.getCantidad());
                productosBO.decrementarStockProducto(obtenerIdProducto(productoSalida), productoSalida.getCantidad());
            }
            return salidasBO.registrarSalida(dto);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.DATOS_INVALIDOS, ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<SalidaDTO> obtenerTodasLasSalidas() throws AbastecimientoException {
        try {
            return salidasBO.obtenerSalidas();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener salidas.", ex);
        }
    }

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    @Override
    public List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws AbastecimientoException {
        try {
            return salidasBO.obtenerSalidas(filtro);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener salidas filtradas.", ex);
        }
    }

    private String obtenerIdProducto(ProductoOrdenDTO productoOrden) throws NegocioException {
        if (productoOrden == null || productoOrden.getProducto() == null) {
            throw new NegocioException("Debe indicar el producto de la orden.");
        }
        return productoOrden.getProducto().getIdProducto();
    }

    private String obtenerIdProducto(ProductoSalidaDTO productoSalida) throws NegocioException {
        if (productoSalida == null || productoSalida.getProducto() == null) {
            throw new NegocioException("Debe indicar el producto de la salida.");
        }
        return productoSalida.getProducto().getIdProducto();
    }

    private void validarCantidadMovimientoStock(int cantidad) throws NegocioException {
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad debe ser mayor a cero.");
        }
    }
}
