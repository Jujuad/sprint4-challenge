package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dao.LocalizacaoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.model.Localizacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LocalizacaoService {

    private LocalizacaoDao localizacaoDao;

    public LocalizacaoService(Connection connection) {
        this.localizacaoDao = new LocalizacaoDao(connection);
    }

    public void cadastrarLocalizacao(Localizacao localizacao) throws SQLException, BadInfoException {
        validarLocalizacao(localizacao);
        localizacaoDao.cadastrarLocalizacao(localizacao);
    }

    private void validarLocalizacao(Localizacao localizacao) throws BadInfoException {
        if (localizacao.getTipoTerrenoLocalizacao() == null || localizacao.getTipoTerrenoLocalizacao().isEmpty()
                || localizacao.getDescLocalizacao() == null || localizacao.getDescLocalizacao().isEmpty()
                || localizacao.getIdEndereco() <= 0) {
            throw new BadInfoException("Todos os campos da localização são obrigatórios. Preencha todos os campos.");
        }
    }


    public void alterarLocalizacao(long id, Localizacao localizacao) throws SQLException, IdNotFoundException, BadInfoException {
        validarLocalizacao(localizacao);
        localizacaoDao.alterarLocalizacao(localizacao);
    }

    public void removerLocalizacao(long id) throws SQLException, IdNotFoundException {
        localizacaoDao.removerLocalizacao(id);
    }

    public Localizacao pesquisarLocalizacaoPorId(long id) throws SQLException, IdNotFoundException {
        return localizacaoDao.pesquisarLocalizacaoPorId(id);
    }

    public List<Localizacao> listarLocalizacoes() throws SQLException {
        return localizacaoDao.listarLocalizacoes();
    }
}
