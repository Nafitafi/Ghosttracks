/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.fachada;

import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracksabastecimiento.excepciones.AbastecimientoException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IAbastecimiento {

    List<OrdenDTO> obtenerTodasLasOrdenes() throws AbastecimientoException;

    OrdenDTO obtenerOrdenPorId(Long idOrden) throws AbastecimientoException;

    OrdenDTO registrarNuevaOrden(NuevaOrdenDTO dto) throws AbastecimientoException;

    void actualizarEstadoOrden(Long idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException;

    OrdenDTO confirmarRecepcionOrden(Long idOrden, byte[] imagen) throws AbastecimientoException;
    
    List<ProveedorDTO> obtenerTodosLosProveedores() throws AbastecimientoException;
    
}
