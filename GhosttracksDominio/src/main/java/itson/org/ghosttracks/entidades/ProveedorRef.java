/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

/**
 *
 * @author nafbr
 */
public class ProveedorRef {

    private String idProveedor;  
    private String nombre;

    public ProveedorRef() {
    }

    public ProveedorRef(String idProveedor, String nombre) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
    }

    public static ProveedorRef desde(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }
        return new ProveedorRef(
                String.valueOf(proveedor.getIdProveedor()),
                proveedor.getNombreProovedor()
        );
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
