package uol.compass.ong.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import uol.compass.ong.entities.Adocao;
import uol.compass.ong.enums.Porte;
import uol.compass.ong.enums.Sexo;

@Getter
public class AdocaoDTO {
	
	@NotNull
	@NotEmpty
	private String nomeUsuario;
	@NotNull
	@NotEmpty
	private String cpf;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Porte porte;
	@NotNull
	private Integer idade;
	@NotNull
	@NotEmpty
	private String raca;
	@NotNull
	@NotEmpty
	private String especie;
	public AdocaoDTO(Adocao adocao) {
		this.nomeUsuario = adocao.getUsuario().getNome();
		this.cpf = adocao.getUsuario().getCpf();
		this.sexo = adocao.getAnimal().getSexo();
		this.porte = adocao.getAnimal().getPorte();
		this.idade = adocao.getAnimal().getIdade();
		this.raca = adocao.getAnimal().getRaca();
		this.especie = adocao.getAnimal().getEspecie();
	}
	
	

}
