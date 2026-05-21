/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.IProductosBO;
import itson.org.ghosttracks.negocio.mappers.ProductoMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class ProductosBO implements IProductosBO {

    private final IPersistenciaAbastecimiento persistencia;

    /**
     * Construye un nuevo objeto de negocio para productos.
     */
    public ProductosBO() {
        this.persistencia = new PersistenciaFachada();
    }

    /**
     * Obtiene una lista con todos los productos registrados en el sistema.
     *
     * @return Lista de todos los productos encontrados.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public List<Producto> obtenerTodos() throws NegocioException {
        try {
            return persistencia.obtenerProductos();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar productos en BD", e);
        }
    }

    /**
     * Obtiene una lista con todos los productos disponibles en el sistema.
     *
     * @return Lista de todos los productos disponibles encontrados.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public List<ProductoDTO> obtenerProductosDisponibles() throws NegocioException {
        return obtenerTodos().stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param id Identificador del producto a buscar.
     * @return El producto DTO correspondiente al ID proporcionado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public ProductoDTO obtenerProductoDTOPorId(String id) throws NegocioException {
        return ProductoMapper.toDTO(obtenerProductoPorId(id));
    }

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param id Identificador del producto a buscar.
     * @return El producto correspondiente al ID proporcionado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public Producto obtenerProductoPorId(String id) throws NegocioException {
        try {
            return persistencia.obtenerProductoPorId(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el producto", e);
        }
    }

    /**
     * Incrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le incrementará el
     * stock.
     * @param cantidad Cantidad a incrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public Producto incrementarStockProducto(String idProducto, int cantidad) throws NegocioException {
        validarIdProducto(idProducto);
        try {
            return persistencia.incrementarStockProducto(idProducto, cantidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al incrementar el stock del producto", e);
        }
    }

    /**
     * Decrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le decrementará el
     * stock.
     * @param cantidad Cantidad a decrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public Producto decrementarStockProducto(String idProducto, int cantidad) throws NegocioException {
        validarIdProducto(idProducto);
        try {
            return persistencia.decrementarStockProducto(idProducto, cantidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al decrementar el stock del producto", e);
        }
    }

    private void validarIdProducto(String idProducto) throws NegocioException {
        if (idProducto == null || idProducto.isBlank()) {
            throw new NegocioException("El identificador del producto es obligatorio.");
        }
    }
}
