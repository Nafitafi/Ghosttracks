/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.entidades.SucursalRef;

/**
 *
 * @author nafbr
 */
public class SucursalMapper {
    public static SucursalDTO toSucursalDTO(SucursalRef ref) {
        if (ref == null) {
            return null;
        }
        Long id = null;
        try {
            id = Long.valueOf(ref.getIdSucursal());
        } catch (NumberFormatException ignored) {
        }
        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(id);
        dto.setNombre(ref.getNombre());
        return dto;
    }
}
