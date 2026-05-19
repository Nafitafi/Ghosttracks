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
public class ComunicacionProveedor implements IComunicacionProveedor{
    private final IComunicacionProveedor estrategia;

    public ComunicacionProveedor() {
        this(new NotificacionEmailStrategy());
    }

    public ComunicacionProveedor(IComunicacionProveedor estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public boolean notificar(OrdenDTO orden) {
        if (estrategia == null) {
            return false;
        }
        return estrategia.notificar(orden);
    }
}
