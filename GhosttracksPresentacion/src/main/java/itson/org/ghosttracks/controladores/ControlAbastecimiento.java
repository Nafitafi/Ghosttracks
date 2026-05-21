/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.controladores;

import com.itextpdf.text.DocumentException;
import itson.org.ghosttracks.dtos.FiltroOrdenDTO;
import itson.org.ghosttracks.dtos.FiltroSalidaDTO;
import itson.org.ghosttracks.dtos.NuevaSalidaDTO;
import itson.org.ghosttracks.dtos.OrdenDTO;
import itson.org.ghosttracks.dtos.ProductoDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import itson.org.ghosttracks.dtos.ProductoSalidaDTO;
import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SalidaDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.RazonSalidaDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import itson.org.ghosttracks.factory.IOrdenDTOFactory;
import itson.org.ghosttracks.factory.OrdenDTOFactory;
import itson.org.ghosttracks.presentacion.administrador.PantallaNuevaOrdenProveedor;
import itson.org.ghosttracks.presentacion.administrador.PantallaNuevaSalida;
import itson.org.ghosttracks.presentacion.administrador.PantallaOrdenesProveedores;
import itson.org.ghosttracks.presentacion.administrador.PantallaSalidas;
import itson.org.ghosttracksabastecimiento.excepciones.AbastecimientoException;
import itson.org.ghosttracksabastecimiento.fachada.Abastecimiento;
import itson.org.ghosttracksabastecimiento.fachada.IAbastecimiento;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author nafbr
 */
public class ControlAbastecimiento {

    private final Navegador navegador;
    private final IAbastecimiento abastecimientoFachada;
    private final ReportePdfExporter reportePdfExporter;
    private final IOrdenDTOFactory ordenDTOFactory;

    public ControlAbastecimiento(Navegador navegador) {
        this.navegador = navegador;
        this.abastecimientoFachada = new Abastecimiento();
        this.reportePdfExporter = new ReportePdfExporter();
        this.ordenDTOFactory = new OrdenDTOFactory();
    }

    public void irAInicio() {
        navegador.irVentasAdmin();
    }

    public void irAAgregarOrdenNueva() {
        navegador.irFormularioNuevaOrden();
    }

    public void irAAgregarSalidaNueva() {
        navegador.irFormularioNuevaSalida();
    }

    public void inicializarFiltrosYTabla(PantallaOrdenesProveedores vista) {
        try {
            List<ProveedorDTO> proveedores = abastecimientoFachada.obtenerTodosLosProveedores();
            List<OrdenDTO> ordenes = abastecimientoFachada.obtenerTodasLasOrdenes();
            vista.configurarComponentesDinamicos(proveedores, List.of(EstadoOrdenDTO.values()), List.of(TipoOrdenDTO.values()));
            vista.llenarTabla(ordenes);
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al inicializar el catalogo de abastecimiento.", true);
        }
    }

    public void filtrarOrdenes(PantallaOrdenesProveedores vista, Object proveedorSel, Object estadoSel,
            Object tipoSel, LocalDate inicio, LocalDate fin) {
        try {
            vista.llenarTabla(abastecimientoFachada.obtenerOrdenes(
                    crearFiltroOrden(proveedorSel, estadoSel, tipoSel, inicio, fin)));
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al procesar el filtrado de datos.", true);
        }
    }

    public void imprimirTabla(JTable tabla, String titulo) {
        exportarReporteIndividual(tabla, titulo);
    }

    public void exportarReporteAgrupado(JTable tabla, String titulo, String filtros) {
        exportarTablaAPdf(tabla, titulo, filtros, true);
    }

    public void exportarReporteIndividual(JTable tabla, String titulo) {
        exportarTablaAPdf(tabla, titulo, null, false);
    }

    public void inicializarFiltrosYTablaSalidas(PantallaSalidas vista) {
        try {
            vista.configurarComponentesDinamicos(List.of(RazonSalidaDTO.values()));
            vista.llenarTabla(abastecimientoFachada.obtenerTodasLasSalidas());
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al inicializar el registro de salidas.", true);
        }
    }

    public void filtrarSalidas(PantallaSalidas vista, Object razonSel, LocalDate inicio, LocalDate fin) {
        try {
            vista.llenarTabla(abastecimientoFachada.obtenerSalidas(crearFiltroSalida(razonSel, inicio, fin)));
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al filtrar salidas.", true);
        }
    }

    public void verDetalleSalida(SalidaDTO salida) {
        navegador.irRegistroSalida(salida);
    }

    public void verDetalleOrden(OrdenDTO orden) {
        navegador.irResumenOrden(orden);
    }

    public void confirmarOrden(OrdenDTO orden) {
        confirmarOrden(orden, null);
    }

    public void confirmarOrden(OrdenDTO orden, PantallaOrdenesProveedores vista) {
        if (orden == null) {
            navegador.mostrarMensaje("No hay una orden seleccionada.", true);
            return;
        }
        if (!puedeConfirmarRecepcion(orden)) {
            navegador.mostrarMensaje("Esta orden ya no puede confirmar recepcion.", true);
            return;
        }
        navegador.irConfirmacionRecepcion(orden);
    }

    public void confirmarRecepcionOrden(OrdenDTO orden, byte[] imagen, List<ProductoOrdenDTO> productosRecibidos) {
        if (orden == null) {
            navegador.mostrarMensaje("No hay una orden seleccionada.", true);
            return;
        }
        if (!puedeConfirmarRecepcion(orden)) {
            navegador.mostrarMensaje("Esta orden ya no puede confirmar recepcion.", true);
            return;
        }
        try {
            abastecimientoFachada.confirmarRecepcionOrden(orden.getIdOrden(), imagen, productosRecibidos);
            navegador.mostrarMensaje("Recepcion de orden confirmada.", false);
            navegador.irOrdenesProveedores();
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje(mensajeError(ex, "No se pudo confirmar la orden."), true);
        }
    }

    public void cancelarOrden(OrdenDTO orden, PantallaOrdenesProveedores vista) {
        if (orden == null) {
            navegador.mostrarMensaje("No hay una orden seleccionada.", true);
            return;
        }
        try {
            abastecimientoFachada.actualizarEstadoOrden(orden.getIdOrden(), EstadoOrdenDTO.CANCELADO);
            vista.llenarTabla(abastecimientoFachada.obtenerTodasLasOrdenes());
            navegador.mostrarMensaje("Orden cancelada.", false);
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("No se pudo cancelar la orden.", true);
        }
    }

    public void inicializarFormularioNuevaOrden(PantallaNuevaOrdenProveedor vista) {
        try {
            List<ProveedorDTO> proveedores = abastecimientoFachada.obtenerTodosLosProveedores();
            List<SucursalDTO> sucursales = abastecimientoFachada.obtenerTodasLasSucursales();
            List<ProductoDTO> productos = abastecimientoFachada.obtenerProductosDisponibles();
            vista.configurarComponentesDinamicos(proveedores, sucursales, List.of(TipoOrdenDTO.values()));
            vista.cargarProductosDisponibles(productos);
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al cargar datos para la nueva orden.", true);
        }
    }

    public List<ProductoDTO> obtenerProductosDisponibles() throws AbastecimientoException {
        return abastecimientoFachada.obtenerProductosDisponibles();
    }

    public void registrarOrdenNueva(PantallaNuevaOrdenProveedor vista, ProveedorDTO proveedor, SucursalDTO sucursal,
            TipoOrdenDTO tipoOrden, LocalDate fechaEstimada, String comentarios, List<ProductoOrdenDTO> productos) {
        try {
            OrdenDTO ordenRegistrada = abastecimientoFachada.registrarNuevaOrden(
                    ordenDTOFactory.crearNuevaOrden(proveedor, sucursal, productos, comentarios, fechaEstimada, tipoOrden));
            navegador.mostrarMensaje("Orden " + ordenRegistrada.getFolio() + " registrada correctamente.", false);
            navegador.irOrdenesProveedores();
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje(mensajeError(ex, "No se pudo registrar la orden."), true);
        }
    }

    public void inicializarFormularioNuevaSalida(PantallaNuevaSalida vista) {
        try {
            vista.configurarComponentesDinamicos(abastecimientoFachada.obtenerTodasLasSucursales(), List.of(RazonSalidaDTO.values()));
            vista.cargarProductosDisponibles(abastecimientoFachada.obtenerProductosDisponibles());
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al cargar datos para la salida.", true);
        }
    }

    public void registrarSalidaNueva(SucursalDTO sucursal, RazonSalidaDTO razon, String comentarios,
            List<ProductoSalidaDTO> productos) {
        try {
            SalidaDTO salida = abastecimientoFachada.registrarNuevaSalida(nuevaSalidaDTO(sucursal, razon, comentarios, productos));
            navegador.mostrarMensaje("Salida " + salida.getFolio() + " registrada correctamente.", false);
            navegador.irSalidas();
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje(mensajeError(ex, "No se pudo registrar la salida."), true);
        }
    }

    public void volverAOrdenes() {
        navegador.irOrdenesProveedores();
    }

    public void volverASalidas() {
        navegador.irSalidas();
    }

    private void exportarTablaAPdf(JTable tabla, String titulo, String filtros, boolean incluirFiltros) {
        if (tabla == null || tabla.getRowCount() == 0) {
            navegador.mostrarMensaje("No hay datos disponibles en la tabla para exportar.", true);
            return;
        }

        try {
            if (reportePdfExporter.exportarTabla(tabla, titulo, filtros, incluirFiltros)) {
                navegador.mostrarMensaje("Reporte PDF guardado correctamente.", false);
            }
        } catch (DocumentException | IOException ex) {
            navegador.mostrarMensaje("No se pudo guardar el reporte PDF.", true);
        }
    }

    private FiltroOrdenDTO crearFiltroOrden(Object proveedorSel, Object estadoSel, Object tipoSel,
            LocalDate inicio, LocalDate fin) {
        FiltroOrdenDTO filtro = new FiltroOrdenDTO();
        if (proveedorSel instanceof ProveedorDTO proveedor) {
            filtro.setIdProveedor(proveedor.getIdProveedor());
        }
        if (estadoSel instanceof EstadoOrdenDTO estado) {
            filtro.setEstado(estado);
        }
        if (tipoSel instanceof TipoOrdenDTO tipo) {
            filtro.setTipoOrden(tipo);
        }
        filtro.setFechaInicio(inicio);
        filtro.setFechaFin(fin);
        return filtro;
    }

    private FiltroSalidaDTO crearFiltroSalida(Object razonSel, LocalDate inicio, LocalDate fin) {
        FiltroSalidaDTO filtro = new FiltroSalidaDTO();
        if (razonSel instanceof RazonSalidaDTO razon) {
            filtro.setRazon(razon);
        }
        filtro.setFechaInicio(inicio);
        filtro.setFechaFin(fin);
        return filtro;
    }

    private boolean puedeConfirmarRecepcion(OrdenDTO orden) {
        return orden.getEstadoOrden() != EstadoOrdenDTO.RECIBIDO
                && orden.getEstadoOrden() != EstadoOrdenDTO.CERRADO
                && orden.getEstadoOrden() != EstadoOrdenDTO.CANCELADO;
    }

    private NuevaSalidaDTO nuevaSalidaDTO(SucursalDTO sucursal, RazonSalidaDTO razon, String comentarios,
            List<ProductoSalidaDTO> productos) {
        NuevaSalidaDTO dto = new NuevaSalidaDTO();
        dto.setSucursal(sucursal);
        dto.setRazon(razon);
        dto.setComentarios(comentarios);
        dto.setProductos(productos);
        return dto;
    }

    private String mensajeError(Exception ex, String mensajePorDefecto) {
        return ex.getMessage() != null ? ex.getMessage() : mensajePorDefecto;
    }
}
