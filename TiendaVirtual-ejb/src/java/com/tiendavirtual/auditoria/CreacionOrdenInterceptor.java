/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.auditoria;

import com.tiendavirtual.entidades.Bitacora;
import com.tiendavirtual.logica.AdministracionOrdenLocal;
import com.tiendavirtual.logica.AdministracionPersistenciaLocal;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Administrador
 */
public class CreacionOrdenInterceptor {
    
    @EJB
    AdministracionPersistenciaLocal administracionPersistencia;
    
    @AroundInvoke
    public Object creacionOrden(InvocationContext ic) throws Exception{
      
        Object object = ic.getTarget();
        AdministracionOrdenLocal administracionOrden = (AdministracionOrdenLocal) object;
        
        Bitacora bitacora = new Bitacora();
        bitacora.setUsuario( administracionOrden.getComprador());
        bitacora.setFecha(Calendar.getInstance());
        bitacora.setDescripcion( "Creacion de una nueva orden");
        administracionPersistencia.crearBitacora(bitacora);
        
        administracionOrden = null;
        
        return ic.proceed();
        
    } 
    
}
