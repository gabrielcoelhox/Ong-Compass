package uol.compass.ong.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.ong.entities.dto.UsuarioDTO;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Usuario;

	private String nome;
	private String cpf;
	private Integer idade;
	private String telefone;
	private String email;
	private String senha;

	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	public Usuario(UsuarioDTO usuarioDTO) {
		this.cpf = usuarioDTO.getCpf();
		this.email = usuarioDTO.getEmail();
		this.id_Usuario = usuarioDTO.getId_usuario();
		this.idade = usuarioDTO.getIdade();
		this.nome = usuarioDTO.getNome();
		this.telefone = usuarioDTO.getTelefone();
		this.senha = usuarioDTO.getSenha();
	}

}
