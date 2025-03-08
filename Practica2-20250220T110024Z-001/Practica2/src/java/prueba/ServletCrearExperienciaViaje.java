/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;

@WebServlet(name = "ServletCrearExperienciaViaje", urlPatterns = {"/normal/ServletCrearExperienciaViaje"})
public class ServletCrearExperienciaViaje extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/normal/crear-experiencia-viaje.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        
        try {
            String titulo = request.getParameter("titulo-experiencia-viaje");
            String descripcion = request.getParameter("descripcion-experiencia-viaje");
            String fechaInicio = request.getParameter("fecha-inicio-experiencia-viaje");
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicioFormateada = formatoFecha.parse(fechaInicio);
            
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            
            ExperienciaViaje experienciaViaje = new ExperienciaViaje();
            experienciaViaje.setTitulo(titulo);
            experienciaViaje.setDescripcion(descripcion);
            experienciaViaje.setFechaInicio(fechaInicioFormateada);
            experienciaViaje.setUsuario(usuario);

            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(entityManagerFactory);
            servicioExperienciaViaje.create(experienciaViaje);
            
            response.sendRedirect("ServletAplicacion");
        } catch (Exception exception) {
            request.setAttribute("error", "Ha ocurrido un error: " + exception.toString());
            exception.printStackTrace();
            
            request.getRequestDispatcher("/normal/crear-experiencia-viaje.jsp").forward(request, response);
        } finally {
            entityManagerFactory.close();
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
