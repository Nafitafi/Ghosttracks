/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.fachada;

import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.mocks.OrdenesMockDAO;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class PersistenciaFachada implements IPersistenciaAbastecimiento {

    private final IOrdenesDAO ordenesDAO;

    public PersistenciaFachada() {
        this.ordenesDAO = new OrdenesMockDAO();
    }

    @Override
    public Orden insertar(Orden orden) throws PersistenciaException {
        try {
            return ordenesDAO.insertar(orden);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException(ex.getMessage());
        }

    }

    @Override
    public Orden actualizar(Long idOrden, EstadoOrden estado) throws PersistenciaException {
        try {
            return ordenesDAO.actualizar(idOrden, estado);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException(ex.getMessage());
        }

    }

    @Override
    public List<Orden> obtenerTodos() throws PersistenciaException {
        try {
            return ordenesDAO.obtenerTodos();
        } catch (PersistenciaException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }

    @Override
    public Orden obtenerPorId(Long id) throws PersistenciaException {
        try {
            return ordenesDAO.obtenerPorId(id);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException(ex.getMessage());
        }
    }

}
