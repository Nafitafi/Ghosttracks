/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.ghosttracks.fabricas;

import itson.org.ghosttracks.daos.IOrdenesDAO;
import itson.org.ghosttracks.daos.IProductosDAO;
import itson.org.ghosttracks.daos.IProveedoresDAO;
import itson.org.ghosttracks.daos.ISalidasDAO;
import itson.org.ghosttracks.daos.ISucursalesDAO;
import itson.org.ghosttracks.mocks.OrdenesMockDAO;
import itson.org.ghosttracks.mocks.ProductosMockDAO;
import itson.org.ghosttracks.mocks.ProveedoresMockDAO;
import itson.org.ghosttracks.mocks.SalidasMockDAO;
import itson.org.ghosttracks.mocks.SucursalesMockDAO;
import itson.org.ghosttracks.mongo.OrdenesMongoDAO;
import itson.org.ghosttracks.mongo.ProductosMongoDAO;
import itson.org.ghosttracks.mongo.ProveedoresMongoDAO;
import itson.org.ghosttracks.mongo.SalidasMongoDAO;
import itson.org.ghosttracks.mongo.SucursalesMongoDAO;

/**
 *
 * @author nafbr
 */
public class DAOFactory {

    private static final DAOFactory INSTANCIA = new DAOFactory();
    private final boolean usarMongo = true;

    private DAOFactory() {
 
    }

    public static DAOFactory getInstancia() {
        return INSTANCIA;
    }

    public IOrdenesDAO crearOrdenesDAO() {
        return usarMongo ? new OrdenesMongoDAO() : new OrdenesMockDAO();
    }

    public IProductosDAO crearProductosDAO() {
        return usarMongo ? new ProductosMongoDAO() : new ProductosMockDAO();
    }

    public IProveedoresDAO crearProveedoresDAO() {
        return usarMongo ? new ProveedoresMongoDAO() : new ProveedoresMockDAO();
    }

    public ISucursalesDAO crearSucursalesDAO() {
        return usarMongo ? new SucursalesMongoDAO() : new SucursalesMockDAO();
    }

    public ISalidasDAO crearSalidasDAO() {
        return usarMongo ? new SalidasMongoDAO() : new SalidasMockDAO();
    }
}
