package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "TurnosEnEspera")
public class TurnoEnEsperaEntity {
	@Id
	@Column (name = "especialidad")
	private String especialidad;
	@Column (name = "idUsrPac")
	private int idUsrPac;
	@Column (name = "idUsrMed")
	private int idUsrMed;
	
	public TurnoEnEsperaEntity() {
	}
	
	public TurnoEnEsperaEntity(String especialidad, int idUsrPac, int idUsrMed) {
		this.especialidad = especialidad;
		this.idUsrPac = idUsrPac;
		this.idUsrMed = idUsrMed;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getIdUsrPac() {
		return idUsrPac;
	}

	public void setIdUsrPac(int idUsrPac) {
		this.idUsrPac = idUsrPac;
	}

	public int getIdUsrMed() {
		return idUsrMed;
	}

	public void setIdUsrMed(int idUsrMed) {
		this.idUsrMed = idUsrMed;
	}
	
	@Override
	public String toString() {
		return "TurnoEnEsperaEntity [especialidad=" + especialidad + ", idUsrPac=" + idUsrPac + ", idUsrMed=" + idUsrMed
				+ "]";
	}
	
}