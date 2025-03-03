package prueba;

import java.io.IOException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.servicio.ServicioSolicitud;

@WebServlet("/rechazarSolicitud")
public class ServletRechazarSolicitud extends HttpServlet {
    private ServicioSolicitud servicioSolicitud = new ServicioSolicitud(Persistence.createEntityManagerFactory("Practica2PU"));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idSolicitud = Long.parseLong(request.getParameter("id"));
        
        servicioSolicitud.eliminarSolicitud(idSolicitud);
        
        response.sendRedirect("administracion.jsp");
    }
}

