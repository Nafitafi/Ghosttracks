/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

/**
 *
 * @author nafbr
 */
public class Proovedor {
    private Long idProveedor;
    private String nombreProovedor;
    private Direccion direccionProovedor;
    private String numeroTelefono;

    public Proovedor() {
    }

    public Proovedor(Long idProveedor, String nombreProovedor, Direccion direccionProovedor, String numeroTelefono) {
        this.idProveedor = idProveedor;
        this.nombreProovedor = nombreProovedor;
        this.direccionProovedor = direccionProovedor;
        this.numeroTelefono = numeroTelefono;
    }

    
    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
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
