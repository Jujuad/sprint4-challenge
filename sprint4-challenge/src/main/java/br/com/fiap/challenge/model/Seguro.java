package br.com.fiap.challenge.model;

import java.util.Date;


public class Seguro {

    private long idSeguro;
    private Date dataInicioSeguro;
    private Date dataTerminoSeguro;
    private double valorCoberturaSeguro;
    private long idUsuario;
    private long idVeiculo;
    
    public Seguro(long id, Date dataInicioSeguro, Date dataTerminoSeguro, double valorCoberturaSeguro, long idUsuario2, long idVeiculo2) {
        this.idSeguro = id;
        this.dataInicioSeguro = dataInicioSeguro;
        this.dataTerminoSeguro = dataTerminoSeguro;
        this.valorCoberturaSeguro = valorCoberturaSeguro;
        this.idUsuario = idUsuario2;
        this.idVeiculo = idVeiculo2;
    }

    
	public long getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(long idSeguro) {
		this.idSeguro = idSeguro;
	}
	public Date getDataInicioSeguro() {
		return dataInicioSeguro;
	}
	public void setDataInicioSeguro(Date dataInicioSeguro) {
		this.dataInicioSeguro = dataInicioSeguro;
	}
	public Date getDataTerminoSeguro() {
		return dataTerminoSeguro;
	}
	public void setDataTerminoSeguro(Date dataTerminoSeguro) {
		this.dataTerminoSeguro = dataTerminoSeguro;
	}
	public double getValorCoberturaSeguro() {
		return valorCoberturaSeguro;
	}
	public void setValorCoberturaSeguro(double valorCoberturaSeguro) {
		this.valorCoberturaSeguro = valorCoberturaSeguro;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

    
}
