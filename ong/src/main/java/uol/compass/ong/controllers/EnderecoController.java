package uol.compass.ong.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.ong.entities.dto.EnderecoDTO;
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
}
