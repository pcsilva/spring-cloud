package com.pcsilva.rest.infra;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import com.pcsilva.rest.representation.EstadoRepresentation;
import com.pcsilva.rest.representation.LocalidadesRepresentation;
import com.pcsilva.rest.representation.MunicipioRepresentation;
import com.pcsilva.rest.service.IBGEService;

import lombok.Getter;

@Named
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
	
	public LocalidadesRepresentation getCidadeById(Long id) {
		
		for( LocalidadesRepresentation cidade : cidades) {
			
			if(id.equals(cidade.getIdCidade()) ) {
				
				return cidade; //dsasadsa
			}
		}
		
		return null;
		
	}

	public String getUfById(Long id) {
		
		for( LocalidadesRepresentation cidade : cidades) {
			
			if(id.equals(cidade.getIdCidade()) ) {
				
				return cidade.getSiglaEstado();
			}
		}
		
		return null;
		
	}
	
}