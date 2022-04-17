package uol.compass.ong.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import uol.compass.ong.entities.Animal;
import uol.compass.ong.enums.Porte;
import uol.compass.ong.enums.Sexo;
import uol.compass.ong.enums.StatusAnimal;

@Getter
@Setter
public class AnimalDTO {

	private Long id_animal;
	
	@NotNull
	private Integer idade;
	@NotNull
	@NotEmpty
	private String raca;
	@NotNull
	@NotEmpty
	private String especie;
	
	@Enumerated(EnumType.STRING)
	private StatusAnimal status;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	private Porte porte;

	

	public AnimalDTO() {
	}

	public AnimalDTO(Animal animalObj) {
		this.id_animal = animalObj.getId_animal();
		this.id_animal = animalObj.getId_animal();
		this.sexo = animalObj.getSexo();
		this.porte = animalObj.getPorte();
		this.idade = animalObj.getIdade();
		this.raca = animalObj.getRaca();
		this.especie = animalObj.getEspecie();
		this.status = animalObj.getStatus();
	}
}
