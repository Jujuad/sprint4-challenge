package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.SituacaoSolicitacaoGuincho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SituacaoSolicitacaoGuinchoDao {

    private Connection connection;

    public SituacaoSolicitacaoGuinchoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarSituacaoSolicitacaoGuincho(SituacaoSolicitacaoGuincho situacao) {
        String sql = "INSERT INTO situacao_solicitacao_guincho (id_solicitacao_guincho, id_guincho, id_prestador_servico) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, situacao.getIdSolicitacaoGuincho());
            stmt.setLong(2, situacao.getIdGuincho());
            stmt.setLong(3, situacao.getIdPrestadorServico());

            stmt.execute();

            System.out.println("Situação da Solicitação de Guincho cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Situação da Solicitação de Guincho: " + e.getMessage());
        }
    }

    public void atualizarSituacaoSolicitacaoGuincho(SituacaoSolicitacaoGuincho situacao) {
        String sql = "UPDATE situacao_solicitacao_guincho SET id_solicitacao_guincho = ?, id_guincho = ?, id_prestador_servico = ? WHERE id_situacao_solicitacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, situacao.getIdSolicitacaoGuincho());
            stmt.setLong(2, situacao.getIdGuincho());
            stmt.setLong(3, situacao.getIdPrestadorServico());
            stmt.setLong(4, situacao.getIdSituacaoSolicitacao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Situação da Solicitação de Guincho atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma Situação da Solicitação de Guincho encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Situação da Solicitação de Guincho: " + e.getMessage());
        }
    }

    public void removerSituacaoSolicitacaoGuincho(long id) {
        String sql = "DELETE FROM situacao_solicitacao_guincho WHERE id_situacao_solicitacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Situação da Solicitação de Guincho removida com sucesso!");
            } else {
                System.out.println("Nenhuma Situação da Solicitação de Guincho encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover Situação da Solicitação de Guincho: " + e.getMessage());
        }
    }

    public SituacaoSolicitacaoGuincho pesquisarSituacaoSolicitacaoGuinchoPorId(long id) {
        String sql = "SELECT * FROM situacao_solicitacao_guincho WHERE id_situacao_solicitacao = ?";
        SituacaoSolicitacaoGuincho situacao = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                situacao = criarSituacaoSolicitacaoGuincho(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar Situação da Solicitação de Guincho por ID: " + e.getMessage());
        }

        return situacao;
    }

    public List<SituacaoSolicitacaoGuincho> listarSituacoesSolicitacaoGuincho() {
        String sql = "SELECT * FROM situacao_solicitacao_guincho";
        List<SituacaoSolicitacaoGuincho> situacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                SituacaoSolicitacaoGuincho situacao = criarSituacaoSolicitacaoGuincho(resultSet);
                situacoes.add(situacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Situações da Solicitação de Guincho: " + e.getMessage());
        }

        return situacoes;
    }

    private SituacaoSolicitacaoGuincho criarSituacaoSolicitacaoGuincho(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_situacao_solicitacao");
        long idSolicitacaoGuincho = resultSet.getLong("id_solicitacao_guincho");
        long idGuincho = resultSet.getLong("id_guincho");
        long idPrestadorServico = resultSet.getLong("id_prestador_servico");

        return new SituacaoSolicitacaoGuincho(id, idSolicitacaoGuincho, idGuincho, idPrestadorServico);
    }
}
