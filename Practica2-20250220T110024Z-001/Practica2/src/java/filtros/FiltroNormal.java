/*
 * FiltroEmpleado.
 * Controla el acceso a todos los recursos de empleado registrado.
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
@WebFilter(filterName = "FiltroNormal", urlPatterns = {"/normal/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class FiltroNormal implements Filter {
    
    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
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
        }

        chain.doFilter(request, response);
    }


    
}
