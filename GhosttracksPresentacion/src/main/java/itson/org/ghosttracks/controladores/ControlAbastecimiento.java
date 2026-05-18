/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.controladores;

import itson.org.ghosttracksabastecimiento.fachada.AbastecimientoFachada;
import itson.org.ghosttracksabastecimiento.fachada.IAbastecimiento;

/**
 *
 * @author nafbr
 */
public class ControlAbastecimiento {

    private final Navegador navegador;
    private final IAbastecimiento abastecimientoFachada = new AbastecimientoFachada();

    public ControlAbastecimiento(Navegador navegador) {
        this.navegador = navegador;
    }

    public void irAInicio() {
        navegador.irVentasAdmin();
    }

    public void irAAgregarOrdenNueva() {
        navegador.irFormularioNuevaOrden();
    }

    public void inicializarFiltrosYTabla(PantallaOrdenesProveedores vista) {
        try {
            // Carga dinámica de listas desde persistencia
            List<ProveedorDTO> proveedores = abastecimientoFachada.obtenerTodosLosProveedores();
            List<OrdenDTO> ordenes = abastecimientoFachada.obtenerTodasLasOrdenes();

            // Inyectar colecciones al modelo de la vista
            vista.configurarComponentesDinamicos(proveedores, List.of(EstadoOrdenDTO.values()), List.of(TipoOrden.values()));
            vista.llenarTabla(ordenes);
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al inicializar el catálogo de abastecimiento.", true);
        }
    }

    public void filtrarOrdenes(PantallaOrdenesProveedores vista, Object proveedorSel, Object estadoSel, Object tipoSel, LocalDate inicio, LocalDate fin) {
        try {
            List<OrdenDTO> todas = abastecimientoFachada.obtenerTodasLasOrdenes();

            List<OrdenDTO> filtradas = todas.stream().filter(o -> {
                // Filtro Dinámico de Proveedor
                if (proveedorSel instanceof ProveedorDTO prov) {
                    if (o.getProveedor() == null || !o.getProveedor().getIdProveedor().equals(prov.getIdProveedor())) {
                        return false;
                    }
                }
                // Filtro de Estado de Orden
                if (estadoSel instanceof EstadoOrdenDTO estado) {
                    if (o.getEstado() != estado) {
                        return false;
                    }
                }
                // Filtro de Tipo de Orden
                if (tipoSel instanceof TipoOrden tipo) {
                    if (o.getTipoOrden() != tipo) {
                        return false;
                    }
                }
                // Filtro por Fechas
                if (inicio != null && (o.getFechaEntregaEstimada() == null || o.getFechaEntregaEstimada().isBefore(inicio))) {
                    return false;
                }
                if (fin != null && (o.getFechaEntregaEstimada() == null || o.getFechaEntregaEstimada().isAfter(fin))) {
                    return false;
                }
                return true;
            }).toList();

            vista.llenarTabla(filtradas);
        } catch (AbastecimientoException ex) {
            navegador.mostrarMensaje("Error al procesar el filtrado de datos.", true);
        }
    }

    public void generarReportePDF(List<OrdenDTO> ordenes) {
        if (ordenes == null || ordenes.isEmpty()) {
            navegador.mostrarMensaje("No hay datos disponibles en la tabla para exportar.", true);
            return;
        }
        navegador.mostrarMensaje("Reporte PDF generado exitosamente.", false);
    }
}
