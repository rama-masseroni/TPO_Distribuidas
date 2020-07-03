package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import hibernate.HibernateUtil;

public class MedicoDAO {

	public List<String> getEspecialidadesByMedico(int idUsrMed) {
		List<String> le = new ArrayList<String>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		le = s.createQuery("select m.especialidad from MedicoEntity m where m.idUsr = ?0").setParameter(0, idUsrMed)
				.list();
		s.getTransaction().commit();
		s.close();
		return le;
	}

	public List<Integer> getAllIdsMed() {
		List<Integer> idMeds = new ArrayList<Integer>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		idMeds = s.createQuery("select distinct m.idUsr from MedicoEntity m").list();
		s.getTransaction().commit();
		s.close();
		return idMeds;
	}

	public List<String> getAllEspe() {
		List<String> esps = new ArrayList<String>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		esps = s.createQuery("select distinct m.especialidad from MedicoEntity m").list();
		s.getTransaction().commit();
		s.close();
		return esps;
	}

}
