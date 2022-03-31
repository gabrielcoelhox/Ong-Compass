package uol.compass.ong.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> listUsuarioDTO = usuarioService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.update(id, usuario));
	}

}
