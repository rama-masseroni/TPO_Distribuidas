package modelo;

import java.util.List;

public class Medico extends Rol {
	
	private int idUsr;
	//private List<String> especialidades;
	//private List<Turno> agenda; 
	
	public Medico(int idUsr) {
		super(idUsr, "Medico");
		this.idUsr = idUsr;
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
}