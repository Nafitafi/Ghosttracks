/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

/**
 *
 * @author nafbr
 */
public class Proveedor {

    private String idProveedor;
    private String nombreProovedor;
    private Direccion direccionProovedor;
    private String numeroTelefono;

    public Proveedor() {
    }

    public Proveedor(String idProveedor, String nombreProovedor, Direccion direccionProovedor, String numeroTelefono) {
        this.idProveedor = idProveedor;
        this.nombreProovedor = nombreProovedor;
        this.direccionProovedor = direccionProovedor;
        this.numeroTelefono = numeroTelefono;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProovedor() {
        return nombreProovedor;
    }

    public void setNombreProovedor(String nombreProovedor) {
        this.nombreProovedor = nombreProovedor;
    }

    public Direccion getDireccionProovedor() {
        return direccionProovedor;
    }

    public void setDireccionProovedor(Direccion direccionProovedor) {
        this.direccionProovedor = direccionProovedor;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

}
