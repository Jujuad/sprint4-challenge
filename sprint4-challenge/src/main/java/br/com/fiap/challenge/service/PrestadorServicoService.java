package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.PrestadorServicoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.PrestadorServico;

public class PrestadorServicoService {

    private PrestadorServicoDao prestadorServicoDao;

    public PrestadorServicoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        prestadorServicoDao = new PrestadorServicoDao(conn);
    }

    public void cadastrar(PrestadorServico prestadorServico) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(prestadorServico);
        prestadorServicoDao.cadastrarPrestadorServico(prestadorServico);
    }

    public void atualizar(PrestadorServico prestadorServico) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(prestadorServico);
        prestadorServicoDao.atualizarPrestadorServico(prestadorServico);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        prestadorServicoDao.removerPrestadorServico(id);
    }

    public List<PrestadorServico> listar() throws ClassNotFoundException, SQLException {
        return prestadorServicoDao.listarPrestadoresServico();
    }

    public PrestadorServico pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return prestadorServicoDao.pesquisarPrestadorServicoPorId(id);
    }

    private void validar(PrestadorServico prestadorServico) throws BadInfoException {
        if (prestadorServico.getCpfPrestadorServico() <= 0 || prestadorServico.getNomePrestadorServico() == null
                || prestadorServico.getNomePrestadorServico().isEmpty()
                || prestadorServico.getServicoOferecidoPrestador() == null
                || prestadorServico.getServicoOferecidoPrestador().isEmpty()) {
            throw new BadInfoException("Todos os campos do Prestador de Serviço são obrigatórios. Preencha todos os campos.");
        }
    }
}
