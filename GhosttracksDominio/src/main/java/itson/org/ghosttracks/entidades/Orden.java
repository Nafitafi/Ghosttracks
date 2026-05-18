/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

import itson.org.ghosttracks.enums.EstadoOrden;
import itson.org.ghosttracks.enums.TipoOrden;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class Orden {

    private Long idOrden;
    private TipoOrden tipoOrden;
    private String comentarios;
    private Double total;
    private LocalDate fechaEntregaEstimada;   
    private LocalDateTime fecha; // instante de registro en el sistema
    private String folio;
    private EstadoOrden estado;
    private ProveedorRef proveedor;
    private SucursalRef sucursal;
    private List<ProductoOrden> productosOrden;
    private LocalDateTime fechaSolicitud;// instante en que se solicitó
    private LocalDateTime fechaEntrega;// instante real de entrega (null hasta que llega)
    private byte[] imagen;

    public Orden() {
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public TipoOrden getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrden tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public ProveedorRef getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorRef proveedor) {
        this.proveedor = proveedor;
    }

    public SucursalRef getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalRef sucursal) {
        this.sucursal = sucursal;
    }

    public List<ProductoOrden> getProductosOrden() {
        return productosOrden;
    }

    public void setProductosOrden(List<ProductoOrden> productosOrden) {
        this.productosOrden = productosOrden;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
