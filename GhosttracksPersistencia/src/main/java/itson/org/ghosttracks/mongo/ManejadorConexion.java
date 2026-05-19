/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mongo;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author nafbr
 */
public class ManejadorConexion {
    public static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String BASE_DATOS = "ghosttracks";

    private static MongoClient cliente;

    private ManejadorConexion() {
    }

    public static MongoClient crearConexion() {
        return MongoClients.create(CADENA_CONEXION);
    }

    public static CodecRegistry obtenerCodecs() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        return fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }

    public static synchronized MongoDatabase obtenerBaseDatos() {
        if (cliente == null) {
            cliente = crearConexion();
        }
        return cliente.getDatabase(BASE_DATOS).withCodecRegistry(obtenerCodecs());
    }
}
