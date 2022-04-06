package uol.compass.ong.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adocao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Adocao;
	
	@ManyToOne
	@JoinColumn(name = "id_Usuario")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "id_Animal")
	private Animal animal;
	
	

}
