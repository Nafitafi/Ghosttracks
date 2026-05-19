/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Direccion;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class DireccionMongoMapper {

    public static Document toDocument(Direccion direccion) {
        return direccion == null ? null : new Document("calle", direccion.getCalle())
                .append("numero", direccion.getNumero())
                .append("colonia", direccion.getColonia())
                .append("ciudad", direccion.getCiudad())
                .append("codigo_postal", direccion.getCodigoPostal());
    }

    public static Direccion toEntity(Document doc) {
        return doc == null ? null : new Direccion(doc.getString("calle"), doc.getString("colonia"),
                doc.getString("numero"), doc.getString("ciudad"), doc.getString("codigo_postal"));
    }
}
