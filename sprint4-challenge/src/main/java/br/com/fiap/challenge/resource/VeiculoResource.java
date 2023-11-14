package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.VeiculoDao;
import br.com.fiap.challenge.model.Veiculo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculo")
public class VeiculoResource {

    private VeiculoDao veiculoDao;

    public VeiculoResource(Connection connection) {
        this.veiculoDao = new VeiculoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarVeiculo(Veiculo veiculo, @Context UriInfo uri) {
        veiculoDao.cadastrarVeiculo(veiculo);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(veiculo.getIdVeiculo()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("id") long id, Veiculo veiculo) {
        veiculo.setIdVeiculo(id);
        veiculoDao.atualizarVeiculo(veiculo);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerVeiculo(@PathParam("id") long id) {
        veiculoDao.removerVeiculo(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarVeiculoPorId(@PathParam("id") long id) {
        Veiculo veiculo = veiculoDao.pesquisarVeiculoPorId(id);
        if (veiculo != null) {
            return Response.ok(veiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        List<Veiculo> veiculos = veiculoDao.listarVeiculos();
        return Response.ok(veiculos).build();
    }

    @POST
    @Path("/calcularPesoTotal")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response calcularPesoTotalVeiculo(@FormParam("idVeiculo") long idVeiculo) {
        double pesoTotal = veiculoDao.calcularPesoTotalVeiculo(idVeiculo);
        return Response.ok(pesoTotal).build();
    }

    @POST
    @Path("/calcularAlturaTotal")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response calcularAlturaTotalVeiculo(@FormParam("idVeiculo") long idVeiculo) {
        double alturaTotal = veiculoDao.calcularAlturaTotalVeiculo(idVeiculo);
        return Response.ok(alturaTotal).build();
    }

    @POST
    @Path("/calcularComprimentoTotal")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response calcularComprimentoTotalVeiculo(@FormParam("idVeiculo") long idVeiculo) {
        double comprimentoTotal = veiculoDao.calcularComprimentoTotalVeiculo(idVeiculo);
        return Response.ok(comprimentoTotal).build();
    }
}
