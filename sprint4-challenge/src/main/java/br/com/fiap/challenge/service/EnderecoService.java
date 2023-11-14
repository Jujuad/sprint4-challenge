package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dao.EnderecoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.model.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class EnderecoService {

    private EnderecoDao enderecoDao;

    public EnderecoService(Connection connection) {
        this.enderecoDao = new EnderecoDao(connection);
    }

    public void cadastrarEndereco(Endereco endereco) throws SQLException, BackingStoreException, BadInfoException {
        validarEndereco(endereco);
        enderecoDao.cadastrarEndereco(endereco);
    }

    private void validarEndereco(Endereco endereco) throws BadInfoException {
        if (!endereco.getCepEndereco().matches("\\d{5}-\\d{3}")) {
            throw new BadInfoException("CEP inválido");
        }
    }

    public void alterarEndereco(long id, Endereco endereco) throws SQLException, IdNotFoundException, BadInfoException {
        validarEndereco(endereco);
        enderecoDao.alterarEndereco(endereco);
    }

    public void removerEndereco(long id) throws SQLException, IdNotFoundException {
        enderecoDao.removerEndereco(id);
    }

    public Endereco pesquisarEnderecoPorId(long id) throws SQLException, IdNotFoundException {
        return enderecoDao.pesquisarEnderecoPorId(id);
    }

    public List<Endereco> listarEnderecos() throws SQLException {
        return enderecoDao.listarEnderecos();
    }
}
