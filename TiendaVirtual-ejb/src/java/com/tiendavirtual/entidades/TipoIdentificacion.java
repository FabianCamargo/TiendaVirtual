/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name="TIPO_IDENTIFICACION")
public class TipoIdentificacion {

    @Id
    @Column(length=2)
    private String id;
    @Column(nullable=false,length=30)
    private String descripcion;


}
