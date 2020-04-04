package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="edificios")
public class EdificioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int codigo;
	@Column (name= "nombre")
	private String nombre;
	@Column (name= "direccion")
	private String direccion;
	@OneToMany
	@JoinColumn(name = "codigoEdificio")
	private List<UnidadEntity> unidades;
	
 	public EdificioEntity () {}
	
	public EdificioEntity(int codigo, String nombre, String direccion, List<UnidadEntity> unidades) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.unidades = unidades;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<UnidadEntity> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<UnidadEntity> unidades) {
		this.unidades = unidades;
	}
}
