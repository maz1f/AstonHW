package main.third.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import main.second.enums.Os;

@Getter
public class LaptopDto extends ProductDto {
    private final Os os;
    private final String model;

    @Builder
    public LaptopDto(ProductDto productDto, Os os, String model) {
        super(productDto);
        this.os = os;
        this.model = model;
    }
}
