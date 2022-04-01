package uol.compass.ong.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import uol.compass.ong.entities.Animal;
import uol.compass.ong.enums.Porte;
import uol.compass.ong.enums.Sexo;

@Getter
@Setter
public class AnimalDTO {

	private Long id_animal;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	private Porte porte;
	
	private Integer idade;
	private String especie;
	
	public AnimalDTO() {}
	
	public AnimalDTO(Animal animalObj) {
		this.id_animal = animalObj.getId_animal();
		this.sexo = animalObj.getSexo();
		this.porte = animalObj.getPorte();
		this.idade = animalObj.getIdade();
		this.especie = animalObj.getEspecie();
	}
}
