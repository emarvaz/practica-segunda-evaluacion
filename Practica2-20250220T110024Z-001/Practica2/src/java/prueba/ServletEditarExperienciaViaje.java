/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author Eduardo Martínez Vázquez
 */
@WebServlet(name = "ServletEditarExperienciaViaje", urlPatterns = {"/normal/ServletEditarExperienciaViaje"})
public class ServletEditarExperienciaViaje extends HttpServlet {
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
        ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(Persistence.createEntityManagerFactory("Practica2PU"));
        
        try {
            Long id = Long.valueOf(request.getParameter("id-experiencia-viaje"));
            String titulo = request.getParameter("titulo-experiencia-viaje");
            String descripcion = request.getParameter("descripcion-experiencia-viaje");
            String fechaInicio = request.getParameter("fecha-inicio-experiencia-viaje");
            
            SimpleDateFormat formatoFechaInicio = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicioFormateada = formatoFechaInicio.parse(fechaInicio);
            
            ExperienciaViaje experienciaViaje = servicioExperienciaViaje.findExperienciaViaje(id);
            experienciaViaje.setTitulo(titulo);
            experienciaViaje.setDescripcion(descripcion);
            experienciaViaje.setFechaInicio(fechaInicioFormateada);
            
            try {
                servicioExperienciaViaje.edit(experienciaViaje);
            } catch (Exception exception) {
            }
            
            response.sendRedirect("ServletProcesarExperienciaViaje");
        } catch (ParseException exception) {
            Logger.getLogger(ServletEditarExperienciaViaje.class.getName()).log(Level.SEVERE, null, exception);
        }
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
