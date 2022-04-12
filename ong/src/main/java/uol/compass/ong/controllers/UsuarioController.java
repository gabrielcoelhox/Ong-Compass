package uol.compass.ong.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import uol.compass.ong.entities.Usuario;
import uol.compass.ong.entities.dto.CredenciaisDTO;
import uol.compass.ong.entities.dto.TokenDTO;
import uol.compass.ong.entities.dto.UsuarioDTO;
import uol.compass.ong.exceptions.SenhaInvalidaException;
import uol.compass.ong.security.JwtService;
import uol.compass.ong.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	private final PasswordEncoder passwordEncoder;

	@ApiOperation(value= "Retorna uma lista com todos usuários.")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> listUsuarioDTO = usuarioService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}

	@ApiOperation(value= "Retorna um usuário unico.")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@ApiOperation(value= "Insere um novo usuário.")
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO inserirUsuario, UriComponentsBuilder uriComponentsBuilder) {
		String senhaCriptografada = passwordEncoder.encode(inserirUsuario.getSenha());
		inserirUsuario.setSenha(senhaCriptografada);
		
		URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(inserirUsuario.getId_usuario()).toUri();
		return ResponseEntity.created(uri).body(usuarioService.insert(inserirUsuario));
	}

	@ApiOperation(value= "Deleta um Usuário.")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Delete realizado com sucesso",
	            response = UsuarioDTO.class),
	      @ApiResponse(code = 403, message = "Perfil não autorizado para realizar esta operação",
			response = UsuarioDTO.class),
	      @ApiResponse(code = 404, message = "Usuário não encontrado",
	            response = UsuarioDTO.class)
	})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value= "Atualiza um usuário.")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Atualização realizada com sucesso",
	            response = UsuarioDTO.class),
	      @ApiResponse(code = 403, message = "Perfil não autorizado para realizar esta operação",
			response = UsuarioDTO.class),
	      @ApiResponse(code = 404, message = "Usuário não encontrado",
	            response = UsuarioDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.update(id, usuario));
	}

	@PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
