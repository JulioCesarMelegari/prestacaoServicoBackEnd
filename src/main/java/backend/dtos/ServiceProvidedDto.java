package backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ServiceProvidedDto(
		
		@NotEmpty(message = "{campo.descricao.obrigatorio}")
		String description,
		
		@NotEmpty(message = "{campo.valor.obrigatorio}")
		String value,
		
		@NotEmpty(message = "{campo.data.obrigatorio}")
		String date,
		
		@NotNull(message = "{campo.idCliente.obrigatorio}")
		Integer idClient){}
