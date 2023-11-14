package br.com.fiap.challenge.model;

public class SolicitacaoGuincho {

    private long idSolicitacaoGuincho;
    private String descricaoSolicitacaoGuincho;
    private long idLocalizacao;
    private long idUsuario;
    private long idVeiculo;
    
    public SolicitacaoGuincho(long id, String descricaoSolicitacaoGuincho, long idLocalizacao2, long idUsuario2, long idVeiculo2) {
        this.idSolicitacaoGuincho = id;
        this.descricaoSolicitacaoGuincho = descricaoSolicitacaoGuincho;
        this.idLocalizacao = idLocalizacao2;
        this.idUsuario = idUsuario2;
        this.idVeiculo = idVeiculo2;
    }
    
    
	public long getIdSolicitacaoGuincho() {
		return idSolicitacaoGuincho;
	}
	public void setIdSolicitacaoGuincho(long idSolicitacaoGuincho) {
		this.idSolicitacaoGuincho = idSolicitacaoGuincho;
	}
	public String getDescricaoSolicitacaoGuincho() {
		return descricaoSolicitacaoGuincho;
	}
	public void setDescricaoSolicitacaoGuincho(String descricaoSolicitacaoGuincho) {
		this.descricaoSolicitacaoGuincho = descricaoSolicitacaoGuincho;
	}
	public long getIdLocalizacao() {
		return idLocalizacao;
	}
	public void setIdLocalizacao(long idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
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
