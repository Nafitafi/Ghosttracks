/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

/**
 *
 * @author nafbr
 */
public class ProductoOrdenDTO {

    private ProductoDTO producto;
    private int cantidadProducto;
    private double precioUnitario;
    private double importeTotal;
    private boolean recibido;

    public ProductoOrdenDTO() {
    }

    public ProductoOrdenDTO(ProductoDTO producto, int cantidadProducto, double precioUnitario) {
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
        this.precioUnitario = precioUnitario;
        this.importeTotal = precioUnitario * cantidadProducto;
        this.recibido = false;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
        this.importeTotal = this.precioUnitario * cantidadProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }

}
