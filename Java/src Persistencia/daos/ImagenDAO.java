package daos;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.*;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;


public class ImagenDAO {
	
	public List<Imagen> buscarImagenes(int idReclamo){
		List<Imagen> resultado = new ArrayList<Imagen>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<ImagenEntity> i = new ArrayList<ImagenEntity>();
		i = s.createQuery("FROM ImagenEntity").list();
		for(ImagenEntity ie : i) {
			if(idReclamo == ie.getIdReclamo()) {
				resultado.add(toNegocio(ie));
			}
		}
		return resultado;
	}
	
	public void save(Imagen imagen, int numReclamo) throws ReclamoException {
		Reclamo r = new ReclamoDAO().buscarReclamo(numReclamo);
		ImagenEntity save = toEntity(imagen, r);
		System.out.println(save.getDireccion());
		System.out.println(r.getDescripcion());
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(save);
		s.getTransaction().commit();
		s.close();
	}
	
	/*public void update(Imagen imagen) {
		ImagenEntity update = toEntity(imagen);
		update.setNumero(imagen.getNumero());
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(update);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(Imagen imagen) {
		ImagenEntity delete= toEntity(imagen);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(delete);
		s.getTransaction().commit();
		s.close();
	}*/
	
	public ImagenEntity toEntity(Imagen i, Reclamo r) {
		return new ImagenEntity(i.getDireccion(), i.getTipo(), new ReclamoDAO().toEntity(r));
	}
	
	public Imagen toNegocio(ImagenEntity ie) {
		return new Imagen(ie.getDireccion(), ie.getTipo());
	}


	
	
}
