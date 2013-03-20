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
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.tiendavirtual.notificaciones.NotificacionInterceptor;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Administrador
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdministracionPersistenciaJPA implements AdministracionPersistenciaLocal{
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    TimerService timerService;

    @Override
    public Producto consultarProducto(int idProducto) {
        
        Producto producto;
        producto = em.find(Producto.class, idProducto);
        return producto;
        
    }

    @Override
    @Interceptors(NotificacionInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer crearOrden(Orden orden) throws CreacionOrdenException {
        
        try{
            
            em.persist(orden);
            timerService.createTimer(1500, orden);
            
        } catch(Exception ex){
            throw new CreacionOrdenException();
        }
        return orden.getId();
        
    }
    
    @Timeout    
    private void timerCrearOrden(Timer timer){
        Orden orden = (Orden)timer.getInfo();
        System.out.println("Se ha enviado la orden a la direccion "+
                orden.getInformacionEnvio().getDireccion());
        
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer crearInformacionEnvio(InformacionEnvio informacionEnvio) {
            
            em.persist(informacionEnvio);
            return informacionEnvio.getId();
        
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer crearInformacionFactura(InformacionFactura informacionFactura) {
        
        em.persist(informacionFactura);
        return informacionFactura.getId();
        
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modificarProductos(List<Producto> productos, Integer idOrden) throws ModificacionProductoException {
       
        
        try{
                Orden orden = em.find(Orden.class,idOrden);
                for(Producto producto:productos){
                    producto.setOrden(orden);
                    em.merge(producto);

                }
                
                int a = 1/0;
                
        } catch(Exception ex){
            
            throw new ModificacionProductoException();
        }
        
        
    }//end of method

    @Override
    
    public Comprador consultarComprador(int idComprador) {
        
        Comprador comprador;
        comprador = em.find( Comprador.class, idComprador);
        return comprador;
        
    }

    @Override
    public List<Producto> consultarProductos() {
        Query consulta = em.createNamedQuery("findAllProducts");
       return consulta.getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer crearBitacora(Bitacora bitacora) {
        
        em.persist(bitacora);
        return bitacora.getId();
        
    }

    @Override
    public List<Comprador> consultarCompradores() {
       
        Query query = em.createNamedQuery("findAllComprador");
        return query.getResultList();
        
    }
    
    
}
