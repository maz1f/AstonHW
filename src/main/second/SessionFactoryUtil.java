package main.second;

import main.second.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Monitor.class);
            configuration.addAnnotatedClass(Laptop.class);
            configuration.addAnnotatedClass(Store.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(JobTitle.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

}
