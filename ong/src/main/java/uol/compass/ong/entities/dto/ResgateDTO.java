package uol.compass.ong.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.ong.entities.Resgate;
import uol.compass.ong.enums.Status;

@Getter
@Setter
@NoArgsConstructor
public class ResgateDTO {

	private Long id_resgate;
	
	@NotNull @NotEmpty
	private String endereco;
	
	@NotNull @NotEmpty
	private String caracteristicas_animal;
	
	private String descricao;
	
	@NotNull @NotEmpty
	private String usuario;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public ResgateDTO(Resgate resgateObj) {
		this.id_resgate = resgateObj.getId_Resgate();
		this.endereco = resgateObj.getEndereco();
		this.caracteristicas_animal = resgateObj.getCaracteristicas_animal();
		this.descricao = resgateObj.getDescricao();
		this.usuario = resgateObj.getUsuario();
		this.status = resgateObj.getStatus();
	}
}
