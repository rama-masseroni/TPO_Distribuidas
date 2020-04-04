package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import daos.PersonaDAO;
import entities.DueñoEntity;
import entities.PersonaEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;

public class DuenioDAO {
	public List<Persona> findDueniosByUnidad(int numero) throws PersonaException{
		List<Persona> resultado = new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<DueñoEntity> seleccion = s.createQuery("from DueñoEntity de where de.identificador = ?").setInteger(0, numero).list();
		s.getTransaction().commit();
		if(seleccion == null)
			throw new PersonaException("No hay un dueño para la unidad N° " + numero);
		else {
			for(DueñoEntity de: seleccion)
				resultado.add(new PersonaDAO().getPersonaDAO(de.getDocumento()));
		}
		return resultado;
	}
	
	public void save (int idUnidad, String documento) {
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<DueñoEntity> selección = s.createQuery("from DueñoEntity").list();
		int indice = selección.size();
//		indice++;
		DueñoEntity aGrabar= toEntity(indice, idUnidad, documento);
		aGrabar.setId(indice);
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update (int idDueño, int idUnidad, String documento) {
		DueñoEntity aGrabar= new DueñoEntity(idDueño, idUnidad, documento);
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
		
	DueñoEntity toEntity(int id, int idUnidad, String documento) {
		return new DueñoEntity(id, idUnidad, documento);
	}
	
	PersonaEntity toNegocio(PersonaEntity persona) {
		return new PersonaEntity(persona.getDocumento(), persona.getNombre());
	}
}
