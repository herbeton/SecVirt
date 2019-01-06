package br.com.secretariavirtual.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Representado extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 3, nullable = false)
	private String tipo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa pastro;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa primeirocopastor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa segundocopastor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa secretario;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa respatrimonio;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Setor setor;
	
	@Column(length = 50, nullable = false)
	private String observacoes;
	
	//I-Endereco
	@Column(length = 100, nullable = false)
	private String rua;
	
	@Column(nullable = false)
	private Short numero;
	
	@Column(length = 30, nullable = false)
	private String bairro;
	
	@Column(length = 10, nullable = false)
	private String cep;
	
	@Column(length = 10, nullable = false)
	private String complemento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;
	//F-Endereco
	
	//I-Contato
	@Column(length = 13, nullable = false)
	private String telefone;

	@Column(length = 14, nullable = false)
	private String celular;
	
	@Column(length = 100, nullable = false)
	private String email;
	//F-Contato
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPastro() {
		return pastro;
	}

	public void setPastro(Pessoa pastro) {
		this.pastro = pastro;
	}

	public Pessoa getPrimeiroCopastor() {
		return primeirocopastor;
	}

	public void setPrimeiroCopastor(Pessoa primeirocopastor) {
		this.primeirocopastor = primeirocopastor;
	}

	public Pessoa getSegundoCopastor() {
		return segundocopastor;
	}

	public void setSegundoCopastor(Pessoa segundocopastor) {
		this.segundocopastor = segundocopastor;
	}

	public Pessoa getSecretario() {
		return secretario;
	}

	public void setSecretario(Pessoa secretario) {
		this.secretario = secretario;
	}

	public Pessoa getResPatrimonio() {
		return respatrimonio;
	}

	public void setResPatrimonio(Pessoa respatrimonio) {
		this.respatrimonio = respatrimonio;
	}
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
	
}

