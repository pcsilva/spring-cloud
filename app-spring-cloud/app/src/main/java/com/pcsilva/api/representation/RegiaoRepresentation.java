package com.pcsilva.api.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RegiaoRepresentation {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String sigla;

	@Getter
	@Setter
	private String nome;

}
