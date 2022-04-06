package uol.compass.ong.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.ong.entities.dto.AnimalDTO;
import uol.compass.ong.enums.Porte;
import uol.compass.ong.enums.Sexo;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_animal;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	private Porte porte;
	
	private Integer idade;
	private String raca;
	private String especie;
	
	public Animal(AnimalDTO animalDTO) {
		this.idade = animalDTO.getIdade();
		this.sexo = animalDTO.getSexo();
		this.id_animal = animalDTO.getId_animal();
		this.porte = animalDTO.getPorte();
		this.raca = animalDTO.getRaca();
		this.especie = animalDTO.getEspecie();
	}
}
