package views;

import java.time.LocalDateTime;

import modelo.Usuario;

public class TurnoView {
	
	private LocalDateTime fecha;
	private Usuario asignado, paciente;
	private String estado;

	public TurnoView() {}

	public TurnoView(LocalDateTime fecha, Usuario asignado, Usuario paciente, String estado) {
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

	@Override
	public String toString() {
		return "TurnoView [fecha=" + fecha + ", asignado=" + asignado + ", paciente=" + paciente + ", estado=" + estado
				+ "]";
	}
	
	
}
