package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.SolicitacaoGuinchoDao;
import br.com.fiap.challenge.model.SolicitacaoGuincho;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/solicitacao-guincho")
public class SolicitacaoGuinchoResource {

    private SolicitacaoGuinchoDao solicitacaoDao;

    public SolicitacaoGuinchoResource(Connection connection) {
        this.solicitacaoDao = new SolicitacaoGuinchoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarSolicitacaoGuincho(SolicitacaoGuincho solicitacao, @Context UriInfo uri) {
        solicitacaoDao.cadastrarSolicitacaoGuincho(solicitacao);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(solicitacao.getIdSolicitacaoGuincho()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSolicitacaoGuincho(@PathParam("id") long id, SolicitacaoGuincho solicitacao) {
        solicitacao.setIdSolicitacaoGuincho(id);
        solicitacaoDao.atualizarSolicitacaoGuincho(solicitacao);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerSolicitacaoGuincho(@PathParam("id") long id) {
        solicitacaoDao.removerSolicitacaoGuincho(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarSolicitacaoGuinchoPorId(@PathParam("id") long id) {
        SolicitacaoGuincho solicitacao = solicitacaoDao.pesquisarSolicitacaoGuinchoPorId(id);
        if (solicitacao != null) {
            return Response.ok(solicitacao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSolicitacoesGuincho() {
        List<SolicitacaoGuincho> solicitacoes = solicitacaoDao.listarSolicitacoesGuincho();
        return Response.ok(solicitacoes).build();
    }
    
    @POST
    @Path("/calcular-melhor-rota")
    public Response calcularMelhorRota() {
        solicitacaoDao.calcularMelhorRota();
        return Response.ok("Melhor rota calculada com sucesso!").build();
    }
}
