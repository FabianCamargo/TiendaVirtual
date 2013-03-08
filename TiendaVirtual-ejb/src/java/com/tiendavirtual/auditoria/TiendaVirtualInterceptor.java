/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.auditoria;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Administrador
 */
public class TiendaVirtualInterceptor {
    
    @AroundInvoke
    public Object interceptor(InvocationContext ic) throws Exception{
        
        System.out.println("Se ha ejecutado el metodo: " + ic.getMethod().getName() );
        return ic.proceed();
    }
    
}
