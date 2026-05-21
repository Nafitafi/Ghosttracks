/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.dtos.FiltroOrdenPersistenciaDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mappers.MongoDocumentoMapper;
import itson.org.ghosttracks.mappers.OrdenMongoMapper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author nafbr
 */
public class OrdenesMongoDAO implements IOrdenesDAO {

     private final MongoCollection<Document> coleccion;

    public OrdenesMongoDAO() {
        this.coleccion = ManejadorConexion.obtenerBaseDatos().getCollection("Ordenes");
    }

    @Override
    public Orden insertarOrden(Orden orden) throws PersistenciaException {
        if (orden == null) {
            throw new PersistenciaException("La orden a insertar no puede ser nula.");
        }
        if (orden.getIdOrden() == null) {
            orden.setIdOrden(MongoDocumentoMapper.nuevoId());
        }
        coleccion.insertOne(OrdenMongoMapper.toDocument(orden));
        return orden;
    }

    @Override
    public Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException {
        Orden orden = obtenerPorId(idOrden);
        orden.setEstado(estado);
        return actualizarOrdenCompleta(orden);
    }

    @Override
    public Orden actualizarOrdenCompleta(Orden orden) throws PersistenciaException {
        if (orden == null || orden.getIdOrden() == null) {
            throw new PersistenciaException("La orden a actualizar debe tener identificador.");
        }
        Document documento = OrdenMongoMapper.toDocument(orden);
        coleccion.replaceOne(MongoDocumentoMapper.filtroPorId(orden.getIdOrden()), documento, new ReplaceOptions().upsert(false));
        return orden;
    }

    @Override
    public List<Orden> obtenerTodos() {
        List<Orden> ordenes = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            ordenes.add(OrdenMongoMapper.toEntity(doc));
        }
        return ordenes;
    }

    @Override
    public Orden obtenerPorId(String id) throws PersistenciaException {
        Orden orden = OrdenMongoMapper.toEntity(coleccion.find(MongoDocumentoMapper.filtroPorId(id)).first());
        if (orden == null) {
            throw new PersistenciaException("Orden no encontrada con el ID: " + id);
        }
        return orden;
    }
    
    @Override
    public List<Orden> obtenerPorFiltro(FiltroOrdenPersistenciaDTO filtro) {
        Bson consulta = construirFiltro(filtro);
        List<Orden> ordenes = new ArrayList<>();
        for (Document doc : coleccion.find(consulta)) {
            ordenes.add(OrdenMongoMapper.toEntity(doc));
        }
        return ordenes;
    }
    
    private Bson construirFiltro(FiltroOrdenPersistenciaDTO filtro) {
        if (filtro == null) {
            return new Document();
        }
        List<Bson> condiciones = new ArrayList<>();
        if (filtro.getIdProveedor() != null && !filtro.getIdProveedor().isBlank()) {
            condiciones.add(Filters.eq("proveedor.id_proveedor", filtro.getIdProveedor()));
        }
        if (filtro.getEstado() != null) {
            condiciones.add(Filters.eq("estado", filtro.getEstado().name()));
        }
        if (filtro.getTipoOrden() != null) {
            condiciones.add(Filters.eq("tipo_orden", filtro.getTipoOrden().name()));
        }
        if (filtro.getFechaInicio() != null) {
            condiciones.add(Filters.gte("fecha_entrega", MongoDocumentoMapper.fechaADate(filtro.getFechaInicio())));
        }
        if (filtro.getFechaFin() != null) {
            condiciones.add(Filters.lte("fecha_entrega", MongoDocumentoMapper.fechaADate(filtro.getFechaFin())));
        }
        return condiciones.isEmpty() ? new Document() : Filters.and(condiciones);
    }
}
