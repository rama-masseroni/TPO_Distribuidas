package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.PersonaEntity;
import hibernate.HibernateUtil;
import modelo.Persona;

public class PersonaDAO {
	
	public List<Persona> getPersonasDAO(){
		List<Persona> resultado= new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<PersonaEntity> seleccion= s.createQuery("from PersonaEntity").list();
		s.getTransaction().commit();
		s.close();
		for(PersonaEntity pe : seleccion) {
			resultado.add(toNegocio(pe));
		}
		return resultado;
	}
	public Persona getPersonaDAO(String documento){
		Persona resultado= null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PersonaEntity seleccion= (PersonaEntity) s.createQuery("from PersonaEntity p where p.documento = ?").setString(0, documento).uniqueResult();
		s.getTransaction().commit();
		s.close();
		resultado = toNegocio(seleccion);
		return resultado;
	}
	
	public void save(Persona persona) {
		PersonaEntity aGrabar = toEntity(persona);
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update (Persona persona) {
		PersonaEntity aGrabar= toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}

	public void delete(Persona persona) {
		// TODO Auto-generated method stub
		PersonaEntity aGrabar= toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	PersonaEntity toEntity(Persona p) {
		return new PersonaEntity(p.getDocumento(), p.getNombre());
	}
	
	Persona toNegocio(PersonaEntity pe) {
		// TODO Auto-generated method stub
		return new Persona(pe.getDocumento(), pe.getNombre());
	}
}
