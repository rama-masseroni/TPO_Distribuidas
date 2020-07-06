package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

import entities.UsuarioEntity;
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

	public List<String> getNomApellMedicosXEsp(String esp) {
		String nombre;
		List<String> medicos = new ArrayList<String>();
		List<UsuarioEntity> lu = new ArrayList<UsuarioEntity>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		lu = s.createQuery("from UsuarioEntity as usr where usr.id in (select idUsr from MedicoEntity where especialidad = ?0)").setParameter(0, esp).list();
		s.getTransaction().commit();
		s.close();
		for(UsuarioEntity usr : lu) {
			nombre = usr.getNombre();
			medicos.add(nombre.concat(" " + usr.getApellido()));
		}
		return medicos;
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
