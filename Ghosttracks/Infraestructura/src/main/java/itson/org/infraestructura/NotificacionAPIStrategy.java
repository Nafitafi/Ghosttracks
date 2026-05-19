/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.infraestructura;

import itson.org.ghosttracks.dtos.OrdenDTO;

/**
 *
 * @author nafbr
 */
public class NotificacionAPIStrategy implements IComunicacionProveedor {

    @Override
    public boolean notificar(OrdenDTO orden) {
        if (orden == null || orden.getProveedor() == null) {
            return false;
        }
        System.out.println("[Proveedor API] Orden enviada al proveedor "
                + orden.getProveedor().getNombreProveedor()
                + " con folio " + orden.getFolio());
        return true;
    }
}
