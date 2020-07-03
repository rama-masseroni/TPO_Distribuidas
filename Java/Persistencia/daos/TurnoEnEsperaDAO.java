package daos;

import org.hibernate.Session;
import entities.TurnoEnEsperaEntity;
import hibernate.HibernateUtil;
import modelo.TurnoEnEspera;

public class TurnoEnEsperaDAO {
	
	public void save(TurnoEnEspera t) {
		TurnoEnEsperaEntity nuevo = toEntity(t);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(nuevo);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(TurnoEnEspera t) {
		TurnoEnEsperaEntity turno = toEntity(t);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(turno);
		s.getTransaction().commit();
		s.close();
	}
	
	public TurnoEnEspera toNegocio(TurnoEnEsperaEntity t) {
		return new TurnoEnEspera(t.getEspecialidad(), t.getIdUsrPac(), t.getIdUsrMed());
	}
	
	public TurnoEnEsperaEntity toEntity(TurnoEnEspera t) {
		return new TurnoEnEsperaEntity(t.getEspecialidad(), t.getIdUsrPac(), t.getIdUsrMed());
	}

}
