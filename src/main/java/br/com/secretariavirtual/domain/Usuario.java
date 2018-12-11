package br.com.secretariavirtual.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";
		if(ativo)
			ativoFormatado = "Sim";
		return ativoFormatado;
	}
	
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		if(tipoFormatado == "A")
			tipoFormatado = "Administrador";
		else if(tipoFormatado == "B")
			tipoFormatado = "Balconista";
		else if(tipoFormatado == "G")
			tipoFormatado = "Gerente";
		return tipoFormatado;
	}
}