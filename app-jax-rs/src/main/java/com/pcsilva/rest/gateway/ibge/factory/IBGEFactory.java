package com.pcsilva.rest.gateway.ibge.factory;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.pcsilva.rest.exception.ServicoIndisponivelException;
import com.pcsilva.rest.representation.EstadoRepresentation;
import com.pcsilva.rest.representation.MunicipioRepresentation;

public class IBGEFactory implements Serializable {

	private static final long serialVersionUID = 7245793151364893692L;

	@Inject
	private IBGEApiGateway usuariosApiGateway;

	public List<EstadoRepresentation> listarEstados() throws ServicoIndisponivelException {

		return usuariosApiGateway.listarEstados();

	}

	public List<MunicipioRepresentation> listarMunicipios(Long uf) throws ServicoIndisponivelException {
		
		return usuariosApiGateway.listarMunicipios(uf);
		
	}
}
