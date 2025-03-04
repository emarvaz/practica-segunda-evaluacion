/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package prueba;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioUsuario;

@WebServlet(name = "ServletInicioSesion", urlPatterns = {"/ServletInicioSesion"})
public class ServletInicioSesion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioUsuario servicioUsuario = new ServicioUsuario(entityManagerFactory);
        
        try {
            Usuario usuario = servicioUsuario.findByEmail(email);
            
            if (usuario != null && usuario.getPassword().equals(password)) {
                if (!usuario.isActivo()) {
                    request.setAttribute("error", "Tu solicitud de registro aún no ha sido aprobada por un administrador.");
                    request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
                    
                    return;
                }
                
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                
                if (usuario.getTipo().equals("administrador")) {
                    response.sendRedirect("administracion.jsp");
                } else {
                    response.sendRedirect("bienvenida.jsp");
                }
                    
            } else {
                request.setAttribute("error", "Email o contraseña incorrectos");
                request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error en el login: " + e.getMessage());
            request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
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