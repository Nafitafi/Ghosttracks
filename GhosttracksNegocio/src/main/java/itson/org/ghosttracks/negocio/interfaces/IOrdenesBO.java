/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.negocio.interfaces;

import itson.org.ghosttracks.dtos.FiltroOrdenDTO;
import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IOrdenesBO {

    /**
     * Valida que los datos de la orden sean correctos y completos.
     *
     * @param dto Objeto DTO con los datos de la orden a validar.
     * @throws NegocioException En caso de que la validación falle.
     */
    public abstract void validarDatosOrden(NuevaOrdenDTO dto) throws NegocioException;

    /**
     * Procesa y persiste una nueva orden de compra.
     *
     * @param dto Objeto DTO con los datos de la orden a registrar.
     * @return Orden registrada con éxito.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract OrdenDTO procesarNuevaOrden(NuevaOrdenDTO dto) throws NegocioException;

    /**
     * Actualiza el estado de una orden existente mediante su identificador
     * único.
     *
     * @param idOrden Identificador de la orden a actualizar.
     * @param nuevoEstado Nuevo estado que se asignará a la orden.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws NegocioException;

    /**
     * Obtiene una lista con todas las órdenes registradas en el sistema.
     *
     * @return Lista de todas las órdenes encontradas.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    List<OrdenDTO> obtenerOrdenes() throws NegocioException;

    /**
     * Obtiene una lista de órdenes que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * órdenes.
     * @return Lista de órdenes que cumplen con el filtro aplicado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    List<OrdenDTO> obtenerOrdenes(FiltroOrdenDTO filtro) throws NegocioException;

    /**
     * Obtiene una orden en específico mediante su identificador único.
     *
     * @param idOrden Identificador de la orden a buscar.
     * @return La orden correspondiente al ID proporcionado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    OrdenDTO obtenerOrdenPorId(String idOrden) throws NegocioException;

    /**
     * Confirma la recepción de una orden con su evidencia y productos
     * recibidos.
     *
     * @param idOrden Identificador de la orden a confirmar.
     * @param imagen Evidencia de la recepción de la orden.
     * @param productosRecibidos Lista de productos recibidos en la orden.
     * @return Orden actualizada con la recepción confirmada.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    OrdenDTO confirmarRecepcion(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws NegocioException;

}
