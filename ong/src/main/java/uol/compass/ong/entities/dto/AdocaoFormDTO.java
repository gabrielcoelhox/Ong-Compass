package uol.compass.ong.entities.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AdocaoFormDTO {
	
	private Long id_Adocao;
	@NotNull
	private Long id_Animal;
	@NotNull
	private Long id_Usuario;
	
	
	

}
