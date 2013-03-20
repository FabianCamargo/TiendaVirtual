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
import com.tiendavirtual.excepciones.CreacionOrdenException;
import com.tiendavirtual.excepciones.ModificacionProductoException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface AdministracionPersistenciaLocal {
    
    public Producto consultarProducto(int idProducto);
    
    public Integer crearOrden(Orden orden) throws CreacionOrdenException;
    
    public Integer crearInformacionEnvio(InformacionEnvio informacionEnvio);
    
    public Integer crearInformacionFactura(InformacionFactura informacionFactura);
    
    public void modificarProductos(List<Producto> productos, Integer idOrden) throws ModificacionProductoException;
    
    public Comprador consultarComprador(int idComprador);
    
    public List<Producto> consultarProductos();
    
    public Integer crearBitacora(Bitacora bitacora);
    
    public List<Comprador> consultarCompradores();
    
}
