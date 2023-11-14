package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.TipoVeiculoDao;
import br.com.fiap.challenge.model.TipoVeiculo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/tipo-veiculo")
public class TipoVeiculoResource {

    private TipoVeiculoDao tipoVeiculoDao;

    public TipoVeiculoResource(Connection connection) {
        this.tipoVeiculoDao = new TipoVeiculoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarTipoVeiculo(TipoVeiculo tipoVeiculo, @Context UriInfo uri) {
        tipoVeiculoDao.cadastrarTipoVeiculo(tipoVeiculo);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(tipoVeiculo.getIdTipoVeiculo()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarTipoVeiculo(@PathParam("id") long id, TipoVeiculo tipoVeiculo) {
        tipoVeiculo.setIdTipoVeiculo(id);
        tipoVeiculoDao.atualizarTipoVeiculo(tipoVeiculo);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerTipoVeiculo(@PathParam("id") long id) {
        tipoVeiculoDao.removerTipoVeiculo(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarTipoVeiculoPorId(@PathParam("id") long id) {
        TipoVeiculo tipoVeiculo = tipoVeiculoDao.pesquisarTipoVeiculoPorId(id);
        if (tipoVeiculo != null) {
            return Response.ok(tipoVeiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTiposVeiculo() {
        List<TipoVeiculo> tiposVeiculo = tipoVeiculoDao.listarTiposVeiculo();
        return Response.ok(tiposVeiculo).build();
    }
}
