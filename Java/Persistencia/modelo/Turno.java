package modelo;

public class Turno {
	
	private int id;
	private String fecha, hora, especialidad, estado;
	private int idUsrMed, idUsrPac;
	
	public Turno() {
	}
	
	public Turno(int id, String fecha, String hs, String esp, String estado, int idUsrMed, int idUsrPac) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hs;
		this.especialidad = esp;
		this.estado = estado;
		this.idUsrMed = idUsrMed;
		this.idUsrPac = idUsrPac;
	}
	
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
	
	public String getEspecialidad() {
		return this.especialidad;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
