/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.IProveedoresBO;
import itson.org.ghosttracks.negocio.mappers.ProveedorMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nafbr
 */
public class ProveedoresBO implements IProveedoresBO {

    private final IPersistenciaAbastecimiento persistencia;

    /**
     * Construye un nuevo objeto de negocio para proveedores.
     */
    public ProveedoresBO() {
        this.persistencia = new PersistenciaFachada();
    }

    /**
     * Obtiene una lista con todos los proveedores registrados en el sistema.
     *
     * @return Lista de todos los proveedores encontrados.
     * @throws NegocioException En caso de algun fallo en negocio.
     */
    @Override
    public List<ProveedorDTO> obtenerTodos() throws NegocioException {
        try {
            return persistencia.obtenerProveedores().stream()
                    .map(ProveedorMapper::toProveedorDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener los proveedores: " + ex.getMessage(), ex);
        }
    }
}
