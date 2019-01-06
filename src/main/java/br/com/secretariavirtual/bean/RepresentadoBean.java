package br.com.secretariavirtual.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.secretariavirtual.dao.CidadeDAO;
import br.com.secretariavirtual.dao.EstadoDAO;
import br.com.secretariavirtual.dao.PessoaDAO;
import br.com.secretariavirtual.dao.RepresentadoDAO;
import br.com.secretariavirtual.dao.SetorDAO;
import br.com.secretariavirtual.domain.Cidade;
import br.com.secretariavirtual.domain.Estado;
import br.com.secretariavirtual.domain.Pessoa;
import br.com.secretariavirtual.domain.Representado;
import br.com.secretariavirtual.domain.Setor;

@ManagedBean
@ViewScoped
public class RepresentadoBean {
	private Representado representado;
	private List<Representado> representados;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	private List<Setor> setores;

	public Representado getrepresentado() {
		return representado;
	}

	public void setrepresentado(Representado representado) {
		this.representado = representado;
	}

	public void novo() {
		try {
			representado = new Representado();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<Cidade>();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma novo representado");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			RepresentadoDAO representadoDAO = new RepresentadoDAO();
			PessoaDAO pessoaDAO = new PessoaDAO();
			SetorDAO setorDAO = new SetorDAO();
			representadoDAO.merge(representado);

			novo();
			representados = representadoDAO.listar();
			pessoas = pessoaDAO.listar();
			setores = setorDAO.listar();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			Messages.addGlobalInfo("representado salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			RepresentadoDAO representadoDAO = new RepresentadoDAO();
			setrepresentados(representadoDAO.listar());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os representadoes");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			representado = (Representado) evento.getComponent().getAttributes().get("representadoSelecionado");

			RepresentadoDAO representadoDAO = new RepresentadoDAO();
			representadoDAO.excluir(representado);

			representados = representadoDAO.listar();

			Messages.addGlobalInfo("representado removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o representado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		representado = (Representado) evento.getComponent().getAttributes().get("representadoSelecionado");
	}

	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

	public List<Representado> getrepresentados() {
		return representados;
	}

	public void setrepresentados(List<Representado> representados) {
		this.representados = representados;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Representado getRepresentado() {
		return representado;
	}

	public void setRepresentado(Representado representado) {
		this.representado = representado;
	}

	public List<Representado> getRepresentados() {
		return representados;
	}

	public void setRepresentados(List<Representado> representados) {
		this.representados = representados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	
}
