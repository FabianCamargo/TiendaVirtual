/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.auditoria;

import com.tiendavirtual.entidades.Producto;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

/**
 *
 * @author Administrador
 */
public class MonitoreoProducto {
    
    @PreUpdate
    public void preActualizacionProducto(Producto producto){
        
        System.out.println("El producto " + producto.getId() + " va a ser asignado"
                + "a la orden de compra" + producto.getOrden().getId() );
        
        
    }
    
    @PostUpdate
    public void postActualizacionProducto(Producto producto){
        
        System.out.println("El producto " + producto.getId() + " fue asignado"
                + "a la orden de compra" + producto.getOrden().getId());
        
    }
    
}
