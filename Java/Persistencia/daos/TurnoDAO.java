package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import entities.TurnoEntity;
import hibernate.HibernateUtil;
import modelo.Turno;

public class TurnoDAO {
	
	public List<Turno> getTurnosByPaciente(int idUsrPac) {
		List<Turno> lt = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnosPaciente = s.createQuery("from TurnoEntity t where t.idUsrPac = ?0").setParameter(0, idUsrPac).list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity te : turnosPaciente) {
			lt.add(toNegocio(te));
		}
		return lt;
	}
	
	public Turno getTurnoIndividual(int idUsrMed, String fecha, String hora) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity te = (TurnoEntity) s.createQuery("from TurnoEntity t where t.idUsrMed = ?0 and t.fecha = ?1 and t.hora = ?2").setParameter(0, idUsrMed).setParameter(1, fecha).setParameter(2, hora).uniqueResult();
		s.getTransaction().commit();
		s.close();
		Turno turno = toNegocio(te);
		return turno;		
	}
	
	public void update(Turno t) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		TurnoEntity te = toEntity(t);
		s.beginTransaction();
		s.update(te);
		s.getTransaction().commit();
		s.close();		
	}
	
	public void save(Turno t) {
		TurnoEntity nuevo = toEntity(t);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(nuevo);
		s.getTransaction().commit();
		s.close();
	}
	
	TurnoEntity toEntity(Turno t) {
		return new TurnoEntity(t.getId(), t.getFecha(), t.getHora(), t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
	}

	Turno toNegocio(TurnoEntity t) {
		return new Turno(t.getId(), t.getFecha(), t.getHora(), t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
	}
	
}
