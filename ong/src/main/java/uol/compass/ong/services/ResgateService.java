package uol.compass.ong.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.ong.entities.Resgate;
import uol.compass.ong.entities.dto.ResgateDTO;
import uol.compass.ong.exceptions.DefaultException;
import uol.compass.ong.repository.ResgateRepository;

@Service
public class ResgateService {

	@Autowired
	private ResgateRepository resgateRepository;
	
	@Transactional
	public List<ResgateDTO> findAll() {
		List<Resgate> list = resgateRepository.findAll();
		return instanciaListaResgateDTO(list);
	}
	
	@Transactional
	public ResgateDTO findById(Long id) {
		Resgate resgateObj = resgateRepository.findById(id).orElseThrow(
				() -> new DefaultException("Resgate com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		return new ResgateDTO(resgateObj);

	}
	
	public ResgateDTO insert(@Valid ResgateDTO resgateDTO) {
		Resgate resgate = new Resgate(resgateDTO);
		try {
			resgateRepository.save(resgate);
			return new ResgateDTO(resgate);
		} catch (uol.compass.ong.exceptions.MethodArgumentNotValidException e) {
			throw new uol.compass.ong.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public ResgateDTO update(Long id, @Valid Resgate resgate) {
		Resgate res = resgateRepository.findById(id).orElseThrow(
				() -> new DefaultException("Resgate com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		res.setCaracteristicas_animal(resgate.getCaracteristicas_animal());
		res.setEndereco(resgate.getEndereco());
		res.setDescricao(resgate.getDescricao());
		res.setUsuario(resgate.getUsuario());
		res.setStatus(resgate.getStatus());

		ResgateDTO resgateDTO = new ResgateDTO(res);
		return resgateDTO;
	}
	
	public void delete(Long id) {
		findById(id);
		resgateRepository.deleteById(id);
	}
	
	public void deleteById(Long id) {
		Resgate resgateObj = resgateRepository.findById(id).orElseThrow(
				() -> new DefaultException("Resgate com id: " + id + " não encontrado.", "NOT_FOUND", 404));
		resgateRepository.delete(resgateObj);
	}
	
	public static List<ResgateDTO> instanciaListaResgateDTO(List<Resgate> list) {
		List<ResgateDTO> listDTO = new ArrayList<>();
		for (Resgate resgate : list) {
			ResgateDTO dto = new ResgateDTO();
			dto.setId_resgate(resgate.getId_Resgate());
			dto.setEndereco(resgate.getEndereco());
			dto.setCaracteristicas_animal(resgate.getCaracteristicas_animal());
			dto.setDescricao(resgate.getDescricao());
			dto.setUsuario(resgate.getUsuario());
			dto.setStatus(resgate.getStatus());

			listDTO.add(dto);
		}
		return listDTO;
	}
}
