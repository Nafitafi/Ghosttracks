/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracksabastecimiento.excepciones;

/**
 *
 * @author nafbr
 */
public class AbastecimientoException extends Exception{
    private final CodigoErrorAbastecimiento codigo;

    public AbastecimientoException(CodigoErrorAbastecimiento codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    public AbastecimientoException(CodigoErrorAbastecimiento codigo, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigo = codigo;
    }

    public CodigoErrorAbastecimiento getCodigo() {
        return codigo;
    }
}
