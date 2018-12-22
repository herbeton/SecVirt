package br.com.secretariavirtual.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Setor extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
