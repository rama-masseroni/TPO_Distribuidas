package modelo;

import java.util.List;

import daos.PacienteDAO;

public class Paciente extends Rol {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3946676232737481944L;
	private int idUsr;
	private boolean pagoAlDia;
	
	public Paciente(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public int getIdUsr() {
		return idUsr;
	}
	
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public boolean getPagoAlDia() {
		return pagoAlDia;
	}
	
	public void setPagoAlDia(boolean pagoAlDia) {
		this.pagoAlDia = pagoAlDia;
	}
	
	@Override
	public String toString() {
		return "Paciente [idUsr=" + idUsr + ", pagoAlDia=" + pagoAlDia + "]";
	}
	
	public boolean alDia() {
		//agregar dao recuperacion de este dato
		if(this.pagoAlDia)
			return true;
		else
			return false;
	}
	
	public List<Turno> misTurnos() {
		List<Turno> lt = new PacienteDAO().getTurnosByIdUsrPac(this.idUsr);
		return lt;
	}
	
	
	
	
	
	
	
	
		
		
}