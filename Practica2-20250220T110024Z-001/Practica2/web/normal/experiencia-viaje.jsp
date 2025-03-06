<%@page import="modelo.servicio.ServicioOpinion"%>
<%@page import="modelo.entidades.Opinion"%>
<%@page import="modelo.entidades.Usuario"%>
<%@page import="modelo.entidades.Actividad"%>
<%@page import="java.util.List"%>
<%@page import="modelo.entidades.ExperienciaViaje"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="modelo.servicio.ServicioExperienciaViaje"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>${experienciaViaje.titulo}</title>
    </head>
    <body>
        <h1>Título: ${experienciaViaje.titulo}</h1>
        
        <p>Descripción: ${experienciaViaje.descripcion}</p>
        <p>Fecha de Inicio: ${experienciaViaje.fechaInicio}</p>

        <h3>Actividades</h3>
        <ul>
            <core:choose>
                <core:when test="${not empty experienciaViaje.actividades}">
                    <core:forEach var="actividad" items="${experienciaViaje.actividades}">
                        <li>${actividad.titulo} - ${actividad.descripcion}</li>
                    </core:forEach>
                </core:when>
                <core:otherwise>
                    <li>No hay actividades asociadas.</li>
                </core:otherwise>
            </core:choose>
        </ul>

        <div>
            <a href="crear-actividad.jsp?id=${experienciaViaje.id}">Añadir actividad</a>
        </div>

        <h3>Agregar comentario:</h3>
        <form action="ProcesarComentario" method="POST">
            <input type="hidden" name="experienciaId" value="">
            
            <input type="hidden" name="usuarioId" value="">
            <div>
                <label for="contenido">Comentario:</label>
                <textarea id="contenido" name="contenido" required></textarea>
            </div>
            <button type="submit">Enviar Comentario</button>
        </form>
        
        <p>No se encontró la experiencia.</p>
        
        <section>
            <a href="ServletAplicacion">Volver</a>
        </section>
    </body>
</html>