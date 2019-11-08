package com.pcsilva.rest.infra;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import com.pcsilva.rest.representation.LocalidadesRepresentation;
import com.pcsilva.rest.service.IBGEService;

import lombok.Getter;

@Named
@Eager
@ApplicationScoped
public class IBGEScope implements Serializable {
	
	private static final long serialVersionUID = -4717061677517092358L;

	@Getter
	private List<LocalidadesRepresentation> cidades;
	
	@Inject
	private IBGEService ibgeService;

	@PostConstruct
	public void carregarCidades() {
		
		cidades = ibgeService.listarLocalidades();

	}
	
	public Long getIdCidade(String nomeCidade) {
		
		LocalidadesRepresentation cidade = cidades.stream()
				  .filter(current -> nomeCidade.equals(current.getNomeCidade()))
				  .findAny()
				  .orElse(null);
		
		if(null != cidade) {
			return cidade.getIdCidade();
		}
		return null;
		
	}

	public Long getIdUf(String nomeCidade) {
		LocalidadesRepresentation cidade = cidades.stream()
				  .filter(current -> nomeCidade.equals(current.getNomeCidade()))
				  .findAny()
				  .orElse(null);
		
		if(null != cidade) {
			return cidade.getIdEstado();
		}
		return null;

	}
	
	
}