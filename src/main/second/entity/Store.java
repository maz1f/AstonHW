package main.second.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "store-with-employee",
        attributeNodes = {
                @NamedAttributeNode("employees")
        }
)
@Entity
@Getter @Setter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "store_product",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Employee> employees = new HashSet<>();

    @Embedded
    private Address address;

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }
}
