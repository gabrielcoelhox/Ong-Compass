package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.exceptions.DefaultException;
import uol.compass.ong.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = usuarioRepository.findAll();
		return instanciaListaUsuarioDTO(list);
	}
	
	@Transactional
	public UsuarioDTO findById(Long id) {
		Usuario usuarioObj = usuarioRepository.findById(id)
				.orElseThrow(() -> new DefaultException ("Usuario com id: " + id +  " não encontrado." , "NOT_FOUND", 404));
		return new UsuarioDTO (usuarioObj);
		
	}

	public static List<UsuarioDTO> instanciaListaUsuarioDTO(List<Usuario> list) {
		List<UsuarioDTO> listDTO = new ArrayList<>();
		for (Usuario usuario : list) {
			UsuarioDTO dto = new UsuarioDTO();
			dto.setId_usuario(usuario.getId_Usuario());
			dto.setNome(usuario.getNome());
			dto.setCpf(usuario.getCpf());
			dto.setEmail(usuario.getEmail());
			dto.setIdade(usuario.getIdade());
			dto.setTelefone(usuario.getTelefone());
			dto.setSenha(usuario.getSenha());

			listDTO.add(dto);
    }
		return listDTO;
	}
			
	public UsuarioDTO update(Long id, @Valid Usuario usuario) {
		Usuario newUsuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Usuario com id: " + id +  " não encontrado." , "NOT_FOUND", 404));
		newUsuario.setNome(usuario.getNome());
		newUsuario.setCpf(usuario.getCpf());
		newUsuario.setEmail(usuario.getEmail());
		newUsuario.setIdade(usuario.getIdade());
		newUsuario.setTelefone(usuario.getTelefone());
		newUsuario.setSenha(usuario.getSenha());
		UsuarioDTO usuarioDTO = new UsuarioDTO(newUsuario);
		return usuarioDTO;
	}
	
	public void deleteById(Long id) {
		Usuario usuarioObj = usuarioRepository.findById(id)
				.orElseThrow(() -> new DefaultException ("Usuario com id: " + id +  " não encontrado." , "NOT_FOUND", 404));		
		usuarioRepository.delete(usuarioObj);	
		
	}
}
