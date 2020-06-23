package entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utilitarios.CalculosFechas;

@Entity
@Table (name = "Usuarios")
public class UsuarioEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private int id; 
	@Column (name = "username")
	private String username;
	@Column (name = "password")
	private String password;
	@Column (name = "dni")
	private int dni;
	@Column (name = "nombre")
	private String nombre;
	@Column (name = "apellido")
	private String apellido; 
	@Column (name = "fechaDeNacimiento")
	private Date fechaDeNacimiento;
	@Column (name = "sexo")
	private char sexo;
	
	public UsuarioEntity() {	
	}

	public UsuarioEntity(int id, String username, String password, String nombre, String apellido, String fechaDeNacimiento, int dni, char sexo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		Date fNac = CalculosFechas.getInstancia().deStringADateSql(fechaDeNacimiento);
		this.fechaDeNacimiento = fNac;
		this.dni = dni;
		this.sexo = sexo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		Date fNac = CalculosFechas.getInstancia().deStringADateSql(fechaDeNacimiento);
		this.fechaDeNacimiento = fNac;
	}
	
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
}
