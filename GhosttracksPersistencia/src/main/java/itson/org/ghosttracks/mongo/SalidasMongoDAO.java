/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import com.mongodb.client.MongoCollection;
import itson.org.ghosttracks.daos.ISalidasDAO;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mappers.MongoDocumentoMapper;
import itson.org.ghosttracks.mappers.SalidaMongoMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nafbr
 */
public class SalidasMongoDAO implements ISalidasDAO{

    private final MongoCollection<Document> coleccion;

    public SalidasMongoDAO() {
        this.coleccion = ManejadorConexion.obtenerBaseDatos().getCollection("salidas");
    }

    @Override
    public Salida insertar(Salida salida) throws PersistenciaException {
        if (salida == null) {
            throw new PersistenciaException("La salida no puede ser nula.");
        }
        if (salida.getIdSalida() == null) {
            salida.setIdSalida(MongoDocumentoMapper.nuevoId());
        }
        if (salida.getFechaSalida() == null) {
            salida.setFechaSalida(LocalDate.now());
        }
        coleccion.insertOne(SalidaMongoMapper.toDocument(salida));
        return salida;
    }

    @Override
    public List<Salida> obtenerTodos() {
        List<Salida> salidas = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            salidas.add(SalidaMongoMapper.toEntity(doc));
        }
        return salidas;
    }

    @Override
    public Salida obtenerPorId(String idSalida) throws PersistenciaException {
        Salida salida = SalidaMongoMapper.toEntity(coleccion.find(MongoDocumentoMapper.filtroPorId(idSalida)).first());
        if (salida == null) {
            throw new PersistenciaException("Salida no encontrada con el ID: " + idSalida);
        }
        return salida;
    }
}
