/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author USUARIO
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_PERSONA",discriminatorType=DiscriminatorType.STRING,length=1)
public class Persona {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name="TIPO_ID")
    private TipoIdentificacion tipoId;

    @Column(length=15,nullable=false,name="NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    
    @Column(length=40,nullable=false)
    private String nombre1;
    
    @Column(length=40)
    private String nombre2;
    
    @Column(length=40,nullable=false)
    private String apellido1;
    @Column(length=40)
    private String apellido2;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthDate;

}
