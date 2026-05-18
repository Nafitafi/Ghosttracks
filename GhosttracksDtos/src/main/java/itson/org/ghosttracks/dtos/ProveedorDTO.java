/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.dtos;

/**
 *
 * @author nafbr
 */
public class ProveedorDTO {

    private String idProveedor;
    private String nombreProveedor;

    public ProveedorDTO() {
    }

    public ProveedorDTO(String idProveedor, String nombreProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Override
    public String toString() {
        return "ProveedorDTO{id='" + idProveedor + "', nombre='" + nombreProveedor + "'}";
    }

}
