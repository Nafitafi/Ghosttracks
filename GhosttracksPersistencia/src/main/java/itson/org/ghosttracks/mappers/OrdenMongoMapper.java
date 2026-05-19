/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.ProveedorRef;
import itson.org.ghosttracks.entidades.SucursalRef;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.TipoOrden;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class OrdenMongoMapper {


    public static Document toDocument(Orden orden) {
        String id = MongoDocumentoMapper.idParaGuardar(orden.getIdOrden());
        orden.setIdOrden(id);
        return new Document("_id", id)
                .append("fecha_solicitud", MongoDocumentoMapper.fechaHoraADate(orden.getFechaSolicitud()))
                .append("fecha_entrega", MongoDocumentoMapper.fechaADate(orden.getFechaEntregaEstimada()))
                .append("fecha_recepcion", MongoDocumentoMapper.fechaHoraADate(orden.getFechaEntrega()))
                .append("imagen_recepcion", orden.getImagen())
                .append("folio", orden.getFolio())
                .append("tipo_orden", MongoDocumentoMapper.nombreEnum(orden.getTipoOrden()))
                .append("comentarios", orden.getComentarios())
                .append("estado", MongoDocumentoMapper.nombreEnum(orden.getEstado()))
                .append("total_orden", orden.getTotal())
                .append("proveedor", proveedorRefADocumento(orden.getProveedor()))
                .append("sucursal", sucursalRefADocumento(orden.getSucursal()))
                .append("detalles_productos", ProductoOrdenMongoMapper.toDocumentList(orden.getProductosOrden()));
    }

    public static Orden toEntity(Document doc) {
        if (doc == null) {
            return null;
        }
        Orden orden = new Orden();
        orden.setIdOrden(MongoDocumentoMapper.documentoAId(doc.get("_id")));
        orden.setFechaSolicitud(MongoDocumentoMapper.dateALocalDateTime(doc.getDate("fecha_solicitud")));
        orden.setFecha(MongoDocumentoMapper.dateALocalDateTime(doc.getDate("fecha_solicitud")));
        orden.setFechaEntregaEstimada(MongoDocumentoMapper.dateALocalDate(doc.getDate("fecha_entrega")));
        orden.setFechaEntrega(MongoDocumentoMapper.dateALocalDateTime(doc.getDate("fecha_recepcion")));
        orden.setImagen(MongoDocumentoMapper.documentoABytes(doc.get("imagen_recepcion")));
        orden.setFolio(doc.getString("folio"));
        orden.setTipoOrden(MongoDocumentoMapper.enumValue(TipoOrden.class, doc.getString("tipo_orden")));
        orden.setComentarios(doc.getString("comentarios"));
        orden.setEstado(MongoDocumentoMapper.enumValue(EstadoOrden.class, doc.getString("estado")));
        orden.setTotal(MongoDocumentoMapper.documentoADouble(doc.get("total_orden")));
        orden.setProveedor(documentoAProveedorRef(doc.get("proveedor", Document.class)));
        orden.setSucursal(documentoASucursalRef(doc.get("sucursal", Document.class)));
        orden.setProductosOrden(ProductoOrdenMongoMapper.toEntityList(doc.getList("detalles_productos", Document.class)));
        if (orden.getFolio() == null || orden.getFolio().isBlank()) {
            orden.setFolio("ORD-" + orden.getIdOrden().substring(Math.max(0, orden.getIdOrden().length() - 6)).toUpperCase());
        }
        return orden;
    }

    private static Document proveedorRefADocumento(ProveedorRef proveedor) {
        if (proveedor == null) {
            return null;
        }
        return new Document("id_proveedor", proveedor.getIdProveedor())
                .append("nombre", proveedor.getNombre());
    }

    private static ProveedorRef documentoAProveedorRef(Document doc) {
        if (doc == null) {
            return null;
        }
        return new ProveedorRef(MongoDocumentoMapper.documentoAId(doc.get("id_proveedor")), doc.getString("nombre"));
    }

    private static Document sucursalRefADocumento(SucursalRef sucursal) {
        if (sucursal == null) {
            return null;
        }
        return new Document("id_sucursal", sucursal.getIdSucursal())
                .append("nombre", sucursal.getNombre());
    }

    private static SucursalRef documentoASucursalRef(Document doc) {
        if (doc == null) {
            return null;
        }
        return new SucursalRef(MongoDocumentoMapper.documentoAId(doc.get("id_sucursal")), doc.getString("nombre"));
    }
}
