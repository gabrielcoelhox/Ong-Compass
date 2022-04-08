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
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@ApiOperation("Listar todos")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> listUsuarioDTO = usuarioService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}

	@ApiOperation("Encontrar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@ApiOperation("Inserir")
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO inserirUsuario, UriComponentsBuilder uriComponentsBuilder){
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(inserirUsuario.getId_usuario()).toUri();
		return ResponseEntity.created(uri).body(usuarioService.insert(inserirUsuario));
	}

	@ApiOperation("Deletar")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Delete realizado com sucesso",
	            response = UsuarioDTO.class),
	      @ApiResponse(code = 404, message = "Usuário não encontrado",
	            response = UsuarioDTO.class)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		usuarioService.delete(id);
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}


	@ApiOperation("Atualizar")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Atualização realizada com sucesso",
	            response = UsuarioDTO.class),
	      @ApiResponse(code = 404, message = "Usuário não encontrado",
	            response = UsuarioDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.update(id, usuario));
	}

}
