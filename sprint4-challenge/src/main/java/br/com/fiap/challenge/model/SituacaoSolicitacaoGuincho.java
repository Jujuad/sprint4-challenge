package br.com.fiap.challenge.model;

public class SituacaoSolicitacaoGuincho {

    private long idSituacaoSolicitacao;
    private long idSolicitacaoGuincho;
    private long idGuincho;
    private long idPrestadorServico;
    
    
    public SituacaoSolicitacaoGuincho(long id, long idSolicitacaoGuincho2, long idGuincho2, long idPrestadorServico2) {
        this.idSituacaoSolicitacao = id;
        this.idSolicitacaoGuincho = idSolicitacaoGuincho2;
        this.idGuincho = idGuincho2;
        this.idPrestadorServico = idPrestadorServico2;
    }
    
	public long getIdSituacaoSolicitacao() {
		return idSituacaoSolicitacao;
	}
	public void setIdSituacaoSolicitacao(long idSituacaoSolicitacao) {
		this.idSituacaoSolicitacao = idSituacaoSolicitacao;
	}
	public long getIdSolicitacaoGuincho() {
		return idSolicitacaoGuincho;
	}
	public void setIdSolicitacaoGuincho(long idSolicitacaoGuincho) {
		this.idSolicitacaoGuincho = idSolicitacaoGuincho;
	}
	public long getIdGuincho() {
		return idGuincho;
	}
	public void setIdGuincho(long idGuincho) {
		this.idGuincho = idGuincho;
	}
	public long getIdPrestadorServico() {
		return idPrestadorServico;
	}
	public void setIdPrestadorServico(long idPrestadorServico) {
		this.idPrestadorServico = idPrestadorServico;
	}

    
}
