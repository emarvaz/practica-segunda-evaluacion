<%@page import="modelo.entidades.Usuario"%>
<%@page import="modelo.servicio.ServicioUsuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>Registro</title>
    </head>
    
    <body>
        <main>
            <section>
                <form action="ServletEditarExperienciaViaje" method="POST">
                    <input type="hidden" name="id-experiencia-viaje" value="${experienciaViaje.id}" />
                    
                    <label for="titulo-experiencia-viaje">Título</label>
                    <input type="text" name="titulo-experiencia-viaje" placeholder="Título" value="${experienciaViaje.titulo}" required />
                    
                    <label for="descripcion-experiencia-viaje">Descripción</label>
                    <textarea name="descripcion-experiencia-viaje" placeholder="Descripción" required>${experienciaViaje.descripcion}</textarea>
                    
                    <label for="fecha-inicio-experiencia-viaje">Título</label>
                    <fmt:formatDate var="fechaInicioFormateada" value="${experienciaViaje.fechaInicio}" pattern="yyyy-MM-dd" />
                    <input type="date" name="fecha-inicio-experiencia-viaje" placeholder="Fecha de inicio" value="${fechaInicioFormateada}" required />
                    
                    <button type="submit">Guardar cambios</button>
                </form>
            </section>
        </main>
    </body>
</html>
