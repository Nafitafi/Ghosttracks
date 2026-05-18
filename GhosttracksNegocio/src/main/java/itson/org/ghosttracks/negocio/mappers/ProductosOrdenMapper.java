/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.mappers;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.entidades.ProductoOrden;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class ProductosOrdenMapper {
    public static List<ProductoOrdenDTO> toProductosOrdenDTO(List<ProductoOrden> productos) {
        List<ProductoOrdenDTO> lista = new ArrayList<>();
        if (productos == null) {
            return lista;
        }
        for (ProductoOrden po : productos) {
            ProductoOrdenDTO item = new ProductoOrdenDTO();
            if (po.getIdProducto() != null) {
                ProductoDTO producto = new ProductoDTO();
                producto.setIdProducto(po.getIdProducto());
                producto.setNombre("Producto #" + po.getIdProducto());
                item.setProducto(producto);
            }
            item.setCantidadProducto(po.getCantidadProducto() != null ? po.getCantidadProducto() : 0);
            item.setPrecioUnitario(po.getPrecioUnitario() != null ? po.getPrecioUnitario() : 0);
            item.setImporteTotal(po.getImporteTotal() != null ? po.getImporteTotal() : 0);
            item.setRecibido(po.getRecibido() != null && po.getRecibido());
            lista.add(item);
        }
        return lista;
    }
}
