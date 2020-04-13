package daos;

import java.util.*;

import org.hibernate.classic.Session;

import entities.PersonaEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.*;

public class ReclamoDAO {
	
	public List<ReclamoEntity> getReclamoPorEdificio(int codigo) {
		List<ReclamoEntity> resultado = new ArrayList<>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> r = s.createQuery("from ReclamoEntity").list();
		for (ReclamoEntity reclamo : r) {
			if(reclamo.getEdificio().getCodigo() == codigo)
				resultado.add(reclamo);
		}
		return resultado;
	}
	
	public Reclamo buscarReclamo(int codigo) throws ReclamoException {
		Reclamo resultado = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<ReclamoEntity> r = new ArrayList<ReclamoEntity>();
		ReclamoEntity reclamo = new ReclamoEntity();
		reclamo = (ReclamoEntity) s.createQuery("FROM ReclamoEntity WHERE idReclamo = ?").setInteger(0, codigo).uniqueResult();
		/*r = s.createQuery("FROM ReclamoEntity").list();
		for(ReclamoEntity  reclamo : r) {
			System.out.println("CODIGO = " + codigo);
			System.out.println("CODDB = " + reclamo.getNumero());
			if(reclamo.getNumero() == codigo) {
				System.out.println("ENTRA ENTRA ENTRA");
				resultado = toNegocio(reclamo);
			}
		}*/
		if(reclamo == null) {
			throw new ReclamoException("No existe el reclamo en la BD");
		} else {
			resultado = toNegocio(reclamo);
			resultado.setNumero(codigo);		
		}
		return resultado;
	}
	
	public List<Reclamo> buscarReclamosPorPersona(String documento){
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<ReclamoEntity> r = new ArrayList<ReclamoEntity>();
		r = s.createQuery("FROM ReclamoEntity").list();
		for(ReclamoEntity reclamo : r) {
			if(documento.compareTo(reclamo.getUsuario().getDocumento()) == 0) {
				resultado.add(toNegocio(reclamo));
			}
		}
		return resultado;
	}
	

	public List<Reclamo> buscarReclamosPorEdificio(int codigo) {
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<ReclamoEntity> r = new ArrayList<ReclamoEntity>();
		r = s.createQuery("FROM ReclamoEntity").list();
		for(ReclamoEntity reclamo : r) {
			if(codigo == reclamo.getEdificio().getCodigo()) {
				resultado.add(toNegocio(reclamo));
			}
		}
		return resultado;
	}
	
	public List<Reclamo> buscarReclamosPorUnidad(int codigo, String piso, String numero) {
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<ReclamoEntity> r = new ArrayList<ReclamoEntity>();
		r = s.createQuery("FROM ReclamoEntity").list();
		for(ReclamoEntity reclamo : r) {
			if(reclamo.getUnidad().getIdentificador() == codigo) {
				if(piso.compareTo(reclamo.getUnidad().getPiso()) == 0) {
					if(numero.compareTo(reclamo.getUnidad().getNumero()) == 0) {
						resultado.add(toNegocio(reclamo));
					}
				}
			}
		}
		return resultado;
	}

	
	public ReclamoEntity toEntity(Reclamo r) {
		return new ReclamoEntity(r.getNumero(), new PersonaDAO().toEntity(r.getUsuario()), new EdificioDAO().toEntity(r.getEdificio()), r.getUbicación(), r.getDescripcion(), new UnidadDAO().toEntity(r.getUnidad()), r.getEstado());
	}
	
	public Reclamo toNegocio(ReclamoEntity re) {
		return new Reclamo(new PersonaDAO().toNegocio(re.getUsuario()), new EdificioDAO().toNegocio(re.getEdificio()), re.getUbicación(), re.getDescripcion(), new UnidadDAO().toNegocio(re.getUnidad()), re.getEstado());
	}
	
	public void save(Reclamo reclamo) {
		ReclamoEntity save = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(save);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Reclamo reclamo) {
		ReclamoEntity update = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(update);
		s.getTransaction().commit();
		s.close();
	}
}
