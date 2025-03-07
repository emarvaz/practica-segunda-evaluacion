/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Opinion;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioOpinion;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author alfon
 */
@WebServlet(name = "ProcesarComentario", urlPatterns = {"/ProcesarComentario"})
public class ServletComentario extends HttpServlet {

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

        // ConfiguraciÃ³n de la base de datos
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioOpinion opiService = new ServicioOpinion(emf);

        try {
            // Obtener el ID de la opinión desde la solicitud
            Long id = Long.parseLong(request.getParameter("id"));
            // Obtener objeto de la entidad "Opinion"
            Opinion opinion = opiService.findOpinion(id);
            // Verificar si la opinión fue encontrada
            if (opinion != null) {
                // Enviar la opinión a la vista experiencia.jsp
                request.setAttribute("opinion", opinion);
                request.getRequestDispatcher("opinion.jsp?id=" + id).forward(request, response);
            } else {
                request.setAttribute("error", "Opinión no encontrada.");
                request.getRequestDispatcher("experiencia.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener la opinión: " + e.getMessage());
            request.getRequestDispatcher("experiencia.jsp").forward(request, response);
        } finally {
            emf.close();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String contenido = request.getParameter("contenido");
        String expId = request.getParameter("experienciaId");
        String usuId = request.getParameter("usuarioId");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");

        ServicioExperienciaViaje sev = new ServicioExperienciaViaje(emf);
        ServicioUsuario su = new ServicioUsuario(emf);

        Opinion opinion = new Opinion();
        opinion.setContenido(contenido);
        opinion.setExperiencia(sev.findExperienciaViaje(Long.parseLong(expId)));
        opinion.setUsuario(su.findUsuario(Long.parseLong(usuId)));

        ServicioOpinion opi = new ServicioOpinion(emf);

        try {
            opi.create(opinion);

            // Obtener la lista de experiencias después de crear una nueva
            List<Opinion> opiniones = opi.findOpinionEntities();
            request.setAttribute("opiniones", opiniones);

            // Redirigir a aplicacion.jsp
            //request.getRequestDispatcher("ProcesarExperiencia").forward(request, response);
            response.sendRedirect("experiencia.jsp?id=" + expId);
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar opinion: " + e.getMessage());
            //request.getRequestDispatcher("ProcesarExperiencia").forward(request, response);
        } finally {
            emf.close();
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
