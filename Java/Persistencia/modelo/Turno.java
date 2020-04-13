package modelo;

import java.time.LocalDateTime;

public class Turno {
	private LocalDateTime fecha;
	private Usuario asignado, paciente;
	private String estado;
	
	public Turno(LocalDateTime fecha, Usuario asignado, Usuario paciente, String estado) {
		super();
		this.fecha = fecha;
		this.asignado = asignado;
		this.paciente = paciente;
		this.estado = estado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Usuario getAsignado() {
		return asignado;
	}

	public Usuario getPaciente() {
		return paciente;
	}

	public String getEstado() {
		return estado;
	}
	
	
	
}
