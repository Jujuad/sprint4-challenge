package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.EnderecoDao;
import br.com.fiap.challenge.model.Endereco;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/endereco")
public class EnderecoResource {

    private EnderecoDao enderecoDao;

    public EnderecoResource(Connection connection) {
        this.enderecoDao = new EnderecoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarEndereco(Endereco endereco, @Context UriInfo uri) {
        enderecoDao.cadastrarEndereco(endereco);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(endereco.getIdEndereco()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarEndereco(@PathParam("id") long id, Endereco endereco) {
        endereco.setIdEndereco(id);
        enderecoDao.alterarEndereco(endereco);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerEndereco(@PathParam("id") long id) {
        enderecoDao.removerEndereco(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarEnderecoPorId(@PathParam("id") long id) {
        Endereco endereco = enderecoDao.pesquisarEnderecoPorId(id);
        if (endereco != null) {
            return Response.ok(endereco).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEnderecos() {
        List<Endereco> enderecos = enderecoDao.listarEnderecos();
        return Response.ok(enderecos).build();
    }
}
