package uol.compass.ong.entities.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import uol.compass.ong.entities.Animal;
import uol.compass.ong.enums.Porte;
import uol.compass.ong.enums.Sexo;

public class AnimalDTO {

	private Long id_animal;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	private Porte porte;
	
	private Integer idade;
	private LocalDate tempo_no_abrigo;
	private String especie;
	
	public AnimalDTO() {}
	
	public AnimalDTO(Animal animalObj) {
		this.id_animal = animalObj.getId_animal();
		this.sexo = animalObj.getSexo();
		this.porte = animalObj.getPorte();
		this.idade = animalObj.getIdade();
		this.tempo_no_abrigo = animalObj.getTempo_no_abrigo();
		this.especie = animalObj.getEspecie();
	}
}
