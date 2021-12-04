package DAO;

public class Cliente {
    private String codigo;
    private String nome;
    private String cnpj;
    private String datacadastro;
    private String endereco;
    private String telefone;
    
    public String getCodigo(){
        return codigo;
    }
    public void setCodigo (String codigo){
        this.codigo = codigo;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome (String nome){
        this.nome = nome;
    }    

    
    public String getCnpj(){
        return cnpj;
    }
    public void setCnpj (String cnpj){
        this.cnpj = cnpj;
    }


    public String getDatacadastro(){
        return datacadastro;
    }
    public void setDatacadastro (String datacadastro){
        this.datacadastro = datacadastro;
    }


    public String getEndereco(){
        return endereco;
    }
    public void setEndereco (String endereco){
        this.endereco = endereco;
    }


    public String getTelefone(){
        return telefone;
    }
    public void setTelefone (String telefone){
        this.telefone = telefone;
    }    
}
