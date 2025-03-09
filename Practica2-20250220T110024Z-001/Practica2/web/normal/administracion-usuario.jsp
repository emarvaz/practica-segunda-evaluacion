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
        
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            
            .tabla-padre {
                margin: 1rem 0;
            }
            
            .tabla-padre th {
                background-color: grey;
                color: white;
            }
            
            .tabla-hija tr {
                background-color: whitesmoke;
            }
            
            .tabla-hija tr:nth-of-type(even) {
                background-color: white;
            }

            
            
            td {
                vertical-align: top;
            }

            .acciones {
                display: grid;
            }
        </style>
    </head>

    <body>
        <main>
            
            <p>${error}</p>
            
            <section>
                <h1>Experiencias</h1>

                <a href="ServletCrearExperienciaViaje"><button>Crear nueva experiencia viaje</button></a>

                <table>
                    <core:choose>
                        <core:when test="${not empty experienciasViajes}">
                            <core:forEach var="experienciaViaje" items="${experienciasViajes}">
                                <tr>
                                    <td>
                                        <table class="tabla-padre" border="1" style="border-collapse: collapse; border-color: black">
                                            <tr>
                                                <th>Título</th>
                                                <th>Descripción</th>
                                                <th>Fecha de inicio</th>
                                                <th>Acciones</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <a href="ServletExperienciaViaje?idExperienciaViaje=${experienciaViaje.id}">${experienciaViaje.titulo}</a>
                                                </td>
                                                <td>${experienciaViaje.descripcion}</td>
                                                <fmt:formatDate var="fechaInicioFormateadaExperienciaViaje" value="${experienciaViaje.fechaInicio}" pattern="yyyy-MM-dd" />
                                                <td>${fechaInicioFormateadaExperienciaViaje}</td>
                                                <td>
                                                    <form action="ServletProcesarExperienciaViaje" method="POST" class="acciones">
                                                        <input type="hidden" name="idExperienciaViaje" value="${experienciaViaje.id}" />
                                                        
                                                        <button type="submit" name="accion" value="editar">Editar</button>
                                                        <button type="submit" name="accion" value="eliminar">Eliminar</button>
                                                    </form>
                                                        
                                                    <form action="ServletCrearActividad" method="POST" class="acciones">
                                                        <input type="hidden" name="idExperienciaViaje" value="${experienciaViaje.id}" />

                                                        <button type="submit" name="accion" value="crear">Crear actividad</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="4">Actividades</th>
                                            </tr>
                                            <tr>
                                                <td colspan="4">
                                                    <table class="tabla-hija" border="1" style="border-collapse: collapse; border-color: black">
                                                        <tr>
                                                            <th>Título</th>
                                                            <th>Fecha</th>
                                                            <th>Descripción</th>
                                                            <th>Acciones</th>
                                                        </tr>
                                                        <core:choose>
                                                            <core:when test="${not empty experienciaViaje.actividades}">
                                                                <core:forEach var="actividad" items="${experienciaViaje.actividades}">
                                                                    <tr>
                                                                        <td>${actividad.titulo}</td>
                                                                        <fmt:formatDate var="fechaFormateadaActividad" value="${actividad.fecha}" pattern="yyyy-MM-dd" />
                                                                        <td>${fechaFormateadaActividad}</td>
                                                                        <td>${actividad.descripcion}</td>
                                                                        <td>
                                                                            <form action="ServletProcesarActividad" method="POST" class="acciones">
                                                                                <input type="hidden" name="idExperienciaViaje" value="${experienciaViaje.id}" />
                                                                                <input type="hidden" name="idActividad" value="${actividad.id}" />
                                                                                
                                                                                <button type="submit" name="accion" value="eliminar">Eliminar</button>
                                                                            </form>
                                                                        </td>
                                                                    </tr>
                                                                </core:forEach>
                                                            </core:when>
                                                            <core:otherwise>
                                                                <tr>
                                                                    <td colspan="2">No hay actividades asociadas.</td>
                                                                </tr>
                                                            </core:otherwise>
                                                        </core:choose>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </core:forEach>
                        </core:when>
                        <core:otherwise>
                            <p>No hay experiencias de viajes disponibles</p>
                        </core:otherwise>
                    </core:choose>
                </table>
            </section>

            <section>
                <a href="ServletAplicacion"><button>Volver a la página principal</button></a>
            </section>
        </main>
    </body>
</html>
