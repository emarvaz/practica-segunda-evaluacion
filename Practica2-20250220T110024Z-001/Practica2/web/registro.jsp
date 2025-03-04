<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <main>
            <form action="ServletRegistro" method="POST">
                <input type="email" name="email" placeholder="E-mail" required>
                <input type="password" name="password" placeholder="Contraseña" required>
                <input type="text" name="nombre" placeholder="Nombre" required>
                <input type="text" name="apellidos" placeholder="Apellidos" required>
                
                <button type="submit">Registrarse</button>
            </form>
        </main>
    </body>
</html>
