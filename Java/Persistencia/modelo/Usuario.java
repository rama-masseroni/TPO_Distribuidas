package modelo;

public class Usuario {
	
	private String username, pwd, name, apellido;
	private int dni;
	
	public Usuario(String id, String pwd, String name, String apellido, int dni) {
		super();
		this.username = id;
		this.pwd = pwd;
		this.name = name;
		this.apellido = apellido;
		this.dni = dni;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPwd() {
		return pwd;
	}
	public String getName() {
		return name;
	}
	public String getApellido() {
		return apellido;
	}
	public int getDni() {
		return dni;
	}
	
}
