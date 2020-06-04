package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

import entities.RolEntity;
import hibernate.HibernateUtil;
import modelo.Rol;

public class RolDAO {
	
	public List<Rol> getRolesByIdUsr(int idUsr) {
		List<Rol> lr = new ArrayList<Rol>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		//asi trae todo, cuando agrego clausula where da error
		List<RolEntity> roles = s.createQuery("from RolEntity").list();
		s.getTransaction().commit();
		s.close();
		for(RolEntity re : roles) {
			lr.add(toNegocio(re));
		}
		return lr;		
	}
	
	public void save(Rol r) {
		RolEntity nuevo = toEntity(r);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(nuevo);
		s.getTransaction().commit();
		s.close();
	}
	
	RolEntity toEntity(Rol r) {
		return new RolEntity(r.getIdUsr(), r.getNombreRol());
	}

	Rol toNegocio(RolEntity re) {
		return new Rol(re.getIdUsr(), re.getNombreRol());
	}

}
