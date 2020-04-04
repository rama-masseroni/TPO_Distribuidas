package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.DueñoEntity;
import entities.EdificioEntity;
import entities.ImagenEntity;
import entities.InquilinoEntity;
import entities.LoginEntity;
import entities.PersonaEntity;
import entities.ReclamoEntity;
import entities.UnidadEntity;


 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 	config.addAnnotatedClass(EdificioEntity.class);
        	 	config.addAnnotatedClass(UnidadEntity.class);
        	 	config.addAnnotatedClass(PersonaEntity.class);
        	 	config.addAnnotatedClass(DueñoEntity.class);
        	 	config.addAnnotatedClass(InquilinoEntity.class);
        	 	config.addAnnotatedClass(ReclamoEntity.class);
        	 	config.addAnnotatedClass(ImagenEntity.class);
        	 	config.addAnnotatedClass(LoginEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
