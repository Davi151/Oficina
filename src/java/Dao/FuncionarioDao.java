/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionFactory.ConnectionFactory;
import Pojo.FuncionarioPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Davi
 */
public class FuncionarioDao {
    ConnectionFactory connect = new ConnectionFactory();      
    String sql = "";
    
     // Método que insere uma aposta no banco de dados.
    public void cadastrar (FuncionarioPojo funcionarioPojo){
        sql = "INSERT INTO Funcionario(F_NOME, F_CPF ) VALUES(?, ?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, funcionarioPojo.getF_NOME());
            pst.setString(2, funcionarioPojo.getF_CPF()); 
            pst.execute();
            connect.disconect();
            //JOptionPane.showMessageDialog(null, "Cadastro salvo com êxito!");
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar: " + ex.getMessage());
        }                        
    }
    
}
