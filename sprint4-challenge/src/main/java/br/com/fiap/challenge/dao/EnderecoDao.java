package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    private Connection connection;

    public EnderecoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco (cep, rua, numero, logradouro, bairro, cidade, municipio, uf, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getCepEndereco());
            stmt.setString(2, endereco.getRuaEndereco());
            stmt.setString(3, endereco.getNumEndereco());
            stmt.setString(4, endereco.getLogradouroEndereco());
            stmt.setString(5, endereco.getBairroEndereco());
            stmt.setString(6, endereco.getCidadeEndereco());
            stmt.setString(7, endereco.getMunicipioEndereco());
            stmt.setString(8, endereco.getUfEndereco());
            stmt.setString(9, endereco.getPaisEndereco());

            stmt.execute();

            System.out.println("Endereço cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar endereço: " + e.getMessage());
        }
    }

    public void alterarEndereco(Endereco endereco) {
        String sql = "UPDATE endereco SET cep = ?, rua = ?, numero = ?, logradouro = ?, bairro = ?, cidade = ?, municipio = ?, uf = ?, pais = ? WHERE id_endereco = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getCepEndereco());
            stmt.setString(2, endereco.getRuaEndereco());
            stmt.setString(3, endereco.getNumEndereco());
            stmt.setString(4, endereco.getLogradouroEndereco());
            stmt.setString(5, endereco.getBairroEndereco());
            stmt.setString(6, endereco.getCidadeEndereco());
            stmt.setString(7, endereco.getMunicipioEndereco());
            stmt.setString(8, endereco.getUfEndereco());
            stmt.setString(9, endereco.getPaisEndereco());
            stmt.setLong(10, endereco.getIdEndereco());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Endereço alterado com sucesso!");
            } else {
                System.out.println("Nenhum endereço encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar endereço: " + e.getMessage());
        }
    }

    public void removerEndereco(long id) {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Endereço removido com sucesso!");
            } else {
                System.out.println("Nenhum endereço encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover endereço: " + e.getMessage());
        }
    }

    public Endereco pesquisarEnderecoPorId(long id) {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        Endereco endereco = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                endereco = criarEndereco(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar endereço por ID: " + e.getMessage());
        }

        return endereco;
    }

    public List<Endereco> listarEnderecos() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> enderecos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Endereco endereco = criarEndereco(resultSet);
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar endereços: " + e.getMessage());
        }

        return enderecos;
    }

    private Endereco criarEndereco(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id_endereco");
        String cep = resultSet.getString("cep");
        String rua = resultSet.getString("rua");
        String numero = resultSet.getString("numero");
        String logradouro = resultSet.getString("logradouro");
        String bairro = resultSet.getString("bairro");
        String cidade = resultSet.getString("cidade");
        String municipio = resultSet.getString("municipio");
        String uf = resultSet.getString("uf");
        String pais = resultSet.getString("pais");

        return new Endereco(id, cep, rua, numero, logradouro, bairro, cidade, municipio, uf, pais);
    }
}
