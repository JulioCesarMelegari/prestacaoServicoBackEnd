package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entities.ServiceProvided;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Integer> {

}
