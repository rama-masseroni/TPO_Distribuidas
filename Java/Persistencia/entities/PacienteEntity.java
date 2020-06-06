package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Pacientes")
public class PacienteEntity {
	@Id
	@Column (name = "idUsr")
	private int idUsr;
	@Column(name = "pagoAlDia")
	private boolean pagoAlDia;
	
	public PacienteEntity(){		
	}

	public PacienteEntity(int idUsr, boolean pagoAlDia) {
		this.idUsr = idUsr;
		this.pagoAlDia = pagoAlDia;
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}

	public boolean getPagoAlDia() {
		return pagoAlDia;
	}

	public void setPagoAlDia(boolean pagoAlDia) {
		this.pagoAlDia = pagoAlDia;
	}
	
	
}
