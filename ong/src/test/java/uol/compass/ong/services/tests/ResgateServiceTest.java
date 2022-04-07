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

import uol.compass.ong.entities.Resgate;
import uol.compass.ong.entities.dto.ResgateDTO;
import uol.compass.ong.repository.ResgateRepository;
import uol.compass.ong.services.ResgateService;

@ExtendWith(SpringExtension.class)
public class ResgateServiceTest {

	@InjectMocks
	private ResgateService resgateService;

	@Mock
	private ResgateRepository resgateRepository;

	private Optional<Resgate> optionalResgate;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveriaDeletarComSucessoQuandoIdExistir() {

		Mockito.when(resgateRepository.findById(Mockito.anyLong())).thenReturn(instanciaOptionalResgate(1L));
		Mockito.doNothing().when(resgateRepository).deleteById(Mockito.anyLong());
		resgateService.deleteById(1L);

		Mockito.verify(resgateRepository, times(1)).delete(instanciaResgate(1L));

	}

	@Test
	void deveriaBuscarUmaListaComTodosResgates() {
		Mockito.when(resgateRepository.findAll()).thenReturn(List.of(instanciaResgate(1L)));
		List<ResgateDTO> listResgateDTO = resgateService.findAll();

		Assertions.assertNotNull(listResgateDTO);
		listResgateDTO.stream().forEach(resgateDTO -> {
			Assertions.assertEquals(ResgateDTO.class, resgateDTO.getClass());
			Assertions.assertEquals("Animal faminto, ferido", resgateDTO.getCaracteristicas_animal());
			Assertions.assertEquals("Um gato, Cor branca, novinho, fêmea", resgateDTO.getDescricao());
			Assertions.assertEquals("Rua projetada", resgateDTO.getEndereco());
			Assertions.assertEquals(resgateDTO.getStatus().AGUARDANDO, resgateDTO.getStatus());
			Assertions.assertEquals("Teste", resgateDTO.getUsuario());
		});

	}

	@ParameterizedTest
	@ValueSource(longs = { 1L, 2L, 3L })
	void deveriaBuscarResgateComIdExistente(Long id) {
		Mockito.when(resgateRepository.findById(id)).thenReturn(instanciaOptionalResgate(id));
		ResgateDTO resgateDTO = resgateService.findById(id);

		Assertions.assertNotNull(resgateDTO);
		Assertions.assertEquals(ResgateDTO.class, resgateDTO.getClass());
		Assertions.assertEquals(id, resgateDTO.getId_resgate());
		Assertions.assertEquals("Animal faminto, ferido", resgateDTO.getCaracteristicas_animal());
		Assertions.assertEquals("Um gato, Cor branca, novinho, fêmea", resgateDTO.getDescricao());
		Assertions.assertEquals("Rua projetada", resgateDTO.getEndereco());
		Assertions.assertEquals(resgateDTO.getStatus().AGUARDANDO, resgateDTO.getStatus());
		Assertions.assertEquals("Teste", resgateDTO.getUsuario());

	}

	@Test
	void deveriaSalvarUmNovoResgate() {

		Mockito.when(resgateRepository.save(Mockito.any())).thenReturn(instanciaResgate(1L));

		ResgateDTO resgateDTO = resgateService.insert(instanciaResgateDTO());

		System.out.println(resgateDTO.getId_resgate());

		Assertions.assertNotNull(resgateDTO);
		Assertions.assertEquals(ResgateDTO.class, resgateDTO.getClass());
		Assertions.assertEquals(1L, resgateDTO.getId_resgate());
		Assertions.assertEquals("Animal faminto, ferido", resgateDTO.getCaracteristicas_animal());
		Assertions.assertEquals("Um gato, Cor branca, novinho, fêmea", resgateDTO.getDescricao());
		Assertions.assertEquals("Rua projetada", resgateDTO.getEndereco());
		Assertions.assertEquals(resgateDTO.getStatus().AGUARDANDO, resgateDTO.getStatus());
		Assertions.assertEquals("Teste", resgateDTO.getUsuario());

	}

	public Resgate instanciaResgate(Long id) {
		Resgate resgate = new Resgate();
		resgate.setId_Resgate(id);
		resgate.setCaracteristicas_animal("Animal faminto, ferido");
		resgate.setDescricao("Um gato, Cor branca, novinho, fêmea");
		resgate.setEndereco("Rua projetada");
		resgate.setStatus(resgate.getStatus().AGUARDANDO);
		resgate.setUsuario("Teste");

		return resgate;
	}

	private ResgateDTO instanciaResgateDTO() {
		ResgateDTO resgateDTO = new ResgateDTO();
		resgateDTO.setId_resgate(1L);
		resgateDTO.setCaracteristicas_animal("Animal faminto, ferido");
		resgateDTO.setDescricao("Um gato, Cor branca, novinho, fêmea");
		resgateDTO.setEndereco("Rua projetada");
		resgateDTO.setStatus(resgateDTO.getStatus().AGUARDANDO);
		resgateDTO.setUsuario("Teste");
		return resgateDTO;
	}

	private Optional<Resgate> instanciaOptionalResgate(Long id) {
		return Optional.of(instanciaResgate(id));
	}
}
