/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.daos;

import itson.org.ghosttracks.dtos.FiltroSalidaPersistenciaDTO;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface ISalidasDAO {

    /**
     * Inserta un nuevo registro de salida.
     *
     * @param salida Objeto con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Salida insertar(Salida salida) throws PersistenciaException;

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Salida> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Salida> obtenerPorFiltro(FiltroSalidaPersistenciaDTO filtro) throws PersistenciaException;

    /**
     * Obtiene una salida en específico mediante su identificador único.
     *
     * @param idSalida Identificador de la salida a buscar.
     * @return La salida correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Salida obtenerPorId(String idSalida) throws PersistenciaException;

}
