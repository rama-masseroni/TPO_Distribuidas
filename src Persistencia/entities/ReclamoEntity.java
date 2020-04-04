package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import modelo.*;

@Entity
@Table(name="reclamos")
public class ReclamoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReclamo", unique = true, nullable = false, precision = 10, scale = 0)
	private int numero;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="documento", referencedColumnName="documento")
	private PersonaEntity usuario;
	@ManyToOne()
	@JoinColumn(name="codigo", referencedColumnName="codigo", nullable=false)
	private EdificioEntity edificio;
	@Column(name="ubicacion")
	private String ubicación;
	@Column(name="descripcion")
	private String descripcion;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	@Column(name="estado")
	private String estado;
	@OneToMany
	private List<ImagenEntity> imagenes;
	
	public ReclamoEntity() {}
	
	public ReclamoEntity(int numero, PersonaEntity usuario, EdificioEntity edificio, String ubicación, String descripcion, UnidadEntity unidad, String estado) {
		this.numero = numero;
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicación = ubicación;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		imagenes = new ArrayList<ImagenEntity>();
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public PersonaEntity getUsuario() {
		return usuario;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}

	public String getUbicación() {
		return ubicación;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public UnidadEntity getUnidad() {
		return unidad;
	}

	public String getEstado() {
		return estado;
	}
	
	public List<ImagenEntity > getImagenes(){
		return this.imagenes;
	}
	
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}

	public void save() {
		
	}
	
	public void update() {
		
	}
}
