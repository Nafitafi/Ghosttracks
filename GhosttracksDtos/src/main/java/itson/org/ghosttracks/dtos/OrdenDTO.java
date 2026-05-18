/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class OrdenDTO {
    private Long idOrden;
    private String folio;
    private TipoOrdenDTO tipoOrden;
    private String comentarios;
    private Double total;
    private LocalDate fechaEntregaEst;
    private LocalDateTime fechaSolicitud;
    private EstadoOrdenDTO estadoOrden;
    private List<ProductoOrdenDTO> productosOrden;
    private Byte[] imagenRecepcion;
    private ProveedorDTO proveedor;
    private SucursalDTO sucursal;

    public OrdenDTO() {
    }

    public OrdenDTO(Long idOrden, String folio, TipoOrdenDTO tipoOrden, String comentarios, Double total,
            LocalDate fechaEntregaEst, LocalDateTime fechaSolicitud, EstadoOrdenDTO estadoOrden,
            List<ProductoOrdenDTO> productosOrden, Byte[] imagenRecepcion,
            ProveedorDTO proveedor, SucursalDTO sucursal) {
        this.idOrden = idOrden;
        this.folio = folio;
        this.tipoOrden = tipoOrden;
        this.comentarios = comentarios;
        this.total = total;
        this.fechaEntregaEst = fechaEntregaEst;
        this.fechaSolicitud = fechaSolicitud;
        this.estadoOrden = estadoOrden;
        this.productosOrden = productosOrden;
        this.imagenRecepcion = imagenRecepcion;
        this.proveedor = proveedor;
        this.sucursal = sucursal;
    }

    
    
    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public TipoOrdenDTO getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrdenDTO tipoOrden) {
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

    public LocalDate getFechaEntregaEst() {
        return fechaEntregaEst;
    }

    public void setFechaEntregaEst(LocalDate fechaEntregaEst) {
        this.fechaEntregaEst = fechaEntregaEst;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoOrdenDTO getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrdenDTO estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public List<ProductoOrdenDTO> getProductosOrden() {
        return productosOrden;
    }

    public void setProductosOrden(List<ProductoOrdenDTO> productosOrden) {
        this.productosOrden = productosOrden;
    }

    public Byte[] getImagenRecepcion() {
        return imagenRecepcion;
    }

    public void setImagenRecepcion(Byte[] imagenRecepcion) {
        this.imagenRecepcion = imagenRecepcion;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
}
