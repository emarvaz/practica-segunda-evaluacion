package prueba;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioUsuario;
import modelo.servicio.ServicioSolicitudRegistro;
import modelo.servicio.exceptions.NonexistentEntityException;

@WebServlet(name = "ProcesarSolicitudRegistro", urlPatterns = {"/ProcesarSolicitudRegistro"})
public class ProcesarSolicitudRegistro extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long notificacionId = Long.parseLong(request.getParameter("notificacionId"));
        Long usuarioId = Long.parseLong(request.getParameter("usuarioId"));
        String accion = request.getParameter("accion");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioUsuario su = new ServicioUsuario(emf);
        ServicioSolicitudRegistro sn = new ServicioSolicitudRegistro(emf);
        
        try {
            Usuario usuario = su.findUsuario(usuarioId);
            if (usuario != null) {
                if ("aprobar".equals(accion)) {
                    usuario.setActivo(true);
                    try {
                        su.edit(usuario);
                    } catch (NonexistentEntityException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("administracion.jsp").forward(request, response);
                        return;
                    } catch (Exception e) {
                        request.setAttribute("error", "Error al actualizar el usuario: " + e.getMessage());
                        request.getRequestDispatcher("administracion.jsp").forward(request, response);
                        return;
                    }
                } else if ("rechazar".equals(accion)) {
                    try {
                        su.destroy(usuarioId);
                    } catch (NonexistentEntityException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("administracion.jsp").forward(request, response);
                        return;
                    }
                }
            }
            
            sn.marcarComoProcesada(notificacionId);
            
            response.sendRedirect("administracion.jsp");
        } finally {
            emf.close();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}