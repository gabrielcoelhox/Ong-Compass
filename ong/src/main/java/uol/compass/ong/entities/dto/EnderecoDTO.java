package uol.compass.ong.entities.dto;

import uol.compass.ong.entities.Endereco;

public class EnderecoDTO {

	private Long id_Endereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;

	public EnderecoDTO() {}
	
	public EnderecoDTO(Endereco enderecoObj) {
		this.id_Endereco = enderecoObj.getId_Endereco();
		this.logradouro = enderecoObj.getLogradouro();
		this.cep = enderecoObj.getCep();
		this.numero = enderecoObj.getNumero();
		this.complemento = enderecoObj.getComplemento();
		this.bairro = enderecoObj.getBairro();
		this.cidade = enderecoObj.getCidade();
		this.estado = enderecoObj.getEstado();
	}

	public Long getId_Endereco() {
		return id_Endereco;
	}

	public void setId_Endereco(Long id_Endereco) {
		this.id_Endereco = id_Endereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
