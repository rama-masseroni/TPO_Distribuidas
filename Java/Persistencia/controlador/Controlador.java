package controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import daos.LoginDAO;
import daos.EdificioDAO;
import daos.ImagenDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
//import daos.UnidadDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Imagen;
import modelo.Login;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

private static Controlador instancia;
	
	public Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	
	
	public boolean verficarLogin(String usuario, String password) {
		boolean respuesta = new LoginDAO().verificarLogin(usuario, password);
		return respuesta;
	}
	
	
}
