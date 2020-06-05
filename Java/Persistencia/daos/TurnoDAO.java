package daos;

import java.util.List;

import org.hibernate.Session;
import entities.TurnoEntity;
import hibernate.HibernateUtil;
import modelo.Turno;

public class TurnoDAO {
	
	public List<Turno> getTurnosByPaciente() {
		
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
