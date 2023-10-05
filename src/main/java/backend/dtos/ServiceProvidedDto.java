package backend.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;

public record ServiceProvidedDto(
		
		@NotEmpty(message = "{campo.descricao.obrigatorio}")
		String description,
		@NotEmpty(message = "{campo.valor.obrigatorio}")
		String value,
		@NotEmpty(message = "{campo.data.obrigatorio}")
		String date,
		@NotEmpty(message = "{campo.idCliente.obrigatorio}")
		Integer idClient		
		) implements Serializable{}
