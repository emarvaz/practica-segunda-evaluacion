/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.entidades.Opinion;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioActividad;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioOpinion;
import modelo.servicio.ServicioUsuario;

@WebServlet(name = "ServletCrearDatos", urlPatterns = {"/ServletCrearDatos"})
public class ServletCrearDatos extends HttpServlet {
    
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
        response.setContentType("text/html;charset=ISO-8859-1");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(entityManagerFactory);
        ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(entityManagerFactory);
        ServicioActividad servicioActividad = new ServicioActividad(entityManagerFactory);
        ServicioOpinion servicioOpinion = new ServicioOpinion(entityManagerFactory);
        
        Usuario administrador = new Usuario();
        administrador.setNombre("Administrador");
        administrador.setApellidos("");
        administrador.setEmail("administrador@gmail.com");
        administrador.setPassword("administrador");
        administrador.setTipo("administrador");
        administrador.setActivo(true);
        
        Usuario usuario = new Usuario();
        usuario.setNombre("Pepe");
        usuario.setApellidos("Pérez");
        usuario.setEmail("pepe@iescamas.es");
        usuario.setPassword("pepe");
        usuario.setTipo("normal");
        usuario.setActivo(true);
        
        servicioUsuario.create(usuario);
        servicioUsuario.create(administrador);
        
        ExperienciaViaje experienciaViaje = new ExperienciaViaje();
        experienciaViaje.setTitulo("Vacaciones de verano 2024");
        experienciaViaje.setDescripcion("Pasamos unos días en los Pirineos");
        experienciaViaje.setFechaInicio(new Date());
        experienciaViaje.setUsuario(usuario);
        
        servicioExperienciaViaje.create(experienciaViaje);
        
        Actividad actividad = new Actividad();
        actividad.setTitulo("Sendero del valle de Ordesa");
        actividad.setFecha(new Date());
        actividad.setDescripcion("Subimos por el valle de Ordesa hasta la cascada de La Cola de Caballo");
        actividad.getImagenes().add("foto1");
        
        experienciaViaje.getActividades().add(actividad);
        servicioActividad.create(actividad);
        
        Opinion opinion = new Opinion();
        opinion.setUsuario(usuario);
        opinion.setExperiencia(experienciaViaje);
        opinion.setContenido("Es una ruta espectacular");
        
        servicioOpinion.create(opinion);
        
        entityManagerFactory.close();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearDatos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Se han creado los datos de prueba</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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