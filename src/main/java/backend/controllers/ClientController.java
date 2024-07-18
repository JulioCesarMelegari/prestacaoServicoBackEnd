package backend.controllers;


import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.dtos.ClientDto;
import backend.dtos.ClientResponseDto;
import backend.entities.Client;
import backend.repository.ClientRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private ClientRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ClientResponseDto>> findAll(){
		List<Client> list = repository.findAll();
		List<ClientResponseDto> listDto = list
									.stream()
									.map(client -> new ClientResponseDto(client.getId(),client.getName(),client.getCpf(), client.getDateRegister()))
									.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Client> save(@Valid @RequestBody ClientDto clientDto) {
		Client client = new Client();
		client.setName(clientDto.name());
		client.setCpf(clientDto.cpf());
		repository.save(client);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(client);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Client> getClient(@PathVariable Integer id){
		Client client = repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}
	
	public List<Client> getLikeName(@RequestParam(value = "name", required = true) String name){
		return repository.findByName('%' + name + '%');

	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		if(repository.findById(id)!=null) {
			repository.deleteById(id);
			ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}	
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClientDto> update(@PathVariable Integer id, @RequestBody @Valid ClientDto updteClient){
		
		Client client = repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		BeanUtils.copyProperties(updteClient,client);
		
		repository.save(client);
		return ResponseEntity.status(HttpStatus.OK).body(updteClient);

	}

}
