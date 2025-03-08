<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Crear experiencia de viaje</title>
    </head>
    <body>
        <h1>Crear experiencia de viaje</h1>
        <form action="ServletCrearExperienciaViaje" method="POST">
            <div>
                <label for="titulo-experiencia-viaje">Título:</label>
                <input type="text" name="titulo-experiencia-viaje" required>
            </div>
            <div>
                <label for="descripcion-experiencia-viaje">Descripción:</label>
                <textarea name="descripcion-experiencia-viaje" required></textarea>
            </div>
            <div>
                <label for="fecha-inicio-experiencia-viaje">Fecha de inicio:</label>
                <input type="date" name="fecha-inicio-experiencia-viaje" required>
            </div>

            <button type="submit">Crear experiencia de viaje</button>
        </form>
    </body>
</html>
