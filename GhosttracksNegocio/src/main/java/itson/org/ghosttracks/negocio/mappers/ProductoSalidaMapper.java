/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoSalidaDTO;
import itson.org.ghosttracks.entidades.ProductoSalida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class ProductoSalidaMapper {

    public static ProductoSalida toEntidad(ProductoSalidaDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductoSalida salida = new ProductoSalida();
        ProductoDTO producto = dto.getProducto();
        if (producto != null) {
            salida.setIdProducto(producto.getIdProducto());
            salida.setNombreProducto(producto.getNombre());
        }
        salida.setCantidad(dto.getCantidad());
        return salida;
    }

    public static ProductoSalidaDTO toDTO(ProductoSalida entidad) {
        if (entidad == null) {
            return null;
        }
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(entidad.getIdProducto());
        producto.setNombre(entidad.getNombreProducto());
        return new ProductoSalidaDTO(producto, entidad.getCantidad());
    }

    public static List<ProductoSalida> toEntidades(List<ProductoSalidaDTO> productos) {
        List<ProductoSalida> entidades = new ArrayList<>();
        if (productos == null) {
            return entidades;
        }
        for (ProductoSalidaDTO producto : productos) {
            ProductoSalida entidad = toEntidad(producto);
            if (entidad != null) {
                entidades.add(entidad);
            }
        }
        return entidades;
    }

    public static List<ProductoSalidaDTO> toDTOs(List<ProductoSalida> productos) {
        List<ProductoSalidaDTO> dtos = new ArrayList<>();
        if (productos == null) {
            return dtos;
        }
        for (ProductoSalida producto : productos) {
            ProductoSalidaDTO dto = toDTO(producto);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
