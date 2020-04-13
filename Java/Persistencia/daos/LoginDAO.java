package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;


import entities.LoginEntity;
import hibernate.HibernateUtil;
import modelo.Login;
import modelo.Persona;



public class LoginDAO {
	public boolean existeUsrDocumento(String documento) {
		boolean respuesta;
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		LoginEntity seleccion = (LoginEntity) s.createQuery("from LoginEntity l where l.personaID = ?").setString(0, documento).uniqueResult();
		s.getTransaction().commit();
		if(seleccion == null) {
			respuesta = false;
		} else respuesta = true;
		return respuesta;
	}

	public boolean existeUsuario(String usuario) {
		boolean respuesta;
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		LoginEntity seleccion = (LoginEntity) s.createQuery("from LoginEntity l where l.usuario = ?").setString(0, usuario).uniqueResult();
		s.getTransaction().commit();
		if(seleccion == null) {
			respuesta = false;
		} else respuesta = true;
		return respuesta;
	}
	
	public boolean verificarLogin(String usuario, String password) {
		boolean respuesta;
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		LoginEntity seleccion = (LoginEntity) s.createQuery("from LoginEntity l where l.usuario = ?").setString(0, usuario).uniqueResult();
		s.getTransaction().commit();
		if (seleccion != null) {
			if(seleccion.getPassword().equals(password)) {
				respuesta = true;
			} else respuesta = false;
		} else respuesta = false;
		return respuesta;
	}
	
	public void save(Login login) {
		LoginEntity aGrabar = toEntity(login);
		Session s1 = HibernateUtil.getSessionFactory().openSession();
		s1.beginTransaction();
		s1.save(aGrabar);
		s1.getTransaction().commit();
		s1.close();
	}
	
	public void update(Login login){
		LoginEntity aGrabar = toEntity(login);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	LoginEntity toEntity(Login login) {
		return new LoginEntity(login.getUsuario(), login.getPassword(), login.getDocumento());
	}
	
	Login toNegocio(LoginEntity l) {
		return new Login(l.getUsuario(), l.getPassword(), l.getPersonaID());
	}

	public String getDocumentoFromUsr(String usuario) {
		SessionFactory sf= HibernateUtil.getSessionFactory();
		Session s= sf.getCurrentSession();
		s.beginTransaction();
		LoginEntity seleccion = (LoginEntity) s.createQuery("from LoginEntity l where l.usuario = ?").setString(0, usuario).uniqueResult();
		s.getTransaction().commit();
		return seleccion.getPersonaID();
	}


}
