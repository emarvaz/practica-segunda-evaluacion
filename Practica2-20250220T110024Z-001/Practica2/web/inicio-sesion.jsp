<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Iniciar sesi�n</title>
    </head>
    
    <body>
        <main>
            <h2>Iniciar sesi�n</h2>
            
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
                    <label for="password">Contrase�a:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit">Iniciar sesi�n</button>
            </form>
            
            <p>�No tienes cuenta? <a href="${pageContext.request.contextPath}/registro-usuario.jsp">Reg�strate aqu�</a></p>
        </main>
    </body>
</html>