package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {

    private Connection connection;

    public VeiculoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, peso, comprimento, altura, modelo, chassi_alongado, qtd_eixos, capacidade_carga, disponibilidade, id_tipo_veiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlacaVeiculo());
            stmt.setDouble(2, veiculo.getPesoVeiculo());
            stmt.setDouble(3, veiculo.getComprimentoVeiculo());
            stmt.setDouble(4, veiculo.getAlturaVeiculo());
            stmt.setString(5, veiculo.getModeloVeiculo());
            stmt.setInt(6, veiculo.getChassiAlongadoVeiculo());
            stmt.setInt(7, veiculo.getQtdEixosVeiculo());
            stmt.setInt(8, veiculo.getCapacidadeCargaVeiculo());
            stmt.setInt(9, veiculo.getDisponibilidadeVeiculo());
            stmt.setLong(10, veiculo.getIdTipoVeiculoVeiculo());

            stmt.execute();

            System.out.println("Veículo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar veículo: " + e.getMessage());
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET placa = ?, peso = ?, comprimento = ?, altura = ?, modelo = ?, chassi_alongado = ?, qtd_eixos = ?, capacidade_carga = ?, disponibilidade = ?, id_tipo_veiculo = ? WHERE id_veiculo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlacaVeiculo());
            stmt.setDouble(2, veiculo.getPesoVeiculo());
            stmt.setDouble(3, veiculo.getComprimentoVeiculo());
            stmt.setDouble(4, veiculo.getAlturaVeiculo());
            stmt.setString(5, veiculo.getModeloVeiculo());
            stmt.setInt(6, veiculo.getChassiAlongadoVeiculo());
            stmt.setInt(7, veiculo.getQtdEixosVeiculo());
            stmt.setInt(8, veiculo.getCapacidadeCargaVeiculo());
            stmt.setInt(9, veiculo.getDisponibilidadeVeiculo());
            stmt.setLong(10, veiculo.getIdTipoVeiculoVeiculo());
            stmt.setLong(11, veiculo.getIdVeiculo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Veículo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    public void removerVeiculo(long id) {
        String sql = "DELETE FROM veiculo WHERE id_veiculo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover veículo: " + e.getMessage());
        }
    }

    public Veiculo pesquisarVeiculoPorId(long id) {
        String sql = "SELECT * FROM veiculo WHERE id_veiculo = ?";
        Veiculo veiculo = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                veiculo = criarVeiculo(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar veículo por ID: " + e.getMessage());
        }

        return veiculo;
    }

    public List<Veiculo> listarVeiculos() {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> veiculos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Veiculo veiculo = criarVeiculo(resultSet);
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
        }

        return veiculos;
    }

    private Veiculo criarVeiculo(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_veiculo");
        String placa = resultSet.getString("placa");
        double peso = resultSet.getDouble("peso");
        double comprimento = resultSet.getDouble("comprimento");
        double altura = resultSet.getDouble("altura");
        String modelo = resultSet.getString("modelo");
        int chassiAlongado = resultSet.getInt("chassi_alongado");
        int qtdEixos = resultSet.getInt("qtd_eixos");
        int capacidadeCarga = resultSet.getInt("capacidade_carga");
        int disponibilidade = resultSet.getInt("disponibilidade");
        long idTipoVeiculo = resultSet.getLong("id_tipo_veiculo");

        return new Veiculo(id, placa, peso, comprimento, altura, modelo, chassiAlongado, qtdEixos, capacidadeCarga, disponibilidade, idTipoVeiculo);
    }
    
    public double calcularPesoTotalVeiculo(long idVeiculo) {
        String sql = "SELECT peso, capacidade_carga FROM veiculo WHERE id_veiculo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idVeiculo);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                double peso = resultSet.getDouble("peso");
                int capacidadeCarga = resultSet.getInt("capacidade_carga");

                double pesoTotal = peso + capacidadeCarga;

                System.out.println("Peso total do veículo (incluindo capacidade de carga): " + pesoTotal + " kg");

                return pesoTotal;
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao calcular peso total do veículo: " + e.getMessage());
        }

        return 0; 
    }
    
    public double calcularAlturaTotalVeiculo(long idVeiculo) {
        String sql = "SELECT altura FROM veiculo WHERE id_veiculo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idVeiculo);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                double altura = resultSet.getDouble("altura");

                System.out.println("Altura total do veículo: " + altura + " metros");

                return altura;
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao calcular altura total do veículo: " + e.getMessage());
        }

        return 0; 
    }
    
    public double calcularComprimentoTotalVeiculo(long idVeiculo) {
        String sql = "SELECT comprimento FROM veiculo WHERE id_veiculo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idVeiculo);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                double comprimento = resultSet.getDouble("comprimento");

                System.out.println("Comprimento total do veículo: " + comprimento + " metros");

                return comprimento;
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao calcular comprimento total do veículo: " + e.getMessage());
        }

        return 0;
    }

}
