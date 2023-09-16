package backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.dtos.ClientDto;
import backend.entities.Client;
import backend.repository.ClientRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ClientDto>> findAll(){
		List<Client> list = repository.findAll();
		List<ClientDto> listDto = list
									.stream()
									.map(client -> new ClientDto(client.getName(),client.getCpf()))
									.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDto> save(@RequestBody @Valid ClientDto client) {
		repository.save(new Client(client.name(), client.cpf()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(client);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClientDto> getClient(@PathVariable Integer id){
		Client client = repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
		ClientDto clientDto = new ClientDto(client.getName(), client.getCpf());
		return ResponseEntity.status(HttpStatus.OK).body(clientDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		if(repository.findById(id).isPresent()) {
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
