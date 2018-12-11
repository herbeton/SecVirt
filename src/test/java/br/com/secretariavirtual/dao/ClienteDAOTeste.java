package br.com.secretariavirtual.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secretariavirtual.dao.ClienteDAO;
import br.com.secretariavirtual.dao.PessoaDAO;
import br.com.secretariavirtual.domain.Cliente;
import br.com.secretariavirtual.domain.Pessoa;

public class ClienteDAOTeste {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);

		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2015"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Cliente salvo com sucesso.");
	}
}
