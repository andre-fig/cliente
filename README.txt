SQL responsável por criar a estrutura do banco:

CREATE TABLE cliente( 
	codigo VARCHAR(50) NOT NULL, 
	nome VARCHAR(100) NOT NULL, 
	cnpj VARCHAR(20) NOT NULL,
	datacadastro VARCHAR(20) NOT NULL,
	endereco VARCHAR(100),
	telefone VARCHAR (20),
	PRIMARY KEY (codigo),
	UNIQUE (cnpj)
);



 Trata-se de uma aplicação CRUD (Create, Read, Update, Delete) que conta com a implementação do Web Service RESTful.
 Nesse sentido, o Web Service disponibiliza os recursos de inserir, remover e editar clientes, além de listar clientes ou apresentá-los por código.
 Os métodos que tratam as requisições devolvem as respostas no formato JSON e, além disso,
 as operações implementadas no Web Service atendem seguintes scripts implementados utilizando a API Fetch:


Método @GET: recupera os dados de um determinado cliente a partir de seu código (codigo): 

fetch('/sistemaEmpresa/pessoal/cliente/{CODIGO}'). 
then(resposta => { 
   if (!resposta.ok){ 
       throw Error(resposta.status);  
   } 
   return resposta.json(); 
 }). 
then(jsonReposta => console.log(jsonReposta)).  
catch(erro => console.log("Erro! Código do cliente não encontrado."));



Método @DELETE: remove um determinado cliente a partir de seu código (codigo): 

fetch('/sistemaEmpresa/pessoal/cliente/{CODIGO}',{method: 'DELETE'}). 
then(resposta => { 
   if (!resposta.ok){ 
       throw Error(resposta.status);  
   } 
   return resposta; 
 }). 
then(jsonReposta => console.log("Cliente removido!")).  
catch(erro => console.log("Erro! O código do cliente não foi encontrado.")); 



Método @POST: adiciona um novo cliente a partir de seu código (codigo):

 fetch('/sistemaEmpresa/pessoal/cliente/',  
  {method: 'POST', 
    body: JSON.stringify({ 
        codigo: 1234, 
        nome: 'Cliente teste', 
        cnpj: 01234567891234, 
        datacadastro: 12345678, 
        endereco: 'Rua Teste', 
        telefone: 0123456789 
    }), 
    headers: { 
        "Content-type": "application/json" 
    } 
}). 
then(resposta => { 
   if (!resposta.ok){ 
       throw Error(resposta.status);  
   } 
   return resposta; 
 }). 
then(jsonReposta => console.log("Cliente inserido!")).  
catch(erro => console.log("Erro na inserção."));



Método @PUT: atualiza os dados de um determinado cliente a partir de seu código (codigo):

fetch('/sistemaEmpresa/pessoal/cliente/',  
  {method: 'PUT', 
    body: JSON.stringify({ 
        codigo: 1234, 
        nome: 'Empresa teste', 
        cnpj: 01234567891234, 
        datacadastro: 12345678,
        endereco: 'Rua Teste', 
        telefone: 0123456789 
    }), 
    headers: { 
        "Content-type": "application/json" 
    } 
}). 
then(resposta => { 
   if (!resposta.ok){ 
       throw Error(resposta.status);  
   } 
   return resposta; 
 }). 
then(jsonReposta => console.log("Cliente atualizado.")). 
catch(erro => console.log("Erro na atualização do cliente."));  
