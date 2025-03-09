/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
@WebServlet(name = "ServletProcesarExperienciaViaje", urlPatterns = {"/normal/ServletProcesarExperienciaViaje"})
public class ServletProcesarExperienciaViaje extends HttpServlet {

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
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(entityManagerFactory);

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (usuario != null) {
                List<ExperienciaViaje> experienciasViajes = servicioExperienciaViaje.findExperienciaViajeEntitiesByUsuario(usuario.getId());

                request.setAttribute("experienciasViajes", experienciasViajes);
                getServletContext().getRequestDispatcher("/normal/administracion-usuario.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/normal/ServletInicioSesion");
            }
        } catch (Exception exception) {
            request.setAttribute("error", "Algo no ha salido bien: " + exception);
        }
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
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        
        try {
            Long idExperienciaViaje = Long.valueOf(request.getParameter("idExperienciaViaje"));
            String accion = request.getParameter("accion");
            
            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(entityManagerFactory);

            if (accion.equals("eliminar")) {
                servicioExperienciaViaje.destroy(idExperienciaViaje);
                
                response.sendRedirect("ServletProcesarExperienciaViaje");
            } else {
                response.sendRedirect("ServletProcesarExperienciaViaje");
            }
        } catch (Exception exception) {
            
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
