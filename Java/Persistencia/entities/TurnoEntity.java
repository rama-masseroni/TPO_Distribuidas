package entities;

import java.sql.Timestamp;
import javax.persistence.Column;
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
	private Timestamp fecha;
	@Column (name = "especialidad")
	private String especialidad;
	@Column (name = "estado")
	private String estado;
	@Column (name = "idUsrMed")
	private int idUsrMed;
	@Column (name = "idUsrPac")
	private int idUsrPac;
	
	public TurnoEntity() {
	}
	
	public TurnoEntity(int id, Timestamp fecha, String esp, String estado, int idUsrMed, int idUsrPac) {
		this.id = id;
		this.fecha = fecha;
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
	
	public Timestamp getFecha() {
		return fecha;
	}
	
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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

	@Override
	public String toString() {
		return "NuevoTurnoEntity [id=" + id + ", fecha=" + fecha + ", especialidad=" + especialidad + ", estado="
				+ estado + ", idUsrMed=" + idUsrMed + ", idUsrPac=" + idUsrPac + "]";
	}	
		
}
