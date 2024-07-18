package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entities.ServiceProvided;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Integer> {
	
	@Query(value= "select * from service_provided where month (sp.data_cadastro)=:month", nativeQuery = true)
	List<ServiceProvided> findByMonth(@Param("month") Integer month);
			
		
	@Query(value= "select c.name , sp.data_cadastro as service_provided from service_provided sp"
			+ "inner join client c on c.id = sp.client_id"
			+ "where upper(c.name) like upper(:name) and month(sp.data_cadastro) =:month", nativeQuery = true)
	List<ServiceProvided> findByNameMonth(@Param("month") Integer month, @Param("name") String name);

}
