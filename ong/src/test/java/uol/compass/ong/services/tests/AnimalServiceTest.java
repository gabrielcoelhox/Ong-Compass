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

import uol.compass.ong.entities.Animal;
import uol.compass.ong.entities.dto.AnimalDTO;
import uol.compass.ong.repository.AnimalRepository;
import uol.compass.ong.services.AnimalService;

@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {

	@InjectMocks
	private AnimalService animalService;

	@Mock
	private AnimalRepository animalRepository;

	private Optional<Animal> optionalAnimal;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveriaDeletarComSucessoQuandoIdExistir() {

		Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(instanciaOptionalAnimal(1L));
		Mockito.doNothing().when(animalRepository).deleteById(Mockito.anyLong());
		animalService.deleteById(1L);

		Mockito.verify(animalRepository, times(1)).delete(instanciaAnimal(1L));

	}

	@Test
	void deveriaBuscarUmaListaComTodosAnimais() {
		Mockito.when(animalRepository.findAll()).thenReturn(List.of(instanciaAnimal(1L)));
		List<AnimalDTO> listAnimalDTO = animalService.findAll();

		Assertions.assertNotNull(listAnimalDTO);
		listAnimalDTO.stream().forEach(animalDTO -> {
			Assertions.assertEquals(AnimalDTO.class, animalDTO.getClass());
			Assertions.assertEquals(10, animalDTO.getIdade());
			Assertions.assertEquals(animalDTO.getPorte().G, animalDTO.getPorte());
			Assertions.assertEquals(animalDTO.getSexo().F, animalDTO.getSexo());
			Assertions.assertEquals("Cachorro", animalDTO.getEspecie());
			Assertions.assertEquals("Vira lata", animalDTO.getRaca());
		});

	}

	@ParameterizedTest
	@ValueSource(longs = { 1L, 2L, 3L })
	void deveriaBuscarAnimalComIdExistente(Long id) {
		Mockito.when(animalRepository.findById(id)).thenReturn(instanciaOptionalAnimal(id));
		AnimalDTO animalDTO = animalService.findById(id);

		Assertions.assertNotNull(animalDTO);
		Assertions.assertEquals(AnimalDTO.class, animalDTO.getClass());
		Assertions.assertEquals(id, animalDTO.getId_animal());
		Assertions.assertEquals(10, animalDTO.getIdade());
		Assertions.assertEquals(animalDTO.getPorte().G, animalDTO.getPorte());
		Assertions.assertEquals(animalDTO.getSexo().F, animalDTO.getSexo());
		Assertions.assertEquals("Cachorro", animalDTO.getEspecie());
		Assertions.assertEquals("Vira lata", animalDTO.getRaca());

	}

	@Test
	void deveriaSalvarUmNovoAnimal() {

		Mockito.when(animalRepository.save(Mockito.any())).thenReturn(instanciaAnimal(1L));

		AnimalDTO animal = animalService.insert(instanciaAnimalDTO());

		Assertions.assertNotNull(animal);
		Assertions.assertEquals(AnimalDTO.class, animal.getClass());
		Assertions.assertEquals(1L, animal.getId_animal());
		Assertions.assertEquals(10, animal.getIdade());
		Assertions.assertEquals(animal.getPorte().G, animal.getPorte());
		Assertions.assertEquals(animal.getSexo().F, animal.getSexo());
		Assertions.assertEquals("Cachorro", animal.getEspecie());
		Assertions.assertEquals("Vira lata", animal.getRaca());

	}

	private Animal instanciaAnimal(Long id) {
		Animal animal = new Animal();
		animal.setId_animal(id);
		animal.setIdade(10);
		animal.setPorte(animal.getPorte().G);
		animal.setSexo(animal.getSexo().F);
		animal.setEspecie("Cachorro");
		animal.setRaca("Vira lata");
		return animal;
	}

	private AnimalDTO instanciaAnimalDTO() {
		AnimalDTO animalDTO = new AnimalDTO();
		animalDTO.setId_animal(1L);
		animalDTO.setIdade(10);
		animalDTO.setPorte(animalDTO.getPorte().G);
		animalDTO.setSexo(animalDTO.getSexo().F);
		animalDTO.setEspecie("Cachorro");
		animalDTO.setRaca("Vira lata");
		return animalDTO;
	}

	private Optional<Animal> instanciaOptionalAnimal(Long id) {
		return Optional.of(instanciaAnimal(id));
	}

}
