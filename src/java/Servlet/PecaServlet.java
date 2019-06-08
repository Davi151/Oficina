package Servlet;

import Dao.PecaDao;
import Pojo.PecaPojo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

public class PecaServlet extends HttpServlet {

    PecaDao pecaDao = new PecaDao();
    ArrayList listPecaPojo = null;
    PecaPojo pecaPojo = new PecaPojo();
    
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
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        double valor_total = 0.0;
        
        try {
            
            if(request.getParameter("id").equals("cadastro")){
                if(usuario == null){
                    response.sendRedirect("index.html");
                }else{
                    pecaPojo.setP_NOME(request.getParameter("nome"));
                    pecaPojo.setP_PRECO(Double.parseDouble(request.getParameter("preco")));
                    pecaPojo.setP_UNIDADE(Integer.parseInt(request.getParameter("unidade")));
                    pecaDao.salvar(pecaPojo);                                     
                    response.sendRedirect("cadastroPeca.jsp");
                }                
            }
            
            if(request.getParameter("id").equals("excluir")){
                if(usuario == null){
                    response.sendRedirect("index.html");
                }else{
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("identificador")));                    
                    pecaDao.excluir(pecaPojo);                                     
                    response.sendRedirect("PecaServlet?id=listar");
                }                
            }
            
            
            if(request.getParameter("id").equals("listar")){
                if(usuario == null){
                    response.sendRedirect("index.html");
                }else{
                    listPecaPojo = (ArrayList<PecaPojo>) pecaDao.listar();            
                    request.setAttribute("listPecaPojo", listPecaPojo);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Peca.jsp");
                    requestDispatcher.forward(request, response);
                }                
            }
            
            if(request.getParameter("id").equals("editar")){
                if(usuario == null){
                    response.sendRedirect("index.html");
                }else{                         
                    //System.out.println(request.getParameter("nome"));
                    //System.out.println(request.getParameter("preco"));
                    //System.out.println(request.getParameter("unidade"));
                    
                    JOptionPane.showMessageDialog(null, request.getParameter("identificador"));
                    JOptionPane.showMessageDialog(null, request.getParameter("nome"));
                    JOptionPane.showMessageDialog(null, request.getParameter("preco"));
                    JOptionPane.showMessageDialog(null, request.getParameter("unidade"));
                                        
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("identificador")));                    
                    pecaPojo.setP_NOME(request.getParameter("nome"));
                    pecaPojo.setP_PRECO(Double.parseDouble(request.getParameter("preco")));
                    pecaPojo.setP_UNIDADE(Integer.parseInt(request.getParameter("unidade")));
                    valor_total = pecaPojo.getP_PRECO() * pecaPojo.getP_UNIDADE();
                    pecaPojo.setP_VALOR_TOTAL(valor_total);                                       
                    pecaDao.editar(pecaPojo);
                    
                    
                    response.sendRedirect("PecaServlet?id=listar");
                }                
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PecaServlet.class.getName()).log(Level.SEVERE, null, ex);                       
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