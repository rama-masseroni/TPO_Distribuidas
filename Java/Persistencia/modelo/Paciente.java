package modelo;

public class Paciente {
	
	private int idUsr;
	private boolean pagoAlDia;
	
	public Paciente(int idUsr, boolean pagoAlDia) {
		this.idUsr = idUsr;
		this.pagoAlDia = pagoAlDia;
	}
	
	public int getIdUsr() {
		return idUsr;
	}
	
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public int getPagoAlDia() {
		return pagoAlDia;
	}
	
	public void setPagoAlDia(boolean pagoAlDia) {
		this.pagoAlDia = pagoAlDia;
	}
		
}