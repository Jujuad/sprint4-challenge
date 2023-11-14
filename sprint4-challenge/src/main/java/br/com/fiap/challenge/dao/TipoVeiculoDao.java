package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.TipoVeiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoVeiculoDao {

    private Connection connection;

    public TipoVeiculoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarTipoVeiculo(TipoVeiculo tipoVeiculo) {
        String sql = "INSERT INTO tipo_veiculo (nome) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoVeiculo.getNomeTipoVeiculo());

            stmt.execute();

            System.out.println("Tipo de Veículo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Tipo de Veículo: " + e.getMessage());
        }
    }

    public void atualizarTipoVeiculo(TipoVeiculo tipoVeiculo) {
        String sql = "UPDATE tipo_veiculo SET nome = ? WHERE id_tipo_veiculo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoVeiculo.getNomeTipoVeiculo());
            stmt.setLong(2, tipoVeiculo.getIdTipoVeiculo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tipo de Veículo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum Tipo de Veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Tipo de Veículo: " + e.getMessage());
        }
    }

    public void removerTipoVeiculo(long id) {
        String sql = "DELETE FROM tipo_veiculo WHERE id_tipo_veiculo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tipo de Veículo removido com sucesso!");
            } else {
                System.out.println("Nenhum Tipo de Veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover Tipo de Veículo: " + e.getMessage());
        }
    }

    public TipoVeiculo pesquisarTipoVeiculoPorId(long id) {
        String sql = "SELECT * FROM tipo_veiculo WHERE id_tipo_veiculo = ?";
        TipoVeiculo tipoVeiculo = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                tipoVeiculo = criarTipoVeiculo(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar Tipo de Veículo por ID: " + e.getMessage());
        }

        return tipoVeiculo;
    }

    public List<TipoVeiculo> listarTiposVeiculo() {
        String sql = "SELECT * FROM tipo_veiculo";
        List<TipoVeiculo> tiposVeiculo = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                TipoVeiculo tipoVeiculo = criarTipoVeiculo(resultSet);
                tiposVeiculo.add(tipoVeiculo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Tipos de Veículo: " + e.getMessage());
        }

        return tiposVeiculo;
    }

    private TipoVeiculo criarTipoVeiculo(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_tipo_veiculo");
        String nome = resultSet.getString("nome");

        return new TipoVeiculo(id, nome);
    }
}
