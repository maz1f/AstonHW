package main.second.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NamedEntityGraph(
        name = "product-with-stores",
        attributeNodes = {
                @NamedAttributeNode("stores")
        }
)
@NamedEntityGraph(
        name = "product-with-stores-with-employees",
        attributeNodes = {
                @NamedAttributeNode(value = "stores", subgraph = "stores-with-employees-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "stores-with-employees-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("employees")
                        }
                )
        }
)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor
public abstract class Product {
    @Id
    @SequenceGenerator(name = "product_seq",
            sequenceName = "product_sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private long id;

    private String name;

    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<Store> stores = new HashSet<>();

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
