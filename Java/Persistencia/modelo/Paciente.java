package modelo;

import java.util.List;
import daos.PacienteDAO;
import daos.TurnoDAO;

public class Paciente extends Rol {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3946676232737481944L;
	private int idUsr;
	private boolean pagoAlDia;

	public Paciente(int idUsr) {
		super(idUsr, "Paciente");
		this.idUsr = idUsr;
		this.pagoAlDia = new PacienteDAO().getEstadoPagos(idUsr);
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

	@Override
	public String toString() {
		return "Paciente [idUsr=" + idUsr + ", pagoAlDia=" + pagoAlDia + "]";
	}

	public boolean alDia() {
		if (this.pagoAlDia)
			return true;
		else
			return false;
	}

	public List<Turno> misTurnos() {
		List<Turno> lt = new PacienteDAO().getTurnosByIdUsrPac(this.idUsr);
		return lt;
	}

	public String reservarTurno(String esp, int idUsrMed, String fecha, String hora) {
		TurnoDAO td = new TurnoDAO();
		List<Turno> lt = misTurnos();
		boolean dispHoraria = true;
		boolean repiteEspDia = false;
		for (Turno t : lt)
			if (t.getFecha().equals(fecha) && t.getHora().equals(hora))
				dispHoraria = false;
		if (dispHoraria) {
			for (Turno t : lt)
				if (t.getFecha().equals(fecha) && t.getEspecialidad().equals(esp))
					repiteEspDia = true;
			if (repiteEspDia) {
				return "No puede reservar dos turnos para la misma especialidad en un día";
			} else {
				Turno turno = td.getTurnoIndividual(idUsrMed, fecha, hora);
				if (turno.getEstado().equals("Disponible")) {
					turno.setIdUsrPac(this.idUsr);
					turno.setEstado("Reservado");
					td.reservaDeTurno(turno);
					return "Ha reservado el turno exitosamente!";
				} else {
					return "Este turno ya no está disponible";
				}
			}
		} else {
			return "Usted ya tiene un turno en ese día y horario";
		}
	}
	
	/*

	public boolean confirmarAsistencia(String fecha, String hora) {
		return false;
	}

	public boolean cancelarTurno(String fecha, String hora) {
		return false;
	}
	
	*/

}