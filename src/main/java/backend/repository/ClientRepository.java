package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	//@Query(value= "select * from client as c where c.cliente_nome like=:name", nativeQuery = true)
	List<Client> findByName(/*@Param("name")*/ String name);

}
