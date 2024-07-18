package backend.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Integer id;
	
	@Column(nullable = false, length = 150, name = "cliente_nome")
	private String name;
	
	@CPF(message = "{campo.cpf.invalido}") 
	@Column(nullable = false, length = 11, name = "cliente_cpf")
	private String cpf;
	
	@Column(name = "cliente_data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateRegister;
	
	public Client() {}
		
	public Client(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public Client(Integer id, String name, String cpf, LocalDate dateRegister) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.dateRegister = dateRegister;
	}
	
	@PrePersist
	public void prePersist() {
		setDateRegister(LocalDate.now());
	}
}
