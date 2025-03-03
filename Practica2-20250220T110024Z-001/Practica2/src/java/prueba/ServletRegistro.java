package prueba;

import modelo.entidades.Solicitud;
import modelo.servicio.ServicioSolicitud;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registro")
public class ServletRegistro extends HttpServlet {
    private ServicioSolicitud servicioSolicitud = new ServicioSolicitud(Persistence.createEntityManagerFactory("Practica2PU"));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud solicitud = new Solicitud(
            request.getParameter("email"),
            request.getParameter("password"),
            request.getParameter("nombre"),
            request.getParameter("apellidos")
        );

        servicioSolicitud.crearSolicitud(solicitud);
        
        response.sendRedirect("registro-exitoso.jsp");
    }
}
