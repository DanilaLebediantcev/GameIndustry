package db;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static SessionFactory sessionFactory;

    private HibernateConnection() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    public static void shutDown(){
        getSessionFactory().close();
    }
}