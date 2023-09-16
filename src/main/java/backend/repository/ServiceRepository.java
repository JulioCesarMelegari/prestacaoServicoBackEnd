package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
