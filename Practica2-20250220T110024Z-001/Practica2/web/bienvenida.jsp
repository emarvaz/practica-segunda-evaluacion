<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>¡Bienvenido!</title>
        <style>
            .welcome-container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                text-align: center;
            }
            .welcome-message {
                font-size: 1.2em;
                margin: 20px 0;
            }
            .btn-continuar {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 4px;
                display: inline-block;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="welcome-container">
            <h1>¡Bienvenido a la aplicación!</h1>
            <div class="welcome-message">
                <p>Tu cuenta ha sido activada correctamente.</p>
                <p>Ya puedes comenzar a utilizar todas las funcionalidades de la aplicación.</p>
            </div>
            <a href="index.html" class="btn-continuar">Continuar a la página principal</a>
        </div>
    </body>
</html>