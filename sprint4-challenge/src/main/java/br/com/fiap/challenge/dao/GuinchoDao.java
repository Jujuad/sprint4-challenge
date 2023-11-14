package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Guincho;
import br.com.fiap.challenge.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuinchoDao {

    private Connection connection;

    public GuinchoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarGuincho(Guincho guincho) {
        String sql = "INSERT INTO guincho (peso, tipo, disponibilidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, guincho.getPesoGuincho());
            stmt.setString(2, guincho.getTipoGuincho());
            stmt.setInt(3, guincho.getDisponibilidadeGuincho());

            stmt.execute();

            System.out.println("Guincho cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar guincho: " + e.getMessage());
        }
    }

    public void alterarGuincho(Guincho guincho) {
        String sql = "UPDATE guincho SET peso = ?, tipo = ?, disponibilidade = ? WHERE id_guincho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, guincho.getPesoGuincho());
            stmt.setString(2, guincho.getTipoGuincho());
            stmt.setInt(3, guincho.getDisponibilidadeGuincho());
            stmt.setLong(4, guincho.getIdGuincho());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Guincho alterado com sucesso!");
            } else {
                System.out.println("Nenhum guincho encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar guincho: " + e.getMessage());
        }
    }

    public void removerGuincho(long id) {
        String sql = "DELETE FROM guincho WHERE id_guincho = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Guincho removido com sucesso!");
            } else {
                System.out.println("Nenhum guincho encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover guincho: " + e.getMessage());
        }
    }

    public Guincho pesquisarGuinchoPorId(long id) {
        String sql = "SELECT * FROM guincho WHERE id_guincho = ?";
        Guincho guincho = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                guincho = criarGuincho(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar guincho por ID: " + e.getMessage());
        }

        return guincho;
    }

    public List<Guincho> listarGuinchos() {
        String sql = "SELECT * FROM guincho";
        List<Guincho> guinchos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Guincho guincho = criarGuincho(resultSet);
                guinchos.add(guincho);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar guinchos: " + e.getMessage());
        }

        return guinchos;
    }

    private Guincho criarGuincho(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_guincho");
        double peso = resultSet.getDouble("peso");
        String tipo = resultSet.getString("tipo");
        int disponibilidade = resultSet.getInt("disponibilidade");

        return new Guincho(id, peso, tipo, disponibilidade);
    }
    
    public String calcularTensaoIdeal(Veiculo veiculo) {
        double pesoVeiculo = veiculo.getPesoVeiculo();
        int qtdEixos = veiculo.getQtdEixosVeiculo();
        double pesoEixo = veiculo.getPesoVeiculo();
        double capacidadeCarga = veiculo.getCapacidadeCargaVeiculo();

        // Chamando o m�todo pesoGuincho
        double tensaoIdeal = pesoGuincho(pesoVeiculo, qtdEixos, pesoEixo, capacidadeCarga);
        return "A tens�o ideal do guincho � " + tensaoIdeal;
    }

    private double pesoGuincho(double pesoVeiculo, int qtdEixos, double pesoEixo, double capacidadeCarga) {
        // C�lculos para determinar a tens�o ideal do guincho
        double result = pesoVeiculo * 2;
        result += qtdEixos * pesoEixo;
        return result;
    }
}
