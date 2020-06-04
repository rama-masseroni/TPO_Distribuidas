package test;

import modelo.Rol;
import modelo.Usuario;

import java.util.List;

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
		
		List<Rol> lr = new RolDAO().getRolesByIdUsr(1);
		for(Rol r : lr)
			System.out.println(r.toString());
		
	}
	

}


