package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Seguro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguroDao {

    private Connection connection;

    public SeguroDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarSeguro(Seguro seguro) {
        String sql = "INSERT INTO seguro (data_inicio, data_termino, valor_cobertura, id_usuario, id_veiculo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(seguro.getDataInicioSeguro().getTime()));
            stmt.setDate(2, new java.sql.Date(seguro.getDataTerminoSeguro().getTime()));
            stmt.setDouble(3, seguro.getValorCoberturaSeguro());
            stmt.setLong(4, seguro.getIdUsuario());
            stmt.setLong(5, seguro.getIdVeiculo());

            stmt.execute();

            System.out.println("Seguro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Seguro: " + e.getMessage());
        }
    }

    public void atualizarSeguro(Seguro seguro) {
        String sql = "UPDATE seguro SET data_inicio = ?, data_termino = ?, valor_cobertura = ?, id_usuario = ?, id_veiculo = ? WHERE id_seguro = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(seguro.getDataInicioSeguro().getTime()));
            stmt.setDate(2, new java.sql.Date(seguro.getDataTerminoSeguro().getTime()));
            stmt.setDouble(3, seguro.getValorCoberturaSeguro());
            stmt.setLong(4, seguro.getIdUsuario());
            stmt.setLong(5, seguro.getIdVeiculo());
            stmt.setLong(6, seguro.getIdSeguro());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Seguro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum Seguro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Seguro: " + e.getMessage());
        }
    }

    public void removerSeguro(long id) {
        String sql = "DELETE FROM seguro WHERE id_seguro = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Seguro removido com sucesso!");
            } else {
                System.out.println("Nenhum Seguro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover Seguro: " + e.getMessage());
        }
    }

    public Seguro pesquisarSeguroPorId(long id) {
        String sql = "SELECT * FROM seguro WHERE id_seguro = ?";
        Seguro seguro = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                seguro = criarSeguro(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar Seguro por ID: " + e.getMessage());
        }

        return seguro;
    }

    public List<Seguro> listarSeguros() {
        String sql = "SELECT * FROM seguro";
        List<Seguro> seguros = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Seguro seguro = criarSeguro(resultSet);
                seguros.add(seguro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Seguros: " + e.getMessage());
        }

        return seguros;
    }

    private Seguro criarSeguro(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_seguro");
        java.util.Date dataInicio = resultSet.getDate("data_inicio");
        java.util.Date dataTermino = resultSet.getDate("data_termino");
        double valorCobertura = resultSet.getDouble("valor_cobertura");
        long idUsuario = resultSet.getLong("id_usuario");
        long idVeiculo = resultSet.getLong("id_veiculo");

        return new Seguro(id, dataInicio, dataTermino, valorCobertura, idUsuario, idVeiculo);
    }
}
