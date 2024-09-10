package main.third.controller;

import main.third.dto.CustomResponse;
import main.third.dto.StoreDto;
import main.third.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    public CustomResponse<?> getStoreById(@PathVariable("id") long id) {
        Optional<StoreDto> store = storeService.getStore(id);
        boolean present = store.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 404)
                .body(List.of(store.get()))
                .build();
    }

    @GetMapping
    public CustomResponse<?> getAllStores() {
        Optional<List<StoreDto>> allStores = storeService.getAllStores();
        return CustomResponse.builder()
                .status(200)
                .body(allStores.get())
                .build();
    }

    @PostMapping
    public CustomResponse<?> addStore(@RequestBody StoreDto store) {
        Optional<StoreDto> storeDto = storeService.addStore(store);
        boolean present = storeDto.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 415)
                .body(List.of(storeDto.get()))
                .build();
    }

    @PutMapping
    public CustomResponse<?> updateStore(@RequestBody StoreDto store) {
        Optional<StoreDto> storeDto = storeService.updateStore(store);
        boolean present = storeDto.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 415)
                .body(List.of(storeDto.get()))
                .build();
    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> deleteStore(@PathVariable("id") long id) {
        Optional<StoreDto> storeDto = storeService.deleteStore(id);
        boolean present = storeDto.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 404)
                .body(List.of(storeDto.get()))
                .build();
    }
}
