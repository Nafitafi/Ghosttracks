/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.daos;

import itson.org.ghosttracks.entidades.Proveedor;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IProveedoresDAO {

    /**
     * Obtiene una lista con todos los proveedores registrados en el sistema.
     *
     * @return Lista de todos los proveedores encontrados.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Proveedor> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene un proveedor en específico mediante su identificador único.
     *
     * @param idProveedor Identificador del proveedor a buscar.
     * @return El proveedor correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Proveedor obtenerPorId(String idProveedor) throws PersistenciaException;
}
