package test;

import modelo.Paciente;
import modelo.Rol;
import modelo.Turno;
import modelo.Usuario;
import java.util.List;
import controlador.Controlador;
import daos.PacienteDAO;
import daos.RolDAO;
import daos.TurnoDAO;
import daos.UsuarioDAO;

public class prueba {

	public static void main(String[] args) {

//		// SELECT DE USUARIOS
//		Usuario usr = new UsuarioDAO().getUsuarioByUsername("pedro@ejemplo.ar");
//		System.out.println(usr.toString());

//		// INSERTAR USUARIO
//		Usuario usr = new Usuario(0, "patricia@ejemplo.ar", "444444", "Patricia", "Perez", "1995-09-24", 33455715, 'F');
//		UsuarioDAO ud = new UsuarioDAO();
//		ud.save(usr);

//		// VERIFICAR EL LOGIN DE UN USUARIO
//		boolean respuestaLogin;
//		respuestaLogin = Controlador.getInstancia().verficarLogin("patricia@ejemplo.ar", "444444");
//		System.out.println(respuestaLogin);

//		// AGREGAR PACIENTE
//		Paciente p = new Paciente(13, false);
//		p.guardar();

//		// AGREGAR ROL A USUARIO
//		Rol r = new Rol(2, "Medico");
//		Usuario usr = new UsuarioDAO().getUsuarioByUsername("ana@ejemplo.ar");
//		usr.agregarRol(r);

//		// OBTENER LOS ROLES DE UN USR 
//		List<Rol> lr = new RolDAO().getRolesByIdUsr(7);
//		for(Rol r : lr)
//			System.out.println(r.toString());

//		// INSERTAR TURNO
//		Turno turno = new Turno(0, "2020-06-08", "09:30", "Traumatología", "Disponible", 11, 1);
//		turno.guardar();

//		// RESERVA DE TURNO - Update en tabla
//		Paciente pac = new Paciente(8);
//		String resultadoReserva = pac.reservarTurno("Traumatología", 11, "2020-06-08", "09:30");
//		System.out.println(resultadoReserva);

//		// SELECT DE LOS TURNOS DE UN USR
//		List<Turno> lt = new Paciente(8).misTurnos();
//		for(Turno t : lt)
//			System.out.println(t.toString());

//		// SELECT de estado de pagos
//		boolean estaAlDia = new PacienteDAO().getEstadoPagos(8);
//		System.out.println(estaAlDia);
		
//		// CANCELAR UN TURNO
//		Paciente p = new Paciente(8);
//		String cancelar = p.cancelarTurno("2020-06-12", "11:30");
//		System.out.println(cancelar);
		
//		// CONFIRMAR ASISTENCIA
//		Paciente p = new Paciente(8);
//		String confirmar = p.confirmarAsistencia("2020-06-08", "09:30");
//		System.out.println(confirmar);
		
	}

}
