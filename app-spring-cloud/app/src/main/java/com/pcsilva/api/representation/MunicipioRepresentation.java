package com.pcsilva.api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class MunicipioRepresentation {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	@JsonProperty("microrregiao")
	private MicrorRegiaoRepresentation microrRegiao;

}
