package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Endereco;
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.EnderecoDTO;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.exceptions.DefaultException;
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
		Endereco enderecoObj = enderecoRepository.findById(id)
				.orElseThrow(() -> new DefaultException ("Endereço com id: " + id +  " não encontrado." , "NOT_FOUND", 404));
		return new EnderecoDTO (enderecoObj);	
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
	
	public EnderecoDTO update(Long id, @Valid Endereco endereco) {
		Endereco newEndereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Endereço com id: " + id +  " não encontrado." , "NOT_FOUND", 404));
		newEndereco.setLogradouro(endereco.getLogradouro());
		newEndereco.setCep(endereco.getCep());
		newEndereco.setNumero(endereco.getNumero());
		newEndereco.setComplemento(endereco.getComplemento());
		newEndereco.setBairro(endereco.getBairro());
		newEndereco.setEstado(endereco.getEstado());
		EnderecoDTO enderecoDTO = new EnderecoDTO(newEndereco);
		return enderecoDTO;
	}
	
	public void deleteById(Long id) {
		Endereco enderecoObj = enderecoRepository.findById(id)
				.orElseThrow(() -> new DefaultException ("Endereço com id: " + id +  " não encontrado." , "NOT_FOUND", 404));		
		enderecoRepository.delete(enderecoObj);	
		
}
	
	
	
}
