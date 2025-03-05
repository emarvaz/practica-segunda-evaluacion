<%@page import="modelo.entidades.Usuario" %>
<%@page import="modelo.servicio.ServicioUsuario" %>
<%@page import="java.util.List, modelo.entidades.Solicitud, modelo.servicio.ServicioSolicitud" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Solicitudes</title>
    </head>
    
    <body>
        <main>
            <section>
                <%
                    ServicioSolicitud servicioSolicitud = new ServicioSolicitud(javax.persistence.Persistence.createEntityManagerFactory("Practica2PU"));
                    List<Solicitud> solicitudes = servicioSolicitud.obtenerSolicitudesPendientes();
                %>
                
                <h2>Solicitudes</h2>

                <table border="1" style="border-collapse: collapse;">
                    <tr>
                        <th>E-mail</th>
                        <th>Nombre y apellidos</th>
                        <th>Acciones</th>
                    </tr>
                    <% for (Solicitud solicitud : solicitudes) { %>
                        <tr>
                            <td><%= solicitud.getEmail() %></td>
                            <td><%= solicitud.getNombre() + " " + solicitud.getApellidos()%></td>
                            <td>
                                <form action="ServletAceptarSolicitud" method="post">
                                    <input type="hidden" name="id" value="<%= solicitud.getId() %>">
                                    <button type="submit">Aceptar</button>
                                </form>

                                <form action="ServletRechazarSolicitud" method="post">
                                    <input type="hidden" name="id" value="<%= solicitud.getId() %>">
                                    <button type="submit">Rechazar</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </section>
                    
            <section>
                <%
                    ServicioUsuario servicioUsuario = new ServicioUsuario(javax.persistence.Persistence.createEntityManagerFactory("Practica2PU"));
                    List<Usuario> usuarios = servicioUsuario.findUsuarioEntities();
                %>
                
                <h2>Usuarios</h2>
                
                <table border="1" style="border-collapse: collapse">
                    <tr>
                        <th>Id</th>
                        <th>E-mail</th>
                        <th>Contraseña</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Tipo</th>
                        <th>Activo</th>
                    </tr>
                    <% for (Usuario usuario : usuarios) { %>
                        <tr>
                            <td><%= usuario.getId() %></td>
                            <td><%= usuario.getEmail() %></td>
                            <td><%= usuario.getPassword() %></td>
                            <td><%= usuario.getNombre() %></td>
                            <td><%= usuario.getApellidos() %></td>
                            <td><%= usuario.getTipo() %></td>
                            <td>
                                <% if (usuario.isActivo() == true) { %>
                                    Activo
                                <% } else { %>
                                    Inactivo
                                <% } %>
                            </td>
                            <td>
                                <a href="./editar-usuario.jsp?id-usuario=<%= usuario.getId() %>"><button>Editar</button></a>

                                <form action="ServletEliminarUsuario" method="POST">
                                    <input type="hidden" name="id-usuario" value="<%= usuario.getId() %>">
                                    
                                    <button type="submit">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </table>
            </section>
                
            <section>
                <a href="index.html"><button>Volver a la página principal</button></a>
            </section>
        </main>
    </body>
</html>