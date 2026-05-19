/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

import itson.org.ghosttracks.enums.RazonSalida;
import java.time.LocalDate;

/**
 *
 * @author nafbr
 */
public class FiltroSalidaPersistenciaDTO {

    private RazonSalida razon;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public RazonSalida getRazon() {
        return razon;
    }

    public void setRazon(RazonSalida razon) {
        this.razon = razon;
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
