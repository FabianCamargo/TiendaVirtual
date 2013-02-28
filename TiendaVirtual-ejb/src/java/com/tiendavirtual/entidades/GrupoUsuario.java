/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity(name="GRUPO_USUARIO")
public class GrupoUsuario {
    
    @EmbeddedId
    private GrupoUsuarioPK id;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_USUARIO", insertable=false, updatable=false)
    private Usuario usuario;

    public GrupoUsuarioPK getId() {
        return id;
    }

    public void setId(GrupoUsuarioPK id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
