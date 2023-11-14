package br.com.fiap.challenge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Usuario {

    private long idUsuario;
    private long cpfUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private Date dtNascUsuario;
    
    private static List<Usuario> usuariosCadastrados = new ArrayList<>();
    
    public Usuario(long id, long cpfUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, Date dtNascUsuario) {
        this.idUsuario = id;
        this.cpfUsuario = cpfUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dtNascUsuario = dtNascUsuario;
    }

    
    public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(long cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public Date getDtNascUsuario() {
		return dtNascUsuario;
	}
	public void setDtNascUsuario(Date dtNascUsuario) {
		this.dtNascUsuario = dtNascUsuario;
	}

    
}
