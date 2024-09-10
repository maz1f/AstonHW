package main.third.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.second.enums.Os;
import main.second.enums.ProductType;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    
    private String name;
    private double price;
    private ProductType type;

    @Nullable
    private Os os;
    @Nullable
    private String model;

    @Nullable
    private double ghz;
    @Nullable
    private String manufacturer;
}
