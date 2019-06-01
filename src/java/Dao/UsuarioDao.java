/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionFactory.ConnectionFactory;
import Pojo.FuncionarioPojo;
import Pojo.PecaPojo;
import Pojo.UsuarioPojo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Davi
 */
public class UsuarioDao {
    ConnectionFactory connect = new ConnectionFactory(); 
    String sql;
    UsuarioPojo usuarioPojo = new UsuarioPojo();
    int f_id = -1;
     
    public boolean login (UsuarioPojo usuarioPojo){
        //sql = "SELECT * FROM Funcionario f, Usuario u WHERE F_ID = '"+funcionarioPojo.getF_ID()+"';";
        sql = "select f.F_ID \n" +
              "from Funcionario as f, Usuario as U\n" +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID";
      
        connect.connection();
        int count = 0;
        
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
}
