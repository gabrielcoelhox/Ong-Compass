package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Resgate;
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.exceptions.DefaultException;
import uol.compass.ong.exceptions.SenhaInvalidaException;
import uol.compass.ong.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Lazy
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
	}
	
	public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if(senhasBatem) {
            return user;
        }
        throw new SenhaInvalidaException();
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
	}
	
	Resgate resgate = new Resgate();
	
	@Transactional
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = usuarioRepository.findAll();
		return instanciaListaUsuarioDTO(list);
	}

	@Transactional
	public UsuarioDTO findById(Long id) {
		Usuario usuarioObj = usuarioRepository.findById(id).orElseThrow(
				() -> new DefaultException("Usuario com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		return new UsuarioDTO(usuarioObj);
	}

	public UsuarioDTO insert(@Valid UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO);
		try {
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario);
		} catch (uol.compass.ong.exceptions.MethodArgumentNotValidException e) {
			throw new uol.compass.ong.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}

	public UsuarioDTO update(Long id, @Valid Usuario usuario) {
		Usuario newUsuario = usuarioRepository.findById(id).orElseThrow(
				() -> new DefaultException("Usuario com id: " + id + " não encontrado.", "NOT_FOUND", 404));

		newUsuario.setNome(usuario.getNome());
		newUsuario.setCpf(usuario.getCpf());
		newUsuario.setEmail(usuario.getEmail());
		newUsuario.setIdade(usuario.getIdade());
		newUsuario.setTelefone(usuario.getTelefone());
		newUsuario.setSenha(usuario.getSenha());
		UsuarioDTO usuarioDTO = new UsuarioDTO(newUsuario);
		return usuarioDTO;
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

	public void deleteById(Long id) {
		Usuario usuarioObj = usuarioRepository.findById(id).orElseThrow(
				() -> new DefaultException("Usuario com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		usuarioRepository.delete(usuarioObj);
	}
}
