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

    SalidaDTO registrarSalida(NuevaSalidaDTO dto) throws NegocioException;

    List<SalidaDTO> obtenerSalidas() throws NegocioException;
    
    List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws NegocioException;
}
