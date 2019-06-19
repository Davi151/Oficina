package Servlet;

import Dao.UsuarioDao;
import Pojo.UsuarioPojo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


public class UsuarioServlet extends HttpServlet {
    UsuarioPojo usuarioPojo = new UsuarioPojo();
    UsuarioDao usuarioDao = new UsuarioDao();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException, NoSuchAlgorithmException {                  
            HttpSession session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");
            
            try {
                if (request.getParameter("id").equals("cadastro")) {
                    if(usuario == null){
                    response.sendRedirect("index.jsp");
                } else{
                    usuarioPojo.setU_LOGIN(request.getParameter("login"));
                    usuarioPojo.setU_SENHA(request.getParameter("senha"));
                    usuarioPojo.setF_FID(Integer.parseInt(request.getParameter("funcionarios")));
                
                    usuarioDao.salvar(usuarioPojo);
                    
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
                    requestDispatcher.forward(request, response);
                 }
            }                                            
        } catch (IOException | ServletException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar Usuário: " + e.getMessage());
        }
            
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short deion of the servlet.
     *
     * @return a String containing servlet deion
     */
    @Override
    public String getServletInfo() {
        return "Short deion";
    }// </editor-fold>

}
