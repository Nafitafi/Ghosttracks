/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mappers;

import com.mongodb.client.model.Filters;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

/**
 *
 * @author nafbr
 */
public class MongoDocumentoMapper {

    public static String nuevoId() {
        return new ObjectId().toHexString();
    }

    public static Bson filtroPorId(String id) {
        if (id == null || id.isBlank()) {
            return Filters.eq("_id", null);
        }
        if (ObjectId.isValid(id)) {
            return Filters.or(Filters.eq("_id", id), Filters.eq("_id", new ObjectId(id)));
        }
        return Filters.eq("_id", id);
    }

    public static String documentoAId(Object id) {
        if (id == null) {
            return null;
        }
        if (id instanceof ObjectId objectId) {
            return objectId.toHexString();
        }
        return String.valueOf(id);
    }

    public static String idParaGuardar(String id) {
        return id == null || id.isBlank() ? nuevoId() : id;
    }

    public static String nombreEnum(Enum<?> valor) {
        return valor == null ? null : valor.name();
    }

    public static Double documentoADouble(Object valor) {
        if (valor == null) {
            return null;
        }
        if (valor instanceof Number numero) {
            return numero.doubleValue();
        }
        if (valor instanceof String texto && !texto.isBlank()) {
            return Double.valueOf(texto);
        }
        return null;
    }

    public static byte[] documentoABytes(Object valor) {
        if (valor instanceof byte[] bytes) {
            return bytes;
        }
        if (valor instanceof Binary binary) {
            return binary.getData();
        }
        return null;
    }

    public static <T extends Enum<T>> T enumValue(Class<T> tipo, String valor) {
        return valor == null ? null : Enum.valueOf(tipo, valor);
    }

    public static Date fechaADate(LocalDate fecha) {
        return fecha == null ? null : Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date fechaHoraADate(LocalDateTime fecha) {
        return fecha == null ? null : Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateALocalDate(Date fecha) {
        return fecha == null ? null : fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateALocalDateTime(Date fecha) {
        return fecha == null ? null : fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
