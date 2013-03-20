/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.logica;

import com.tiendavirtual.auditoria.CreacionOrdenInterceptor;
import com.tiendavirtual.entidades.Comprador;
import com.tiendavirtual.entidades.InformacionEnvio;
import com.tiendavirtual.entidades.InformacionFactura;
import com.tiendavirtual.entidades.Orden;
import com.tiendavirtual.entidades.Producto;
import com.tiendavirtual.excepciones.CreacionOrdenException;
import com.tiendavirtual.excepciones.ModificacionProductoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

/**
 *
 * @author User
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdministracionOrden implements AdministracionOrdenLocal {

     
    private List<Producto> productos;
    private Comprador comprador;
    private InformacionFactura informacionFactura;
    private InformacionEnvio informacionEnvio;
    
    @EJB()
    AdministracionPersistenciaLocal administracionPersistencia;
    
    public AdministracionOrden(){
        
        productos = new ArrayList<Producto>();
        
    }

    @Override
    public void adicionarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public void adicionarComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public void adicionarInformacionFactura(InformacionFactura informacionFactura) {
       this.informacionFactura = informacionFactura;
       
    }

    @Override
    public void adicionarInformacionEnvio(InformacionEnvio informacionEnvio) {
        this.informacionEnvio = informacionEnvio;
    }

    
    @Override
    @Remove
    @Interceptors(CreacionOrdenInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Integer crearOrdenCompra() throws CreacionOrdenException,ModificacionProductoException{
        
  
       
            administracionPersistencia.crearInformacionEnvio(informacionEnvio);
            administracionPersistencia.crearInformacionFactura(informacionFactura);

            Orden orden = new Orden();
            orden.setComprador(comprador);
            orden.setFecha(Calendar.getInstance());
            orden.setInformacionEnvio(informacionEnvio);
            orden.setInformacionFactura(informacionFactura);
            orden.setProductos(productos);

            administracionPersistencia.crearOrden(orden);

            administracionPersistencia.modificarProductos(productos,orden.getId());
            
       
       
       return orden.getId();
       
    }//end of method crearOrdenCompra
    

    @Remove
    @Override
    public void cancelarOrdenCompra(){
        
    }
    
    @Override
    public Comprador getComprador(){
        return this.comprador;
    }
    
    public List consultarCarroCompras(){
        return null;
    }
    
}
