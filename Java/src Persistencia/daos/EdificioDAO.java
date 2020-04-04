package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import entities.EdificioEntity;
import entities.UnidadEntity;
import exceptions.UnidadException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Unidad;
import views.EdificioView;

public class EdificioDAO {
	
	public List<Edificio> listaGetAll(){
		List<Edificio> resultado= new ArrayList<Edificio>();
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		List<EdificioEntity> seleccion = s.createQuery("from EdificioEntity").list();
		s.getTransaction().commit();
//		s.close();
		for(EdificioEntity e : seleccion)
			resultado.add(toNegocio(e));
		return resultado;
	}
	
	public Edificio buscarEdificio(int codigo) {
		Edificio resultado= new Edificio(codigo, null, null);
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		EdificioEntity seleccion = (EdificioEntity) s.createQuery("from EdificioEntity e where e.codigo= ?").setInteger(0, codigo).uniqueResult();
		s.getTransaction().commit();
		resultado=toNegocio(seleccion);
//		s.close();
		return resultado;
	}
	
	public void save(Edificio edificio) {
		EdificioEntity aGrabar = toEntity(edificio);
		Session s1 = HibernateUtil.getSessionFactory().openSession();
		s1.beginTransaction();
		s1.save(aGrabar);
		s1.getTransaction().commit();
		s1.close();
	}
	
	public void update(Edificio edificio){
		EdificioEntity aGrabar = toEntity(edificio);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	EdificioEntity toEntity(Edificio edificio) {
		List<Unidad> u = edificio.getUnidades();
		List<UnidadEntity> ue = new ArrayList<UnidadEntity>();
			for(Unidad aux : u){
				ue.add(new UnidadDAO().toEntity(aux));
			}
		return new EdificioEntity(edificio.getCodigo(),edificio.getNombre(),edificio.getDireccion(), ue);
	}
	
	Edificio toNegocio(EdificioEntity ee) {
		return new Edificio(ee.getCodigo(),ee.getNombre(),ee.getDireccion());
	}

}
