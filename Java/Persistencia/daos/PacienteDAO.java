package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import entities.TurnoEntity;
import hibernate.HibernateUtil;
import modelo.Turno;

public class PacienteDAO {
	
	public List<Turno> getTurnosByIdUsrPac(int idUsrPac) {
		List<Turno> lt = new ArrayList<Turno>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.idUsrPac = ?0").setParameter(0, idUsrPac).list();
		s.getTransaction().commit();
		s.close();
		for(TurnoEntity te : turnos) {
			lt.add(turnoToNegocio(te));
		}
		return lt;		
	}
	
	public boolean getEstadoPagos(int idUsrPac) {
		boolean alDia = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		alDia = (boolean) s.createQuery("SELECT p.pagoAlDia FROM PacienteEntity p where p.idUsr = ?0").setParameter(0, idUsrPac).uniqueResult();
		s.getTransaction().commit();
		s.close();
		return alDia;		
	}

	Turno turnoToNegocio(TurnoEntity t) {
		return new Turno(t.getId(), t.getFecha(), t.getHora(), t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
	}

}
