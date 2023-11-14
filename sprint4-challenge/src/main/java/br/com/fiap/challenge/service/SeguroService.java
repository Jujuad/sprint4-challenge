package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.SeguroDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.Seguro;

public class SeguroService {

    private SeguroDao seguroDao;

    public SeguroService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        seguroDao = new SeguroDao(conn);
    }

    public void cadastrar(Seguro seguro) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(seguro);
        seguroDao.cadastrarSeguro(seguro);
    }

    public void atualizar(Seguro seguro) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(seguro);
        seguroDao.atualizarSeguro(seguro);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        seguroDao.removerSeguro(id);
    }

    public List<Seguro> listar() throws ClassNotFoundException, SQLException {
        return seguroDao.listarSeguros();
    }

    public Seguro pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return seguroDao.pesquisarSeguroPorId(id);
    }

    private void validar(Seguro seguro) throws BadInfoException {
        if (seguro.getDataInicioSeguro() == null || seguro.getDataTerminoSeguro() == null
                || seguro.getValorCoberturaSeguro() <= 0 || seguro.getIdUsuario() <= 0
                || seguro.getIdVeiculo() <= 0) {
            throw new BadInfoException("Todos os campos do Seguro são obrigatórios. Preencha todos os campos.");
        }
    }
}
