/*
 * Servlet EnviarEmail.
 * Controlador para enviar email.
 */
package prueba;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Email;
import utilidades.Utilidades;

/**
 *
 * @author jose
 */
@WebServlet(name = "ServletEnviarEmail", urlPatterns = {"/ServletEnviarEmail"})
public class ServletEnviarEmail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String to = request.getParameter("to");
        if (to != null) {
            String subject = request.getParameter("subject");
            String text = request.getParameter("text");
            String from = request.getParameter("from");
            String password = request.getParameter("password");
            Email email = new Email();
            email.setTo(to);
            email.setSubject(subject);
            email.setText(text);
            email.setFrom(from);
            Utilidades u = new Utilidades();
            String error = "El email se ha enviado correctamente";
            try {
             u.enviarEmail(email, password);
            } catch (Throwable e) {
                error = "Error al enviar e-mail: <br>" + e.getClass().getName() + ":" + 
                        e.getMessage();
            }
            request.setAttribute("error", error);
        }
        // getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}