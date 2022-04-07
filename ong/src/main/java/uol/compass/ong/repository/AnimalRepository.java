package uol.compass.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.ong.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

	@Query("select s from Animal s where :especie is null or s.especie = :especie")
	List<Animal> filtro(String especie);

	public List<Animal> findByEspecie(String especie);
}
