/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.ProductoSalida;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProductoSalidaMongoMapper {

    public static Document toDocument(ProductoSalida producto) {
        if (producto == null) {
            return null;
        }
        return new Document("id_producto", producto.getIdProducto())
                .append("nombre", producto.getNombreProducto())
                .append("cantidad", producto.getCantidad());
    }

    public static ProductoSalida toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        return new ProductoSalida(
                MongoDocumentoMapper.documentoAId(doc.get("id_producto")),
                doc.getString("nombre"),
                doc.getInteger("cantidad"));
    }

    public static List<Document> toDocumentList(List<ProductoSalida> productos) {
        List<Document> documentos = new ArrayList<>();
        if (productos == null) {
            return documentos;
        }
        for (ProductoSalida producto : productos) {
            Document documento = toDocument(producto);
            if (documento != null) {
                documentos.add(documento);
            }
        }
        return documentos;
    }

    public static List<ProductoSalida> toEntityList(List<Document> docs) {
        List<ProductoSalida> productos = new ArrayList<>();
        if (docs == null) {
            return productos;
        }
        for (Document doc : docs) {
            ProductoSalida producto = toEntity(doc);
            if (producto != null) {
                productos.add(producto);
            }
        }
        return productos;
    }
}
