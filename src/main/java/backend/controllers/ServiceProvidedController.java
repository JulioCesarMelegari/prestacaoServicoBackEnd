package backend.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.dtos.ServiceProvidedDto;
import backend.entities.Client;
import backend.entities.ServiceProvided;
import backend.repository.ClientRepository;
import backend.repository.ServiceProvidedRepository;
import backend.util.BigDecimalConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequestMapping("/servicos")
@RequiredArgsConstructor
@RestController
public class ServiceProvidedController {
	
	private final ClientRepository clientRepository;
	private final ServiceProvidedRepository serviceRepository;
	private final BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceProvided salvar(@RequestBody @Valid ServiceProvidedDto dto ) {
		
		Integer idClient = dto.idClient();
		Client client = clientRepository.findById(idClient)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente inexistente."));
		
		LocalDate date =  LocalDate.parse(dto.date(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		ServiceProvided serviceProvided = new ServiceProvided();
		serviceProvided.setDescription(dto.description());
		serviceProvided.setDate(date);
		serviceProvided.setClient(client);
		serviceProvided.setValue(bigDecimalConverter.converter(dto.value()));
		return serviceRepository.save(serviceProvided);	
	}
	
	@GetMapping
	public List<ServiceProvided> serviceByMonth (@RequestParam(value = "month", required = false) Integer month){
		return serviceRepository.findByMonth(month);
	}

}
