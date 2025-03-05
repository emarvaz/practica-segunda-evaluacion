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
import modelo.entidades.Email;
import utilidades.Utilidades;

@WebServlet(name = "/ServletAceptarSolicitud",  urlPatterns = "/administrador/ServletAceptarSolicitud")
public class ServletAceptarSolicitud extends HttpServlet {
    private final ServicioUsuario servicioUsuario = new ServicioUsuario(Persistence.createEntityManagerFactory("Practica2PU"));
    private final ServicioSolicitud servicioSolicitud = new ServicioSolicitud(Persistence.createEntityManagerFactory("Practica2PU"));
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idSolicitud = Long.valueOf(request.getParameter("id"));
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
            
            String to = usuario.getEmail();
            String subject = "Solicitud de registro aceptada";
            String text = "Bienvenido " + usuario.getNombre() + " " + usuario.getApellidos() + ",\n\nTu solicitud de registro ha sido aprobada. Ahora eres un usuario activo de nuestro sistema.";
            String from = "martvazqedua@gmail.com";
            String password = "msws bmdd upao aipf";

            Email email = new Email();
            email.setTo(to);
            email.setSubject(subject);
            email.setText(text);
            email.setFrom(from);
            
            Utilidades utilidades = new Utilidades();
            String error = "El e-mail de confirmación se ha enviado correctamente.";
            
            try {
                utilidades.enviarEmail(email, password);
            } catch (Throwable e) {
                error = "Error al enviar el e-mail de confirmación: <br>" + e.getClass().getName() + ":" + e.getMessage();
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("ServletAdministracion");
    }
}