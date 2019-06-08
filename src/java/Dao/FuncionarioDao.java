package Dao;
import ConnectionFactory.ConnectionFactory;
import Pojo.FuncionarioPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class FuncionarioDao {
    ConnectionFactory connect = new ConnectionFactory();      
    String sql = "";
         
    // INSERT
    public void salvar (FuncionarioPojo funcionarioPojo){
        sql = "INSERT INTO Funcionario(F_NOME, F_CPF, F_ESTADO) VALUES(?, ?, ?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, funcionarioPojo.getF_NOME());
            pst.setString(2, funcionarioPojo.getF_CPF());
            pst.setBoolean(3, funcionarioPojo.isF_ESTADO());
            pst.execute();
            connect.disconect();
  
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar: " + ex.getMessage());
        }                        
    }      
    
    // SELECT
    public ArrayList<FuncionarioPojo> listar() throws SQLException{
        sql = "SELECT * FROM Funcionario WHERE F_ESTADO != true;";            
        ArrayList<FuncionarioPojo> listFuncionarioPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);                  
               while (connect.rst.next()){                
                    FuncionarioPojo funcionarioPojo = new FuncionarioPojo();
                    funcionarioPojo.setF_ID(Integer.parseInt(connect.rst.getString("F_ID")));             
                    funcionarioPojo.setF_NOME(connect.rst.getString("F_NOME"));             
                    funcionarioPojo.setF_CPF(connect.rst.getString("F_CPF"));
                    listFuncionarioPojo.add(funcionarioPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar os funcionários!");
        }                    
        return listFuncionarioPojo;       
    }
    
    // DELETE
    public void excluir(FuncionarioPojo funcionarioPojo){                
        sql = "UPDATE Funcionario set F_ESTADO=? WHERE F_ID=? ;";
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);            
            pst.setBoolean(1, true);
            pst.setInt(2, funcionarioPojo.getF_ID());
            pst.execute();
            connect.disconect();         
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário!");
        }        
    }
    
    // UPDATE
    public void editar(FuncionarioPojo funcionarioPojo){        
        sql = "UPDATE Funcionario SET F_NOME=?, F_CPF=? WHERE F_ID=?;";
        try {
            connect.connection();                             
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, funcionarioPojo.getF_NOME());
            pst.setString(2, funcionarioPojo.getF_CPF());
            pst.setInt(3, funcionarioPojo.getF_ID());            
            pst.execute();
            connect.disconect();                                
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar funcionario!");
        }       
    }
}