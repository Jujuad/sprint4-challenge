package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (cpf, nome, email, senha, dt_nascimento) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, usuario.getCpfUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmailUsuario());
            stmt.setString(4, usuario.getSenhaUsuario());
            stmt.setDate(5, new java.sql.Date(usuario.getDtNascUsuario().getTime()));

            stmt.execute();

            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET cpf = ?, nome = ?, email = ?, senha = ?, dt_nascimento = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, usuario.getCpfUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmailUsuario());
            stmt.setString(4, usuario.getSenhaUsuario());
            stmt.setDate(5, new java.sql.Date(usuario.getDtNascUsuario().getTime()));
            stmt.setLong(6, usuario.getIdUsuario());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void removerUsuario(long id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
        }
    }

    public Usuario pesquisarUsuarioPorId(long id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                usuario = criarUsuario(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar usuário por ID: " + e.getMessage());
        }

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = criarUsuario(resultSet);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    public boolean realizarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        boolean loginSucesso = false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet resultSet = stmt.executeQuery();

            loginSucesso = resultSet.next();
        } catch (SQLException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
        }

        return loginSucesso;
    }

    private Usuario criarUsuario(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_usuario");
        long cpf = resultSet.getLong("cpf");
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String senha = resultSet.getString("senha");
        java.util.Date dtNascimento = resultSet.getDate("dt_nascimento");

        return new Usuario(id, cpf, nome, email, senha, dtNascimento);
    }
}
