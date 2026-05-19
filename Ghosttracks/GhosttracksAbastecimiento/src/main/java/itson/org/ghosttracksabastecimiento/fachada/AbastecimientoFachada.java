/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.fachada;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
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
import java.util.List;

/**
 *
 * @author nafbr
 */
public class AbastecimientoFachada implements IAbastecimiento {

    private final IOrdenesBO ordenesBO;
    private final IProveedoresBO proveedoresBO;
    private final ISalidasBO salidasBO;
    private final ISucursalesBO sucursalesBO;
    private final IProductosBO productosBO;

    public AbastecimientoFachada() {
        this.ordenesBO = new OrdenesBO();
        this.proveedoresBO = new ProveedoresBO();
        this.salidasBO = new SalidasBO();
        this.sucursalesBO = new SucursalesBO();
        this.productosBO = new ProductosBO();
    }

    @Override
    public List<OrdenDTO> obtenerTodasLasOrdenes() throws AbastecimientoException {
        try {
            return ordenesBO.obtenerOrdenes();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener órdenes.", ex);
        }
    }

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

    @Override
    public OrdenDTO registrarNuevaOrden(NuevaOrdenDTO dto) throws AbastecimientoException {
        try {
            return ordenesBO.procesarNuevaOrden(dto);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.DATOS_INVALIDOS, ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException {
        try {
            ordenesBO.actualizarEstadoOrden(idOrden, nuevoEstado);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "No se pudo actualizar el estado.", ex);
        }
    }

    @Override
    public OrdenDTO confirmarRecepcionOrden(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws AbastecimientoException {
        try {
            return ordenesBO.confirmarRecepcion(idOrden, imagen, productosRecibidos);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al confirmar la recepción.", ex);
        }
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedores() throws AbastecimientoException {
        try {
            return proveedoresBO.obtenerTodos();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener proveedores.", ex);
        }
    }

    @Override
    public List<SucursalDTO> obtenerTodasLasSucursales() throws AbastecimientoException {
        try {
            return sucursalesBO.obtenerTodos();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener sucursales.", ex);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductosDisponibles() throws AbastecimientoException {
        try {
            return productosBO.obtenerProductosDisponibles();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener productos.", ex);
        }
    }

    @Override
    public SalidaDTO registrarNuevaSalida(NuevaSalidaDTO dto) throws AbastecimientoException {
        try {
            return salidasBO.registrarSalida(dto);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.DATOS_INVALIDOS, ex.getMessage(), ex);
        }
    }

    @Override
    public List<SalidaDTO> obtenerTodasLasSalidas() throws AbastecimientoException {
        try {
            return salidasBO.obtenerSalidas();
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al obtener salidas.", ex);
        }
    }
}
