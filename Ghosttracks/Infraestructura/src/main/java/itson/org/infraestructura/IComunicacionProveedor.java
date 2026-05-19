/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.infraestructura;

import itson.org.ghosttracks.dtos.OrdenDTO;

/**
 *
 * @author nafbr
 */
public interface IComunicacionProveedor {
    boolean notificar(OrdenDTO orden);
}
