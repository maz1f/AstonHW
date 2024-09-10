package main.second.dao;

import main.second.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao {

    protected Session session;
    protected Transaction transaction;

    protected void initSession() {
        session = SessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void closeSession() {
        transaction.commit();
        session.close();
    }
}
