package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import daos.PersonaDAO;
import daos.UnidadDAO;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.EdificioView;
import views.UnidadView;

public class Unidad {

	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	private Edificio edificio;
	private List<Persona> duenios;
	private List<Persona> inquilinos;
	

	
	public Unidad(int id, String piso, String numero, Edificio edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public void transferir(Persona nuevoDuenio) {
		duenios = new ArrayList<Persona>();
		duenios.add(nuevoDuenio);
		new UnidadDAO().update(this);
	}
	
	public void agregarDuenio(Persona duenio) {
		this.duenios.add(duenio);
		System.out.println("Se añadió a " +duenio.getNombre()+ "como dueño de la unidad n° "+ this.id);
		new UnidadDAO().update(this);
		new DuenioDAO().save(this.id, duenio.getDocumento());
		
	}
	
	public void alquilar(Persona inquilino) throws UnidadException {
		if(!this.habitado) {
			this.habitado = true;
			inquilinos = new ArrayList<Persona>();
			inquilinos.add(inquilino);
			new UnidadDAO().update(this);
		}
		else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Persona inquilino) {
		inquilinos.add(inquilino);
		new UnidadDAO().update(this);
		new InquilinoDAO().save(this.id, inquilino.getDocumento());

	}
	
	public boolean estaHabitado() {
		return habitado;
	}
	
	public void liberar() {
		this.inquilinos = new ArrayList<Persona>();
		this.habitado = false;
		new InquilinoDAO().delete(this.id);
	}
	
	public void habitar() throws UnidadException {
		if(this.habitado)
			throw new UnidadException("La unidad ya esta habitada");
		else {
			this.habitado = true;
			new UnidadDAO().update(this);
		}
	}
	
	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	
	public Edificio getEdificio() {
		return edificio;
	}

	public List<Persona> getDuenios() {
		List<Persona> duenios = new ArrayList<Persona>();
		try {
			duenios = new DuenioDAO().findDueniosByUnidad(getId());
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return duenios;
	}

	public List<Persona> getInquilinos() {
		List<Persona> inquilinos = new InquilinoDAO().findByUnidad(this.id);
		return inquilinos;
	}

	public UnidadView toView() {
		EdificioView auxEdificio = edificio.toView();
		return new UnidadView(id, piso, numero, habitado, auxEdificio);
	}

}
