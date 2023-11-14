package br.com.fiap.challenge.model;

public class Veiculo {

    private long idVeiculo;
    private String placaVeiculo;
    private double pesoVeiculo;
    private double comprimentoVeiculo;
    private double alturaVeiculo;
    private String modeloVeiculo;
    private int chassiAlongadoVeiculo;
    private int qtdEixosVeiculo;
    private int capacidadeCargaVeiculo;
    private int disponibilidadeVeiculo;
    private long idTipoVeiculoVeiculo;
    
    public Veiculo(long id, String placaVeiculo, double pesoVeiculo, double comprimentoVeiculo, double alturaVeiculo, String modeloVeiculo, int chassiAlongadoVeiculo, int qtdEixosVeiculo, int capacidadeCargaVeiculo, int disponibilidadeVeiculo, long idTipoVeiculo) {
        this.idVeiculo = id;
        this.placaVeiculo = placaVeiculo;
        this.pesoVeiculo = pesoVeiculo;
        this.comprimentoVeiculo = comprimentoVeiculo;
        this.alturaVeiculo = alturaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.chassiAlongadoVeiculo = chassiAlongadoVeiculo;
        this.qtdEixosVeiculo = qtdEixosVeiculo;
        this.capacidadeCargaVeiculo = capacidadeCargaVeiculo;
        this.disponibilidadeVeiculo = disponibilidadeVeiculo;
        this.idTipoVeiculoVeiculo = idTipoVeiculo;
    }


    
	public long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public double getPesoVeiculo() {
		return pesoVeiculo;
	}
	public void setPesoVeiculo(double pesoVeiculo) {
		this.pesoVeiculo = pesoVeiculo;
	}
	public double getComprimentoVeiculo() {
		return comprimentoVeiculo;
	}
	public void setComprimentoVeiculo(double comprimentoVeiculo) {
		this.comprimentoVeiculo = comprimentoVeiculo;
	}
	public double getAlturaVeiculo() {
		return alturaVeiculo;
	}
	public void setAlturaVeiculo(double alturaVeiculo) {
		this.alturaVeiculo = alturaVeiculo;
	}
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}
	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}
	public int getChassiAlongadoVeiculo() {
		return chassiAlongadoVeiculo;
	}
	public void setChassiAlongadoVeiculo(int chassiAlongadoVeiculo) {
		this.chassiAlongadoVeiculo = chassiAlongadoVeiculo;
	}
	public int getQtdEixosVeiculo() {
		return qtdEixosVeiculo;
	}
	public void setQtdEixosVeiculo(int qtdEixosVeiculo) {
		this.qtdEixosVeiculo = qtdEixosVeiculo;
	}
	public int getCapacidadeCargaVeiculo() {
		return capacidadeCargaVeiculo;
	}
	public void setCapacidadeCargaVeiculo(int capacidadeCargaVeiculo) {
		this.capacidadeCargaVeiculo = capacidadeCargaVeiculo;
	}
	public int getDisponibilidadeVeiculo() {
		return disponibilidadeVeiculo;
	}
	public void setDisponibilidadeVeiculo(int disponibilidadeVeiculo) {
		this.disponibilidadeVeiculo = disponibilidadeVeiculo;
	}
	public long getIdTipoVeiculoVeiculo() {
		return idTipoVeiculoVeiculo;
	}
	public void setIdTipoVeiculoVeiculo(long idTipoVeiculoVeiculo) {
		this.idTipoVeiculoVeiculo = idTipoVeiculoVeiculo;
	}

 
}
