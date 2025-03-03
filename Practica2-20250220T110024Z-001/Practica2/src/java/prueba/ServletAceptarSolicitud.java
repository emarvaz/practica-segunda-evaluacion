package prueba;

import java.io.IOException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Solicitud;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioSolicitud;
import modelo.servicio.ServicioUsuario;

@WebServlet("/aceptarSolicitud")
public class ServletAceptarSolicitud extends HttpServlet {
    private ServicioUsuario servicioUsuario = new ServicioUsuario(Persistence.createEntityManagerFactory("Practica2PU"));
    private ServicioSolicitud servicioSolicitud = new ServicioSolicitud(Persistence.createEntityManagerFactory("Practica2PU"));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idSolicitud = Long.parseLong(request.getParameter("id"));
        
        Solicitud solicitud = servicioSolicitud.buscarPorId(idSolicitud);

        if (solicitud != null) {
            Usuario usuario = new Usuario();
            usuario.setEmail(solicitud.getEmail());
            usuario.setPassword(solicitud.getPassword());
            usuario.setNombre(solicitud.getNombre());
            usuario.setApellidos(solicitud.getApellidos());
            usuario.setActivo(true);

            servicioUsuario.create(usuario);
            servicioSolicitud.eliminarSolicitud(idSolicitud);
        }
        
        response.sendRedirect("administracion.jsp");
    }
}