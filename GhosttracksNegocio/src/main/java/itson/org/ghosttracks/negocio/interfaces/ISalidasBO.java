/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.negocio.interfaces;

import itson.org.ghosttracks.dtos.FiltroSalidaDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface ISalidasBO {

    /**
     * Registra una nueva salida de productos.
     *
     * @param dto Objeto DTO con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    SalidaDTO registrarSalida(NuevaSalidaDTO dto) throws NegocioException;

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    List<SalidaDTO> obtenerSalidas() throws NegocioException;

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws NegocioException;
}
