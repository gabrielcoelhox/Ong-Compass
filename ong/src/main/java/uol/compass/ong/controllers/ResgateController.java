package uol.compass.ong.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.ong.entities.Resgate;
import uol.compass.ong.entities.dto.ResgateDTO;
import uol.compass.ong.enums.Status;
import uol.compass.ong.repository.ResgateRepository;
import uol.compass.ong.services.ResgateService;

@Repository
@RequestMapping("/resgates")
public class ResgateController {

	@Autowired
	ResgateService resgateService;
	
	@Autowired
	private ResgateRepository resgateRepository;
	
	@GetMapping
	public ResponseEntity<List<Resgate>> findAll(@RequestParam (required = false) Status status) {
		List<Resgate> listResgate = resgateRepository.filtro(status);
		return ResponseEntity.ok().body(listResgate);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResgateDTO> findById(@PathVariable Long id) {
		ResgateDTO resgateDTO = resgateService.findById(id);
		return ResponseEntity.ok().body(resgateDTO);
	}
	
	@PostMapping
	public ResponseEntity<ResgateDTO> insert(@RequestBody @Valid ResgateDTO resgateDTO, UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(resgateDTO.getId_resgate()).toUri();
		return ResponseEntity.created(uri).body(resgateService.insert(resgateDTO));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ResgateDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Resgate resgate) {
		return ResponseEntity.ok().body(resgateService.update(id, resgate));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		resgateService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
