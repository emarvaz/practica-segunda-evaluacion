package prueba;

import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.SolicitudRegistro;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioSolicitudRegistro;
import modelo.servicio.ServicioUsuario;

@WebServlet(name = "ProcesarRegistroUsuario", urlPatterns = {"/ProcesarRegistroUsuario"})
public class ProcesarRegistroUsuario extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Crear el objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipo("normal");
        usuario.setActivo(false);
        
        // Guardar en la base de datos
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(entityManagerFactory);
        ServicioSolicitudRegistro servicioSolicitudRegistro = new ServicioSolicitudRegistro(entityManagerFactory);
        
        try {
            servicioUsuario.create(usuario);
            
            // Crear notificaciÃ³n para el administrador
            SolicitudRegistro solicitudRegistro = new SolicitudRegistro();
            solicitudRegistro.setUsuario(usuario);
            solicitudRegistro.setTipo("REGISTRO_PENDIENTE");
            solicitudRegistro.setMensaje("Nuevo usuario pendiente de aprobación: " + nombre + " " + apellidos);
            solicitudRegistro.setFecha(new Date());
            solicitudRegistro.setLeida(false);
            solicitudRegistro.setProcesada(false);
            
            servicioSolicitudRegistro.create(solicitudRegistro);
            
            response.sendRedirect("registro-usuario-exitoso.jsp");
        } catch (Exception exception) {
            request.setAttribute("error", "Error al registrar el usuario: " + exception.getMessage());
            request.getRequestDispatcher("registro-usuario.jsp").forward(request, response);
        } finally {
            entityManagerFactory.close();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}