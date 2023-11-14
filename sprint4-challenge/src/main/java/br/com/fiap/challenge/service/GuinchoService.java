package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dao.GuinchoDao;
import br.com.fiap.challenge.exception.BadInfoException;
import br.com.fiap.challenge.exception.IdNotFoundException;
import br.com.fiap.challenge.model.Guincho;
import br.com.fiap.challenge.model.Veiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GuinchoService {

    private GuinchoDao guinchoDao;

    public GuinchoService(Connection connection) {
        this.guinchoDao = new GuinchoDao(connection);
    }

    public void cadastrarGuincho(Guincho guincho) throws SQLException, BadInfoException {
        validarGuincho(guincho);
        guinchoDao.cadastrarGuincho(guincho);
    }

    private void validarGuincho(Guincho guincho) throws BadInfoException {
       
        if (guincho.getTipoGuincho() == null || guincho.getTipoGuincho().isEmpty()) {
            throw new BadInfoException("Tipo de guincho é obrigatório");
        }
        
    }

    public void alterarGuincho(long id, Guincho guincho) throws SQLException, IdNotFoundException, BadInfoException {
        validarGuincho(guincho);
        guinchoDao.alterarGuincho(guincho);
    }

    public void removerGuincho(long id) throws SQLException, IdNotFoundException {
        guinchoDao.removerGuincho(id);
    }

    public Guincho pesquisarGuinchoPorId(long id) throws SQLException, IdNotFoundException {
        return guinchoDao.pesquisarGuinchoPorId(id);
    }

    public List<Guincho> listarGuinchos() throws SQLException {
        return guinchoDao.listarGuinchos();
    }

    public String calcularTensaoIdeal(Veiculo veiculo) {
        double pesoVeiculo = veiculo.getPesoVeiculo();
        int qtdEixos = veiculo.getQtdEixosVeiculo();
        double pesoEixo = veiculo.getPesoVeiculo();
        double capacidadeCarga = veiculo.getCapacidadeCargaVeiculo();

        
        double tensaoIdeal = pesoGuincho(pesoVeiculo, qtdEixos, pesoEixo, capacidadeCarga);
        return "A tensão ideal do guincho é " + tensaoIdeal;
    }

    private double pesoGuincho(double pesoVeiculo, int qtdEixos, double pesoEixo, double capacidadeCarga) {
        
        double result = pesoVeiculo * 2;
        result += qtdEixos * pesoEixo;
        return result;
    }
}
