/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Eduardo
 */
public class DaoManagerHiber {

    private static final SessionFactory sessionFactory;
    private static Session s;

    static {

        try {

            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            s = sessionFactory.openSession();

        } catch (Throwable th) {

            System.err.println("Enitial SessionFactory creation failed" + th);

            throw new ExceptionInInitializerError(th);

        }

    }

    public static void persist(Object o) {
        Transaction tr = s.beginTransaction();

        s.save(o);

        tr.commit();

        s.flush();
    }

    public static List recover(String hql) {

        Transaction tr = s.beginTransaction();

        Query query = s.createQuery(hql);

        s.flush();

        return query.list();
    }

    public static void update(Object o) {

        Transaction tr = s.beginTransaction();

        s.update(o);

        tr.commit();

        s.flush();
    }

    public static void delete(Object o) {

        Transaction tr = s.beginTransaction();

        s.delete(o);

        tr.commit();

        s.flush();
    }

    public static void main(String args[]) {
        SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
        se.create(true, true);

    }
}
