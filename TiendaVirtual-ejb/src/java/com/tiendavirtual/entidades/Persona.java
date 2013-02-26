/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author USUARIO
 */
@Entity
public class Persona {

    @Id
    private int id;

    @ManyToOne
    private TipoIdentificacion tipoId;

    private String numeroIdentificacion;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthDate;

}
