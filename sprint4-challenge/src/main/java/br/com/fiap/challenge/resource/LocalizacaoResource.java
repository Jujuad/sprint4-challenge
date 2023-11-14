package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.LocalizacaoDao;
import br.com.fiap.challenge.model.Localizacao;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/localizacao")
public class LocalizacaoResource {

    private LocalizacaoDao localizacaoDao;

    public LocalizacaoResource(Connection connection) {
        this.localizacaoDao = new LocalizacaoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarLocalizacao(Localizacao localizacao, @Context UriInfo uri) {
        localizacaoDao.cadastrarLocalizacao(localizacao);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(localizacao.getIdLocalizacao()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarLocalizacao(@PathParam("id") long id, Localizacao localizacao) {
        localizacao.setIdLocalizacao(id);
        localizacaoDao.alterarLocalizacao(localizacao);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerLocalizacao(@PathParam("id") long id) {
        localizacaoDao.removerLocalizacao(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarLocalizacaoPorId(@PathParam("id") long id) {
        Localizacao localizacao = localizacaoDao.pesquisarLocalizacaoPorId(id);
        if (localizacao != null) {
            return Response.ok(localizacao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLocalizacoes() {
        List<Localizacao> localizacoes = localizacaoDao.listarLocalizacoes();
        return Response.ok(localizacoes).build();
    }
}
