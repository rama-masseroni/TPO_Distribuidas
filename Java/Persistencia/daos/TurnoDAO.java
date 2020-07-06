package daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import entities.TurnoEntity;
import hibernate.HibernateUtil;
import modelo.Turno;
import utilitarios.CalculosFechas;

public class TurnoDAO {

	public Turno getTurnoIndividual(int idUsrMed, String fecha, String hora) {
		Timestamp ts = CalculosFechas.getInstancia().deStringATimestamp(fecha, hora);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		TurnoEntity te = (TurnoEntity) s.createQuery("from TurnoEntity t where t.idUsrMed = ?0 and t.fecha = ?1")
				.setParameter(0, idUsrMed).setParameter(1, ts).uniqueResult();
		s.getTransaction().commit();
		s.close();
		Turno turno = new Turno();
		if(te != null)
			turno = toNegocio(te);
		return turno;
	}

	public List<Turno> getTurnosWithData(String fecha, String hora, String especialidad, int idMed) {
		Timestamp ts = CalculosFechas.getInstancia().deStringATimestamp(fecha, hora);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> select = (List<TurnoEntity>) s
				.createQuery("from TurnoEntity t where t.especialidad = ?0 and t.estado = ?1 and t.idUsrMed = ?2")
				.setParameter(0, especialidad).setParameter(1, "Disponible").setParameter(2, idMed).list();
		s.getTransaction().commit();
		s.close();
		List<Turno> result = new ArrayList<Turno>();
		Timestamp comparo;
		for (TurnoEntity te : select) {
			comparo = new Timestamp(te.getFecha().getTime());
			comparo.setHours(0);
			comparo.setMinutes(0);
			if (comparo.before(ts))
				result.add(toNegocio(te));
		}
		return result;
	}

	public List<Turno> getProxTurnosPaciente(int idUsrPac) {
		List<Turno> lt = new ArrayList<Turno>();
		Timestamp actual = new Timestamp(System.currentTimeMillis());
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnosPaciente = s.createQuery("from TurnoEntity t where t.idUsrPac = ?0 and t.fecha >= ?1")
				.setParameter(0, idUsrPac).setParameter(1, actual).list();
		s.getTransaction().commit();
		s.close();
		for (TurnoEntity te : turnosPaciente)
			lt.add(toNegocio(te));
		return lt;
	}

	public List<Turno> getProxTurnosMedico(int idUsrMed) {
		List<Turno> lt = new ArrayList<Turno>();
		Timestamp actual = new Timestamp(System.currentTimeMillis());
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnosMed = s.createQuery("from TurnoEntity t where t.idUsrMed = ?0 and t.fecha >= ?1")
				.setParameter(0, idUsrMed).setParameter(1, actual).list();
		s.getTransaction().commit();
		s.close();
		for (TurnoEntity te : turnosMed)
			lt.add(toNegocio(te));
		return lt;
	}

	public String espDelDia(int idUsrMed, String fecha) {
		Timestamp inicioDia = CalculosFechas.getInstancia().deStringATimestamp(fecha, "00:00");
		Timestamp finDia = CalculosFechas.getInstancia().deStringATimestamp(fecha, "23:59");
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		String especialidad = (String) s.createQuery(
				"select t.especialidad from TurnoEntity t where t.idUsrMed = ?0 and t.fecha >= ?1 and t.fecha <= ?2")
				.setParameter(0, idUsrMed).setParameter(1, inicioDia).setParameter(2, finDia).setMaxResults(1)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		return especialidad;
	}

	public List<Turno> turnosEnFecha(int idUsrMed, String fecha) {
		List<Turno> lt = new ArrayList<Turno>();
		Timestamp inicioDia = CalculosFechas.getInstancia().deStringATimestamp(fecha, "00:00");
		Timestamp finDia = CalculosFechas.getInstancia().deStringATimestamp(fecha, "23:59");
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<TurnoEntity> turnosMed = s
				.createQuery("from TurnoEntity t where t.idUsrMed = ?0 and t.fecha >= ?1 and t.fecha <= ?2")
				.setParameter(0, idUsrMed).setParameter(1, inicioDia).setParameter(2, finDia).list();
		s.getTransaction().commit();
		s.close();
		for (TurnoEntity te : turnosMed)
			lt.add(toNegocio(te));
		return lt;
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

	public void delete(Turno t) {
		TurnoEntity te = toEntity(t);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(te);
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
