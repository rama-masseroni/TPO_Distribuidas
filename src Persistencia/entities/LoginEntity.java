package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.Persona;

@Entity
@Table(name="usrlogin")
public class LoginEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuarioid")
	private int id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "password")
	private String password;
//	@OneToOne(targetEntity=PersonaEntity.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "personaID")
	private String personaID;
	
	public LoginEntity () {}

	public LoginEntity(String usuario, String password, String persona) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.personaID = persona;
	} 

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonaID() {
		return personaID;
	}

	public void setPersonaID (String personaID) {
		this.personaID = personaID;
	}
}
