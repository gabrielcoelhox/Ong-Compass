package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.dto.AnimalDTO;
import uol.compass.ong.entities.Animal;
import uol.compass.ong.enums.StatusAnimal;
import uol.compass.ong.exceptions.DefaultException;
import uol.compass.ong.repository.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Transactional
	public List<AnimalDTO> findAll(StatusAnimal status) {
		List<AnimalDTO> listAnimalDTO = animalRepository.filtro(status).stream().map(AnimalDTO::new).collect(Collectors.toList());
		return listAnimalDTO;
	}
	
	@Transactional
	public AnimalDTO findById(Long id) {
		Animal animalObj = animalRepository.findById(id).orElseThrow(
				() -> new DefaultException("Animal com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		return new AnimalDTO(animalObj);

	}
	
	public AnimalDTO insert(@Valid AnimalDTO animalDTO) {
		Animal animal = new Animal(animalDTO);
		try {
			animalRepository.save(animal);
			return new AnimalDTO(animal);
		}catch(uol.compass.ong.exceptions.MethodArgumentNotValidException e) {
			throw new uol.compass.ong.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public AnimalDTO update(Long id, @Valid Animal animal) {
		Animal an = animalRepository.findById(id).orElseThrow(
				() -> new DefaultException("Animal com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		an.setEspecie(animal.getEspecie());
		an.setRaca(animal.getRaca());
		an.setIdade(animal.getIdade());
		an.setPorte(animal.getPorte());
		an.setSexo(animal.getSexo());

		AnimalDTO animalDTO = new AnimalDTO(an);
		return animalDTO;
	}
	
	public void delete(Long id) {
		try {
			findById(id);
			animalRepository.deleteById(id);
		} catch (uol.compass.ong.exceptions.MethodArgumentNotValidException e) {
			throw new uol.compass.ong.exceptions.MethodArgumentNotValidException(e.getMessage());
		} 
	}
	
	public void deleteById(Long id) {
		Animal animalObj = animalRepository.findById(id).orElseThrow(
				() -> new DefaultException("Animal com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		animalRepository.delete(animalObj);
	}
	
	@Transactional
	public List<AnimalDTO> findByEspecie(String especie) {
		try {
			List<Animal> list = animalRepository.findByEspecie(especie.toLowerCase());
			return instanciaListaAnimalDTO(list);
		} catch (uol.compass.ong.exceptions.MethodArgumentNotValidException e) {
			throw new uol.compass.ong.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public static List<AnimalDTO> instanciaListaAnimalDTO(List<Animal> list) {
		List<AnimalDTO> listDTO = new ArrayList<>();
		for (Animal animal : list) {
			AnimalDTO dto = new AnimalDTO();
			dto.setId_animal(animal.getId_animal());
			dto.setSexo(animal.getSexo());
			dto.setPorte(animal.getPorte());
			dto.setIdade(animal.getIdade());
			dto.setEspecie(animal.getEspecie());
			dto.setRaca(animal.getRaca());
			dto.setStatus(animal.getStatus());

			listDTO.add(dto);
		}
		return listDTO;
	}
}
