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

import uol.compass.ong.entities.Endereco;
import uol.compass.ong.entities.dto.EnderecoDTO;
import uol.compass.ong.repository.EnderecoRepository;
import uol.compass.ong.services.EnderecoService;

@ExtendWith(SpringExtension.class)
public class EnderecoServiceTest {

	@InjectMocks
	private EnderecoService enderecoService;

	@Mock
	private EnderecoRepository enderecoRepository;

	private Optional<Endereco> optionalEnderece;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveriaDeletarEnderecoComSucessoQuandoIdExistir() {

		Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(instanciaOptionalEndereco(1L));
		Mockito.doNothing().when(enderecoRepository).deleteById(Mockito.anyLong());
		enderecoService.deleteById(1L);

		Mockito.verify(enderecoRepository, times(1)).delete(instanciaEndereco(1L));

	}

	@Test
	void deveriaBuscarUmaListaComTodosEnderecos() {
		Mockito.when(enderecoRepository.findAll()).thenReturn(List.of(instanciaEndereco(1L)));
		List<EnderecoDTO> listEnderecoDTO = enderecoService.findAll();

		Assertions.assertNotNull(listEnderecoDTO);
		listEnderecoDTO.stream().forEach(enderecoDTO -> {
			Assertions.assertEquals(EnderecoDTO.class, enderecoDTO.getClass());
			Assertions.assertEquals("Industrial", enderecoDTO.getBairro());
			Assertions.assertEquals("10333-000", enderecoDTO.getCep());
			Assertions.assertEquals("João Pessoa", enderecoDTO.getCidade());
			Assertions.assertEquals("Paraíba", enderecoDTO.getEstado());
			Assertions.assertEquals("Próximo ao estádio XPTO", enderecoDTO.getComplemento());
			Assertions.assertEquals("Rua alves correia", enderecoDTO.getLogradouro());
			Assertions.assertEquals("100", enderecoDTO.getNumero());
		});

	}

	@ParameterizedTest
	@ValueSource(longs = { 1L, 2L, 3L })
	void deveriaBuscarEnderecoComIdExistente(Long id) {
		Mockito.when(enderecoRepository.findById(id)).thenReturn(instanciaOptionalEndereco(id));
		EnderecoDTO enderecoDTO = enderecoService.findById(id);

		Assertions.assertNotNull(enderecoDTO);
		Assertions.assertEquals(EnderecoDTO.class, enderecoDTO.getClass());
		Assertions.assertEquals(id, enderecoDTO.getId_Endereco());
		Assertions.assertEquals("Industrial", enderecoDTO.getBairro());
		Assertions.assertEquals("10333-000", enderecoDTO.getCep());
		Assertions.assertEquals("João Pessoa", enderecoDTO.getCidade());
		Assertions.assertEquals("Paraíba", enderecoDTO.getEstado());
		Assertions.assertEquals("Próximo ao estádio XPTO", enderecoDTO.getComplemento());
		Assertions.assertEquals("Rua alves correia", enderecoDTO.getLogradouro());
		Assertions.assertEquals("100", enderecoDTO.getNumero());

	}

	@Test
	void deveriaSalvarUmNovoEndereco() {

		Mockito.when(enderecoRepository.save(Mockito.any())).thenReturn(instanciaEndereco(1L));

		EnderecoDTO enderecoDTO = enderecoService.insert(instanciaEnderecoDTO());

		Assertions.assertNotNull(enderecoDTO);
		Assertions.assertEquals(EnderecoDTO.class, enderecoDTO.getClass());
		Assertions.assertEquals(1L, enderecoDTO.getId_Endereco());
		Assertions.assertEquals("Industrial", enderecoDTO.getBairro());
		Assertions.assertEquals("10333-000", enderecoDTO.getCep());
		Assertions.assertEquals("João Pessoa", enderecoDTO.getCidade());
		Assertions.assertEquals("Paraíba", enderecoDTO.getEstado());
		Assertions.assertEquals("Próximo ao estádio XPTO", enderecoDTO.getComplemento());
		Assertions.assertEquals("Rua alves correia", enderecoDTO.getLogradouro());
		Assertions.assertEquals("100", enderecoDTO.getNumero());

	}

	private Endereco instanciaEndereco(Long id) {
		Endereco endereco = new Endereco();
		endereco.setId_Endereco(id);
		endereco.setBairro("Industrial");
		endereco.setCep("10333-000");
		endereco.setCidade("João Pessoa");
		endereco.setEstado("Paraíba");
		endereco.setComplemento("Próximo ao estádio XPTO");
		endereco.setLogradouro("Rua alves correia");
		endereco.setNumero("100");
		return endereco;
	}

	private EnderecoDTO instanciaEnderecoDTO() {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setId_Endereco(1L);
		enderecoDTO.setBairro("Industrial");
		enderecoDTO.setCep("10333-000");
		enderecoDTO.setCidade("João Pessoa");
		enderecoDTO.setEstado("Paraíba");
		enderecoDTO.setComplemento("Próximo ao estádio XPTO");
		enderecoDTO.setLogradouro("Rua alves correia");
		enderecoDTO.setNumero("100");
		return enderecoDTO;
	}

	private Optional<Endereco> instanciaOptionalEndereco(Long id) {
		return Optional.of(instanciaEndereco(id));
	}

}
