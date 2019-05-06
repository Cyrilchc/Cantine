package fr.metz.iut.licencepro.projet.metier.manager;

import java.util.logging.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public abstract class HibernateFactory<T> {
    private Class<T> tType;

    HibernateFactory(Class<T> tType) {
        this.tType = tType;
    }

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        try {
            Configuration cfg = new Configuration();
            sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            Logger.getLogger(HibernateFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory.openSession();
    }

    public T create(T t) {
        Session session = getSession();
        session.beginTransaction();
        int id = (int) session.save(t);
        session.getTransaction().commit();
        session.close();
        return read(id);
    }

    public T read(int id) {
        Session session = getSession();
        T t = session.get(tType, id);
        session.close();
        return t;
    }

    public void delete(T t) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    public void update(T t) {
        Session session = getSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }
}
