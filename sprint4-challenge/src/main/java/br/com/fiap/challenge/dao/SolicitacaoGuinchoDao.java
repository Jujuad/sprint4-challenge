package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.SolicitacaoGuincho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoGuinchoDao {

    private Connection connection;

    public SolicitacaoGuinchoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarSolicitacaoGuincho(SolicitacaoGuincho solicitacao) {
        String sql = "INSERT INTO solicitacao_guincho (descricao, id_localizacao, id_usuario, id_veiculo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, solicitacao.getDescricaoSolicitacaoGuincho());
            stmt.setLong(2, solicitacao.getIdLocalizacao());
            stmt.setLong(3, solicitacao.getIdUsuario());
            stmt.setLong(4, solicitacao.getIdVeiculo());

            stmt.execute();

            System.out.println("Solicitação de Guincho cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Solicitação de Guincho: " + e.getMessage());
        }
    }

    public void atualizarSolicitacaoGuincho(SolicitacaoGuincho solicitacao) {
        String sql = "UPDATE solicitacao_guincho SET descricao = ?, id_localizacao = ?, id_usuario = ?, id_veiculo = ? WHERE id_solicitacao_guincho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, solicitacao.getDescricaoSolicitacaoGuincho());
            stmt.setLong(2, solicitacao.getIdLocalizacao());
            stmt.setLong(3, solicitacao.getIdUsuario());
            stmt.setLong(4, solicitacao.getIdVeiculo());
            stmt.setLong(5, solicitacao.getIdSolicitacaoGuincho());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Solicitação de Guincho atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma Solicitação de Guincho encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Solicitação de Guincho: " + e.getMessage());
        }
    }

    public void removerSolicitacaoGuincho(long id) {
        String sql = "DELETE FROM solicitacao_guincho WHERE id_solicitacao_guincho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Solicitação de Guincho removida com sucesso!");
            } else {
                System.out.println("Nenhuma Solicitação de Guincho encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover Solicitação de Guincho: " + e.getMessage());
        }
    }

    public SolicitacaoGuincho pesquisarSolicitacaoGuinchoPorId(long id) {
        String sql = "SELECT * FROM solicitacao_guincho WHERE id_solicitacao_guincho = ?";
        SolicitacaoGuincho solicitacao = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                solicitacao = criarSolicitacaoGuincho(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar Solicitação de Guincho por ID: " + e.getMessage());
        }

        return solicitacao;
    }

    public List<SolicitacaoGuincho> listarSolicitacoesGuincho() {
        String sql = "SELECT * FROM solicitacao_guincho";
        List<SolicitacaoGuincho> solicitacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                SolicitacaoGuincho solicitacao = criarSolicitacaoGuincho(resultSet);
                solicitacoes.add(solicitacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Solicitações de Guincho: " + e.getMessage());
        }

        return solicitacoes;
    }

    private SolicitacaoGuincho criarSolicitacaoGuincho(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_solicitacao_guincho");
        String descricao = resultSet.getString("descricao");
        long idLocalizacao = resultSet.getLong("id_localizacao");
        long idUsuario = resultSet.getLong("id_usuario");
        long idVeiculo = resultSet.getLong("id_veiculo");

        return new SolicitacaoGuincho(id, descricao, idLocalizacao, idUsuario, idVeiculo);
    }
    
    public void calcularMelhorRota() {
		// m�todo a ser implementado utilizando alguma API de gps - atualizacoes futuras;
	}
}
