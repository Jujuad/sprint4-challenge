package br.com.fiap.challenge.model;

public class Guincho {

    private long idGuincho;
    private double pesoGuincho;
    private String tipoGuincho;
    private int disponibilidadeGuincho;
    
    public Guincho(long id, double pesoGuincho, String tipoGuincho, int disponibilidadeGuincho) {
        this.idGuincho = id;
        this.pesoGuincho = pesoGuincho;
        this.tipoGuincho = tipoGuincho;
        this.disponibilidadeGuincho = disponibilidadeGuincho;
    }

    
	public long getIdGuincho() {
		return idGuincho;
	}
	public void setIdGuincho(long idGuincho) {
		this.idGuincho = idGuincho;
	}
	public double getPesoGuincho() {
		return pesoGuincho;
	}
	public void setPesoGuincho(double pesoGuincho) {
		this.pesoGuincho = pesoGuincho;
	}
	public String getTipoGuincho() {
		return tipoGuincho;
	}
	public void setTipoGuincho(String tipoGuincho) {
		this.tipoGuincho = tipoGuincho;
	}
	public int getDisponibilidadeGuincho() {
		return disponibilidadeGuincho;
	}
	public void setDisponibilidadeGuincho(int disponibilidadeGuincho) {
		this.disponibilidadeGuincho = disponibilidadeGuincho;
	}

   
}
