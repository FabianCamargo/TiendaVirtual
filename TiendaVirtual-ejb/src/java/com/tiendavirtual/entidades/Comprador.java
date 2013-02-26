/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author USUARIO
 */
@Entity
@DiscriminatorValue(value="C")
public class Comprador extends Persona{

    @OneToMany(mappedBy="comprador")
    private List<Orden> ordenes;
    @Column(name="CANTIDAD_COMPRAS")
    private int cantidadCompras;
    
}
