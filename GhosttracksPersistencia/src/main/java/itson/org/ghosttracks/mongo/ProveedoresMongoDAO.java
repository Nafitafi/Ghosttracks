/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import com.mongodb.client.MongoCollection;
import itson.org.ghosttracks.daos.IProveedoresDAO;
import itson.org.ghosttracks.entidades.Proveedor;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mappers.MongoDocumentoMapper;
import itson.org.ghosttracks.mappers.ProveedorMongoMapper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class ProveedoresMongoDAO implements IProveedoresDAO{

    private final MongoCollection<Document> coleccion;

    public ProveedoresMongoDAO() {
        this.coleccion = ManejadorConexion.obtenerBaseDatos().getCollection("Proveedores");
    }

    @Override
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            proveedores.add(ProveedorMongoMapper.toEntity(doc));
        }
        return proveedores;
    }

    @Override
    public Proveedor obtenerPorId(String idProveedor) throws PersistenciaException {
        Proveedor proveedor = ProveedorMongoMapper.toEntity(coleccion.find(MongoDocumentoMapper.filtroPorId(idProveedor)).first());
        if (proveedor == null) {
            throw new PersistenciaException("Proveedor no encontrado con el ID: " + idProveedor);
        }
        return proveedor;
    }
}
