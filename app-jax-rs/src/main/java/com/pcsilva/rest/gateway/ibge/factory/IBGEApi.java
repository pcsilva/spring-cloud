package com.pcsilva.rest.gateway.ibge.factory;

import java.util.List;

import com.pcsilva.rest.representation.EstadoRepresentation;
import com.pcsilva.rest.representation.MunicipioRepresentation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBGEApi {

	@GET("localidades/estados")
	public Call<List<EstadoRepresentation>> listarEstados();

	@GET("localidades/estados/{uf}/municipios")
	public Call<List<MunicipioRepresentation>> listarMunicipios(@Path("uf") Long id);

}
