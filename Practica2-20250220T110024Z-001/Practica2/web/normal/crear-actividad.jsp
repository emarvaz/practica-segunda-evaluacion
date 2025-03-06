<%-- 
    Document   : actividad
    Created on : 2 mar 2025, 9:31:28
    Author     : alfon
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Crear Nueva Actividad</h1>
        
        <!-- Formulario creación actividades -->
        <form action="ProcesarActividad" method="POST">
            <div>
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required>
            </div>
            <div>
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>
            </div>
            <div>
                <label for="fecha">Fecha:</label>
                <input type="date" id="fecha" name="fecha_inicio" required>
            </div>
            <button type="submit">Crear Actividad</button>
        </form>
        
        <!-- Formulario creación Imágenes -->

        <form action="ProcesatActividadImagen" method="POST">
            <div>
                <label for="imagenes">Imágenes (URLs):</label>
                <input type="file" id="imagenes" name="imagenes">
            </div>
            <button type="submit">Añadir imagen a la actividad</button>
        </form >
        
        <!-- Bótón de regreso -->
        <div>
            <a href="experiencia.jsp?id=<%= request.getParameter("id")%>" class="btn-continuar">Regresar</a>
        </div>
    </body>
</html>
