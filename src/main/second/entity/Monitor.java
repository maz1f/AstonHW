package main.second.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Monitor extends Product{
    private double ghz;

    private String manufacturer;

    public Monitor(String name, double price, String manufacturer, double ghz) {
        super(name, price);
        this.manufacturer = manufacturer;
        this.ghz = ghz;
    }
}
