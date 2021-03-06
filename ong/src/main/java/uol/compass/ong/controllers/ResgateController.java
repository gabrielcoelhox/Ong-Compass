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

import io.swagger.annotations.ApiOperation;
import uol.compass.ong.dto.ResgateDTO;
import uol.compass.ong.entities.Resgate;
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

	@ApiOperation(value = "Retorna uma lista com resgates e seu status.")
	@GetMapping
	public ResponseEntity<List<Resgate>> findAll(@RequestParam(required = false) Status status) {
		List<Resgate> listResgate = resgateRepository.filtro(status);
		return ResponseEntity.ok().body(listResgate);
	}

	@ApiOperation(value = "Retorna um único resgate.")
	@GetMapping("/{id}")
	public ResponseEntity<ResgateDTO> findById(@PathVariable Long id) {
		ResgateDTO resgateDTO = resgateService.findById(id);
		return ResponseEntity.ok().body(resgateDTO);
	}

	@ApiOperation(value = "Insere um novo resgate.")
	@PostMapping
	public ResponseEntity<ResgateDTO> insert(@RequestBody @Valid ResgateDTO resgateDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(resgateDTO.getId_resgate()).toUri();
		return ResponseEntity.created(uri).body(resgateService.insert(resgateDTO));
	}

	@ApiOperation(value = "Atualiza um resgate.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ResgateDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Resgate resgate) {
		return ResponseEntity.ok().body(resgateService.update(id, resgate));
	}

	@ApiOperation(value = "Exclui um resgate registrado.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		resgateService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
