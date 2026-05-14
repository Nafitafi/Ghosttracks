/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mocks;

import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock DAO de Ordenes.
 *
 * @author nafbr
 */
public class OrdenesMockDAO implements IOrdenesDAO {

    private List<Orden> ordenesDB;
    private Long generadorId;

    public OrdenesMockDAO() {
        this.ordenesDB = new ArrayList<>();
        this.generadorId = 1L;
        
        // Mock para manipularlo
        Orden o1 = new Orden();
        o1.setIdOrden(generadorId++);
        o1.setFolio("ORD-001");
        o1.setComentarios("Entrega urgente por la mañana.");
        o1.setTotal(2450.75);
        o1.setFechaEntregaEst(LocalDate.now().plusDays(10));
        o1.setFechaSolicitud(LocalDateTime.now());
        o1.setIdProovedor(10L);
        o1.setIdSucursal(5L);
        o1.setTipoOrden(TipoOrden.PLANIFICADA); 
        o1.setEstadoOrden(EstadoOrden.CONFIRMADO);
        
        ordenesDB.add(o1);
    }

    @Override
    public Orden insertar(Orden orden) throws PersistenciaException {
        if (orden == null) {
            throw new PersistenciaException("La orden a insertar no puede ser nula.");
        }
        orden.setIdOrden(generadorId++);
        ordenesDB.add(orden);
        
        return orden;
    }

    @Override
    public Orden actualizar(Long idOrden, EstadoOrden estado) throws PersistenciaException {
        for (Orden orden : ordenesDB) {
            if (orden.getIdOrden().equals(idOrden)) {
                orden.setEstadoOrden(estado);
                return orden;
            }
        }
        throw new PersistenciaException("No se pudo actualizar. Orden no encontrada con el ID: " + idOrden);
    }

    @Override
    public List<Orden> obtenerTodos() throws PersistenciaException {
        return new ArrayList<>(ordenesDB);
    }

    @Override
    public Orden obtenerPorId(Long id) throws PersistenciaException {
        for (Orden orden : ordenesDB) {
            if (orden.getIdOrden().equals(id)) {
                return orden;
            }
        }
        throw new PersistenciaException("Orden no encontrada con el ID: " + id);
    }

}
