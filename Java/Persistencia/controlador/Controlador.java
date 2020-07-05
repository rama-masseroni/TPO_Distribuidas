package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import daos.MedicoDAO;
import daos.RolDAO;
import daos.TurnoDAO;
import daos.UsuarioDAO;
import modelo.Medico;
import modelo.Paciente;
import modelo.Rol;
import modelo.Turno;
import modelo.Usuario;
import views.TurnoView;
import views.UsuarioView;

public class Controlador {

	private static Controlador instancia;

	public Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public boolean verificarLogin(String username, String password) {
		Usuario usr = new UsuarioDAO().getUsuarioByUsername(username);
		if (usr.verificarLogin(username, password))
			return true;
		else
			return false;
	}

	public UsuarioView obtenerUsuario(String username) {
		Usuario usr = usrConRoles(username);
		if (usr != null) {
			UsuarioView uv = usr.toView();
			return uv;
		} else {
			return null;
		}
	}

	public UsuarioView inicioDeSesion(String username, String password) {
		boolean login = verificarLogin(username, password);
		if (login) {
			UsuarioView uv = obtenerUsuario(username);
			return uv;
		} else {
			return null;
		}
	}

	public TurnoView buscarTurnoIndividual(int idUsrMed, String fecha, String hora) {
		Turno turno = new TurnoDAO().getTurnoIndividual(idUsrMed, fecha, hora);
		TurnoView tv = turno.toView();
		return tv;
	}

	public List<TurnoView> buscarTurnos(Date dia, String especialidad){
		List<TurnoView> result = new ArrayList<TurnoView>();
		SimpleDateFormat formatFecha = new SimpleDateFormat("YYYY-MM-dd");
		String fecha = formatFecha.format(dia);
		SimpleDateFormat formatHora = new SimpleDateFormat("kk:mm");
		String hora = formatHora.format(dia);
		List<Turno> turnos = new TurnoDAO().getTurnosConObligatorios(fecha, hora, especialidad);
		for(Turno t : turnos)
			result.add(t.toView());
		return result;
	}

	public List<TurnoView> proxTurnosPaciente(int idUsrPac) {
		List<Turno> turnos = new Paciente(idUsrPac).misTurnos();
		List<TurnoView> lt = new ArrayList<TurnoView>();
		for (Turno t : turnos) {
			lt.add(t.toView());
		}
		return lt;
	}

	private Usuario usrConRoles(String username) {
		Usuario usr = new UsuarioDAO().getUsuarioByUsername(username);
		if (usr != null) {
			List<Rol> roles = new RolDAO().getRolesByIdUsr(usr.getId());
			usr.setRoles(roles);
			return usr;
		} else {
			return null;
		}
	}

	public List<UsuarioView> getAllMeds() {
		List<Integer> idMeds = new MedicoDAO().getAllIdsMed();
		List<UsuarioView> medicos = new ArrayList<UsuarioView>();
		for (Integer i : idMeds)
			medicos.add(new UsuarioDAO().getUsuarioByID(i).toView());
		return medicos;
	}

	public List<String> getEspecialidades() {
		return new MedicoDAO().getAllEspe();
	}

	public String reservarTurno(int idUsrPac, int idUsrMed, String especialidad, String fecha, String hora) {
		Paciente pac = new Paciente(idUsrPac);
		String respuesta = pac.reservarTurno(especialidad, idUsrMed, fecha, hora);
		return respuesta;
	}

	public String cancelarTurno(int idUsrPac, String fecha, String hora) {
		Paciente pac = new Paciente(idUsrPac);
		String respuesta = pac.cancelarTurno(fecha, hora);
		return respuesta;
	}

	public String confirmarAsistencia(int idUsrPac, String fecha, String hora) {
		Paciente pac = new Paciente(idUsrPac);
		String respuesta = pac.confirmarAsistencia(fecha, hora);
		return respuesta;
	}

	public List<TurnoView> proxTurnosMedico(int idUsrMed) {
		List<Turno> turnos = new Medico(idUsrMed).proximosTurnos();
		List<TurnoView> lt = new ArrayList<TurnoView>();
		for (Turno t : turnos) {
			lt.add(t.toView());
		}
		return lt;
	}

	public String agendarNuevoTurnoIndividual(int idUsrMed, String esp, String fecha, String hora) {
		String resultado = new Medico(idUsrMed).agendarTurnoIndividual(esp, fecha, hora);
		return resultado;
	}
	
	public String agendarPeriodoMedico(int idUsrMed, String esp, Map<String, List<String>> horarios) {
		String resultado = new Medico(idUsrMed).agendarPeriodo(esp, horarios);
		return resultado;
	}

	public String eliminacionTurnoIndividual(int idUsrMed, String fecha, String hora) {
		String resultado = new Medico(idUsrMed).eliminarTurnoIndividual(fecha, hora);
		return resultado;
	}

	public String eliminarPeriodoMedico(int idUsrMed, Map<String, List<String>> horarios) {
		String resultado = new Medico(idUsrMed).eliminarPeriodo(horarios);
		return resultado;
	}

	public String cancelacionXParteDelCM(int idUsrMed, String fecha, String hora) {
		Turno t = new TurnoDAO().getTurnoIndividual(idUsrMed, fecha, hora);
		t.setEstado("CanceladoCM");
		t.actualizar();
		return "Se cancelo el turno";
	}
	
	public String pacienteAColaDeEspera(String esp, int idUsrPac, int idUsrMed) {
		String resultado = new Paciente(idUsrPac).ingresarAColaDeEspera(esp, idUsrMed);
		return resultado;
	}
	
}
