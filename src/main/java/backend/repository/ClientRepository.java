package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
