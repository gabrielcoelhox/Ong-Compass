package uol.compass.ong.services.tests;

import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.repository.UsuarioRepository;
import uol.compass.ong.services.UsuarioService;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	private Optional<Usuario> optionalUsuario;


	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveriaDeletarComSucessoQuandoIdExistir() {

		Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(instanciaOptionalUsuario(1L));
		Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyLong());
		usuarioService.deleteById(1L);

		Mockito.verify(usuarioRepository, times(1)).delete(instanciaUsuario(1L));

	}
	
	@Test
	void deveriaBuscarUmaListaComTodosUsuarios() {
		Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(instanciaUsuario(1L)));
		List<UsuarioDTO> listUsuarioDTO = usuarioService.findAll();
		
		Assertions.assertNotNull(listUsuarioDTO);
		listUsuarioDTO.stream().forEach(usuarioDTO -> {
			Assertions.assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
			Assertions.assertEquals("UsuarioTeste", usuarioDTO.getNome());
			Assertions.assertEquals("000.111.222-02", usuarioDTO.getCpf());
			Assertions.assertEquals("usuarioteste@teste.com", usuarioDTO.getEmail());
			Assertions.assertEquals(30, usuarioDTO.getIdade());
			Assertions.assertEquals("123teste", usuarioDTO.getSenha());
			Assertions.assertEquals("081955666777", usuarioDTO.getTelefone());
		});
		
		
	}
	
	@ParameterizedTest
	@ValueSource(longs = {1L, 2L, 3L})
	void deveriaBuscarUsuarioComIdExistente(Long id) {
		Mockito.when(usuarioRepository.findById(id)).thenReturn(instanciaOptionalUsuario(id));
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		
		Assertions.assertNotNull(usuarioDTO);
		Assertions.assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		Assertions.assertEquals(id, usuarioDTO.getId_usuario());
		Assertions.assertEquals("UsuarioTeste", usuarioDTO.getNome());
		Assertions.assertEquals("000.111.222-02", usuarioDTO.getCpf());
		Assertions.assertEquals("usuarioteste@teste.com", usuarioDTO.getEmail());
		Assertions.assertEquals(30, usuarioDTO.getIdade());
		Assertions.assertEquals("123teste", usuarioDTO.getSenha());
		Assertions.assertEquals("081955666777", usuarioDTO.getTelefone());
		
	}

	@Test
	void deveriaSalvarUmNovoUsuario() {

		Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(instanciaUsuario(1L));

		UsuarioDTO usuario = usuarioService.insert(instanciaUsuarioDTO());

		Assertions.assertNotNull(usuario);
		Assertions.assertEquals(UsuarioDTO.class, usuario.getClass());
		Assertions.assertEquals(1L, usuario.getId_usuario());
		Assertions.assertEquals("UsuarioTeste", usuario.getNome());
		Assertions.assertEquals("000.111.222-02", usuario.getCpf());
		Assertions.assertEquals("usuarioteste@teste.com", usuario.getEmail());
		Assertions.assertEquals(30, usuario.getIdade());
		Assertions.assertEquals("123teste", usuario.getSenha());
		Assertions.assertEquals("081955666777", usuario.getTelefone());

	}
	
	
	private Usuario instanciaUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId_Usuario(id);
		usuario.setNome("UsuarioTeste");
		usuario.setCpf("000.111.222-02");
		usuario.setEmail("usuarioteste@teste.com");
		usuario.setIdade(30);
		usuario.setSenha("123teste");
		usuario.setTelefone("081955666777");
		return usuario;
	}

	private UsuarioDTO instanciaUsuarioDTO() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId_usuario(1L);
		usuarioDTO.setNome("UsuarioTeste");
		usuarioDTO.setCpf("000.111.222-02");
		usuarioDTO.setEmail("usuarioteste@teste.com");
		usuarioDTO.setIdade(30);
		usuarioDTO.setSenha("123teste");
		usuarioDTO.setTelefone("081955666777");
		return usuarioDTO;
	}

	private Optional<Usuario> instanciaOptionalUsuario(Long id) {
		return Optional.of(instanciaUsuario(id));
	}

}
