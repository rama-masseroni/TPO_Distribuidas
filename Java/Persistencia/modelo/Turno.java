package modelo;

public class Turno {
	
	private int id;
	private String fecha, hora;
	private int idEsp, idUsrMed, idUsrPac, idEstado;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public int getIdEsp() {
		return idEsp;
	}
	
	public void setIdEsp(int idEsp) {
		this.idEsp = idEsp;
	}
	
	public int getIdUsrMed() {
		return idUsrMed;
	}
	
	public void setIdUsrMed(int idUsrMed) {
		this.idUsrMed = idUsrMed;
	}
	
	public int getIdUsrPac() {
		return idUsrPac;
	}
	
	public void setIdUsrPac(int idUsrPac) {
		this.idUsrPac = idUsrPac;
	}
	
	public int getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
}
