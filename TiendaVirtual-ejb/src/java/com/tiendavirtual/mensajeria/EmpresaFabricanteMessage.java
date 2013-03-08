/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.mensajeria;

import com.tiendavirtual.entidades.Orden;
import com.tiendavirtual.entidades.Producto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Administrador
 */
@MessageDriven(mappedName = "jms/CreacionOrdenTopic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "EmpresaFabricanteMessage"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "EmpresaFabricanteMessage")
})
public class EmpresaFabricanteMessage implements MessageListener {
    
    public EmpresaFabricanteMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage mensaje = (ObjectMessage) message;
            Orden orden = (Orden)mensaje.getObject();
            System.out.println("Se ha recibido la notificacion de venta de los productos:");
            for(Producto p: orden.getProductos()){
                
                System.out.println("IdProducto " + p.getId() + " Nombre: " + p.getNombre()  );
            
            }
        } catch (JMSException ex) {
            Logger.getLogger(EmpresaFabricanteMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
