package com.pcsilva.api.config;

import com.pcsilva.api.representation.LocalidadesRepresentation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class IBGEScope implements Serializable {
	
	private static final long serialVersionUID = -4717061677517092358L;

	@Getter @Setter
	private List<LocalidadesRepresentation> cidades;
	
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