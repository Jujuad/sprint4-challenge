package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.PrestadorServicoDao;
import br.com.fiap.challenge.model.PrestadorServico;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/prestador-servico")
public class PrestadorServicoResource {

    private PrestadorServicoDao prestadorServicoDao;

    public PrestadorServicoResource(Connection connection) {
        this.prestadorServicoDao = new PrestadorServicoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPrestadorServico(PrestadorServico prestadorServico, @Context UriInfo uri) {
        prestadorServicoDao.cadastrarPrestadorServico(prestadorServico);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(prestadorServico.getIdPrestadorServico()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPrestadorServico(@PathParam("id") long id, PrestadorServico prestadorServico) {
        prestadorServico.setIdPrestadorServico(id);
        prestadorServicoDao.atualizarPrestadorServico(prestadorServico);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerPrestadorServico(@PathParam("id") long id) {
        prestadorServicoDao.removerPrestadorServico(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarPrestadorServicoPorId(@PathParam("id") long id) {
        PrestadorServico prestadorServico = prestadorServicoDao.pesquisarPrestadorServicoPorId(id);
        if (prestadorServico != null) {
            return Response.ok(prestadorServico).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPrestadoresServico() {
        List<PrestadorServico> prestadoresServico = prestadorServicoDao.listarPrestadoresServico();
        return Response.ok(prestadoresServico).build();
    }
}
