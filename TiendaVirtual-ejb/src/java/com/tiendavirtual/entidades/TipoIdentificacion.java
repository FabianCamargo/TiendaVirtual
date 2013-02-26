/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author USUARIO
 */
@Entity
public class TipoIdentificacion {

    @Id
    private String id;
    private String descripcion;


}
