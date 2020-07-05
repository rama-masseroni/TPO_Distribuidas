package modelo;

import java.util.List;
import java.util.Map;
import daos.MedicoDAO;
import daos.TurnoDAO;
import utilitarios.CalculosFechas;

public class Medico extends Rol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7315396380352778902L;
	private int idUsr;
	private String especialidad; // atributo usado en entity
	private List<String> especialidades;

	public Medico(int idUsr) {
		super(idUsr, "Medico");
		this.idUsr = idUsr;
		MedicoDAO md = new MedicoDAO();
		this.especialidades = md.getEspecialidadesByMedico(idUsr);
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Turno> proximosTurnos() {
		List<Turno> lt = new TurnoDAO().getProxTurnosMedico(idUsr);
		return lt;
	}

	public String espDelDia(String fecha) {
		String resultado = new TurnoDAO().espDelDia(idUsr, fecha);
		if (resultado == null)
			resultado = "Disponible";
		return resultado;
	}

	public String agendarTurnoIndividual(String esp, String fecha, String hora) {
		if (CalculosFechas.getInstancia().puedeAgendar(fecha)) {
			String espFecha = espDelDia(fecha);
			if (espFecha.equals(esp) || espFecha.equals("Disponible")) {
				TurnoDAO td = new TurnoDAO();
				List<Turno> lt = td.turnosEnFecha(idUsr, fecha);
				boolean dispHoraria = true;
				for (Turno t : lt)
					if (t.getHora().equals(hora))
						dispHoraria = false;
				if (dispHoraria) {
					Turno turno = new Turno(fecha, hora, esp, "Disponible", this.idUsr, 1);
					turno.guardar();
					return "Su turno se agendó de forma exitosa";
				} else {
					return "Ya tiene un turno agendando en ese horario";
				}
			} else {
				return "No puede atender más de una especialidad el mismo día";
			}
		} else {
			return "No puede agendar un turno luego de dos meses respecto a la fecha actual";
		}
	}

	public String agendarPeriodo(String esp, Map<String, List<String>> horarios) {
		boolean todosGuardados = true;
		for (Map.Entry<String, List<String>> entry : horarios.entrySet()) {
			String fecha = entry.getKey();
			List<String> turnos = entry.getValue();
			String espDia = espDelDia(fecha);
			// YA TIENE TURNOS CARGADOS EN ESA ESPECIALIDAD EN ESE DÍA
			if (espDia.equals(esp) && CalculosFechas.getInstancia().puedeAgendar(fecha)) {
				TurnoDAO td = new TurnoDAO();
				List<Turno> lt = td.turnosEnFecha(this.idUsr, fecha);
				while (!lt.isEmpty() && !turnos.isEmpty()) {
					Turno t1 = lt.get(0);
					boolean coincide = false;
					for (String t2 : turnos)
						if (t2.equals(t1.getHora()))
							coincide = true;
					if (coincide) {
						turnos.remove(t1.getHora());
						lt.remove(t1);
						todosGuardados = false;
					} else {
						lt.remove(t1);
					}
				}
				if (!turnos.isEmpty())
					for (String t : turnos) {
						Turno nuevo = new Turno(fecha, t, esp, "Disponible", this.idUsr, 1);
						nuevo.guardar();
					}
				// NO TIENE TURNOS CARGADOS ESE DÍA
			} else if (espDia.equals("Disponible") && CalculosFechas.getInstancia().puedeAgendar(fecha)) {
				for (String t : turnos) {
					Turno nuevo = new Turno(fecha, t, esp, "Disponible", this.idUsr, 1);
					nuevo.guardar();
				}
			} else {
				todosGuardados = false;
			}
		}
		if (todosGuardados)
			return "Ha creado sus turnos en forma exitosa";
		else
			return "No todos los turnos pudieron crearse";
	}

	public String eliminarTurnoIndividual(String fecha, String hora) {
		if (CalculosFechas.getInstancia().puedeModificarAgenda(fecha)) {
			Turno t = new TurnoDAO().getTurnoIndividual(idUsr, fecha, hora);
			if (t.getEstado().equals("Disponible")) {
				t.eliminar();
				return "Su turno se elimino de forma exitosa";
			} else {
				return "No puede eliminar turnos que ya han sido reservados";
			}
		} else {
			return "No puede modificar turnos con anterioridad inferior a una semana";
		}
	}


	public String eliminarPeriodo(Map<String, List<String>> horarios) {
		boolean eliminacionExitosa = true;
		for (Map.Entry<String, List<String>> entry : horarios.entrySet()) {
			String fecha = entry.getKey();
			List<String> turnos = entry.getValue();
			if (CalculosFechas.getInstancia().puedeModificarAgenda(fecha)) {
				TurnoDAO td = new TurnoDAO();
				List<Turno> lt = td.turnosEnFecha(this.idUsr, fecha);
				while (!turnos.isEmpty() && !lt.isEmpty()) {
					Turno t1 = lt.get(0);
					boolean coincide = false;
					for (String t2 : turnos)
						if (t2.equals(t1.getHora()))
							coincide = true;
					if (coincide && t1.getEstado().equals("Disponible")) {
						t1.eliminar();
						turnos.remove(t1.getHora());
						lt.remove(t1);
					} else if (coincide) {
						turnos.remove(t1.getHora());
						lt.remove(t1);
						eliminacionExitosa = false;
					} else {
						lt.remove(t1);
					}
				}
			} else {
				eliminacionExitosa = false;
			}
		}
		if (eliminacionExitosa)
			return "Sus turnos se eliminaron de forma exitosa";
		else
			return "Sus turnos o algunos de ellos no pudieron eliminarse";
	}

	@Override
	public String toString() {
		return "Medico [idUsr=" + idUsr + ", especialidades=" + especialidades + "]";
	}

}
