<%@page import="modelo.entidades.Usuario"%>
<%@page import="modelo.servicio.ServicioUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <main>
            <section>
                <%
                    ServicioUsuario servicioUsuario = new ServicioUsuario(javax.persistence.Persistence.createEntityManagerFactory("Practica2PU"));
                    Long idUsuario = Long.valueOf(request.getParameter("id-usuario"));
                    Usuario usuario = servicioUsuario.findUsuario(idUsuario);
                %>
                
                <form action="ServletEditarUsuario" method="POST">
                    <input type="hidden" name="id-usuario" value="<%= usuario.getId() %>" />
                        
                    <label for="e-mail-usuario">E-mail</label>
                    <input type="email" name="e-mail-usuario" placeholder="E-mail" value="<%= usuario.getEmail() %>" required />
                    
                    <label for="contrasena-usuario">Contraseña</label>
                    <input type="password" name="contrasena-usuario" placeholder="Contraseña" value="<%= usuario.getPassword() %>" required />
                    
                    <label for="nombre-usuario">Nombre</label>
                    <input type="text" name="nombre-usuario" placeholder="Nombre" value="<%= usuario.getNombre() %>" required />
                    
                    <label for="apellidos-usuario">Apellidos</label>
                    <input type="text" name="apellidos-usuario" placeholder="Apellidos" value="<%= usuario.getApellidos() %>" required />
                    
                    <label for="tipo-usuario">Tipo</label>
                    <select type="text" name="tipo-usuario" placeholder="Tipo" required>
                        <option value="administrador"
                            <% if (usuario.getTipo().equals("administrador")) { %>
                                selected
                            <% } %>
                        >
                            Administrador
                        </option>
                        <option value="normal"
                            <% if (usuario.getTipo().equals("normal")) { %>
                                selected
                            <% } %>
                        >
                            Normal
                        </option>
                    </select>
                        
                    <label for="esta-activo-usuario">Está activo</label>
                    <input type="checkbox" name="esta-activo-usuario"
                        <% if (usuario.isActivo()) { %>
                            checked
                        <% } %>
                    required />

                    <button type="submit">Guardar</button>
                </form>
            </section>
        </main>
    </body>
</html>
