package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.GuinchoDao;
import br.com.fiap.challenge.model.Guincho;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/guincho")
public class GuinchoResource {

    private GuinchoDao guinchoDao;

    public GuinchoResource(Connection connection) {
        this.guinchoDao = new GuinchoDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarGuincho(Guincho guincho, @Context UriInfo uri) {
        guinchoDao.cadastrarGuincho(guincho);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(guincho.getIdGuincho()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarGuincho(@PathParam("id") long id, Guincho guincho) {
        guincho.setIdGuincho(id);
        guinchoDao.alterarGuincho(guincho);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerGuincho(@PathParam("id") long id) {
        guinchoDao.removerGuincho(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarGuinchoPorId(@PathParam("id") long id) {
        Guincho guincho = guinchoDao.pesquisarGuinchoPorId(id);
        if (guincho != null) {
            return Response.ok(guincho).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarGuinchos() {
        List<Guincho> guinchos = guinchoDao.listarGuinchos();
        return Response.ok(guinchos).build();
    }

    @GET
    @Path("/calcularTensaoIdeal/{idVeiculo}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calcularTensaoIdeal(@PathParam("idVeiculo") long idVeiculo) {
        // Supondo que você tenha uma classe VeiculoDao para buscar o veículo pelo ID
        // Veiculo veiculo = veiculoDao.pesquisarVeiculoPorId(idVeiculo);

        // Se você tiver a instância do Veiculo, pode chamar o método calcularTensaoIdeal
        // String resultado = guinchoDao.calcularTensaoIdeal(veiculo);

        // Retorna uma resposta de exemplo
        String resultado = "Exemplo de resultado para calcularTensaoIdeal";
        return Response.ok(resultado).build();
    }
}
