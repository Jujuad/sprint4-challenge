package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.SolicitacaoGuinchoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.SolicitacaoGuincho;

public class SolicitacaoGuinchoService {

    private SolicitacaoGuinchoDao solicitacaoDao;

    public SolicitacaoGuinchoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        solicitacaoDao = new SolicitacaoGuinchoDao(conn);
    }

    public void cadastrar(SolicitacaoGuincho solicitacao) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(solicitacao);
        solicitacaoDao.cadastrarSolicitacaoGuincho(solicitacao);
    }

    public void atualizar(SolicitacaoGuincho solicitacao) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(solicitacao);
        solicitacaoDao.atualizarSolicitacaoGuincho(solicitacao);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        solicitacaoDao.removerSolicitacaoGuincho(id);
    }

    public List<SolicitacaoGuincho> listar() throws ClassNotFoundException, SQLException {
        return solicitacaoDao.listarSolicitacoesGuincho();
    }

    public SolicitacaoGuincho pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return solicitacaoDao.pesquisarSolicitacaoGuinchoPorId(id);
    }

    private void validar(SolicitacaoGuincho solicitacao) throws BadInfoException {
        if (solicitacao.getDescricaoSolicitacaoGuincho() == null || solicitacao.getDescricaoSolicitacaoGuincho().isEmpty()
                || solicitacao.getIdLocalizacao() <= 0 || solicitacao.getIdUsuario() <= 0 || solicitacao.getIdVeiculo() <= 0) {
            throw new BadInfoException("Todos os campos da Solicitação de Guincho são obrigatórios. Preencha todos os campos.");
        }
    }
    
    //public void calcularMelhorRota() {
        //solicitacaoDao.calcularMelhorRota();
    //}
}
