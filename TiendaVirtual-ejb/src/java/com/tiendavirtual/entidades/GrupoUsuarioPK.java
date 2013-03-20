/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class GrupoUsuarioPK implements Serializable {
    @Basic(optional=false)
    @Column(name="ID_USUARIO")
    private String idUsuario;
    
    @Basic(optional=false)
    @Column(name="ID_GRUPO_USUARIO")
    private String idGrupoUsuario;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdGrupoUsuario() {
        return idGrupoUsuario;
    }

    public void setIdGrupoUsuario(String idGrupoUsuario) {
        this.idGrupoUsuario = idGrupoUsuario;
    }
    
    
    
}
