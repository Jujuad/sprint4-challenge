package br.com.fiap.challenge.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.challenge.dao.VeiculoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.factory.ConnectionFactory;
import br.com.fiap.challenge.model.Veiculo;

public class VeiculoService {

    private VeiculoDao veiculoDao;

    public VeiculoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        veiculoDao = new VeiculoDao(conn);
    }

    public void cadastrar(Veiculo veiculo) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(veiculo);
        veiculoDao.cadastrarVeiculo(veiculo);
    }

    public void atualizar(Veiculo veiculo) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(veiculo);
        veiculoDao.atualizarVeiculo(veiculo);
    }

    public void remover(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        veiculoDao.removerVeiculo(id);
    }

    public List<Veiculo> listar() throws ClassNotFoundException, SQLException {
        return veiculoDao.listarVeiculos();
    }

    public Veiculo pesquisar(long id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return veiculoDao.pesquisarVeiculoPorId(id);
    }

    public double calcularPesoTotal(long idVeiculo) throws ClassNotFoundException, SQLException {
        return veiculoDao.calcularPesoTotalVeiculo(idVeiculo);
    }

    public double calcularAlturaTotal(long idVeiculo) throws ClassNotFoundException, SQLException {
        return veiculoDao.calcularAlturaTotalVeiculo(idVeiculo);
    }

    public double calcularComprimentoTotal(long idVeiculo) throws ClassNotFoundException, SQLException {
        return veiculoDao.calcularComprimentoTotalVeiculo(idVeiculo);
    }

    private void validar(Veiculo veiculo) throws BadInfoException {
        if (veiculo == null) {
            throw new BadInfoException("Veículo não pode ser nulo");
        }

        if (veiculo.getPlacaVeiculo() == null || veiculo.getPlacaVeiculo().isEmpty()) {
            throw new BadInfoException("A placa do veículo é obrigatória");
        }

        if (veiculo.getPesoVeiculo() <= 0) {
            throw new BadInfoException("O peso do veículo deve ser maior que zero");
        }

        if (veiculo.getAlturaVeiculo() <= 0) {
            throw new BadInfoException("A altura do veículo deve ser maior que zero");
        }

        if (veiculo.getComprimentoVeiculo() <= 0) {
            throw new BadInfoException("O comprimento do veículo deve ser maior que zero");
        }

    }

}
