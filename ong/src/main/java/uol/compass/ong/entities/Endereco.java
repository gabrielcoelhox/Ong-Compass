package uol.compass.ong.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.ong.entities.dto.EnderecoDTO;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Endereco;
	
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco(@Valid EnderecoDTO enderecoDTO) {
		this.id_Endereco = enderecoDTO.getId_Endereco();
		
		this.logradouro = enderecoDTO.getLogradouro();
		this.cep = enderecoDTO.getCep();
		this.numero = enderecoDTO.getNumero();
		this.complemento = enderecoDTO.getComplemento();
		this.bairro = enderecoDTO.getBairro();
		this.cidade = enderecoDTO.getCidade();
		this.estado = enderecoDTO.getEstado();
	}
}
