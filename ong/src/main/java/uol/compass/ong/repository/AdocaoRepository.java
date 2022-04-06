package uol.compass.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uol.compass.ong.entities.Adocao;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

}
