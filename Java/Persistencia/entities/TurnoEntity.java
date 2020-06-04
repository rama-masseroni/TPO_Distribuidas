package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Turnos")
public class TurnoEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private int id;
	@Column (name = "fecha")
	private String fecha;
	@Column (name = "hora")
	private String hora;
	@Column (name = "idEsp")
	private int idEsp;
	@Column (name = "idUsrMed")
	private int idUsrMed;
	@Column (name = "idUsrPac")
	private int idUsrPac;
	@Column (name = "idEstado")
	private int idEstado;
	
	public TurnoEntity() {
	}
	
	public TurnoEntity(int id, String fecha, String hora, int idEsp, int idUsrMed, int idUsrPac, int idEstado) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.idEsp = idEsp;
		this.idUsrMed = idUsrMed;
		this.idUsrPac = idUsrPac;
		this.idEstado = idEstado;
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
