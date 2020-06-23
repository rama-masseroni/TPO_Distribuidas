package controlador;

import java.util.List;
import daos.RolDAO;
import daos.TurnoDAO;
import daos.UsuarioDAO;
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

	public boolean verficarLogin(String username, String password) {
		Usuario usr = new UsuarioDAO().getUsuarioByUsername(username);
		if (usr.verificarLogin(username, password))
			return true;
		else
			return false;
	}
	
	public TurnoView buscarTurnoIndividual(int idUsrMed, String fecha, String hora) {
		Turno turno = new TurnoDAO().getTurnoIndividual(idUsrMed, fecha, hora);
		TurnoView tv = turno.toView();
		return tv;
	}

	public UsuarioView obtenerUsuario(String username) {
		Usuario usr = usrConRoles(username);
		if(usr != null) {
			UsuarioView uv = usr.toView();
			return uv;
		}
		else {
			return null;
		}
	}
	
	private Usuario usrConRoles(String username) {
		Usuario usr = new UsuarioDAO().getUsuarioByUsername(username);
		if(usr != null) {
			List<Rol> roles = new RolDAO().getRolesByIdUsr(usr.getId());
			usr.setRoles(roles);
			return usr;
		}
		else {
			return null;
		}
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
	
}
