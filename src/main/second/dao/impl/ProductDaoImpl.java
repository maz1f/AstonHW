package main.second.dao.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.second.dao.AbstractDao;
import main.second.dao.ProductDao;
import main.second.entity.Product;
import main.second.entity.Store;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl extends AbstractDao implements ProductDao {

    @Override
    public Product saveProduct(Product product) {
        initSession();

        session.persist(product);

        closeSession();

        return product;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        initSession();

        Product product = session.get(Product.class, id);

        closeSession();

        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        initSession();

        Product product = session.createQuery("from Product where name = :name", Product.class)
                .setParameter("name", name).getSingleResult();

        closeSession();

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAllProductsWithStores() {
        initSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        cq.from(Product.class);

        EntityGraph<?> productWithStores = session.getEntityGraph("product-with-stores");

        List<Product> products = session.createQuery(cq)
                .setHint("jakarta.persistence.fetchgraph", productWithStores)
                .getResultList();

        closeSession();

        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        initSession();

        List<Product> products = session
                .createQuery("from Product p join fetch p.stores", Product.class)
                .getResultList();

        closeSession();

        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        initSession();

        Product updatedProduct = session.merge(product);

        closeSession();

        return updatedProduct;
    }

    @Override
    public Product deleteProduct(Product product) {
        initSession();

        Product p = session.merge(product);

        EntityGraph<?> storesWithProducts = session.getEntityGraph("store-with-products");

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Store> cq = cb.createQuery(Store.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root.get("stores")).where(cb.equal(root.get("id"), p.getId()));

        session.createQuery(cq)
                .setHint("jakarta.persistence.fetchgraph", storesWithProducts)
                .getResultList()
                .forEach(store -> store.removeProduct(p));
        session.remove(p);

        closeSession();

        return p;
    }

}
