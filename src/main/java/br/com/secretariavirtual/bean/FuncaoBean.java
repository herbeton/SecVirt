package br.com.secretariavirtual.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.secretariavirtual.dao.FuncaoDAO;
import br.com.secretariavirtual.domain.Funcao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncaoBean implements Serializable {
	private Funcao funcao;
	private List<Funcao>funcoes;
	
	public Funcao getFuncao() {
		return funcao;
	}
	
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	

	public void novo() {
		funcao = new Funcao();
	}

	public void salvar() {
		try {
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcaoDAO.merge(funcao);

			novo();
			funcoes = funcaoDAO.listar();
			
			Messages.addGlobalInfo("Funcao salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a funcao");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as funcoes");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			funcao = (Funcao) evento.getComponent().getAttributes().get("estadoSelecionado");
	
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcaoDAO.excluir(funcao);
			
			funcoes = funcaoDAO.listar();
	
			Messages.addGlobalInfo("Funcao removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a funcao");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		funcao = (Funcao) evento.getComponent().getAttributes().get("funcaoSelecionada");
	}
}