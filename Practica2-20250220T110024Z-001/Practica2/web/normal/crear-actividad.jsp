<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Crear actividad</title>
    </head>
    <body>
        <h1>Crear actividad</h1>
        
        <div>${error}</div>
        
        <form action="ServletCrearActividad" method="POST">
            <div>
                <input type="hidden" name="idExperienciaViaje" value="${param.idExperienciaViaje}">
            </div>
            <div>
                <label for="titulo-actividad">Título:</label>
                <input type="text" name="titulo-actividad" required>
            </div>
            <div>
                <label for="descripcion-actividad">Descripción:</label>
                <textarea name="descripcion-actividad" required></textarea>
            </div>
            <div>
                <label for="fecha-inicio-actividad">Fecha:</label>
                <input type="date" name="fecha-inicio-actividad" required>
            </div>
            
            <button type="submit">Crear actividad</button>
        </form>
        
        <section>
            <a href="ServletExperienciaViaje?idExperienciaViaje=${param.idExperienciaViaje}">Volver a la experiencia de viaje</a>
        </section>
    </body>
</html>
