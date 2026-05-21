package itson.org.ghosttracks.daos;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author emyla
 */
public interface IProductosDAO {

    /**
     * Obtiene una lista con todos los productos registrados en el sistema.
     *
     * @return Lista de todos los productos encontrados.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract List<Producto> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene un producto en específico mediante su identificador único.
     *
     * @param idProducto Identificador del producto a buscar.
     * @return El producto correspondiente al ID proporcionado.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Producto buscarPorId(String idProducto) throws PersistenciaException;

    /**
     * Agrega un nuevo registro de producto.
     *
     * @param producto Objeto DTO con los datos del producto a registrar.
     * @return Producto registrado con éxito.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Producto agregar(ProductoDTO producto) throws PersistenciaException;

    /**
     * Incrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le incrementará el
     * stock.
     * @param cantidad Cantidad a incrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Producto incrementarStock(String idProducto, int cantidad) throws PersistenciaException;

    /**
     * Decrementa la cantidad en stock de un producto específico.
     *
     * @param idProducto Identificador del producto al que se le decrementará el
     * stock.
     * @param cantidad Cantidad a decrementar en el stock.
     * @return El producto actualizado con el nuevo stock.
     * @throws PersistenciaException En caso de algun fallo en persistencia.
     */
    public abstract Producto decrementarStock(String idProducto, int cantidad) throws PersistenciaException;
}
