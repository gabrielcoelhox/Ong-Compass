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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.ong.entities.Animal;
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.AnimalDTO;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.services.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalController {

	@Autowired
	AnimalService animalService;
	
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> findAll() {
		List<AnimalDTO> listAnimalDTO = animalService.findAll();
		return ResponseEntity.ok().body(listAnimalDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnimalDTO> findById(@PathVariable Long id) {
		AnimalDTO animalDTO = animalService.findById(id);
		return ResponseEntity.ok().body(animalDTO);
	}
	
	@PostMapping
	public ResponseEntity<AnimalDTO> insert(@RequestBody @Valid AnimalDTO animalDTO, UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(animalDTO.getId_animal()).toUri();
		return ResponseEntity.created(uri).body(animalService.insert(animalDTO));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AnimalDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Animal animal) {
		return ResponseEntity.ok().body(animalService.update(id, animal));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		animalService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
