package uol.compass.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.ong.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long>{

	@Query("select s from Endereco s where :bairro is null or s.bairro = :bairro")
	List<Endereco> filtro(String bairro);
}
