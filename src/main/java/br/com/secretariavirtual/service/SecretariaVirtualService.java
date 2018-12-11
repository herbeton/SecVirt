package br.com.secretariavirtual.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

// http://localhost:8080/SecretariaVirtual/rest/SecretariaVirtual
@Path("secretariavirtual")
public class SecretariaVirtualService {
	@GET
	public String exibir(){
		return "Cursos de Hugo Vasconcelos";
	}
}