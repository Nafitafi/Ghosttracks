/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import com.mongodb.client.MongoCollection;
import itson.org.ghosttracks.daos.ISucursalesDAO;
import itson.org.ghosttracks.entidades.Sucursal;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mappers.MongoDocumentoMapper;
import itson.org.ghosttracks.mappers.SucursalMongoMapper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class SucursalesMongoDAO implements ISucursalesDAO{

    private final MongoCollection<Document> coleccion;

    public SucursalesMongoDAO() {
        this.coleccion = ManejadorConexion.obtenerBaseDatos().getCollection("Sucursales");
    }

    @Override
    public List<Sucursal> obtenerTodos() {
        List<Sucursal> sucursales = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            sucursales.add(SucursalMongoMapper.toEntity(doc));
        }
        return sucursales;
    }

    @Override
    public Sucursal obtenerPorId(String idSucursal) throws PersistenciaException {
        Sucursal sucursal = SucursalMongoMapper.toEntity(coleccion.find(MongoDocumentoMapper.filtroPorId(idSucursal)).first());
        if (sucursal == null) {
            throw new PersistenciaException("Sucursal no encontrada con el ID: " + idSucursal);
        }
        return sucursal;
    }
}
