package views;

import java.util.List;

public class UsuarioView {

	private String username, password, nombre, apellido, fechaDeNacimiento;
	private int id, dni;
	private char sexo;
	private List<RolView> roles;
	
	public UsuarioView() {
	}

	public UsuarioView(int id, String username, String password, String nombre, String apellido, String fechaDeNacimiento, int dni, char sexo, List<RolView> lr) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.dni = dni;
		this.sexo = sexo;
		this.roles = lr;
	}

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

	public List<RolView> getRoles() {
		return roles;
	}

	public void setRoles(List<RolView> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UsuarioView [username=" + username + ", password=" + password + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fechaDeNacimiento=" + fechaDeNacimiento + ", id=" + id + ", dni=" + dni + ", sexo="
				+ sexo + ", roles=" + roles + "]";
	}

}
