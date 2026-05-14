/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

import itson.org.ghosttracks.enums.RazonSalida;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nafbr
 */
public class Salida {
    private Long idSalida;
    private LocalDate fechaSalida;
    private String comenatriosSalida;
    private RazonSalida razon;
    
    //Relaciones
    private Long idSucursal;
    private List<ProductoSalida> productosSalida;

    public Salida() {
    }

    public Salida(Long idSalida, LocalDate fechaSalida, String comenatriosSalida, RazonSalida razon, Long idSucursal, List<ProductoSalida> productosSalida) {
        this.idSalida = idSalida;
        this.fechaSalida = fechaSalida;
        this.comenatriosSalida = comenatriosSalida;
        this.razon = razon;
        this.idSucursal = idSucursal;
        this.productosSalida = productosSalida;
    }

    public Long getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(Long idSalida) {
        this.idSalida = idSalida;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getComenatriosSalida() {
        return comenatriosSalida;
    }

    public void setComenatriosSalida(String comenatriosSalida) {
        this.comenatriosSalida = comenatriosSalida;
    }

    public RazonSalida getRazon() {
        return razon;
    }

    public void setRazon(RazonSalida razon) {
        this.razon = razon;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public List<ProductoSalida> getProductosSalida() {
        return productosSalida;
    }

    public void setProductosSalida(List<ProductoSalida> productosSalida) {
        this.productosSalida = productosSalida;
    }
    
    
}
