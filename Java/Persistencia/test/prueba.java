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
		
		
		/*
		 // FUNCIONA EL SELECT DE USUARIOS
		Usuario usr = new UsuarioDAO().getUsuarioByUsername("pedro@ejemplo.ar");
		System.out.println(usr.toString());
		 */
		
		/*
		 // FUNCIONA EL INSERT DE USUARIOS
		Usuario usr = new Usuario(0, "pablo@ejemplo.ar", "444444", "Pablo", "Musso", "1990-09-12", 33455715, 'M');
		UsuarioDAO ud = new UsuarioDAO();
		ud.save(usr);
		*/
		
		/*
		 // FUNCIONA obtener los roles de un usuario 
		List<Rol> lr = new RolDAO().getRolesByIdUsr(4);
		for(Rol r : lr)
			System.out.println(r.toString());
		*/
		
		/* FUNCIONA controlar el login de un usr
		boolean respuestaLogin;
		respuestaLogin = Controlador.getInstancia().verficarLogin("pedro@ejemplo.ar", "123abc");
		System.out.println(respuestaLogin);
		*/
		
		/*
		 // INSERT de turno
		Turno turno = new Turno(0, "2020-09-22", "14:30", "Urologia", "Reservado", 1, 3);
		turno.guardar();
		*/
		
		/*
		 // SELECT de turnos de usuario
		List<Turno> lt = new Paciente(4).misTurnos();
		for(Turno t : lt)
			System.out.println(t.toString());
		*/
		
		/*
		// SELECT de estado de pagos
		boolean estaAlDia = new PacienteDAO().getEstadoPagos(4);
		System.out.println(estaAlDia);
		*/
		
		/*
		 // Cuando se crea un Paciente ya nos devuelve su estado de pagos (agregué el método DAO que verifica el estado de pagos en el constructor de Paciente)
		Paciente p = new Paciente(5);
		System.out.println(p.toString());
		*/
		
		/*
		 // Se recupera un turno
		Turno t = new TurnoDAO().getTurnoIndividual(2, "2020-10-28", "15:00");
		System.out.println(t.toString());
		*/
		
		/*
		// RESERVA DE TURNO - Update
		Paciente pac = new Paciente(8);
		String resultadoReserva = pac.reservarTurno("Traumatología", 12, "2020-06-29", "12:00");
		System.out.println(resultadoReserva);
		*/
		
		
	}
	

}
