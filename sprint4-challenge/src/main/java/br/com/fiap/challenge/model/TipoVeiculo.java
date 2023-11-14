package br.com.fiap.challenge.model;

public class TipoVeiculo {

    private long idTipoVeiculo;
    private String nomeTipoVeiculo;
    
    public TipoVeiculo(long id, String nomeTipoVeiculo) {
        this.idTipoVeiculo = id;
        this.nomeTipoVeiculo = nomeTipoVeiculo;
    }

    
	public long getIdTipoVeiculo() {
		return idTipoVeiculo;
	}
	public void setIdTipoVeiculo(long idTipoVeiculo) {
		this.idTipoVeiculo = idTipoVeiculo;
	}
	public String getNomeTipoVeiculo() {
		return nomeTipoVeiculo;
	}
	public void setNomeTipoVeiculo(String nomeTipoVeiculo) {
		this.nomeTipoVeiculo = nomeTipoVeiculo;
	}

 
}
