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
        <section>
            <h1>Título: ${experienciaViaje.titulo}</h1>

            <p>Descripción: ${experienciaViaje.descripcion}</p>
            <p>Fecha de Inicio: ${experienciaViaje.fechaInicio}</p>
        </section>

        <section>
            <h3>Actividades</h3>

            <ul>
                <core:choose>
                    <core:when test="${not empty experienciaViaje.actividades}">
                        <core:forEach var="actividad" items="${experienciaViaje.actividades}">
                            <li>
                                <div>${actividad.titulo}</div>
                                <div>${actividad.fecha}</div>
                                <div>${actividad.descripcion}</div>
                            </li>
                        </core:forEach>
                    </core:when>
                    <core:otherwise>
                        <li>No hay actividades asociadas.</li>
                    </core:otherwise>
                </core:choose>
            </ul>
            
            <a href="ServletCrearActividad?idExperienciaViaje=${experienciaViaje.id}"><button>Crear actividad</button></a>
        </section>
        
        <section>
            <h3>Crear comentario:</h3>
            
            <form action="ServletComentario" method="POST">
                <input type="hidden" name="idExperienciaViaje" value="">
                <input type="hidden" name="id-usuario" value="">
                
                <label for="contenido">Comentario:</label>
                <textarea name="comentario" required></textarea>
                    
                <button type="submit">Enviar comentario</button>
            </form>
        </section>

        
                
        <section>
            <a href="ServletAplicacion">Volver</a>
        </section>
    </body>
</html>