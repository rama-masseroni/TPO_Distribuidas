package test;

import modelo.Rol;
import modelo.Turno;
import modelo.Usuario;
import java.util.List;
import controlador.Controlador;
import daos.RolDAO;
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
		
		
		
		
	}
	

}




















