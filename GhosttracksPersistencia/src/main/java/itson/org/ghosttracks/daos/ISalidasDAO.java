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

    Salida insertar(Salida salida) throws PersistenciaException;

    List<Salida> obtenerTodos() throws PersistenciaException;

    List<Salida> obtenerPorFiltro(FiltroSalidaPersistenciaDTO filtro) throws PersistenciaException;

    Salida obtenerPorId(String idSalida) throws PersistenciaException;
    
}
