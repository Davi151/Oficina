package Dao;


import ConnectionFactory.ConnectionFactory;
import Pojo.UsuarioPojo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDao {   
    ConnectionFactory connect = new ConnectionFactory();
           
    String sql = "";
    UsuarioPojo usuarioPojo = new UsuarioPojo();   
     
    public boolean login (UsuarioPojo usuarioPojo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(usuarioPojo.getU_SENHA().getBytes("UTF-8"));  
            StringBuilder sb = new StringBuilder();
            
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            
            String senhaHex = sb.toString();
        
            
        
        /*sql = "select f.F_ID \n" +
              "from Funcionario as f, Usuario as U " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID and U.u_estado = false";
        */
        
        sql = "select f.F_ID, u.u_LOGIN, u.u_SENHA \n" +
              "from Funcionario as f, Usuario as U " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and f.F_ID = u.F_ID and U.u_estado = false";
        
        connect.connection();        
        
        try {
            connect.executaSql(sql);             
            if (connect.rst.next()){                
                String senhaHash = connect.rst.getString("u_senha").trim();            
                String loginBanco = connect.rst.getString("u_login").trim();                                                               
                
                if(loginBanco.equals(usuarioPojo.getU_LOGIN()) &&
                   senhaHash.equals(senhaHex)){
                   return true;
                }else{
                    return false;
                }                                                  
            }                               
            connect.disconect();    
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return  false;
    }
      
     
    public int getU_ID (UsuarioPojo usuarioPojo) throws NoSuchAlgorithmException, UnsupportedEncodingException{    
        
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(usuarioPojo.getU_SENHA().getBytes("UTF-8"));  
            StringBuilder sb = new StringBuilder();
            
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            
            String senhaHex = sb.toString();
        
        sql = "select u.U_ID \n" +
              "from Funcionario as f, Usuario as u " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+senhaHex+"' and f.F_ID = u.F_ID";
      
        connect.connection();        
        
        try {
            connect.executaSql(sql);             
            if (connect.rst.next()){                
                return connect.rst.getInt("U_ID");
            }                               
            connect.disconect();    
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
        
        return -1;
    }
    
    
    
    
     public void salvar (UsuarioPojo UsuarioPojo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        sql = "INSERT INTO Usuario(U_lOGIN, U_SENHA,F_ID, U_ESTADO) VALUES(?,?,?,?);";
        
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(UsuarioPojo.getU_SENHA().getBytes("UTF-8"));  
            StringBuilder sb = new StringBuilder();
            
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            
            String senhaHex = sb.toString();
        
        
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, UsuarioPojo.getU_LOGIN());
            pst.setString(2, senhaHex );
            pst.setInt(3, UsuarioPojo.getF_FID());
            pst.setBoolean(4, UsuarioPojo.isU_ESTADO());
            pst.execute();
            connect.disconect();
  
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar: " + ex.getMessage());
        }                        
    }
    
}