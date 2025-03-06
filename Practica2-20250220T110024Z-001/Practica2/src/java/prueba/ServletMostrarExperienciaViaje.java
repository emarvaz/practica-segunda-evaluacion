/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.entidades.ExperienciaViaje;

@WebServlet(name = "ServletMostrarExperienciaViaje", urlPatterns = {"/normal/ServletMostrarExperienciaViaje"})
public class ServletMostrarExperienciaViaje extends HttpServlet {

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
            Long idExperienciaViaje = Long.valueOf(request.getParameter("id-experiencia-viaje"));

            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(Persistence.createEntityManagerFactory("Practica2PU"));   
            ExperienciaViaje experienciaViaje = servicioExperienciaViaje.findExperienciaViaje(idExperienciaViaje);

            if (experienciaViaje != null) {
                request.setAttribute("experienciaViaje", experienciaViaje);
                getServletContext().getRequestDispatcher("/normal/experiencia-viaje.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/normal/aplicacion.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/normal/aplicacion.jsp");
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
