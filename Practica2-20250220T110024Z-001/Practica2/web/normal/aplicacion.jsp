<%@page import="modelo.entidades.ExperienciaViaje"%>
<%@page import="modelo.servicio.ServicioExperienciaViaje"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Aplicación</title>
    </head>
    
    <body>
        <main>
            <section>
                <h1>Experiencias</h1>
                
                <a href="ServletCrearExperiencia"><button>Crear nueva experiencia</button></a>

                <table border="1">
                    <tr>
                        <th>Título</th>
                        <th>Fecha de inicio</th>
                    </tr>
                    
                    <core:choose>
                        <core:when test="${not empty experienciasViajes}">
                            <core:forEach var="experienciaViaje" items="${experienciasViajes}">
                                <tr>
                                    <td>
                                        <a href="ServletExperienciaViaje?idExperienciaViaje=${experienciaViaje.id}">${experienciaViaje.titulo}</a>
                                    </td>
                                    <td>${experienciaViaje.fechaInicio}</td>
                                </tr>
                            </core:forEach>
                        </core:when>
                        <core:otherwise>
                            <tr>
                                <td colspan="2">No hay experiencias de viaje registradas.</td>
                            </tr>
                        </core:otherwise>
                    </core:choose>
                </table>
            </section>
            
            <section>
                <a href="../">Volver a la página principal</a>
            </section>
        </main>
    </body>
</html>
