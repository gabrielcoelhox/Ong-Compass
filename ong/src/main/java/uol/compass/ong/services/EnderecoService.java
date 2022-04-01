package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Endereco;
import uol.compass.ong.entities.dto.EnderecoDTO;
import uol.compass.ong.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public List<EnderecoDTO> findAll() {
		List<Endereco> list = enderecoRepository.findAll();
		return instanciaListaEnderecoDTO(list);
	}
	
	@Transactional
	public EnderecoDTO findById(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		Endereco enderecoObj = endereco
				.orElseThrow(() -> new EntityNotFoundException("Endereco " + id + " não encontrado."));
		return new EnderecoDTO(enderecoObj);
	}
	
	public static List<EnderecoDTO> instanciaListaEnderecoDTO(List<Endereco> list) {
		List<EnderecoDTO> listDTO = new ArrayList<>();
		for (Endereco endereco : list) {
			EnderecoDTO dto = new EnderecoDTO();
			dto.setId_Endereco(endereco.getId_Endereco());
			dto.setLogradouro(endereco.getLogradouro());
			dto.setCep(endereco.getCep());
			dto.setNumero(endereco.getNumero());
			dto.setComplemento(endereco.getComplemento());
			dto.setBairro(endereco.getBairro());
			dto.setCidade(endereco.getCidade());
			dto.setEstado(endereco.getEstado());
			
			listDTO.add(dto);
		}
		return listDTO;
	}
}
