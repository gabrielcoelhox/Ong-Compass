package uol.compass.ong.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.ong.entities.dto.AdocaoDTO;
import uol.compass.ong.services.AdocaoService;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {
	
	@Autowired
	private AdocaoService adocaoService;
	
	@GetMapping
	public ResponseEntity<List<AdocaoDTO>> findAll() {
		return ResponseEntity.ok().body(adocaoService.findAll());
	}
	

}
