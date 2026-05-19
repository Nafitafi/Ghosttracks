/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.EstadoOrdenDTO;
import itson.org.ghosttracks.enums.TipoOrdenDTO;
import java.time.LocalDate;

/**
 *
 * @author nafbr
 */
public class FiltroOrdenDTO {

    private String idProveedor;
    private EstadoOrdenDTO estado;
    private TipoOrdenDTO tipoOrden;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public EstadoOrdenDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdenDTO estado) {
        this.estado = estado;
    }

    public TipoOrdenDTO getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrdenDTO tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
