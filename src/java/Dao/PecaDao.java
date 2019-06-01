/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionFactory.ConnectionFactory;
import Pojo.PecaPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PecaDao {
    ConnectionFactory connect = new ConnectionFactory();      
    String sql = "";
    
     // Método que insere uma aposta no banco de dados.
    public void cadastrar (PecaPojo pecaPojo){
        sql = "INSERT INTO peca(P_NOME, P_PRECO, P_UNIDADE) VALUES(?, ?, ?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, pecaPojo.getNome());
            pst.setDouble(2, pecaPojo.getPreco());
            pst.setInt(3, pecaPojo.getUnidade());            
            pst.execute();
            connect.disconect();
            JOptionPane.showMessageDialog(null, "Cadastro salvo com êxito!");
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar a peça: " + ex.getMessage());
        }                        
    }
    
    public ArrayList<PecaPojo> listar() throws SQLException{
        sql = "SELECT * FROM Peca;";            
        ArrayList<PecaPojo> listPecaPojo = new ArrayList<>();
        connect.connection();
        int count = 0;
        
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    PecaPojo pecaPojo = new PecaPojo();
                    pecaPojo.setPid(Integer.parseInt(connect.rst.getString("P_ID")));             
                    pecaPojo.setNome(connect.rst.getString("P_NOME"));             
                    pecaPojo.setPreco(Double.parseDouble(connect.rst.getString("P_PRECO")));             
                    pecaPojo.setUnidade(Integer.parseInt(connect.rst.getString("P_UNIDADE")));             
                    listPecaPojo.add(pecaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
            
        return listPecaPojo;
       
    }
}