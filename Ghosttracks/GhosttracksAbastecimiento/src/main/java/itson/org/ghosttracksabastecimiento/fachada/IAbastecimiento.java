/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.fachada;

import itson.org.ghosttracks.dtos.FiltroOrdenDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaDTO;
import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracksabastecimiento.excepciones.AbastecimientoException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IAbastecimiento {

    List<OrdenDTO> obtenerTodasLasOrdenes() throws AbastecimientoException;

    List<OrdenDTO> obtenerOrdenes(FiltroOrdenDTO filtro) throws AbastecimientoException;

    OrdenDTO obtenerOrdenPorId(String idOrden) throws AbastecimientoException;

    OrdenDTO registrarNuevaOrden(NuevaOrdenDTO dto) throws AbastecimientoException;

    void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException;

    OrdenDTO confirmarRecepcionOrden(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws AbastecimientoException;

    List<ProveedorDTO> obtenerTodosLosProveedores() throws AbastecimientoException;

    List<SucursalDTO> obtenerTodasLasSucursales() throws AbastecimientoException;

    List<ProductoDTO> obtenerProductosDisponibles() throws AbastecimientoException;

    SalidaDTO registrarNuevaSalida(NuevaSalidaDTO dto) throws AbastecimientoException;

    List<SalidaDTO> obtenerTodasLasSalidas() throws AbastecimientoException;

    List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws AbastecimientoException;

}
