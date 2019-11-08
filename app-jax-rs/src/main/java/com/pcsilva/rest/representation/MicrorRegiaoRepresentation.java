package com.pcsilva.rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class MicrorRegiaoRepresentation {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	@JsonProperty("mesorregiao")
	private MesoRegiaoRepresentation mesoRegiao;

}
