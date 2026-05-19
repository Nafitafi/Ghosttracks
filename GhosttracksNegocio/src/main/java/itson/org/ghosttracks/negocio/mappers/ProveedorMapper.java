/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.entidades.Proveedor;
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

    public static ProveedorDTO toProveedorDTO(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }
        return new ProveedorDTO(proveedor.getIdProveedor(), proveedor.getNombreProovedor());
    }

    public static ProveedorRef toRef(ProveedorDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ProveedorRef(dto.getIdProveedor(), dto.getNombreProveedor());
    }
}
