/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Proveedor;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProveedorMongoMapper {

    public static Document toDocument(Proveedor proveedor) {
        String id = MongoDocumentoMapper.idParaGuardar(proveedor.getIdProveedor());
        proveedor.setIdProveedor(id);
        return new Document("_id", id)
                .append("nombre_comercial", proveedor.getNombreProovedor())
                .append("telefono", proveedor.getNumeroTelefono())
                .append("direccion", DireccionMongoMapper.toDocument(proveedor.getDireccionProovedor()));
    }

    public static Proveedor toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        return new Proveedor(
                MongoDocumentoMapper.documentoAId(doc.get("_id")),
                doc.getString("nombre_comercial"),
                DireccionMongoMapper.toEntity(doc.get("direccion", Document.class)),
                doc.getString("telefono"));
    }
}
