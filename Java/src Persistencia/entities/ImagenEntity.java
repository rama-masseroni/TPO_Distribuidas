package entities;

import javax.persistence.*;

import views.ImagenView;

@Entity
@Table(name="imagenes")
public class ImagenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero", unique = true, nullable = false, precision = 10, scale = 0)
	private int numero;
	@Column(name="path")
	private String direccion;
	@Column(name="tipo")
	private String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity idReclamo;

	public ImagenEntity() {}
	
	public ImagenEntity(String direccion, String tipo, ReclamoEntity idReclamo) {
		this.direccion = direccion;
		this.tipo = tipo;
		this.idReclamo = idReclamo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdReclamo() {
		return idReclamo.getNumero();
	}

	public void setIdReclamo(int idReclamo) {
		this.idReclamo.setNumero(idReclamo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
