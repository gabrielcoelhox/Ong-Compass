package uol.compass.ong.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Adocao;
import uol.compass.ong.entities.Animal;
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.AdocaoDTO;
import uol.compass.ong.entities.dto.AdocaoFormDTO;
import uol.compass.ong.repository.AdocaoRepository;
import uol.compass.ong.repository.AnimalRepository;
import uol.compass.ong.repository.UsuarioRepository;

@Service
public class AdocaoService {

	@Autowired
	private AdocaoRepository adocaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AnimalRepository animalRepository;

	public List<AdocaoDTO> findAll() {
		List<Adocao> adocoes = adocaoRepository.findAll();
		return adocoes.stream().map(a -> converter(a)).collect(Collectors.toList());
	}

	private AdocaoDTO converter(Adocao adocao) {
		return new AdocaoDTO(adocao);
	}

	public AdocaoDTO insert(AdocaoFormDTO adocaoFormDTO) {
		Usuario usuario = usuarioRepository.findById(adocaoFormDTO.getId_Usuario()).get(); // tratar Exceção
		Animal animal = animalRepository.findById(adocaoFormDTO.getId_Animal()).get(); // tratar Exceção
		Adocao adocao = new Adocao();
		adocao.setUsuario(usuario);
		adocao.setAnimal(animal);
		adocaoRepository.save(adocao);
		return converter(adocao);
	}

	public AdocaoDTO findById(Long id) {
		Adocao adocao = adocaoRepository.findById(id).get();// tratar Exceção;
		return converter(adocao);
	}

	public void deleteById(Long id) {
		findById(id);
		adocaoRepository.deleteById(id);
	}
	
	public AdocaoDTO update(AdocaoFormDTO adocaoFormDTO, Long id) {
		Adocao adocao = adocaoRepository.findById(id).get();// tratar Exceção;
		Usuario usuario = usuarioRepository.findById(adocaoFormDTO.getId_Usuario()).get(); // tratar Exceção
		Animal animal = animalRepository.findById(adocaoFormDTO.getId_Animal()).get(); // tratar Exceção
		adocao.setUsuario(usuario);
		adocao.setAnimal(animal);
		return converter(adocao);
	}

}