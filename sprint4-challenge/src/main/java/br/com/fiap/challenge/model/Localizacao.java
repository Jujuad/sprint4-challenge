package br.com.fiap.challenge.model;

public class Localizacao {

    private long idLocalizacao;
    private String tipoTerrenoLocalizacao;
    private String descLocalizacao;
    private long idEndereco;
    
    public Localizacao(long idLocalizacao2, String tipoTerrenoLocalizacao, String descLocalizacao, long idEndereco2) {
        this.idLocalizacao = idLocalizacao2;
        this.tipoTerrenoLocalizacao = tipoTerrenoLocalizacao;
        this.descLocalizacao = descLocalizacao;
        this.idEndereco = idEndereco2;
    }

    
	public long getIdLocalizacao() {
		return idLocalizacao;
	}
	public void setIdLocalizacao(long idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
	}
	public String getTipoTerrenoLocalizacao() {
		return tipoTerrenoLocalizacao;
	}
	public void setTipoTerrenoLocalizacao(String tipoTerrenoLocalizacao) {
		this.tipoTerrenoLocalizacao = tipoTerrenoLocalizacao;
	}
	public String getDescLocalizacao() {
		return descLocalizacao;
	}
	public void setDescLocalizacao(String descLocalizacao) {
		this.descLocalizacao = descLocalizacao;
	}
	public long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

   
}
