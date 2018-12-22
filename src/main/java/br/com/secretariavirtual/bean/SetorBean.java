package br.com.secretariavirtual.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.secretariavirtual.dao.PessoaDAO;
import br.com.secretariavirtual.dao.SetorDAO;
import br.com.secretariavirtual.domain.Estado;
import br.com.secretariavirtual.domain.Pessoa;
import br.com.secretariavirtual.domain.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SetorBean implements Serializable {
	private Setor setor;
	private List<Setor> setores;
	private List<Pessoa> pessoas;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public void novo() {
		try {
			setor = new Setor();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		}
		catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma novo Setor");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			PessoaDAO pessoaDAO = new PessoaDAO();
			setorDAO.merge(setor);

			novo();
			setores = setorDAO.listar();
			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Setor salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar(){
		try{
			SetorDAO setorDAO = new SetorDAO();
			setSetores(setorDAO.listar());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os setores");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionado");

			SetorDAO setorDAO = new SetorDAO();
			setorDAO.excluir(setor);
			
			setores = setorDAO.listar();

			Messages.addGlobalInfo("Setor removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o setor");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionado");
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	
}
