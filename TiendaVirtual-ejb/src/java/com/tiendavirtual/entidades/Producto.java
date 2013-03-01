/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author USUARIO
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllProducts", query="SELECT p FROM Producto p"),
    @NamedQuery(name="findProductById", query="SELECT p FROM Producto p WHERE p.id =: idProducto")
})
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
    @ManyToMany(fetch= FetchType.LAZY)
    private List<Categoria> categorias;
    
    @ManyToOne
    @JoinColumn(name="ID_ORDEN")
    private Orden orden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
    
    
}
