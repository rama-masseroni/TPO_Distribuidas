package modelo;

import java.io.Serializable;

public class Rol implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6219529356041815669L;
	private int idUsr;
	private String nombreRol;
	
	public Rol() {
	}
	
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
