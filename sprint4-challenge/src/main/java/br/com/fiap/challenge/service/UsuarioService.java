package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.UsuarioDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.Usuario;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        usuarioDao = new UsuarioDao(conn);
    }

    public void cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(usuario);
        usuarioDao.cadastrarUsuario(usuario);
    }

    public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(usuario);
        usuarioDao.atualizarUsuario(usuario);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        usuarioDao.removerUsuario(id);
    }

    public List<Usuario> listar() throws ClassNotFoundException, SQLException {
        return usuarioDao.listarUsuarios();
    }

    public Usuario pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return usuarioDao.pesquisarUsuarioPorId(id);
    }

    public boolean realizarLogin(String email, String senha) throws ClassNotFoundException, SQLException {
        return usuarioDao.realizarLogin(email, senha);
    }

    private void validar(Usuario usuario) throws BadInfoException {
        if (usuario.getCpfUsuario() <= 0) {
            throw new BadInfoException("O CPF do usuário é obrigatório e deve ser maior que zero.");
        }

        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isEmpty()) {
            throw new BadInfoException("O nome do usuário é obrigatório. Preencha o campo.");
        }

        if (usuario.getEmailUsuario() == null || usuario.getEmailUsuario().isEmpty()) {
            throw new BadInfoException("O email do usuário é obrigatório. Preencha o campo.");
        }

        if (usuario.getSenhaUsuario() == null || usuario.getSenhaUsuario().isEmpty()) {
            throw new BadInfoException("A senha do usuário é obrigatória. Preencha o campo.");
        }

        if (usuario.getDtNascUsuario() == null) {
            throw new BadInfoException("A data de nascimento do usuário é obrigatória. Preencha o campo.");
        }
    }
}
