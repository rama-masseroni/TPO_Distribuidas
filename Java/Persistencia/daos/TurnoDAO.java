package daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import entities.TurnoEntity;
import hibernate.HibernateUtil;
import modelo.Turno;
import utilitarios.CalculosFechas;

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
		Timestamp ts = CalculosFechas.getInstancia().deStringATimestamp(fecha, hora);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity te = (TurnoEntity) s.createQuery("from TurnoEntity t where t.idUsrMed = ?0 and t.fecha = ?1").setParameter(0, idUsrMed).setParameter(1, ts).uniqueResult();
		s.getTransaction().commit();
		s.close();
		Turno turno = toNegocio(te);
		return turno;		
	}
	
	// en prueba
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
	
	public TurnoEntity toEntity(Turno t) {
		String fechaCompleta = t.getFecha().concat(" " + t.getHora() + ":00");
		Timestamp ts = Timestamp.valueOf(fechaCompleta);
		return new TurnoEntity(t.getId(), ts, t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
	}

	public Turno toNegocio(TurnoEntity t) {
		String fechaCompleta = t.getFecha().toString();
		String fecha = fechaCompleta.substring(0, 10);
		String hora = fechaCompleta.substring(11, 16);
		return new Turno(t.getId(), fecha, hora, t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
	}
		
}
