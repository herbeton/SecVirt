package br.com.secretariavirtual.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.OutputStream;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.secretariavirtual.dao.FornecedorDAO;
import br.com.secretariavirtual.dao.UsuarioDAO;
import br.com.secretariavirtual.dao.EntradaArquivoDAO;
import br.com.secretariavirtual.domain.Fornecedor;
import br.com.secretariavirtual.domain.Usuario;
import br.com.secretariavirtual.domain.EntradaArquivo;
import br.com.secretariavirtual.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EntradaArquivoBean implements Serializable {
	private EntradaArquivo entradaArquivo;
	private List<EntradaArquivo> EntradaArquivos;
	private List<Fornecedor> fornecedor;
	private List<Usuario> usuarios;
	
	public EntradaArquivo getEntradaArquivo() {
		return entradaArquivo;
	}

	public void setEntradaArquivo(EntradaArquivo EntradaArquivo) {
		this.entradaArquivo = EntradaArquivo;
	}

	public List<EntradaArquivo> getEntradaArquivos() {
		return EntradaArquivos;
	}

	public void setEntradaArquivos(List<EntradaArquivo> EntradaArquivos) {
		this.EntradaArquivos = EntradaArquivos;
	}

	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			EntradaArquivoDAO EntradaArquivoDAO = new EntradaArquivoDAO();
			EntradaArquivos = EntradaArquivoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os EntradaArquivos");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			entradaArquivo = new EntradaArquivo();

			FornecedorDAO fabricanteDAO = new FornecedorDAO();
			fornecedor = fabricanteDAO.listar();
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo EntradaArquivo");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
			entradaArquivo = (EntradaArquivo) evento.getComponent().getAttributes().get("EntradaArquivoSelecionado");
			entradaArquivo.setCaminho("C:/Users/Herbeton/git/SecVirt/uploads/" + 
					entradaArquivo.getCodigo() + ".jpg");

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedor = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um EntradaArquivo");
			erro.printStackTrace();
		}	
	}
	
	public void salvar() {
		try {
			
			if(entradaArquivo.getCaminho() == null) {
				Messages.addGlobalInfo("A imagem eh obrigatoria!");
				return;
			}
			EntradaArquivoDAO EntradaArquivoDAO = new EntradaArquivoDAO();
			EntradaArquivo EntradaArquivoRetorno = EntradaArquivoDAO.merge(entradaArquivo);
			
			Path origem = Paths.get(entradaArquivo.getCaminho());
			Path destino = Paths.get("C:/Users/Herbeton/git/SecVirt/uploads/" + 
			EntradaArquivoRetorno.getCodigo() + ".jpg");
			
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			entradaArquivo = new EntradaArquivo();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedor = fornecedorDAO.listar();

			EntradaArquivos = EntradaArquivoDAO.listar();

			Messages.addGlobalInfo("EntradaArquivo salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o EntradaArquivo");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			entradaArquivo = (EntradaArquivo) evento.getComponent().getAttributes().get("EntradaArquivoSelecionado");

			EntradaArquivoDAO EntradaArquivoDAO = new EntradaArquivoDAO();
			EntradaArquivoDAO.excluir(entradaArquivo);
			
			Path arquivo = Paths.get("C:/Users/Herbeton/git/SecVirt/uploads/" + 
					entradaArquivo.getCodigo() + ".jpg");
			Files.deleteIfExists(arquivo);

			EntradaArquivos = EntradaArquivoDAO.listar();

			Messages.addGlobalInfo("EntradaArquivo removido com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalInfo("Ocorreu um erro ao tentar remover o EntradaArquivo");
			erro.printStackTrace();
		}
	}
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			entradaArquivo.setCaminho(arquivoTemp.toString());
			Messages.addGlobalInfo("Imagem salva com sucesso!");
			
		} catch (IOException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar carregar a imagem");
			e.printStackTrace();
		}
	}
	
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String proDescricao = (String) filtros.get("descricao");
			String forDescricao = (String) filtros.get("fornecedor.descricao");

			String caminho = Faces.getRealPath("/reports/EntradaArquivo.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (proDescricao == null) {
				parametros.put("EntradaArquivo_DESCRICAO", "%%");
			} else {
				parametros.put("EntradaArquivo_DESCRICAO", "%" + proDescricao + "%");
			}
			if (forDescricao == null) {
				parametros.put("FORNECEDOR_DESCRICAO", "%%");
			} else {
				parametros.put("FORNECEDOR_DESCRICAO", "%" + forDescricao + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	public void download(ActionEvent evento) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
            externalContext.setResponseContentType("image/jpg");
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"image.jpg\"");
            
            entradaArquivo = (EntradaArquivo) evento.getComponent().getAttributes().get("EntradaArquivoSelecionado");
            FileInputStream inputStream = new FileInputStream(new File(Paths.get("C:/Users/Herbeton/git/SecVirt/uploads/" + 
					entradaArquivo.getCodigo() + ".jpg").toString()));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}