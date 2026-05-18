/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.entidades;

/**
 *
 * @author nafbr
 */
public class SucursalRef {

    private String idSucursal;  
    private String nombre;

    public SucursalRef() {
    }

    public SucursalRef(String idSucursal, String nombre) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
    }

    public static SucursalRef desde(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }
        return new SucursalRef(
                String.valueOf(sucursal.getIdSucursal()),
                sucursal.getNombre()
        );
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
