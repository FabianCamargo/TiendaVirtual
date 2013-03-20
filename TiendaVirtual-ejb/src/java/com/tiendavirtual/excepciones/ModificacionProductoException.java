/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author User
 */
@ApplicationException(rollback=true)
public class ModificacionProductoException extends Exception{
    
}
