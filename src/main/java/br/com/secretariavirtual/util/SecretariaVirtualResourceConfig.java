package br.com.secretariavirtual.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/SecretariaVirtual/rest
@ApplicationPath("rest")
public class SecretariaVirtualResourceConfig extends ResourceConfig {
	public SecretariaVirtualResourceConfig(){
		packages("br.com.secretariavirtual.service");
	}
}