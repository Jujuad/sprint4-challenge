package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.PrestadorServico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestadorServicoDao {

    private Connection connection;

    public PrestadorServicoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarPrestadorServico(PrestadorServico prestadorServico) {
        String sql = "INSERT INTO prestador_servico (cpf, nome, servico_oferecido) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, prestadorServico.getCpfPrestadorServico());
            stmt.setString(2, prestadorServico.getNomePrestadorServico());
            stmt.setString(3, prestadorServico.getServicoOferecidoPrestador());

            stmt.execute();

            System.out.println("Prestador de Serviço cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Prestador de Serviço: " + e.getMessage());
        }
    }

    public void atualizarPrestadorServico(PrestadorServico prestadorServico) {
        String sql = "UPDATE prestador_servico SET cpf = ?, nome = ?, servico_oferecido = ? WHERE id_prestador_servico = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, prestadorServico.getCpfPrestadorServico());
            stmt.setString(2, prestadorServico.getNomePrestadorServico());
            stmt.setString(3, prestadorServico.getServicoOferecidoPrestador());
            stmt.setLong(4, prestadorServico.getIdPrestadorServico());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Prestador de Serviço atualizado com sucesso!");
            } else {
                System.out.println("Nenhum Prestador de Serviço encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Prestador de Serviço: " + e.getMessage());
        }
    }

    public void removerPrestadorServico(long id) {
        String sql = "DELETE FROM prestador_servico WHERE id_prestador_servico = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Prestador de Serviço removido com sucesso!");
            } else {
                System.out.println("Nenhum Prestador de Serviço encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover Prestador de Serviço: " + e.getMessage());
        }
    }

    public PrestadorServico pesquisarPrestadorServicoPorId(long id) {
        String sql = "SELECT * FROM prestador_servico WHERE id_prestador_servico = ?";
        PrestadorServico prestadorServico = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                prestadorServico = criarPrestadorServico(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar Prestador de Serviço por ID: " + e.getMessage());
        }

        return prestadorServico;
    }

    public List<PrestadorServico> listarPrestadoresServico() {
        String sql = "SELECT * FROM prestador_servico";
        List<PrestadorServico> prestadoresServico = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                PrestadorServico prestadorServico = criarPrestadorServico(resultSet);
                prestadoresServico.add(prestadorServico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Prestadores de Serviço: " + e.getMessage());
        }

        return prestadoresServico;
    }

    private PrestadorServico criarPrestadorServico(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_prestador_servico");
        long cpf = resultSet.getLong("cpf");
        String nome = resultSet.getString("nome");
        String servicoOferecido = resultSet.getString("servico_oferecido");

        return new PrestadorServico(id, cpf, nome, servicoOferecido);
    }
}
