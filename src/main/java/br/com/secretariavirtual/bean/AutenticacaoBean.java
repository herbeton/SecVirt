package br.com.secretariavirtual.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.secretariavirtual.dao.UsuarioDAO;
import br.com.secretariavirtual.domain.Pessoa;
import br.com.secretariavirtual.domain.Usuario;

@ManagedBean 
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			if(usuarioLogado == null) {
				Messages.addGlobalInfo("CPF ou senha incorretos!");
				return;
			}
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean temPermissoes(List<String>permissoes) {
		for(String permisao : permissoes) {
			if(usuarioLogado.getTipo() == permisao.charAt(0)) {
				return true;
			}
		}
		return false;
	}
	
}
