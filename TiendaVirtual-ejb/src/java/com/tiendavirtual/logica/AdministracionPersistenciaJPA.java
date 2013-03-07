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
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
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
    public Integer crearOrden(Orden orden) {
        
        em.persist(orden);
        timerService.createTimer(1500, orden);
        return orden.getId();
        
    }
    
    @Timeout    
    private void timerCrearOrden(Timer timer){
        Orden orden = (Orden)timer.getInfo();
        System.out.println("Se ha enviado la orden a la direccion "+
                orden.getInformacionEnvio().getDireccion());
        
    }

    @Override
    public Integer crearInformacionEnvio(InformacionEnvio informacionEnvio) {
            
            em.persist(informacionEnvio);
            return informacionEnvio.getId();
        
    }

    @Override
    public Integer crearInformacionFactura(InformacionFactura informacionFactura) {
        
        em.persist(informacionFactura);
        return informacionFactura.getId();
        
    }

    @Override
    public void modificarProductos(List<Producto> productos, Integer idOrden) {
       
        Orden orden = em.find(Orden.class,idOrden);
        for(Producto producto:productos){
            producto.setOrden(orden);
            em.merge(producto);
            
        }
        
        
    }

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
