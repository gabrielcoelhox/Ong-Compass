package uol.compass.usuario.controller.dto;

public class AnimalDTO {

	private Long id_animal;
	private int idade;
	private String porte;
	private String raca;
	private String sexo;

	private String especie;

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Long getId_animal() {
		return id_animal;
	}

	public void setId_animal(Long id_animal) {
		this.id_animal = id_animal;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "AnimalDTO [id_animal=" + id_animal + ", idade=" + idade + ", porte=" + porte + ", raca=" + raca
				+ ", sexo=" + sexo + ", especie=" + especie + "]";
	}
	
	

}
