/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notificaciones;

import com.tiendavirtual.entidades.Orden;
import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Administrador
 */
public class NotificacionInterceptor {
    @Resource(mappedName="jms/CreacionOrdenTopicFactory")
    ConnectionFactory connectionFactory;
    @Resource(mappedName="jms/CreacionOrdenTopic")
    private Topic topic;
    
    @AroundInvoke
    public Object notificarCreacionOrden(InvocationContext ic) throws Exception{
        
        Object[] parametros = ic.getParameters();
        Orden orden = (Orden)parametros[0];
        
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(topic);
        
        ObjectMessage message = session.createObjectMessage();
        message.setObject(orden);
        
        messageProducer.send(message);
        
        session.close();
        connection.close();
        return ic.proceed();
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
}
