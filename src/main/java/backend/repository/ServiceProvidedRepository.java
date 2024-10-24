package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entities.ServiceProvided;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Integer> {
	
	@Query(value= "select * from service_provided where month (sp.data_cadastro)=:month", nativeQuery = true)
	List<ServiceProvided> findByMonth(@Param("month") Integer month);
				
	@Query(value= "SELECT sp.servico_descricao, sp.data_cadastro FROM service_provided sp WHERE MONTH(sp.data_cadastro) = :mes AND sp.cliente_id = :clientId", nativeQuery = true)
	List<ServiceProvided> findByNameMonth(@Param("clientId") Integer clientId, @Param("mes") Integer mes);

}
