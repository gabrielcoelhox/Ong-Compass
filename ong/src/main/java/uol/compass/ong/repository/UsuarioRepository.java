package uol.compass.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.ong.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select s from Usuario s where :cpf is null or s.cpf = :cpf")
	List<Usuario> filtro(String cpf);
}
