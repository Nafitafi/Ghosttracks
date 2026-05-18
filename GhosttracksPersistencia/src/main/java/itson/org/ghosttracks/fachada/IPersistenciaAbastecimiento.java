/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.fachada;

import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IPersistenciaAbastecimiento {

    Orden insertar(Orden orden) throws PersistenciaException;

    Orden actualizar(Long idOrden, EstadoOrden estado) throws PersistenciaException;

    Orden actualizarOrdenCompleta(Orden orden) throws PersistenciaException;

    List<Orden> obtenerTodos() throws PersistenciaException;

    Orden obtenerPorId(Long id) throws PersistenciaException;
}
