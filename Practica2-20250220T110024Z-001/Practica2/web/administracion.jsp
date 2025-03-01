<%@page import="modelo.entidades.SolicitudRegistro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.servicio.ServicioSolicitudRegistro"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Solicitudes</title>
    </head>
    <body>
        <main>
            <h2>Solicitudes pendientes</h2>
            
            <%
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practica2PU");
                ServicioSolicitudRegistro servicioSolicitudRegistro = new ServicioSolicitudRegistro(entityManagerFactory);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                
                List<SolicitudRegistro> solicitudesRegistro = servicioSolicitudRegistro.buscarPendientes();
                
                for (SolicitudRegistro solicitudRegistro : solicitudesRegistro) {
            %>
            <div>
                <h3><%= solicitudRegistro.getTipo() %></h3>
                <p><%= solicitudRegistro.getMensaje() %></p>
                <p>Fecha: <%= simpleDateFormat.format(solicitudRegistro.getFecha()) %></p>
                <div>
                    <form action="ProcesarSolicitudRegistro" method="POST"">
                        <input type="hidden" name="notificacionId" value="<%= solicitudRegistro.getId() %>">
                        <input type="hidden" name="usuarioId" value="<%= solicitudRegistro.getUsuario().getId() %>">
                        <button type="submit" name="accion" value="aprobar">Aprobar</button>
                        <button type="submit" name="accion" value="rechazar">Rechazar</button>
                    </form>
                </div>
            </div>
            <%
                }
                entityManagerFactory.close();
                
                if (solicitudesRegistro.isEmpty()) {
            %>
                <p>No hay solicitudes de registro pendientes</p>
            <%
                }
            %>
        </main>
    </body>
</html>