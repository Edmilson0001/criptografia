package criptografia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Mudança importante: PreparedStatement
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class BancoDeDados {
    private Connection connection = null;

    public void conectar() {
        String servidor = "jdbc:mysql://127.0.0.1:3306/banco";
        String usuario = "root";
        String senha = "Aluno";
        
        try {
            this.connection = DriverManager.getConnection(servidor, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public boolean estaConectado() {
        return this.connection != null;
    }

    public void inserirdado(String usuario, String Senha) {
    	String sql = "insert into login(usuario,senha)values (?,?)";
    	try(PreparedStatement pst = connection.prepareStatement(sql)) {
    		pst.setString(1, usuario);
    		pst.setString(2, Senha);
    		pst.executeUpdate();
    	}catch (SQLException e) {
    		System.out.println("Erro");
    	}
    }
     public boolean verificarusuario(String usuario) {
	        String sql = "Select * from login where usuario = ?";
	        try(PreparedStatement pst = connection.prepareStatement(sql)) {
	        	pst.setString(1, usuario);
	        	ResultSet rs = pst.executeQuery();
	        	while(rs.next()) {
	        		return true;
	        	}
	        }catch (SQLException e) {
	        	System.out.println("Erro");
	        }	return false;
	        }
     
     public boolean verificarsenha(String usuario,String senha) {
     	String sql = "select senha from login where usuario = ?";
     	try(PreparedStatement pst = connection.prepareStatement(sql)){
     		pst.setString(1, usuario);
     		ResultSet rs = pst.executeQuery();
     		while(rs.next()) {
     			String senhabanco = rs.getString("senha");
     			if(BCrypt.checkpw(senha, senhabanco)) {	
     				return true;
     			}
     		}
     	}catch(SQLException e) {
     		System.out.println("Erro");
     	}
     	return false;
     }

		        
	        
	        
	        
	        

    public void desconectar() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao desconectar: " + e.getMessage());
        }
    }
}
