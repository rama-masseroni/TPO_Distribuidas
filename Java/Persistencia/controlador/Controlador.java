package controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import daos.LoginDAO;
import daos.EdificioDAO;
import daos.ImagenDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
//import daos.UnidadDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Imagen;
import modelo.Login;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

private static Controlador instancia;
	
	public Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public void añadirUnidadesAEdificio(int codigo){
		Edificio aux = new EdificioDAO().buscarEdificio(codigo);
		try {
			List<Unidad> lu =new UnidadDAO().findByEdificio(codigo);
			for(Unidad u : lu) {
				aux.agregarUnidad(u);
				System.out.println("Se agregó la unidad N° " + u.getId() + "al edificio N° "+aux.getCodigo());
			}
		} catch (UnidadException e) {
				// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		new EdificioDAO().save(aux);
	}
	
	public EdificioView findEdificio(int codigo) {
		EdificioView resultado=null;
		try {
			Edificio aux = new Controlador().buscarEdificio(codigo);
			resultado= aux.toView();
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
		
	}
	
	public List<PersonaView> getPersonas(){
		List<Persona> aux = new PersonaDAO().getPersonasDAO();
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		for(Persona e:aux)
			resultado.add(e.toView());
		return resultado;
	}
	
	//FUNCIONA
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws UnidadException{
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio;
		try {
			edificio = buscarEdificio(codigo);
			List<Unidad> unidades = edificio.getUnidades();
			for(Unidad aux : unidades)
				resultado.add(aux.toView());
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		
		return resultado;
	}
	
	public Set<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
		Edificio edificio = buscarEdificio(codigo);
		Set<PersonaView> habilitados = new HashSet<PersonaView>();
		List<Unidad> unidades;
		List<Persona> inquilinos;
		List<Persona> duenios;
		unidades = edificio.getUnidades();
		for(Unidad unidad : unidades) {
			duenios = unidad.getDuenios();
			for(Persona d : duenios)
				habilitados.add(d.toView());	
			inquilinos = unidad.getInquilinos();
			for(Persona i : inquilinos)
				habilitados.add(i.toView());
		}
		return habilitados;
	}
	
	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> u;
		List<Persona> duenios;
		u = edificio.getUnidades();
		for(Unidad aux: u) {
			duenios = aux.getDuenios();
			for(Persona d : duenios)
				resultado.add(d.toView());
		}	
		return resultado;
	}

	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.habitantes();
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		if(!duenios.isEmpty())
			for(Persona persona : duenios)
				resultado.add(persona.toView());
		else
			System.out.println("La lista está vacía.");
		return resultado;
	}

	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		if(unidad == null)
			throw new UnidadException("No existe la unidad N° "+ codigo);
		List<Persona> inquilinos = new InquilinoDAO().findByUnidad(unidad.getId());
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

//	FUNCIONA
	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Persona persona = new Controlador().buscarPersona(documento);
		Unidad unidad = new Controlador().buscarUnidad(codigo, piso, numero);
		
		unidad.agregarDuenio(persona);	
	}

	public void alquilarUnidad(int edificio, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(edificio, piso, numero);
		Persona persona = buscarPersona(documento);
		new Controlador().agregarInquilinoUnidad(unidad.getEdificio().getCodigo(), unidad.getPiso(), unidad.getNumero(), persona.getDocumento());
		unidad.alquilar(persona);
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarInquilino(persona);
		System.out.println("Se agregó a la persona " + persona.getNombre() + "a la unidad N° " + unidad.getId());
	}
//	FUNCIONA
	public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();
	}
//	FUNCIONA
	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		persona.save(persona);
	}
	
//	FUNCIONA
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		if(persona==null)
			throw new PersonaException("No existe una persona con documento " + documento);
		persona.delete(persona);
	}
	
	public List<ReclamoView> reclamosPorEdificio(int codigo){
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> res = new ReclamoDAO().buscarReclamosPorEdificio(codigo);
		for(Reclamo r : res) {
			List<Imagen> imagenes = new ImagenDAO().buscarImagenes(r.getNumero());
			r.setImagenes(imagenes);
			resultado.add(r.toView());
		}
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> res = new ArrayList<ReclamoView>();
		List<Reclamo> resultado = new ReclamoDAO().buscarReclamosPorUnidad(codigo, piso, numero);
		for(Reclamo r : resultado) {
			List<Imagen> imagenes = new ImagenDAO().buscarImagenes(r.getNumero());
			r.setImagenes(imagenes);
			res.add(r.toView());
		}
		return res;
	}
	
	public ReclamoView reclamosPorNumero(int numero) throws ReclamoException {
		ReclamoView resultado;
		Reclamo res = buscarReclamo(numero);
		List<Imagen> imagenes = new ImagenDAO().buscarImagenes(res.getNumero());
		res.setImagenes(imagenes);
		resultado = res.toView();
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorPersona(String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> res = new ReclamoDAO().buscarReclamosPorPersona(documento);
		for(Reclamo r  : res) {
			List<Imagen> imagenes = new ImagenDAO().buscarImagenes(r.getNumero());
			r.setImagenes(imagenes);
			resultado.add(r.toView());
		}
		return resultado;
	}
 
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicación, String descripcion) throws EdificioException, UnidadException, PersonaException, NumberFormatException, ReclamoException {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicación, descripcion, unidad, "Nuevo");
		reclamo.save(reclamo);;
		System.out.println("AGREGADO");
		return reclamo.getNumero();
	}
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		if (reclamo == null) {
			System.out.println("No existe el reclamo");
		} else {
			Imagen imagen = new Imagen(direccion, tipo);
			reclamo.agregarImagen(direccion, tipo);
			//reclamo.update(reclamo);
			imagen.save(imagen, numero);
			System.out.println("SE AGREGO LA SIGUIENTE DIRECCION: " +  direccion); 
		}
	}
	
	public boolean registrarUsuario(String usuario, String password, String persona) throws PersonaException {
		boolean b = false;
		Login login = new Login(usuario, password, persona);
		boolean verificar = new LoginDAO().existeUsrDocumento(persona);
		if(verificar == true) {
			System.out.println("Un usuario ya existe con el documento ingresado");
		} else {
			verificar = new LoginDAO().existeUsuario(usuario);
			if(verificar == false) {
				login.save();
				b = true;
				System.out.println("Se registro el Usuario");
			} else System.out.println("Ya existe un usuario con ese nombre");
		}
		return b;
		
	}
	
	public boolean verficarLogin(String usuario, String password) {
		boolean respuesta = new LoginDAO().verificarLogin(usuario, password);
		return respuesta;
	}
	
	public boolean verificarDuenio(String documento, int numero) throws PersonaException {
		boolean resultado = false;
		List<Persona> duenios = new DuenioDAO().findDueniosByUnidad(numero);
		for(Persona p : duenios) {
			if(p.getDocumento().equals(documento))
				resultado = true;
		}
		return resultado;
	}
	
	public void cambiarEstado(int numero, String estado) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
		reclamo.update(reclamo);
	}
	
	private Edificio buscarEdificio(int codigo) throws EdificioException {
		List<Edificio> aux = new EdificioDAO().listaGetAll();
		Edificio resultado = new Edificio(0, null, null);
		for(Edificio e:aux) {
			if(e.getCodigo() == codigo) {
				resultado = e;
			}
		}
		return resultado;
		
	}

	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<Unidad> auxU = new UnidadDAO().findByEdificio(codigo);
		Unidad unidad = new Unidad(0, null, null, null);
		for(Unidad scroll : auxU) {
			if(scroll.getEdificio().getCodigo() == codigo && piso.compareTo(scroll.getPiso()) == 0 && numero.compareTo(scroll.getNumero()) == 0) {
				unidad=scroll;
			}
		}	
		return unidad;
	}
	
	private Persona buscarPersona(String documento) throws PersonaException {
		List<Persona> aux= new PersonaDAO().getPersonasDAO();
		Persona resultado= new Persona(documento, null);
		for(Persona paux : aux) {
			if(documento.compareTo(paux.getDocumento()) == 0) {
				resultado = paux;
			}
		}
		return resultado;
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException {
		return new ReclamoDAO().buscarReclamo(numero);
	}

	public String getDocumentoFromLogin(String usuario) {
		return new LoginDAO().getDocumentoFromUsr(usuario);
	}
}
