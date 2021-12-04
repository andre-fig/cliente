package WS;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import DAO.*;
import java.util.List;
import javax.ws.rs.PathParam;
import com.google.gson.Gson;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("")
public class ClienteWS {
    
    
    @Context
    private UriInfo context;
    
    public ClienteWS() {
    }
    
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson(){
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("cliente")
    public String getCliente(){
        ClienteDao dao = new ClienteDao();
        List <Cliente> listaClientes = dao.getLista();
        Gson gson = new Gson();
        return gson.toJson(listaClientes);
    }
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("cliente/{codigo}")
    public String getCliente(@PathParam("codigo") int codigo){
        ClienteDao dao = new ClienteDao();
        Cliente cliente = dao.consulta(codigo);
        
        if (cliente != null){
            Gson gson = new Gson();
            return gson.toJson(cliente);
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("cliente")
    public Response addCliente (String content){
        Gson gson = new Gson();
        Cliente cliente = (Cliente) gson.fromJson (content, Cliente.class);
        ClienteDao dao = new ClienteDao();
        
        // tenta inserir um novo cliente
        
        try{
            dao.adiciona(cliente);
        } catch (RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("cliente")
    public Response setCliente(String content){
        Gson gson = new Gson();
        Cliente cliente = (Cliente) gson.fromJson (content, Cliente.class);
        ClienteDao dao = new ClienteDao();
        
        try{
            // testa se conseguiu atualizar o cliente
            if (dao.atualiza(cliente))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        catch (RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @DELETE
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("cliente/{codigo}")
    public Response delCliente(@PathParam("codigo") int codigo){
        ClienteDao dao = new ClienteDao();
        
        // resta se encontrou e removeu o cliente
        if (dao.remove(codigo)){
            return Response.status(Response.Status.OK).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
}
        