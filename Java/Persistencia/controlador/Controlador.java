package controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import daos.UsuarioDAO;
import modelo.Usuario;

public class Controlador {

private static Controlador instancia;
	
	public Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public Usuario verficarLogin(String username, String password) {
		Usuario usr = new UsuarioDAO().getUsuarioByUsername(username);
		if(usr.verificarLogin(username, password))
			return usr;
		else
			return null;
	}
	
	
}
