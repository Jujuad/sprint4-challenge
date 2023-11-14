package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dao.UsuarioDao;
import br.com.fiap.challenge.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/usuario")
public class UsuarioResource {

    private UsuarioDao usuarioDao;

    public UsuarioResource(Connection connection) {
        this.usuarioDao = new UsuarioDao(connection);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuario, @Context UriInfo uri) {
        usuarioDao.cadastrarUsuario(usuario);

        UriBuilder url = uri.getAbsolutePathBuilder();
        url.path(String.valueOf(usuario.getIdUsuario()));

        return Response.created(url.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") long id, Usuario usuario) {
        usuario.setIdUsuario(id);
        usuarioDao.atualizarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerUsuario(@PathParam("id") long id) {
        usuarioDao.removerUsuario(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarUsuarioPorId(@PathParam("id") long id) {
        Usuario usuario = usuarioDao.pesquisarUsuarioPorId(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        return Response.ok(usuarios).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response realizarLogin(@FormParam("email") String email, @FormParam("senha") String senha) {
        boolean loginSucesso = usuarioDao.realizarLogin(email, senha);

        if (loginSucesso) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
