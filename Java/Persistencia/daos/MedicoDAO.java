//package daos;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.hibernate.Session;
//
//import entities.MedicoEntity;
//import entities.TurnoEntity;
//import hibernate.HibernateUtil;
//import modelo.Turno;
//
//public class MedicoDAO {
//	
//	//AGREGAR UN MÉTODO QUE TRAIGA LOS TURNOS QUE TIENE POR DELANTE (COMPARADNO CON FECHA ACTUAL)
//	public List<Turno> getTurnosByIdUsrMed(int idUsrMed) {
//		List<Turno> lt = new ArrayList<Turno>();
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		List<TurnoEntity> turnos = s.createQuery("from TurnoEntity t where t.idUsrMed = ?0").setParameter(0, idUsrMed).list();
//		s.getTransaction().commit();
//		s.close();
//		for(TurnoEntity te : turnos) {
//			lt.add(turnoToNegocio(te));
//		}
//		return lt;
//	}
//	
//	public List<String> getEspecialidadesByMedico(int idUsrMed) {
//		List<String> le = new ArrayList<String>();
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		le = s.createQuery("select m.especialidad from MedicoEntity m where m.idUsr = ?0").setParameter(0, idUsrMed).list();
//		s.getTransaction().commit();
//		s.close();
//		return le;
//	}
//	
//	Turno turnoToNegocio(TurnoEntity t) {
//		return new Turno(t.getId(), t.getFecha(), t.getHora(), t.getEspecialidad(), t.getEstado(), t.getIdUsrMed(), t.getIdUsrPac());
//	}
//
//}
//
//
//
//
//
//
//
//
