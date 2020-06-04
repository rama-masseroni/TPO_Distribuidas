package modelo;

public class Rol {
	
	private int idUsr;
	private String nombreRol;
	
	public Rol(int idUsr, String nombreRol) {
		this.idUsr = idUsr;
		this.nombreRol = nombreRol;
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@Override
	public String toString() {
		return "Rol [idUsr=" + idUsr + ", nombreRol=" + nombreRol + "]";
	}
	
}
