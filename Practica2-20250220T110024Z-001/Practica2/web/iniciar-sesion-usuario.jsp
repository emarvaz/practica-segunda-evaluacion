<%-- 
    Document   : registro-usuario
    Created on : 27 feb 2025, 19:02:34
    Author     : Eduardo Martínez Vázquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Iniciar sesión</title>
    </head>
    <body>
        <main>
            <h2>Iniciar sesión</h2>
            
            <% if (request.getAttribute("error") != null) { %>
                <div>
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            <form action="ProcesarInicioSesionUsuario" method="POST">
                <div>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div>
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit">Iniciar sesión</button>
            </form>
            <p>¿No tienes cuenta? <a href="registro-usuario.jsp">Regístrate aquí</a></p>
        </main>
    </body>
</html>