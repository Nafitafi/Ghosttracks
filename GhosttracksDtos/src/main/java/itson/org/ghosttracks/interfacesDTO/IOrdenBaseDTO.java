package itson.org.ghosttracks.interfacesDTO;


import itson.org.ghosttracks.dtos.ProveedorDTO;
import itson.org.ghosttracks.dtos.SucursalDTO;
import itson.org.ghosttracks.dtos.ProductoOrdenDTO;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author nafbr
 */
public interface IOrdenBaseDTO {
    public abstract ProveedorDTO getProveedor();
    public abstract SucursalDTO getSucursal();
    public abstract List<ProductoOrdenDTO> getProductos();
}
