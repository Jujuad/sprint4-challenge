package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.SeguroDao;
import br.com.fiap.challenge.model.Seguro;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/seguro")
public class SeguroResource {

    private SeguroDao seguroDao;

    public SeguroResource(Connection connection) {
        this.seguroDao = new SeguroDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarSeguro(Seguro seguro, @Context UriInfo uri) {
        seguroDao.cadastrarSeguro(seguro);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(seguro.getIdSeguro()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSeguro(@PathParam("id") long id, Seguro seguro) {
        seguro.setIdSeguro(id);
        seguroDao.atualizarSeguro(seguro);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerSeguro(@PathParam("id") long id) {
        seguroDao.removerSeguro(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarSeguroPorId(@PathParam("id") long id) {
        Seguro seguro = seguroDao.pesquisarSeguroPorId(id);
        if (seguro != null) {
            return Response.ok(seguro).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSeguros() {
        List<Seguro> seguros = seguroDao.listarSeguros();
        return Response.ok(seguros).build();
    }
}
