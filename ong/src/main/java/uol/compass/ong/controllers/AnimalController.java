package uol.compass.ong.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import uol.compass.ong.dto.AnimalDTO;
import uol.compass.ong.entities.Animal;
import uol.compass.ong.enums.StatusAnimal;
import uol.compass.ong.repository.AnimalRepository;
import uol.compass.ong.services.AnimalService;


@RestController
@RequestMapping("/animais")
public class AnimalController {

	@Autowired
	AnimalService animalService;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@ApiOperation(value= "Retorna uma lista de animais.")
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> findAll(@RequestParam (required = false) StatusAnimal status) {
		return ResponseEntity.ok().body(animalService.findAll(status));
	}

	@ApiOperation(value= "Retorna um único animal.")
	@GetMapping("/{id}")
	public ResponseEntity<AnimalDTO> findById(@PathVariable Long id) {
		AnimalDTO animalDTO = animalService.findById(id);
		return ResponseEntity.ok().body(animalDTO);
	}
	
	@ApiOperation(value= "Salva um animal.")
	@PostMapping
	public ResponseEntity<AnimalDTO> insert(@RequestBody @Valid AnimalDTO animalDTO, UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(animalDTO.getId_animal()).toUri();
		return ResponseEntity.created(uri).body(animalService.insert(animalDTO));
	}
	
	@ApiOperation(value= "Atualiza um animal por seu id.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AnimalDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Animal animal) {
		return ResponseEntity.ok().body(animalService.update(id, animal));
	}
	
	@ApiOperation(value= "Deleta um único animal.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		animalService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value= "Lista todos animais por uma especie.")
	@GetMapping(path = "Especie/{especie}")
	public List<AnimalDTO> getListaAnimais(@PathVariable(value = "especie") String especie) {
		List<AnimalDTO> listAnimais = animalService.findByEspecie(especie);
		return listAnimais;

	}
}
