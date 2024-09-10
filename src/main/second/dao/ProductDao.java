package main.second.dao;

import main.second.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ProductDao {

    Optional<Product> getProductById(long id);
    Optional<Product> getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getAllProductsWithStores();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Product product);


}
