package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Roles")
public class RolEntity {
	@Id
	@Column (name = "idUsr")
	private int idUsr;
	@Column (name = "nombreRol")
	private String nombreRol;
	
	public RolEntity() {
	}
	
	public RolEntity(int idUsr, String nombreRol) {
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
}
