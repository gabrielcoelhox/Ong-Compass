package uol.compass.ong.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@OneToMany(mappedBy = "usuario")
	private List<Adocao> adocoes;

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
