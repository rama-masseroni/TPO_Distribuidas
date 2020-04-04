package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.UnidadEntity;
import exceptions.UnidadException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Unidad;

public class UnidadDAO {
	public List<Unidad> findByEdificio(int numero) throws UnidadException{
		List<Unidad> resultado = new ArrayList<Unidad>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<UnidadEntity> seleccion = s.createQuery("from UnidadEntity u where u.edificio.codigo = ?").setInteger(0, numero).list();
		s.getTransaction().commit();
		if(seleccion == null)
			throw new UnidadException("No hay unidades pertenecientes al edificio " + numero);
		else {
			System.out.println(seleccion.get(0).getEdificio().getNombre());
			for(UnidadEntity ue: seleccion)
				
				resultado.add(toNegocio(ue));
		}
		return resultado;
	}
	public Unidad findUnidad(int edificio, String piso, String numero) throws UnidadException{
		Unidad resultado;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity seleccion = (UnidadEntity) s.createQuery("from UnidadEntity u where u.piso = ? and u.numero = ? and u.edificio.codigo = ?").setString(0, piso).setString(1, numero).setInteger(2, edificio).uniqueResult();
		s.getTransaction().commit();
//		System.out.println(seleccion.get);
		if(seleccion == null)
			throw new UnidadException("No hay unidades pertenecientes al edificio " + edificio);
		else {
				resultado=toNegocio(seleccion);
		}
		return resultado;
	}

	public void save (Unidad unit) {
		UnidadEntity aGrabar= toEntity(unit);
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update (Unidad unit) {
		UnidadEntity aGrabar= new UnidadEntity(unit.getId(), unit.getPiso(), unit.getNumero(), unit.estaHabitado(), new EdificioDAO().toEntity(unit.getEdificio()));
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
		
	UnidadEntity toEntity(Unidad unit) {
		return new UnidadEntity(unit.getId(), unit.getPiso(), unit.getNumero(), unit.estaHabitado(), new EdificioDAO().toEntity(unit.getEdificio()));
	}

	Unidad toNegocio(UnidadEntity ue) {
		// TODO Auto-generated method stub
		Edificio aux = new EdificioDAO().toNegocio(ue.getEdificio());
		return new Unidad(ue.getIdentificador(), ue.getPiso(), ue.getNumero(), aux);
		
	}
}
