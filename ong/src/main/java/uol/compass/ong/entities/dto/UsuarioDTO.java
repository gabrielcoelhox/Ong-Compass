package uol.compass.ong.entities.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.ong.entities.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

	private Long id_usuario;

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cpf;
	@NotNull
	private Integer idade;
	@NotNull
	@NotEmpty
	private String telefone;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String senha;

	public UsuarioDTO(Usuario usuarioObj) {
		this.id_usuario = usuarioObj.getId_Usuario();
		this.nome = usuarioObj.getNome();
		this.cpf = usuarioObj.getCpf();
		this.idade = usuarioObj.getIdade();
		this.telefone = usuarioObj.getTelefone();
		this.email = usuarioObj.getEmail();
		this.senha = usuarioObj.getSenha();
	}

}
