package br.com.fiap.challenge.model;

public class Endereco {

    private long idEndereco;
    private String cepEndereco;
    private String ruaEndereco;
    private String numEndereco;
    private String logradouroEndereco;
    private String bairroEndereco;
    private String cidadeEndereco;
    private String municipioEndereco;
    private String ufEndereco;
    private String paisEndereco;
    
    public Endereco(long id, String cepEndereco, String ruaEndereco, String numEndereco, String logradouroEndereco, String bairroEndereco, String cidadeEndereco, String municipioEndereco, String ufEndereco, String paisEndereco) {
        this.idEndereco = id;
        this.cepEndereco = cepEndereco;
        this.ruaEndereco = ruaEndereco;
        this.numEndereco = numEndereco;
        this.logradouroEndereco = logradouroEndereco;
        this.bairroEndereco = bairroEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.municipioEndereco = municipioEndereco;
        this.ufEndereco = ufEndereco;
        this.paisEndereco = paisEndereco;
    }

    
	public long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getCepEndereco() {
		return cepEndereco;
	}
	public void setCepEndereco(String cepEndereco) {
		this.cepEndereco = cepEndereco;
	}
	public String getRuaEndereco() {
		return ruaEndereco;
	}
	public void setRuaEndereco(String ruaEndereco) {
		this.ruaEndereco = ruaEndereco;
	}
	public String getNumEndereco() {
		return numEndereco;
	}
	public void setNumEndereco(String numEndereco) {
		this.numEndereco = numEndereco;
	}
	public String getLogradouroEndereco() {
		return logradouroEndereco;
	}
	public void setLogradouroEndereco(String logradouroEndereco) {
		this.logradouroEndereco = logradouroEndereco;
	}
	public String getBairroEndereco() {
		return bairroEndereco;
	}
	public void setBairroEndereco(String bairroEndereco) {
		this.bairroEndereco = bairroEndereco;
	}
	public String getCidadeEndereco() {
		return cidadeEndereco;
	}
	public void setCidadeEndereco(String cidadeEndereco) {
		this.cidadeEndereco = cidadeEndereco;
	}
	public String getMunicipioEndereco() {
		return municipioEndereco;
	}
	public void setMunicipioEndereco(String municipioEndereco) {
		this.municipioEndereco = municipioEndereco;
	}
	public String getUfEndereco() {
		return ufEndereco;
	}
	public void setUfEndereco(String ufEndereco) {
		this.ufEndereco = ufEndereco;
	}
	public String getPaisEndereco() {
		return paisEndereco;
	}
	public void setPaisEndereco(String paisEndereco) {
		this.paisEndereco = paisEndereco;
	}


}
