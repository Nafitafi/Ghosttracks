/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.daos;

import itson.org.ghosttracks.dtos.FiltroOrdenPersistenciaDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IOrdenesDAO {

    /**
     * Inserta un nuevo registro de orden.
     *
     * @param orden Objeto con los datos de la orden a registrar.
     * @return Orden registrada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Orden insertarOrden(Orden orden) throws PersistenciaException;

    /**
     * Actualiza el estado de una orden existente mediante su identificador
     * único.
     *
     * @param idOrden Identificador de la orden a actualizar.
     * @param estado Nuevo estado que se asignará a la orden.
     * @return La orden actualizada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException;

    /**
     * Actualiza una orden por completo.
     *
     * @param orden La orden con los nuevos datos a actualizar.
     * @return La orden actualizada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Orden actualizarOrdenCompleta(Orden orden) throws PersistenciaException;

    /**
     * Obtiene una lista con todas las órdenes registradas en el sistema.
     *
     * @return Lista de todas las órdenes encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract List<Orden> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene una lista de órdenes que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * órdenes.
     * @return Lista de órdenes que cumplen con el filtro aplicado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract List<Orden> obtenerPorFiltro(FiltroOrdenPersistenciaDTO filtro) throws PersistenciaException;

    /**
     * Obtiene una orden en específico mediante su identificador único.
     *
     * @param id Identificador de la orden a buscar.
     * @return La orden correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Orden obtenerPorId(String id) throws PersistenciaException;

}
