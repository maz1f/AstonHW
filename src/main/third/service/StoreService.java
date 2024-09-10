package main.third.service;

import main.third.dto.StoreDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    Optional<StoreDto> getStore(long id);
    Optional<List<StoreDto>> getAllStores();
    Optional<StoreDto> addStore(StoreDto store);
    Optional<StoreDto> updateStore(StoreDto store);
    Optional<StoreDto> deleteStore(long id);

}
