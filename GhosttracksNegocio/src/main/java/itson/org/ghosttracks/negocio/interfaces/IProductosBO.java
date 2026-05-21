/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.interfaces;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IProductosBO {

    /**
     * Obtiene una lista con todos los productos registrados en el sistema.
     *
     * @return Lista de todos los productos encontrados.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract List<Producto> obtenerTodos() throws NegocioException;

    /**
     * Obtiene una lista con todos los productos disponibles en el sistema.
     *
     * @return Lista de todos los productos disponibles encontrados.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract List<ProductoDTO> obtenerProductosDisponibles() throws NegocioException;

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param id Identificador del producto a buscar.
     * @return El producto DTO correspondiente al ID proporcionado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract ProductoDTO obtenerProductoDTOPorId(String id) throws NegocioException;

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param id Identificador del producto a buscar.
     * @return El producto correspondiente al ID proporcionado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract Producto obtenerProductoPorId(String id) throws NegocioException;

    /**
     * Incrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le incrementará el
     * stock.
     * @param cantidad Cantidad a incrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract Producto incrementarStockProducto(String idProducto, int cantidad) throws NegocioException;

    /**
     * Decrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le decrementará el
     * stock.
     * @param cantidad Cantidad a decrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    public abstract Producto decrementarStockProducto(String idProducto, int cantidad) throws NegocioException;
}
