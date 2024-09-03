package main.second.dao;

import main.second.entity.Product;

public interface ProductDao {

    void saveProduct(Product product);

    void deleteProduct(Product product);


}
