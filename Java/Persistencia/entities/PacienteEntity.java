package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "pacientes")
public class PacienteEntity {
	private int id;
	@Id
	private int dni;
	private String estadoPagos;
	
	public PacienteEntity(){
		
	}

	public PacienteEntity(int id, int dni, String estadoPagos) {
		super();
		this.id = id;
		this.dni = dni;
		this.estadoPagos = estadoPagos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEstadoPagos() {
		return estadoPagos;
	}

	public void setEstadoPagos(String estadoPagos) {
		this.estadoPagos = estadoPagos;
	}
	
	
}
