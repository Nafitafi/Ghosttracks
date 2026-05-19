/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.enums.EstadoProducto;
import itson.org.ghosttracks.enums.TipoProducto;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProductoMongoMapper {

    public static Document toDocument(Producto producto) {
        String id = MongoDocumentoMapper.idParaGuardar(producto.getIdProducto());
        producto.setIdProducto(id);
        return new Document("_id", id)
                .append("nombre", producto.getNombre())
                .append("imagen", producto.getImgProducto())
                .append("tipo", MongoDocumentoMapper.nombreEnum(producto.getTipo()))
                .append("artista", producto.getArtista())
                .append("genero", producto.getGenero())
                .append("setlist", producto.getSetlist())
                .append("estado", MongoDocumentoMapper.nombreEnum(producto.getEstado()))
                .append("stock_actual", producto.getStock())
                .append("precio_base", producto.getPrecio());
    }

    public static Producto toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setIdProducto(MongoDocumentoMapper.documentoAId(doc.get("_id")));
        producto.setNombre(doc.getString("nombre"));
        producto.setImgProducto(doc.getString("imagen"));
        producto.setStock(doc.getInteger("stock_actual"));
        producto.setPrecio(MongoDocumentoMapper.documentoADouble(doc.get("precio_base")));
        TipoProducto tipo = MongoDocumentoMapper.enumValue(TipoProducto.class, doc.getString("tipo"));
        EstadoProducto estado = MongoDocumentoMapper.enumValue(EstadoProducto.class, doc.getString("estado"));
        producto.setTipo(tipo != null ? tipo : TipoProducto.VINILO);
        producto.setArtista(doc.getString("artista"));
        producto.setGenero(doc.getString("genero"));
        producto.setSetlist(doc.getList("setlist", String.class));
        producto.setEstado(estado != null ? estado : EstadoProducto.DISPONIBLE);
        return producto;
    }
}
