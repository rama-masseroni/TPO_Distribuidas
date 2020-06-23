package daos;

import org.hibernate.Session;
import entities.PacienteEntity;
import hibernate.HibernateUtil;
import modelo.Paciente;

public class PacienteDAO {
	
	public boolean getEstadoPagos(int idUsrPac) {
		boolean alDia = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		alDia = (boolean) s.createQuery("select p.pagoAlDia from PacienteEntity p where p.idUsr = ?0").setParameter(0, idUsrPac).uniqueResult();
		s.getTransaction().commit();
		s.close();
		return alDia;		
	}
	
	public void save(Paciente p) {
		PacienteEntity nuevo = toEntity(p);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(nuevo);
		s.getTransaction().commit();
		s.close();
	}
	
	PacienteEntity toEntity(Paciente p) {
		return new PacienteEntity(p.getIdUsr(), p.getPagoAlDia());
	}

	Paciente toNegocio(PacienteEntity pe) {
		return new Paciente(pe.getIdUsr());
	}

}
