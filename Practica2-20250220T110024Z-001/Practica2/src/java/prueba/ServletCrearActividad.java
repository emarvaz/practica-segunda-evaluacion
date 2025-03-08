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
import modelo.entidades.Actividad;
import modelo.entidades.ExperienciaViaje;
import modelo.servicio.ServicioActividad;
import modelo.servicio.ServicioExperienciaViaje;

@WebServlet(name = "ServletCrearActividad", urlPatterns = {"/normal/ServletCrearActividad"})
public class ServletCrearActividad extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/normal/crear-actividad.jsp").forward(request, response);
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
            String titulo = request.getParameter("titulo-actividad");
            String descripcion = request.getParameter("descripcion-actividad");
            String fechaInicio = request.getParameter("fecha-inicio-actividad");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(fechaInicio);
            Long idExperienciaViaje = Long.valueOf(request.getParameter("idExperienciaViaje"));

            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(entityManagerFactory);
            ExperienciaViaje experienciaViaje = servicioExperienciaViaje.findExperienciaViaje(idExperienciaViaje);
            
            Actividad actividad = new Actividad();
            actividad.setTitulo(titulo);
            actividad.setDescripcion(descripcion);
            actividad.setFecha(fecha);
            actividad.setExperienciaViaje(experienciaViaje);

            ServicioActividad servicioActividad = new ServicioActividad(entityManagerFactory);
            servicioActividad.create(actividad);
            
            response.sendRedirect("ServletExperienciaViaje?idExperienciaViaje=" + idExperienciaViaje);
        } catch (Exception exception) {
            request.setAttribute("error", "Ha ocurrido un error: " + exception.toString());
            exception.printStackTrace();
            
            request.getRequestDispatcher("/normal/crear-actividad.jsp").forward(request, response);
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
