<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
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
            <form action="ServletInicioSesion" method="POST">
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
            
            <p>¿No tienes cuenta? <a href="${pageContext.request.contextPath}/registro-usuario.jsp">Regístrate aquí</a></p>
        </main>
    </body>
</html>