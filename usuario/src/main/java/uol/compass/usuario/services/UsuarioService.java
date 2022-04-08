package uol.compass.usuario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uol.compass.usuario.controller.dto.AnimalDTO;

@Service
public class UsuarioService {

	public List<AnimalDTO> getAnimal(String especie) {

		RestTemplate usuario = new RestTemplate();
		ResponseEntity<AnimalDTO[]> exchange = usuario.exchange("http://localhost:8080/animais/Especie/" + especie,
				HttpMethod.GET, null, AnimalDTO[].class);

		List<AnimalDTO> list = new ArrayList<>();
		Stream.of(exchange.getBody()).forEach(animal -> list.add(animal));

		return list;

	}

}
