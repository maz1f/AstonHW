package main.third.service.impl;

import main.second.dao.StoreDao;
import main.second.entity.Store;
import main.third.dto.StoreDto;
import main.third.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;

    public StoreServiceImpl(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public Optional<StoreDto> getStore(long id) {
        Optional<Store> store = storeDao.getStoreById(id);
        return store.map(StoreDto::fromEntity);
    }

    @Override
    public Optional<List<StoreDto>> getAllStores() {
        Optional<List<Store>> allStores = storeDao.getAllStores();
        List<StoreDto> res = allStores.stream()
                .flatMap(List::stream)
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
        return Optional.of(res);

    }

    @Override
    public Optional<StoreDto> addStore(StoreDto store) {
        Optional<Store> savedStore = storeDao.saveStore(StoreDto.fromDto(store));
        return savedStore.map(StoreDto::fromEntity);
    }

    @Override
    public Optional<StoreDto> updateStore(StoreDto store) {
        Optional<Store> updatedStore = storeDao.updateStore(StoreDto.fromDto(store));
        return updatedStore.map(StoreDto::fromEntity);
    }

    @Override
    public Optional<StoreDto> deleteStore(long id) {
        Optional<Store> deletedStore = storeDao.deleteStore(id);
        return deletedStore.map(StoreDto::fromEntity);
    }
}
