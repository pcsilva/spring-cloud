package com.pcsilva.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class MesoRegiaoRepresentation {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	@JsonProperty("UF")
	private EstadoRepresentation uf;

}