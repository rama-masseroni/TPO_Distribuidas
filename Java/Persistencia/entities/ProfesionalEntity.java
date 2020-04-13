package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "profesionales")
public class ProfesionalEntity {
	private int id;
	
	@Id
	private int dni;
	
	private EspecialidadEntity especialidad;
	
	public ProfesionalEntity() {
		
	}

	public ProfesionalEntity(int id, int dni, EspecialidadEntity especialidad) {
		super();
		this.id = id;
		this.dni = dni;
		this.especialidad = especialidad;
	}
	
	
	
}
