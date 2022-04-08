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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uol.compass.ong.entities.dto.AdocaoDTO;
import uol.compass.ong.entities.dto.AdocaoFormDTO;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.services.AdocaoService;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

	@Autowired
	private AdocaoService adocaoService;

	@ApiOperation(value= "Retorna uma lista com adoções.")
	@GetMapping
	public ResponseEntity<List<AdocaoDTO>> findAll() {
		return ResponseEntity.ok().body(adocaoService.findAll());
	}

	@ApiOperation(value= "Retorna uma adoção.")
	@GetMapping("/{id}")
	public ResponseEntity<AdocaoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(adocaoService.findById(id));
	}

	@ApiOperation(value= "Insere uma nova adoção.")
	@PostMapping
	@Transactional
	public ResponseEntity<AdocaoDTO> insert(@RequestBody @Valid AdocaoFormDTO adocaoFormDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		URI uri = uriComponentsBuilder.path("/adocoes/{id}").buildAndExpand(adocaoFormDTO.getId_Adocao()).toUri();
		return ResponseEntity.created(uri).body(adocaoService.insert(adocaoFormDTO));
	}
	
	@ApiOperation(value= "Deleta uma adoção.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		adocaoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value= "Atualiza um usuário.")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Atualização realizada com sucesso!",
	            response = AdocaoDTO.class),
	      @ApiResponse(code = 404, message = "Adoção não encontrada.",
	            response = AdocaoDTO.class)
	})
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AdocaoDTO> update(@RequestBody @Valid AdocaoFormDTO adocaoFormDTO, @PathVariable Long id) {
		return ResponseEntity.ok().body(adocaoService.update(adocaoFormDTO, id));
	}


}
