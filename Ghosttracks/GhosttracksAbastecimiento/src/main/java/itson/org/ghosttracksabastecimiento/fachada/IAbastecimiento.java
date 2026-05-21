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

    /**
     * Obtiene una lista con todas las órdenes registradas en el sistema.
     *
     * @return Lista de todas las órdenes encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<OrdenDTO> obtenerTodasLasOrdenes() throws AbastecimientoException;

    /**
     * Obtiene una lista de órdenes que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * órdenes.
     * @return Lista de órdenes que cumplen con el filtro aplicado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<OrdenDTO> obtenerOrdenes(FiltroOrdenDTO filtro) throws AbastecimientoException;

    /**
     * Obtiene una orden en específico mediante su identificador único.
     *
     * @param idOrden Identificador de la orden a buscar.
     * @return La orden correspondiente al ID proporcionado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    OrdenDTO obtenerOrdenPorId(String idOrden) throws AbastecimientoException;

    /**
     * Registra una nueva orden de compra.
     *
     * @param dto Objeto DTO con los datos de la orden a registrar.
     * @return Orden registrada con éxito.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    OrdenDTO registrarNuevaOrden(NuevaOrdenDTO dto) throws AbastecimientoException;

    /**
     * Actualiza el estado de una orden existente mediante su identificador
     * único.
     *
     * @param idOrden Identificador de la orden a actualizar.
     * @param nuevoEstado Nuevo estado que se asignará a la orden.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    void actualizarEstadoOrden(String idOrden, EstadoOrdenDTO nuevoEstado) throws AbastecimientoException;

    /**
     * Confirma la recepción de una orden con su evidencia y productos
     * recibidos.
     *
     * @param idOrden Identificador de la orden a confirmar.
     * @param imagen Evidencia de la recepción de la orden.
     * @param productosRecibidos Lista de productos recibidos en la orden.
     * @return Orden actualizada con la recepción confirmada.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    OrdenDTO confirmarRecepcionOrden(String idOrden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) throws AbastecimientoException;

    /**
     * Obtiene una lista con todos los proveedores registrados en el sistema.
     *
     * @return Lista de todos los proveedores encontrados.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<ProveedorDTO> obtenerTodosLosProveedores() throws AbastecimientoException;

    /**
     * Obtiene una lista con todas las sucursales registradas en el sistema.
     *
     * @return Lista de todas las sucursales encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<SucursalDTO> obtenerTodasLasSucursales() throws AbastecimientoException;

    /**
     * Obtiene una lista con todos los productos disponibles en el sistema.
     *
     * @return Lista de todos los productos disponibles encontrados.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<ProductoDTO> obtenerProductosDisponibles() throws AbastecimientoException;

    /**
     * Registra una nueva salida de productos.
     *
     * @param dto Objeto DTO con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    SalidaDTO registrarNuevaSalida(NuevaSalidaDTO dto) throws AbastecimientoException;

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<SalidaDTO> obtenerTodasLasSalidas() throws AbastecimientoException;

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws AbastecimientoException En caso de algun fallo en abastecimiento.
     */
    List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws AbastecimientoException;

}
