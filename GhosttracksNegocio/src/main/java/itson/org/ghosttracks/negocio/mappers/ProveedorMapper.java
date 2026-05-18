/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.entidades.ProveedorRef;

/**
 *
 * @author nafbr
 */
public class ProveedorMapper {
    public static ProveedorDTO toProveedorDTO(ProveedorRef ref) {
        if (ref == null) {
            return null;
        }
        return new ProveedorDTO(ref.getIdProveedor(), ref.getNombre());
    }
}
