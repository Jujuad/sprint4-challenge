package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.TipoVeiculoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.TipoVeiculo;

public class TipoVeiculoService {

    private TipoVeiculoDao tipoVeiculoDao;

    public TipoVeiculoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        tipoVeiculoDao = new TipoVeiculoDao(conn);
    }

    public void cadastrar(TipoVeiculo tipoVeiculo) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(tipoVeiculo);
        tipoVeiculoDao.cadastrarTipoVeiculo(tipoVeiculo);
    }

    public void atualizar(TipoVeiculo tipoVeiculo) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(tipoVeiculo);
        tipoVeiculoDao.atualizarTipoVeiculo(tipoVeiculo);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        tipoVeiculoDao.removerTipoVeiculo(id);
    }

    public List<TipoVeiculo> listar() throws ClassNotFoundException, SQLException {
        return tipoVeiculoDao.listarTiposVeiculo();
    }

    public TipoVeiculo pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return tipoVeiculoDao.pesquisarTipoVeiculoPorId(id);
    }

    private void validar(TipoVeiculo tipoVeiculo) throws BadInfoException {
        if (tipoVeiculo.getNomeTipoVeiculo() == null || tipoVeiculo.getNomeTipoVeiculo().isEmpty()) {
            throw new BadInfoException("O nome do Tipo de Veículo é obrigatório. Preencha o campo.");
        }
    }
}
