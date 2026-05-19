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
    private String idSalida;
    private LocalDate fechaSalida;
    private String comenatriosSalida;
    private RazonSalida razon;
    
    //Relaciones
    private String idSucursal;
    private String nombreSucursal;
    private List<ProductoSalida> productosSalida;

    public Salida() {
    }

    public Salida(String idSalida, LocalDate fechaSalida, String comenatriosSalida, RazonSalida razon, String idSucursal, List<ProductoSalida> productosSalida) {
        this.idSalida = idSalida;
        this.fechaSalida = fechaSalida;
        this.comenatriosSalida = comenatriosSalida;
        this.razon = razon;
        this.idSucursal = idSucursal;
        this.productosSalida = productosSalida;
    }

    public String getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(String idSalida) {
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

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public List<ProductoSalida> getProductosSalida() {
        return productosSalida;
    }

    public void setProductosSalida(List<ProductoSalida> productosSalida) {
        this.productosSalida = productosSalida;
    }
    
}
