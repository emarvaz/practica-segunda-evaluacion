package prueba;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

@WebServlet(name = "ServletAplicacion", urlPatterns = {"/normal/ServletAplicacion"})
public class ServletAplicacion extends HttpServlet {
    private final ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(Persistence.createEntityManagerFactory("Practica2PU"));

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
            ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(Persistence.createEntityManagerFactory("Practica2PU"));
            List<ExperienciaViaje> experienciasViajes = servicioExperienciaViaje.findExperienciaViajeEntities();

            request.setAttribute("experienciasViajes", experienciasViajes);
            
            getServletContext().getRequestDispatcher("/normal/aplicacion.jsp").forward(request, response);
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
        String descripcion = request.getParameter("descripcion");

        String fechaInicio = request.getParameter("fecha_inicio");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicial = null;
        try {
            fechaInicial = formatoFecha.parse(fechaInicio);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String titulo = request.getParameter("titulo");
        
        HttpSession sesion = request.getSession(false);
        Usuario usuario = null;

        if (sesion != null) {
            usuario = (Usuario) sesion.getAttribute("usuario");
        }

        ExperienciaViaje experiencia = new ExperienciaViaje(titulo, descripcion, fechaInicial, usuario);

        servicioExperienciaViaje.create(experiencia);

        response.sendRedirect("ServletAplicacion");
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
