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

    public abstract Orden insertarOrden(Orden orden) throws PersistenciaException;

    public abstract Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException;

    public abstract Orden actualizarOrdenCompleta(Orden orden) throws PersistenciaException;

    public abstract List<Orden> obtenerTodos() throws PersistenciaException;

    public abstract List<Orden> obtenerPorFiltro(FiltroOrdenPersistenciaDTO filtro) throws PersistenciaException;

    public abstract Orden obtenerPorId(String id) throws PersistenciaException;
    
}
