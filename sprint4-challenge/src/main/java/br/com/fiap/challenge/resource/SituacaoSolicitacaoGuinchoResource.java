package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.SituacaoSolicitacaoGuinchoDao;
import br.com.fiap.challenge.model.SituacaoSolicitacaoGuincho;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/situacao-solicitacao-guincho")
public class SituacaoSolicitacaoGuinchoResource {

    private SituacaoSolicitacaoGuinchoDao situacaoDao;

    public SituacaoSolicitacaoGuinchoResource(Connection connection) {
        this.situacaoDao = new SituacaoSolicitacaoGuinchoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarSituacaoSolicitacaoGuincho(SituacaoSolicitacaoGuincho situacao, @Context UriInfo uri) {
        situacaoDao.cadastrarSituacaoSolicitacaoGuincho(situacao);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(situacao.getIdSituacaoSolicitacao()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSituacaoSolicitacaoGuincho(@PathParam("id") long id, SituacaoSolicitacaoGuincho situacao) {
        situacao.setIdSituacaoSolicitacao(id);
        situacaoDao.atualizarSituacaoSolicitacaoGuincho(situacao);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerSituacaoSolicitacaoGuincho(@PathParam("id") long id) {
        situacaoDao.removerSituacaoSolicitacaoGuincho(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarSituacaoSolicitacaoGuinchoPorId(@PathParam("id") long id) {
        SituacaoSolicitacaoGuincho situacao = situacaoDao.pesquisarSituacaoSolicitacaoGuinchoPorId(id);
        if (situacao != null) {
            return Response.ok(situacao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSituacoesSolicitacaoGuincho() {
        List<SituacaoSolicitacaoGuincho> situacoes = situacaoDao.listarSituacoesSolicitacaoGuincho();
        return Response.ok(situacoes).build();
    }
}
