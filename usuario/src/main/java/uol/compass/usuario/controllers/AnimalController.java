package uol.compass.usuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.usuario.controller.dto.AnimalDTO;
import uol.compass.usuario.services.UsuarioService;

@RestController
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/{especie}")
	public ResponseEntity<List<AnimalDTO>> consultaAnimal(@PathVariable(value = "especie") String especie) {

		return ResponseEntity.ok(usuarioService.getAnimal(especie));
	}

}
