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
        request.setCharacterEncoding("ISO-8859-1");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/inicio-sesion.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("contrasena");
        
            ServicioUsuario servicioUsuario = new ServicioUsuario(Persistence.createEntityManagerFactory("Practica2PU"));
            Usuario usuario = servicioUsuario.findByEmail(email);
            
            if (usuario != null && usuario.getPassword().equals(password)) {
                if (!usuario.isActivo()) {
                    request.setAttribute("error", usuario.getEmail() + " ha sido inactivada por el administrador.");
                    request.getRequestDispatcher("/inicio-sesion.jsp").forward(request, response);
                    
                    return;
                }
                
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                
                if (usuario.getTipo().equals("administrador")) {
                    response.sendRedirect("administrador/ServletAdministracion");
                } else {
                    response.sendRedirect("normal/ServletAplicacion");
                }
                    
            } else {
                request.setAttribute("error", "El email o la contraseña son incorrectos.");
                request.getRequestDispatcher("/inicio-sesion.jsp").forward(request, response);

                
                return;
            }
        } catch (Exception exception) {
            request.setAttribute("error", "El inicio de sesión ha fallado: " + exception.getMessage());
            request.getRequestDispatcher("/inicio-sesion.jsp").forward(request, response);
        }
    }
}