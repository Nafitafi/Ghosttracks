/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.fachada;

import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.daos.IProductosDAO;
import itson.org.ghosttracks.daos.IProveedoresDAO;
import itson.org.ghosttracks.daos.ISalidasDAO;
import itson.org.ghosttracks.daos.ISucursalesDAO;
import itson.org.ghosttracks.dtos.FiltroOrdenPersistenciaDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaPersistenciaDTO;
import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.entidades.Proveedor;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.entidades.Sucursal;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import itson.org.ghosttracks.fabricas.DAOFactory;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class PersistenciaFachada implements IPersistenciaAbastecimiento {

    private final IOrdenesDAO ordenesDAO;
    private final IProveedoresDAO proveedoresDAO;
    private final ISucursalesDAO sucursalesDAO;
    private final IProductosDAO productosDAO;
    private final ISalidasDAO salidasDAO;

    public PersistenciaFachada() {
        DAOFactory factory = DAOFactory.getInstancia();
        this.ordenesDAO = factory.crearOrdenesDAO();
        this.proveedoresDAO = factory.crearProveedoresDAO();
        this.sucursalesDAO = factory.crearSucursalesDAO();
        this.productosDAO = factory.crearProductosDAO();
        this.salidasDAO = factory.crearSalidasDAO();
    }

    @Override
    public Orden insertarOrden(Orden orden) throws PersistenciaException {
        return ordenesDAO.insertar(orden);
    }

    @Override
    public Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException {
        return ordenesDAO.actualizar(idOrden, estado);
    }

    @Override
    public Orden actualizarOrden(Orden orden) throws PersistenciaException {
        return ordenesDAO.actualizarOrdenCompleta(orden);
    }

    @Override
    public List<Orden> obtenerOrdenes() throws PersistenciaException {
        return ordenesDAO.obtenerTodos();
    }

    @Override
    public Orden obtenerOrdenPorId(String id) throws PersistenciaException {
        return ordenesDAO.obtenerPorId(id);
    }

    @Override
    public List<Proveedor> obtenerProveedores() throws PersistenciaException {
        return proveedoresDAO.obtenerTodos();
    }

    @Override
    public List<Sucursal> obtenerSucursales() throws PersistenciaException {
        return sucursalesDAO.obtenerTodos();
    }

    @Override
    public List<Producto> obtenerProductos() throws PersistenciaException {
        return productosDAO.obtenerTodos();
    }

    @Override
    public Producto obtenerProductoPorId(String idProducto) throws PersistenciaException {
        return productosDAO.buscarPorId(idProducto);
    }

    @Override
    public Salida guardarSalida(Salida salida) throws PersistenciaException {
        return salidasDAO.insertar(salida);
    }

    @Override
    public List<Salida> obtenerSalidas() throws PersistenciaException {
        return salidasDAO.obtenerTodos();
    }

    @Override
    public Producto incrementarStockProducto(String idProducto, int cantidad) throws PersistenciaException {
        return productosDAO.incrementarStock(idProducto, cantidad);
    }

    @Override
    public List<Orden> obtenerOrdenes(FiltroOrdenPersistenciaDTO filtro) throws PersistenciaException {
        return ordenesDAO.obtenerPorFiltro(filtro);
    }
    
    @Override
    public Producto decrementarStockProducto(String idProducto, int cantidad) throws PersistenciaException {
        return productosDAO.decrementarStock(idProducto, cantidad);
    }

    @Override
    public List<Salida> obtenerSalidas(FiltroSalidaPersistenciaDTO filtro) throws PersistenciaException {
        return salidasDAO.obtenerPorFiltro(filtro);
    }
}
