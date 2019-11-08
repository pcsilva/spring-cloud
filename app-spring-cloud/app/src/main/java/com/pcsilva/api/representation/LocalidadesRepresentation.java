package com.pcsilva.api.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class LocalidadesRepresentation {

	@Getter
	@Setter
	private Long idEstado;

	@Getter
	@Setter
	private String siglaEstado;

	@Getter
	@Setter
	private String regiaoNome;

	@Getter
	@Setter
	private String nomeCidade;

	@Getter
	@Setter
	private String nomeMesorregiao;

	public String getNomeFormatado() {

		return nomeCidade + "/" + siglaEstado;

	}

	@Getter
	@Setter
	@JsonIgnoreProperties
	private Long idCidade;

	@JsonIgnore
	public String toString() {
		return idEstado+",'"+siglaEstado+"','"+regiaoNome+"','"+nomeCidade+"','"+nomeMesorregiao+"','"+getNomeFormatado()+"'";
	}

}
