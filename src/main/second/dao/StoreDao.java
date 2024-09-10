package main.second.dao;

import main.second.entity.Store;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface StoreDao {

    Optional<Store> getStoreById(long id);
    Optional<List<Store>> getAllStores();
    Optional<List<Store>> getAllStoresWithProductsAndEmployees();
    Optional<Store> saveStore(Store store);
    Optional<Store> updateStore(Store store);
    Optional<Store> deleteStore(long id);

}
