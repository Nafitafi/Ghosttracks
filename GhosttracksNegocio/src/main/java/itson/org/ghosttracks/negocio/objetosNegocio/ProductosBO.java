/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.IProductosBO;
import itson.org.ghosttracks.negocio.mappers.ProductoMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class ProductosBO implements IProductosBO {

    private final IPersistenciaAbastecimiento persistencia;

    public ProductosBO() {
        this.persistencia = new PersistenciaFachada();
    }

    @Override
    public List<Producto> obtenerTodos() throws NegocioException {
        try {
            return persistencia.obtenerProductos();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar productos en BD", e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductosDisponibles() throws NegocioException {
        return obtenerTodos().stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public Producto obtenerProductoPorId(String id) throws NegocioException {
        try {
            return persistencia.obtenerProductoPorId(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el producto", e);
        }
    }
}
