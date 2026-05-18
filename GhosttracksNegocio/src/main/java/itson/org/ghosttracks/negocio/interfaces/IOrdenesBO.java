/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.negocio.interfaces;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IOrdenesBO {

    /**
     * Valida que los datos de la orden sean correctos y completos. PREGUNTARRRR
     *
     * @param dto datos a validar
     * @throws NegocioException si la validación falla
     */
    public abstract void validarDatosOrden(NuevaOrdenDTO dto) throws NegocioException;

    /**
     * Procesa y persiste una nueva orden de compra.
     *
     * @param dto datos de la orden
     * @return OrdenDTO de la orden creada
     * @throws
     * itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException
     */
    public abstract OrdenDTO procesarNuevaOrden(NuevaOrdenDTO dto) throws NegocioException;

    /**
     * Actualiza el estado de una orden existente.
     *
     * @param idOrden identificador de la orden
     * @param nuevoEstado nuevo estado a asignar
     * @throws
     * itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException
     */
    void actualizarEstadoOrden(Long idOrden, EstadoOrdenDTO nuevoEstado) throws NegocioException;

    List<OrdenDTO> obtenerOrdenes() throws NegocioException;

    OrdenDTO obtenerOrdenPorId(Long idOrden) throws NegocioException;

    OrdenDTO confirmarRecepcion(Long idOrden, byte[] imagen) throws NegocioException;

}
