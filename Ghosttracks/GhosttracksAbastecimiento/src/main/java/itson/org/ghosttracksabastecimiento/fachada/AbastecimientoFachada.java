/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.fachada;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.negocio.interfaces.IOrdenesBO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import itson.org.ghosttracks.negocio.objetosNegocio.OrdenesBO;
import itson.org.ghosttracksabastecimiento.excepciones.AbastecimientoException;
import itson.org.ghosttracksabastecimiento.excepciones.CodigoErrorAbastecimiento;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class AbastecimientoFachada implements IAbastecimiento{

    private final IOrdenesBO ordenesBO;

    public AbastecimientoFachada() {
        this.ordenesBO = new OrdenesBO();
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
    public OrdenDTO obtenerOrdenPorId(Long idOrden) throws AbastecimientoException {
        if (idOrden == null || idOrden <= 0) {
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
    public void actualizarEstadoOrden(Long idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException {
        try {
            ordenesBO.actualizarEstadoOrden(idOrden, nuevoEstado);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "No se pudo actualizar el estado.", ex);
        }
    }

    @Override
    public OrdenDTO confirmarRecepcionOrden(Long idOrden, byte[] imagen) throws AbastecimientoException {
        try {
            return ordenesBO.confirmarRecepcion(idOrden, imagen);
        } catch (NegocioException ex) {
            throw new AbastecimientoException(CodigoErrorAbastecimiento.ERROR_PERSISTENCIA, "Error al confirmar la recepción.", ex);
        }
    }
}
