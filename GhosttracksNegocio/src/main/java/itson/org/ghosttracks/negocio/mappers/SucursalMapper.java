/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.entidades.Sucursal;
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
        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(ref.getIdSucursal());
        dto.setNombre(ref.getNombre());
        return dto;
    }

    public static SucursalDTO toSucursalDTO(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }
        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(sucursal.getIdSucursal());
        dto.setNombre(sucursal.getNombre());
        dto.setTelefono(sucursal.getTelefono());
        return dto;
    }

    public static SucursalRef toRef(SucursalDTO dto) {
        if (dto == null) {
            return null;
        }
        return new SucursalRef(dto.getIdSucursal(), dto.getNombre());
    }
}
