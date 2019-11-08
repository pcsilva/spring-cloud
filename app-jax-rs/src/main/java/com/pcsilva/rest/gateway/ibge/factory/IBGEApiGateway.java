package com.pcsilva.rest.gateway.ibge.factory;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.pcsilva.rest.exception.ServicoIndisponivelException;
import com.pcsilva.rest.gateway.ibge.producer.RetrofitIBGEApi;
import com.pcsilva.rest.representation.EstadoRepresentation;
import com.pcsilva.rest.representation.MunicipioRepresentation;

import retrofit2.Response;
import retrofit2.Retrofit;

public class IBGEApiGateway implements Serializable {

	private static final long serialVersionUID = 2518169418096982719L;
	
	@Inject
	@RetrofitIBGEApi
	private Retrofit retrofitIBGEApi;

	public List<EstadoRepresentation> listarEstados() throws ServicoIndisponivelException {

		IBGEApi ibgeApi = retrofitIBGEApi.create(IBGEApi.class);

		Response<List<EstadoRepresentation>> executado = null;
		
		try {
			
			executado = ibgeApi.listarEstados().execute();
			
		}
		catch (IOException e) {
			
			throw new ServicoIndisponivelException("Servidor indisponivel : " + e.getMessage());
			
		}
		if (executado.isSuccessful()) {
			
			return executado.body();
			
		}
		return null;

	}

	public List<MunicipioRepresentation> listarMunicipios(Long uf) {
		
		IBGEApi ibgeApi = retrofitIBGEApi.create(IBGEApi.class);

		Response<List<MunicipioRepresentation>> executado = null;
		
		try {
			
			executado = ibgeApi.listarMunicipios(uf).execute();
			
		}
		catch (IOException e) {
			
			throw new ServicoIndisponivelException("Servidor indisponivel : " + e.getMessage());
			
		}
		if (executado.isSuccessful()) {
			
			return executado.body();
			
		}
		return null;
		
	}
	
	

}
