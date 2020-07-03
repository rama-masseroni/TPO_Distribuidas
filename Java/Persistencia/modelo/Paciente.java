package modelo;

import java.util.Date;
import java.util.List;
import daos.PacienteDAO;
import daos.TurnoDAO;
import utilitarios.CalculosFechas;

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

	public Paciente(int idUsr, boolean pagoAlDia) {
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

	@Override
	public String toString() {
		return "Paciente [idUsr=" + idUsr + ", pagoAlDia=" + pagoAlDia + "]";
	}

	public void guardar() {
		PacienteDAO pd = new PacienteDAO();
		pd.save(this);
	}

	public boolean alDia() {
		if (this.pagoAlDia)
			return true;
		else
			return false;
	}

	public List<Turno> misTurnos() {
		List<Turno> lt = new TurnoDAO().getProxTurnosPaciente(this.idUsr);
		return lt;
	}

	public String reservarTurno(String esp, int idUsrMed, String fecha, String hora) {
		if (this.alDia()) {
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
						td.update(turno);
						return "Ha reservado el turno exitosamente!";
					} else {
						return "Este turno ya no está disponible";
					}
				}
			} else {
				return "Usted ya tiene un turno en ese día y horario";
			}
		} else {
			return "No puede reservar el turno dado que no está al día con la cuota";
		}
	}

	public String cancelarTurno(String fecha, String hora) {
		CalculosFechas calc = new CalculosFechas();
		Date fechaDelTurno = calc.deStringADateUtil(fecha, hora);
		Date cancelacionPermitida = calc.sumarHorasAFecha(fechaDelTurno, -12);
		Date fechaHsActual = new Date();
		if (fechaHsActual.compareTo(cancelacionPermitida) > 0) {
			TurnoDAO td = new TurnoDAO();
			List<Turno> lt = misTurnos();
			for (Turno t : lt)
				if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
					t.setEstado("Cancelado");
					td.update(t);
				}
			return "La cancelación de este turno generará un cargo en su cuenta corriente";
		} else {
			TurnoDAO td = new TurnoDAO();
			List<Turno> lt = misTurnos();
			for (Turno t : lt)
				if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
					t.setEstado("Disponible");
					t.setIdUsrPac(1);
					td.update(t);
					return "Su turno ha sido cancelado";
				}
			return "No existe el turno";
		}
	}

	public String confirmarAsistencia(String fecha, String hora) {
		boolean intervinoCM = false;
		CalculosFechas calc = new CalculosFechas();
		Date fechaDelTurno = calc.deStringADateUtil(fecha, hora);
		Date confInicial = calc.sumarHorasAFecha(fechaDelTurno, -12);
		Date confFinal = calc.sumarHorasAFecha(fechaDelTurno, -1);
		Date fechaHsActual = new Date();
		if (fechaHsActual.compareTo(confInicial) >= 0 && fechaHsActual.compareTo(confFinal) < 0) {
			TurnoDAO td = new TurnoDAO();
			List<Turno> lt = misTurnos();
			for (Turno t : lt)
				if (t.getFecha().equals(fecha) && t.getHora().equals(hora) && t.getEstado().equals("Reservado")) {
					t.setEstado("Confirmado");
					td.update(t);
				} else if (t.getFecha().equals(fecha) && t.getHora().equals(hora) && t.getEstado().equals("CanceladoCM")) {
					intervinoCM = true;
				}
			if(intervinoCM)
				return "Su turno ha sido cancelado debido a un improvisto. Lo contactaremos para su reprogramacion";
			else
				return "Ha confirmado el turno exitosamente!";
		} else {
			if (fechaHsActual.compareTo(confInicial) < 0) {
				return "Todavía no puede confirmar este turno, debe esperar hasta doce horas antes del mismo.";
			} else {
				TurnoDAO td = new TurnoDAO();
				List<Turno> lt = misTurnos();
				for (Turno t : lt)
					if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
						t.setEstado("Confirmado tarde");
						td.update(t);
					}
				return "Usted no ha confirmado su turno a tiempo";
			}
		}
	}

//	public String confirmarAsistencia(String fecha, String hora) {
//		CalculosFechas calc = new CalculosFechas();
//		Date fechaDelTurno = calc.deStringADateUtil(fecha, hora);
//		Date confInicial = calc.sumarHorasAFecha(fechaDelTurno, -12);
//		Date confFinal = calc.sumarHorasAFecha(fechaDelTurno, -1);
//		Date fechaHsActual = new Date();
//		if (fechaHsActual.compareTo(confInicial) > 0 && fechaHsActual.compareTo(confFinal) < 0) {
//			TurnoDAO td = new TurnoDAO();
//			List<Turno> lt = misTurnos();
//			for (Turno t : lt)
//				if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
//					t.setEstado("Confirmado");
//					td.update(t);
//				}
//			return "Ha confirmado el turno exitosamente!";
//		} else {
//			TurnoDAO td = new TurnoDAO();
//			List<Turno> lt = misTurnos();
//			for (Turno t : lt)
//				if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
//					t.setEstado("Confirmado fuera de término");
//					td.update(t);
//				}
//			return "Usted no ha confirmado su turno a tiempo";
//		}
//	}

}
