package uol.compass.ong.usuario.services.tests;

import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
//@SpringBootTest
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	private Optional<Usuario> optionalUsuario;

	@Mock
	private Usuario usuario;

	@Mock
	private UsuarioDTO UsuarioDTO;

	@Mock
	private UsuarioDTO usuarioDTO;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		instanciaUsuario();
		instanciaOptionalUsuario();
		instanciaUsuarioDTO();

	}

	@Test
	void deveriaDeletarComSucessoQuandoIdExistir() {

		Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(optionalUsuario);
		Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyLong());
		usuarioService.deleteById(1L);

		Mockito.verify(usuarioRepository, times(1)).delete(usuario);

	}
	
	@Test
	void deveriaBuscarUmaListaComTodosUsuarios() {
		Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
		List<UsuarioDTO> listUsuarioDTO = usuarioService.findAll();
		
		Assertions.assertNotNull(listUsuarioDTO);
		Assertions.assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		Assertions.assertEquals(1L, usuario.getId_Usuario());
		Assertions.assertEquals("UsuarioTeste", usuario.getNome());
		Assertions.assertEquals("000.111.222-02", usuario.getCpf());
		Assertions.assertEquals("usuarioteste@teste.com", usuario.getEmail());
		Assertions.assertEquals(30, usuario.getIdade());
		Assertions.assertEquals("123teste", usuario.getSenha());
		Assertions.assertEquals("081955666777", usuario.getTelefone());
		
		
	}
	
	@Test
	void deveriaBuscarUsuarioComIdExistente() {
		Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(optionalUsuario);
		UsuarioDTO usuarioDTO = usuarioService.findById(1L);
		
		Assertions.assertNotNull(usuarioDTO);
		Assertions.assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		Assertions.assertEquals(1L, usuario.getId_Usuario());
		Assertions.assertEquals("UsuarioTeste", usuario.getNome());
		Assertions.assertEquals("000.111.222-02", usuario.getCpf());
		Assertions.assertEquals("usuarioteste@teste.com", usuario.getEmail());
		Assertions.assertEquals(30, usuario.getIdade());
		Assertions.assertEquals("123teste", usuario.getSenha());
		Assertions.assertEquals("081955666777", usuario.getTelefone());
		
	}

	@Test
	void deveriaSalvarUmNovoUsuario() {

		Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);

		UsuarioDTO usuario = usuarioService.insert(usuarioDTO);

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

	private void instanciaUsuario() {
		usuario = new Usuario();
		usuario.setId_Usuario(1L);
		usuario.setNome("UsuarioTeste");
		usuario.setCpf("000.111.222-02");
		usuario.setEmail("usuarioteste@teste.com");
		usuario.setIdade(30);
		usuario.setSenha("123teste");
		usuario.setTelefone("081955666777");

	}

	private void instanciaUsuarioDTO() {
		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId_usuario(1L);
		usuarioDTO.setNome("UsuarioTeste");
		usuarioDTO.setCpf("000.111.222-02");
		usuarioDTO.setEmail("usuarioteste@teste.com");
		usuarioDTO.setIdade(30);
		usuarioDTO.setSenha("123teste");
		usuarioDTO.setTelefone("081955666777");

	}

	private void instanciaOptionalUsuario() {
		usuario = new Usuario();
		usuario.setId_Usuario(1L);
		usuario.setNome("UsuarioTeste");
		usuario.setCpf("000.111.222-02");
		usuario.setEmail("usuarioteste@teste.com");
		usuario.setIdade(30);
		usuario.setSenha("123teste");
		usuario.setTelefone("081955666777");
		optionalUsuario = Optional.of(usuario);

	}

}
