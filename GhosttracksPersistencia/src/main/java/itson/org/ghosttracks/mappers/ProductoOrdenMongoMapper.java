/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.ProductoOrden;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProductoOrdenMongoMapper {

    public static Document toDocument(ProductoOrden producto) {
        if (producto == null) {
            return null;
        }
        return new Document("id_producto", producto.getIdProducto())
                .append("nombre_producto", producto.getNombreProducto())
                .append("cantidad", producto.getCantidadProducto())
                .append("precio_unitario", producto.getPrecioUnitario())
                .append("subtotal", producto.getImporteTotal())
                .append("recibido", producto.getRecibido());
    }

    public static ProductoOrden toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        ProductoOrden producto = new ProductoOrden();
        producto.setIdProducto(MongoDocumentoMapper.documentoAId(doc.get("id_producto")));
        producto.setNombreProducto(doc.getString("nombre_producto"));
        producto.setCantidadProducto(doc.getInteger("cantidad"));
        producto.setPrecioUnitario(MongoDocumentoMapper.documentoADouble(doc.get("precio_unitario")));
        producto.setImporteTotal(MongoDocumentoMapper.documentoADouble(doc.get("subtotal")));
        producto.setRecibido(doc.getBoolean("recibido", false));
        return producto;
    }

    public static List<Document> toDocumentList(List<ProductoOrden> productos) {
        List<Document> documentos = new ArrayList<>();
        if (productos == null) {
            return documentos;
        }
        for (ProductoOrden producto : productos) {
            Document documento = toDocument(producto);
            if (documento != null) {
                documentos.add(documento);
            }
        }
        return documentos;
    }

    public static List<ProductoOrden> toEntityList(List<Document> docs) {
        List<ProductoOrden> productos = new ArrayList<>();
        if (docs == null) {
            return productos;
        }
        for (Document doc : docs) {
            ProductoOrden producto = toEntity(doc);
            if (producto != null) {
                productos.add(producto);
            }
        }
        return productos;
    }
}
