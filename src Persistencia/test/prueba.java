package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Login;
import modelo.Persona;
import views.EdificioView;
import views.ImagenView;
import views.LoginView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class prueba {

	public static void main(String[] args) throws UnidadException, EdificioException, PersonaException, ReclamoException {
		Controlador c = new Controlador();
		List<PersonaView> p = c.getPersonas();
		for(PersonaView pe : p) {
			System.out.println(pe.getNombre() + " " + pe.getDocumento());
		}
		
		p = c.dueniosPorEdificio(1);
		for(PersonaView pe : p) {
			System.out.println(pe.getNombre() + " " + pe.getDocumento());
		}
		
		c.registrarUsuario("berni", "testcontra", "DNI93277649");
		//LoginView l = new LoginView("berni", "testcontra", "DNI93277649");
		boolean b = c.verficarLogin("admin", "contraadmin");
		System.out.println(b);
		b = c.verificarDuenio("DNI93277649", 488);
		System.out.println(b);
	}
	

}


