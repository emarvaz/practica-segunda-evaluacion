<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Iniciar sesi�n</title>
    </head>
    
    <body>
        <main>
            <h2>Iniciar sesi�n</h2>
            
            <form action="ServletInicioSesion" method="POST">
                <div>
                    <label for="email">Email:</label>
                    <input type="email" name="email" required>
                </div>
                
                <div>
                    <label for="contrasena">Contrase�a:</label>
                    <input type="password" name="contrasena" required>
                </div>
                
                <button type="submit">Iniciar sesi�n</button>
            </form>
            
            <core:if test="${not empty error}">
                <p>${error}</p>
            </core:if>
            
            <p>�No tienes cuenta? <a href="ServletRegistro">Reg�strate aqu�</a></p>
        </main>
    </body>
</html>