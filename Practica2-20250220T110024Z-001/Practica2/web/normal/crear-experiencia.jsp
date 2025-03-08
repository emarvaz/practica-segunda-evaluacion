<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Crear experiencia de viaje</title>
    </head>
    <body>
        <h1>Crear experiencia de viaje</h1>
        <form action="ServletAplicacion" method="POST">
            <div>
                <label for="descripcion">Descripci�n:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>
            </div>
            <div>
                <label for="fecha_inicio">Fecha de Inicio:</label>
                <input type="date" id="fecha_inicio" name="fecha_inicio" required>
            </div>
            <div>
                <label for="titulo">T�tulo:</label>
                <input type="text" id="titulo" name="titulo" required>
            </div>

            <button type="submit">Crear experiencia</button>
        </form>
    </body>
</html>
