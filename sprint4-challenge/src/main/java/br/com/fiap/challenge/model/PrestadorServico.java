package br.com.fiap.challenge.model;

public class PrestadorServico {

    private long idPrestadorServico;
    private long cpfPrestadorServico;
    private String nomePrestadorServico;
    private String servicoOferecidoPrestador;
    
    public PrestadorServico(long id, long cpfPrestadorServico, String nomePrestadorServico, String servicoOferecidoPrestador) {
        this.idPrestadorServico = id;
        this.cpfPrestadorServico = cpfPrestadorServico;
        this.nomePrestadorServico = nomePrestadorServico;
        this.servicoOferecidoPrestador = servicoOferecidoPrestador;
    }

    
	public long getIdPrestadorServico() {
		return idPrestadorServico;
	}
	public void setIdPrestadorServico(long idPrestadorServico) {
		this.idPrestadorServico = idPrestadorServico;
	}
	public long getCpfPrestadorServico() {
		return cpfPrestadorServico;
	}
	public void setCpfPrestadorServico(long cpfPrestadorServico) {
		this.cpfPrestadorServico = cpfPrestadorServico;
	}
	public String getNomePrestadorServico() {
		return nomePrestadorServico;
	}
	public void setNomePrestadorServico(String nomePrestadorServico) {
		this.nomePrestadorServico = nomePrestadorServico;
	}
	public String getServicoOferecidoPrestador() {
		return servicoOferecidoPrestador;
	}
	public void setServicoOferecidoPrestador(String servicoOferecidoPrestador) {
		this.servicoOferecidoPrestador = servicoOferecidoPrestador;
	}


}
