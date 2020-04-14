package views;

public class UsuarioView {

	private String name, apellido;
	private int dni;
	
	public UsuarioView(){
		
	}
	
	public UsuarioView(String name, String apellido, int dni) {
		super();
		this.name = name;
		this.apellido = apellido;
		this.dni = dni;
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

	@Override
	public String toString() {
		return "UsuarioView [name=" + name + ", apellido=" + apellido + ", dni=" + dni + "]";
	}
	
	
}
