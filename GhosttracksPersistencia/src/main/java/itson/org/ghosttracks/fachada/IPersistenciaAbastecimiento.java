/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.fachada;

import itson.org.ghosttracks.dtos.FiltroOrdenPersistenciaDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaPersistenciaDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.entidades.Proveedor;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.entidades.Sucursal;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IPersistenciaAbastecimiento {

    /**
     * Inserta un nuevo registro de orden.
     *
     * @param orden Objeto con los datos de la orden a registrar.
     * @return Orden registrada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Orden insertarOrden(Orden orden) throws PersistenciaException;

    /**
     * Actualiza el estado de una orden existente mediante su identificador
     * único.
     *
     * @param idOrden Identificador de la orden a actualizar.
     * @param estado Nuevo estado que se asignará a la orden.
     * @return La orden actualizada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException;

    /**
     * Actualiza una orden por completo.
     *
     * @param orden La orden con los nuevos datos a actualizar.
     * @return La orden actualizada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Orden actualizarOrden(Orden orden) throws PersistenciaException;

    /**
     * Obtiene una lista con todas las órdenes registradas en el sistema.
     *
     * @return Lista de todas las órdenes encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Orden> obtenerOrdenes() throws PersistenciaException;

    /**
     * Obtiene una lista de órdenes que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * órdenes.
     * @return Lista de órdenes que cumplen con el filtro aplicado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Orden> obtenerOrdenes(FiltroOrdenPersistenciaDTO filtro) throws PersistenciaException;

    /**
     * Obtiene una orden en específico mediante su identificador único.
     *
     * @param id Identificador de la orden a buscar.
     * @return La orden correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Orden obtenerOrdenPorId(String id) throws PersistenciaException;

    /**
     * Obtiene una lista con todos los proveedores registrados en el sistema.
     *
     * @return Lista de todos los proveedores encontrados.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Proveedor> obtenerProveedores() throws PersistenciaException;

    /**
     * Obtiene una lista con todas las sucursales registradas en el sistema.
     *
     * @return Lista de todas las sucursales encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Sucursal> obtenerSucursales() throws PersistenciaException;

    /**
     * Obtiene una lista con todos los productos registrados en el sistema.
     *
     * @return Lista de todos los productos encontrados.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Producto> obtenerProductos() throws PersistenciaException;

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param idProducto Identificador del producto a buscar.
     * @return El producto correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Producto obtenerProductoPorId(String idProducto) throws PersistenciaException;

    /**
     * Incrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le incrementará el
     * stock.
     * @param cantidad Cantidad a incrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Producto incrementarStockProducto(String idProducto, int cantidad) throws PersistenciaException;

    /**
     * Decrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le decrementará el
     * stock.
     * @param cantidad Cantidad a decrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Producto decrementarStockProducto(String idProducto, int cantidad) throws PersistenciaException;

    /**
     * Guarda un nuevo registro de salida.
     *
     * @param salida Objeto con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    Salida guardarSalida(Salida salida) throws PersistenciaException;

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Salida> obtenerSalidas() throws PersistenciaException;

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    List<Salida> obtenerSalidas(FiltroSalidaPersistenciaDTO filtro) throws PersistenciaException;
}
