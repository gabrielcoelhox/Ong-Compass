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

import uol.compass.ong.entities.Endereco;
import uol.compass.ong.entities.dto.EnderecoDTO;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> findAll(){
		List<EnderecoDTO> listEnderecoDTO = enderecoService.findAll();
		return ResponseEntity.ok().body(listEnderecoDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id){
		EnderecoDTO enderecoDTO = enderecoService.findById(id);
		return ResponseEntity.ok().body(enderecoDTO);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> insert(@RequestBody @Valid EnderecoDTO enderecoDTO, UriComponentsBuilder uriComponentsBuilder){
		URI uri = uriComponentsBuilder.path("/enderecos/{id}").buildAndExpand(enderecoDTO.getId_Endereco()).toUri();
		return ResponseEntity.created(uri).body(enderecoService.insert(enderecoDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		enderecoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EnderecoDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Endereco endereco) {
		return ResponseEntity.ok().body(enderecoService.update(id, endereco));
	}
}

