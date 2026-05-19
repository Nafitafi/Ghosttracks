/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.mocks;

import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.ProveedorRef;
import itson.org.ghosttracks.entidades.SucursalRef;
import itson.org.ghosttracks.enums.EstadoOrden;
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

    private final List<Orden> ordenesDB = new ArrayList<>();
    private long generadorId = 1L;

    public OrdenesMockDAO() {
        Orden o1 = new Orden();
        o1.setIdOrden(String.valueOf(generadorId++));
        o1.setFolio("ORD-001");
        o1.setComentarios("Entrega urgente por la mañana.");
        o1.setTotal(2450.75);
        o1.setFechaEntregaEstimada(LocalDate.now().plusDays(10));
        o1.setFecha(LocalDateTime.now().minusDays(2));
        o1.setFechaSolicitud(LocalDateTime.now().minusDays(2));
        o1.setTipoOrden(TipoOrden.PLANIFICADA);
        o1.setEstado(EstadoOrden.CONFIRMADO);
        o1.setProveedor(new ProveedorRef("1", "Discos Norte"));
        o1.setSucursal(new SucursalRef("1", "Obregón Centro"));
        ordenesDB.add(o1);

        Orden o2 = new Orden();
        o2.setIdOrden(String.valueOf(generadorId++));
        o2.setFolio("ORD-002");
        o2.setComentarios("Reposición de vinilos.");
        o2.setTotal(1890.00);
        o2.setFechaEntregaEstimada(LocalDate.now().plusDays(5));
        o2.setFecha(LocalDateTime.now().minusDays(1));
        o2.setFechaSolicitud(LocalDateTime.now().minusDays(1));
        o2.setTipoOrden(TipoOrden.URGENTE);
        o2.setEstado(EstadoOrden.ENVIADO);
        o2.setProveedor(new ProveedorRef("2", "Vinilos del Pacífico"));
        o2.setSucursal(new SucursalRef("2", "Obregón Norte"));
        ordenesDB.add(o2);
    }

    @Override
    public Orden insertar(Orden orden) throws PersistenciaException {
        if (orden == null) {
            throw new PersistenciaException("La orden a insertar no puede ser nula.");
        }
        orden.setIdOrden(String.valueOf(generadorId++));
        if (orden.getFecha() == null) {
            orden.setFecha(LocalDateTime.now());
        }
        if (orden.getFechaSolicitud() == null) {
            orden.setFechaSolicitud(LocalDateTime.now());
        }
        ordenesDB.add(orden);
        return orden;
    }

    @Override
    public Orden actualizar(String idOrden, EstadoOrden estado) throws PersistenciaException {
        for (Orden orden : ordenesDB) {
            if (orden.getIdOrden().equals(idOrden)) {
                orden.setEstado(estado);
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
    public Orden obtenerPorId(String id) throws PersistenciaException {
        for (Orden orden : ordenesDB) {
            if (orden.getIdOrden().equals(id)) {
                return orden;
            }
        }
        throw new PersistenciaException("Orden no encontrada con el ID: " + id);
    }

    @Override
    public Orden actualizarOrdenCompleta(Orden orden) throws PersistenciaException {
        if (orden == null || orden.getIdOrden() == null) {
            throw new PersistenciaException("La orden a actualizar debe tener identificador.");
        }
        for (int i = 0; i < ordenesDB.size(); i++) {
            if (ordenesDB.get(i).getIdOrden().equals(orden.getIdOrden())) {
                ordenesDB.set(i, orden);
                return orden;
            }
        }
        throw new PersistenciaException("Orden no encontrada con el ID: " + orden.getIdOrden());
    }
}
