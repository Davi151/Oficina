/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionFactory.ConnectionFactory;
import Pojo.FuncionarioPojo;
import Pojo.UsuarioPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Davi
 */
public class UsuarioDao {
    ConnectionFactory connect = new ConnectionFactory(); 
    String sql;
    UsuarioPojo usuarioPojo = new UsuarioPojo();
    
    
    public boolean login (UsuarioPojo usuarioPojo){
        //sql = "SELECT * FROM Funcionario f, Usuario u WHERE F_ID = '"+funcionarioPojo.getF_ID()+"';";
        sql = "select f.F_ID \n" +
              "from Funcionario as f, Usuario as U\n" +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID";
      
        connect.connection();
        
        try {
            connect.executaSql(sql);
             
               while (connect.rst.next()){                
                   return true;
                }                               
            connect.disconect();    
            
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return  false;
    }
    
     public void salvar (UsuarioPojo UsuarioPojo){
        sql = "INSERT INTO Usuario(U_lOGIN, U_SENHA,F_ID, U_ESTADO) VALUES(?,?,?,?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, UsuarioPojo.getU_LOGIN());
            pst.setString(2, UsuarioPojo.getU_SENHA());
            pst.setInt(3, UsuarioPojo.getF_FID());
            pst.setBoolean(4, UsuarioPojo.isU_ESTADO());
            pst.execute();
            connect.disconect();
  
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar: " + ex.getMessage());
        }                        
    }
}
