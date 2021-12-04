package DAO;
import java.sql.*;
import java.util.ArrayList;


public class ClienteDao {
    private Connection connection; 

    public ClienteDao(){
     this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Cliente cliente){
        try{
            String sql ="INSERT INTO cliente VALUES (?,?,?,?,?,?)";    
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString(1,cliente.getCodigo());
            stmt.setString(2,cliente.getNome());
            stmt.setString(3,cliente.getCnpj());
            stmt.setString(4,cliente.getDatacadastro());
            stmt.setString(5,cliente.getEndereco());
            stmt.setString(6,cliente.getTelefone());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch(SQLException e){
            throw new RuntimeException(e);
            }
    }    
    
    public ArrayList getLista(){
    String sql = "SELECT * from cliente";
    ArrayList <Cliente> lista = new ArrayList(); 

    try{  
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            
        while (rs.next()){
            Cliente cliente = new Cliente();
            
            cliente.setCodigo(rs.getString("codigo"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCnpj(rs.getString("cnpj"));
            cliente.setDatacadastro(rs.getString("datacadastro"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setTelefone(rs.getString("telefone"));
            
            lista.add(cliente);
            
        }
        
            rs.close();
            stmt.close();
            
    } catch (SQLException e){
        throw new RuntimeException(e);
    }
    
    return lista;
    }
    
    public Cliente consulta (int codigo){
        String sql = "SELECT * FROM cliente WHERE codigo = ?";
        Cliente cliente = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1,codigo);
            ResultSet rs = stmt.executeQuery ();
            
            if (rs.next() ){
                cliente = new Cliente();
                
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setDatacadastro(rs.getString("datacadastro"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException (e);
        }
        
        return cliente;
    }
        
    public boolean atualiza (Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, cnpj = ?, datacadastro = ?, endereco = ?, telefone = ? WHERE codigo = ?";
        int clientesAtualizados = 0;
        try {
            // prepared statement para inserção
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            // seta os valores
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getCnpj());
            stmt.setString(3,cliente.getDatacadastro());
            stmt.setString(4,cliente.getEndereco());
            stmt.setString(5,cliente.getTelefone());
            stmt.setString(6,cliente.getCodigo());
            
            // executeUpdate retorna a quantidade de linhas atualizadas
            
            clientesAtualizados = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException (e);
        }
        
        // retorna true se atualizou 1 aluno
        return clientesAtualizados == 1;
    }
    
    public boolean remove (int codigo){
        String sql = "DELETE FROM cliente WHERE codigo = ?";
        int clientesRemovidos = 0;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1,codigo);
            
            // executeUpdate retorna a quantidade de linhas removidas
            clientesRemovidos = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        // retorna true se removeu 1 aluno
        return clientesRemovidos == 1;
    }
}
