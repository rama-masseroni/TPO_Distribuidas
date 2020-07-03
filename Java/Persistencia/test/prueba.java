package test;

import modelo.Medico;
import modelo.Paciente;
import modelo.Rol;
import modelo.Turno;
import modelo.Usuario;
import views.TurnoView;
import views.UsuarioView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import controlador.Controlador;
import daos.MedicoDAO;
import daos.PacienteDAO;
import daos.RolDAO;
import daos.TurnoDAO;
import daos.UsuarioDAO;

public class prueba {

	public static void main(String[] args) {

//		// INSERTAR USUARIO
//		Usuario usr = new Usuario(0, "delfina@ejemplo.com.ar", "444444", "Delfina", "Martinez", "1993-11-03", 37222715, 'F');
//		UsuarioDAO ud = new UsuarioDAO();
//		ud.save(usr);

//		// SELECT DE USUARIOS
//		Usuario usr = new UsuarioDAO().getUsuarioByUsername("alejandra@ejemplo.com.ar");
//		System.out.println(usr.toString());

//		// VERIFICAR EL LOGIN DE UN USUARIO
//		Usuario usr = new UsuarioDAO().getUsuarioByUsername("alejandra@ejemplo.com.ar");
//		boolean respuestaLogin = usr.verificarLogin("alejandra@ejemplo.com.ar", "asdasd");
//		System.out.println(respuestaLogin);

//		// AGREGAR ROL A USUARIO
//		Rol r = new Rol(12, "Paciente");
//		Usuario usr = new UsuarioDAO().getUsuarioByUsername("delfina@ejemplo.com.ar");
//		usr.agregarRol(r);

//		// AGREGAR PACIENTE
//		Paciente p = new Paciente(12, false);
//		p.guardar();

//		// OBTENER LOS ROLES DE UN USR 
//		List<Rol> lr = new RolDAO().getRolesByIdUsr(9);
//		for(Rol r : lr)
//			System.out.println(r.toString());

//		// INSERTAR TURNO
//		Turno turno = new Turno("2020-07-03", "10:30", "Infectologia", "Disponible", 3, 1);
//		turno.guardar();

//		// SELECT DE LOS TURNOS DE UN PACIENTE
//		List<Turno> lt = new Paciente(7).misTurnos();
//		for (Turno t : lt)
//			System.out.println(t.toString());

//		// SELECT de estado de pagos
//		Paciente p = new Paciente(6);
//		boolean pagosAlDia = p.alDia();
//		System.out.println(pagosAlDia);

//		// SELECT DE LAS ESPECIALIDADES DE UN MÉDICO
//		List<String> le = new MedicoDAO().getEspecialidadesByMedico(2);
//		for (String e : le)
//			System.out.println(e);
		
//		// TRAER DATOS DEL MÉDICO
//		Medico m = new Medico(5);
//		System.out.println(m.toString());
		
//		// SELECT DE LOS TURNOS QUE UN MÉDICO TIENE POR DELANTE
//		List<Turno> lt = new TurnoDAO().getProxTurnosMedico(3);
//		for(Turno t : lt)
//			System.out.println(t.toString());
		
//		// conocer la especialidad que un médico atiende en x día
//		String especialidad = new Medico(3).espDelDia("2020-06-22");
//		System.out.println(especialidad);
		
//		// turnos de un médico en una fecha
//		List<Turno> lt = new TurnoDAO().turnosEnFecha(3, "2020-06-22");
//		for(Turno t : lt)
//			System.out.println(t.toString());

//		//OBTENER Y LUEGO ELIMINAR UN TURNO
//		Turno t = new TurnoDAO().getTurnoIndividual(3, "2020-06-23", "06:00");
//		t.eliminar();

		// -------------------------------------- Métodos del Controlador --------------------------------------

//		// Verificar login de usr desde Controlador
//		boolean respuesta = Controlador.getInstancia().verficarLogin("miguel@ejemplo.com.ar", "asd5555asd");
//		System.out.println(respuesta);

//		// Obtención de un turno
//		TurnoView tv = Controlador.getInstancia().buscarTurnoIndividual(3, "2020-07-30", "12:15");
//		System.out.println(tv.toString());

//		// OBTENER view de un usr
//		UsuarioView uv = Controlador.getInstancia().obtenerUsuario("pedro@ejemplo.com.ar");
//		System.out.println(uv.toString());

//		// RESERVA DE TURNO - Update en tabla
//		String reserva = Controlador.getInstancia().reservarTurno(4, 3, "Infectologia", "2020-07-03", "15:15");
//		System.out.println(reserva);
		
//		//CANCELACIÓN POR PARTE DEL CENTRO MÉDICO
//		String resultado = Controlador.getInstancia().cancelacionXParteDelCM(3, "2020-07-03", "08:00");
//		System.out.println(resultado);

//		// CANCELAR UN TURNO
//		String cancelacion = Controlador.getInstancia().cancelarTurno(5, "2020-09-30", "20:00");
//		System.out.println(cancelacion);

//		// CONFIRMAR ASISTENCIA
//		String respuesta = Controlador.getInstancia().confirmarAsistencia(5, "2020-07-03", "08:15");
//		System.out.println(respuesta);
		
//		// OBTENER LOS TURNOS QUE EL MÉDICO TIENE POR DELANTE
//		List<TurnoView> lt = Controlador.getInstancia().proxTurnosMedico(3);
//		for(TurnoView tv : lt)
//			System.out.println(tv.toString());
		
//		// obtener los próximos turnos de un paciente
//		List<TurnoView> lt = Controlador.getInstancia().proxTurnosPaciente(4);
//		for(TurnoView tv : lt)
//			System.out.println(tv.toString());
		
//		// EL MÉDICO AGENDA UN TURNO DE FORMA INDIVIDUAL
//		String resultado = Controlador.getInstancia().agendarNuevoTurnoIndividual(3, "Neumonologia", "2020-08-31", "16:45");
//		System.out.println(resultado);

//		// eliminar turno individual
//		String res = Controlador.getInstancia().eliminacionTurnoIndividual(3, "2020-07-03", "15:00");
//		System.out.println(res);
		
//		// CREAR PERÍODO DE TURNOS
//		Map<String, List<String>> horarios = new HashMap<>();
//		List<String> primerDia = new ArrayList<>();
//		//List<String> segundoDia = new ArrayList<>();
//		primerDia.add("11:00");
//		primerDia.add("11:45");
//		//segundoDia.add("11:00");
//		//segundoDia.add("11:15");
//		horarios.put("2020-07-30", primerDia);
//		//horarios.put("2020-07-20", segundoDia);		
//		String resultado = Controlador.getInstancia().agendarPeriodoMedico(3, "Infectologia", horarios);
//		System.out.println(resultado);
		
//		// ELIMINAR PERÍODO DE TURNOS DE UN MÉDICO
//		Map<String, List<String>> horarios = new HashMap<>();
//		List<String> primerDia = new ArrayList<>();
//		List<String> segundoDia = new ArrayList<>();
//		primerDia.add("15:15");
//		primerDia.add("15:30");
//		segundoDia.add("09:30");
//		segundoDia.add("09:45");
//		horarios.put("2020-07-20", primerDia);
//		horarios.put("2020-07-29", segundoDia);
//		String resultado = Controlador.getInstancia().eliminarPeriodoMedico(3, horarios);
//		System.out.println(resultado);
		
		// -------------------------------------- FIN Métodos del Controlador --------------------------------------

		
		// ------------------------------------------------------------------------------------------------------------
		// ---------------------------------------- ARRIBA DE ESTA LÍNEA ESTÁ PROBADO -------------------------------------------------
		// ------------------------------------------------------------------------------------------------------------
		
		String res = Controlador.getInstancia().pacienteAColaDeEspera("Hepatologia", 7, 5);
		System.out.println(res);
		
		
		


		
		
		


		List<UsuarioView> medicos = Controlador.getInstancia().getAllMeds();
		for(UsuarioView uv: medicos)
			System.out.println(uv.toString());
		



		
		


	}

}










