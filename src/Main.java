import jakarta.persistence.EntityGraph;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.second.SessionFactoryUtil;
import main.second.entity.*;
import main.second.enums.Os;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Session s = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();


        // LazyLoadException solution with entity graph
//        EntityGraph entityGraph = s.getEntityGraph("product-with-stores-with-employees");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("jakarta.persistence.fetchgraph", entityGraph);
//
//        Product laptop = s.find(Product.class, 10, properties);
//
//
//        tx.commit();
//        s.close();
//        laptop.getStores().stream()
//                .flatMap(store -> store.getEmployees().stream())
//                .map(Employee::getName)
//                .forEach(System.out::println);



        // LazyLoadException solution with JOIN FETCH

//        String hql = "from Product p join fetch p.stores as stores " +
//                "join fetch stores.employees " +
//                "where p.id=10";
//        Query query = s.createQuery(hql, Product.class);
//        Product p = (Product) query.getSingleResult();
//
//        tx.commit();
//        s.close();
//
//        p.getStores().stream()
//                .flatMap(store -> store.getEmployees().stream())
//                .map(Employee::getName)
//                .forEach(System.out::println);


        // n+1 problem

//        CriteriaBuilder cb = s.getCriteriaBuilder();
//        CriteriaQuery<Store> cq = cb.createQuery(Store.class);
//        Root<Store> root = cq.from(Store.class);
//        cq.select(root);
//        List<Store> stores = s.createQuery(cq).getResultList();
//
//        stores.stream()
//                .flatMap(store -> store.getEmployees().stream())
//                .map(Employee::getName)
//                .forEach(System.out::println);

        // n+1 solution with entity graph

        EntityGraph storesWithEmp = s.getEntityGraph("store-with-employee");

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Store> cq = cb.createQuery(Store.class);
        Root<Store> root = cq.from(Store.class);

        List<Store> stores = s.createQuery(cq)
                .setHint("jakarta.persistence.fetchgraph", storesWithEmp)
                .getResultList();

        stores.stream()
                .flatMap(st -> st.getEmployees().stream())
                .collect(Collectors.toMap(Employee::getName, emp->emp.getStore().getName()))
                .forEach((key, value) -> System.out.println(key + " : " + value));

        tx.commit();
        s.close();


        // n+1 solution with join fetch
//        Query q = s.createQuery("from Store st join fetch st.employees");
//        List<Store> stores = q.getResultList();
//        stores.stream()
//                .flatMap(st -> st.getEmployees().stream())
//                .collect(Collectors.toMap(Employee::getName, emp->emp.getStore().getName()))
//                .forEach((key, value) -> System.out.println(key + " : " + value));
//
//        tx.commit();
//        s.close();

        SessionFactoryUtil.getSessionFactory().close();
    }
}