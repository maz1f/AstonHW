package main.second.dao.impl;

import main.second.SessionFactoryUtil;
import main.second.dao.ProductDao;
import main.second.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDaoImpl implements ProductDao {

    private Session session;
    private Transaction transaction;

    private void initSession() {
        session = SessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void closeSession() {
        transaction.commit();
        session.close();
    }

    @Override
    public void saveProduct(Product product) {
        initSession();
        session.persist(product);
        closeSession();
    }
    

    @Override
    public void deleteProduct(Product product) {
        initSession();
        session.remove(product);
        closeSession();
    }
}
