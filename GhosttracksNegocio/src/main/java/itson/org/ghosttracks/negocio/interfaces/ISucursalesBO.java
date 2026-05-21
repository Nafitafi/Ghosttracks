/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.negocio.interfaces;

import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface ISucursalesBO {

    /**
     * Obtiene una lista con todas las sucursales registradas en el sistema.
     *
     * @return Lista de todas las sucursales encontradas.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    List<SucursalDTO> obtenerTodos() throws NegocioException;
}
