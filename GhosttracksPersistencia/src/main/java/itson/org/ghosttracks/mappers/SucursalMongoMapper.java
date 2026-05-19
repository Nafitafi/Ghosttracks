/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Sucursal;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class SucursalMongoMapper {
    
    public static Document toDocument(Sucursal sucursal) {
        String id = MongoDocumentoMapper.idParaGuardar(sucursal.getIdSucursal());
        sucursal.setIdSucursal(id);
        return new Document("_id", id)
                .append("nombre_sucursal", sucursal.getNombre())
                .append("direccion", DireccionMongoMapper.toDocument(sucursal.getDireccion()))
                .append("telefono", sucursal.getTelefono());
    }

    public static Sucursal toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        return new Sucursal(
                MongoDocumentoMapper.documentoAId(doc.get("_id")),
                DireccionMongoMapper.toEntity(doc.get("direccion", Document.class)),
                doc.getString("telefono"),
                doc.getString("nombre_sucursal"));
    }
}
