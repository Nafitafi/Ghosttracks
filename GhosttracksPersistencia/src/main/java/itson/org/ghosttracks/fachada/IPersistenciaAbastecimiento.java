/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.ghosttracks.fachada;

import itson.org.ghosttracks.entidades.Orden;
import itson.org.ghosttracks.entidades.Producto;
import itson.org.ghosttracks.entidades.Proveedor;
import itson.org.ghosttracks.entidades.Salida;
import itson.org.ghosttracks.entidades.Sucursal;
import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author nafbr
 */
public interface IPersistenciaAbastecimiento {

    Orden insertarOrden(Orden orden) throws PersistenciaException;

    Orden actualizarEstadoOrden(String idOrden, EstadoOrden estado) throws PersistenciaException;

    Orden actualizarOrden(Orden orden) throws PersistenciaException;

    List<Orden> obtenerOrdenes() throws PersistenciaException;

    Orden obtenerOrdenPorId(String id) throws PersistenciaException;

    List<Proveedor> obtenerProveedores() throws PersistenciaException;

    List<Sucursal> obtenerSucursales() throws PersistenciaException;

    List<Producto> obtenerProductos() throws PersistenciaException;

    Producto obtenerProductoPorId(String idProducto) throws PersistenciaException;

    Salida guardarSalida(Salida salida) throws PersistenciaException;

    List<Salida> obtenerSalidas() throws PersistenciaException;
}
