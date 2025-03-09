package prueba;

import modelo.entidades.Solicitud;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioSolicitud;
import modelo.servicio.ServicioUsuario;

import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;

@WebServlet(name = "ServletRegistro", urlPatterns = {"/ServletRegistro"})
public class ServletRegistro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        
        ServicioSolicitud servicioSolicitud = new ServicioSolicitud(entityManagerFactory);
        ServicioUsuario servicioUsuario = new ServicioUsuario(entityManagerFactory);
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");

        Usuario usuarioExistente = servicioUsuario.findByEmail(email);
        
        if (usuarioExistente != null) {
            request.setAttribute("error", "El correo electrónico ya está registrado.");
            getServletContext().getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }

        Solicitud solicitudExistente = servicioSolicitud.findByEmail(email);
        
        if (solicitudExistente != null) {
            request.setAttribute("error", "Ya existe una solicitud pendiente con este correo.");
            getServletContext().getRequestDispatcher("/registro.jsp").forward(request, response);
            return;
        }

        Solicitud solicitud = new Solicitud(email, password, nombre, apellidos);
        servicioSolicitud.crearSolicitud(solicitud);

        response.sendRedirect("./");
    }
}
