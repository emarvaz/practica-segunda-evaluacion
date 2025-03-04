/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author Eduardo Martínez Vázquez
 */
@WebServlet(name = "ServletEditarUsuario", urlPatterns = {"/ServletEditarUsuario"})
public class ServletEditarUsuario extends HttpServlet {
    ServicioUsuario servicioUsuario = new ServicioUsuario(Persistence.createEntityManagerFactory("Practica2PU"));

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idUsuario = Long.valueOf(request.getParameter("id-usuario"));
        String emailUsuario = request.getParameter("e-mail-usuario");
        String contrasenaUsuario = request.getParameter("contrasena-usuario");
        String nombreUsuario = request.getParameter("nombre-usuario");
        String apellidosUsuario = request.getParameter("apellidos-usuario");
        String tipoUsuario = request.getParameter("tipo-usuario");
        Boolean estaActivoUsuario = Boolean.valueOf(request.getParameter("esta-activo-usuario"));
        
        Usuario usuario = servicioUsuario.findUsuario(idUsuario);
        
        usuario.setEmail(emailUsuario);
        usuario.setPassword(contrasenaUsuario);
        usuario.setNombre(nombreUsuario);
        usuario.setApellidos(apellidosUsuario);
        usuario.setTipo(tipoUsuario);
        usuario.setActivo(estaActivoUsuario);
        
        try {
            servicioUsuario.edit(usuario);
        } catch (Exception e) {
            Logger.getLogger(ServletEditarUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        
        response.sendRedirect("administracion.jsp");
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
