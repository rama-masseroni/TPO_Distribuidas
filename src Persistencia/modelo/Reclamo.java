package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.PersonaDAO;
import daos.ReclamoDAO;
import exceptions.ReclamoException;
import views.ImagenView;
import views.ReclamoView;

public class Reclamo {

	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicación;
	private String descripcion;
	private Unidad unidad;
	private String estado;
	private List<Imagen> imagenes;
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicación, String descripcion, Unidad unidad, String estado) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicación = ubicación;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		imagenes = new ArrayList<Imagen>();
	}

	public void agregarImagen(String direccion, String tipo) throws ReclamoException {
		Imagen imagen = new Imagen(direccion, tipo);
		imagenes.add(imagen);
		imagen.save(imagen, numero);
	}
	
	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public String getUbicación() {
		return ubicación;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public String getEstado() {
		return estado;
	}
	
	public List<Imagen> getImagenes(){
		return this.imagenes;
	}
	
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}

	public void save(Reclamo reclamo) {
		new ReclamoDAO().save(reclamo);
	}
	
	public void update(Reclamo reclamo) {
		new ReclamoDAO().update(reclamo);
	}
	
	public ReclamoView toView() {
		List<ImagenView> iv = new ArrayList<ImagenView>();
		for(Imagen i : imagenes) {
			iv.add(i.toView());
		}
		return new  ReclamoView(numero, usuario, edificio, ubicación, descripcion, unidad, iv);
	}
}
