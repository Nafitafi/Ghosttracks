/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import itson.org.ghosttracks.daos.IProductosDAO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mappers.MongoDocumentoMapper;
import itson.org.ghosttracks.mappers.ProductoMongoMapper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProductosMongoDAO implements IProductosDAO {

    private final MongoCollection<Document> coleccion;

    public ProductosMongoDAO() {
        this.coleccion = ManejadorConexion.obtenerBaseDatos().getCollection("Productos");
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            productos.add(ProductoMongoMapper.toEntity(doc));
        }
        return productos;
    }

    @Override
    public Producto buscarPorId(String idProducto) throws PersistenciaException {
        Producto producto = ProductoMongoMapper.toEntity(coleccion.find(MongoDocumentoMapper.filtroPorId(idProducto)).first());
        if (producto == null) {
            throw new PersistenciaException("Producto no encontrado con el ID: " + idProducto);
        }
        return producto;
    }

    @Override
    public Producto agregar(ProductoDTO productoDTO) throws PersistenciaException {
        Producto producto = new Producto(MongoDocumentoMapper.nuevoId(), productoDTO.getNombre(), productoDTO.getImgProducto(),
                productoDTO.getTipoProducto(), productoDTO.getArtista(), productoDTO.getGenero(),
                productoDTO.getSetlist(), productoDTO.getPrecio(), productoDTO.getStock(), productoDTO.getEstado());
        coleccion.insertOne(ProductoMongoMapper.toDocument(producto));
        return producto;
    }

    @Override
    public Producto incrementarStock(String idProducto, int cantidad) throws PersistenciaException {
        if (idProducto == null || idProducto.isBlank()) {
            throw new PersistenciaException("El ID del producto es obligatorio para incrementar stock.");
        }
        if (cantidad <= 0) {
            throw new PersistenciaException("La cantidad a incrementar debe ser mayor a cero.");
        }
        Producto producto = buscarPorId(idProducto);
        coleccion.updateOne(MongoDocumentoMapper.filtroPorId(idProducto), Updates.inc("stock_actual", cantidad));
        producto.setStock((producto.getStock() != null ? producto.getStock() : 0) + cantidad);
        return producto;
    }
}
