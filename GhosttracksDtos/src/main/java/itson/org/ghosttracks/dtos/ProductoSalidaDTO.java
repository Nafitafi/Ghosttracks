package itson.org.ghosttracks.dtos;

/**
 * @author nafbr
 */
public class ProductoSalidaDTO {

    private ProductoDTO producto;
    private int cantidad;

    public ProductoSalidaDTO() {
    }

    public ProductoSalidaDTO(ProductoDTO producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
