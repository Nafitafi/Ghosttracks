///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package itson.org.ghosttracks.negocio.objetosNegocio;
//
//import itson.org.ghosttracks.dtos.NuevaOrdenDTO;
//import itson.org.ghosttracks.dtos.OrdenDTO;
//import itson.org.ghosttracks.enums.EstadoOrdenDTO;
//import itson.org.ghosttracks.fachada.IPersistenciaAbastecimiento;
//import itson.org.ghosttracks.fachada.PersistenciaFachada;
//import itson.org.ghosttracks.negocio.interfaces.IOrdenBO;
//import itson.org.ghosttracks.negocio.objetosNegocio.Excepciones.NegocioException;
//
///**
// *
// * @author nafbr
// */
//public class OrdenBO implements IOrdenBO{
//    
//    private final IPersistenciaAbastecimiento persistencia;
//
//    //Lo mismo preguntar para estar un 1000% segura
//    public OrdenBO() {
//        this.persistencia = new PersistenciaFachada();
//    }
//
//    @Override
//    public void validarDatosOrden(NuevaOrdenDTO dto) throws NegocioException {
//        if (dto == null) {
//            throw new NegocioException("La orden no puede ser nula.");
//        }
//        if (dto.getProveedor() == null) {
//            throw new NegocioException("Debe seleccionar un proveedor.");
//        }
//        if (dto.getSucursal() == null) {
//            throw new NegocioException("Debe seleccionar una sucursal.");
//        }
//        if (dto.getProductosOrden() == null || dto.getProductosOrden().isEmpty()) {
//            throw new NegocioException("La orden debe contener al menos un producto.");
//        }
//        for (ProductoOrdenDTO item : dto.getProductosOrden()) {
//            if (item.getCantidadProducto() <= 0) {
//                throw new NegocioException(
//                    "La cantidad del producto debe ser mayor a cero: "
//                    + item.getProducto().getNombre());
//            }
//            if (item.getPrecioUnitario() < 0) {
//                throw new NegocioException(
//                    "El precio unitario no puede ser negativo: "
//                    + item.getProducto().getNombre());
//            }
//        }
//}
//
//    @Override
//    public OrdenDTO procesarNuevaOrden(NuevaOrdenDTO dto) throws NegocioException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void actualizarEstadoOrden(Long idOrden, EstadoOrdenDTO nuevoEstado) throws NegocioException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//
//}
