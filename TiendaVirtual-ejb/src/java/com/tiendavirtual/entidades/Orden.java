/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author USUARIO
 */
@Entity
public class Orden {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fecha;
    
    @OneToMany(mappedBy="orden")
    private List<Producto> productos;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_COMPRADOR")
    private Comprador comprador;
    
    @OneToOne(optional=false)
    @JoinColumn(name="ID_INF_FACTURA")
    private InformacionFactura informacionFactura;
    
    @OneToOne(optional=false)
    @JoinColumn(name="ID_INF-ENVIO")
    private InformacionEnvio informacionEnvio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public InformacionFactura getInformacionFactura() {
        return informacionFactura;
    }

    public void setInformacionFactura(InformacionFactura informacionFactura) {
        this.informacionFactura = informacionFactura;
    }

    public InformacionEnvio getInformacionEnvio() {
        return informacionEnvio;
    }

    public void setInformacionEnvio(InformacionEnvio informacionEnvio) {
        this.informacionEnvio = informacionEnvio;
    }
    
    

}
