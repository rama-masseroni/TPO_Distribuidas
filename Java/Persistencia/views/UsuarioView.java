package views;

import java.util.ArrayList;
import java.util.List;

public class UsuarioView {

	private String username, nombre, apellido, fechaDeNacimiento;
	private int dni;
	private char sexo;
	//private List<RolView> roles;
	
	public UsuarioView() {
	}

	public UsuarioView(String username, String nombre, String apellido, String fechaDeNacimiento, int dni, char sexo) {
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.dni = dni;
		this.sexo = sexo;
//		roles = new ArrayList<RolView>();
//		this.roles = lr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		return "UsuarioView [username=" + username + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaDeNacimiento=" + fechaDeNacimiento + ", dni=" + dni + ", sexo=" + sexo + "]";
	}

//	public List<RolView> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<RolView> roles) {
//		this.roles = roles;
//	}

}
