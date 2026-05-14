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
    
    private Long idProovedor;
    private Long idSucursal;

    public OrdenDTO() {
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

    public Long getIdProovedor() {
        return idProovedor;
    }

    public void setIdProovedor(Long idProovedor) {
        this.idProovedor = idProovedor;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
}
