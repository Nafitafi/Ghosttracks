/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.FiltroSalidaDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaPersistenciaDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.ProductoSalidaDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.entidades.ProductoSalida;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.enums.RazonSalida;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.ISalidasBO;
import itson.org.ghosttracks.negocio.mappers.SalidaMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nafbr
 */
public class SalidasBO implements ISalidasBO {

    private final IPersistenciaAbastecimiento persistencia;

    public SalidasBO() {
        this.persistencia = new PersistenciaFachada();
    }

    @Override
    public SalidaDTO registrarSalida(NuevaSalidaDTO dto) throws NegocioException {
        validarSalida(dto);
        Salida salida = SalidaMapper.toEntidad(dto);
        try {
            decrementarStockProductosSalida(salida.getProductosSalida());
            return SalidaMapper.toDTO(persistencia.guardarSalida(salida));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo guardar la salida: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<SalidaDTO> obtenerSalidas() throws NegocioException {
        try {
            return persistencia.obtenerSalidas().stream()
                    .map(SalidaMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las salidas: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<SalidaDTO> obtenerSalidas(FiltroSalidaDTO filtro) throws NegocioException {
        try {
            return persistencia.obtenerSalidas(mapearFiltroPersistencia(filtro)).stream()
                    .map(SalidaMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las salidas filtradas: " + ex.getMessage(), ex);
        }
    }

    private void validarSalida(NuevaSalidaDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos de salida son obligatorios.");
        }
        if (dto.getSucursal() == null) {
            throw new NegocioException("Debe seleccionar una sucursal.");
        }
        if (dto.getRazon() == null) {
            throw new NegocioException("Debe seleccionar la razon de salida.");
        }
        if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
            throw new NegocioException("Debe agregar al menos un producto.");
        }
        for (ProductoSalidaDTO producto : dto.getProductos()) {
            if (producto.getCantidad() <= 0) {
                throw new NegocioException("La cantidad de salida debe ser mayor a cero.");
            }
        }
    }

    private void decrementarStockProductosSalida(List<ProductoSalida> productosSalida) throws PersistenciaException {
        if (productosSalida == null) {
            return;
        }
        for (ProductoSalida productoSalida : productosSalida) {
            Integer cantidad = productoSalida.getCantidad();
            if (cantidad == null || cantidad <= 0) {
                throw new PersistenciaException("La cantidad de salida debe ser mayor a cero.");
            }
            Producto producto = persistencia.obtenerProductoPorId(productoSalida.getIdProducto());
            int stockActual = producto.getStock() != null ? producto.getStock() : 0;
            if (stockActual < cantidad) {
                throw new PersistenciaException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }
        for (ProductoSalida productoSalida : productosSalida) {
            persistencia.decrementarStockProducto(productoSalida.getIdProducto(), productoSalida.getCantidad());
        }
    }

    private FiltroSalidaPersistenciaDTO mapearFiltroPersistencia(FiltroSalidaDTO filtro) {
        if (filtro == null) {
            return null;
        }
        FiltroSalidaPersistenciaDTO filtroPersistencia = new FiltroSalidaPersistenciaDTO();
        filtroPersistencia.setRazon(filtro.getRazon() != null ? RazonSalida.valueOf(filtro.getRazon().name()) : null);
        filtroPersistencia.setFechaInicio(filtro.getFechaInicio());
        filtroPersistencia.setFechaFin(filtro.getFechaFin());
        return filtroPersistencia;
    }
}
