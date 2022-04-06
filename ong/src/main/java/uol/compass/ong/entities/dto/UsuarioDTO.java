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
	@NotEmpty (message = "O campo nome não pode ser vazio")
	private String nome;
	
	@NotNull
	@NotEmpty (message = "O campo cpf não pode ser vazio")
	private String cpf;
	
	@NotNull
	private Integer idade;
	
	@NotNull
	@NotEmpty (message = "O campo telefone não pode ser vazio")
	private String telefone;
	
	@NotNull
	@NotEmpty (message = "O campo email não pode ser vazio")
	private String email;
	
	@NotNull
	@NotEmpty (message = "O campo senha não pode ser vazio")
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
