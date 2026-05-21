/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.enums.RazonSalida;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class SalidaMongoMapper {

    public static Document toDocument(Salida salida) {
        String id = MongoDocumentoMapper.idParaGuardar(salida.getIdSalida());
        salida.setIdSalida(id);
        return new Document("_id", id)
                .append("folio", salida.getFolio())
                .append("fecha_salida", MongoDocumentoMapper.fechaADate(salida.getFechaSalida()))
                .append("razon_salida", MongoDocumentoMapper.nombreEnum(salida.getRazon()))
                .append("comentarios", salida.getComenatriosSalida())
                .append("sucursal", sucursalADocumento(salida))
                .append("productos_salida", ProductoSalidaMongoMapper.toDocumentList(salida.getProductosSalida()));
    }

    public static Salida toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        Salida salida = new Salida();
        salida.setIdSalida(MongoDocumentoMapper.documentoAId(doc.get("_id")));
        salida.setFolio(doc.getString("folio"));
        salida.setFechaSalida(MongoDocumentoMapper.dateALocalDate(doc.getDate("fecha_salida")));
        salida.setRazon(MongoDocumentoMapper.enumValue(RazonSalida.class, doc.getString("razon_salida")));
        salida.setComenatriosSalida(doc.getString("comentarios"));
        Document sucursal = doc.get("sucursal", Document.class);
        if (sucursal != null) {
            salida.setIdSucursal(MongoDocumentoMapper.documentoAId(sucursal.get("id_sucursal")));
            salida.setNombreSucursal(sucursal.getString("nombre"));
        }
        salida.setProductosSalida(ProductoSalidaMongoMapper.toEntityList(doc.getList("productos_salida", Document.class)));
        return salida;
    }

    private static Document sucursalADocumento(Salida salida) {
        return new Document("id_sucursal", salida.getIdSucursal())
                .append("nombre", salida.getNombreSucursal());
    }
}
