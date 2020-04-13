package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "especialidades")
public class EspecialidadEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nomEspe;
	
	public EspecialidadEntity() {
		
	}

	public EspecialidadEntity(int id, String nomEspe) {
		super();
		this.id = id;
		this.nomEspe = nomEspe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomEspe() {
		return nomEspe;
	}

	public void setNomEspe(String nomEspe) {
		this.nomEspe = nomEspe;
	}
	
	
	
}
