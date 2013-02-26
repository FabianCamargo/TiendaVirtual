/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name="INFORMACION_ENVIO")
public class InformacionEnvio {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(length=50,nullable=false)
    private String pais;
    @Column(length=50,nullable=false)
    private String departamento;
    @Column(length=50,nullable=false)
    private String ciudad;
    @Column(length=50,nullable=false)
    private String direccion;
    
    
}
