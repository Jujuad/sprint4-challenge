package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Localizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalizacaoDao {

    private Connection connection;

    public LocalizacaoDao(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarLocalizacao(Localizacao localizacao) {
        String sql = "INSERT INTO localizacao (tipo_terreno, descricao, id_endereco) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, localizacao.getTipoTerrenoLocalizacao());
            stmt.setString(2, localizacao.getDescLocalizacao());
            stmt.setLong(3, localizacao.getIdEndereco());

            stmt.execute();

            System.out.println("Localização cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar localização: " + e.getMessage());
        }
    }

    public void alterarLocalizacao(Localizacao localizacao) {
        String sql = "UPDATE localizacao SET tipo_terreno = ?, descricao = ?, id_endereco = ? WHERE id_localizacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, localizacao.getTipoTerrenoLocalizacao());
            stmt.setString(2, localizacao.getDescLocalizacao());
            stmt.setLong(3, localizacao.getIdEndereco());
            stmt.setLong(4, localizacao.getIdLocalizacao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Localização alterada com sucesso!");
            } else {
                System.out.println("Nenhuma localização encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar localização: " + e.getMessage());
        }
    }

    public void removerLocalizacao(long id) {
        String sql = "DELETE FROM localizacao WHERE id_localizacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Localização removida com sucesso!");
            } else {
                System.out.println("Nenhuma localização encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover localização: " + e.getMessage());
        }
    }

    public Localizacao pesquisarLocalizacaoPorId(long id) {
        String sql = "SELECT * FROM localizacao WHERE id_localizacao = ?";
        Localizacao localizacao = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                localizacao = criarLocalizacao(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar localização por ID: " + e.getMessage());
        }

        return localizacao;
    }

    public List<Localizacao> listarLocalizacoes() {
        String sql = "SELECT * FROM localizacao";
        List<Localizacao> localizacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Localizacao localizacao = criarLocalizacao(resultSet);
                localizacoes.add(localizacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar localizações: " + e.getMessage());
        }

        return localizacoes;
    }

    private Localizacao criarLocalizacao(ResultSet resultSet) throws SQLException {
        long idLocalizacao = resultSet.getLong("id_localizacao");
        String tipoTerreno = resultSet.getString("tipo_terreno");
        String descricao = resultSet.getString("descricao");
        long idEndereco = resultSet.getLong("id_endereco");

        return new Localizacao(idLocalizacao, tipoTerreno, descricao, idEndereco);
    }
}
