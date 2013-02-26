/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiendavirtual.entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author USUARIO
 */
@Entity
@DiscriminatorValue(value="V")
public class Vendedor extends Persona {

    @OneToMany(mappedBy="vendedor")
    private List<Producto> productos;
    private int calificacion;
    
    
}
