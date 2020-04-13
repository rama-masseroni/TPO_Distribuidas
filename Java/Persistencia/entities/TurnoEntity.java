package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "turnos")
public class TurnoEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int nroTurno;
	
	private LocalDateTime fecha;
	
	private ProfesionalEntity asignado;
	
	private PacienteEntity paciente;
	
	private String estado;
	
	public TurnoEntity() {
		
	}

	public TurnoEntity(LocalDateTime fecha, ProfesionalEntity asignado, PacienteEntity paciente, String estado) {
		super();
		this.fecha = fecha;
		this.asignado = asignado;
		this.paciente = paciente;
		this.estado = estado;
	}

	public int getNroTurno() {
		return nroTurno;
	}

	public void setNroTurno(int nroTurno) {
		this.nroTurno = nroTurno;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public ProfesionalEntity getAsignado() {
		return asignado;
	}

	public void setAsignado(ProfesionalEntity asignado) {
		this.asignado = asignado;
	}

	public PacienteEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.paciente = paciente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}
