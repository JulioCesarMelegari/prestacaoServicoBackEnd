package backend.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClientDto(
		@NotEmpty(message = "{campo.nome.obrigatorio}")
		String name, 
		
		@NotNull(message = "{campo.cpf.obrigatorio}") 
		@CPF(message = "{campo.cpf.invalido}")
		String cpf) {
		
}
