package DAO;
import java.sql.*;

public class ConnectionFactory {
    
    public Connection getConnection(){
        try{
                Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/sistema_academico","app","app");
                return conexao;
               
            } catch(SQLException ex){
        
            System.out.println("Não foi possível conectar ao banco de dados: "+ex);     
            }
            return null;
 }
}