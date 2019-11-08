package com.pcsilva.rest.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.pcsilva.rest.exception.ServicoIndisponivelException;
import com.pcsilva.rest.gateway.ibge.factory.IBGEFactory;
import com.pcsilva.rest.representation.EstadoRepresentation;
import com.pcsilva.rest.representation.LocalidadesRepresentation;
import com.pcsilva.rest.representation.MunicipioRepresentation;

public class IBGEService implements Serializable {

	private static final long serialVersionUID = -1381708555612104911L;

	@Inject
	private IBGEFactory ibgeFactory;

	public List<LocalidadesRepresentation> listarLocalidades()  throws ServicoIndisponivelException {
		
		List<EstadoRepresentation> estados = ibgeFactory.listarEstados();
		
		List<LocalidadesRepresentation> localidades = new ArrayList<LocalidadesRepresentation>();
		
		for( EstadoRepresentation estado : estados) {
			
			List<MunicipioRepresentation> municipios = ibgeFactory.listarMunicipios(estado.getId());
			
			for(MunicipioRepresentation municipio : municipios) {
				
				LocalidadesRepresentation localidade = new LocalidadesRepresentation(
						estado.getId(),
						estado.getSigla(),
						estado.getRegiao().getNome(),
						municipio.getNome(),
						municipio.getMicrorRegiao().getMesoRegiao().getNome(),
						municipio.getId());
				
				localidades.add( localidade );
				
			}
			
			
		}

		return localidades;
		
	}
	

	

}
