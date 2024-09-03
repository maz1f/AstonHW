package main.second.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.second.enums.Os;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Laptop extends Product {
    @Enumerated(EnumType.STRING)
    private Os os;

    private String model;

    public Laptop(String name, double price, Os os, String model) {
        super(name, price);
        this.os = os;
        this.model = model;
    }
}
