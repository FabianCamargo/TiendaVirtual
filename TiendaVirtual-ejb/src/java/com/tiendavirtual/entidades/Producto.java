/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author USUARIO
 */
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(length=30,nullable=false)
    private String nombre;
    @Column(length=30,nullable=false)
    private String descripcion;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="FECHA_CREACION")
    private Calendar fechaCreacion;
    private long precio;
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_VENDEDOR",nullable=false)
    private Vendedor vendedor;
    @ManyToMany
    private List<Categoria> categorias;
    
    @ManyToOne
    @JoinColumn(name="ID_ORDEN")
    private Orden orden;
    

}
