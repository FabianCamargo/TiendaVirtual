/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.logica;

import com.tiendavirtual.entidades.Bitacora;
import com.tiendavirtual.entidades.Comprador;
import com.tiendavirtual.entidades.InformacionEnvio;
import com.tiendavirtual.entidades.InformacionFactura;
import com.tiendavirtual.entidades.Orden;
import com.tiendavirtual.entidades.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
@Stateless
public class AdministracionPersistencia  {

    private Connection connection;
    
    @Resource(name="jdbc/tiendaVirtualDB")
    DataSource ds;
    
    @PostConstruct
    private void inicializar(){
        try {
            
            connection = ds.getConnection();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
    @PreDestroy    
    private void limpiar(){
        try {
            
            connection.close();
            connection = null;
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
    
    
    
    public Producto consultarProducto(int idProducto) {
        
        Producto producto = null;
        try {
            String sql = "SELECT ID, NOMBRE FROM PRODUCTO WHERE ID = " + idProducto;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //si la consulta produce resultados se crea objeto producto
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("ID"));
                producto.setNombre(rs.getString("NOMBRE"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return producto;
        
    }

    
    public Integer createOrden(Orden orden) {
        Integer idOrden = null;
        try {
            //inserta registro en la tabla ORDEN
            String sql = "INSERT INTO ORDEN (FECHA, ID_INF_ENVIO, ID_INF_FACTURA, ID_COMPRADOR) "
                    + "VALUES(CURRENT_TIMESTAMP, " + orden.getInformacionEnvio().getId()
                    + ", " + orden.getInformacionFactura().getId()
                    + ", " + orden.getComprador().getId() + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM ORDEN";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idOrden = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearOrden " + ex.toString());
        }
        return idOrden;
        
    }

    
    public Integer crearInformacionEnvio(InformacionEnvio informacionEnvio) {
        Integer idInformacionEnvio = null;
        try {
            //inserta registro en la tabla INFORMACION_ENVIO
            String sql = "INSERT INTO INFORMACION_ENVIO (DIRECCION, CIUDAD, PAIS, DEPARTAMENTO) "
                    + "VALUES ('" + informacionEnvio.getDireccion() + "', '" + informacionEnvio.getCiudad() + "', "
                    + "'" + informacionEnvio.getPais() + "', '" + informacionEnvio.getDepartamento() + "')";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM INFORMACION_ENVIO";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idInformacionEnvio = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearInformacionEnvio " + ex.toString());
        }
        return idInformacionEnvio;
    }

    
    public Integer crearInformacionFactura(InformacionFactura informacionFactura) {
        Integer idInformacionFactura = null;
        try {
            //inserta registro en la tabla INFORMACION_FACTURA
            String sql = "INSERT INTO INFORMACION_FACTURA " +
                    "(CODIGO_TARJETA, NUMERO_CUENTA, FECHA_EXPIRACION) "
                    + "VALUES ('" + informacionFactura.getCodigoTarjeta() + "', '" + informacionFactura.getNumeroCuenta() + "', "
                    + "CURRENT_DATE)";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM INFORMACION_FACTURA";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idInformacionFactura = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearInformacionFactura " + ex.toString());
        }
        return idInformacionFactura;
    }

    
    public void modificarProductos(List<Producto> productos, Integer idOrden) {
        try {
            //asigna número de orden a los productos comprados
            String sql;
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                sql = "UPDATE PRODUCTO SET ID_ORDEN = " + idOrden
                        + "WHERE ID = " + producto.getId();
                Statement st = connection.createStatement();
                st.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("Error modificarProductos " + ex.toString());
        }
    }

    
    public Comprador consultarComprador(int idComprador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Producto> consultarProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Integer crearBitacora(Bitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Comprador> consultarCompradores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
