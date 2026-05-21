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
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.enums.RazonSalida;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.ISalidasBO;
import itson.org.ghosttracks.negocio.mappers.SalidaMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author nafbr
 */
public class SalidasBO implements ISalidasBO {

    private final IPersistenciaAbastecimiento persistencia;

    /**
     * Construye un nuevo objeto de negocio para salidas.
     */
    public SalidasBO() {
        this.persistencia = new PersistenciaFachada();
    }

    /**
     * Registra una nueva salida de productos.
     *
     * @param dto Objeto DTO con los datos de la salida a registrar.
     * @return Salida registrada con éxito.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public SalidaDTO registrarSalida(NuevaSalidaDTO dto) throws NegocioException {
        validarSalida(dto);
        Salida salida = SalidaMapper.toEntidad(dto);
        salida.setFolio(generarFolio());
        try {
            return SalidaMapper.toDTO(persistencia.guardarSalida(salida));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo guardar la salida: " + ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene una lista con todas las salidas registradas en el sistema.
     *
     * @return Lista de todas las salidas encontradas.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
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

    /**
     * Obtiene una lista de salidas que coincidan con los criterios de búsqueda
     * especificados.
     *
     * @param filtro Objeto DTO que contiene los criterios para filtrar las
     * salidas.
     * @return Lista de salidas que cumplen con el filtro aplicado.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
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
        if (dto.getSucursal() == null || dto.getRazon() == null || dto.getProductos() == null) {
            throw new NegocioException("Los datos base de salida son obligatorios.");
        }
        for (ProductoSalidaDTO producto : dto.getProductos()) {
            if (producto == null || producto.getProducto() == null) {
                throw new NegocioException("Los productos de salida son obligatorios.");
            }
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

    private String generarFolio() {
        return "GT-SAL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
