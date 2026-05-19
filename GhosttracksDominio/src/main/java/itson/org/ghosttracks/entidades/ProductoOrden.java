/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

import itson.org.ghosttracks.enums.RazonSalida;

/**
 *
 * @author nafbr
 */
public class ProductoOrden {
    private Integer cantidadProducto;
    private Double importeTotal;
    private Double precioUnitario;
    private Boolean recibido;
    private String nombreProducto;
    //Relaciones
    private String idOrden;
    private String idProducto;

    public ProductoOrden() {
    }

    
    public ProductoOrden(Integer cantidadProducto, Double importeTotal, Double precioUnitario, Boolean recibido, RazonSalida razon, String idOrden, String idProducto) {
        this.cantidadProducto = cantidadProducto;
        this.importeTotal = importeTotal;
        this.precioUnitario = precioUnitario;
        this.recibido = recibido;
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Boolean getRecibido() {
        return recibido;
    }

    public void setRecibido(Boolean recibido) {
        this.recibido = recibido;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
