/*
 * FiltroAdmin.
 * Controla el acceso a todos los recursos de administrador
 */
package filtros;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Usuario;

/**
 *
 * @author Eduardo Martínez Vázquez
 */
@WebFilter(filterName = "FiltroAdministrador", urlPatterns = {"/administrador/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR})
public class FiltroAdministrador implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest peticion = (HttpServletRequest)request;
        HttpServletResponse respuesta = (HttpServletResponse)response;
        HttpSession sesion = peticion.getSession();
        
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        
        if (usuario == null) {
            respuesta.sendRedirect(peticion.getServletContext().getContextPath() + "/ServletInicioSesion");
            
            return;
        } else if (!usuario.getTipo().equals("administrador")) {
            respuesta.sendRedirect(peticion.getServletContext().getContextPath() + "/normal/ServletAplicacion");
            
            return;
        }
        
        chain.doFilter(request, response);
    }
    
}
