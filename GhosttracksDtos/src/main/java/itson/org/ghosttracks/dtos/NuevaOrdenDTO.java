/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.TipoOrdenDTO;
import itson.org.ghosttracks.interfacesDTO.IOrdenBaseDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class NuevaOrdenDTO implements IOrdenBaseDTO {

    private ProveedorDTO proveedor;
    private SucursalDTO sucursal;
    private List<ProductoOrdenDTO> productos;
    private TipoOrdenDTO tipoOrden;
    private String comentarios;
    private LocalDate fechaEntregaEstimada;
    private LocalDateTime fechaSolicitud;

    
    public NuevaOrdenDTO() {
    }

    public NuevaOrdenDTO(ProveedorDTO proveedor, SucursalDTO sucursal, TipoOrdenDTO tipoOrden,
            List<ProductoOrdenDTO> productos, String comentarios, LocalDate fechaEntregaEstimada) {
        this.proveedor = proveedor;
        this.sucursal = sucursal;
        this.tipoOrden = tipoOrden;
        this.productos = productos;
        this.comentarios = comentarios;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.fechaSolicitud = LocalDateTime.now();
    }

    public TipoOrdenDTO getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrdenDTO tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    @Override
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public List<ProductoOrdenDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoOrdenDTO> productos) {
        this.productos = productos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
