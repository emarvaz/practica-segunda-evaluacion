<%@ page import="java.util.List, modelo.entidades.Solicitud, modelo.servicio.ServicioSolicitud" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Solicitudes</title>
    </head>
    
    <body>
        <main>
            <%
                ServicioSolicitud servicioSolicitud = new ServicioSolicitud(javax.persistence.Persistence.createEntityManagerFactory("Practica2PU"));
                List<Solicitud> solicitudes = servicioSolicitud.obtenerSolicitudesPendientes();
            %>
            <table>
                <tr><th>Email</th><th>Nombre</th><th>Acciones</th></tr>
                
                <% for (Solicitud solicitud : solicitudes) { %>
                
                    <tr>
                        <td><%= solicitud.getEmail() %></td>
                        <td><%= solicitud.getNombre() %></td>
                        <td>
                            <form action="aceptarSolicitud" method="post">
                                <input type="hidden" name="id" value="<%= solicitud.getId() %>">
                                <button type="submit">Aceptar</button>
                            </form>
                                
                            <form action="rechazarSolicitud" method="post">
                                <input type="hidden" name="id" value="<%= solicitud.getId() %>">
                                <button type="submit">Rechazar</button>
                            </form>
                        </td>
                    </tr>
                    
                <% } %>
                
            </table>
        </main>
    </body>
</html>