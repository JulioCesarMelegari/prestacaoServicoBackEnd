package backend.dtos;

import java.time.LocalDate;

public record ClientResponseDto(Integer id, String name,String cpf, LocalDate dateRegister ) { }
