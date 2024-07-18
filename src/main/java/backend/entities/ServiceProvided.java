package backend.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ServiceProvided{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "servico_id")
	private Integer id;
	
	@Column(nullable = false, length = 150, name = "servico_descricao")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;
	
	@Column(name = "valor_servico")
	private BigDecimal value;
	
	@Column(name = "data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

}
