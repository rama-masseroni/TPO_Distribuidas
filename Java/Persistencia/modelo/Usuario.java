package modelo;

import java.util.List;

public class Usuario {
	
	private String username, password, nombre, apellido, fechaDeNacimiento;
	private int id, dni;
	private char sexo;
	private List<Rol> roles;
	
	//Constructor con roles
	public Usuario(int id, String username, String password, String nombre, String apellido, String fechaDeNacimiento, int dni, char sexo, List<Rol> lr) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.dni = dni;
		this.sexo = sexo;
		for(Rol r : lr)
			this.roles.add(r);
	}
	
	// Constructor sin roles
	public Usuario(int id, String username, String password, String nombre, String apellido, String fechaDeNacimiento, int dni, char sexo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.dni = dni;
		this.sexo = sexo;
	}
	
	public boolean verificarLogin(String username, String password) {
		if(this.username.equals(username) && this.password.equals(password))
			return true;
		else
			return false;
	}
	
/*
	
	public void agregarRol(Rol r) {}
	
	public boolean tieneRol(String nombreRol) {}
	
	public Rol obtenerRol(String nombreRol) {}
	
	public void eliminarRol(String nombreRol) {}
	
	public UsuarioView usuarioToView() {}
	
	*/
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fechaDeNacimiento=" + fechaDeNacimiento + ", id=" + id + ", dni=" + dni + ", sexo="
				+ sexo + "]";
	}
	
	
		
	
}
