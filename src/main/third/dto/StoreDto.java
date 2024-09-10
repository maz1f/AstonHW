package main.third.dto;

import lombok.Getter;
import lombok.Setter;
import main.second.entity.Address;
import main.second.entity.Store;
import org.springframework.lang.Nullable;

@Setter @Getter
public class StoreDto {
    @Nullable
    private Long id;
    private String name;
    private String address;

    public static StoreDto fromEntity(Store store) {
        StoreDto dto = new StoreDto();
        dto.setId(store.getId());
        dto.setName(store.getName());
        dto.setAddress(store.getAddress().getAddress());
        return dto;
    }

    public static Store fromDto(StoreDto dto) {
        Store store = new Store();
        Address address = new Address(dto.getAddress());
        store.setName(dto.getName());
        store.setAddress(address);
        if (dto.getId() != null)
            store.setId(dto.getId());
        return store;
    }
}
