/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.mensajeria;

import com.tiendavirtual.entidades.Orden;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "EmpresaEnvioMensaje"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "EmpresaEnvioMensaje")
})
public class EmpresaEnvioMensaje implements MessageListener {
    
    public EmpresaEnvioMensaje() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Orden orden = (Orden)objectMessage.getObject();
            System.out.println("Empresa de envio: Se ha recibido la notificacion para"
                    + "que los productos sean enviados a la direccion"
                    + orden.getInformacionEnvio().getDireccion() );
        } catch (JMSException ex) {
            Logger.getLogger(EmpresaEnvioMensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
