package com.tiendavirtual.cliente;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tiendavirtual.entidades.Comprador;
import com.tiendavirtual.entidades.InformacionEnvio;
import com.tiendavirtual.entidades.InformacionFactura;
import com.tiendavirtual.entidades.Producto;
import com.tiendavirtual.excepciones.CreacionOrdenException;
import com.tiendavirtual.excepciones.ModificacionProductoException;
import com.tiendavirtual.logica.AdministracionOrdenLocal;
import com.tiendavirtual.logica.AdministracionPersistenciaLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
@WebServlet(urlPatterns = {"/ClienteEJB"})
public class ClienteEJB extends HttpServlet {

    @EJB
    AdministracionPersistenciaLocal administracionPersistencia;
    @EJB
    AdministracionOrdenLocal administracionOrden;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteEJB</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteEJB at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            

            Producto producto = administracionPersistencia.consultarProducto(1);
            administracionOrden.adicionarProducto(producto);
            producto = new Producto();
            producto = administracionPersistencia.consultarProducto(2);
            administracionOrden.adicionarProducto(producto);

            Comprador comprador = new Comprador();
            comprador.setId(3);
            administracionOrden.adicionarComprador(comprador);

            InformacionEnvio informacionEnvio = new InformacionEnvio();
            informacionEnvio.setCiudad("BOGOTA");
            informacionEnvio.setDepartamento("CUNDINAMARCA");
            informacionEnvio.setPais("COLOMBIA");
            informacionEnvio.setDireccion("CR50 N30-22");
            administracionOrden.adicionarInformacionEnvio(informacionEnvio);

            InformacionFactura informacionFactura = new InformacionFactura();
            informacionFactura.setCodigoTarjeta("0001");
            informacionFactura.setFechaExpiracion(Calendar.getInstance());
            informacionFactura.setNumeroCuenta("123456789");
            administracionOrden.adicionarInformacionFactura(informacionFactura);
            try {
                administracionOrden.crearOrdenCompra();
            } catch (CreacionOrdenException ex) {
                Logger.getLogger(ClienteEJB.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ocurrio un error al crear la orden de compra");
            } catch (ModificacionProductoException ex) {
                Logger.getLogger(ClienteEJB.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ocurrio un error al modificar los productos de la orden de compra");
            }

        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


}
