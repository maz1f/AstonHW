package main.third.dto;

import lombok.*;

@Getter
public class MonitorDto extends ProductDto{
    private final double ghz;
    private final String manufacturer;

    @Builder
    public MonitorDto(ProductDto productDto, double ghz, String manufacturer) {
        super(productDto);
        this.ghz = ghz;
        this.manufacturer = manufacturer;
    }
}
