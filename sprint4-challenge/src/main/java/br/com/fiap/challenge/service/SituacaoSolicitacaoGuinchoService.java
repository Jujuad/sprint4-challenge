package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.SituacaoSolicitacaoGuinchoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.SituacaoSolicitacaoGuincho;

public class SituacaoSolicitacaoGuinchoService {

    private SituacaoSolicitacaoGuinchoDao situacaoDao;

    public SituacaoSolicitacaoGuinchoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        situacaoDao = new SituacaoSolicitacaoGuinchoDao(conn);
    }

    public void cadastrar(SituacaoSolicitacaoGuincho situacao) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(situacao);
        situacaoDao.cadastrarSituacaoSolicitacaoGuincho(situacao);
    }

    public void atualizar(SituacaoSolicitacaoGuincho situacao) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(situacao);
        situacaoDao.atualizarSituacaoSolicitacaoGuincho(situacao);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        situacaoDao.removerSituacaoSolicitacaoGuincho(id);
    }

    public List<SituacaoSolicitacaoGuincho> listar() throws ClassNotFoundException, SQLException {
        return situacaoDao.listarSituacoesSolicitacaoGuincho();
    }

    public SituacaoSolicitacaoGuincho pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return situacaoDao.pesquisarSituacaoSolicitacaoGuinchoPorId(id);
    }

    private void validar(SituacaoSolicitacaoGuincho situacao) throws BadInfoException {
        if (situacao.getIdSolicitacaoGuincho() <= 0 || situacao.getIdGuincho() <= 0
                || situacao.getIdPrestadorServico() <= 0) {
            throw new BadInfoException("Todos os campos da Situação da Solicitação de Guincho são obrigatórios. Preencha todos os campos.");
        }
    }
}
