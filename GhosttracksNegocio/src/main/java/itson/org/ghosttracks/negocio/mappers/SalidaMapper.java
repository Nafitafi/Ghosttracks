/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.enums.RazonSalida;
import itson.org.ghosttracks.enums.RazonSalidaDTO;
import java.time.LocalDate;

/**
 *
 * @author nafbr
 */
public class SalidaMapper {

    public static Salida toEntidad(NuevaSalidaDTO dto) {
        if (dto == null) {
            return null;
        }
        Salida salida = new Salida();
        salida.setFechaSalida(LocalDate.now());
        salida.setComenatriosSalida(dto.getComentarios());
        salida.setRazon(dto.getRazon() != null ? RazonSalida.valueOf(dto.getRazon().name()) : null);
        if (dto.getSucursal() != null) {
            salida.setIdSucursal(dto.getSucursal().getIdSucursal());
            salida.setNombreSucursal(dto.getSucursal().getNombre());
        }
        salida.setProductosSalida(ProductoSalidaMapper.toEntidades(dto.getProductos()));
        return salida;
    }

    public static SalidaDTO toDTO(Salida salida) {
        if (salida == null) {
            return null;
        }
        SalidaDTO dto = new SalidaDTO();
        dto.setIdSalida(salida.getIdSalida());
        dto.setFolio(salida.getFolio());
        dto.setFechaSalida(salida.getFechaSalida());
        dto.setComentarios(salida.getComenatriosSalida());
        dto.setRazon(salida.getRazon() != null ? RazonSalidaDTO.valueOf(salida.getRazon().name()) : null);

        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setIdSucursal(salida.getIdSucursal());
        sucursal.setNombre(salida.getNombreSucursal());
        dto.setSucursal(sucursal);
        dto.setProductos(ProductoSalidaMapper.toDTOs(salida.getProductosSalida()));
        return dto;
    }
}
