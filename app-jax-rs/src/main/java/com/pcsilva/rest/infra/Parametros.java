package com.pcsilva.rest.infra;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import lombok.Getter;

@Named
@Eager
@ApplicationScoped
public class Parametros implements Serializable {
	
	private static final long serialVersionUID = -574281853308559516L;

	@Getter
	private String ambiente;
	
	@Getter
	private String ibgeApi;

	
	@PostConstruct
	public void carregarParametros() {
		
		Map<String, String> variaveis = System.getenv();
		
		this.ambiente = variaveis.get("AMBIENTE");

		this.ibgeApi = variaveis.get("IBGE_API");
		
	}
	
}