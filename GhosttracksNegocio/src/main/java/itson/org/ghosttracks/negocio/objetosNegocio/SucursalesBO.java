/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.negocio.objetosNegocio;

import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
import itson.org.ghosttracks.fachada.PersistenciaFachada;
import itson.org.ghosttracks.negocio.interfaces.ISucursalesBO;
import itson.org.ghosttracks.negocio.mappers.SucursalMapper;
import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nafbr
 */
public class SucursalesBO implements ISucursalesBO {

    private final IPersistenciaAbastecimiento persistencia;

    public SucursalesBO() {
        this.persistencia = new PersistenciaFachada();
    }

    @Override
    public List<SucursalDTO> obtenerTodos() throws NegocioException {
        try {
            return persistencia.obtenerSucursales().stream()
                    .map(SucursalMapper::toSucursalDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las sucursales: " + ex.getMessage(), ex);
        }
    }
}
