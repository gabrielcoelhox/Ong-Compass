package uol.compass.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uol.compass.ong.enums.Status;

import uol.compass.ong.entities.Resgate;

public interface ResgateRepository extends JpaRepository<Resgate, Long> {
	
	@Query("select s from Resgate s where :status is null or s.status = :status")
	List<Resgate> filtro(Status status);
}
