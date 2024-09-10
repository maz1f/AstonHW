package main.third.controller;

import main.second.enums.MethodType;
import main.third.dto.StoreDto;
import main.third.dto.ViewResponse;
import main.third.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/store/view")
public class StoreViewController {

    private final StoreService storeService;

    public StoreViewController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public String allStores(Model model) {
        List<StoreDto> storeDto = storeService.getAllStores().get();
        model.addAttribute(
                "stores",
                ViewResponse.builder()
                        .method(MethodType.GET)
                        .body(storeDto)
                        .build()
                );
        model.addAttribute("form_store", new StoreDto());
        return "store";
    }

    @GetMapping("/{id}")
    public String viewStore(@PathVariable("id") long id, Model model) {
        Optional<StoreDto> store = storeService.getStore(id);
        model.addAttribute(
                "stores",
                ViewResponse.builder()
                        .method(MethodType.GET)
                        .body(List.of(store.get()))
                        .build()
        );
        model.addAttribute("form_store", new StoreDto());
        return "store";
    }

    @PostMapping
    public String saveStore(@ModelAttribute("form_store") StoreDto storeDto, BindingResult result, Model model) {
        Optional<StoreDto> savedStore = storeService.addStore(storeDto);
        model.addAttribute(
                "stores",
                ViewResponse.builder()
                        .method(MethodType.POST)
                        .body(List.of(savedStore.get()))
                        .build()
        );
        return "store";
    }

    @PutMapping
    public String updateStore(@ModelAttribute("store") StoreDto storeDto, Model model) {
        Optional<StoreDto> updatedStore = storeService.updateStore(storeDto);
        model.addAttribute(
                "employees",
                ViewResponse.builder()
                        .method(MethodType.PUT)
                        .body(List.of(updatedStore.get()))
                        .build()
        );
        return "store";
    }

    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable("id") long id, Model model) {
        Optional<StoreDto> storeDto = storeService.deleteStore(id);
        model.addAttribute(
                "employees",
                ViewResponse.builder()
                        .method(MethodType.DELETE)
                        .body(List.of(storeDto.get()))
                        .build()
        );
        return "store";
    }
}
