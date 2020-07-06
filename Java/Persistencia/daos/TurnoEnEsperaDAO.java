package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.TurnoEnEsperaEntity;
import hibernate.HibernateUtil;
import modelo.TurnoEnEspera;

public class TurnoEnEsperaDAO {
	
	public int countPacientesEsperando(String especialidad, int idUsrMed) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEnEsperaEntity> turnos = (List<TurnoEnEsperaEntity>) s.createQuery("select te from TurnoEnEsperaEntity te where te.especialidad = ?0 and te.idUsrMed = ?1").setParameter(0, especialidad).setParameter(1, idUsrMed).list();
		s.getTransaction().commit();
		s.close();
		return turnos.size();
	}
	
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
