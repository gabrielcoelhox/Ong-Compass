package uol.compass.ong.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Adocao;
import uol.compass.ong.entities.dto.AdocaoDTO;
import uol.compass.ong.repository.AdocaoRepository;

@Service
public class AdocaoService {
	
	@Autowired
	private AdocaoRepository adocaoRepository;

	public List<AdocaoDTO> findAll() {
		List<Adocao> adocoes = adocaoRepository.findAll();
		return adocoes.stream().map(a -> converter(a)).collect(Collectors.toList());
	}
	
	private AdocaoDTO converter(Adocao adocao) {
		return new AdocaoDTO(adocao);
	}
}
