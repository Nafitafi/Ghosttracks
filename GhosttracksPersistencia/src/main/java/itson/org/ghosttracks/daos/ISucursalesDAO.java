/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.daos;

import itson.org.ghosttracks.entidades.Sucursal;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface ISucursalesDAO {

    /**
     * Obtiene una lista con todas las sucursales registradas en el sistema.
     *
     * @return Lista de todas las sucursales encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Sucursal> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene una sucursal en específico mediante su identificador único.
     *
     * @param idSucursal Identificador de la sucursal a buscar.
     * @return La sucursal correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Sucursal obtenerPorId(String idSucursal) throws PersistenciaException;
}
