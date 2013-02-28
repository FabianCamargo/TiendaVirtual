/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavirtual.logica;

import com.tiendavirtual.entidades.Comprador;
import com.tiendavirtual.entidades.InformacionEnvio;
import com.tiendavirtual.entidades.InformacionFactura;
import com.tiendavirtual.entidades.Producto;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface AdministracionOrdenLocal {
    
    void adicionarProducto(Producto producto);
    void adicionarComprador(Comprador comprador);
    void adicionarInformacionFactura(InformacionFactura informacionFactura);
    void adicionarInformacionEnvio(InformacionEnvio informacionEnvio);
    Integer crearOrdenCompra();
    
}
